package nl.piq.realworldjavaee.rest;

import nl.piq.realworldjavaee.domain.Article;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.time.format.DateTimeFormatter;
import java.util.List;

class ArticlesJsonBuilder {

    private final List<Article> articles;

    ArticlesJsonBuilder(List<Article> articles) {
        this.articles = articles;
    }

    public JsonObject build() {
        return Json.createObjectBuilder()
                .add("articles", createArticlesJson())
                .add("articlesCount", articles.size())
                .build();
    }

    private JsonArrayBuilder createArticlesJson() {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        articles.forEach(article ->
            builder.add(Json.createObjectBuilder()
                    .add("slug", article.getSlug())
                    .add("title", article.getTitle())
                    .add("description", article.getDescription())
                    .add("body", article.getBody())
                    .add("createdAt", article.getCreatedAt().format(DateTimeFormatter.ISO_INSTANT))
                    .add("updatedAt", article.getUpdatedAt().format(DateTimeFormatter.ISO_INSTANT))
            ));
        return builder;
    }
}
