package demo.piano.StackExchangeSearch;

import org.springframework.web.client.RestTemplate;

public class Test {

    public Test() {
    }

    public String getContent() {
        RestTemplate restTemplate = new RestTemplate();
        final String response = restTemplate.getForObject("https://httpbin.org/ip", String.class);


        return response;
    }
}
