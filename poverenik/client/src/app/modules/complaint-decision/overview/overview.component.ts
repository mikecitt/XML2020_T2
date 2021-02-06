import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-complaint-decision-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.css'],
})
export class ComplaintDecisionOverviewComponent implements OnInit {
  @Input()
  complaint: any;

  constructor() {}

  ngOnInit(): void {}
}
