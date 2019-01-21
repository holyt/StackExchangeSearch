package demo.piano.StackExchangeSearch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

@Controller
public class HttpController {

    @GetMapping("/search")
    @ResponseBody
    public Mono<String> search(@RequestParam(value="searchString", defaultValue="") String searchString,
                               StackExchangeApi stackExchangeApi) {
        return stackExchangeApi.search(searchString) ;
    }
}