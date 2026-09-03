import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { InvestmentInquiry } from 'src/app/models/investment-inquiry.model';
import { InvestmentInquiryService } from 'src/app/services/investment-inquiry.service';

@Component({
  selector: 'app-admin-view-inquiry',
  templateUrl: './admin-view-inquiry.component.html',
  styleUrls: ['./admin-view-inquiry.component.css']
})
export class AdminViewInquiryComponent implements OnInit {
  priorities: string[] = ['AllPriorities', 'High', 'Medium', 'Low'];
  statuses: string[] = ['Allstatuses', 'Pending', 'Resolved'];
  inquiries: InvestmentInquiry[] = [];
  selectedInquiry: InvestmentInquiry | null = null;
  responseText: string = '';
  selectedPriority = 'AllPriorities';
  selectedStatus = 'Allstatuses';
  searchfilter = '';
  filteredList: InvestmentInquiry[] = [];

  SelectedStatus = 'Allstatuses';


  page: number = 1;



  constructor(private ivs: InvestmentInquiryService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const status = params['status'];
      if (status) {
        this.selectedStatus = status;
        this.applyFilters();
      }
    })
    this.route.queryParams.subscribe(params => {
      const priority = params['priority'];
      if (priority) {
        this.selectedPriority = priority;
        this.applyFilters();
      }
    })
    this.loadInquiries();
  }

  loadInquiries() {
    this.ivs.getAllInquiries().subscribe((result) => {
      this.inquiries = result;
      this.applyFilters();
    })
  }

  applyFilters(): void {
    this.filteredList = this.inquiries.filter(inquiry =>
      (this.selectedPriority === 'AllPriorities' || inquiry.priority === this.selectedPriority) &&
      (this.selectedStatus === 'Allstatuses' || inquiry.status === this.selectedStatus)
    );
  }



  openResponseModal(inquiry: InvestmentInquiry): void {
    this.selectedInquiry = inquiry;
    this.responseText = inquiry.adminResponse || '';
  }

  submitResponse(): void {
    if (this.selectedInquiry) {
      this.selectedInquiry.adminResponse = this.responseText;
      this.ivs.updateInquiry(this.selectedInquiry.inquiryId, this.selectedInquiry).subscribe(() => {
        this.loadInquiries();
        this.selectedInquiry = null;
      });
    }
  }

  deleteInquiry(inquiryId: number): void {
    if (confirm('Are you sure you want to delete this inquiry?')) {
      this.ivs.deleteInquiry(inquiryId).subscribe(() => {
        console.log(inquiryId);
        this.loadInquiries();
      });
    }
  }

  changeStatus(inquiry: InvestmentInquiry, newStatus: string): void {
    inquiry.status = newStatus;
    this.ivs.updateInquiry(inquiry.inquiryId, inquiry).subscribe(() => {
      this.loadInquiries();
    });
  }
}





