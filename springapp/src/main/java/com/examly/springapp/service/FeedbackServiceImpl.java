package com.examly.springapp.service;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.stereotype.Service;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.Investment;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.FeedbackRepo;
import com.examly.springapp.repository.InvestmentRepo;
import com.examly.springapp.repository.UserRepo;

@Service
public class FeedbackServiceImpl implements FeedbackService {

  private final FeedbackRepo feedbackRepo;

  private final UserRepo userRepo;

  private final InvestmentRepo investmentRepo;

  public FeedbackServiceImpl(FeedbackRepo feedbackRepo, UserRepo userRepo, InvestmentRepo investmentRepo) {
    this.feedbackRepo = feedbackRepo;
    this.userRepo = userRepo;
    this.investmentRepo = investmentRepo;
  }

  @Override
  public Feedback createFeedback(Feedback feedback, Long userId, Long investmentId) {

    User u = userRepo.findById(userId).orElse(null);
    feedback.setUser(u);
    Investment inv = investmentRepo.findById(investmentId).orElse(null);
    feedback.setInvestment(inv);

    Instant now = Instant.now();

    // Format it to ISO 8601 with 'Z' for UTC
    String formattedDate = DateTimeFormatter.ISO_INSTANT.format(now);

    feedback.setDate(formattedDate);
    return feedbackRepo.save(feedback);

  }

  @Override
  public List<Feedback> getAllFeedbacks() {
    return feedbackRepo.findAll();
  }

  @Override
  public Feedback getFeedBackById(Long feedbackId) {
    return feedbackRepo.findById(feedbackId).orElse(null);
  }

  @Override
  public void deleteFeedback(Long feedbackId) {
    if (feedbackRepo.existsById(feedbackId)) {
      feedbackRepo.deleteById(feedbackId);
    }

  }

  @Override
  public List<Feedback> getFeedbacksByUserId(Long userId) {
    return feedbackRepo.findByUser_UserId(userId);

  }

  @Override
  public List<Feedback> getFeedbacksByInvestmentId(Long investmentId) {
    return feedbackRepo.findByInvestment_InvestmentId(investmentId);

  }
}
