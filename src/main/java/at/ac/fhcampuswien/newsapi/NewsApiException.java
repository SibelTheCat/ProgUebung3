package at.ac.fhcampuswien.newsapi;

public class NewsApiException extends Exception{

        public NewsApiException(String message){
            super(message);
        }
        public NewsApiException(String message, Throwable t){
            super(message, t);
        }
    }

