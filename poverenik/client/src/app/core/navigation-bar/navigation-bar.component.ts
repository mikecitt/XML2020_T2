import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/auth/authentication.service';

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css'],
})
export class NavigationBarComponent implements OnInit {
  constructor(private authService: AuthenticationService) {}

  get isLogged() {
    return this.authService.isLogged();
  }

  get isPoverenik() {
    return this.authService.getRole() === 'ROLE_POVERENIK';
  }

  logout() {
    this.authService.logout();
  }

  ngOnInit(): void {}
}
