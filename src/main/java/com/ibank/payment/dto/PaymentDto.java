/**
 * 
 */
package com.ibank.payment.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Data
@Getter
@Setter
@AllArgsConstructor
public class PaymentDto {
	
	private LocalDate transactionDate;
	private String vendor;
	private String paymentType;
	private double amount;
	private String category;
	
	
	
}
