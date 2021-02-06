import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbDateStruct, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Zalba } from 'src/app/model/zalba';
import { AuthService } from 'src/app/services/auth.service';
import { ZalbaService } from 'src/app/services/zalba.service';

@Component({
  selector: 'app-obrazlozi-zalbu',
  templateUrl: './obrazlozi-zalbu.component.html',
  styleUrls: ['./obrazlozi-zalbu.component.css']
})
export class ObrazloziZalbuComponent implements OnInit {
  public submitValidate: boolean = false;
  @Input()
  public zalba!: Zalba;
  @Output() added = new EventEmitter<any>();
  @Input()
  public status!: string;

  myForm = new FormGroup({
    obrazlozenje: new FormControl('', Validators.required)
  });
 

  constructor(private modalService: NgbModal, private auth_service: AuthService, private zalbaService: ZalbaService) { }

  ngOnInit(): void {
  }

  get f(){
    return this.myForm.controls;
  }

  onAdded(added: any) {
    
  }
 
  open(content: any) {
   this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
   }, (reason: any) => {
     
   });
 }

 dodajObrazlozenje(){
  const val = this.myForm.value;
  this.submitValidate = false;
   if( !val.obrazlozenje)
    this.submitValidate = true;
   else{
     if(this.status == "odluka"){
       this.zalbaService.obrazloziZalbuOdluka(val.obrazlozenje, this.zalba.id).subscribe((res)=>{
         this.added.emit(this.zalba.id);
       });
     }
     else{
       this.zalbaService.obrazloziZalbuCutanje(val.obrazlozenje, this.zalba.id).subscribe((res)=>{
        this.added.emit(this.zalba.id);
        
        this.modalService.dismissAll();
      });
     }
   }
 }

}
