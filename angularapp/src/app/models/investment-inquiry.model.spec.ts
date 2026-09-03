import { InvestmentInquiry } from './investment-inquiry.model';

describe('InvestmentInquiryModel', () => {

  fit('frontend_InvestmentInquiry_model_should_create_an_instance', () => {
    const inquiry: InvestmentInquiry = {
      inquiryId: 1, // Optional, included for testing
      user: {
        userId: 123,
        username: 'john_doe' // Optional field from Partial<User>
        ,
        email: '',
        password: '',
        mobileNumber: '',
        userRole: ''
      },
      investment: {
        investmentId: 456,
        name: 'Tech Stock' // Optional field from Partial<Investment>
        ,
        description: '',
        type: '',
        purchasePrice: 0,
        currentPrice: 0,
        quantity: 0,
        purchaseDate: '',
        status: ''
      },
      message: 'Need advice on this investment',
      status: 'Pending', // Optional, included for testing
      inquiryDate: '2025-03-18T10:42:29Z', // Optional, included for testing
      responseDate: '2025-03-19T12:00:00Z', // Optional, included for testing
      adminResponse: 'Here is the advice you requested', // Optional, included for testing
      priority: 'High',
      contactDetails: 'john.doe@example.com' // Optional, included for testing
    };

    expect(inquiry).toBeTruthy();
    expect(inquiry.inquiryId).toBeDefined();
    expect(inquiry.user).toBeDefined();
    expect(inquiry.investment).toBeDefined();
    expect(inquiry.message).toBeDefined();
    expect(inquiry.status).toBeDefined();
    expect(inquiry.inquiryDate).toBeDefined();
    expect(inquiry.responseDate).toBeDefined();
    expect(inquiry.adminResponse).toBeDefined();
    expect(inquiry.priority).toBeDefined();
    expect(inquiry.contactDetails).toBeDefined();
  });

});