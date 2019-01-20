package demo.piano.StackExchangeSearch;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


public class StackExchangeApi {

    private String baseUri;
    private RestTemplate restTemplate;

    public StackExchangeApi()
    {
        baseUri = "http://api.stackexchange.com/2.2/";
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                HttpClientBuilder.create().build());
        restTemplate = new RestTemplate(clientHttpRequestFactory);

    }

    public String search(String searchString) {
        String order = "desc";
        String sort = "activity";
        String site = "stackoverflow";

        return restTemplate.getForObject(
                searchUriTemplate(),
                String.class,
                order,
                sort,
                searchString,
                site
        );
    }

    private String searchUriTemplate()
    {
        return baseUri.concat("search?order={order}&sort={sort}&intitle={searchString}&site={site}");
    }
}
