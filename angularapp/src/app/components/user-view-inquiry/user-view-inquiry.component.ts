import { Component, OnInit } from '@angular/core';
import { InvestmentInquiry } from 'src/app/models/investment-inquiry.model';
import { InvestmentInquiryService } from 'src/app/services/investment-inquiry.service';

@Component({
  selector: 'app-user-view-inquiry',
  templateUrl: './user-view-inquiry.component.html',
  styleUrls: ['./user-view-inquiry.component.css']
})
export class UserViewInquiryComponent implements OnInit {
  inquries:InvestmentInquiry[]=[];
  userId : number = null;
  page: number = 1;

  constructor(private is:InvestmentInquiryService) { }

  ngOnInit(): void {
    this.userId = +localStorage.getItem('userId');
    this.loadInquiries();
  }
  loadInquiries(){
    this.is.getInquiriesByUserId(this.userId).subscribe((result)=>{
      this.inquries=result;
    })

  }

}
