import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Investment } from 'src/app/models/investment.model';
import { InvestmentService } from 'src/app/services/investment.service';

@Component({
  selector: 'app-admin-view-investment',
  templateUrl: './admin-view-investment.component.html',
  styleUrls: ['./admin-view-investment.component.css']
})
export class AdminViewInvestmentComponent implements OnInit {

  investments: Investment[] = [];
  searchText: string = '';
  filterBy: string = 'All Types';

  showPopup: boolean = false;
  popupMessage: string = '';
  selectedAction: 'edit' | 'delete' | null = null;
  selectedInvestmentId: number | null = null;

  page:number=1;
  itemsPerPage : number = 5;

  constructor(private service: InvestmentService, private router: Router) {}

  ngOnInit(): void {
    this.getAllInvestments();
  }

  getAllInvestments(): void {
    this.service.getAllInvestments().subscribe(
      (result) => {
        this.investments = result;
        console.log(result);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  openPopup(action: 'edit' | 'delete', investmentId: number): void {
    this.selectedAction = action;
    this.selectedInvestmentId = investmentId;
    this.popupMessage = action === 'edit'
      ? 'Do you want to edit this investment?'
      : 'Are you sure you want to delete this investment?';
    this.showPopup = true;
  }

  confirmAction(): void {
    if (this.selectedAction && this.selectedInvestmentId !== null) {
      if (this.selectedAction === 'edit') {
        this.editInvestment(this.selectedInvestmentId);
      } else if (this.selectedAction === 'delete') {
        this.deleteInvestment(this.selectedInvestmentId);
      }
    }
    this.resetPopup();
  }

  cancelAction(): void {
    this.resetPopup();
  }

  resetPopup(): void {
    this.showPopup = false;
    this.popupMessage = '';
    this.selectedAction = null;
    this.selectedInvestmentId = null;
  }

  editInvestment(investmentId: number): void {
    this.router.navigate(['/admin/edit-investment', investmentId]);
  }

  deleteInvestment(investmentId: number): void {
    this.service.deleteInvestment(investmentId).subscribe(
      (result) => {
        this.getAllInvestments();
        console.log('Investment deleted');
        alert("Investment deleted");
      },
      (error) => {
        console.error(error);
      }
    );
  }
}
