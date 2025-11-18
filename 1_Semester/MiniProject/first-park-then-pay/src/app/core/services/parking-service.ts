import { inject, Injectable } from '@angular/core';
import { ApiService } from './api-sevice';
import { Parkingvehicle } from '../models/parkingvehicle';
import { Observable } from 'rxjs';
import { CommonAPIResponse } from '../models/common-apiresponse';
import { Confirmationresult } from '../models/confirmationresult';
import { AvailableSlots } from '../models/available-slots';
import { Vehiclestatus } from '../models/vehiclestatus';
import { PaginationTypes } from '../models/pagination-types';
import { ParkingFilterObject } from '../models/parking-filter-object';

@Injectable({
  providedIn: 'root'
})
export class ParkingService {
  private apiService = inject(ApiService);

  public registerVehicle(
    parkingVehicle: Parkingvehicle
  ): Observable<CommonAPIResponse<Confirmationresult>> {
    return this.apiService.post("api/parking/RegisterVehicle", parkingVehicle);
  }

  public getAvailableSlots(
    vehicleId: number,
    ownerId: string
  ): Observable<CommonAPIResponse<AvailableSlots>> {
    const params: Record<string, any> = {
      vehicleId,
      ownerId
    };
    return this.apiService.get("api/parking/GetAvailableSlots", params);
  }

  public getVehicleStatus(
    vehicleNo: string,
    ownerId: string
  ): Observable<CommonAPIResponse<Vehiclestatus>> {
    const params: Record<string, any> = {
      vehicleNo,
      ownerId
    };
    return this.apiService.get("api/parking/GetVehicleStatus", params);
  }

  public getParkingVehicleTableRecords(
    filterObject: ParkingFilterObject
  ): Observable<CommonAPIResponse<PaginationTypes<Parkingvehicle>>> {
    const params: Record<string, any> = {
      pageNo: filterObject.pageNo,
      pageSize: filterObject.pageSize,
      ownerId: filterObject.ownerId,
      filterVehicleNo: filterObject.filterVehicleNo,
      filterIsPaid: filterObject.filterIsPaid,
      filterCheckInDate: filterObject.filterCheckInDate,
      filterCheckOutDate:filterObject.filterCheckOutDate,
      filterMobileNo: filterObject.filterMobileNo,
      filterName: filterObject.filterName,
      filterVehicleId: filterObject.filterVehicleId,
      filterIsSubmittedKey: filterObject.filterIsSubmittedKey,
      filterPaidThrow: filterObject.filterPaidThrow
    };
    return this.apiService.get("api/parking/GetParkingVehicleTableRecords", params);
  }

  public deleteRegisterationVehicle(
    parkingId: number,
    ownerId: string
  ): Observable<CommonAPIResponse<Vehiclestatus>> {
    const params: Record<string, any> = {
      parkingId,
      ownerId
    };
    return this.apiService.delete("api/parking/DeleteRegisterationVehicle", params);
  }

  public getTotalChargeOfVehicle(
    parkingId: number,
    ownerId: string
  ): Observable<CommonAPIResponse<Parkingvehicle>> {
    const params: Record<string, any> = {
      parkingId,
      ownerId
    };
    return this.apiService.get("api/parking/GetTotalChargeOfVehicle", params);
  }

  public paidVehicleCharge(
    parkingVehicle: Parkingvehicle
  ): Observable<CommonAPIResponse<Confirmationresult>> {
    return this.apiService.put("api/parking/PaidVehicleCharge", parkingVehicle);
  }

}
