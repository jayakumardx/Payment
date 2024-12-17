/**
 * 
 */
package com.ibank.payment.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ibank.payment.dto.PaymentDto;

/**
 * 
 */
public interface IPaymentService {

	public List<PaymentDto> findTransactionsByCategory(String category);
	public Map<String, Long> findTotalOutgoingByCategory();
	public Map<String, Double> findTotalOutgoingAmountByCategory();
	public Map<Object, java.lang.Double> findMonthlyAverageSpendByCategory(String category);
	public Optional<PaymentDto> findHighestSpendByCategoryForYear(String category, int year);
	public Optional<PaymentDto> findLowestSpendByCategoryForYear(String category, int year);
}
