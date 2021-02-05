import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { AuthenticationService } from 'src/app/services/auth/authentication.service';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css'],
})
export class RegisterFormComponent implements OnInit {
  registerForm!: FormGroup;

  loading: boolean = false;

  register(): void {
    for (const i in this.registerForm.controls) {
      this.registerForm.controls[i].markAsDirty();
      this.registerForm.controls[i].updateValueAndValidity();
    }

    if (this.registerForm.valid) {
      this.loading = true;

      this.authService
        .register(
          this.registerForm.value.email,
          this.registerForm.value.password,
          this.registerForm.value.firstName,
          this.registerForm.value.lastName
        )
        .subscribe(
          () => {
            this.router.navigateByUrl('/');
            this.notification.create(
              'success',
              'Successfully registered',
              'Go to login page to log in'
            );
          },
          (error) => {
            switch (error.status) {
              case 409:
                this.message.create('warning', `Email already in use`);
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
    this.registerForm = this.fb.group({
      firstName: [null, [Validators.required]],
      lastName: [null, [Validators.required]],
      email: [null, [Validators.email, Validators.required]],
      password: [null, [Validators.required]],
    });
  }
}
