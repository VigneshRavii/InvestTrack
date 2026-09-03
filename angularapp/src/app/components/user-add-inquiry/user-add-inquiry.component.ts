import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { InvestmentInquiry } from 'src/app/models/investment-inquiry.model';
import { Investment } from 'src/app/models/investment.model';
import { User } from 'src/app/models/user.model';
import { InvestmentInquiryService } from 'src/app/services/investment-inquiry.service';
import { InvestmentService } from 'src/app/services/investment.service';

@Component({
  selector: 'app-user-add-inquiry',
  templateUrl: './user-add-inquiry.component.html',
  styleUrls: ['./user-add-inquiry.component.css']
})
export class UserAddInquiryComponent implements OnInit {
  showPopup = false; 

  investmentId : number = null;

investment:Investment ={
  name: '',
  description: '',
  type: '',
  purchasePrice: 0,
  currentPrice: 0,
  quantity: 0,
  purchaseDate: '',
  status: ''
};
inquiry: InvestmentInquiry = {
  user: null,
  investment: null,
  message: '',
  priority: '',
  status:'',
  adminResponse:''
};


  successMessage = '';

  constructor(private router: Router,private ts:InvestmentInquiryService,private tsi:InvestmentService, private route : ActivatedRoute) {}

  ngOnInit(): void {
    this.investmentId = +this.route.snapshot.paramMap.get('investmentId');
    this.loadInvestment();
  }

  loadInvestment() {
    this.tsi.getInvestmentById(this.investmentId).subscribe((result)=>{
      this.investment = result;
    });

  }

  onSubmit(form: any): void {
  
    if (form.invalid) return;
   this.inquiry.status = 'Pending'
   this.inquiry.adminResponse='No Response Yet'
    this.ts.addInquiry(this.inquiry,this.investmentId).subscribe({
      next: () => {
        this.successMessage = 'Inquiry submitted successfully!';
        

        this.showPopup = true

        form.resetForm();
  
       
      },
      error: (err) => {
        console.error('Error submitting inquiry:', err);
      }
    });
  }
  
 
  viewComponent(): void {
    this.showPopup = false;
    this.router.navigate(['/user/view-inquiry']);
  }

 
}
