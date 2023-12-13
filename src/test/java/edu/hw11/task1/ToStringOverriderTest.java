package edu.hw11.task1;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToStringOverriderTest {

    @Test
    public void toString_shouldReturnHelloByteBuddy() throws InstantiationException, IllegalAccessException {
        String expectedAnswer = "Hello, ByteBuddy!";
        Object createdObject = new ByteBuddy()
            .subclass(Object.class)
            .name("example.Type")
            .method(named("toString")).intercept(FixedValue.value(expectedAnswer))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded()
            .newInstance();
        String actualAnswer = createdObject.toString();

        assertEquals(expectedAnswer, actualAnswer);
    }
}
