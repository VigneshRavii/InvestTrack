package com.examly.springapp.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Feedback;
import com.examly.springapp.service.FeedbackService;

import jakarta.validation.Valid;

@RestController
public class FeedbackController {
    
    private final FeedbackService feedbackService;
    
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/api/feedback/{userId}/{investmentId}")
    public ResponseEntity<Feedback> createFeedback(@Valid @RequestBody Feedback feedback, @PathVariable Long userId, @PathVariable Long investmentId) {
        Feedback fb = feedbackService.createFeedback(feedback,userId, investmentId);
        if (fb != null) {
            return ResponseEntity.status(201).body(fb);
        } else {
            return ResponseEntity.status(403).body(null);
        }
    }

    @GetMapping("/api/feedback/{feedbackId}")
    public ResponseEntity<Feedback> getFeedBackById(@PathVariable Long feedbackId){

        Feedback feedback=feedbackService.getFeedBackById(feedbackId);
        if(feedback!=null){
            return ResponseEntity.status(200).body(feedback);
        }  
        else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/api/feedback/user/{userId}")
    public ResponseEntity<List<Feedback>> getFeedbacksByUserId(@PathVariable Long userId)
    {
        List<Feedback> list = feedbackService.getFeedbacksByUserId(userId);
        if(!list.isEmpty())
        {
            return ResponseEntity.status(200).body(list);
        }
        else 
        {
            return ResponseEntity.status(404).body(null); 
        }
    }

    @DeleteMapping("/api/feedback/{feedbackId}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long feedbackId)
    {
        feedbackService.deleteFeedback(feedbackId);
            return ResponseEntity.status(200).body(null);
    }

    @GetMapping("/api/feedback")
    public ResponseEntity<List<Feedback>> getAllFeedbacks(){
        List<Feedback> li=feedbackService.getAllFeedbacks();
        if(!li.isEmpty()){
            return ResponseEntity.status(200).body(li);
        }
        else{
            return ResponseEntity.status(400).body(null);
        }
    }
}
