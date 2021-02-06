import { Component, OnInit } from '@angular/core';
import { ComplaintSilenceService } from 'src/app/services/complaint-silence/complaint-silence.service';

@Component({
  selector: 'app-home-complaints-silence',
  templateUrl: './complaints-silence.component.html',
  styleUrls: ['./complaints-silence.component.css'],
})
export class HomeComplaintsSilenceComponent implements OnInit {
  complaints: any[] = [];

  constructor(private service: ComplaintSilenceService) {}

  ngOnInit(): void {
    this.service.get().subscribe(
      (response) => {
        if (response.body !== null) {
          this.complaints = response.body.zalbacutanje;
        }
      },
      () => {}
    );
  }
}
