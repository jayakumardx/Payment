package com.ibank.payment;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ibank.payment.dto.PaymentDto;
import com.ibank.payment.service.PaymentServiceImpl;

class PaymentServiceImplTest {

    private PaymentServiceImpl paymentService;

    @BeforeEach
    void setUp() {
        paymentService = new PaymentServiceImpl();
    }

    @Test
    void testFindTransactionsByCategory() {
        String category = "Groceries";
        List<PaymentDto> result = paymentService.findTransactionsByCategory(category);

        assertEquals(3, result.size());
        assertEquals("CYBG", result.get(0).getVendor()); // Most recent transaction
        assertEquals("Morrisons", result.get(1).getVendor()); // Second-most recent transaction
    }

    @Test
    void testFindTotalOutgoingByCategory() {
        Map<String, Long> result = paymentService.findTotalOutgoingByCategory();

        assertEquals(2, result.size());
        assertEquals(3, result.get("Groceries"));
        assertEquals(2, result.get("MyMonthlyDD"));
    }

    @Test
    void testFindTotalOutgoingAmountByCategory() {
        Map<String, Double> result = paymentService.findTotalOutgoingAmountByCategory();

        assertEquals(2, result.size());
        assertEquals(620.4, result.get("Groceries"), 0.01);
        assertEquals(45.99, result.get("MyMonthlyDD"), 0.01);
    }

    @Test
    void testFindMonthlyAverageSpendByCategory() {
        String category = "Groceries";
        Map<Object, Double> result = paymentService.findMonthlyAverageSpendByCategory(category);

        assertEquals(3, result.size());
        assertEquals(10.4, result.get(Month.NOVEMBER), 0.01);
        assertEquals(600, result.get(Month.OCTOBER), 0.01);
    }

    @Test
    void testFindHighestSpendByCategoryForYear() {
        String category = "Groceries";
        int year = 2021;

        Optional<PaymentDto> result = paymentService.findHighestSpendByCategoryForYear(category, year);

        assertTrue(result.isPresent());
        assertEquals(600, result.get().getAmount());
        assertEquals("CYBG", result.get().getVendor());
    }

    @Test
    void testFindLowestSpendByCategoryForYear() {
        String category = "Groceries";
        int year = 2020;

        Optional<PaymentDto> result = paymentService.findLowestSpendByCategoryForYear(category, year);

        assertTrue(result.isPresent());
        assertEquals(10.0, result.get().getAmount());
        assertEquals("McMillan", result.get().getVendor());
    }

    @Test
    void testFindTransactionsByCategory_EmptyCategory() {
        List<PaymentDto> result = paymentService.findTransactionsByCategory("NonExistentCategory");

        assertTrue(result.isEmpty());
    }

    @Test
    void testFindHighestSpendByCategoryForYear_NoTransactions() {
        String category = "Groceries";
        int year = 2025;

        Optional<PaymentDto> result = paymentService.findHighestSpendByCategoryForYear(category, year);

        assertTrue(result.isEmpty());
    }

    @Test
    void testFindLowestSpendByCategoryForYear_NoTransactions() {
        String category = "Groceries";
        int year = 2025;

        Optional<PaymentDto> result = paymentService.findLowestSpendByCategoryForYear(category, year);

        assertTrue(result.isEmpty());
    }
}
