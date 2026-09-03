import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { InvestmentInquiryService } from './investment-inquiry.service';

describe('InvestmentInquiryService', () => {
  let service: InvestmentInquiryService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(InvestmentInquiryService);
  });

  fit('frontend_should_create_investment_inquiry_service', () => {
    expect((service as any)).toBeTruthy();
  });
});