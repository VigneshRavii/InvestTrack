package com.examly.springapp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentInquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long inquiryId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "investmentId", nullable = false)
    private Investment investment;


    @NotBlank(message = "Message must not be blank")
    @Size(max = 1000, message = "Message must not exceed 1000 characters")
    private String message;

    @NotBlank(message = "Status must not be blank")
    private String status;

    @NotBlank(message = "Priority must not be blank")
    private String priority;

    private LocalDateTime inquiryDate;
    private LocalDateTime responseDate;

    private String adminResponse;
    
    private String contactDetails;

    // public InvestmentInquiry() {
    // }

    // public InvestmentInquiry(long inquiryId, User user, Investment investment, String message, String status,
    //         String priority, LocalDateTime inquiryDate, LocalDateTime responseDate, String adminResponse,
    //         String contactDetails) {
    //     this.inquiryId = inquiryId;
    //     this.user = user;
    //     this.investment = investment;
    //     this.message = message;
    //     this.status = status;
    //     this.priority = priority;
    //     this.inquiryDate = inquiryDate;
    //     this.responseDate = responseDate;
    //     this.adminResponse = adminResponse;
    //     this.contactDetails = contactDetails;
    // }

    // public long getInquiryId() {
    //     return inquiryId;
    // }

    // public void setInquiryId(long inquiryId) {
    //     this.inquiryId = inquiryId;
    // }

    // public User getUser() {
    //     return user;
    // }

    // public void setUser(User user) {
    //     this.user = user;
    // }

    // public Investment getInvestment() {
    //     return investment;
    // }

    // public void setInvestment(Investment investment) {
    //     this.investment = investment;
    // }

    // public String getMessage() {
    //     return message;
    // }

    // public void setMessage(String message) {
    //     this.message = message;
    // }

    // public String getStatus() {
    //     return status;
    // }

    // public void setStatus(String status) {
    //     this.status = status;
    // }

    // public String getPriority() {
    //     return priority;
    // }

    // public void setPriority(String priority) {
    //     this.priority = priority;
    // }

    // public LocalDateTime getInquiryDate() {
    //     return inquiryDate;
    // }

    // public void setInquiryDate(LocalDateTime inquiryDate) {
    //     this.inquiryDate = inquiryDate;
    // }

    // public LocalDateTime getResponseDate() {
    //     return responseDate;
    // }

    // public void setResponseDate(LocalDateTime responseDate) {
    //     this.responseDate = responseDate;
    // }

    // public String getAdminResponse() {
    //     return adminResponse;
    // }

    // public void setAdminResponse(String adminResponse) {
    //     this.adminResponse = adminResponse;
    // }

    // public String getContactDetails() {
    //     return contactDetails;
    // }

    // public void setContactDetails(String contactDetails) {
    //     this.contactDetails = contactDetails;
    // }

}
