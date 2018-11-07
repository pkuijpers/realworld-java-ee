package nl.piq.realworldjavaee.rest;

import io.restassured.RestAssured;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URL;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;

@RunWith(Arquillian.class)
public class ArticleResourceIT {

    @ArquillianResource
    private URL base;

    @Deployment(testable = false)
    public static Archive<?> createDeployment() {
        Archive<?> archive = ShrinkWrap.create(WebArchive.class)
                .addClasses(RealWorldApplication.class, ArticlesResource.class);
        System.out.println(archive.toString(true));
        return archive;
    }

    @Before
    public void setUp() throws Exception {
        RestAssured.baseURI = base.getProtocol() + "://" + base.getAuthority();
        RestAssured.port = base.getPort();
        RestAssured.basePath = base.getPath();
    }

    @Test
    public void getArticles_withEmptyDatabase_returnsZeroArticles() {
        given()
                .log().all()
        .when()
                .get("/api/articles")
        .then()
                .statusCode(200)
                .body("articles", empty()).log();
    }
}
