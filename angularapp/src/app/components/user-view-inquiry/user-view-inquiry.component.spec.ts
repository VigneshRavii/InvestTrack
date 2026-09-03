import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserViewInquiryComponent } from './user-view-inquiry.component';

describe('UserViewInquiryComponent', () => {
  let component: UserViewInquiryComponent;
  let fixture: ComponentFixture<UserViewInquiryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserViewInquiryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserViewInquiryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
