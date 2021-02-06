import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ComplaintDecisionService {
  constructor(private http: HttpClient) {}

  get(): Observable<HttpResponse<any>> {
    return this.http.get<any>('http://localhost:8080/zalbe/odluka', {
      observe: 'response',
    });
  }
}
