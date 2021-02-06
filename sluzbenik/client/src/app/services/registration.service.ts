import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private readonly port = "http://localhost:8082"
  private readonly path = "/auth/register";
	private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  constructor(private http: HttpClient) { }

  register(password: string, firstName: string, lastName: string,
    emailAddress: string): Observable<boolean> {
    var body;
    var xml2js = require('xml2js');
 
    var obj = {"kor:korisnik":{$: {
      "xmlns:kor": "http://localhost:8080/korisnici"
      }, 
      "kor:email_adresa": emailAddress, "kor:sifra": password, "kor:ime": firstName, "kor:prezime":lastName}};
 
    var builder = new xml2js.Builder();
    body = builder.buildObject(obj); 

    //console.log(body);
    return this.http.post(this.port + this.path, body,
      {headers: this.headers, responseType: 'text'}).pipe(
      map((res: any) => {
        console.log(res);
        return true;
      }),
      catchError(error => {
        if (error.status === 409) {
          return throwError('Email already in use');
        }
        else {
          return throwError ('Server error');
        }
      }));
  }
}
