import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Zalba } from 'src/app/model/zalba';
import { AuthService } from 'src/app/services/auth.service';
import { ZalbaService } from 'src/app/services/zalba.service';

@Component({
  selector: 'app-zalba-list',
  templateUrl: './zalba-list.component.html',
  styleUrls: ['./zalba-list.component.css']
})
export class ZalbaListComponent implements OnInit {
  public zalbeCutanje : Zalba[] = [];
  public zalbeOdluka : Zalba[] = [];
  public userType: string = this.authService.getRole();


  constructor(private zalbaService: ZalbaService,
    private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.zalbaService.vratiSveZalbeCutanje().subscribe((res) => {
      this.zalbeCutanje = res;
    });
    this.zalbaService.vratiSveZalbeOdluka().subscribe((res) => {
      this.zalbeOdluka = res;
    });
  }

  logOut(){
    this.authService.logout();
    this.router.navigateByUrl('');
  }

  onAdded(added: any) {
    console.log(added);
    this.zalbeCutanje = this.zalbeCutanje.filter(item => item.id !== added);
    this.zalbeOdluka = this.zalbeOdluka.filter(item => item.id !== added);
  }

  getZalbaCutanjePDF(id:string){
    this.zalbaService.vratiZalbuCutanjePDF(id).subscribe((response)=>{
      let file = new Blob([response], { type: 'application/pdf' });            
      var fileURL = URL.createObjectURL(file);
      window.open(fileURL);
    })
  }

  getZalbaOdlukaPDF(id:string){
    this.zalbaService.vratiZalbuOdlukaPDF(id).subscribe((response)=>{
      let file = new Blob([response], { type: 'application/pdf' });            
      var fileURL = URL.createObjectURL(file);
      window.open(fileURL);
    })
  }
}
