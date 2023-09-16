import { Component, OnInit } from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import { Csr } from '../model/Csr';
import { AdminService } from '../z-Services/admin.service';
@Component({
  selector: 'app-admin-csr-registration',
  templateUrl: './admin-csr-registration.component.html',
  styleUrls: ['./admin-csr-registration.component.css']
})
export class AdminCsrRegistrationComponent implements OnInit {

  hide = true;
  state = false;
  errMessage ="";
  csrform:any = {};
  allcsr:Csr[]=[];
  num1:number | undefined = 0;

  constructor(private adminService:AdminService,
    private matSnackBar:MatSnackBar) {
  }

  ngOnInit(): void {
  }

  addCsr(){
    this.csrform.enabled = true;
    this.csrform.details = "";
    let csrData = this.csrform;
    console.log(csrData);

    this.num1 = csrData.phoneNo;

    if (this.num1 == undefined ) {
      this.errMessage = "Phone Number Can not be empty"
      this.state = true;
      return;
    }

      if (Array.from(this.num1.toString()).length != 10) {
          this.errMessage = "Please enter 10 digits for phone number";
          this.state=true;
          return;
      }

    this.adminService.registerCsr(csrData).subscribe(
      result =>{
        this.matSnackBar.open("Csr", "Added", {
          duration: 1000
        });

        this.allcsr.push(result);
        (document.getElementById("csrId") as HTMLInputElement).value = "";
        (document.getElementById("name") as HTMLInputElement).value = "";
        (document.getElementById("userName") as HTMLInputElement).value = "";
        (document.getElementById("password") as HTMLInputElement).value = "";
        (document.getElementById("email") as HTMLInputElement).value = "";
        (document.getElementById("phoneNo") as HTMLInputElement).value = "";
      },
      (err:any)=>
      {
        this.errMessage="Failed to Add data to backend, Error "+err;
        this.state=true;
      }
    )
  }

  goBack(){
    this.adminService.openAdminDashBoard()
  }

  changeErrorState(){
    this.state = false;
  }

}
