import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserViewInvestmentComponent } from './user-view-investment.component';

describe('UserViewInvestmentComponent', () => {
  let component: UserViewInvestmentComponent;
  let fixture: ComponentFixture<UserViewInvestmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserViewInvestmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserViewInvestmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
