import { DatePipe } from '@angular/common';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConvertIso {
  private datePipe = inject(DatePipe);

  public convertISODate(dateString: any): string {
    if (!dateString) return '';
    if (dateString instanceof Date && !isNaN(dateString.getTime())) {
      return this.datePipe.transform(dateString, 'yyyy-MM-dd\'T\'HH:mm:ss') || '';
    }
    else {
      const [day, month, year] = dateString.split('-').map(Number);
      const dateObj = new Date(year, month - 1, day);
      return this.datePipe.transform(dateObj, 'yyyy-MM-dd\'T\'HH:mm:ss') || '';
    }
  }
}
