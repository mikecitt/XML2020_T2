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

  getPdf(id: string) {
    return this.http.get<any>(`http://localhost:8080/zalbe/odluka/pdf/${id}`, {
      observe: 'response',
      responseType: 'arraybuffer' as 'json',
    });
  }

  getHtml(id: string) {
    return this.http.get<any>(`http://localhost:8080/zalbe/odluka/html/${id}`, {
      observe: 'response',
      responseType: 'text' as 'json',
    });
  }

  insert(complaint: any) {
    const body = `<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                  <zlbod:zalbanaodluku xmlns:zlbod="http://localhost:8080/zalbanaodluku">
                      <zlbod:podnosilac_zalbe>
                          <zlbod:ime>${complaint.podnosilacime}</zlbod:ime>
                          <zlbod:prezime>${complaint.podnosilacprezime}</zlbod:prezime>
                          <zlbod:naziv>${complaint.podnosilacnaziv}</zlbod:naziv>
                          <zlbod:adresa>${complaint.podnosilacadresa}</zlbod:adresa>
                          <zlbod:sediste>${complaint.podnosilacsediste}</zlbod:sediste>
                      </zlbod:podnosilac_zalbe>
                      <zlbod:naziv_organa>${complaint.nazivorgana}</zlbod:naziv_organa>
                      <zlbod:resenje>
                          <zlbod:broj_resenja>${complaint.brojresenja}</zlbod:broj_resenja>
                          <zlbod:datum_resenja>${complaint.datumresenja}</zlbod:datum_resenja>
                      </zlbod:resenje>
                      <zlbod:datum_podnosenja_zahteva>${complaint.datumpodnosenjazahteva}</zlbod:datum_podnosenja_zahteva>
                      <zlbod:opis_zalbe>${complaint.opiszalbe}</zlbod:opis_zalbe>
                      <zlbod:detalji_predaje>
                          <zlbod:mesto>${complaint.predajamesto}</zlbod:mesto>
                          <zlbod:datum>${complaint.predajadatum}</zlbod:datum>
                      </zlbod:detalji_predaje>
                      <zlbod:informacije_o_podnosiocu_zalbe>
                          <zlbod:ime>${complaint.informacijeime}</zlbod:ime>
                          <zlbod:prezime>${complaint.informacijeprezime}</zlbod:prezime>
                          <zlbod:adresa>${complaint.informacijeadresa}</zlbod:adresa>
                          <zlbod:drugi_kontakt>${complaint.informacijedrugikontakt}</zlbod:drugi_kontakt>
                      </zlbod:informacije_o_podnosiocu_zalbe>
                  </zlbod:zalbanaodluku>`;
    return this.http.post<any>(`http://localhost:8080/zalbe/odluka`, body, {
      observe: 'response',
      responseType: 'text' as 'json',
    });
  }
}
