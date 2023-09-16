import { Component, OnInit } from '@angular/core';
import { CallService } from '../z-Services/Call.service';
import { FormControl, Validators } from '@angular/forms';
import { CustomerService } from '../z-Services/customer.service';
import { AdminService } from '../z-Services/admin.service';
import { Call } from '../model/Call';

@Component({
  selector: 'app-csr-dashboard',
  templateUrl: './csr-dashboard.component.html',
  styleUrls: ['./csr-dashboard.component.css']
})

export class CsrDashboardComponent implements OnInit {

  displayedColumns: string[] = ['date', 'UserName', 'phone', 'callSource', 'callSource1', "callSource2", "callSource3", "callSource4"];
  errMessage="";
  state=false;
  calls : Call[] = [];
  csrId : number = 14;
  customerPhoneNumber : FormControl;

  constructor(private callService:CallService,
    private customerService : CustomerService,
    private adminService : AdminService) {
    this.customerPhoneNumber = new FormControl("", Validators.required)
   }

  ngOnInit() {

    let csrIdString = sessionStorage.getItem('csrId');
    if (csrIdString != null) {
       this.csrId = parseInt(csrIdString);
    }
    this.getCallsWithCsrId();
  }

  private getCallsWithCsrId() {
    this.callService.getAllCallsByCsrId(this.csrId).subscribe(
      result => {
        this.calls = result;
      },
      (err: any) => {
        this.errMessage = "Failed to Fetch Call data to backend, Error " + err;
        this.state = true;
      }
    );
  }

  getRegistrationPage(){
    this.customerService.getCustomerByPhoneNumber(this.customerPhoneNumber.value)
    .subscribe(
      result => {
        console.log(result);
        this.customerService.setCustomerUserName(result.userName);
        this.callService.openCsrAddCall();
      },

      error=>{
        console.log(error.status);
        if(error.status === 404){
          alert("No Customer exists with that phone number!")
          return;
        }
        alert("Something went wrong please try again!")
      }
    )
  }

}
