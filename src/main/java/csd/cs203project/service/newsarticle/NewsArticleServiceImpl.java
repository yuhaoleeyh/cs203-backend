package csd.cs203project.service.newsarticle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import csd.cs203project.model.NewsArticle;
import csd.cs203project.repository.newsarticle.NewsArticleRepository;

@Configuration
@EnableScheduling
@Service
public class NewsArticleServiceImpl implements NewsArticleService {
    private NewsArticleRepository newsArticleRepository;

    @Value("${newsApiKey}") 
    private String newsApiKey;

    @Autowired
    public NewsArticleServiceImpl(NewsArticleRepository newsArticleRepository) {
        this.newsArticleRepository = newsArticleRepository;
    }

    @Override
    public List<NewsArticle> getLatestArticles () {
        return newsArticleRepository.findTop5ByOrderByIdDesc();
    }

    @Override
    public void addNewsArticle (NewsArticle newsArticle) {
        newsArticleRepository.save(newsArticle);
    }

    /** Call the News API at 7am every day */
    @Scheduled(cron = "0 0 7 * * *")
    @Override
    public void callNewsAPI() {
        LocalDate fromDate = LocalDate.now().minusDays(2);
        String fromDateString = fromDate.toString();
        
        try {
            String apiUrlString = "https://newsapi.org/v2/everything?";
            apiUrlString += "q=covid&";
            apiUrlString += "from=" + fromDateString + "&";
            apiUrlString += "sortBy=relevancy&";
            apiUrlString += "pageSize=20&";
            apiUrlString += "domains=straitstimes.com,channelnewsasia.com,todayonline.com&";
            apiUrlString += "apiKey=" + newsApiKey;

            URL urlForGetRequest = new URL(apiUrlString);
            String readLine = null;
            HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                } in.close();

                String jsonString = response.toString();

                JSONObject jsonObject = new JSONObject(jsonString);
                handleNewsApiResponse(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleNewsApiResponse(JSONObject jsonObject) throws IOException, JSONException {
        JSONArray jsonArray = jsonObject.getJSONArray("articles");

        // Limit to top 5 articles
        int numArticles = 5;

        for (int i = 0; i < numArticles; i++) {
            JSONObject articleObject = jsonArray.getJSONObject(i);

            String title = articleObject.getString("title");
            if (newsArticleRepository.existsByTitle(title))
                continue;
            
            String description = articleObject.getString("description");
            String url = articleObject.getString("url");
            String imageUrl = articleObject.getString("urlToImage");

            String dateString = articleObject.getString("publishedAt").substring(0,10);
            LocalDate date = LocalDate.parse(dateString);

            NewsArticle article = new NewsArticle(title, description, date, url, imageUrl);
            addNewsArticle(article);
            System.out.println("Added article: " + article);
        }
    }
}
