import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { IconFieldModule } from 'primeng/iconfield';
import { InputTextModule } from 'primeng/inputtext';
import { TableModule } from 'primeng/table';
import { VehicleService } from '../../core/services/vehicle-service';
import { debounceTime, distinctUntilChanged, filter, of, Subject, switchMap, takeUntil, tap } from 'rxjs';
import { PaginationTypes } from '../../core/models/pagination-types';
import { VehicleTypes } from '../../core/models/vehicle-types';
import { ToggleSwitchModule } from 'primeng/toggleswitch';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { environment } from '../../../environments/environment';
import { CommonModule, CurrencyPipe, DatePipe } from '@angular/common';
import { NGXLogger } from 'ngx-logger';
import { Loader } from '../../shared/components/loader/loader';
import { ConfirmDialog } from 'primeng/confirmdialog';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Toast } from "primeng/toast";
import { Dialog } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { DatePickerModule } from 'primeng/datepicker';
import { Parkingvehicle, ParkingvehicleForm } from '../../core/models/parkingvehicle';
import { SelectModule } from 'primeng/select';
import { ParkingService } from '../../core/services/parking-service';
import { AvailableSlots } from '../../core/models/available-slots';
import { Vehiclestatus } from '../../core/models/vehiclestatus';
import { ParkingFilterObject, ParkingFilterObjectForm } from '../../core/models/parking-filter-object';
import { TimerInterval } from "../../shared/components/timer-interval/timer-interval";
import { QRCodeComponent } from 'angularx-qrcode';
import { RadioButton } from 'primeng/radiobutton';
import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { ConvertIso } from '../../core/utilities/convert-iso';
import { Storage } from '../../core/utilities/storage';
@Component({
  selector: 'app-bills',
  imports: [
    TableModule,
    IconFieldModule,
    InputTextModule,
    ToggleSwitchModule,
    FormsModule,
    DatePipe,
    Loader,
    CurrencyPipe,
    ConfirmDialog,
    Toast,
    Dialog,
    ButtonModule,
    InputTextModule,
    ReactiveFormsModule,
    CommonModule,
    DatePickerModule,
    SelectModule,
    TimerInterval,
    QRCodeComponent,
    RadioButton
  ],
  templateUrl: './bills.html',
  styleUrl: './bills.scss',
  standalone: true,
  providers: [VehicleService, ParkingService, ConfirmationService, ConvertIso, Storage]
})
export class Bills implements OnInit {
  private _ownerId: string = "";
  private destroy$ = new Subject<void>();

  public vehicleTypes: VehicleTypes[] = [];
  public parkingVehicleForms!: FormGroup<ParkingvehicleForm>;
  public filterParkingVehicleForms!: FormGroup<ParkingFilterObjectForm>;
  public showLoader: boolean = false;
  public slotAvailable: boolean = false;
  public dateFormat: string = environment.DATE_FORMAT;
  public vehicleStatus: Vehiclestatus | null = null;
  public parkingVehiclePagination: PaginationTypes<Parkingvehicle> = { data: [], totalRecords: 0 };
  public totalChargeParkingVehicle: Parkingvehicle | null = null;
  public showChargesPopup: boolean = false;
  public paymentFormGroup!: FormGroup;
  public paymentPaidStatusDd: { data: number, text: string }[] = [{ data: 1, text: 'Paid' }, { data: 0, text: 'Un-Paid' }]
  public keyStatusDd: { data: number, text: string }[] = [{ data: 1, text: 'Submitted' }, { data: 0, text: 'Non-Submitted' }]
  public paymentModeDd: { data: string, text: string }[] = [{ data: 'Online', text: 'Online' }, { data: 'Cash', text: 'Cash' }, { data: 'Abandant', text: 'Abandant' }]
  public showInformationPopup: boolean = false;
  public showParkingVehicleInformation: Parkingvehicle | null = null;

  constructor(
    private fb: FormBuilder,
    private vehicleService: VehicleService,
    private logger: NGXLogger,
    private parkingService: ParkingService,
    private messageService: MessageService,
    private cdr: ChangeDetectorRef,
    private confirmationService: ConfirmationService,
    private datePipe: DatePipe,
    private convertISO: ConvertIso,
    private storageService: Storage
  ) { }

  public ngOnInit(): void {
    this._ownerId = this.storageService.getOwnerId();
    this.defaultFormLoad();
    this.getActiveVehicle();
    this.filterDefaultLoad();
    this.getParkingVehicleTableRecords();

    this.filterParkingVehicleForms.valueChanges
      .pipe(debounceTime(400), distinctUntilChanged(), takeUntil(this.destroy$))
      .subscribe((filters) => {
        this.filterParkingVehicleForms.patchValue(
          {
            filterCheckInDate: this.convertISO.convertISODate(filters.filterCheckInDate),
            filterCheckOutDate: this.convertISO.convertISODate(filters.filterCheckOutDate),
            pageNo: 0,
            pageSize: filters.pageSize,
            firstPageIndex: 0
          },
          { emitEvent: false }
        );
        this.getParkingVehicleTableRecords();
      });
  }

  public onRegisterVehicle(): void {
    if (this.parkingVehicleForms.valid && this.vehicleStatus && !this.vehicleStatus.parkingStatus) {
      this.logger.info(this.parkingVehicleForms.value);
      let parkingVehicle: any = this.parkingVehicleForms.value;
      this.registerVehicle(parkingVehicle);
    } else {
      this.parkingVehicleForms.markAllAsTouched();
    }
  }

  public onShowVehicleInformation(parkingVehicle: Parkingvehicle): void {
    this.showParkingVehicleInformation = parkingVehicle;
    this.showInformationPopup = true;
  }

  public onPaymentVehicle(): void {
    if (!this.totalChargeParkingVehicle)
      return;

    let paymentSelectionValue = this.paymentFormGroup.value;
    if (paymentSelectionValue.paidThrow === 'Online') {
      this.totalChargeParkingVehicle.transactionId = paymentSelectionValue.textData;
    }
    else {
      this.totalChargeParkingVehicle.comments = paymentSelectionValue.textData;
    }

    this.totalChargeParkingVehicle.paidThrow = paymentSelectionValue.paidThrow;
    this.paidVehicleCharge(this.totalChargeParkingVehicle);
  }

  public defaultFormLoad(): void {
    this.parkingVehicleForms = this.fb.group({
      parkingId: [0],
      name: [''],
      mobileNo: [''],
      vehicleNo: ['', [Validators.required, Validators.pattern('^[A-Za-z]{2}[0-9]{1,2}[A-Za-z]{0,3}[0-9]{4}$')]],
      checkIn: [new Date()],
      checkOut: [new Date()],
      totalHours: [''],
      vehicleTypeId: [0],
      perHourCharge: [0],
      transactionId: [''],
      paidThrow: [''],
      isPaid: [false],
      createdDate: [new Date()],
      modifiedDate: [new Date()],
      ownerId: [this._ownerId],
      isDeleted: [false],
      totalCharge: [0],
      blockName: [''],
      slotNo: [0],
      isSubmittedKey: [false],
      vehicleTypeName: [''],
      comments: ['']
    });

    this.paymentFormGroup = this.fb.group({
      paidThrow: ['Online'],
      textData: ['']
    });

    this.slotAvailable = false;
    this.vehicleStatus = null;
  }

  public bindVehicleInformation(e: any): void {
    this.logger.info(e);
    if (this.vehicleTypes && this.vehicleTypes.length > 0) {
      let selectedVehicleTypes = this.vehicleTypes.find(x => x.vehicleId == (e.value || e));
      if (!selectedVehicleTypes) {
        this.slotAvailable = false;
        return;
      }

      this.showLoader = true;
      this.parkingService.getAvailableSlots(selectedVehicleTypes.vehicleId, this._ownerId).pipe(takeUntil(this.destroy$)).subscribe({
        next: (response) => {
          if (response.success) {
            this.logger.info(response);
            let slotDetails: AvailableSlots = response.data;
            if (slotDetails && slotDetails.slotNumber > 0) {
              this.parkingVehicleForms.patchValue({
                blockName: slotDetails.blockName,
                perHourCharge: selectedVehicleTypes?.perHourCharge,
                slotNo: slotDetails.slotNumber,
                checkIn: slotDetails.checkInTime,
                vehicleTypeName: selectedVehicleTypes?.name
              });

              this.slotAvailable = true;
            }
            else {
              this.slotAvailable = false;
            }
          }
          else {
            this.slotAvailable = false;
          }

          this.cdr.markForCheck();
          this.showLoader = false;
        },
        error: (err) => {
          this.slotAvailable = false;
          this.showLoader = false;
          throw err
        }
      });
    }
  }

  public checkVehicleStatus(): void {
    let vehicleNo = this.parkingVehicleForms.get('vehicleNo')?.value;
    if (this.parkingVehicleForms.get('vehicleNo')?.invalid || !vehicleNo)
      return;

    this.showLoader = true;
    this.parkingService.getVehicleStatus(vehicleNo, this._ownerId).pipe(takeUntil(this.destroy$)).subscribe({
      next: (response) => {
        if (response.success) {
          this.logger.info(response);
        }
        this.vehicleStatus = response.data;
        if (this.vehicleStatus) {
          this.parkingVehicleForms.patchValue({
            name: this.vehicleStatus.name,
            mobileNo: this.vehicleStatus.mobileNo,
            vehicleTypeId: this.vehicleStatus.vehicleTypeId
          });

          this.bindVehicleInformation(this.vehicleStatus.vehicleTypeId);
        }

        this.showLoader = false;
        this.cdr.markForCheck();
      },
      error: (err) => {
        this.showLoader = false;
        this.vehicleStatus = null;
        throw err
      }
    });

  }

  public onPageChange(event: any): void {
    if (this.filterParkingVehicleForms) {
      let pageNo = event.first / event.rows;
      this.filterParkingVehicleForms.patchValue({
        pageNo: pageNo,
        pageSize: event.rows,
        firstPageIndex: event.first
      }, { emitEvent: false });
    }

    this.logger.info(event);
    this.getParkingVehicleTableRecords();
  }

  public onFilterChange(field: string, value: any) {
    this.filterParkingVehicleForms.get(field)?.setValue(value);
    // this.getParkingVehicleTableRecords();
  }

  private getActiveVehicle(): void {
    if (!Array.isArray(this.vehicleTypes))
      return;

    this.showLoader = true;
    this.vehicleService.getActiveVehicle(this._ownerId).pipe(takeUntil(this.destroy$)).subscribe({
      next: (response) => {
        if (response.success) {
          this.logger.info(response);
        }
        this.vehicleTypes = response.data;
        this.showLoader = false;
        this.cdr.markForCheck();
      },
      error: (err) => {
        this.showLoader = false;
        this.vehicleTypes = [];
        this.cdr.markForCheck();
        if (err as HttpErrorResponse && err.status == HttpStatusCode.NotFound as number)
          return;

        throw err
      }
    });
  }

  private paidVehicleCharge(parkingVehicle: Parkingvehicle): void {
    this.showLoader = true;
    this.parkingService.paidVehicleCharge(
      parkingVehicle
    ).pipe(takeUntil(this.destroy$)).subscribe({
      next: (response) => {
        if (response.success) {
          this.logger.info(response);
          this.messageService.add({ severity: 'success', summary: 'Confirmed', detail: response.data.message as string });
          this.defaultFormLoad();
          this.filterDefaultLoad();
          this.getParkingVehicleTableRecords();
        }

        this.showLoader = false;
        this.totalChargeParkingVehicle = null;
        this.showChargesPopup = false;
        this.cdr.markForCheck();
      },
      error: (err) => {
        this.showLoader = false;
        throw err
      }
    });
  }

  private registerVehicle(parkingVehicle: Parkingvehicle): void {
    this.showLoader = true;
    this.parkingService.registerVehicle(
      parkingVehicle
    ).pipe(takeUntil(this.destroy$)).subscribe({
      next: (response) => {
        if (response.success) {
          this.logger.info(response);
          this.messageService.add({ severity: 'success', summary: 'Confirmed', detail: response.data.message as string });
          this.defaultFormLoad();
          this.filterDefaultLoad();
          this.getParkingVehicleTableRecords();
        }

        this.cdr.markForCheck();
        this.showLoader = false;
      },
      error: (err) => {
        this.showLoader = false;
        throw err
      }
    });
  }

  public deleteRegisterationVehicle(parkingVehicle: Parkingvehicle): void {
    this.confirmationService.confirm({
      message: `Are you sure that you want to delete (${parkingVehicle.vehicleNo}) ?`,
      header: 'Confirmation',
      closable: true,
      closeOnEscape: true,
      rejectButtonProps: {
        label: 'Cancel',
        severity: 'secondary',
        outlined: true,
      },
      acceptButtonProps: {
        label: 'Delete',
        style: {
          backgroundColor: '#fd7e14', // green
          color: '#fff',
          border: 'none'
        }
      },
      accept: () => {
        parkingVehicle.isDeleted = true;
        this.deleteRegisterationVehicleAPI(parkingVehicle);
      },
      reject: () => {
        parkingVehicle.isDeleted = false;
      },
    });
  }

  public prepareTotalCharges(parkingVehicle: Parkingvehicle): void {
    this.showLoader = true;
    this.parkingService.getTotalChargeOfVehicle(
      parkingVehicle.parkingId,
      parkingVehicle.ownerId
    ).pipe(takeUntil(this.destroy$)).subscribe({
      next: (response) => {
        if (response.success) {
          this.logger.info(response);
          let resultParkingVehicle: Parkingvehicle = response.data;
          parkingVehicle.totalHours = resultParkingVehicle.totalHours;
          parkingVehicle.totalCharge = resultParkingVehicle.totalCharge;
          parkingVehicle.perHourCharge = resultParkingVehicle.perHourCharge;
        }

        this.defaultFormLoad();
        this.totalChargeParkingVehicle = parkingVehicle;
        this.showLoader = false;
        this.showChargesPopup = true;
        this.cdr.markForCheck();
      },
      error: (err) => {
        this.showLoader = false;
        this.totalChargeParkingVehicle = null;
        this.showChargesPopup = false;
        throw err
      }
    });
  }

  private deleteRegisterationVehicleAPI(parkingVehicle: Parkingvehicle) {
    this.showLoader = true;
    this.parkingService.deleteRegisterationVehicle(
      parkingVehicle.parkingId,
      parkingVehicle.ownerId
    ).pipe(takeUntil(this.destroy$)).subscribe({
      next: (response) => {
        if (response.success) {
          this.logger.info(response);
          this.messageService.add({ severity: 'success', summary: 'Confirmed', detail: response.data.message as string });
          this.filterDefaultLoad();
          this.getParkingVehicleTableRecords();
        }

        this.cdr.markForCheck();
        this.showLoader = false;
      },
      error: (err) => {
        this.showLoader = false;
        throw err
      }
    });
  }

  private getParkingVehicleTableRecords(): void {
    const filter: ParkingFilterObject = {
      ...this.filterParkingVehicleForms.getRawValue(),
    } as ParkingFilterObject;

    if (!filter)
      return;

    filter.filterCheckInDate && this.filterParkingVehicleForms.patchValue({ filterCheckInDate: this.datePipe.transform(filter.filterCheckInDate, 'dd-MM-yyyy') || '' }, { emitEvent: false });
    filter.filterCheckOutDate && this.filterParkingVehicleForms.patchValue({ filterCheckOutDate: this.datePipe.transform(filter.filterCheckOutDate, 'dd-MM-yyyy') || '' }, { emitEvent: false });
    this.logger.info(filter);
    this.showLoader = true;
    this.parkingService.getParkingVehicleTableRecords(filter).pipe(takeUntil(this.destroy$)).subscribe({
      next: (response) => {
        if (response.success) {
          this.logger.info(response);
          this.parkingVehiclePagination = {
            data: response?.data?.data || [],
            totalRecords: response?.data?.totalRecords || 0
          };

          this.cdr.markForCheck();
        }

        this.showLoader = false;
      },
      error: (err) => {
        this.showLoader = false;
        this.parkingVehiclePagination = {
          data: [],
          totalRecords: 0
        };

        this.cdr.markForCheck();
        if (err as HttpErrorResponse && err.status == HttpStatusCode.NotFound as number)
          return;

        throw err
      }
    });
  }

  private filterDefaultLoad(): void {
    this.filterParkingVehicleForms = this.fb.group({
      filterCheckInDate: [''],
      filterCheckOutDate: [''],
      filterIsPaid: [''],
      filterIsSubmittedKey: [''],
      filterMobileNo: [''],
      filterName: [''],
      filterPaidThrow: [''],
      filterVehicleId: [0],
      filterVehicleNo: [''],
      firstPageIndex: [0],
      ownerId: [this._ownerId],
      pageNo: [0],
      pageSize: [10]
    });
  }
}
