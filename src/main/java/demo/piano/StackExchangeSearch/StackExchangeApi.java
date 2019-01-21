package demo.piano.StackExchangeSearch;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class StackExchangeApi {

    private String baseUri = "http://api.stackexchange.com/2.2/";
    private String searchUri = "search?order={order}&sort={sort}&intitle={searchString}&site={site}";
    private WebClient client = WebClient.create(baseUri);


    public Mono<String> search(String searchString) {
        String order = "desc";
        String sort = "activity";
        String site = "stackoverflow";

        return client.get()
                .uri(
                        searchUri,
                        order,
                        sort,
                        searchString,
                        site
                ).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
    }
}
