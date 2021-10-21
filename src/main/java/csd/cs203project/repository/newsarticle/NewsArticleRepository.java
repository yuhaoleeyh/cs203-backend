package csd.cs203project.repository.newsarticle;

import csd.cs203project.model.NewsArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsArticleRepository extends JpaRepository<NewsArticle, Long> {

    List<NewsArticle> findTop5ByOrderByIdDesc();
}
