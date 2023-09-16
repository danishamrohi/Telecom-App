import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Call } from '../model/Call';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})

export class CallService {

constructor(private httpClient:HttpClient, private router:Router) { }

addCall(callData : Call):Observable<Call> {
  return this.httpClient.post(environment.Call_API + "/add", callData, {
    headers:new HttpHeaders({'Authorization': `Bearer ${sessionStorage.getItem("token")}`, "content-type" : "application/json" })
  })
}

getAllCalls():Observable<Array<Call>>{
  return this.httpClient.get<Array<Call>>(environment.Call_API + "/all");
}

getAllCallsByPhone(phoneNumber : number):Observable<Array<Call>>{
  return this.httpClient.get<Array<Call>>(environment.Call_API + "/all-with-ph/" + phoneNumber,{
    headers:new HttpHeaders({'Authorization': `Bearer ${sessionStorage.getItem("token")}`})
  });
}

getAllCallsByCallSource(callSource : string):Observable<Array<Call>>{
  return this.httpClient.get<Array<Call>>(environment.Call_API + "/all-with-source/" + callSource);
}

getAllCallsByCsrId(csrId : number):Observable<Array<Call>>{
  return this.httpClient.get<Array<Call>>(environment.Call_API + "/all-with-csrid/" + csrId);
}

getAllCallsByCsrDate(date : string):Observable<Array<Call>>{
  return this.httpClient.get<Array<Call>>(environment.Call_API + "/all-with-date/" + date);
}

deleteCall(callId:string):Observable<boolean>{
  return this.httpClient.delete<boolean>(environment.Call_API + "/delete/" + callId)
}

openCsrDashBoard(){
  this.router.navigate(["csr/dashboard"])
}

openCsrAddCall(){
  this.router.navigate(["csr/add-call"])
}

}
