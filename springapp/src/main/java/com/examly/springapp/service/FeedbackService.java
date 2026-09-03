package com.examly.springapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springapp.model.Feedback;

@Service
public interface FeedbackService {

    Feedback createFeedback(Feedback feedback, Long userId, Long investmentId);
    Feedback getFeedBackById(Long feedbackId);
    List<Feedback> getAllFeedbacks();
    void deleteFeedback(Long feedbackId);
    List<Feedback> getFeedbacksByUserId(Long userId);
    List<Feedback> getFeedbacksByInvestmentId(Long investmentId);

}
