import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ReportService {
  constructor(private http: HttpClient) {}

  get(): Observable<HttpResponse<any>> {
    return this.http.get<any>('http://localhost:8080/izvestaj', {
      observe: 'response',
    });
  }

  getPdf(id: string) {
    return this.http.get<any>(`http://localhost:8080/izvestaj/pdf/${id}`, {
      observe: 'response',
      responseType: 'arraybuffer' as 'json',
    });
  }
}
