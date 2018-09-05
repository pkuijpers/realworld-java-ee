package nl.piq.realworldjavaee.domain;

import java.time.ZonedDateTime;
import java.util.Objects;

public class Article {
    private String slug;
    private String title;
    private String description = "";
    private String body = "";
    private final ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public Article(String slug, String title) {
        this(slug, title, ZonedDateTime.now());
    }

    public Article(String slug, String title, ZonedDateTime currentTime) {
        this.slug = slug;
        this.title = title;
        this.createdAt = currentTime;
        this.updatedAt = currentTime;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(slug, article.slug) &&
                Objects.equals(title, article.title) &&
                Objects.equals(description, article.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(slug, title, description);
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }
}
