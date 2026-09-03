import { Component, OnInit } from '@angular/core';
import { Feedback } from 'src/app/models/feedback.model';
import { Investment } from 'src/app/models/investment.model';
import { User } from 'src/app/models/user.model';
import { FeedbackService } from 'src/app/services/feedback.service';

@Component({
  selector: 'app-admin-view-feedback',
  templateUrl: './admin-view-feedback.component.html',
  styleUrls: ['./admin-view-feedback.component.css']
})
export class AdminViewFeedbackComponent implements OnInit {
  feedbacks: Feedback[] = [];
  showUserModal = false;
  page: number = 1;
  showInvestmentModal = false;
  selectedUser: any = {}
  selectedInvestment: any = {}
  constructor(private service: FeedbackService) { }

  ngOnInit(): void {
    this.getFeedbacks();
  }
  getFeedbacks() {
    this.service.getFeedbacks().subscribe((result) => {
      console.log(result[0].user);
      
      this.feedbacks = result;
    })
  }
  showUserDetails(user: User) {
    this.selectedUser = {
      userId: user.userId,
      username: user.username,
      email: user.email,
      mobileNumber: user.mobileNumber
    }
    this.showUserModal = true;

  }
  closeUserModal() {
    this.showUserModal = false;
  }
  showInvestmentDetails(investment: Investment) {
    this.selectedInvestment = investment;
    this.showInvestmentModal = true;
  }
  closeInvestmentModal() {
    this.showInvestmentModal = false;
  }


}
