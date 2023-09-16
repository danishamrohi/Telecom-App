import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate,RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { CustomerService } from '../z-Services/customer.service';

@Injectable({
  providedIn: 'root'
})
export class CsrGuard implements CanActivate {
  constructor(private customerService:CustomerService){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      let userType = sessionStorage.getItem("userType");
      let token = sessionStorage.getItem("token");

      if (token==null || userType != "csr") {
        sessionStorage.clear();
        alert("CSR access denied!  Please Login!");
        this.customerService.openLogin();
        return false;
      }

    return true;
  }

}
