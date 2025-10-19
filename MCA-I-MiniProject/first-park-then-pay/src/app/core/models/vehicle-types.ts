export interface VehicleTypes {
    name:string,
    vehicleId:number,
    perHourCharge:number,
    isDeleted:boolean,
    isEnabled:boolean,
    blockName:string,
    ownerId:string,
    totalSlots:number,
    createdDate:Date,
    modifiedDate:Date
}

export interface VehicleTypeSummary{
    name:string,
    totalSlots:number,
    availableSlots:number,
    reservedSlots:number,
    isEnabled:boolean
}
