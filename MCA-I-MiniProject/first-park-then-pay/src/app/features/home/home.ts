import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Loader } from "../../shared/components/loader/loader";
import { DashboardService } from '../../core/services/dashboard-service';
import { Storage } from '../../core/utilities/storage';
import { NGXLogger } from 'ngx-logger';
import { debounceTime, distinctUntilChanged, Subject, takeUntil, tap } from 'rxjs';
import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { Dashboard } from '../../core/models/dashboard';
import {
  ApexAxisChartSeries,
  ApexChart,
  ChartComponent,
  ApexDataLabels,
  ApexXAxis,
  ApexPlotOptions,
  ApexTooltip
} from "ng-apexcharts";
import { TableModule } from 'primeng/table';
import { PaginationTypes } from '../../core/models/pagination-types';
import { VehicleTypeSummary } from '../../core/models/vehicle-types';
import { VehicleService } from '../../core/services/vehicle-service';
import { IconFieldModule } from 'primeng/iconfield';
import { InputTextModule } from 'primeng/inputtext';

export type BarChartOptions = {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  dataLabels: ApexDataLabels;
  plotOptions: ApexPlotOptions;
  xaxis: ApexXAxis,
  colors: string[],
  tooltip?: ApexTooltip;
};

export type PieChartOptions = {
  series: ApexNonAxisChartSeries;
  chart: ApexChart;
  responsive: ApexResponsive[];
  dataLabels: ApexDataLabels;
  labels: any;
};

export type ChartOptions = {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  dataLabels: ApexDataLabels;
  plotOptions: ApexPlotOptions;
  yaxis: ApexYAxis;
  xaxis: ApexXAxis;
  fill: ApexFill;
  tooltip: ApexTooltip;
  stroke: ApexStroke;
  legend: ApexLegend;
};

@Component({
  selector: 'app-home',
  imports: [
    Loader,
    ChartComponent,
    TableModule,
    IconFieldModule,
    InputTextModule
  ],
  templateUrl: './home.html',
  styleUrl: './home.scss',
  standalone: true,
  providers: [DashboardService, Storage, VehicleService]
})
export class Home implements OnInit {
  private _ownerId: string = '';
  private destroy$ = new Subject<void>();
  private _filterName: string = '';

  public showLoader: boolean = false;
  public barChartOptions: Partial<BarChartOptions> | any;
  public pieChartOptions: Partial<PieChartOptions> | any;
  public columnChart: Partial<ChartOptions> | any;
  public vehicleSummaryPagination: PaginationTypes<VehicleTypeSummary> = { data: [], totalRecords: 0 };
  public pageNo: number = 0;
  public pageSize: number = 10;
  public first_Page_Index = 0;
  private filterSubject$ = new Subject<{ name: string }>();

  constructor(
    private dashboardService: DashboardService,
    private logger: NGXLogger,
    private cdr: ChangeDetectorRef,
    private storageService: Storage,
    private vehicleService: VehicleService
  ) { }

  public ngOnInit(): void {
    this._ownerId = this.storageService.getOwnerId();
    this.getDashboardSummary();
    this.getVehicleTypeSummary();
    this.filterSubject$
      .pipe(
        debounceTime(400),
        distinctUntilChanged((a, b) => JSON.stringify(a) === JSON.stringify(b)),
        tap((filterValue: any) => {
          this.logger.info(filterValue);
          this.getVehicleTypeSummary();
        }),
        takeUntil(this.destroy$)
      )
      .subscribe();
  }

  private getDashboardSummary() {
    this.showLoader = true;
    this.dashboardService.getDashboardSummary(this._ownerId).pipe(takeUntil(this.destroy$)).subscribe({
      next: (response) => {
        if (response.success) {
          this.logger.info(response);
        }

        this.logger.info(response.data.pieChart);
        this.showLoader = false;
        this.loadBarChart(response.data.pieChart);
        this.loadPieChart(response.data.donutChart);
        this.loadParkingSummary(response.data.parkingSummary);
        this.cdr.markForCheck();
      },
      error: (err) => {
        this.showLoader = false;
        this.cdr.markForCheck();
        if (err as HttpErrorResponse && err.status == HttpStatusCode.NotFound as number)
          return;

        throw err
      }
    });
  }

  private loadBarChart(barChartSummary: Dashboard[]) {
    this.barChartOptions = {
      series: [
        {
          name: '',
          data: barChartSummary.map(x => x.totalAmount),
        }
      ],
      chart: {
        type: "bar",
        height: 250,
      },
      plotOptions: {
        bar: {
          horizontal: true,
          distributed: false,
          barHeight: '30%'
        }
      },
      dataLabels: {
        enabled: false
      },
      colors: ['#FF9800'],
      xaxis: {
        categories: barChartSummary.map(x => x.text)
      },
      tooltip: {
        y: {
          formatter: function (val: any, opts: any) {
            const formattedVal = new Intl.NumberFormat('en-IN', {
              style: 'currency',
              currency: 'INR',
              minimumFractionDigits: 2
            }).format(val);
            return `${formattedVal}`;
          }
        }
      }
    };
  }

  private loadPieChart(pieChartSummary: Dashboard[]) {
    this.logger.info(pieChartSummary);
    this.pieChartOptions = {
      series: pieChartSummary.map(x => x.totalCount),
      chart: {
        type: "donut",
        height: 250,
      },
      labels: pieChartSummary.map(x => x.text),
      dataLabels: {
        enabled: false // 👈 disables all slice labels (percentages)
      },
      responsive: [
        {
          breakpoint: 480,
          options: {
            chart: {
              width: 50
            },
            legend: {
              position: "bottom"
            }
          }
        }
      ]
    };
  }

  private loadParkingSummary(parkingSummary: Dashboard) {
    this.columnChart = {
      series: [
        {
          name: "In-Parking",
          data: [parkingSummary.inParking]
        },
        {
          name: "Out-Parking",
          data: [parkingSummary.outParking]
        },
      ],
      chart: {
        type: "bar",
        height: 250
      },
      plotOptions: {
        bar: {
          horizontal: false,
          columnWidth: "20%",
          endingShape: "rounded",
        }
      },
      dataLabels: {
        enabled: false
      },
      stroke: {
        show: true,
        width: 2,
        colors: ["transparent"]
      },
      xaxis: {
        categories: [
          'Parking'
        ]
      },
      fill: {
        opacity: 1
      },
      tooltip: {
        y: {
          formatter: function (val: any) {
            return `${val}`;
          }
        }
      }
    };
  }

  private getVehicleTypeSummary(): void {
    this.showLoader = true;
    this.vehicleService.getVehicleTypeSummary(
      this.pageNo,
      this.pageSize,
      this._filterName,
      this._ownerId.toString()
    ).pipe(takeUntil(this.destroy$)).subscribe({
      next: (response) => {
        if (response.success) {
          this.logger.info(response);
          this.vehicleSummaryPagination = {
            data: response?.data?.data || [],
            totalRecords: response?.data?.totalRecords || 0
          };

          this.cdr.markForCheck();
        }

        this.showLoader = false;
      },
      error: (err) => {
        this.showLoader = false;
        this.vehicleSummaryPagination = {
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

  public onFilterChange(value: any): void {
    const trimmed = value?.trim() || '';
    this._filterName = trimmed.length > 0 ? trimmed : '';

    if (trimmed.length >= 2 || trimmed.length === 0) {
      this.emitFilter();
    }
  }

  private emitFilter(): void {
    this.filterSubject$.next({
      name: this._filterName
    });
  }

  public onPageChange(event: any): void {
    this.pageNo = event.first / event.rows;
    this.pageSize = event.rows;
    this.first_Page_Index = event.first;
    this.logger.info(event);
    this.getVehicleTypeSummary();
  }
}
