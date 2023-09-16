import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Route, RouterStateSnapshot, UrlSegment, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { CustomerService } from '../z-Services/customer.service';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {

constructor(private customerService:CustomerService){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      let userType = sessionStorage.getItem("userType");
      let token = sessionStorage.getItem("token");

      if (token==null || userType != "admin") {
        sessionStorage.clear();
        alert("Admin access denied! Please Login!");
        this.customerService.openLogin();
        return false;
      }

    return true;
  }

}
