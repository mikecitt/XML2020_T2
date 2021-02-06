import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { element } from 'protractor';
import { Zahtev } from 'src/app/model/zahtev';
import { AuthService } from 'src/app/services/auth.service';
import { ZahtevService } from 'src/app/services/zahtev.service';

@Component({
  selector: 'app-zahtevi-list',
  templateUrl: './zahtevi-list.component.html',
  styleUrls: ['./zahtevi-list.component.css']
})
export class ZahteviListComponent implements OnInit {
  public zahtevi : Zahtev[] = [];
  public userType: string = this.authService.getRole();

  constructor(private registrationService: ZahtevService,
      private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    if(this.userType == "ROLE_SLUZBENIK")
      this.registrationService.vratiSveZahteve().subscribe((res) => {
        this.zahtevi = res;
      });
    else
      this.registrationService.vratiSveMojeZahteve().subscribe((res) => {
        this.zahtevi = res;
      });
  }

  onAdded(added: any) {
    this.zahtevi.forEach(element => {
      if(element.id == added){
        element.status = 'PRIHVACEN';
        //console.log("prihvatio");
      }
    });
  }

  logOut(){
    this.authService.logout();
    this.router.navigateByUrl('');
  }

  getZahtevPDF(id:string){
    this.registrationService.vratiZahtevPDF(id).subscribe((response)=>{
      let file = new Blob([response], { type: 'application/pdf' });            
      var fileURL = URL.createObjectURL(file);
      window.open(fileURL);
    })
  }

  getZahtevXHTML(id:string){
    this.registrationService.vratiZahtevXHTML(id).subscribe((response)=>{
      var win = window.open('', '_blank');
      if (win !== null) {
        win.document.write(response);
        win.focus();
      }
    })
  }

  getObavestenjPDF(id:string){
    this.registrationService.vratiObavestenjePDF(id).subscribe((response)=>{
      let file = new Blob([response], { type: 'application/pdf' });            
      var fileURL = URL.createObjectURL(file);
      window.open(fileURL);
    })
  }

  getObavestenjXHTML(id:string){
    this.registrationService.vratiObavestenjeXHTML(id).subscribe((response)=>{
      var win = window.open('', '_blank');
      if (win !== null) {
        win.document.write(response);
        win.focus();
      }
    })
  }

  declineZahtev(id:string){
    this.registrationService.odbijZahtev(id).subscribe((response)=>{
      this.zahtevi.forEach(element => {
        if(element.id == id){
          element.status = 'ODBIJEN';
          //console.log("prihvatio");
        }
      });
    })
  }
}
