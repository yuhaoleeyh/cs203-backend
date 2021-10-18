package csd.cs203project.service.newsarticle;

import csd.cs203project.model.NewsArticle;
import csd.cs203project.repository.newsarticle.NewsArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsArticleServiceImpl implements NewsArticleService {
    private NewsArticleRepository newsArticleRepository;

    @Autowired
    public NewsArticleServiceImpl(NewsArticleRepository newsArticleRepository) {
        this.newsArticleRepository = newsArticleRepository;
    }

    @Override
    public List<NewsArticle> listNewsArticles () {
        return newsArticleRepository.findAll();
    }

    @Override
    public void addNewsArticle (NewsArticle newsArticle) {
        newsArticleRepository.save(newsArticle);
    }
}
