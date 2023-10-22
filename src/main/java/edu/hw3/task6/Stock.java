package edu.hw3.task6;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class Stock implements Comparable<Stock> {

    private final String company;
    private Float cost;

    public Stock(String company, Float cost) {
        this.company = company;
        this.cost = cost;
    }

    @Override
    public int compareTo(@NotNull Stock o) {
        return o.getCost().compareTo(this.cost);
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, cost);
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Stock stock = (Stock) o;
        return Objects.equals(cost, stock.cost);
    }

    @Override public String toString() {
        return "Stock{" +
            "company='" + company + '\'' +
            ", cost=" + cost +
            '}';
    }
}
