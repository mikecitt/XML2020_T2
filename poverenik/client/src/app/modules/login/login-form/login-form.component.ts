import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import {
  AuthenticationService,
  LoginResponse,
} from 'src/app/services/auth/authentication.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css'],
})
export class LoginFormComponent implements OnInit {
  loginForm!: FormGroup;

  loading: boolean = false;

  login(): void {
    for (const i in this.loginForm.controls) {
      this.loginForm.controls[i].markAsDirty();
      this.loginForm.controls[i].updateValueAndValidity();
    }

    if (this.loginForm.valid) {
      this.loading = true;

      this.authService
        .login(this.loginForm.value.email, this.loginForm.value.password)
        .subscribe(
          (response) => {
            if (response.body !== null) {
              this.authService.setLoginCredentials(
                response.body.accessToken,
                response.body.role
              );
              this.router.navigateByUrl('/');
            }
          },
          (error) => {
            switch (error.status) {
              case 401:
                this.message.create('warning', `Incorrect email or password`);
                break;
              default:
                this.notification.create(
                  'error',
                  'Error',
                  'There was an error in the server'
                );
                break;
            }
          }
        )
        .add(() => {
          this.loading = false;
        });
    }
  }

  constructor(
    private fb: FormBuilder,
    private authService: AuthenticationService,
    private notification: NzNotificationService,
    private message: NzMessageService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: [null, [Validators.email, Validators.required]],
      password: [null, [Validators.required]],
    });
  }
}
