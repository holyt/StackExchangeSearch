package demo.piano.StackExchangeSearch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HttpController {

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces="application/json")
    public String search(@RequestParam(value="searchString", defaultValue="") String searchString, StackExchangeApi stackExchangeApi) {
        return stackExchangeApi.search(searchString);
    }
}