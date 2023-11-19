package edu.project3.argumentWork;

import edu.project3.output.OutputType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ArgumentsManager {

    private String argumentPath;
    private LocalDateTime argumentFrom;
    private LocalDateTime argumentTo;
    private OutputType argumentFormat;

    public LocalDateTime getArgumentFrom() {
        return argumentFrom;
    }

    public void setArgumentFrom(String argumentFrom) {
        if (this.argumentFrom != null) {
            log.error("the from already added");
            throw new IllegalArgumentException();
        }
        log.debug("parse --from argument");
        this.argumentFrom = parseDate(argumentFrom);
    }

    private LocalDateTime parseDate(String date) {
        try {
            return LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (DateTimeParseException e) {
            log.debug("given date in not format of 2011-12-03T10:15:30");
        }
        try {
            return LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            log.debug("given date in not format of 2011-12-03");
        }
        return null;
    }

    public LocalDateTime getArgumentTo() {
        return argumentTo;
    }

    public ArgumentsManager() {
    }

    public void setArgumentTo(String argumentTo) {
        if (this.argumentTo != null) {
            log.error("the to already added");
            throw new IllegalArgumentException();
        }
        log.debug("parse --to argument");
        this.argumentTo = parseDate(argumentTo);
    }

    public ArgumentsManager(String argumentPath,
                            LocalDateTime argumentFrom,
                            LocalDateTime argumentTo,
                            OutputType argumentFormat) {
        this.argumentPath = argumentPath;
        this.argumentFrom = argumentFrom;
        this.argumentTo = argumentTo;
        this.argumentFormat = argumentFormat;
    }

    public OutputType getArgumentFormat() {
        return argumentFormat;
    }

    public void setArgumentFormat(String argumentFormat) {
        if (this.argumentFormat != null) {
            log.error("the --format already added");
            throw new IllegalArgumentException();
        }
        log.debug("parse --format argument");
        this.argumentFormat = switch (argumentFormat) {
            case "markdown" -> OutputType.MARKDOWN;
            case "adoc" -> OutputType.ADOC;
            default -> {
                log.error("the --format has wrong type");
                throw new IllegalArgumentException();
            }
        };
    }

    public String getArgumentPath() {
        return argumentPath;
    }

    public void setArgumentPath(String argumentPath) {
        if (this.argumentPath != null) {
            log.error("the --path argument already added");
            throw new IllegalArgumentException();
        }
        this.argumentPath = argumentPath;
    }

    public void validate() {
        if (argumentPath == null) {
            log.error("should give --path argument to logfiles");
            throw new IllegalArgumentException();
        }
    }
}
