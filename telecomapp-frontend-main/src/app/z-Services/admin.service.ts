import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Csr } from '../model/Csr';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  tempCsrId : number = 0;

  constructor(private httpcli : HttpClient, private router:Router) { }

  fetchAllCsr() : Observable<any>
  {
   return this.httpcli.get(environment.CSR_API+"/admin/getallcsr",{
    headers:new HttpHeaders({'Authorization': `Bearer ${sessionStorage.getItem("token")}` })
   });
  }

  registerCsr(agent : Csr) : Observable<any>
  {
  return  this.httpcli.post(environment.CSR_API+"/admin/register",agent,
      {
        headers:new HttpHeaders({'Authorization': `Bearer ${sessionStorage.getItem("token")}` ,"content-type" : "application/json" })
      })
  }

  loginCsr(agent : Csr) : Observable<any>
  {
  return  this.httpcli.post(environment.CSR_API+"/login",agent,
      {
        headers:new HttpHeaders({"content-type" : "application/json" })
      })
  }

  loginAdmin(agent : Csr) : Observable<any>
  {
  return  this.httpcli.post(environment.CSR_API+"/login/admin",agent,
      {
        headers:new HttpHeaders({"content-type" : "application/json" })
      })
  }

  updateCsr(agent : Csr,  CsrId: number) : Observable<any>
  {
  return  this.httpcli.put(environment.CSR_API+"/admin/"+CsrId,agent,
      {
        headers:new HttpHeaders({'Authorization': `Bearer ${sessionStorage.getItem("token")}` ,"Content-Type": "application/json"})
      })
  }

  deleteCsr(CsrId: number) : Observable<any>
  {
  return  this.httpcli.delete(environment.CSR_API+"/admin/"+CsrId,
      {
        headers:new HttpHeaders({'Authorization': `Bearer ${sessionStorage.getItem("token")}` ,"content-Type": "application/json"})
      })
  }

  getCsr(CsrId: number) : Observable<any> {
    return  this.httpcli.get(environment.CSR_API+"/admin/"+CsrId,
      {
        headers:new HttpHeaders({'Authorization': `Bearer ${sessionStorage.getItem("token")}` ,"content-Type": "application/json"})
      })
  }

  setCsrId(CsrId : number){
    this.tempCsrId = CsrId;
  }

  getCsrId(){
    return this.tempCsrId;
  }

  storeToken(token : string){
    sessionStorage.setItem('token',token);
  }

  getToken() : any
  {
  return sessionStorage.getItem('token');
  }

  openAdminDashBoard(){
    this.router.navigate(["/admin/dashboard"])
  }

  openAdminRegistration(){
    this.router.navigate(["/admin/register"])
  }

  openAdminUpdate(){
    this.router.navigate(["/admin/update"])
  }

  openCallReports(){
    this.router.navigate(["/admin/callreports"])
  }

  openCsrDashBoard(){
    this.router.navigate(["/csr/dashboard"])
  }

  openCsrCall(){
    this.router.navigate(["/csr/add-call"])
  }

}
