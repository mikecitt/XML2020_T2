import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-complaint-silence-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.css'],
})
export class ComplaintSilenceOverviewComponent implements OnInit {
  @Input()
  complaint: any;

  constructor() {}

  ngOnInit(): void {}
}
