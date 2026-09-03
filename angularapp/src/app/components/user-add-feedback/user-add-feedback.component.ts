import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Feedback } from 'src/app/models/feedback.model';
import { Investment } from 'src/app/models/investment.model';
import { FeedbackService } from 'src/app/services/feedback.service';
import { InvestmentService } from 'src/app/services/investment.service';

@Component({
  selector: 'app-user-add-feedback',
  templateUrl: './user-add-feedback.component.html',
  styleUrls: ['./user-add-feedback.component.css']
})
export class UserAddFeedbackComponent implements OnInit {

  userId : number = null;

  investments:Investment[]=[];

  feedback:Feedback={
    feedbackText:'',
    date:'',
    user:null,
    investment:null,
    category:''
};
successMessage:boolean=false;
  constructor(private router:Router,private service:FeedbackService,private investmentService:InvestmentService) { }

 
  addFeedback(feedbackForm:NgForm)
  {
    if(feedbackForm.valid){
    this.service.sendFeedback(this.feedback,this.userId, this.feedback.investment.investmentId).subscribe((result)=>{
      this.feedback=result;
      console.log(this.feedback);
      this.successMessage=true;
      feedbackForm.resetForm();
    },
    (error)=>{
      console.log(error);
    });
   
  }
}

loadInvestments() {
  this.investmentService.getAllInvestments().subscribe((result)=>{
    this.investments = result;
  })
}

  ngOnInit(): void {
    this.loadInvestments();
    this.userId = +localStorage.getItem('userId');
  }

  cancel(feedbackForm:NgForm)
  {
    this.successMessage=false;
    feedbackForm.resetForm();
  }

  close()
  {
    this.successMessage=false;//added
    this.router.navigate(['/user/view-feedback']);
  }
}
