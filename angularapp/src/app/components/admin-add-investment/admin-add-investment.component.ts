import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Investment } from 'src/app/models/investment.model';
import { InvestmentService } from 'src/app/services/investment.service';

@Component({
  selector: 'app-admin-add-investment',
  templateUrl: './admin-add-investment.component.html',
  styleUrls: ['./admin-add-investment.component.css']
})
export class AdminAddInvestmentComponent implements OnInit {

  invobj:Investment={
    name: '',
    description: '',
    type: '',
    purchasePrice: null,
    currentPrice: null,
    quantity: null,
    purchaseDate: '',
    status: ''
  }

  isSubmittedSuccessfully = false;

  
  addInvestment(invForm:NgForm){
    if(invForm.valid){
    this.service.addInvestment(this.invobj).subscribe((result)=>{
      alert("Investment added successfully!");
      invForm.resetForm();
      this.router.navigate(["/admin/view-investment"]);
      console.log(result);
    },(error)=>{
      for(const f in error.error){
          alert(`${error.error[f]}`);
      }
      console.log(error.error);
    });
  }
}

  constructor(private readonly service:InvestmentService,private readonly router:Router) { }

  ngOnInit(): void {
  }

}
