import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Resenje } from 'src/app/model/resenje';
import { AuthService } from 'src/app/services/auth.service';
import { ResenjaService } from 'src/app/services/resenja.service';

@Component({
  selector: 'app-resenja-list',
  templateUrl: './resenja-list.component.html',
  styleUrls: ['./resenja-list.component.css']
})
export class ResenjaListComponent implements OnInit {
  public resenja : Resenje[] = [];
  public userType: string = this.authService.getRole();

  constructor(private resenjeService: ResenjaService,
    private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    if(this.userType == "ROLE_SLUZBENIK")
      this.resenjeService.vratiSvaResenja().subscribe((res) => {
        this.resenja = res;
      });
  }

  logOut(){
    this.authService.logout();
    this.router.navigateByUrl('');
  }

  getResenjePDF(id:string){
    this.resenjeService.vratiResenjePDF(id).subscribe((response)=>{
      let file = new Blob([response], { type: 'application/pdf' });            
      var fileURL = URL.createObjectURL(file);
      window.open(fileURL);
    })
  }

  /*getResenjeXHTML(id:string){
    this.resenjeService.vratiResenjeXHTML(id).subscribe((response)=>{
      let file = new Blob([response], { type: 'application/pdf' });            
      var fileURL = URL.createObjectURL(file);
      window.open(fileURL);
    })
  }*/

}
