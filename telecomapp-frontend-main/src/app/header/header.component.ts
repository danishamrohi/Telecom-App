import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../z-Services/customer.service';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  constructor(private customerService : CustomerService) { }

  ngOnInit() {
    this.IsLoggedIn();
    this.checkIfIsAdmin();
    this.checkIfIsCSR();
    this.checkIfIsCustomer();
  }

  checkIfIsAdmin(){
    let userType = sessionStorage.getItem("userType");
    let token = sessionStorage.getItem("token");
      if (userType == 'admin' && token != null) {
        return true;
      }
      return false;
    }

  checkIfIsCustomer(){
    let userType = sessionStorage.getItem("userType");
    let token = sessionStorage.getItem("token");
    if (userType == 'customer' && token != null) {
      return true;
    }
    return false;
  }

  checkIfIsCSR(){
    let userType = sessionStorage.getItem("userType");
    let token = sessionStorage.getItem("token");
    if (userType == 'csr' && token != null) {
      return true;
    }
    return false;
  }

  IsLoggedIn(){
    let log = sessionStorage.getItem("token");
    if(log == null){
      return false;
    }
    else{
      return true;
    }
  }

  logout(){
    sessionStorage.clear();
    this.customerService.openHome();
  }
}
