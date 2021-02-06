import { Component, Input, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ZahtevService } from 'src/app/services/zahtev.service';

@Component({
  selector: 'app-xhtml-prikaz',
  templateUrl: './xhtml-prikaz.component.html',
  styleUrls: ['./xhtml-prikaz.component.css']
})
export class XhtmlPrikazComponent implements OnInit {
  public content: string ="";  
  @Input() id!: string;

  constructor(private registrationService: ZahtevService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.registrationService.vratiZahtevXHTML(this.id).subscribe((response)=>{
      this.content = response;
    })
  }

  open(content: any) {
    
  }


}
