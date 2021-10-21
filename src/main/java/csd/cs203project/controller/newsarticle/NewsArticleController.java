package csd.cs203project.controller.newsarticle;

import csd.cs203project.model.NewsArticle;
import csd.cs203project.service.newsarticle.NewsArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class NewsArticleController {
    private NewsArticleService newsArticleService;

    @Autowired
    public NewsArticleController(NewsArticleService newsArticleService) {
        this.newsArticleService = newsArticleService;
    }

    @PostMapping("/newsArticle")
    public void addNewsArticle (@RequestBody NewsArticle newsArticle) {
        newsArticleService.addNewsArticle(newsArticle);
    }

    @GetMapping("/newsArticle")
    public List<NewsArticle> getNewsArticles() {
        return newsArticleService.getLatestArticles();
    }

    @GetMapping("/callNewsAPI")
    public void callNewsAPI() {
        newsArticleService.callNewsAPI();
    }
}
