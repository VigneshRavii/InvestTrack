import { Investment } from './investment.model';

describe('InvestmentModel', () => {
  fit('frontend_Investment_model_should_create_an_instance', () => {
    const investment: Investment = {
      investmentId: 1, // Optional, can be omitted for new investments
      name: 'Tech Stocks Portfolio',
      description: 'Diversified portfolio of leading tech companies',
      type: 'Stocks',
      purchasePrice: 5000,
      currentPrice: 6500,
      quantity: 100,
      purchaseDate: '2023-01-15',
      status: 'Active'
    };

    expect(investment).toBeTruthy();
    expect(investment.name).toBe('Tech Stocks Portfolio');
    expect(investment.currentPrice).toBeGreaterThan(investment.purchasePrice);
    expect(investment.purchaseDate).toMatch(/^\d{4}-\d{2}-\d{2}$/); // Validate date format
  });
});