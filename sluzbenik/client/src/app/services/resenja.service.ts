import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Resenje } from '../model/resenje';

@Injectable({
  providedIn: 'root'
})
export class ResenjaService {
	private headers = new HttpHeaders({'Content-Type': 'text/xml', 'Accept': 'text/xml'});

  constructor(private http: HttpClient) { }

  vratiResenjePDF(resenjeId: string): Observable<any>{
    const httpOptions = {
      'responseType'  : 'arraybuffer' as 'json'
       //'responseType'  : 'blob' as 'json'        //This also worked
    };

    return this.http.get<any>('http://localhost:8082/resenja/pdf/'+resenjeId
    , httpOptions);
  }

  /*vratiResenjeXHTML(zahtevId: string): Observable<any>{
    
    return this.http.get('http://localhost:8082/resenja/byZahtev/html?zahtevId='+zahtevId
    , {headers: this.headers, responseType: 'text'}).pipe(
      map((res: any) => {
        console.log(res);
        return res;
      }),
      catchError(error => {
        if (error.status === 400) {
          return throwError('Error');
        }
        else {
          return throwError ('Server error');
        }
      }));;;
  }*/

  vratiSvaResenja(): Observable<Resenje[]> {
    return this.http.get("http://localhost:8082/resenja",
      {headers: this.headers, responseType: 'text'}).pipe(
      map((res: any) => {
        var resenja: Resenje[] = [];

        var parseString = require('xml2js').parseString;
        var xml = res;
        parseString(xml, function (err: any, result: any) {
          //console.dir(result);
          result.resenja.resenje.forEach((element: any) => {
            let pomString = element.$.about.split("/");
            //let pomId = element.$.about.substring(pomIndex);

            let res : Resenje = {
              id: pomString[4],
              datum: element.zaglavlje[0].datum_resenja[0]._
            };
            console.dir(res);
            resenja.push(res);
          });
        });
        return resenja;
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
}
