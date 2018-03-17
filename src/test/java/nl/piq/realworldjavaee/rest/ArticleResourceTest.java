package nl.piq.realworldjavaee.rest;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URL;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.empty;

@RunWith(Arquillian.class)
public class ArticleResourceTest {

    @ArquillianResource
    private URL base;

    @Deployment(testable = false)
    public static Archive<?> createDeployment() {
        Archive<?> archive = ShrinkWrap.create(WebArchive.class)
                .addClasses(RealWorldApplication.class, ArticlesResource.class);
        System.out.println(archive.toString(true));
        return archive;
    }

    @Test
    public void getArticles_withEmptyDatabase_returnsZeroArticles() {
        get("/api/articles").then()
                .statusCode(200)
                .body("articles", empty());
    }
}
