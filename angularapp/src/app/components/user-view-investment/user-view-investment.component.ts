import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Investment } from 'src/app/models/investment.model';
import { InvestmentService } from 'src/app/services/investment.service';

@Component({
  selector: 'app-user-view-investment',
  templateUrl: './user-view-investment.component.html',
  styleUrls: ['./user-view-investment.component.css']
})
export class UserViewInvestmentComponent implements OnInit {

  investments:Investment[]=[];

  getAllInvestments(){
    this.service.getAllInvestments().subscribe((result)=>{
      this.investments=result;
      console.log(result);
    },(error)=>{
      console.log(error);
    });
  }

  actionInquiry(investmentId:number){
    this.router.navigate(["/user/add-inquiry",investmentId]);
  }

  searchText:string='';

  filterBy:string='';

  page: number = 1;

  constructor(private service:InvestmentService,private router:Router) { }

  ngOnInit(): void {
    this.getAllInvestments();
  }

}
