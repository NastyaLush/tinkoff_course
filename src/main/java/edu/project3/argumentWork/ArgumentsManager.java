package edu.project3.argumentWork;

import edu.project3.output.OutputType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Getter @Log4j2 public class ArgumentsManager {

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

    public static ArgumentsManager parseArgs(String... args) {
        if (args.length % 2 != 0) {
            String message = "the count of arguments must be even";
            log.error(message);
            throw new IllegalArgumentException(message);
        }
        ArgumentsManager arguments = new ArgumentsManager();

        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "--path" -> arguments.setPath(args[i + 1]);
                case "--from" -> arguments.setDateFrom(args[i + 1]);
                case "--to" -> arguments.setDateTo(args[i + 1]);
                case "--format" -> arguments.setReportFormat(args[i + 1]);
                default -> {
                    String message = "there is this option";
                    log.error(message);
                    throw new IllegalArgumentException(message);
                }
            }
        }
        return arguments;
    }

    public void setPath(String path) {
        if (this.path != null) {
            String message = "the --path argument already added";
            log.error(message);
            throw new IllegalArgumentException(message);
        }
        this.path = path;
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

    public void setDateTo(String dateTo) {
        if (this.dateTo != null) {
            String message = "the to already added";
            log.error(message);
            throw new IllegalArgumentException(message);
        }
        log.debug("parse --to argument");
        this.dateTo = parseDate(dateTo);
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

    private LocalDateTime parseDate(String date) {
        try {
            return LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (DateTimeParseException e) {
            String message = "given date is not format of 2011-12-03T10:15:30";
            log.error(message);
            throw new IllegalArgumentException(message);
        }
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

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArgumentsManager that)) {
            return false;
        }
        return Objects.equals(path, that.path) && Objects.equals(dateFrom, that.dateFrom) && Objects.equals(
            dateTo,
            that.dateTo
        ) && reportFormat == that.reportFormat;
    }

    @Override public int hashCode() {
        return Objects.hash(path, dateFrom, dateTo, reportFormat);
    }
}
