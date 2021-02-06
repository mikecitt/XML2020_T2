import { Component, Input, OnInit } from '@angular/core';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { ComplaintSilenceService } from 'src/app/services/complaint-silence/complaint-silence.service';

@Component({
  selector: 'app-complaint-silence-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.css'],
})
export class ComplaintSilenceOverviewComponent implements OnInit {
  @Input()
  complaint: any;

  pdfLoading: boolean = false;
  htmlLoading: boolean = false;

  constructor(
    private service: ComplaintSilenceService,
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

  ngOnInit(): void {}
}
