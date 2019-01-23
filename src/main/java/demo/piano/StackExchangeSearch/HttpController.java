package demo.piano.StackExchangeSearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.piano.StackExchangeSearch.domain.SearchResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HttpController {

    private StackExchangeApi stackExchangeApi;

    public HttpController(StackExchangeApi stackExchangeApi, ObjectMapper objectMapper) {
        this.stackExchangeApi = stackExchangeApi;
    }

    @GetMapping("/search")
    @ResponseBody
    public Mono<SearchResult> search(@RequestParam(value="searchString", defaultValue="") String searchString,
                               @RequestParam(value="page", defaultValue="1") Integer page) {
        return stackExchangeApi.search(searchString, page);
    }
}