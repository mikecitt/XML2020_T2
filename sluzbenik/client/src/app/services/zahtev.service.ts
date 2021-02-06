import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { Obavestenje } from '../model/obavestenje';
import { Zahtev } from '../model/zahtev';

@Injectable({
  providedIn: 'root'
})
export class ZahtevService {

  private readonly port = "http://localhost:8082/zahtev/all";
  private readonly dodajZahtevUrl = "http://localhost:8082/zahtev";
  private readonly dodajObavestenjeUrl = "http://localhost:8082/obavestenje?zahtevId=";
	private headers = new HttpHeaders({'Content-Type': 'text/xml', 'Accept': 'text/xml'});

  constructor(private http: HttpClient) { }

  dodajObavestenje(obavestenje: Obavestenje, zahtevId: string)/*: Observable<any>*/{
    var body;
    var dat = new Date();
    var m = dat.getMonth()+1;
    var tosendDate = dat.getFullYear()+"-"+m+"-"+dat.getDate();

    var xml2js = require('xml2js');
 
    var obj = {"obv:obavestenje":{$: {
      "xmlns:obv": "http://localhost:8080/obavestenje"
      }, 
      "obv:zaglavlje": { "obv:organ" : {"obv:naziv_organa" : obavestenje.nazivOrgana, "obv:sediste_organa": obavestenje.sedisteOrgana},
        "obv:broj_predmeta": obavestenje.brojPredmeta,
        "obv:datum": tosendDate,
        "obv:podnosioc_zahteva": {"obv:ime":obavestenje.ime, "obv:prezime":obavestenje.prezime,
        "obv:naziv":"unknown", "obv:adresa": obavestenje.adresa}}, 
      "obv:telo": {"obv:trazena_informacija": {"obv:datum_informacija": obavestenje.datumPodnosenja,
        "obv:opis_informacije": obavestenje.opisInformacije},
        "obv:uvid":{
          "obv:vreme": {"obv:datum": obavestenje.datumUvida, "obv:cas":obavestenje.vremeUvida,
            "obv:trajanje":{
              "obv:pocetak":obavestenje.od, "obv:kraj":obavestenje.do
            }},
          "obv:adresa": {"obv:mesto":obavestenje.mestoUvida, "obv:ulica":obavestenje.ulicaUvida,
            "obv:broj":obavestenje.brojUlice, "obv:br_kancelarije":obavestenje.brojKancelarije}
        },
        "obv:trosak":obavestenje.trosak
        }
      }
    };
 
    var builder = new xml2js.Builder();
    body = builder.buildObject(obj);
    console.log(body);

    return this.http.post(this.dodajObavestenjeUrl+zahtevId, body,
      {headers: this.headers, responseType: 'text'}).pipe(
      map((res: any) => {
        //console.log(res);
        return true;
      }),
      catchError(error => {
        if (error.status === 400) {
          return throwError('Error');
        }
        else {
          return throwError ('Server error');
        }
      }));
  }

  dodajZahtev(zahtev: Zahtev): Observable<boolean>{
    var body;

    var xml2js = require('xml2js');
 
    var obj = {"zah:zahtev":{$: {
      "xmlns:zah": "http://localhost:8080/zahtevcir"
      }, 
      "zah:organ": { "zah:naziv" : zahtev.nazivOrgana, "zah:sediste": zahtev.sediste}, 
      "zah:listaZahteva": {"zah:svrha_zahteva": zahtev.svrha, "zah:nacin_dostave": zahtev.nacinDostave,
        "zah:opis_informacije": zahtev.opisInformacije}, 
      "zah:detalji_predaje": {"zah:mesto":zahtev.mestoPredaje, "zah:datum":zahtev.datum}, 
      "zah:informacije_o_traziocu": {"zah:ime":zahtev.ime, "zah:prezime":zahtev.prezime,
      "zah:adresa": zahtev.adresa, "zah:drugi_kontakt":zahtev.kontakt}
      }
    };
 
    var builder = new xml2js.Builder();
    body = builder.buildObject(obj); 

    //console.log(body);
    return this.http.post(this.dodajZahtevUrl, body,
      {headers: this.headers, responseType: 'text'}).pipe(
      map((res: any) => {
        //console.log(res);
        return true;
      }),
      catchError(error => {
        if (error.status === 400) {
          return throwError('Error');
        }
        else {
          return throwError ('Server error');
        }
      }));
  }

  vratiSveZahteve(): Observable<Zahtev[]> {
    return this.http.get(this.port,
      {headers: this.headers, responseType: 'text'}).pipe(
      map((res: any) => {
        var zahtevi: Zahtev[] = [];

        var parseString = require('xml2js').parseString;
        var xml = res;
        parseString(xml, function (err: any, result: any) {
          //console.dir(result.zahtevi.zahtev[0]);
          result.zahtevi.zahtev.forEach((element: any) => {
            let pomString = element.$.about.split("/");
            //let pomId = element.$.about.substring(pomIndex);

            let zah : Zahtev = {
              nazivOrgana: element.organ[0].naziv[0],
              sediste: element.organ[0].sediste[0]._,
              svrha: element.listaZahteva[0].svrha_zahteva[0],
              nacinDostave: element.listaZahteva[0].nacin_dostave[0],
              opisInformacije: element.listaZahteva[0].opis_informacije[0],
              mestoPredaje: element.detalji_predaje[0].mesto[0]._,
              datum: element.detalji_predaje[0].datum[0]._,
              ime: element.informacije_o_traziocu[0].ime[0],
              prezime: element.informacije_o_traziocu[0].prezime[0],
              adresa: element.informacije_o_traziocu[0].adresa[0],
              kontakt: element.informacije_o_traziocu[0].drugi_kontakt[0],
              id: pomString[4],
              status: element.status[0]._
            };

            zahtevi.push(zah);
          });
        });
        return zahtevi;
      }),
      catchError(error => {
        if (error.status === 400) {
          return throwError('Username or email already in use');
        }
        else {
          return throwError ('Server error');
        }
      }));
  }

  vratiSveMojeZahteve(): Observable<Zahtev[]> {
    return this.http.get("http://localhost:8082/zahtev/my",
      {headers: this.headers, responseType: 'text'}).pipe(
      map((res: any) => {
        var zahtevi: Zahtev[] = [];

        var parseString = require('xml2js').parseString;
        var xml = res;
        parseString(xml, function (err: any, result: any) {
          //console.dir(result.zahtevi.zahtev[0]);
          result.zahtevi.zahtev.forEach((element: any) => {
            let pomString = element.$.about.split("/");
            //let pomId = element.$.about.substring(pomIndex);

            let zah : Zahtev = {
              nazivOrgana: element.organ[0].naziv[0],
              sediste: element.organ[0].sediste[0]._,
              svrha: element.listaZahteva[0].svrha_zahteva[0],
              nacinDostave: element.listaZahteva[0].nacin_dostave[0],
              opisInformacije: element.listaZahteva[0].opis_informacije[0],
              mestoPredaje: element.detalji_predaje[0].mesto[0]._,
              datum: element.detalji_predaje[0].datum[0]._,
              ime: element.informacije_o_traziocu[0].ime[0],
              prezime: element.informacije_o_traziocu[0].prezime[0],
              adresa: element.informacije_o_traziocu[0].adresa[0],
              kontakt: element.informacije_o_traziocu[0].drugi_kontakt[0],
              id: pomString[4],
              status: element.status[0]._
            };

            zahtevi.push(zah);
          });
        });
        return zahtevi;
      }),
      catchError(error => {
        if (error.status === 400) {
          return throwError('Username or email already in use');
        }
        else {
          return throwError ('Server error');
        }
      }));
  }

  vratiZahtevPDF(zahtevId: string): Observable<any>{
    const httpOptions = {
      'responseType'  : 'arraybuffer' as 'json'
       //'responseType'  : 'blob' as 'json'        //This also worked
    };

    return this.http.get<any>('http://localhost:8082/zahtev/pdf?zahtevId='+zahtevId
    , httpOptions);
  }

  odbijZahtev(zahtevId: string): Observable<any>{
    return this.http.put('http://localhost:8082/zahtev/decline?zahtevId='+zahtevId
    , {headers: this.headers}).pipe(
      map((res: any) => {
        //console.log(res);
        return true;
      }),
      catchError(error => {
        if (error.status === 400) {
          return throwError('Error');
        }
        else {
          return throwError ('Server error');
        }
      }));;
  }

}
