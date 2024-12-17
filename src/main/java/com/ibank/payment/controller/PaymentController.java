/**
 * 
 */
package com.ibank.payment.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ibank.payment.dto.PaymentDto;
import com.ibank.payment.service.IPaymentService;

/**
 * 
 */
@RestController
@RequestMapping( path="/payment", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PaymentController {
	
	@Autowired
	IPaymentService ipaymentService;
	
	
	@GetMapping("/getTransactionsByCategory")
	public  ResponseEntity<List<PaymentDto>> getTransactionsByCategory(@RequestParam String category){
	        return ResponseEntity
	        		.status(HttpStatus.OK)
	        		.body(ipaymentService.findTransactionsByCategory(category));
	    } 
	
	@GetMapping("/getTotalOutgoingAmountByCategory")
	public  ResponseEntity<Map<String, Double>> getTotalOutgoingAmountByCategory(){
		 return ResponseEntity
				 .status(HttpStatus.OK)
				 .body(ipaymentService.findTotalOutgoingAmountByCategory());
	}
	
	@GetMapping("/findMonthlyAverageSpendByCategory")
	public  ResponseEntity<Map<Object, Double>> findMonthlyAverageSpendByCategory(@RequestParam String category){
		 return ResponseEntity
				 .status(HttpStatus.OK)
				 .body(ipaymentService.findMonthlyAverageSpendByCategory(category));
	}
	
	@GetMapping("/findHighestSpendByCategoryForYear")
	public  ResponseEntity<Optional<PaymentDto>> findHighestSpendByCategoryForYear(@RequestParam String category,@RequestParam int year ){
		 return ResponseEntity
				 .status(HttpStatus.OK)
				 .body(ipaymentService.findHighestSpendByCategoryForYear(category,year));
	}
	
	@GetMapping("/findLowestSpendByCategoryForYear")	 
	public  ResponseEntity<Optional<PaymentDto>> findLowestSpendByCategoryForYear(@RequestParam String category,@RequestParam int year){
		 return ResponseEntity
				 .status(HttpStatus.OK)
				 .body(ipaymentService.findHighestSpendByCategoryForYear(category,year));
	}

}
