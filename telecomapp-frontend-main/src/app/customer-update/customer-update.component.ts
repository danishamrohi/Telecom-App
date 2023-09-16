import { Component, OnInit } from '@angular/core';
import { Customer } from '../model/Customer';
import { CustomerService } from '../z-Services/customer.service';
import {MatSnackBar} from '@angular/material/snack-bar';
@Component({
  selector: 'app-customer-update',
  templateUrl: './customer-update.component.html',
  styleUrls: ['./customer-update.component.css']
})

export class CustomerUpdateComponent implements OnInit {

  hide = true;
  customer : Customer = new Customer;
  state=false;
  errMessage="";
  num1:number |undefined = 0;

  constructor(private customerService:CustomerService, private matSnackBar:MatSnackBar) { }

  ngOnInit() {
    this.customerService.getCustomerData().subscribe(
      result =>{
        this.customer = result;
      },
      (err:any)=>
      {
        console.log(err);
        this.errMessage="Failed to fetch data from backend";
        this.state=true;
      }
    )
  }

  onSubmit(){
    let customerData = this.customer;
    this.num1 = customerData.phoneNumber;

    if (this.num1 == undefined ) {
      this.errMessage = "Phone Number Can not be empty"
      this.state = true;
      return;
    }

    console.log(Array.from(this.num1.toString()).length);
    if (Array.from(this.num1.toString()).length != 10) {
        this.errMessage = "Please enter 10 digits for phone number";
        this.state=true;
        return;
    }

    if(customerData.userName!=undefined){
    this.customerService.updateCustomer(customerData, customerData.userName).subscribe(
      result =>{
        this.matSnackBar.open("Customer", "Updated", {
          duration: 1000
        });
        (document.getElementById("phoneNumber") as HTMLInputElement).value = "";
        (document.getElementById("name") as HTMLInputElement).value = "";
        (document.getElementById("userName") as HTMLInputElement).value = "";
        (document.getElementById("password") as HTMLInputElement).value = "";
        (document.getElementById("emailId") as HTMLInputElement).value = "";
      },
      (err:any)=>
      {
        console.log(err);
        this.errMessage="Failed to Update data to backend";
        this.state=true;
      }
    )
    }
  }


  openCustomerProfile(){
    this.customerService.openCustomerDashboard();
  }

  changeErrorState(){
    this.state = false;
  }

}
