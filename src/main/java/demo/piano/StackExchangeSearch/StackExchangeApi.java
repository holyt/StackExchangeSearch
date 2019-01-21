package demo.piano.StackExchangeSearch;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StackExchangeApi {

    private String baseUri;
    private String searchUriTemplate;
    private RestTemplate restTemplate;


    public StackExchangeApi()
    {
        baseUri = "http://api.stackexchange.com/2.2/";
        searchUriTemplate = generateSearchUriTemplate();
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                HttpClientBuilder.create().build());
        restTemplate = new RestTemplate(clientHttpRequestFactory);

    }

    private String generateSearchUriTemplate()
    {
        return baseUri.concat("search?order={order}&sort={sort}&intitle={searchString}&site={site}");
    }

    public String search(String searchString) {
        String order = "desc";
        String sort = "activity";
        String site = "stackoverflow";

        return restTemplate.getForObject(
                searchUriTemplate,
                String.class,
                order,
                sort,
                searchString,
                site
        );
    }

}
