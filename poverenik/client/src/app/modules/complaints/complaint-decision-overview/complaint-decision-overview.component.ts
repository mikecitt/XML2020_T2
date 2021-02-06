import { Component, Input, OnInit } from '@angular/core';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { ComplaintDecisionService } from 'src/app/services/complaint-decision/complaint-decision.service';

@Component({
  selector: 'app-complaint-decision-overview',
  templateUrl: './complaint-decision-overview.component.html',
  styleUrls: ['./complaint-decision-overview.component.css'],
})
export class ComplaintDecisionOverviewComponent implements OnInit {
  @Input()
  complaint: any;

  pdfLoading: boolean = false;
  htmlLoading: boolean = false;

  decisionPdfLoading: boolean = false;
  decisionHtmlLoading: boolean = false;

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

  openComplaintHtml() {
    this.htmlLoading = true;

    let id = this.complaint.about.substring(
      this.complaint.about.lastIndexOf('/') + 1
    );

    this.service
      .getHtml(id)
      .subscribe(
        (response) => {
          if (response.body !== null) {
            var win = window.open('', '_blank');
            if (win !== null) {
              win.document.write(response.body);
              win.focus();
            }
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
        this.htmlLoading = false;
      });
  }

  openDecisionPdf() {
    this.decisionPdfLoading = true;

    let id = this.complaint.about.substring(
      this.complaint.about.lastIndexOf('/') + 1
    );

    this.service
      .getDecisionPdf(id)
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
        this.decisionPdfLoading = false;
      });
  }

  openDecisionHtml() {
    this.decisionHtmlLoading = true;

    let id = this.complaint.about.substring(
      this.complaint.about.lastIndexOf('/') + 1
    );

    this.service
      .gettDecisionHtml(id)
      .subscribe(
        (response) => {
          if (response.body !== null) {
            var win = window.open('', '_blank');
            if (win !== null) {
              win.document.write(response.body);
              win.focus();
            }
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
        this.decisionHtmlLoading = false;
      });
  }

  ngOnInit(): void {}
}
