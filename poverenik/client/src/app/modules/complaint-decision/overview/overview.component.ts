import { Component, Input, OnInit } from '@angular/core';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { ComplaintDecisionService } from 'src/app/services/complaint-decision/complaint-decision.service';

@Component({
  selector: 'app-complaint-decision-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.css'],
})
export class ComplaintDecisionOverviewComponent implements OnInit {
  @Input()
  complaint: any;

  pdfLoading: boolean = false;

  constructor(
    private service: ComplaintDecisionService,
    private notification: NzNotificationService
  ) {}

  openComplaintPdf() {
    this.pdfLoading = true;

    let id = this.complaint.about.substring(
      this.complaint.about.lastIndexOf('/') + 1
    );

    this.service
      .getPdf(id)
      .subscribe(
        (response) => {
          if (response.body !== null) {
            let file = new Blob([response.body], { type: 'application/pdf' });
            var fileURL = URL.createObjectURL(file);
            window.open(fileURL);
          }
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
        this.pdfLoading = false;
      });
  }

  ngOnInit(): void {}
}
