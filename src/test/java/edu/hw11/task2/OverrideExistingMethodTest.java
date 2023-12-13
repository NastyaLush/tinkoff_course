package edu.hw11.task2;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OverrideExistingMethodTest {

    public static class ArithmeticUtilsOverriding {

        public int sum(int a, int b) {
            return a * b;
        }
    }

    @Test
    public void sum_shouldExecuteAnotherOperation()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? extends ArithmeticUtils> dynamicType = new ByteBuddy()
            .subclass(ArithmeticUtils.class)
            .method(named("sum"))
            .intercept(MethodDelegation.to(new ArithmeticUtilsOverriding()))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.UsingLookup.of(MethodHandles
                                                                                       .privateLookupIn(
                                                                                           ArithmeticUtils.class,
                                                                                           MethodHandles.lookup()
                                                                                       )))
            .getLoaded();
        assertEquals(
            dynamicType.getDeclaredConstructor().newInstance().sum(4, 8),
            4 * 8
        );

    }
}
