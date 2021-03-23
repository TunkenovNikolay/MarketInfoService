package org.tunkenov.market_info_service.model.criterias.stat;

import java.time.LocalDate;

public class DateInterval {
    private LocalDate startDate;
    private LocalDate endDate;

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getQuery() {
        return "SELECT purchases.customer_id, c.last_name, c.first_name, p.name, SUM(p.price) AS expenses\n" +
                "FROM purchases\n" +
                "LEFT JOIN products AS p\n" +
                "\tON purchases.product_id = p.id\n" +
                "LEFT JOIN customers AS c\n" +
                "\tON purchases.customer_id = c.id\n" +
                "WHERE purchases.purchase_date BETWEEN DATE('" + startDate + "')AND DATE('" + endDate + "') " +
                "AND EXTRACT(ISODOW FROM purchases.purchase_date) NOT IN (6,7)\n" +
                "GROUP BY c.last_name, c.first_name, p.name, purchases.customer_id";
    }
}
