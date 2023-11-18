package edu.hw6.task5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HackerNewsTest {

    @Test
    @DisplayName("hacker news top stories should return long array with top stories")
    public void hackerNewsTopStories_shouldReturnLongArrayWithTopStories() {
        HackerNews hackerNews = new HackerNews();
        Integer expectedMaxSizeOfList = 500;
        Integer actualExpectedSizeOfList = hackerNews.hackerNewsTopStories()
                                                     .size();

        assertTrue(actualExpectedSizeOfList <= expectedMaxSizeOfList);
        assertTrue(actualExpectedSizeOfList != 0);
    }

    @Test
    @DisplayName("news should return headline of new")
    public void news_shouldReturnNewsHeaderById() {
        HackerNews hackerNews = new HackerNews();
        Integer givenId = 38278753;
        String expectedAnswer = "Show HN: Outsmart AI in a Spy Game";
        String actualAnswer = hackerNews.news(givenId);

        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    @DisplayName("news should return null if new not exist")
    public void news_shouldReturnNullIfIdInvalid() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                                             .uri(URI.create(
                                                     "https://hacker-news.firebaseio.com/v0/maxitem.json"))
                                             .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        String body = httpResponse.body();

        HackerNews hackerNews = new HackerNews();
        Long givenId = Long.parseLong(body) + 1;
        String expectedAnswer = null;
        String actualAnswer = hackerNews.news(givenId);

        assertEquals(expectedAnswer, actualAnswer);
    }

}
