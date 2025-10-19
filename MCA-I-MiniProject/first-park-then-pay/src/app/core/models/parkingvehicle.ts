import { FormControl } from "@angular/forms";

export interface Parkingvehicle {
    parkingId:number,
    name:string,
    mobileNo:string,
    vehicleNo:string,
    checkIn:Date,
    checkOut:Date,
    totalHours:string,
    vehicleTypeId:number,
    perHourCharge:number,
    transactionId:string,
    paidThrow:string,
    isPaid:boolean,
    createdDate:Date,
    modifiedDate:Date,
    ownerId:string,
    isDeleted:boolean,
    totalCharge:number,
    blockName:string,
    slotNo:number,
    isSubmittedKey:boolean,
    vehicleTypeName:string,
    comments:string
}

export interface ParkingvehicleForm {
  parkingId: FormControl<number | null>;
  name: FormControl<string | null>;
  mobileNo: FormControl<string | null>;
  vehicleNo: FormControl<string | null>;
  checkIn: FormControl<Date | null>;
  checkOut: FormControl<Date | null>;
  totalHours: FormControl<string | null>;
  vehicleTypeId: FormControl<number | null>;
  perHourCharge: FormControl<number | null>;
  transactionId: FormControl<string | null>;
  paidThrow: FormControl<string | null>;
  isPaid: FormControl<boolean | null>;
  createdDate: FormControl<Date | null>;
  modifiedDate: FormControl<Date | null>;
  ownerId: FormControl<string | null>;
  isDeleted: FormControl<boolean | null>;
  totalCharge: FormControl<number | null>;
  blockName: FormControl<string | null>;
  slotNo: FormControl<number | null>;
  isSubmittedKey: FormControl<boolean | null>;
  vehicleTypeName: FormControl<string | null>;
  comments: FormControl<string | null>;
}
