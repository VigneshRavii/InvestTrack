import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminViewInvestmentComponent } from './admin-view-investment.component';

describe('AdminViewInvestmentComponent', () => {
  let component: AdminViewInvestmentComponent;
  let fixture: ComponentFixture<AdminViewInvestmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminViewInvestmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminViewInvestmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
