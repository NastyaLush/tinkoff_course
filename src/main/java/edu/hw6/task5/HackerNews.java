package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class HackerNews {

    public List<Long> hackerNewsTopStories() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
                                         .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return Arrays.stream(response.body()
                                         .replace("[", "")
                                         .replace("]", "")
                                         .split(","))
                         .map(Long::parseLong)
                         .collect(Collectors.toList());
        } catch (IOException | InterruptedException e) {
            log.error(e);
        }
        return null;

    }

    public String news(long id) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                                             .uri(URI.create(
                                                     "https://hacker-news.firebaseio.com/v0/item/" + id + ".json"))
                                             .build();
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String body = httpResponse.body();
            Pattern pattern = Pattern.compile(".*\"title\":\"([^\"]*)\".*");
            Matcher matcher = pattern.matcher(body);
            if (matcher.find()) {
                return matcher.group(1);
            } else {
                return null;
            }
        } catch (IOException | InterruptedException e) {
            log.error(e);
        }
        return null;
    }

}
