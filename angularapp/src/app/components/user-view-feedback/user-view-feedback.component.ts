import { Component, OnInit } from '@angular/core';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';

@Component({
  selector: 'app-user-view-feedback',
  templateUrl: './user-view-feedback.component.html',
  styleUrls: ['./user-view-feedback.component.css']
})
export class UserViewFeedbackComponent implements OnInit {
  userFeedbacks:Feedback[]=[];
  userId : number = null;
  showConfirmation:boolean=false;
  selectedFeedbackId: number | null = null;
  page: number = 1;
  constructor(private service:FeedbackService) { }

  ngOnInit(): void {
    this.userId = +localStorage.getItem('userId');

    this.getAllFeedbacksByUserId(this.userId);
    
  }
  getAllFeedbacksByUserId(userId:number){
    this.service.getAllFeedbacksByUserId(userId).subscribe((result)=>{
     this.userFeedbacks=result
    });
  }
 
  
  deleteFeedback(feedbackId: number) {
    this.showConfirmation = true;
    this.selectedFeedbackId = feedbackId;
  }

  confirmDelete() {
    if (this.selectedFeedbackId !== null) {
      this.service.deleteFeedback(this.selectedFeedbackId).subscribe((result) => {
        this.showConfirmation = false;
        this.selectedFeedbackId = null;
        this.getAllFeedbacksByUserId(this.userId);
      },(error)=>{
        this.getAllFeedbacksByUserId(this.userId);
        alert(error.error);
      });
      this.getAllFeedbacksByUserId(this.userId);
    }
  }

  cancelDelete() {
    this.showConfirmation = false;
    this.selectedFeedbackId = null;
  }
}


