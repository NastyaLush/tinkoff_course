package edu.hw2.task4;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {

    private static Stream<Arguments> callingInfoProvider() {
        return Stream.of(Arguments.of(new Exception()), Arguments.of(new NullPointerException()),
            Arguments.of(new IllegalArgumentException()), Arguments.of(new StackOverflowError()),
            Arguments.of(new Error())
        );
    }

    @ParameterizedTest(name = "Test {index} throwable {0}")
    @MethodSource("callingInfoProvider")
    public void callingInfo_mustReturnCallingInfoRecordWithClassNameAndMethodName(Throwable throwable) {
        CallingInfo callingInfo = Main.callingInfo(throwable);
        System.out.println(callingInfo);
        assertThat(Main.callingInfo(throwable) instanceof CallingInfo);
    }
}
