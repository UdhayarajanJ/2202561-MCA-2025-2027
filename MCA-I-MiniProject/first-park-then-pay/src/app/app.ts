import { Component, OnInit, signal } from '@angular/core';
import { Header } from './shared/components/header/header';
import { Menu } from "./shared/components/menu/menu";
import { RouterOutlet } from '@angular/router';
import { ToastModule } from 'primeng/toast';
import Keycloak from 'keycloak-js';
import { Storage } from './core/utilities/storage';
import { NGXLogger } from 'ngx-logger';

@Component({
  selector: 'app-root',
  imports: [Header, Menu, RouterOutlet, ToastModule],
  templateUrl: './app.html',
  styleUrl: './app.scss',
  standalone: true,
  providers: [Storage]
})
export class App implements OnInit {
  constructor(
    private keyCloackInstance: Keycloak,
    private storageService: Storage,
    private logger: NGXLogger,
  ) { }

  public ngOnInit(): void {
    this.keyCloackInstance.loadUserProfile?.().then(profile => {
      let upiID: string = profile.attributes && profile.attributes['upiId'] && profile.attributes['upiId'] instanceof Array ? profile.attributes['upiId'][0] : '';
      this.storageService.saveOwnerId(profile.id || '');
      this.storageService.saveUPICustomer(upiID);
    }).catch(err => {
      throw err;
    });
  }
}
