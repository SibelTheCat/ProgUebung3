package at.ac.fhcampuswien.newsapi;

import at.ac.fhcampuswien.newsapi.beans.Article;
import at.ac.fhcampuswien.newsapi.beans.NewsResponse;
import at.ac.fhcampuswien.newsapi.enums.Category;
import at.ac.fhcampuswien.newsapi.enums.Country;
import at.ac.fhcampuswien.newsapi.enums.Endpoint;

import java.util.List;

public class NewsAPIExample {

    public static final String APIKEY = "83fb71c18052435bb3034b2d896bb86f";    //TODO add your api key

    public static void main(String[] args){

        NewsApi newsApi = new NewsApiBuilder()
                .setApiKey(APIKEY)
                .setQ("corona")
                .setEndPoint(Endpoint.TOP_HEADLINES)// example of how to use enums
                .setSourceCountry(Country.at)       // example of how to use enums
                .setSourceCategory(Category.health) // example of how to use enums
                .createNewsApi();

            NewsResponse newsResponse = null;
        try {
            newsResponse = newsApi.getNews();
        } catch (NewsApiException e) {
            System.out.println("Error:" + e.getMessage());
            if (e.getCause() != null) {
                System.out.println("cause: " + e.getCause().getMessage());
            }

            if(newsResponse != null){
                List<Article> articles = newsResponse.getArticles();
                articles.stream().forEach(article -> System.out.println(article.toString()));
            }

        newsApi = new NewsApiBuilder()
                .setApiKey(APIKEY)
                .setQ("corona")
                .setEndPoint(Endpoint.EVERYTHING)
                .setFrom("2020-03-20")
                .setExcludeDomains("Lifehacker.com")
                .createNewsApi();

            NewsResponse newsResponse2 = null;
            try {
                newsResponse2 = newsApi.getNews();
            } catch (NewsApiException f) {
                System.out.println("Error:" + f.getMessage());
                if (e.getCause() != null) {
                    System.out.println("cause: " + f.getCause().getMessage());
                }

        if(newsResponse != null){
            List<Article> articles = newsResponse.getArticles();
            articles.stream().forEach(article -> System.out.println(article.toString()));
        }

    }
}}}
