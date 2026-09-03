import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Feedback } from 'src/app/models/feedback.model';
import { InvestmentInquiry } from 'src/app/models/investment-inquiry.model';
import { Investment } from 'src/app/models/investment.model';
import { FeedbackService } from 'src/app/services/feedback.service';
import { InvestmentInquiryService } from 'src/app/services/investment-inquiry.service';
import { InvestmentService } from 'src/app/services/investment.service';

@Component({
  selector: 'app-admin-console',
  templateUrl: './admin-console.component.html',
  styleUrls: ['./admin-console.component.css']
})
export class AdminConsoleComponent implements OnInit {

  feedbacks : Feedback[] = [];

  inquiries : InvestmentInquiry[] = [];

  investments : Investment[] = [];

  unresolvedInquiries : InvestmentInquiry[] = [];

  highPriorityInquiries : InvestmentInquiry[] = [];

  constructor(private feedbackService : FeedbackService, private investmentService : InvestmentService, private investmentInquiryService : InvestmentInquiryService, private router : Router ) { }

  loadCount() {
    this.feedbackService.getFeedbacks().subscribe((result)=>{
      this.feedbacks = result;
    });
    this.investmentService.getAllInvestments().subscribe((result)=>{
      this.investments = result;
    })
    this.investmentInquiryService.getAllInquiries().subscribe((result)=>{
      this.inquiries = result;
    });
    this.investmentInquiryService.getUnresolvedInquiries().subscribe((result)=>{
      this.unresolvedInquiries = result;
    });
    this.investmentInquiryService.getHighPriorityInquiries().subscribe((result)=>{
      this.highPriorityInquiries = result;
    })

  }

  ngOnInit(): void {
    this.loadCount();

  }

  navigateWithPriorityFilter() {
    this.router.navigate(['/admin/view-inquiries'], {
      queryParams: {priority: 'High'}
    })
  }

  navigateWithStatusFilter() {
    this.router.navigate(['/admin/view-inquiries'],{
      queryParams: { status: 'Pending' }
    });
  }

  handleKeyPress(event : KeyboardEvent) {
    if(event.key === 'Enter' || event.key === ' ') {
      event.preventDefault();
      this.navigateWithStatusFilter();
    }
  }

}
