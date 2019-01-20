package demo.piano.StackExchangeSearch;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


public class StackExchangeApi {

    public String search(String searchString) {

        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                HttpClientBuilder.create().build());
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

        String response = restTemplate.getForObject("http://api.stackexchange.com/2.2/search?order=desc&sort=activity&intitle={searchString}}&site=stackoverflow", String.class, searchString);

        return response;
    }
}
