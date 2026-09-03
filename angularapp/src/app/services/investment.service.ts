import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Investment } from '../models/investment.model';
import { AppConstant } from '../app.constant';
@Injectable({
  providedIn: 'root'
})
export class InvestmentService {

  private apiUrl = AppConstant.apiUrl;

  getAllInvestments():Observable<any>{
    return this.client.get(`${this.apiUrl}/investments`);
  }
 
  getInvestmentById(investmentId:number):Observable<any>{
    return this.client.get(`${this.apiUrl}/investments/${investmentId}`);
  }
 
  addInvestment(investment:Investment):Observable<any>{
    return this.client.post(`${this.apiUrl}/investments`,investment);
  }
 
  updateInvestment(investmentId:number,investment:Investment):Observable<any>{
    return this.client.put(`${this.apiUrl}/investments/${investmentId}`,investment);
  }
 
  deleteInvestment(investmentId:number):Observable<any>{
    return this.client.delete(`${this.apiUrl}/investments/${investmentId}`);
  }
 
 
  constructor(private client:HttpClient) { }
}
