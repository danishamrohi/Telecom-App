import { Component, OnInit } from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import { Csr } from '../model/Csr';
import { AdminService } from '../z-Services/admin.service';

@Component({
  selector: 'app-admin-csr-update',
  templateUrl: './admin-csr-update.component.html',
  styleUrls: ['./admin-csr-update.component.css']
})

export class AdminCsrUpdateComponent implements OnInit {

  hide = true;
  errMessage="";
  state=false;
  num1:number|undefined = 0;
  allcsr:Csr[]=[];
  csrinfo: Csr = new Csr;

  constructor(private adminService:AdminService,
    private matSnackBar:MatSnackBar) {
  }

  ngOnInit(): void {
    this.adminService.getCsr(this.adminService.getCsrId()).subscribe(
      result =>{
        this.csrinfo=result;
      },
      (err:any)=>
      {
        this.errMessage="Failed to Fetch CSR data to backend, Error "+err;
        this.state=true;
      }
    )
  }

  updateCsr(){
    let csrData = this.csrinfo;
    console.log(csrData);

    this.num1 = csrData.phoneNo;

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

    if(this.csrinfo.csrId!=undefined){
    this.adminService.updateCsr(csrData,this.csrinfo.csrId).subscribe(
      result =>{
        this.matSnackBar.open("Csr", "Updated", {
          duration: 1000
        });

        this.allcsr.push(result);
        (document.getElementById("csrId") as HTMLInputElement).value = "";
        (document.getElementById("name") as HTMLInputElement).value = "";
        (document.getElementById("userName") as HTMLInputElement).value = "";
        (document.getElementById("password") as HTMLInputElement).value = "";
        (document.getElementById("email") as HTMLInputElement).value = "";
        (document.getElementById("phoneNo") as HTMLInputElement).value = "";
        (document.getElementById("details") as HTMLInputElement).value = "";
      },
      (err:any)=>
      {
        this.errMessage="Failed to Update data to backend, Error "+err;
        this.state=true;
      }
    )
    }
  }

  goBack(){
    this.adminService.openAdminDashBoard()
  }

  changeErrorState(){
    this.state = false;
  }

}
