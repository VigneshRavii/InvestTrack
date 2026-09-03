package com.examly.springapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.examly.springapp.exceptions.DuplicateInvestmentException;
import com.examly.springapp.exceptions.ResourseNotFoundException;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.Investment;
import com.examly.springapp.model.InvestmentInquiry;
import com.examly.springapp.repository.FeedbackRepo;
import com.examly.springapp.repository.InvestmentInquiryRepo;
import com.examly.springapp.repository.InvestmentRepo;

@Service
public class InvestmentServiceImpl implements InvestmentService {

    private final InvestmentRepo investmentRepo;

    private final FeedbackRepo feedbackRepo;

    private final InvestmentInquiryRepo investmentInquiryRepo;
    

    public InvestmentServiceImpl(InvestmentRepo investmentRepo, FeedbackRepo feedbackRepo,
            InvestmentInquiryRepo investmentInquiryRepo) {
        this.investmentRepo = investmentRepo;
        this.feedbackRepo = feedbackRepo;
        this.investmentInquiryRepo = investmentInquiryRepo;
    }

    @Override
    public Investment addInvestment(Investment investment) {
        Optional<Investment> o= investmentRepo.findByName(investment.getName());
        if(o.isPresent()){
            throw new DuplicateInvestmentException("Investment already exists!");
        }
        investment.setPurchaseDate(LocalDate.now().toString());
        return investmentRepo.save(investment);
    }

    @Override
    public Investment updateInvestment(long investmentId, Investment updatedInvestment) {
        Optional<Investment> o=investmentRepo.findById(investmentId);
        if(o.isPresent()){
            updatedInvestment.setInvestmentId(investmentId);
            return investmentRepo.save(updatedInvestment);
        }
        throw new ResourseNotFoundException("Investment not found with ID: "+investmentId);
    }

    @Override
    public Investment getInvestmentById(long investmentId) {
        Optional<Investment> o=investmentRepo.findById(investmentId);
        if(o.isPresent()){
            return o.get();
        }
        throw new ResourseNotFoundException("Investment not found with ID: "+investmentId);
    }

    @Override
    public List<Investment> getAllInvestments() {
        return investmentRepo.findAll();
    }

    @Override
    public List<Investment> getInvestmentsByType(String type) {
        return investmentRepo.findByType(type);
    }

    @Override
    public List<Investment> getInvestmentsByStatus(String status) {
        return investmentRepo.findByStatus(status);
    }

    @Override
    public List<Investment> searchInvestments(String keyword) {
        List<Investment> list=investmentRepo.findAll();
        List<Investment> ans= new ArrayList<>();
        for(Investment in:list){
            if(in.toString().toLowerCase().contains(keyword.toLowerCase())){
                ans.add(in);
            }
        }
        return ans;
    }

    @Override
    public void deleteInvestment(Long investmentId) {
        List<Feedback> feedbacks = feedbackRepo.getFeedbacksByInvestmentId(investmentId);
        for(int i =0;i<feedbacks.size();i++) {
            Feedback f = feedbacks.get(i);

            feedbackRepo.deleteById(f.getFeedbackId());

        }
        List<InvestmentInquiry> inquiries = investmentInquiryRepo.getInquiriesByInvestmentId(investmentId);
        for(int i = 0; i< inquiries.size();i++) {
            InvestmentInquiry ii = inquiries.get(i);

            investmentInquiryRepo.deleteById(ii.getInquiryId());
        }
        investmentRepo.deleteById(investmentId);
    }

}
