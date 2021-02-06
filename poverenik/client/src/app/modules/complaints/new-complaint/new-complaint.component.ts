import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { ComplaintDecisionService } from 'src/app/services/complaint-decision/complaint-decision.service';
import { ComplaintSilenceService } from 'src/app/services/complaint-silence/complaint-silence.service';
import { complaintType } from '../../home/home-page/home-page.component';

@Component({
  selector: 'app-new-complaint',
  templateUrl: './new-complaint.component.html',
  styleUrls: ['./new-complaint.component.css'],
})
export class NewComplaintComponent implements OnInit {
  decisionForm!: FormGroup;
  silenceForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private decisionService: ComplaintDecisionService,
    private silenceService: ComplaintSilenceService,
    private notification: NzNotificationService,
    private message: NzMessageService
  ) {}

  @Input()
  complaintType: complaintType | undefined;

  isDialogVisible: boolean = false;

  showDialog() {
    this.isDialogVisible = true;
  }

  handleCancel(): void {
    this.isDialogVisible = false;
    this.decisionForm.reset();
    this.silenceForm.reset();
  }

  addComplaintDecision(): void {
    for (const i in this.decisionForm.controls) {
      this.decisionForm.controls[i].markAsDirty();
      this.decisionForm.controls[i].updateValueAndValidity();
    }

    if (this.decisionForm.valid) {
      this.decisionService
        .insert(this.decisionForm.value)
        .subscribe(
          (response) => {
            console.log(response);
            this.message.create('success', `Uspesno ste dodali zalbu`);
          },
          (error) => {
            this.notification.create(
              'error',
              'Error',
              'There was an error in the server'
            );
          }
        )
        .add(() => {
          this.isDialogVisible = false;
        });
    }
  }

  addComplaintSilence(): void {
    for (const i in this.silenceForm.controls) {
      this.silenceForm.controls[i].markAsDirty();
      this.silenceForm.controls[i].updateValueAndValidity();
    }

    if (this.silenceForm.valid) {
      this.silenceService
        .insert(this.silenceForm.value)
        .subscribe(
          (response) => {
            console.log(response);
            this.message.create('success', `Uspesno ste dodali zalbu`);
          },
          (error) => {
            this.notification.create(
              'error',
              'Error',
              'There was an error in the server'
            );
          }
        )
        .add(() => {
          this.isDialogVisible = false;
        });
    }
  }

  ngOnInit(): void {
    this.decisionForm = this.fb.group({
      podnosilacime: [null, [Validators.required]],
      podnosilacprezime: [null, [Validators.required]],
      podnosilacnaziv: [null, [Validators.required]],
      podnosilacadresa: [null, [Validators.required]],
      podnosilacsediste: [null, [Validators.required]],
      nazivorgana: [null, [Validators.required]],
      brojresenja: [null, [Validators.required]],
      datumresenja: [null, [Validators.required]],
      datumpodnosenjazahteva: [null, [Validators.required]],
      opiszalbe: [null, [Validators.required]],
      predajamesto: [null, [Validators.required]],
      predajadatum: [null, [Validators.required]],
      informacijeime: [null, [Validators.required]],
      informacijeprezime: [null, [Validators.required]],
      informacijeadresa: [null, [Validators.required]],
      informacijedrugikontakt: [null, [Validators.required]],
    });
    this.silenceForm = this.fb.group({
      nazivorgana: [null, [Validators.required]],
      razlogzalbe: [null, [Validators.required]],
      datumzahteva: [null, [Validators.required]],
      podacizahteva: [null, [Validators.required]],
      mesto: [null, [Validators.required]],
      datum: [null, [Validators.required]],
      ime: [null, [Validators.required]],
      prezime: [null, [Validators.required]],
      adresa: [null, [Validators.required]],
      drugikontakt: [null, [Validators.required]]
    });
  }
}
