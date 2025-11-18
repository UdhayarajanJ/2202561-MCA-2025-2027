import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { AuthGuardData, createAuthGuard } from 'keycloak-angular';

const isAccessAllowed = async (route: ActivatedRouteSnapshot, state: RouterStateSnapshot, authData: AuthGuardData): Promise<boolean | UrlTree> => {
  const { authenticated, keycloak } = authData;
  if (authenticated) {
    return true;
  }

  await keycloak.login({
    redirectUri: window.location.origin + state.url,
  })

  return false;
};

export const keycloakGuard: CanActivateFn = createAuthGuard<CanActivateFn>(isAccessAllowed);

