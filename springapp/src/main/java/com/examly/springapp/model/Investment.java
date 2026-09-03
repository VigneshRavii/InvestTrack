package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long investmentId;


    @NotBlank(message = "Name must not be blank")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;
    
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @NotBlank(message = "Type must not be blank")
    private String type;

    @Positive(message = "Purchase price must be positive")
    private double purchasePrice;

    @PositiveOrZero(message = "Current price must be zero or positive")
    private double currentPrice;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
    
    private String purchaseDate;

    @NotBlank(message = "Status must not be blank")
    private String status;
    
    // public long getInvestmentId() {
    //     return investmentId;
    // }
    // public void setInvestmentId(long investmentId) {
    //     this.investmentId = investmentId;
    // }
    // public String getName() {
    //     return name;
    // }
    // public void setName(String name) {
    //     this.name = name;
    // }
    // public String getDescription() {
    //     return description;
    // }
    // public void setDescription(String description) {
    //     this.description = description;
    // }
    // public String getType() {
    //     return type;
    // }
    // public void setType(String type) {
    //     this.type = type;
    // }
    // public double getPurchasePrice() {
    //     return purchasePrice;
    // }
    // public void setPurchasePrice(double purchasePrice) {
    //     this.purchasePrice = purchasePrice;
    // }
    // public double getCurrentPrice() {
    //     return currentPrice;
    // }
    // public void setCurrentPrice(double currentPrice) {
    //     this.currentPrice = currentPrice;
    // }
    // public int getQuantity() {
    //     return quantity;
    // }
    // public void setQuantity(int quantity) {
    //     this.quantity = quantity;
    // }
    // public String getPurchaseDate() {
    //     return purchaseDate;
    // }
    // public void setPurchaseDate(String purchaseDate) {
    //     this.purchaseDate = purchaseDate;
    // }
    // public String getStatus() {
    //     return status;
    // }
    // public void setStatus(String status) {
    //     this.status = status;
    // }
    // public Investment(long investmentId, String name, String description, String type, double purchasePrice,
    //         double currentPrice, int quantity, String purchaseDate, String status) {
    //     this.investmentId = investmentId;
    //     this.name = name;
    //     this.description = description;
    //     this.type = type;
    //     this.purchasePrice = purchasePrice;
    //     this.currentPrice = currentPrice;
    //     this.quantity = quantity;
    //     this.purchaseDate = purchaseDate;
    //     this.status = status;
    // }
    // public Investment() {
    // }
    // @Override
    // public String toString() {
    //     return  "" + investmentId +  name + description
    //             + type +  purchasePrice +  currentPrice
    //             + quantity  + purchaseDate +  status ;
    // }

    




}
