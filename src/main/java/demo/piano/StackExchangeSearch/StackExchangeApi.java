package demo.piano.StackExchangeSearch;

import demo.piano.StackExchangeSearch.domain.SearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.ObjectMapper;


import reactor.core.publisher.Mono;

@Service
public class StackExchangeApi {

    private String searchUri = "search?order={order}&sort={sort}&intitle={searchString}&site={site}&page={page}";
    private WebClient webClient;
    private ObjectMapper objectMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public StackExchangeApi(WebClient.Builder WebClientBuilder, ObjectMapper objectMapper)
    {
        this.webClient = WebClientBuilder.build();
        this.objectMapper = objectMapper;
    }

    private SearchResult convertStringToSearchResult(String body) {
        SearchResult searchResult = new SearchResult();
        try {
            searchResult = objectMapper.readValue(body, SearchResult.class);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            throw new IllegalArgumentException(exception.getMessage());
        }

        return  searchResult;
    }

    public Mono<SearchResult>
    search(String searchString, Integer page) {
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
                .bodyToMono(String.class).map(this::convertStringToSearchResult);
    }
}
