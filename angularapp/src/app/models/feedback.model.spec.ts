import { Feedback } from './feedback.model';

describe('FeedbackModel', () => {

  fit('frontend_Feedback_model_should_create_an_instance', () => {
    const feedback: Feedback = {
      feedbackId: 1, // Optional, included for testing
      feedbackText: 'Great investment platform!',
      date: '2025-03-18T10:42:29Z',
      user: {
        userId: 123,
        username: 'john_doe' // Optional, included for testing
        ,
        email: '',
        password: '',
        mobileNumber: '',
        userRole: ''
      },
      investment: {
        investmentId: 456,
        name: 'Tech Stock' // Optional, included for testing``
        ,
        description: '',
        type: '',
        purchasePrice: 0,
        currentPrice: 0,
        quantity: 0,
        purchaseDate: '',
        status: ''
      },
      category: 'General'
    };

    expect(feedback).toBeTruthy();
    expect(feedback.feedbackId).toBeDefined();
    expect(feedback.feedbackText).toBeDefined();
    expect(feedback.date).toBeDefined();
    expect(feedback.user).toBeDefined();
    expect(feedback.investment).toBeDefined();
    expect(feedback.category).toBeDefined();
  });

 

});