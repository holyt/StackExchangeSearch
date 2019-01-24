package demo.piano.StackExchangeSearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import demo.piano.StackExchangeSearch.domain.Question;
import demo.piano.StackExchangeSearch.domain.QuestionOwner;
import demo.piano.StackExchangeSearch.domain.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@WebFluxTest(HttpController.class)

public class StackExchangeApiTest {

        @Autowired
        private WebTestClient webClient;

        @MockBean
        private StackExchangeApi stackExchangeApi;

        @Autowired
        ObjectMapper objectMapper;

        @Test
        public void testSearch() {
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

            QuestionOwner owner = new QuestionOwner();
            owner.setDisplayName("ownerName");


            Question question = new Question();

            question.setIsAnswered(true);
            question.setAnswerCount(3);
            question.setCreationDate(1548279288);
            question.setLink("asd.asd");
            question.setOwner(owner);
            question.setTitle("title");

            Question[] items = new  Question[] {question};

            SearchResult searchResult = new SearchResult();
            searchResult.setItems(items);
            searchResult.setHasMore(true);

            Mono<SearchResult> searchResultMono = Mono.just(searchResult);


            Mockito.when(this.stackExchangeApi.search("test", 1))
                    .thenReturn(searchResultMono);
            this.webClient.get().uri("/search?searchString=test")
                    .exchange()
                    .expectStatus()
                    .isOk()
                    .expectBody()
                    .jsonPath("$.has_more").isEqualTo(true)
                    .jsonPath("$.items").isArray()
                    .jsonPath("$.items[0]").isNotEmpty()
                    .jsonPath("$.items[0].creation_date").isEqualTo(1548279288)
                    .jsonPath("$.items[0].is_answered").isEqualTo(true)
                    .jsonPath("$.items[0].answer_count").isEqualTo(3)
                    .jsonPath("$.items[0].link").isEqualTo("asd.asd")
                    .jsonPath("$.items[0].title").isEqualTo("title")
                    .jsonPath("$.items[0].owner.display_name").isEqualTo("ownerName");
        }
}
