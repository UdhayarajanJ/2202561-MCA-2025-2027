import { inject, Injectable } from '@angular/core';
import { ApiService } from './api-sevice';
import { Observable } from 'rxjs';
import { CommonAPIResponse } from '../models/common-apiresponse';
import { ChartSummary } from '../models/dashboard';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  private apiService = inject(ApiService);
  public getDashboardSummary(ownerId: string): Observable<CommonAPIResponse<ChartSummary>> {
    return this.apiService.get(`api/Dashboard/GetDashboardSummary/${ownerId}`);
  }
}
