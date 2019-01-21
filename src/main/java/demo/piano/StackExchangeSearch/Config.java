package demo.piano.StackExchangeSearch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {

    @Bean
    public WebClient.Builder getWebClientBuilder() {
        String baseUri = "http://api.stackexchange.com/2.2/";

        WebClient.Builder webClientBuilder = WebClient.builder();
        webClientBuilder.baseUrl(baseUri);

        return webClientBuilder;
    }
}
