import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form:FormGroup;
  public wrongUsernameOrPass = false;
  public errorMessage = "";
  public invalidData = false;

  constructor(private fb:FormBuilder, 
              private loginService: AuthService, 
              private router: Router) { 
    this.form = this.fb.group({
      username: ['',Validators.required],
      password: ['',Validators.required]
    });
    
  }

  ngOnInit(): void {
  }

  login() {
    const val = this.form.value;
    this.wrongUsernameOrPass = false;

    if (val.username && val.password) {
      this.invalidData = false;
      this.loginService.login(val.username, val.password)
          .subscribe(
              (loggedIn) => {
                this.router.navigateByUrl('/dashboard');
                console.log("usao");
                },
              (err:Error) => {
                if(err.toString()==='Ilegal login'){
                  this.wrongUsernameOrPass = true;
                  console.log(err);
                }
                else{
                  throwError(err);
                }
              }
          ); 
    }
    else{
      this.invalidData = true;
      this.errorMessage = "Both username and password are required";
    }
  }

}
