package com.examly.springapp.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Investment;
import com.examly.springapp.service.InvestmentService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
public class InvestmentController {

    private final InvestmentService investmentService;

    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @PostMapping("/api/investments")
    public ResponseEntity<Investment> addInvestment(@Valid @RequestBody Investment investment){
        Investment inv=investmentService.addInvestment(investment);
        if(inv!=null){
            return new ResponseEntity<>(inv,HttpStatus.valueOf(201));
        }
        else{
            return new ResponseEntity<>(HttpStatus.valueOf(403));
        }
    }

    @GetMapping("/api/investments/{investmentId}")
    public ResponseEntity<Investment> getInvestmentById(@PathVariable long investmentId){
        Investment inv=investmentService.getInvestmentById(investmentId);
        if(inv!=null){
            return new ResponseEntity<>(inv,HttpStatus.valueOf(200));
        }
        else{
            return new ResponseEntity<>(HttpStatus.valueOf(404));
        }
    }

    @GetMapping("/api/investments")
    public ResponseEntity<List<Investment>> getAllInvestments(){
        List<Investment> list=investmentService.getAllInvestments();
        if(!list.isEmpty()){
            return new ResponseEntity<>(list,HttpStatus.valueOf(200));
        }
        else{
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }
    }

    @PutMapping("/api/investments/{investmentId}")
    public ResponseEntity<Investment> updateInvestment(@PathVariable long investmentId, @Valid @RequestBody Investment updatedInvestment){
        Investment inv=investmentService.updateInvestment(investmentId, updatedInvestment);
        if(inv!=null){
            return new ResponseEntity<>(inv,HttpStatus.valueOf(200));
        }
        else{
            return new ResponseEntity<>(HttpStatus.valueOf(404));
        }
    }

    @DeleteMapping("/api/investments/{investmentId}")
    public ResponseEntity<Void> deleteInvestment(@PathVariable Long investmentId){
        investmentService.deleteInvestment(investmentId);
        return new ResponseEntity<>(HttpStatus.valueOf(200));
    }

}
