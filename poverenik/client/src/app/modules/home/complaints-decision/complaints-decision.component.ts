import { Component, OnInit } from '@angular/core';
import { ComplaintDecisionService } from 'src/app/services/complaint-decision/complaint-decision.service';

@Component({
  selector: 'app-home-complaints-decision',
  templateUrl: './complaints-decision.component.html',
  styleUrls: ['./complaints-decision.component.css'],
})
export class HomeComplaintsDecisionComponent implements OnInit {
  complaints: any[] = [];

  constructor(private service: ComplaintDecisionService) {}

  ngOnInit(): void {
    this.service.get().subscribe(
      (response) => {
        if (response.body !== null) {
          this.complaints = response.body.zalbanaodluku;
        }
      },
      () => {}
    );
  }
}
