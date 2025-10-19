import { Routes } from '@angular/router';
import { keycloakGuard } from './core/guards/keycloak-guard';

export const routes: Routes = [
    {
        path: '',
        loadComponent: () => import('./features/home/home').then(m => m.Home),
        title: 'FPTP - Home',
        canActivate: [keycloakGuard]
    },
    {
        path: 'vehicle',
        loadComponent: () => import('./features/vehicles/vehicles').then(m => m.Vehicles),
        title: 'FPTP - Vehicle',
        canActivate: [keycloakGuard]
    },
    {
        path: 'bill',
        loadComponent: () => import('./features/bills/bills').then(m => m.Bills),
        title: 'FPTP - Bill',
        canActivate: [keycloakGuard]
    },
];
