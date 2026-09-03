package com.examly.springapp.controller;
import com.examly.springapp.model.InvestmentInquiry;
import com.examly.springapp.service.InvestmentInquiryService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/inquiries")
public class InvestmentInquiryController {

    private final InvestmentInquiryService inquiryService;

    public InvestmentInquiryController(InvestmentInquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @PostMapping("/{investmentId}/{userId}")
    public ResponseEntity<InvestmentInquiry> createInquiry(@Valid @RequestBody InvestmentInquiry inquiry, @PathVariable Long investmentId, @PathVariable Long userId) { 
        InvestmentInquiry created = inquiryService.createInquiry(inquiry,investmentId, userId);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{inquiryId}")
    public ResponseEntity<InvestmentInquiry> getInquiryById(@PathVariable long inquiryId) {
        InvestmentInquiry inquiry = inquiryService.getInquiryById(inquiryId);
        if (inquiry != null) {
         return ResponseEntity.ok(inquiry);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<InvestmentInquiry>> getByUser(@PathVariable Long userId) {  
        List<InvestmentInquiry> investment = inquiryService.getInquiriesByUserId(userId);
        if(investment.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.valueOf(404));
        } 
        return ResponseEntity.ok(investment);
    }
   
    @GetMapping                     
    public ResponseEntity<List<InvestmentInquiry>> getAll() {
        List<InvestmentInquiry> li = inquiryService.getAllInquiries();
        if(li.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        } 
        return ResponseEntity.ok(li);
    }


    @PutMapping("/{inquiryId}") 
    public ResponseEntity<InvestmentInquiry> updateInquiry(@PathVariable long inquiryId, @Valid @RequestBody InvestmentInquiry updated) {
        InvestmentInquiry investment = inquiryService.updateInquiry(inquiryId, updated);
        if(investment != null) {
            
            return ResponseEntity.ok(investment);
        }
        return new ResponseEntity<>(HttpStatus.valueOf(404));
    }

    @DeleteMapping("/{inquiryId}")  
    public ResponseEntity<Void> deleteInquiry(@PathVariable Long inquiryId) {
        inquiryService.deleteInquiry(inquiryId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/statusUnresolved")
    public ResponseEntity<List<InvestmentInquiry>> getUnresolvedInquiries() {
        List<InvestmentInquiry> list = inquiryService.getUnresolvedInquiries(); 
        return new ResponseEntity<>(list, HttpStatus.valueOf(200)); 
    }

    @GetMapping("/highPriority")
    public ResponseEntity<List<InvestmentInquiry>> getHighPriorityInquiries() {
        List<InvestmentInquiry> li = inquiryService.getInquiriesByPriority("High");
        return new ResponseEntity<>(li, HttpStatus.valueOf(200));

    }
    
}
