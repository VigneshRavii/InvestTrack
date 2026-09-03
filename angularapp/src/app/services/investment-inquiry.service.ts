import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { InvestmentInquiry } from '../models/investment-inquiry.model';
import { Observable } from 'rxjs';
import { AppConstant } from '../app.constant';
 
@Injectable({
  providedIn: 'root'
})
export class InvestmentInquiryService {
 
  constructor(private http:HttpClient) { }
  private apiUrl = AppConstant.apiUrl;

  addInquiry(inquiry:InvestmentInquiry, investmentId):Observable<any>{
    const userId = localStorage.getItem('userId');
    return this.http.post(`${this.apiUrl}/inquiries/${investmentId}/${userId}`,inquiry);

  }
  getAllInquiries():Observable<any>{
    return this.http.get(`${this.apiUrl}/inquiries`);
  }
  deleteInquiry(inquiryId:number):Observable<any>{
    console.log(inquiryId);
    
    return this.http.delete(`${this.apiUrl}/inquiries/${inquiryId}`);
  }
  getInquiriesByUserId(userId:number):Observable<any>{
    return this.http.get(`${this.apiUrl}/inquiries/user/${userId}`);
  }
  updateInquiry(inquiryId:number,inquiry:InvestmentInquiry):Observable<any>{
    return this.http.put(`${this.apiUrl}/inquiries/${inquiryId}`,inquiry)
  }

  getUnresolvedInquiries() : Observable<any> {
    return this.http.get(`${this.apiUrl}/inquiries/statusUnresolved`);
  }

  getHighPriorityInquiries() : Observable<any> {
    return this.http.get(`${this.apiUrl}/inquiries/highPriority`)
  }
}
 
