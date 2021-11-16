package csd.cs203project.newsarticle;

import csd.cs203project.model.NewsArticle;
import csd.cs203project.repository.newsarticle.NewsArticleRepository;
import csd.cs203project.service.newsarticle.NewsArticleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NewsArticleServiceTest {
    @Mock
    private NewsArticleRepository newsArticleRepository;

    @InjectMocks
    private NewsArticleServiceImpl newsArticleService;

    @Test
    void addNewsArticleTest_ReturnSavedNewsArticle () {
        //Arrange
        NewsArticle newsArticle = new NewsArticle("title", "description", LocalDate.now(), "www.url.com", "www.imageurl.com");
        when(newsArticleRepository.save(any(NewsArticle.class))).thenReturn(newsArticle);

        //Act
        NewsArticle savedNewsArticle = newsArticleService.addNewsArticle(newsArticle);

        //Assert
        assertNotNull(savedNewsArticle);
        verify(newsArticleRepository).save(newsArticle);
    }
}
