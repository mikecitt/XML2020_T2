import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/auth/authentication.service';

export type complaintType = 'odluka' | 'cutanje';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
})
export class HomePageComponent implements OnInit {
  selectedTab: complaintType = 'odluka';

  constructor(private authService: AuthenticationService) {}

  get isLogged(): boolean {
    return this.authService.isLogged();
  }

  get isPoverenik() {
    console.log(this.authService.getRole() === 'ROLE_POVERENIK');
    return this.authService.getRole() === 'ROLE_POVERENIK';
  }

  ngOnInit(): void {}
}
