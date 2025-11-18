export interface Dashboard {
    text: string,
    totalAmount: number,
    totalCount: number,
    inParking: number,
    outParking: number
}

export interface ChartSummary {
    donutChart: Dashboard[],
    parkingSummary: Dashboard,
    pieChart: Dashboard[]
}
