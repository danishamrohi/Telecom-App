import { Component } from '@angular/core';
import { Router,NavigationEnd  } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'telecom-frontend';

  currentRoute: string = "";

  constructor(private router: Router){
    console.log(router.url);


    }

  checkurl(){
    if(this.router.url == 'http://localhost:4200/admin/register' || this.router.url == 'http://localhost:4200/csr/add-call'){
      return false;
    }
    else
    return true;
  }
}
