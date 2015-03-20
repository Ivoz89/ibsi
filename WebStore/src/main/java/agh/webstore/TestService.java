package agh.webstore;

/**
 *
 * @author ag
 */
import agh.webstore.db.StoreDb;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
 
@Path("/hello")
public class TestService {
 
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
		String output = "Test msg : " + msg;
                StoreDb db = new StoreDb();
                db.utworzPrzykladoweDane();
                db.closeConnection();
		return Response.status(200).entity(output).build();
 
	}
 
}
