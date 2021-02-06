import { Component, Input, OnInit } from '@angular/core';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { ReportService } from 'src/app/services/report/report.service';

@Component({
  selector: 'app-report-overview',
  templateUrl: './report-overview.component.html',
  styleUrls: ['./report-overview.component.css'],
})
export class ReportOverviewComponent implements OnInit {
  @Input()
  report: any;

  pdfLoading: boolean = false;

  get time() {
    const date = new Date(this.report.datumPodnosenja.value);
    return date.toLocaleDateString();
  }

  openPdf() {
    this.pdfLoading = true;

    let id = this.report.about.substring(
      this.report.about.lastIndexOf('/') + 1
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

  constructor(
    private service: ReportService,
    private notification: NzNotificationService
  ) {}

  ngOnInit(): void {}
}
