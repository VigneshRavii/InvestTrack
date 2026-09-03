package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.examly.springapp.model.InvestmentInquiry;

@Repository
public interface InvestmentInquiryRepo extends JpaRepository<InvestmentInquiry, Long> {

    List<InvestmentInquiry> findByUser_UserId(Long userId);
    List<InvestmentInquiry> findByInvestment_InvestmentId(Long investmentId);
    List<InvestmentInquiry> findByPriority(String priority);
    List<InvestmentInquiry> findByStatus(String status);
    @Query("select i from InvestmentInquiry i where i.investment.investmentId=?1")
    List<InvestmentInquiry> getInquiriesByInvestmentId(Long investmentId);


}
