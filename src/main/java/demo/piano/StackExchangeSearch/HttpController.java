package demo.piano.StackExchangeSearch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HttpController {

    private StackExchangeApi stackExchangeApi;

    public HttpController(StackExchangeApi stackExchangeApi) {
        this.stackExchangeApi = stackExchangeApi;
    }

    @GetMapping("/search")
    @ResponseBody
    public Mono<String> search(@RequestParam(value="searchString", defaultValue="") String searchString) {
        return stackExchangeApi.search(searchString) ;
    }
}