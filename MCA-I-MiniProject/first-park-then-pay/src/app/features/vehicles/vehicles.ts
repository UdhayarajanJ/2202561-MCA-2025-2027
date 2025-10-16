import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { IconFieldModule } from 'primeng/iconfield';
import { InputTextModule } from 'primeng/inputtext';
import { TableModule } from 'primeng/table';
import { VehicleService } from '../../core/services/vehicle-service';
import { debounceTime, distinctUntilChanged, filter, of, Subject, switchMap, takeUntil, tap } from 'rxjs';
import { PaginationTypes } from '../../core/models/pagination-types';
import { VehicleTypes } from '../../core/models/vehicle-types';
import { ToggleSwitchModule } from 'primeng/toggleswitch';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
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
import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { Storage } from '../../core/utilities/storage';

@Component({
  selector: 'app-vehicles',
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
    DatePickerModule
  ],
  templateUrl: './vehicles.html',
  styleUrl: './vehicles.scss',
  standalone: true,
  providers: [VehicleService, ConfirmationService, Storage]
})
export class Vehicles implements OnInit {

  private _ownerId: string = '';
  private destroy$ = new Subject<void>();
  private _filterName: string = '';
  private _filterDate: string = '';
  private filterSubject$ = new Subject<{ name: string; date: string }>();

  public vehiclePagination: PaginationTypes<VehicleTypes> = { data: [], totalRecords: 0 };
  public pageNo: number = 0;
  public pageSize: number = 10;
  public firstIndexTable_Vehicle_Types: number = 0;
  public dateFormat: string = environment.DATE_FORMAT;
  public showLoader: boolean = false;
  public saveUpdatePopupVisible: boolean = false;
  public buttonText: string = '';
  public vehicleTypesGroup!: FormGroup;

  constructor(
    private vehicleService: VehicleService,
    private logger: NGXLogger,
    private cdr: ChangeDetectorRef,
    private confirmationService: ConfirmationService,
    private messageService: MessageService,
    private fb: FormBuilder,
    private datePipe: DatePipe,
    private storageService: Storage
  ) { }

  public ngOnInit(): void {
    this._ownerId = this.storageService.getOwnerId();
    this.getVehicleTableRecords();
    this.defaultFormLoad();

    this.filterSubject$
      .pipe(
        debounceTime(400),
        distinctUntilChanged((a, b) => JSON.stringify(a) === JSON.stringify(b)),
        tap((filterValue) => {
          this.logger.info(filterValue);
          this.getVehicleTableRecords();
        }),
        takeUntil(this.destroy$)
      )
      .subscribe();

  }

  public onPageChange(event: any): void {
    this.pageNo = event.first / event.rows;
    this.pageSize = event.rows;
    this.firstIndexTable_Vehicle_Types = event.first;
    this.logger.info(event);
    this.getVehicleTableRecords();
  }

  public addVehicleTypes(): void {
    this.defaultFormLoad();
    this.saveUpdatePopupVisible = true;
    this.buttonText = 'Add';
  }

  public updateVehicleTypes(vehicleTypes: VehicleTypes): void {
    this.defaultFormLoad();
    this.vehicleTypesGroup.patchValue({
      id: vehicleTypes.vehicleId,
      name: vehicleTypes.name,
      perHourCharge: vehicleTypes.perHourCharge,
      isEnabled: vehicleTypes.isEnabled,
      blockName: vehicleTypes.blockName,
      totalSlots: vehicleTypes.totalSlots
    });
    this.saveUpdatePopupVisible = true;
    this.buttonText = 'Edit';
  }

  public onFilterTableRecord(event: any): void {
    const filters = event.filters;
    this.filterSubject$.next(filters);
  }

  onFilterChange(field: 'name' | 'modifiedDate', value: any): void {
    if (field === 'name') {
      const trimmed = value?.trim() || '';
      this._filterName = trimmed.length > 0 ? trimmed : '';

      if (trimmed.length >= 2 || trimmed.length === 0) {
        this.emitFilter();
      }
    }
    else if (field === 'modifiedDate') {
      const formatted = this.datePipe.transform(value, 'dd-MM-yyyy HH:mm:ss');
      this._filterDate = formatted || '';
      this.emitFilter();
    }
  }

  private emitFilter(): void {
    this.filterSubject$.next({
      name: this._filterName,
      date: this._filterDate
    });
  }

  public deleteVehicleTypes(vehicleTypes: VehicleTypes): void {
    this.confirmationService.confirm({
      message: `Are you sure that you want to delete (${vehicleTypes.name}) type vehicle ?`,
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
        vehicleTypes.isDeleted = true;
        this.saveUpdateVehicle(vehicleTypes);
      },
      reject: () => {
        vehicleTypes.isDeleted = false;
      },
    });
  }

  public onSubmitVehicleTypes(): void {
    if (this.vehicleTypesGroup.valid) {
      this.logger.info(this.vehicleTypesGroup.value);
      this.saveUpdatePopupVisible = false;
      let objVehicleTypes = this.vehicleTypesGroup.value;
      const vehicleTypesObject: VehicleTypes = {
        vehicleId: objVehicleTypes.id,
        name: objVehicleTypes.name,
        blockName: objVehicleTypes.blockName,
        createdDate: new Date(),
        modifiedDate: new Date(),
        isDeleted: false,
        isEnabled: objVehicleTypes.isEnabled,
        totalSlots: objVehicleTypes.totalSlots,
        ownerId: this._ownerId.toString(),
        perHourCharge: objVehicleTypes.perHourCharge
      };
      this.saveUpdateVehicle(vehicleTypesObject);
    } else {
      this.vehicleTypesGroup.markAllAsTouched();
    }
  }

  public changeStatusOfVehicleTypes(vehicleTypes: VehicleTypes, event: any) {
    const previousState = !event.target;
    this.confirmationService.confirm({
      target: event.target as EventTarget,
      message: 'Are you sure that you want to update vehicle status?',
      header: 'Confirmation',
      closable: true,
      closeOnEscape: true,
      rejectButtonProps: {
        label: 'Cancel',
        severity: 'secondary',
        outlined: true,
      },
      acceptButtonProps: {
        label: 'Edit',
        style: {
          backgroundColor: '#fd7e14', // green
          color: '#fff',
          border: 'none'
        }
      },
      accept: () => {
        this.saveUpdateVehicle(vehicleTypes);
      },
      reject: () => {
        vehicleTypes.isEnabled = previousState;
      },
    });
    this.logger.info(event.checked);
  }

  private getVehicleTableRecords(): void {
    this.showLoader = true;
    this.vehicleService.getVehicleTableData(
      this.pageNo,
      this.pageSize,
      this._filterDate,
      this._filterName,
      this._ownerId.toString()
    ).pipe(takeUntil(this.destroy$)).subscribe({
      next: (response) => {
        if (response.success) {
          this.logger.info(response);
          this.vehiclePagination = {
            data: response?.data?.data || [],
            totalRecords: response?.data?.totalRecords || 0
          };

          this.cdr.markForCheck();
        }

        this.showLoader = false;
      },
      error: (err) => {
        this.showLoader = false;
        this.vehiclePagination = {
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

  private saveUpdateVehicle(vehicleTypes: VehicleTypes): void {
    this.showLoader = true;
    this.vehicleService.saveUpdateVehicleTypes(
      vehicleTypes
    ).pipe(takeUntil(this.destroy$)).subscribe({
      next: (response) => {
        if (response.success) {
          this.logger.info(response);
          this.messageService.add({ severity: 'success', summary: 'Confirmed', detail: response.data.message as string });
          this.pageNo = 0;
          this.firstIndexTable_Vehicle_Types = 0;
          this.pageSize = 10;
          this.getVehicleTableRecords();
        }

        this.showLoader = false;
      },
      error: (err) => {
        this.showLoader = false;
        throw err
      }
    });
  }

  private defaultFormLoad(): void {
    this.vehicleTypesGroup = this.fb.group({
      id: [0],
      name: ['', [Validators.required, Validators.minLength(2)]],
      perHourCharge: [null, [Validators.required, Validators.min(1)]],
      isEnabled: [true],
      blockName: ['', Validators.required],
      totalSlots: [null, [Validators.required, Validators.min(1)]]
    });
  }
}
