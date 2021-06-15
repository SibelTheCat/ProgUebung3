package at.ac.fhcampuswien.newsanalyzer.ctrl;

import at.ac.fhcampuswien.newsapi.NewsApi;
import at.ac.fhcampuswien.newsapi.NewsApiException;
import at.ac.fhcampuswien.newsapi.beans.Article;
import at.ac.fhcampuswien.newsapi.beans.NewsResponse;
import at.ac.fhcampuswien.newsapi.beans.Source;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Controller {

	public static final String APIKEY = "83fb71c18052435bb3034b2d896bb86f";

	public void process(NewsApi newsApi) {
		System.out.println("Start process");
		/*NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("corona")
				.setEndPoint(Endpoint.TOP_HEADLINES)// example of how to use enums
				.setSourceCountry(Country.at)       // example of how to use enums
				.setSourceCategory(Category.health) // example of how to use enums
				.createNewsApi();*/

		NewsResponse newsResponse = (NewsResponse) getData(newsApi);

		if (newsResponse != null) {
			List<Article> articles = newsResponse.getArticles();
			articles.stream().forEach(article -> System.out.println(article.toString()));
			System.out.println();

			sortByLengthAndAlphabet(articles);
			numberOfArticles(articles);
			providerWithMostArticles(articles);
			shortestName(articles);
		}
		System.out.println("End process");
	}
	public Object getData(NewsApi newsApi) {
		NewsResponse newsResponse = newsApi.getNews();

		return newsResponse;
	}
		//#a number of articles
		public static void numberOfArticles(List<Article> articles) {

		// auch möglich ohne stream: long amount = articles.size();

			long amount = articles.stream()
							.count();

			System.out.println("The amount of articles is: " + amount);
			System.out.println();
		}
		// #b provider with the most articles
		public static void providerWithMostArticles(List<Article> articles) {

			String provider =
					articles.stream()
							.map(Article::getSource)
							.map(Source::getName)
							.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
							.entrySet().stream().max(Map.Entry.comparingByValue())
							.map(Map.Entry::getKey).orElse(null);

			System.out.println("The provider with the most articles is " + provider);
			System.out.println();
		}
		// #c author with the shortest name
		public static void shortestName(List<Article> articles) {

			String shortestName = articles.stream()
					.filter(article -> article.getAuthor() != null)
					.map(Article::getAuthor)
					.min(Comparator.comparing(String::length))
					.orElse(null);

			System.out.println("The author with the shortest name is: " + shortestName);
			System.out.println();
		}
		// #d first sort by longest article then by alphabet
		public static void sortByLengthAndAlphabet(List<Article> articles) {
			System.out.println("Articles sorted by length and alphabet");
			System.out.println();

					articles.stream()
					.map(Article::getTitle)
					.sorted(Comparator.comparing(String::length).reversed())
					.forEach(System.out::println);
		}

		//TODO implement Error handling

}
