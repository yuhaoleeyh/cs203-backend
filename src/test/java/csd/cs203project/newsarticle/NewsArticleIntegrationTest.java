package csd.cs203project.newsarticle;

import csd.cs203project.model.NewsArticle;
import csd.cs203project.repository.newsarticle.NewsArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NewsArticleIntegrationTest {
    @LocalServerPort
    private int port;

    private final String baseUrl = "http://localhost:";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private NewsArticleRepository newsArticleRepository;

    
    /** 
     * Test for Get News Articles
     * @throws Exception
     */
    @Test
    public void getNewsArticles_Success() throws Exception {
        URI uri = new URI(baseUrl + port + "/newsArticle");

        ResponseEntity<NewsArticle[]> result = restTemplate.getForEntity(uri, NewsArticle[].class);
        NewsArticle[] newsArticles = result.getBody();

        assertEquals(200, result.getStatusCode().value());
        assertEquals(5, newsArticles.length);
    }
}
