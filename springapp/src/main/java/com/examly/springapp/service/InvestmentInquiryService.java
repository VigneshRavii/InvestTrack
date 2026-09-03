package com.examly.springapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springapp.model.InvestmentInquiry;

@Service
public interface InvestmentInquiryService {

    public InvestmentInquiry createInquiry(InvestmentInquiry inquiry, Long investmentId, Long userId);
    public InvestmentInquiry updateInquiry(Long inquiryId, InvestmentInquiry updatedInquiry);
    public List<InvestmentInquiry> getInquiriesByUserId(Long userId); 
    public List<InvestmentInquiry> getInquiriesByInvestmentId(Long investmentId);
    public List<InvestmentInquiry> getUnresolvedInquiries(); 
    public List<InvestmentInquiry> getInquiriesByPriority(String priority);
    public List<InvestmentInquiry> getAllInquiries();
    public void deleteInquiry(Long inquiryId);  
    public InvestmentInquiry getInquiryById(Long inquiryId);

}
