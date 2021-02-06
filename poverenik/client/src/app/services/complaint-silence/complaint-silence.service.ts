import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ComplaintSilenceService {
  constructor(private http: HttpClient) {}

  get(): Observable<HttpResponse<any>> {
    return this.http.get<any>('http://localhost:8080/zalbe/cutanje', {
      observe: 'response',
    });
  }

  getPdf(id: string) {
    return this.http.get<any>(`http://localhost:8080/zalbe/cutanje/pdf/${id}`, {
      observe: 'response',
      responseType: 'arraybuffer' as 'json',
    });
  }

  getHtml(id: string) {
    return this.http.get<any>(
      `http://localhost:8080/zalbe/cutanje/html/${id}`,
      {
        observe: 'response',
        responseType: 'text' as 'json',
      }
    );
  }

  insert(complaint: any) {
    const body = `<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
            <zal:zalbacutanje xmlns:zal="http://localhost:8080/zalba">
            <zal:naziv_organa>${complaint.nazivorgana}</zal:naziv_organa>
            <zal:razlog_zalbe>${complaint.razlogzalbe}</zal:razlog_zalbe>
            <zal:datum_zahteva>${complaint.datumzahteva}</zal:datum_zahteva>
            <zal:podaci_zahteva>${complaint.podacizahteva}</zal:podaci_zahteva>
            <zal:detalji_predaje>
                <zal:mesto>${complaint.mesto}</zal:mesto>
                <zal:datum>${complaint.datum}</zal:datum>
            </zal:detalji_predaje>
            <zal:informacije_o_podnosiocu_zalbe>
                <zal:ime>${complaint.ime}</zal:ime>
                <zal:prezime>${complaint.prezime}</zal:prezime>
                <zal:adresa>${complaint.adresa}</zal:adresa>
                <zal:drugi_kontakt>${complaint.drugikontakt}</zal:drugi_kontakt>
            </zal:informacije_o_podnosiocu_zalbe>
            </zal:zalbacutanje>`;
    return this.http.post<any>(`http://localhost:8080/zalbe/cutanje`, body, {
      observe: 'response',
      responseType: 'text' as 'json',
    });
  }

  getDecisionPdf(id: string) {
    return this.http.get<any>(
      `http://localhost:8080/resenja/zalba/pdf?id=${id}`,
      {
        observe: 'response',
        responseType: 'arraybuffer' as 'json',
      }
    );
  }

  getDecisionHtml(id: string) {
    return this.http.get<any>(
      `http://localhost:8080/resenja/zalba/html?id=${id}`,
      {
        observe: 'response',
        responseType: 'text' as 'json',
      }
    );
  }
}
