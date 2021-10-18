package csd.cs203project.service.newsarticle;

import csd.cs203project.model.NewsArticle;

import java.util.List;

public interface NewsArticleService {
    /**
     * @return list of NewsArticle from db
     */
    List<NewsArticle> listNewsArticles();

    /**
     * Adds NewsArticle into db
     */
    void addNewsArticle (NewsArticle newsArticle);
}
