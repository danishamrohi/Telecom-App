import { Component, OnInit } from '@angular/core';
import { Call } from '../model/Call';
import { CallService } from '../z-Services/Call.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Customer } from '../model/Customer';
import { AdminService } from '../z-Services/admin.service';
import { CustomerService } from '../z-Services/customer.service';

@Component({
  selector: 'app-csr-call-form',
  templateUrl: './csr-call-form.component.html',
  styleUrls: ['./csr-call-form.component.css']
})
export class CsrCallFormComponent implements OnInit {

  callForm:any = {};
  csrId : number = 14;
  customer : Customer = new Customer();
  calls:Call[] = [];
  state=false;
  errMessage="";

  constructor(private callService:CallService, private adminService:AdminService, private customerService:CustomerService,
    private matSnackBar:MatSnackBar) { }

  ngOnInit():void {

    let csrIdString = sessionStorage.getItem('csrId');
    if (csrIdString != null) {
       this.csrId = parseInt(csrIdString);
    }

    console.log(this.csrId);

    this.fetchCustomerData();
  }


  private fetchCustomerData() {
    this.customerService.getCustomerData().subscribe(
      result => {
        this.customer = result;
      },
      (err: any) => {
        console.log(err);
        this.errMessage = "Failed to fetch data from backend, Error :  " + err;
        this.state = true;
      });
  }


  onSubmit(){
    this.callForm.csrId = this.csrId;
    this.callForm.customerUserName  =this.customer.userName;
    this.callForm.customerPhoneNumber  =this.customer.phoneNumber;
    let callData = this.callForm;
    console.log(callData);

    this.callService.addCall(callData).subscribe(
      result =>{
        this.matSnackBar.open("Call", "Added", {
          duration: 1000
        });

        this.calls.push(result);
        (document.getElementById("customerBehaviour") as HTMLInputElement).value = "";
        (document.getElementById("callRating") as HTMLInputElement).value = "";
        (document.getElementById("callDetails") as HTMLInputElement).value = "";
      }
    )
  }

  goBack(){
    this.callService.openCsrDashBoard();
  }
}
