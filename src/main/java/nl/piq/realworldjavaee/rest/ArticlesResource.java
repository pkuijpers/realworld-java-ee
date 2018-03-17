package nl.piq.realworldjavaee.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Produces("application/json")
@Path("articles")
public class ArticlesResource {

    @GET
    public String listArticles() {
        return "{\"articles\":[]}";
    }
}
