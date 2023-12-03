package edu.project4;

import edu.project4.structures.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomTest {

    @Test
    public void testRandomInt() {
        int from = 5;
        int till = 10;

        int result = Random.randomInt(from, till);

        assertTrue(result >= from && result <= till, "Result is not within the specified range");
    }

    @Test
    public void testRandomPoint() {
        double fromX = 1.0;
        double tillX = 5.0;
        double fromY = 2.0;
        double tillY = 6.0;

        Point result = Random.randomPoint(fromX, tillX, fromY, tillY);

        assertTrue(result.x() >= fromX && result.x() <= tillX, "X coordinate is not within the specified range");
        assertTrue(result.y() >= fromY && result.y() <= tillY, "Y coordinate is not within the specified range");
    }

    @Test
    public void testRandomDouble() {
        double from = 2.5;
        double till = 7.5;

        double result = Random.randomDouble(from, till);

        assertTrue(result >= from && result <= till, "Result is not within the specified range");
    }

    @Test
    public void testRandomCollection() {
        Random.RandomCollection<String> randomCollection = new Random.RandomCollection<>();
        randomCollection.add(0.2, "A");
        randomCollection.add(0.5, "B");
        randomCollection.add(0.3, "C");

        // Test multiple iterations to account for randomness
        for (int i = 0; i < 100; i++) {
            String result = randomCollection.next();
            assertTrue(
                result.equals("A") || result.equals("B") || result.equals("C"),
                "Invalid result from RandomCollection"
            );
        }
    }
}

