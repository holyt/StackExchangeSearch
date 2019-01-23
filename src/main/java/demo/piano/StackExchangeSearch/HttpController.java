package demo.piano.StackExchangeSearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.piano.StackExchangeSearch.domain.SearchResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class HttpController {

    private StackExchangeApi stackExchangeApi;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public HttpController(StackExchangeApi stackExchangeApi, ObjectMapper objectMapper) {
        this.stackExchangeApi = stackExchangeApi;
    }

    @GetMapping("/search")
    @ResponseBody
    public Mono<SearchResult> search(@RequestParam(value="searchString", defaultValue="") String searchString,
                               @RequestParam(value="page", defaultValue="1") Integer page) {
        logger.info("Request for '" + searchString + "' on page " + page );
        return stackExchangeApi.search(searchString, page);
    }
}