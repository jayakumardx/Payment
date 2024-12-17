/**
 * 
 */
package com.ibank.payment.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ibank.payment.dto.PaymentDto;

/**
 * 
 */
@Service
public class PaymentServiceImpl implements IPaymentService
{
	private static final String GROCERIES = "Groceries";
	private static final String MYMONTHLYDD = "MyMonthlyDD";
	
	/**
	 * Returns List contains Transactions for the particular Category
	 */

	public List<PaymentDto> findTransactionsByCategory(String category) {
		
		//Optional<String> opt = Optional.ofNullable(category);

		List<PaymentDto> paymentList = loadPaymentData();

		return paymentList.stream().filter(paymentObj -> category.equals(paymentObj.getCategory()))
				.sorted(Comparator.comparing(paymentObj -> ((PaymentDto) paymentObj).getTransactionDate()).reversed())
				.toList();

	}

	/**
	 * Returns Map with key as individual Category and value as Total Number of
	 * Transactions for Each Category
	 */

	public Map<String, Long> findTotalOutgoingByCategory() {

		List<PaymentDto> filteredList = loadPaymentData();

		return filteredList.stream().collect(Collectors.groupingBy(PaymentDto::getCategory, Collectors.counting()));

	}
	
	/**
	 * Returns Map with key as individual Category and value as Total Aggregation of
	 * amount for Each Category
	 */
	
	public Map<String, Double> findTotalOutgoingAmountByCategory() {

		List<PaymentDto> filteredList = loadPaymentData();

		return filteredList.stream().collect(Collectors.groupingBy(PaymentDto::getCategory, Collectors.summingDouble(PaymentDto::getAmount)));

	}

	
	public Map<Object, java.lang.Double> findMonthlyAverageSpendByCategory(String category) {
		return findTransactionsByCategory(category).stream().collect(Collectors.groupingBy(
				txn -> txn.getTransactionDate().getMonth(), Collectors.averagingDouble(PaymentDto::getAmount)));

	}

	public Optional<PaymentDto> findHighestSpendByCategoryForYear(String category, int year) {

		return findTransactionsByCategory(category).stream().filter(txn -> year == txn.getTransactionDate().getYear())
				.max(Comparator.comparing(PaymentDto::getAmount));

	}

	public Optional<PaymentDto> findLowestSpendByCategoryForYear(String category, int year) {

		return findTransactionsByCategory(category).stream().filter(txn -> year == txn.getTransactionDate().getYear())
				.min(Comparator.comparing(PaymentDto::getAmount));

	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * //Optional<String> category = Optional.ofNullable("Groceries"); int year =
	 * 2020; String category ="Groceries"; System.out.println(new
	 * PaymentServiceImpl(). // findTransactionsByCategory(category)) //
	 * System.out.println( new PaymentService().findTotalOutgoingByCategory()) //
	 * findHighestSpendByCategoryForYear(category,year)) //
	 * .forEach(System.out::println) //
	 * findLowestSpendByCategoryForYear(category,year))
	 * findMonthlyAverageSpendByCategory(category));
	 * //findTotalOutgoingAmountByCategory()) ; }
	 */
	
	private static List<PaymentDto> loadPaymentData() {
		 
		return List.of(
				new PaymentDto(LocalDate.of(2020, Month.NOVEMBER, 01), "Morrisons", "card", Double.valueOf(10.40),
						GROCERIES),
				new PaymentDto(LocalDate.of(2021, Month.OCTOBER, 28), "CYBG", "card", Double.valueOf(600), GROCERIES),
				new PaymentDto(LocalDate.of(2020, Month.OCTOBER, 27), "PureGym", "card", Double.valueOf(40), MYMONTHLYDD),
				new PaymentDto(LocalDate.of(2021, Month.OCTOBER, 01), "M&S", "card", Double.valueOf(5.99), MYMONTHLYDD),
				new PaymentDto(LocalDate.of(2020, Month.SEPTEMBER, 30), "McMillan", "card", Double.valueOf(10),
						GROCERIES));

	}



}
