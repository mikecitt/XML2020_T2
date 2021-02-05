import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface LoginResponse {
  accessToken: string;
  role: string;
}

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  constructor(private http: HttpClient) {}

  login(
    email: string,
    password: string
  ): Observable<HttpResponse<LoginResponse>> {
    const body = `<?xml version="1.0" encoding="UTF-8"?>
                  <user_login_dto xmlns="http://localhost:8080/korisnici">
                    <email_adresa>${email}</email_adresa>
                    <sifra>${password}</sifra>
                  </user_login_dto>`;

    return this.http.post<LoginResponse>(
      'http://localhost:8080/auth/login',
      body,
      {
        observe: 'response',
      }
    );
  }

  register(
    email: string,
    password: string,
    firstName: string,
    lastName: string
  ) {
    const body = `<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                  <kor:korisnik xmlns:kor="http://localhost:8080/korisnici">
                      <kor:email_adresa>${email}</kor:email_adresa>
                      <kor:sifra>${password}</kor:sifra>
                      <kor:ime>${firstName}</kor:ime>
                      <kor:prezime>${lastName}</kor:prezime>
                  </kor:korisnik>`;

    return this.http.post('http://localhost:8080/auth/register', body, {
      observe: 'response',
    });
  }

  setLoginCredentials(token: string, role: string) {
    localStorage.setItem('token', token);
    localStorage.setItem('role', role);
  }

  logout() {
    localStorage.clear();
  }

  isLogged() {
    return localStorage.getItem('token') !== null;
  }

  getRole() {
    return localStorage.getItem('role');
  }
}
