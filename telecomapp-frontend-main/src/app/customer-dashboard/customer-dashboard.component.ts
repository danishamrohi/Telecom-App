import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../z-Services/customer.service';
import { Customer } from '../model/Customer';
import { CallService } from '../z-Services/Call.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-customer-dashboard',
  templateUrl: './customer-dashboard.component.html',
  styleUrls: ['./customer-dashboard.component.css']
})

export class CustomerDashboardComponent implements OnInit {

  customer:Customer = {};
  state = false;
  errMessage ="";

  constructor(private customerService : CustomerService, private callService:CallService, private matSnackBar:MatSnackBar) {}

  ngOnInit() {
    this.customerService.getCustomerData().subscribe(
      result =>{
        this.customer= result;
      },
      (err:any)=>
      {
        console.log(err);
        this.errMessage="Failed to fetch customer data from backend";
        this.state=true;
      }
    )
  }

  updateCustomer(){
    //this.customerService.setCustomerUserName(this.customer.userName);
    this.customerService.openCustomerUpdate();
  }

  deleteConfirm(){
    if(confirm("This action cannot be undone. Are you sure you want to delete your profile ?")) {
      this.deleteCustomer();
    }
  }

  deleteCustomer(){
    this.customerService.deleteCustomer().subscribe(
      result =>{
      },
      (err:any)=>
      {
        console.log(err);
        this.errMessage="Failed to Delete profile";
        this.state=true;
      }
    )
    sessionStorage.clear();
    this.customerService.openHome();
  }

}
