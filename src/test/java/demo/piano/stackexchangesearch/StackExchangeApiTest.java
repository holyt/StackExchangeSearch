package demo.piano.stackexchangesearch;

import demo.piano.stackexchangesearch.domain.SearchResult;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.Buffer;
import org.apache.http.HttpHeaders;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class StackExchangeApiTest {

    private MockWebServer server;

    private StackExchangeApi stackExchangeApi;

    @Before
    public void setup() throws IOException {
        this.server = new MockWebServer();
        server.start();
        String baseUrl = this.server.url("").toString();
        WebClient.Builder webClientBuilder = WebClient.builder().baseUrl(baseUrl);

        stackExchangeApi = new StackExchangeApi(webClientBuilder);
    }

    @After
    public void shutdown() throws Exception {
        this.server.shutdown();
    }

    @Test(expected = IllegalArgumentException.class)
    public void badResponse() {
        server.enqueue(new MockResponse().setResponseCode(200)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setBody("hello, world!")
        );
        stackExchangeApi.search("t", 1).block();
    }

    @Test
    public void goodResponse() throws InterruptedException, IOException {
        try (InputStream io = getClass().getResourceAsStream("good.json")) {
            server.enqueue(new MockResponse().setResponseCode(200)
                    .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .setBody(new Buffer().readFrom(io))
            );
        }

        SearchResult searchResult =  stackExchangeApi.search("t", 1).block();

        assertEquals(300, searchResult.getQuotaMax().intValue());
        assertEquals(291, searchResult.getQuotaRemaining().intValue());
        assertEquals(true, searchResult.getHasMore());
        assertEquals(30, searchResult.getItems().length);
        assertEquals("Zibbobz", searchResult.getItems()[0].getOwner().getDisplayName());
        assertEquals("Java exception handling in Tomcat non-container thread",
                searchResult.getItems()[29].getTitle());
        assertEquals("/search?order=desc&sort=activity&intitle=t&site=stackoverflow&page=1",
                server.takeRequest().getPath());
    }

}