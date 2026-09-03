import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminViewInquiryComponent } from './admin-view-inquiry.component';

describe('AdminViewInquiryComponent', () => {
  let component: AdminViewInquiryComponent;
  let fixture: ComponentFixture<AdminViewInquiryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminViewInquiryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminViewInquiryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
