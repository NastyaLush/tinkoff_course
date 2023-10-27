package edu.hw3.task6;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
public class Stock implements Comparable<Stock> {

    private final String company;
    private Float cost;


    @Override
    public int compareTo(@NotNull Stock o) {
        return o.getCost()
                .compareTo(this.cost);
    }


}
