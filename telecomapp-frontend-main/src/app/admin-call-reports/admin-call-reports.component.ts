import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MatDialog} from '@angular/material/dialog';
import { AdminService } from '../z-Services/admin.service';
import { CallService } from '../z-Services/Call.service';
import { Call } from '../model/Call';
import { jsPDF } from 'jspdf';
import 'jspdf-autotable';

export interface csrlist {
  name: string;
  csrId: number;
  userName: string;
  enabled: boolean;
  admin: boolean
}

@Component({
  selector: 'app-admin-call-reports',
  templateUrl: './admin-call-reports.component.html',
  styleUrls: ['./admin-call-reports.component.css']
})

export class AdminCallReportsComponent implements OnInit {

  calllist : Call[] = [];
  state = false;
  errMessage ="";
  displayedColumns: string[] = ['Username', 'PhoneNumber', 'CsrId','Behaviour','Details','Duration','Rating','Date', 'Source'];
  myinfo = new MatTableDataSource(this.calllist);
  tempCsrId : number = 0;
  tempDate : string = "";
  tempType : string = "";
  tempPhoneNo : number = 0;

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.myinfo.filter = filterValue.trim().toLowerCase();
  }

  constructor(private adminService:AdminService, private callService:CallService,
    private matSnackBar:MatSnackBar, public dialog: MatDialog) { }

  ngOnInit() {
    this.callService.getAllCalls().subscribe(
      (res:any)=>
      {
        this.calllist=res;
        this.myinfo.data = this.calllist;
        console.log(this.calllist)
      },
      (err:any)=>
      {
        this.errMessage="Error Unable to fetch Data from backend";
        this.state=true;
      }
    )
  }

  goBack(){
    this.adminService.openAdminDashBoard();
  }

  getById(){
    this.callService.getAllCallsByCsrId(this.tempCsrId).subscribe(
      (res:any)=>
      {
        this.calllist = res;
        if(this.calllist.length==0)
        {
          this.errMessage="Error: No Calls found by Given Id";
          this.state=true;
        }
        else{
          this.toPDF();
          this.matSnackBar.open("Exported Data by CsrID", "Done", {
            duration: 1000
          });
        }
      },
      (err:any)=>
      {
        this.errMessage="Error failed to fetch data by CsrID";
        this.state=true;
      }
    )
  }

  getByType(){
    this.callService.getAllCallsByCallSource(this.tempType).subscribe(
      (res:any)=>
      {
        this.calllist = res;
        if(this.calllist.length==0)
        {
          this.errMessage="Error: No Calls found by Given Source";
          this.state=true;
        }
        else{
          this.toPDF();
          this.matSnackBar.open("Exported Data by Source", "Done", {
            duration: 1000
          });
        }
      },
      (err:any)=>
      {
        this.errMessage="Error failed to fetch data by source";
        this.state=true;
      }
    )
  }

  getByDate(){
    this.callService.getAllCallsByCsrDate(this.tempDate).subscribe(
      (res:any)=>
      {
        this.calllist = res;
        if(this.calllist.length==0)
        {
          this.errMessage="Error: No Calls found by Given Date"
          this.state=true;
        }
        else{
          this.toPDF();
          this.matSnackBar.open("Exported Data by Date", "Done", {
            duration: 1000
          });
        }
      },
      (err:any)=>
      {
        this.errMessage="Error failed to fetch data by Date";
        this.state=true;
      }
    )
  }

  getByPhoneNo(){
    this.callService.getAllCallsByPhone(this.tempPhoneNo).subscribe(
      (res:any)=>
      {
        this.calllist = res;
        if(this.calllist.length==0)
        {
          this.errMessage="Error: No Calls found by Given Phone No"
          this.state=true;
        }
        else{
          this.toPDF();
          this.matSnackBar.open("Exported Data by Phone No", "Done", {
            duration: 1000
          });
        }
      },
      (err:any)=>
      {
        this.errMessage="Error failed to fetch data by Phone No";
        this.state=true;
      }
    )
  }

  getAllCalls(){
    this.callService.getAllCalls().subscribe(
      (res:any)=>
      {
        this.calllist = res;
        if(this.calllist.length==0)
        {
          this.errMessage="Error: No Calls Exist in database"
          this.state=true;
        }
        else{
          this.toPDF();
          this.matSnackBar.open("Exported All Call Data", "Done", {
            duration: 1000
          });
        }
      },
      (err:any)=>
      {
        this.errMessage="Error failed to fetch data";
        this.state=true;
      }
    )
  }

  toPDF(){
    var doc = new jsPDF();
    var col = [["UserName", "PhoneNo","CsrId","Behaviour","Details","Duration","Rating","Date","Source"]];
    var rows: (string | number | undefined)[][]= [];
    this.calllist.forEach(element => {
      var temp = [element.customerUserName,element.customerPhoneNumber,element.csrId,element.customerBehaviour,element.callDetails,element.callDuration,element.callRating, element.dateAdded, element.callSource];
      console.log(temp);
      rows.push(temp);
    });
    (doc as any).autoTable({
      head: col,
      body: rows
    });
    doc.save('CallExport.pdf');
    this.calllist = [];
  }
}
