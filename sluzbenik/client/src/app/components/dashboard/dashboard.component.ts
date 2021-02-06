import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  public userType: string = "";

  constructor(private authService: AuthService,
    private router: Router) {
      this.userType = this.authService.getCurrentUser().authorities[0];
      console.log(this.userType);
     }

  ngOnInit(): void {
  }

  logOut(){
    this.authService.logout();
    this.router.navigateByUrl('/dashboard');
  }

}
