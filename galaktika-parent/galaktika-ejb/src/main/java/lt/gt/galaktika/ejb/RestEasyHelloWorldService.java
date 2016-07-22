package lt.gt.galaktika.ejb;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/RestEasyHelloWorld")
@Produces({"text/xml", "application/json"})
public class RestEasyHelloWorldService {

	@GET
	@Path("/{pathParameter}")
//	public Response responseMsg( @PathParam("pathParameter") String pathParameter,
	public String responseMsg( @PathParam("pathParameter") String pathParameter,
			@DefaultValue("Nothing to say") @QueryParam("queryParameter") String queryParameter) {

		String response = "Hello from: " + pathParameter + " : " + queryParameter;

//		return Response.status(200).entity(response).build();
		return response;
	}
}