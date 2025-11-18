import { HttpErrorResponse } from '@angular/common/http';
import { ErrorHandler, inject, Injectable } from '@angular/core';
import { NGXLogger } from 'ngx-logger';
import { MessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})

export class GlobalErrorhanlder implements ErrorHandler {
  private messageService = inject(MessageService)
  private logger = inject(NGXLogger)
  handleError(error: any): void {
    let message = 'An unexpected error occurred';

    if (error instanceof HttpErrorResponse) {
      message = `HTTP Error: ${error.status} - ${error.message}`;
    } else if (error instanceof Error) {
      message = `Error: ${error.message}`;
    }

    this.logger.error(error);

    this.messageService.add({
      severity: 'error',
      summary: '',
      detail: message,
      life: 5000,
      closable: false,
      key: 'globalToast',
      styleClass: 'no-icon-toast'
    });
  }

}
