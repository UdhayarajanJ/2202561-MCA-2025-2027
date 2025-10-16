import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class Storage {
  public saveOwnerId(ownerId: string) {
    sessionStorage.clear()
    sessionStorage.setItem("_ownerId", ownerId || '');
  }

  public getOwnerId(): string {
    return sessionStorage.getItem("_ownerId") || '';
  }
}
