package edu.project3.structures;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.log4j.Log4j2;

@Log4j2
public record LogRecord(
        String ipClient,
        String clientName,
        OffsetDateTime dateOfRequest,
        URL url,
        String httpCodeStatus,
        Long sizeOfResponse,
        String referer,
        String userAgent
) {

    private static final Integer IP_CLIENT_GROUP_NUMBER = 1;
    private static final Integer CLIENT_NAME_GROUP_NUMBER = 2;
    private static final Integer DATA_OF_REQUEST_GROUP_NUMBER = 3;
    private static final Integer URL_METHOD_GROUP_NUMBER = 4;
    private static final Integer URL_RESOURCE_GROUP_NUMBER = 5;
    private static final Integer URL_HTTP_PROTOCOL_VERSION_GROUP_NUMBER = 6;
    private static final Integer HTTP_CODE_STATUS_GROUP_NUMBER = 7;
    private static final Integer SIZE_OF_RESPONSE_GROUP_NUMBER = 8;
    private static final Integer REFERER_GROUP_NUMBER = 9;
    private static final Integer USER_AGENT_GROUP_NUMBER = 10;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z");

    public static LogRecord parse(String logValue) {
        Pattern pattern = Pattern.compile(
                "^([\\w\\.:]*) - ([\\w-]*) \\[(.*)\\] \"(\\w{3,7}) "
                        + "([/\\w]*) ([/\\w\\.]*)\" (\\d{3}) (\\d*) \"(.*)\" \"(.*)\"$"
        );
        Matcher matcher = pattern.matcher(logValue);
        if (matcher.matches()) {
            return new LogRecord(
                    matcher.group(IP_CLIENT_GROUP_NUMBER),
                    matcher.group(CLIENT_NAME_GROUP_NUMBER),
                    OffsetDateTime.parse(matcher.group(DATA_OF_REQUEST_GROUP_NUMBER), DATE_TIME_FORMATTER),
                    new URL(
                            matcher.group(URL_METHOD_GROUP_NUMBER),
                            matcher.group(URL_RESOURCE_GROUP_NUMBER),
                            matcher.group(URL_HTTP_PROTOCOL_VERSION_GROUP_NUMBER)
                    ),
                    matcher.group(HTTP_CODE_STATUS_GROUP_NUMBER),
                    Long.parseLong(matcher.group(SIZE_OF_RESPONSE_GROUP_NUMBER)),
                    matcher.group(REFERER_GROUP_NUMBER),
                    matcher.group(USER_AGENT_GROUP_NUMBER)
            );
        }
        log.error("impossible parse log " + logValue);
        throw new IllegalArgumentException();
    }
}


