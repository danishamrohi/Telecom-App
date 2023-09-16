import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../model/Customer';
import { environment } from '../../environments/environment';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})

export class CustomerService {

constructor(private httpClient:HttpClient, private router:Router) { }

  addCustomer(customerData: Customer):Observable <Customer> {
    return this.httpClient.post(environment.Customer_API + "/register",
    customerData,{headers:new HttpHeaders({"content-type":"application/json"})
    })
  }

  getCustomerData():Observable<any>{
    return this.httpClient.get(environment.Customer_API + "/getbyuser/"+ this.getCustomerUserName(),
    {
      headers:new HttpHeaders({'Authorization': `Bearer ${sessionStorage.getItem("token")}` , "content-Type": "application/json"})
    })
  }

  getCustomerByPhoneNumber(phoneNo : number):Observable<any>{
    return this.httpClient.get(environment.Customer_API + "/getbyphone/"+ phoneNo,
    {
      headers:new HttpHeaders({'Authorization': `Bearer ${sessionStorage.getItem("token")}` , "content-Type": "application/json"})
    })
  }

  updateCustomer(customerData: Customer, userName : string):Observable<any>{
    console.log(environment.Customer_API + "/update/"+ userName);
    return this.httpClient.put(environment.Customer_API + "/update/" + userName, customerData,
    {
      headers:new HttpHeaders({'Authorization': `Bearer ${sessionStorage.getItem("token")}`,"content-type":"application/json"})
    })
  }

  deleteCustomer():Observable<any>{
    console.log(environment.Customer_API + "/delete/" + this.getCustomerUserName());
    return this.httpClient.delete(environment.Customer_API + "/delete/" + this.getCustomerUserName(),
    {
      headers:new HttpHeaders({'Authorization': `Bearer ${sessionStorage.getItem("token")}`})
    })
  }

  loginCustomer(customer : any) : Observable<any>{
  return this.httpClient.post(environment.Customer_API+"/login",customer,
      {headers:new HttpHeaders({"content-type" : "application/json" })})
  }

  registerCustomer(customer : any) : Observable<any>{
    return this.httpClient.post(environment.Customer_API+"/register",customer,
        {headers:new HttpHeaders({"content-type" : "application/json" })})
    }

  storeToken(token : string){
    sessionStorage.setItem('token',token);
  }

  getToken() : any
  {
  return sessionStorage.getItem('token');
  }

  openCustomerRegister(){
    this.router.navigate(["customer/register"])
  }

  openCustomerUpdate(){
    this.router.navigate(["customer/update"])
  }

  openCustomerDashboard(){
    this.router.navigate(["customer/dashboard"])
  }

  openLogin(){
    this.router.navigate(["login"])
  }

  openHome(){
    this.router.navigate([""])
  }

  setCustomerUserName(user : string){
    sessionStorage.setItem('customeruser',user);
  }

  getCustomerUserName(){
     return sessionStorage.getItem('customeruser');
  }
}
