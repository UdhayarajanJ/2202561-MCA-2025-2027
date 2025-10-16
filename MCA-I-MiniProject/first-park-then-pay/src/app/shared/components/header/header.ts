import { Component, OnInit,ChangeDetectorRef } from '@angular/core';
import Keycloak from 'keycloak-js';
import { NGXLogger } from 'ngx-logger';
import { ConfirmationService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { ConfirmDialog } from "primeng/confirmdialog";

@Component({
  selector: 'app-header',
  imports: [ButtonModule, ConfirmDialog],
  templateUrl: './header.html',
  styleUrl: './header.scss',
  standalone: true,
  providers: [ConfirmationService]
})
export class Header implements OnInit {

  public userName: string = '';
  public email: string = '';
  public userImageText: string = '';

  constructor(
    private keyCloak: Keycloak,
    private logger: NGXLogger,
    private confirmationService: ConfirmationService,
    private cdr:ChangeDetectorRef
  ) { }

  public ngOnInit(): void {
    this.keyCloak.loadUserProfile?.().then(profile => {
      this.logger.info(profile);
      let firstName = profile.firstName || '';
      let lastName = profile.lastName || '';
      this.userName = firstName + ' ' + lastName;
      this.email = profile.email || '';
      this.userImageText = firstName.slice(0, 1) + lastName.slice(0, 1);
      this.cdr.markForCheck();
    }).catch(err => {
      throw err;
    });
  }

  public logoutUser(): void {
    this.confirmationService.confirm({
      message: `Are you sure logout ?`,
      header: 'Confirmation',
      closable: true,
      closeOnEscape: true,
      rejectButtonProps: {
        label: 'Cancel',
        severity: 'secondary',
        outlined: true,
      },
      acceptButtonProps: {
        label: 'Logout',
        style: {
          backgroundColor: '#fd7e14', // green
          color: '#fff',
          border: 'none'
        }
      },
      accept: () => {
        this.keyCloak.logout({
          redirectUri: window.location.origin
        })
      },
      reject: () => { },
    });


  }

}
