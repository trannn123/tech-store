import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';;

@Injectable({
  providedIn: 'root',
})
export class Invoice {
  private apiUrl = 'http://localhost:8081/invoices';

  constructor(private http:HttpClient){}

  getAll(){
    return this.http.get(this.apiUrl);
  }
}
