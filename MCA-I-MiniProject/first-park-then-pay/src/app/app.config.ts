import { ApplicationConfig, ErrorHandler, importProvidersFrom, provideBrowserGlobalErrorListeners, provideZonelessChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { providePrimeNG } from 'primeng/config';
import Aura from '@primeuix/themes/aura';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { commonInterceptor } from './core/interceptors/common-interceptor-interceptor';
import { GlobalErrorhanlder } from './core/utilities/errorhanlder';
import { MessageService } from 'primeng/api';
import { LoggerModule, NgxLoggerLevel } from 'ngx-logger';
import { environment } from '../environments/environment';
import { DatePipe } from '@angular/common';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideZonelessChangeDetection(),
    provideRouter(routes),
    provideAnimationsAsync(),
    providePrimeNG({
      theme: {
        preset: Aura
      }
    }),
    MessageService,
    provideHttpClient(withInterceptors([commonInterceptor])),
    { provide: ErrorHandler, useClass: GlobalErrorhanlder },
    DatePipe,
    importProvidersFrom(
      LoggerModule.forRoot({
        level: !environment.LOGS_ENABLED ? NgxLoggerLevel.OFF : NgxLoggerLevel.DEBUG,
        serverLogLevel: NgxLoggerLevel.OFF,
        disableConsoleLogging: !environment.LOGS_ENABLED,
        timestampFormat: environment.DATE_FORMAT
      })
    ),
  ]
};
