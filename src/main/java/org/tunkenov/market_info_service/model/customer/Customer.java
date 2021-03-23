package org.tunkenov.market_info_service.model.customer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Customer {
    private final String lastName;
    private final String firstName;
    private final Map<String, Integer> purchases = new HashMap<>();
    private int totalExpenses;

    public Customer(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Map<String, Integer> getPurchases() {
        return purchases;
    }

    public void putPurchase(String productName, int expenses) {
        totalExpenses += expenses;
        purchases.put(productName, expenses);
    }

    public int getTotalExpenses() {
        return totalExpenses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return
                totalExpenses == customer.totalExpenses &&
                        Objects.equals(lastName, customer.lastName) &&
                        Objects.equals(firstName, customer.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, totalExpenses);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
