import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Feedback } from '../models/feedback.model';
import { AppConstant } from '../app.constant';
@Injectable({
  providedIn: 'root'
})
export class FeedbackService {

  private apiUrl = AppConstant.apiUrl;

  constructor(private client:HttpClient) { }
  sendFeedback(feedback:Feedback, userId : number, investmentId : number):Observable<any>{
    
    return this.client.post(`${this.apiUrl}/feedback/${userId}/${investmentId}`,feedback)
  }
  getAllFeedbacksByUserId(userId:number):Observable<any>{
    return this.client.get(`${this.apiUrl}/feedback/user/${userId}`)
  }
  deleteFeedback(feedbackId:number):Observable<any>{
    return this.client.delete(`${this.apiUrl}/feedback/${feedbackId}`)
  }
  getFeedbacks():Observable<Feedback[]>{
    return this.client.get<Feedback[]>(`${this.apiUrl}/feedback`)
  }
 
}
 
