import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { UserToken } from '../model/user-token';
import { JwtHelperService } from '@auth0/angular-jwt';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private headers = new HttpHeaders({ 'Content-Type': 'application/xml' });
  private refreshingToken: boolean = false;

  constructor(private http: HttpClient) {
  }

  private takeUserFrom(): UserToken {
    let currentUser: UserToken = {authorities: [], token: "", expireIn: 0};
    
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

  login(email: string, password: string)/*: Observable<any> */{
    
    
    var body;
    var xml2js = require('xml2js');
 
    var obj = {logindata:{email: email, password: password}};
 
    var builder = new xml2js.Builder();
    body = builder.buildObject(obj); 

    console.log(body);
    /*return this.http.post('api/auth/login', JSON.stringify({ username, password }),
      { headers: this.headers, responseType: 'text' }).pipe(
        map((res: any) => {
          const token = res && res.accessToken;

          if (token) {
            const jwt: JwtHelperService = new JwtHelperService();
            const info = jwt.decodeToken(token);
            const userToken: UserToken = {
              id: parseInt(info.user_id, 10),
              expireIn: info.exp * 1000,
              authorities: info.roles.map((role: { authority: any; }) => role.authority),
              token
            };
            localStorage.setItem('user', JSON.stringify(userToken));
            return true;
          } else {
            return false;
          }
        }),
        catchError(error => {
          if (error.status === 401) {
            return throwError('Ilegal login');
          } else {
            return throwError('Server error');
          }
        }));*/
  }

  refreshToken(): Observable<boolean> {
    return this.http.post('api/auth/refresh', {}).pipe(
      map((res: any) => {
        const token = res && res.accessToken;

        if (token) {
          const jwt: JwtHelperService = new JwtHelperService();
          const info = jwt.decodeToken(token);
          const userToken: UserToken = {
            id: parseInt(info.user_id, 10),
            expireIn: info.exp * 1000,
            authorities: info.roles.map((role: { authority: any; }) => role.authority),
            token
          };
          localStorage.setItem('user', JSON.stringify(userToken));
          return true;
        } else {
          return false;
        }
      }),
      catchError(error => {
        return throwError('Token could not be refreshed.');
      }));
  }

  isRefreshing(): boolean {
    return this.refreshingToken;
  }

  setRefreshing(value: boolean): void {
    this.refreshingToken = value;
  }

  /*getUserId(): number {
    const currentUser: UserToken = this.takeUserFrom();\
    return currentUser ? currentUser.id : null;
  }*/

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
