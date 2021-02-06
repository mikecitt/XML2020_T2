import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Zahtev } from 'src/app/model/zahtev';
import { AuthService } from 'src/app/services/auth.service';
import { ZahtevService } from 'src/app/services/zahtev.service';

@Component({
  selector: 'app-podnosenje-zahteva',
  templateUrl: './podnosenje-zahteva.component.html',
  styleUrls: ['./podnosenje-zahteva.component.css']
})
export class PodnosenjeZahtevaComponent implements OnInit {

  form:FormGroup;
  public wrongUsernameOrPass = false;
  public errorMessage = "";
  public emptyField = false;
  public passwordmissmatch = false;
  public invalidemail = false;

  constructor(private fb:FormBuilder, 
              private zahtevService: ZahtevService,
              private authService: AuthService,
              private router: Router) { 
    this.form = this.fb.group({
      nazivorgana: ['',Validators.required],
      sedisteorgana: ['',Validators.required],
      svrhazahteva: ['',Validators.email],
      nacindostave: ['',Validators.required],
      detaljanopis: ['', Validators.required],
      mestopredaje: ['', Validators.required],
      ime: ['', Validators.required],
      prezime: ['', Validators.required],
      adresa: ['', Validators.required],
      brojtelefona: ['', Validators.required],
    });
    
  }

  ngOnInit(): void {
  }


  podnesiZahtev() {
    const val = this.form.value;
    this.emptyField = false;
    if(!val.nazivorgana || !val.sedisteorgana || !val.svrhazahteva || !val.nacindostave || !val.detaljanopis
    || !val.mestopredaje || !val.ime || !val.prezime || !val.adresa || !val.brojtelefona){
      this.emptyField = true;
    }
    else{
      var dt = new Date();
      var month = dt.getMonth()+1;
      var zahtev: Zahtev = {
        id:'',
        nazivOrgana: val.nazivorgana,
        sediste: val.sedisteorgana,
        svrha: val.svrhazahteva,
        nacinDostave: val.nacindostave,
        opisInformacije: val.detaljanopis,
        mestoPredaje: val.mestopredaje,
        datum: dt.getFullYear()+"-"+month+"-"+dt.getDate(), 
        ime: val.ime,
        prezime: val.prezime,
        adresa: val.adresa,
        kontakt: val.brojtelefona,
        status:""
      };
      console.dir(zahtev);
      this.zahtevService.dodajZahtev(zahtev).subscribe(res => {
        console.log("dodao");
      });
    }
  }

  logOut(){
    this.authService.logout();
    this.router.navigateByUrl('/dashboard');
  }
}
