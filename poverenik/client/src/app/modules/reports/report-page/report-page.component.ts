import { Component, OnInit } from '@angular/core';
import { ReportService } from 'src/app/services/report/report.service';

@Component({
  selector: 'app-report-page',
  templateUrl: './report-page.component.html',
  styleUrls: ['./report-page.component.css'],
})
export class ReportPageComponent implements OnInit {
  reports: any[] = [];

  loading: boolean = true;

  constructor(private service: ReportService) {}

  ngOnInit(): void {
    this.service.get().subscribe(
      (response) => {
        if (response.body !== null) {
          console.log(response.body);
          this.reports = response.body.izvestaj;
          this.loading = false;
        }
      },
      () => {}
    );
  }
}
