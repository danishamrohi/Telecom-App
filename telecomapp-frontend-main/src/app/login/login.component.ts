import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../z-Services/customer.service';
import { AdminService } from '../z-Services/admin.service';
import { Csr } from '../model/Csr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  token:string="";
  csrId:number=14;
  loginForm : any = {}
  loginData = new Csr();
  state = false;
  errMessage ="";

  constructor(private customerService:CustomerService,
    private adminService : AdminService) { }

  onSubmit(){
    this.loginData = this.loginForm;
    if (this.loginForm.userType == null || this.loginForm.userType == undefined)
     {  this.errMessage = "Please select User Type!"
        this.state = true;
        return;
      }

    if (this.loginForm.userName == null || this.loginForm.userName == undefined ||
      this.loginForm.userName == null || this.loginForm.userName == ""||
      this.loginForm.password == null || this.loginForm.password == undefined ||
       this.loginForm.password == "" ) {
        this.errMessage = "Please select Correct User Name and Password!"
        this.state = true;
        return;
    }

    sessionStorage.setItem("userType", this.loginForm.userType);

    if (this.loginForm.userType == "admin") {
      this.signInAsAdmin();
    }
    if (this.loginForm.userType == "csr") {
      this.signInAsCSR();
    }
    if (this.loginForm.userType == "customer") {
      this.signInAsCustomer();
    }
  }

  signInAsAdmin(){
    this.adminService.loginAdmin(this.loginData).subscribe(
      result =>{
        this.token = result['token'];
        this.adminService.storeToken(this.token);
        this.adminService.openAdminDashBoard();
      },
      (err:any)=>
      {
        console.log(err);
        this.errMessage="Failed to login as Admin, Please try again!";
        this.state=true;
      }
    )
  }

  signInAsCSR(){
    this.adminService.loginCsr(this.loginData).subscribe(
      result=>{
        this.token = result['tokenMap']['token'];
        this.csrId = result['csrId'];
        sessionStorage.setItem("csrId", this.csrId.toString());
        this.adminService.storeToken(this.token);
        this.adminService.openCsrDashBoard();
      },
      (err:any)=>
      {
        console.log(err);
        this.errMessage="Failed to login as CSR, Please try again!";
        this.state=true;
      }
    )
  }

  signInAsCustomer(){
    this.customerService.loginCustomer(this.loginForm).subscribe(
      result =>{
        this.token = result['token'];
        this.customerService.storeToken(this.token);
        this.customerService.setCustomerUserName(this.loginForm.userName);
        this.customerService.openCustomerDashboard();
      },
      (err:any)=>
      {
        console.log(err);
        this.errMessage="Failed to login as Customer, Please try again!";
        this.state=true;
      }
    )
  }

  changeErrorState(){
    this.state = false;
  }




  goBack(){
    this.customerService.openHome()
  }

  routeToRegisterCustomer(){
    this.customerService.openCustomerRegister()
  }

  ngOnInit() {
  }


}
