import { Component, OnInit } from '@angular/core';
import { Customer } from '../model/Customer';
import { CustomerService } from '../z-Services/customer.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-customer-registration',
  templateUrl: './customer-registration.component.html',
  styleUrls: ['./customer-registration.component.css']
})
export class CustomerRegistrationComponent implements OnInit {

  hide=true;
  errorState=false;
  errMessage="";
  customerForm:any={};
  num1:number = 0;
  customerList:Customer[]=[];

  constructor(private customerService:CustomerService, private matSnackBar:MatSnackBar) {

  }

  ngOnInit() {}

  onSubmit(){
      let customerData = this.customerForm;
      this.num1 = customerData.phoneNumber;
      console.log(Array.from(this.num1.toString()).length);
      if (Array.from(this.num1.toString()).length != 10) {
          this.errMessage = "Please enter 10 digits for phone number";
          this.errorState=true;
          return;
      }

      this.customerService.registerCustomer(customerData).subscribe(
        result =>{
          this.matSnackBar.open("Awesome you are now registered.", "Please login", {
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
          if (err.status == 409) {
            this.errMessage = "Username already taken, please choose a different username"
            this.errorState=true;
            return;
          }
          this.errMessage="Failed in registration. Please try again";
          this.errorState=true;
        }
      )
    }

    openLogin(){
      this.customerService.openLogin()
    }

    changeErrorState(){
      this.errorState = false;
    }

  }




