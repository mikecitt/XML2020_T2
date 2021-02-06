import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/auth/authentication.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
})
export class HomePageComponent implements OnInit {
  selectedTab: string = 'cutanje';

  constructor(private authService: AuthenticationService) {}

  get isLogged(): boolean {
    return this.authService.isLogged();
  }

  ngOnInit(): void {}
}
