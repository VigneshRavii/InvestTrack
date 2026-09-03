import { User } from './user.model';

describe('UserModel', () => {

  fit('frontend_User_model_should_create_an_instance', () => {
    const user: User = {
      email: 'testuser@example.com',
      password: 'testpassword',
      username: 'testuser',
      mobileNumber: '1234567890',
      userRole: 'admin'
    };

    expect(user).toBeTruthy();
    expect(user.email).toBeDefined();
    expect(user.password).toBeDefined();
    expect(user.username).toBeDefined();
    expect(user.mobileNumber).toBeDefined();
    expect(user.userRole).toBeDefined();
  });

});
