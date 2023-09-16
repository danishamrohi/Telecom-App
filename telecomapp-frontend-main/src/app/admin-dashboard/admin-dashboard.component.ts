import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MatDialog} from '@angular/material/dialog';
import { AdminService } from '../z-Services/admin.service';
import { CallService } from '../z-Services/Call.service';
import { Csr } from '../model/Csr';
import { Call } from '../model/Call';

export interface csrlist {
  name: string;
  csrId: number;
  userName: string;
  enabled: boolean;
  admin: boolean
}

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})

export class AdminDashboardComponent implements OnInit{

  csrlist : Csr[] = [];
  calllist : Call[] = [];
  state = false;
  errMessage ="";
  displayedColumns: string[] = ['csrId', 'name', 'userName','email','phoneNo','enabled','admin','Update', 'Delete'];

  myinfo = new MatTableDataSource(this.csrlist);
  tempCsrId : number = 0;
  tempDate : string = "";
  tempType : string = "";

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.myinfo.filter = filterValue.trim().toLowerCase();
  }

  constructor(private adminService:AdminService, private callService:CallService,
    private matSnackBar:MatSnackBar, public dialog: MatDialog) { }

  ngOnInit() {
    this.adminService.fetchAllCsr().subscribe(
      (res:any)=>
      {
        this.csrlist=res;
        this.myinfo.data = this.csrlist;
        console.log(this.csrlist)
      },
      (err:any)=>
      {
        this.errMessage="Error Unable to fetch Data from backend";
        this.state=true;
      }
    )
  }

  updateCsr(CsrId: number){
    this.adminService.setCsrId(CsrId);
    this.adminService.openAdminUpdate();
  }

  deleteConfirm(csrId : number) {
    if(confirm("Are you sure you want to delete CsrID "+csrId)) {
      this.deleteCsr(csrId);
    }
  }

  deleteCsr(csrId : number)
  {
    this.adminService.deleteCsr(csrId).subscribe(
      (res:any)=>
      {
      },
      (err:any)=>
      {
        this.errMessage="Error failed to delete Csr";
        this.state=true;
      }
    )
    window.location.reload();
  }

  goRegister(){
    this.adminService.openAdminRegistration();
  }

  goToCallReports(){
    this.adminService.openCallReports();
  }
}

