import { Component, OnInit } from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';
import {MatIconRegistry} from '@angular/material/icon';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  constructor(private matIconRegistry: MatIconRegistry,
    private domSanitizer: DomSanitizer) {
    this.matIconRegistry.addSvgIcon(
      "facebook-1",
      this.domSanitizer.bypassSecurityTrustResourceUrl("../../assets/facebook-1.svg")
    );
    this.matIconRegistry.addSvgIcon(
      "snapchat",
      this.domSanitizer.bypassSecurityTrustResourceUrl("../../assets/snapchat.svg")
    );
    this.matIconRegistry.addSvgIcon(
      "instagram",
      this.domSanitizer.bypassSecurityTrustResourceUrl("../../assets/instagram.svg")
    );
    this.matIconRegistry.addSvgIcon(
      "twitter",
      this.domSanitizer.bypassSecurityTrustResourceUrl("../../assets/twitter.svg")
    );
   }

  ngOnInit() {
  }

}
