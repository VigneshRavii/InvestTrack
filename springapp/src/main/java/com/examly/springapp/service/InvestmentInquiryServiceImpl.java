package com.examly.springapp.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import com.examly.springapp.model.Investment;
import com.examly.springapp.model.InvestmentInquiry;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.InvestmentInquiryRepo;
import com.examly.springapp.repository.InvestmentRepo;
import com.examly.springapp.repository.UserRepo;

@Service
public class InvestmentInquiryServiceImpl implements InvestmentInquiryService {

    private final InvestmentInquiryRepo inquiryRepository;
    
    private final UserRepo userRepo;

    private final InvestmentRepo investmentRepo;

    public InvestmentInquiryServiceImpl(InvestmentInquiryRepo inquiryRepository, UserRepo userRepo,
            InvestmentRepo investmentRepo) {
        this.inquiryRepository = inquiryRepository;
        this.userRepo = userRepo;
        this.investmentRepo = investmentRepo;
    }

    @Override
    public InvestmentInquiry createInquiry(InvestmentInquiry inquiry, Long investmentId, Long userId ) {
        User u =  userRepo.findById(userId).orElse(null);
        inquiry.setUser(u);
        Investment inv = investmentRepo.findById(investmentId).orElse(null);
        inquiry.setInvestment(inv);
        inquiry.setInquiryDate(LocalDateTime.now());
        return inquiryRepository.save(inquiry);
    }

    @Override
    public InvestmentInquiry updateInquiry(Long inquiryId, InvestmentInquiry updatedInquiry) {
        updatedInquiry.setInquiryId(inquiryId);
        updatedInquiry.setResponseDate(LocalDateTime.now());
        return inquiryRepository.save(updatedInquiry);
    }

    @Override
    public List<InvestmentInquiry> getInquiriesByUserId(Long userId) {
        return inquiryRepository.findByUser_UserId(userId);
    }

    @Override
    public List<InvestmentInquiry> getInquiriesByInvestmentId(Long investmentId) {
        return inquiryRepository.findByInvestment_InvestmentId(investmentId);
    }

    @Override
    public List<InvestmentInquiry> getUnresolvedInquiries() {
        return inquiryRepository.findByStatus("Pending");
    }

    @Override
    public List<InvestmentInquiry> getInquiriesByPriority(String priority) {
        return inquiryRepository.findByPriority(priority);
    }

    @Override
    public List<InvestmentInquiry> getAllInquiries() {
        return inquiryRepository.findAll();
    }

    @Override
    public void deleteInquiry(Long inquiryId) {

        inquiryRepository.deleteById(inquiryId);
    }
    @Override
    public InvestmentInquiry getInquiryById(Long inquiryId) {
        return inquiryRepository.findById(inquiryId).orElse(null);
    }

}

