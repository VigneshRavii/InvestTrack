package com.examly.springapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Feedback;
@Repository
public interface FeedbackRepo extends JpaRepository<Feedback,Long>{

    @Query("select f from Feedback f where f.user.userId=?1")
    List<Feedback> findByUser_UserId(Long userId);
    List<Feedback> findByInvestment_InvestmentId(Long investmentId);
    @Query("select f from Feedback f where f.investment.investmentId=?1")
    List<Feedback> getFeedbacksByInvestmentId(Long investmentId);
    
}
