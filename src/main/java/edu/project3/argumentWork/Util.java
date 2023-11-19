package edu.project3.argumentWork;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Util {

    private final static Pattern PATTERN_FIRST_TYPE = Pattern.compile("^((\\w*/)*)(\\w*\\*)$");
    private final static Pattern PATTERN_SECOND_TYPE = Pattern.compile("^((\\w*/)*)\\*{2}((/[\\w\\.]*)*)$");
    private final static Integer PATTERN_GROUP_NUMBER = 3;

    private Util() {
    }

    public static Stream<String> getStream(String pathArgument) throws IOException, InterruptedException {
        if (urlValidator(pathArgument)) {
            log.info("--path is url");
            return getUrlStream(pathArgument);
        } else if (pathArgument.contains("*")) {
            log.info("--path is pattern directory");
            return getPatternStream(pathArgument);
        } else {
            Path path = Path.of(pathArgument);
            if (Files.isRegularFile(path)) {
                log.info("--path is file");
                return getFileStream(pathArgument);
            } else if (Files.isDirectory(path)) {
                log.info("--path is simple directory");
                return getDirectoryStream(pathArgument);
            }
        }
        log.error("there is no pattern or path for --path argument");
        throw new IllegalArgumentException();
    }

    protected static boolean urlValidator(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (URISyntaxException | MalformedURLException exception) {
            return false;
        }
    }

    protected static Stream<String> getUrlStream(String pathArgument) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(URI.create(pathArgument))
                                         .build();
        log.info("send url request");
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        log.info("receive response");
        return Arrays.stream(response.body()
                                     .split("\n"));
    }

    protected static Stream<String> getPatternStream(String pathArgument) throws IOException {

        Matcher matcher = PATTERN_FIRST_TYPE.matcher(pathArgument);
        if (matcher.matches()) {
            return getStreamFirstPattern(matcher);
        }

        matcher = PATTERN_SECOND_TYPE.matcher(pathArgument);
        if (matcher.matches()) {
            return getStreamSecondPattern(matcher);
        }
        log.error("There is no pattern for this path");
        throw new IllegalArgumentException();

    }

    protected static Stream<String> getFileStream(String pathArgument) throws IOException {
        try (Stream<String> stream = Files.lines(Path.of(pathArgument))) {
            return stream.toList()
                         .stream();
        }
    }

    protected static Stream<String> getDirectoryStream(String pathArgument) throws IOException {
        try (Stream<Path> stream = Files.walk(Path.of(pathArgument), 1)) {
            return stream.filter(Files::isRegularFile)
                         .flatMap((path -> {
                             try {
                                 return Files.lines(path)
                                             .toList()
                                             .stream();
                             } catch (IOException e) {
                                 throw new RuntimeException(e);
                             }
                         }))
                         .toList()
                         .stream();
        }
    }

    protected static Stream<String> getStreamFirstPattern(Matcher matcher) throws IOException {
        log.info("receive first pattern");

        Path dir = Path.of(matcher.group(1));
        String startPathPattern = matcher.group(PATTERN_GROUP_NUMBER)
                                         .split("\\*")[0];

        try (Stream<Path> stream = Files.walk(dir, 1)) {
            return getStreamFromPathStream(stream, (path) -> path.getFileName()
                                                                 .toString()
                                                                 .startsWith(startPathPattern));
        }
    }

    protected static Stream<String> getStreamSecondPattern(Matcher matcher) throws IOException {
        log.info("receive second pattern");

        Path dir = Path.of(matcher.group(1));
        String endPathPattern = Path.of(matcher.group(PATTERN_GROUP_NUMBER))
                                    .toString();
        try (Stream<Path> stream = Files.walk(dir)) {
            return getStreamFromPathStream(stream, (path -> path.toString()
                                                                .endsWith(endPathPattern)));
        }
    }

    private static Stream<String> getStreamFromPathStream(Stream<Path> stream, PredicatePath predicate) {
        return stream.filter(Files::isRegularFile)
                     .filter(predicate::test)
                     .flatMap((path -> {
                         log.debug("current file for flatmap is " + path);
                         try {
                             return Files.lines(path);
                         } catch (IOException e) {
                             throw new RuntimeException(e);
                         }
                     }))
                     .toList()
                     .stream();
    }

}
