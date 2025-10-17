import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class Storage {
  public saveOwnerId(ownerId: string) {
    sessionStorage.setItem("_ownerId", ownerId || '');
  }

  public getOwnerId(): string {
    return sessionStorage.getItem("_ownerId") || '';
  }

  public saveUPICustomer(upiID: string) {
    sessionStorage.setItem("_ownerUPI", upiID || '');
  }

  public getUPICustomer(): string {
    return sessionStorage.getItem("_ownerUPI") || '';
  }
}
