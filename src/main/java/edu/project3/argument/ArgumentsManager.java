package edu.project3.argument;

import edu.project3.output.OutputType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ArgumentsManager {

    private String pathArgument;
    private LocalDateTime from;
    private LocalDateTime to;
    private OutputType format;

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(String from) {
        if (this.from != null) {
            log.error("the from already added");
            throw new IllegalArgumentException();
        }
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(String to) {
        if (this.to != null) {
            log.error("the to already added");
            throw new IllegalArgumentException();
        }
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public OutputType getFormat() {
        return format;
    }

    public void setFormat(String format) {
        if (this.format != null) {
            log.error("the format already added");
            throw new IllegalArgumentException();
        }
        this.format = switch (format) {
            case "markdown" -> OutputType.MARKDOWN;
            case "adoc" -> OutputType.ADOC;
            default -> {
                log.error("format has wrong type");
                throw new IllegalArgumentException();
            }
        };
    }

    public String getPathArgument() {
        return pathArgument;
    }

    public void setPathArgument(String pathArgument) {
        if (this.pathArgument != null) {
            log.error("the directory already added");
            throw new IllegalArgumentException();
        }
        this.pathArgument = pathArgument;
    }

    public void validate() {
        if (pathArgument == null) {
            log.error("should give path to logfiles");
            throw new IllegalArgumentException();
        }
    }
}
