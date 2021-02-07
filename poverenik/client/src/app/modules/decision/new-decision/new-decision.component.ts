import { Component, Input, OnInit } from '@angular/core';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { ComplaintDecisionService } from 'src/app/services/complaint-decision/complaint-decision.service';
import { ComplaintSilenceService } from 'src/app/services/complaint-silence/complaint-silence.service';
import { complaintType } from '../../home/home-page/home-page.component';

@Component({
  selector: 'app-new-decision',
  templateUrl: './new-decision.component.html',
  styleUrls: ['./new-decision.component.css'],
})
export class NewDecisionComponent implements OnInit {
  @Input()
  complaint: any;

  @Input()
  complaintType: complaintType = 'odluka';

  requestLoading: boolean = false;

  tabIndex: 'CEKANJE' | 'ZAHTEVANJE' | 'OBRADA' = 'CEKANJE';

  constructor(
    private decisionService: ComplaintDecisionService,
    private silenceService: ComplaintSilenceService,
    private notification: NzNotificationService
  ) {}

  ngOnInit(): void {
    if (!this.complaint.status) {
      this.tabIndex = 'CEKANJE';
    } else if (this.complaint.status.value === 'ZAHTEVANJE') {
      this.tabIndex = 'ZAHTEVANJE';
    } else {
      this.tabIndex = 'OBRADA';
    }
  }

  sendRequest() {
    this.requestLoading = true;

    let id = this.complaint.about.substring(
      this.complaint.about.lastIndexOf('/') + 1
    );

    if (this.complaintType === 'odluka') {
      this.decisionService
        .sendRequest(id)
        .subscribe(
          () => {
            this.tabIndex = 'ZAHTEVANJE';
          },
          () => {
            this.notification.create(
              'error',
              'Error',
              'There was an error in the server'
            );
          }
        )
        .add(() => {
          this.requestLoading = false;
        });
    } else {
      this.silenceService
        .sendRequest(id)
        .subscribe(
          () => {
            this.tabIndex = 'ZAHTEVANJE';
          },
          () => {
            this.notification.create(
              'error',
              'Error',
              'There was an error in the server'
            );
          }
        )
        .add(() => {
          this.requestLoading = false;
        });
    }
  }
}
