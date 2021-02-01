import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private readonly port = "http://localhost:8080"
  private readonly path = "/auth/register";
	private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  constructor(private http: HttpClient) { }

  register(password: string, firstName: string, lastName: string,
    emailAddress: string)/*: Observable<boolean>*/ {
    var body;
    var xml2js = require('xml2js');
 
    var obj = {user:{name: firstName, Surname: lastName, email: emailAddress, pw: password}};
 
    var builder = new xml2js.Builder();
    body = builder.buildObject(obj); 

    console.log(body);
      //return true;
    /*return this.http.post(this.port + this.path, body,
      {headers: this.headers, responseType: 'text'}).pipe(
      map((res: any) => {
        return true;
      }),
      catchError(error => {
        if (error.status === 400) {
          return throwError('Username or email already in use');
        }
        else {
          return throwError ('Server error');
        }
      }));*/
  }
}
