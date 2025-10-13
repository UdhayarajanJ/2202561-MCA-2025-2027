export interface CommonAPIResponse<T> {
    success:boolean,
    message:string,
    data:T
}