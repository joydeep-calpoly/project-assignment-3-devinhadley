package org.newsparser;

import org.newsparser.parsers.BaseJsonParser;
import org.newsparser.parsers.NewsResponseJsonParser;
import org.newsparser.parsers.SimpleNewsResponseJsonParser;
import org.newsparser.sources.ArticleSource;
import org.newsparser.sources.FileJsonSource;
import org.newsparser.sources.SourceFormat;
import org.newsparser.sources.URLJsonSource;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Main.class.getName());
        logger.setLevel(Level.WARNING);
        logger.setUseParentHandlers(false);
        logger.addHandler(new ConsoleHandler());

        // NewsResponse from url.
        System.out.println("NewsResponse from URL.");
        SourceFormat.ArticleSourceType source = SourceFormat.ArticleSourceType.WEB;
        SourceFormat.ArticleFormatType format = SourceFormat.ArticleFormatType.NEWS_RESPONSE;
        SourceFormat sourceFormat = new SourceFormat(source, format);
        ArticleSource articleSource = new URLJsonSource("http://newsapi.org/v2/top-headlines?country=us&apiKey=cff43eceb1d44ccf957632ba39fbe4d5");
        BaseJsonParser parser = new NewsResponseJsonParser(articleSource, logger);
        sourceFormat.accept(parser);

        // NewsResponse from file.
        System.out.println("NewsResponse from file.");
        SourceFormat.ArticleSourceType source2 = SourceFormat.ArticleSourceType.FILE_JSON;
        SourceFormat.ArticleFormatType format2 = SourceFormat.ArticleFormatType.NEWS_RESPONSE;
        SourceFormat sourceFormat2 = new SourceFormat(source2, format2);
        ArticleSource articleSource2 = new FileJsonSource("newsapi.txt");
        BaseJsonParser parser2 = new NewsResponseJsonParser(articleSource2, logger);
        sourceFormat2.accept(parser2);

        // SimpleArticle from file.
        System.out.println("Simple article from file.");
        SourceFormat.ArticleSourceType source3 = SourceFormat.ArticleSourceType.FILE_JSON;
        SourceFormat.ArticleFormatType format3 = SourceFormat.ArticleFormatType.SIMPLE_ARTICLE;
        SourceFormat sourceFormat3 = new SourceFormat(source3, format3);
        ArticleSource articleSource3 = new FileJsonSource("simple.txt");
        BaseJsonParser parser3 = new SimpleNewsResponseJsonParser(articleSource3, logger);
        sourceFormat3.accept(parser3);

    }
}