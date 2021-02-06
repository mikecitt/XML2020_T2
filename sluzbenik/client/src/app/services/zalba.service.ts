import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Zahtev } from '../model/zahtev';
import { Zalba } from '../model/zalba';

@Injectable({
  providedIn: 'root'
})
export class ZalbaService {

  private headers = new HttpHeaders({'Content-Type': 'text/xml', 'Accept': 'text/xml'});

  constructor(private http: HttpClient) { }

  vratiZalbuCutanjePDF(resenjeId: string): Observable<any>{
    const httpOptions = {
      'responseType'  : 'arraybuffer' as 'json'
       //'responseType'  : 'blob' as 'json'        //This also worked
    };

    return this.http.get<any>('http://localhost:8082/zalbe/cutanje/pdf/'+resenjeId
    , httpOptions);
  }

  vratiZalbuOdlukaPDF(resenjeId: string): Observable<any>{
    const httpOptions = {
      'responseType'  : 'arraybuffer' as 'json'
       //'responseType'  : 'blob' as 'json'        //This also worked
    };

    return this.http.get<any>('http://localhost:8082/zalbe/odluka/pdf/'+resenjeId
    , httpOptions);
  }

  obrazloziZalbuOdluka(obrazlozenje:string, zalbaId:string): Observable<any>{
    var body;
    var xml2js = require('xml2js');
 
    var obj = {"odgovor":{"tekst":obrazlozenje}};
 
    var builder = new xml2js.Builder();
    body = builder.buildObject(obj);
    console.log('odluka');
    console.log(body);
    return this.http.post("http://localhost:8082/zalbe/odluka/status/"+zalbaId,
      {headers: this.headers, responseType: 'text'})
  }

  obrazloziZalbuCutanje(obrazlozenje:string, zalbaId:string): Observable<any>{
    var body;
    var xml2js = require('xml2js');
 
    var obj = {"odgovor":{"tekst":obrazlozenje}};
 
    var builder = new xml2js.Builder();
    body = builder.buildObject(obj);
    console.log('cutanje');
    console.log(body);
    return this.http.post("http://localhost:8082/zalbe/cutanje/status/"+zalbaId,
      {headers: this.headers, responseType: 'text'})
  }

  vratiSveZalbeCutanje(): Observable<Zalba[]> {
    return this.http.get("http://localhost:8082/zalbe/cutanje",
      {headers: this.headers, responseType: 'text'}).pipe(
      map((res: any) => {
        var zalbe: Zalba[] = [];

        var parseString = require('xml2js').parseString;
        var xml = res;
        parseString(xml, function (err: any, result: any) {
          console.dir(result);
          result.zalbecutanje.zalbacutanje.forEach((element: any) => {
            let pomString = element.$.about.split("/");
            //let pomId = element.$.about.substring(pomIndex);

            let res : Zalba = {
              id: pomString[4],
              datum: element.detalji_predaje[0].datum[0]._
            };
            //console.dir(res);
            zalbe.push(res);
          });
        });
        return zalbe;
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

  vratiSveZalbeOdluka(): Observable<Zalba[]> {
    return this.http.get("http://localhost:8082/zalbe/odluka",
      {headers: this.headers, responseType: 'text'}).pipe(
      map((res: any) => {
        var zalbe: Zalba[] = [];

        var parseString = require('xml2js').parseString;
        var xml = res;
        parseString(xml, function (err: any, result: any) {
          console.dir(result);
          result.zalbenaodluku.zalbanaodluku.forEach((element: any) => {
            let pomString = element.$.about.split("/");
            //let pomId = element.$.about.substring(pomIndex);

            let res : Zalba = {
              id: pomString[4],
              datum: element.detalji_predaje[0].datum[0]._
            };
            //console.dir(res);
            zalbe.push(res);
          });
        });
        return zalbe;
      }),
      catchError(error => {
        if (error.status === 400) {
          return throwError('Error');
        }
        else {
          return throwError ('Server error123');
        }
      }));
  }
}
