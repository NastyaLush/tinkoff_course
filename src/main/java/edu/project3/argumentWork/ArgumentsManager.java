package edu.project3.argumentWork;

import edu.project3.output.OutputType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import lombok.extern.log4j.Log4j2;

@Log4j2 public class ArgumentsManager {

    private String path;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private OutputType reportFormat;

    public ArgumentsManager() {
    }

    public ArgumentsManager(
        String path, LocalDateTime dateFrom, LocalDateTime dateTo, OutputType reportFormat
    ) {
        this.path = path;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.reportFormat = reportFormat;
    }

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        if (this.dateFrom != null) {
            String message = "the from already added";
            log.error(message);
            throw new IllegalArgumentException(message);
        }
        log.debug("parse --from argument");
        this.dateFrom = parseDate(dateFrom);
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

    public LocalDateTime getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        if (this.dateTo != null) {
            String message = "the to already added";
            log.error(message);
            throw new IllegalArgumentException(message);
        }
        log.debug("parse --to argument");
        this.dateTo = parseDate(dateTo);
    }

    public OutputType getReportFormat() {
        return reportFormat;
    }

    public void setReportFormat(String reportFormat) {
        if (this.reportFormat != null) {
            String message = "the --format already added";
            log.error(message);
            throw new IllegalArgumentException(message);
        }
        log.debug("parse --format argument");
        this.reportFormat = switch (reportFormat) {
            case "markdown" -> OutputType.MARKDOWN;
            case "adoc" -> OutputType.ADOC;
            default -> {
                String message = "the --format has wrong type";
                log.error(message);
                throw new IllegalArgumentException(message);
            }
        };
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        if (this.path != null) {
            String message = "the --path argument already added";
            log.error(message);
            throw new IllegalArgumentException(message);
        }
        this.path = path;
    }

    public void validate() {
        if (path == null) {
            String message = "should give --path argument to logfiles";
            log.error(message);
            throw new IllegalArgumentException(message);
        }
        if ((dateFrom != null || dateTo != null) && dateFrom.isAfter(dateTo)) {
            String message = "--from must be earlier than --to";
            log.error(message);
            throw new IllegalArgumentException(message);
        }
    }
}
