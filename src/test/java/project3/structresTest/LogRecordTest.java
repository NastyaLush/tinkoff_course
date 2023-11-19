package project3.structresTest;

import edu.project3.structures.LogRecord;
import edu.project3.structures.URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.stream.Stream;

public class LogRecordTest {

    public static Stream<Arguments> logRecordProvider() {
        return Stream.of(
                Arguments.of(
                        "93.180.71.3 - - [17/May/2030:08:05:23 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
                        new LogRecord("93.180.71.3", "-", OffsetDateTime.of(
                                2030, 5, 17, 8, 5, 23, 0, ZoneOffset.of("+0000")
                        ), new URL("GET", "/downloads/product_1", "HTTP/1.1"),
                                "304", 0L, "-", "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("logRecordProvider")
    public void parse_shouldCorrectlyParseRecord(String givenString, LogRecord expectedLogRecord) {
        LogRecord actualLogRecord = LogRecord.parse(givenString);

        assertEquals(expectedLogRecord, actualLogRecord);
    }

}
