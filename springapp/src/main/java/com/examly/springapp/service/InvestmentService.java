package com.examly.springapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springapp.model.Investment;

@Service
public interface InvestmentService {

    public Investment addInvestment(Investment investment);
    public Investment updateInvestment(long investmentId, Investment updatedInvestment);
    public Investment getInvestmentById(long investmentId);
    public List<Investment> getAllInvestments();
    public List<Investment> getInvestmentsByType(String type);
    public List<Investment> getInvestmentsByStatus(String status);
    public List<Investment> searchInvestments(String keyword);
    public void deleteInvestment(Long investmentId);
    
}
