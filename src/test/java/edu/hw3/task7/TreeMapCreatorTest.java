package edu.hw3.task7;

import edu.hw3.task6.Stock;
import edu.hw3.task6.StockMarketImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeMapCreatorTest {

    @Test
    @DisplayName("should correctly work with null key")
    public void getExtendedTreeMap_shouldReturnTreeWithAbilityOfKeyNull() {
        TreeMap<String, String> tree = new TreeMapCreator().getExtendedTreeMap();
        tree.put(null, "test");
        assertThat(tree.containsKey(null)).isTrue();
    }

}
