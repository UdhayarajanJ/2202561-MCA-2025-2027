import { FormControl } from "@angular/forms";

export interface ParkingFilterObject {
    pageNo: number,
    pageSize: number,
    ownerId: string,
    filterVehicleNo: string,
    filterIsPaid: string,
    filterCheckInDate: string,
    filterCheckOutDate: string,
    filterMobileNo: string,
    filterName: string,
    filterVehicleId: number,
    filterIsSubmittedKey: string,
    filterPaidThrow: string,
    firstPageIndex: number,
}

export interface ParkingFilterObjectForm {
    pageNo: FormControl<number | null>,
    pageSize: FormControl<number | null>,
    ownerId: FormControl<string | null>,
    filterVehicleNo: FormControl<string | null>,
    filterIsPaid: FormControl<string | null>,
    filterCheckInDate: FormControl<string | null>,
    filterCheckOutDate: FormControl<string | null>,
    filterMobileNo: FormControl<string | null>,
    filterName: FormControl<string | null>,
    filterVehicleId: FormControl<number | null>,
    filterIsSubmittedKey: FormControl<string | null>,
    filterPaidThrow: FormControl<string | null>,
    firstPageIndex: FormControl<number | null>,
}

