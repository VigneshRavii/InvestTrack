import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { InvestmentService } from './investment.service';

describe('InvestmentService', () => {
  let service: InvestmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(InvestmentService);
  });

  fit('frontend_should_create_investment_service', () => {
    expect((service as any)).toBeTruthy();
  });
});