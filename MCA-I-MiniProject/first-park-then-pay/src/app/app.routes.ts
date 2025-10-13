import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '',
        loadComponent: () => import('./features/home/home').then(m => m.Home),
        title: 'FPTP - Home'
    },
    {
        path: 'vehicle',
        loadComponent: () => import('./features/vehicles/vehicles').then(m => m.Vehicles),
        title: 'FPTP - Vehicle'
    },
    {
        path: 'bill',
        loadComponent: () => import('./features/bills/bills').then(m => m.Bills),
        title: 'FPTP - Bill'
    },
];
