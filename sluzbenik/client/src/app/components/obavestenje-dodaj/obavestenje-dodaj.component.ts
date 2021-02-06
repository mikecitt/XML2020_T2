import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbDateStruct, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Obavestenje } from 'src/app/model/obavestenje';
import { Zahtev } from 'src/app/model/zahtev';
import { AuthService } from 'src/app/services/auth.service';
import { ZahtevService } from 'src/app/services/zahtev.service';

@Component({
  selector: 'app-obavestenje-dodaj',
  templateUrl: './obavestenje-dodaj.component.html',
  styleUrls: ['./obavestenje-dodaj.component.css']
})
export class ObavestenjeDodajComponent implements OnInit {

  public submitValidate: boolean = false;
  @Input()
  public zahtev!: Zahtev;
  @Output() added = new EventEmitter<any>();
  public model!: NgbDateStruct;
  public timeSat  = {hour: 13, minute: 0};

  myForm = new FormGroup({
   brojpredmeta: new FormControl('', Validators.required),
   datumuvida: new FormControl('', Validators.required),
   sat: new FormControl('', Validators.required),
   mesto: new FormControl('', Validators.required),
   ulica: new FormControl('', Validators.required),
   broj: new FormControl('', Validators.required),
   brojkancelarije: new FormControl('', Validators.required),
   cena: new FormControl('', Validators.required),
   
 });

 constructor(private modalService: NgbModal, private auth_service: AuthService, private zahtevService: ZahtevService) {
    
  }

 ngOnInit(): void {
 }

 get f(){
   return this.myForm.controls;
 }

 open(content: any) {
  this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
  }, (reason: any) => {
    
  });
}

 clearForm(){
  
 }

 dodajObavestenje(){
  const val = this.myForm.value;
  this.submitValidate = false;
  console.log(val.sat);
   if(!val.brojpredmeta || !val.datumuvida || !val.sat || !val.mesto || !val.ulica || !val.broj || !val.brojkancelarije
      || !val.cena)
    this.submitValidate = true;
   else{
      var obavestenje : Obavestenje = {
        nazivOrgana: this.zahtev.nazivOrgana,
        sedisteOrgana: this.zahtev.sediste,
        brojPredmeta: val.brojpredmeta,
        ime: this.zahtev.ime,
        prezime: this.zahtev.prezime,
        adresa: this.zahtev.adresa,
        opisInformacije: this.zahtev.opisInformacije,
        datumUvida: val.datumuvida.year+"-"+val.datumuvida.month+"-"+val.datumuvida.day,
        vremeUvida: val.sat.hour,
        od: 9+"",
        do: 17+"",
        mestoUvida: val.mesto,
        ulicaUvida: val.ulica,
        brojUlice: val.broj,
        brojKancelarije: val.brojkancelarije,
        trosak: val.cena,
        datumPodnosenja: this.zahtev.datum
      };
      //console.dir(obavestenje);
      this.zahtevService.dodajObavestenje(obavestenje, this.zahtev.id).subscribe((res) => {
        this.added.emit(this.zahtev.id);
        //this.clearForm();
        this.modalService.dismissAll();
        //console.log(res.content + " " + res.culturalOfferName + " " + res.userUsername + " " + res.imageUrls);
      })
   }
 }
}
