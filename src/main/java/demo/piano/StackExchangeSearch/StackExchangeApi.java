package demo.piano.StackExchangeSearch;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class StackExchangeApi {

    private String searchUri = "search?order={order}&sort={sort}&intitle={searchString}&site={site}&page={page}";
    private WebClient webClient;

    public StackExchangeApi(WebClient.Builder WebClientBuilder)
    {
        webClient = WebClientBuilder.build();
    }

    public Mono<String> search(String searchString, Integer page) {
        String order = "desc";
        String sort = "activity";
        String site = "stackoverflow";
        return webClient.get()
                .uri(
                        searchUri,
                        order,
                        sort,
                        searchString,
                        site,
                        page
                ).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
    }
}
