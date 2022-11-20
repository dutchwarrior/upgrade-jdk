package org.example;

import java.time.*;
import java.util.*;
import java.util.stream.*;

public class MerchantExample {

    public List<Merchant> findTopMerchants(
            List<Sale> sales, List<Merchant> merchants, int year, Month month) {

        // Local record class
        record MerchantSales(Merchant merchant, double sales) {}

        return merchants.stream()
                .map(merchant -> new MerchantSales(
                        merchant, this.computeSales(sales, merchant, year, month)))
                .sorted((m1, m2) -> Double.compare(m2.sales(), m1.sales()))
                .map(MerchantSales::merchant)
                .collect(Collectors.toList());
    }

    double computeSales(List<Sale> sales, Merchant mt, int yr, Month mo) {
        return sales.stream()
                .filter(s -> s.merchant().name().equals(mt.name()) &&
                        s.date().getYear() == yr &&
                        s.date().getMonth() == mo)
                .mapToDouble(s -> s.value())
                .sum();
    }

}