package nl.piq.realworldjavaee.rest;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.POST;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.Consumes;

@Path("/users/login")
public class LoginResource {

	@POST
	@Consumes({"text/plain", "application/json"})
	public Response doPost(java.lang.String entity) {
		return Response.created(
				UriBuilder.fromResource(LoginResource.class).build()).build();
	}
}