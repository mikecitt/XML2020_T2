import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { UserToken } from '../model/user-token';
import { JwtHelperService } from '@auth0/angular-jwt';
import { catchError, map } from 'rxjs/operators';
import { convertToObject } from 'typescript';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private headers = new HttpHeaders({ 'Content-Type': 'text/xml', 'Accept': 'text/xml'});
  private refreshingToken: boolean = false;

  constructor(private http: HttpClient) {
  }

  private takeUserFrom(): UserToken {
    let currentUser: UserToken = {id: "", authorities: [], token: "", expireIn: 0};
    
    if (localStorage.getItem('user')) {
      currentUser = JSON.parse(localStorage.getItem('user') || '{}');
    }

    return currentUser;
  }

  validateToken(): void {
    const currentUser: UserToken = this.takeUserFrom();
    if (this.isLoggedIn()) {
      if (new Date().getTime() >= currentUser.expireIn) {
        this.logout();
      }
    }
  }

  login(email: string, password: string): Observable<any> {
    
    var body;
    var xml2js = require('xml2js');
 
    var obj = {"kor:user_login_dto":{$: {
      "xmlns:kor": "http://localhost:8080/korisnici"
      }, 
      "kor:email_adresa": email, "kor:sifra": password}};
 
    var builder = new xml2js.Builder();
    body = builder.buildObject(obj); 

    return this.http.post('http://localhost:8082/auth/login', body,
      { headers: this.headers, responseType: 'text' }).pipe(
        map((res: any) => {
          var parseString = require('xml2js').parseString;
          var xml = res;
          parseString(xml, function (err: any, result: any) {
              //console.dir(result);
              
              //console.log("usao");
              const token = result.token.accessToken[0];
              const jwt: JwtHelperService = new JwtHelperService();
              const info = jwt.decodeToken(token);
              //console.dir(info);

              const userToken: UserToken = {
                id: info.sub,
                expireIn: info.exp * 1000,
                authorities: result.token.role,
                token
              };
              localStorage.setItem('user', JSON.stringify(userToken));
              //console.dir(userToken);
              return true;
          });

          
        }),
        catchError(error => {
          if (error.status === 401) {
            return throwError('Ilegal login');
          } else {
            return throwError('Server error');
          }
        }));
  }

  getUserId(): string {
    const currentUser: UserToken = this.takeUserFrom();
    return currentUser ? currentUser.id : "";
  }

  getToken(): string {
    const currentUser: UserToken = this.takeUserFrom();
    return currentUser ? currentUser.token : "";
  }

  getRole(): string {
    const currentUser: UserToken = this.takeUserFrom();
    return currentUser ? currentUser.authorities[0] : "";
  }

  logout(): void {
    localStorage.removeItem('user');
  }

  isLoggedIn(): boolean {
    if (localStorage.getItem('user')) {
      return true;
    } else {
      return false;
    }
  }

  getCurrentUser(): UserToken {
    return this.takeUserFrom();
  }
}
