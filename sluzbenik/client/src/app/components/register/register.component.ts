import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { RegistrationService } from 'src/app/services/registration.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form:FormGroup;
  public wrongUsernameOrPass = false;
  public errorMessage = "";
  public emptyField = false;
  public passwordmissmatch = false;
  public invalidemail = false;

  constructor(private fb:FormBuilder, 
              private registrationService: RegistrationService, 
              private router: Router) { 
    this.form = this.fb.group({
      firstname: ['',Validators.required],
      lastname: ['',Validators.required],
      email: ['',Validators.email],
      password: ['',Validators.required],
      repeatpassword: ['', Validators.required]
    });
    
  }

  ngOnInit(): void {
  }

  register(){
    const val = this.form.value;
    this.emptyField = false;
    this.passwordmissmatch = false;
    this.invalidemail = false;
    this.wrongUsernameOrPass = false;
    var re = new RegExp("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    if (!val.password || !val.firstname || !val.lastname || !val.repeatpassword || !val.email){
      this.emptyField = true;
    }
    else if(!(val.password === val.repeatpassword)){
      this.passwordmissmatch = true;
    }
    else if(!re.test(val.email)){
     this.invalidemail = true; 
    }
    else{
      this.registrationService.register(val.password, val.firstname, val.lastname, val.email)
      .subscribe(
        (loggedIn) => {
            //console.log("usao post reg");
            this.router.navigateByUrl('/postregister');
          },
        (err:Error) => {
          if(err.toString()==='Email already in use'){
            this.wrongUsernameOrPass = true;
            console.log(err);
          }
          else{
            throwError(err);
          }
        }
      );
    }
  }
}
