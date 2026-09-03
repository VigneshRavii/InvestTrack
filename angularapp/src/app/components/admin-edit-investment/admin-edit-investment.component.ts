import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Investment } from 'src/app/models/investment.model';
import { InvestmentService } from 'src/app/services/investment.service';

@Component({
  selector: 'app-admin-edit-investment',
  templateUrl: './admin-edit-investment.component.html',
  styleUrls: ['./admin-edit-investment.component.css']
})
export class AdminEditInvestmentComponent implements OnInit {

  invObj:Investment={
    name: '',
    description: '',
    type: '',
    purchasePrice: 0,
    currentPrice: 0,
    quantity: 0,
    purchaseDate: '',
    status: ''
  };

  updatedInvestmentObj:Investment={
    name: '',
    description: '',
    type: '',
    purchasePrice: 0,
    currentPrice: 0,
    quantity: 0,
    purchaseDate: '',
    status: ''
  };

  id:number=null;

  updateInvestment(invForm:NgForm){
    if(invForm.valid){
      this.service.updateInvestment(this.id,this.updatedInvestmentObj).subscribe((result)=>{
        console.log(result);
        alert("Updated the Investment Successfully!");
        this.router.navigate(["/admin/view-investment"]);
      },(error)=>{
        console.log(error);
      });
    }

  }

  getInvestmentById(){
    this.service.getInvestmentById(this.id).subscribe((result)=>{
      this.invObj=result;
      this.updatedInvestmentObj={...result}
      console.log(result);
    },(error)=>{
      console.log(error);
    });
  }

  constructor(private route:ActivatedRoute,private service:InvestmentService,private router:Router) { }

  ngOnInit(): void {
    this.id=+this.route.snapshot.paramMap.get("investmentId");
    this.getInvestmentById();
  }

}
