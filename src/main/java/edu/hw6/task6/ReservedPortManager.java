package edu.hw6.task6;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ReservedPortManager {

    private final Integer groupOfService = 3;

    private final HashMap<Port, String> reservedPort = new HashMap();

    public HashMap<Port, String> getReservedPort() {
        if (reservedPort.isEmpty()) {
            String html = getHtmlOfPage();
            Pattern pattern = Pattern.compile("<tr><td>(\\d*)</td><td>([TCPUDP,]*)</td><td>(.*?)</td></tr>");
            Matcher matcher = pattern.matcher(html);
            while (matcher.find()) {
                if (matcher.group(2)
                           .contains("TCP")) {
                    reservedPort.put(
                            new Port(ProtocolType.TCP, Integer.parseInt(matcher.group(1))),
                            matcher.group(groupOfService));
                }
                if (matcher.group(2)
                           .contains("UDP")) {
                    reservedPort.put(
                            new Port(ProtocolType.UDP, Integer.parseInt(matcher.group(1))),
                            matcher.group(groupOfService));
                }
            }
        }
        return reservedPort;
    }

    private String getHtmlOfPage() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(URI.create("https://developer.donnoval.ru/ports/"))
                                         .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            log.error(e);
        }
        return null;
    }
}
