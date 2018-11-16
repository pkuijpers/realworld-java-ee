package nl.piq.realworldjavaee.rest;

import nl.piq.realworldjavaee.domain.Article;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import java.io.StringWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

public class ArticlesJsonTest {

    @Test
    public void withEmptyCollection_returnsEmptyJson() throws JSONException {
        String expected = "{\"articles\":[]}";

        JsonObject json = new ArticlesJsonBuilder(emptyList()).build();

        JSONAssert.assertEquals(expected, toJsonString(json), false);
    }

    @Test
    public void withSingleArticle_returnsArticle() throws JSONException {
        String expected = "{\"articles\":[{\"slug\":\"article-1\", " +
                "\"title\":\"Article 1\", " +
                "\"description\":\"Article 1 description\"," +
                "\"body\":\"Article 1 body\"," +
                "\"createdAt\":\"2018-03-20T20:27:00Z\"," +
                "\"updatedAt\":\"2018-03-20T20:27:00Z\"" +
                "}]," +
                "\"articlesCount\":1}";
        ZonedDateTime creationTime = ZonedDateTime.of(2018, 3, 20, 20, 27, 0, 0, ZoneId.of("GMT"));
        Article article = new Article("article-1", "Article 1", creationTime);
        article.setDescription("Article 1 description");
        article.setBody("Article 1 body");
        List<Article> articles = Collections.singletonList(article);

        JsonObject json = new ArticlesJsonBuilder(articles).build();

        JSONAssert.assertEquals(expected, toJsonString(json), false);
    }

    @Test
    public void withMultipleArticles_returnsArticles() throws JSONException {
        String expected = "{\"articles\":[{\"slug\":\"article-1\", \"title\":\"Article 1\", \"description\":\"\"}," +
                         "{\"slug\":\"article-2\", \"title\":\"Article 2\", \"description\":\"\"}]," +
                "\"articlesCount\":2}";
        Article article1 = new Article("article-1", "Article 1");
        Article article2 = new Article("article-2","Article 2");
        List<Article> articles = Arrays.asList(article1, article2);

        JsonObject json = new ArticlesJsonBuilder(articles).build();

        JSONAssert.assertEquals(expected, toJsonString(json), false);
    }

    private String toJsonString(JsonObject json) {
        StringWriter w = new StringWriter();
        try (JsonWriter writer = Json.createWriter(w)) {
            writer.write(json);
        }
        return w.toString();
    }
}
