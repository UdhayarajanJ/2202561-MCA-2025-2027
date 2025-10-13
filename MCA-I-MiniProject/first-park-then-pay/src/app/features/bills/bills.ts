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
import { ParkingFilterObject } from '../../core/models/parking-filter-object';
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
  ],
  templateUrl: './bills.html',
  styleUrl: './bills.scss',
  standalone: true,
  providers: [VehicleService, ParkingService, ConfirmationService]
})
export class Bills implements OnInit {
  private _ownerId: string = "1";
  private destroy$ = new Subject<void>();
  private _filterObject: ParkingFilterObject | null = null;

  public vehicleTypes: VehicleTypes[] = [];
  public parkingVehicleForms!: FormGroup<ParkingvehicleForm>;
  public showLoader: boolean = false;
  public slotAvailable: boolean = false;
  public dateFormat: string = environment.DATE_FORMAT;
  public vehicleStatus: Vehiclestatus | null = null;
  public pageNo: number = 0;
  public pageSize: number = 10;
  public parkingVehiclePagination: PaginationTypes<Parkingvehicle> = { data: [], totalRecords: 0 };

  constructor(
    private fb: FormBuilder,
    private vehicleService: VehicleService,
    private logger: NGXLogger,
    private parkingService: ParkingService,
    private messageService: MessageService,
    private cdr: ChangeDetectorRef,
  ) { }

  public ngOnInit(): void {
    this.defaultFormLoad();
    this.getActiveVehicle();
    this.filterDefaultLoad();
    this.getParkingVehicleTableRecords();
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

    this.slotAvailable = false;
    this.vehicleStatus = null;
  }

  public bindVehicleInformation(e: any): void {
    this.logger.info(e);
    if (this.vehicleTypes && this.vehicleTypes.length > 0) {
      let selectedVehicleTypes = this.vehicleTypes.find(x => x.vehicleId === e.value);
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
    if (!this._filterObject)
      return;

    this.showLoader = true;
    this.parkingService.getParkingVehicleTableRecords(this._filterObject).pipe(takeUntil(this.destroy$)).subscribe({
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
        throw err
      }
    });
  }

  private filterDefaultLoad(): void {
    this._filterObject = {
      filterDate: '',
      filterIsPaid: '',
      filterIsSubmittedKey: '',
      filterMobileNo: '',
      filterName: '',
      filterPaidThrow: '',
      filterVehicleId: 0,
      filterVehicleNo: 'MH14LK0511',
      ownerId: this._ownerId,
      pageNo: this.pageNo,
      pageSize: this.pageSize
    }
  }
}
