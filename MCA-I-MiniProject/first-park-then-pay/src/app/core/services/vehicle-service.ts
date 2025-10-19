import { inject, Injectable } from '@angular/core';
import { ApiService } from './api-sevice';
import { CommonAPIResponse } from '../models/common-apiresponse';
import { Observable } from 'rxjs';
import { PaginationTypes } from '../models/pagination-types';
import { VehicleTypes, VehicleTypeSummary } from '../models/vehicle-types';
import { Confirmationresult } from '../models/confirmationresult';

@Injectable({
  providedIn: 'root'
})

export class VehicleService {
  private apiService = inject(ApiService);

  public getVehicleTableData(
    pageNo: number,
    pageSize: number,
    filterDate: string,
    filterName: string,
    ownerId: string
  ): Observable<CommonAPIResponse<PaginationTypes<VehicleTypes>>> {
    const params: Record<string, any> = {
      pageNo,
      pageSize,
      filterDate,
      filterName,
      ownerId
    };
    return this.apiService.get("api/Vehicle/GetVehicleTableRecords", params);
  }

  public saveUpdateVehicleTypes(
    vehicleTypes: VehicleTypes
  ): Observable<CommonAPIResponse<Confirmationresult>> {
    return this.apiService.post("api/Vehicle/SaveUpdateVehicleTypes", vehicleTypes);
  }

  public getActiveVehicle(ownerId:string): Observable<CommonAPIResponse<VehicleTypes[]>> {
    return this.apiService.get(`api/Vehicle/GetVehicleTypes/${ownerId}`);
  }

  public getVehicleTypeSummary(
    pageNo: number,
    pageSize: number,
    filterName: string,
    ownerId: string
  ): Observable<CommonAPIResponse<PaginationTypes<VehicleTypeSummary>>> {
    const params: Record<string, any> = {
      pageNo,
      pageSize,
      filterName,
      ownerId
    };
    return this.apiService.get("api/Vehicle/GetVehicleTypeSummary", params);
  }
}
