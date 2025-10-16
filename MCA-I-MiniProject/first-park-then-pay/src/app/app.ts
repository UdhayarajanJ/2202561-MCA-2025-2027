import { Component, OnInit, signal } from '@angular/core';
import { Header } from './shared/components/header/header';
import { Menu } from "./shared/components/menu/menu";
import { RouterOutlet } from '@angular/router';
import { ToastModule } from 'primeng/toast';
import Keycloak from 'keycloak-js';
import { Storage } from './core/utilities/storage';

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
    private storageService: Storage
  ) { }

  public ngOnInit(): void {
    this.keyCloackInstance.loadUserProfile?.().then(profile => {
      this.storageService.saveOwnerId(profile.id || '');
    }).catch(err => {
      throw err;
    });
  }
}
