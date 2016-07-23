package lt.gt.galaktika.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import lt.gt.galaktika.core.Fleet;

@Path("/fleets")
public class FleetsRestService {
	
	@EJB
	FleetsServiceRemote fleetsService;
	
	@GET
	@Path("/getall" )
	@Produces(MediaType.APPLICATION_JSON)
	public List<Fleet> getAll() {
//		System.out.println( "on getFleets, fleetsService.hashCode="+fleetsService.hashCode() );
		return fleetsService.getFleets();
	}
	
	public Fleet getFleet(long fleetId) {
		return fleetsService.getById(fleetId);
	}
	
	@Path("/updateFleet")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @POST
	public boolean updateFleet( Fleet fleet ) {
//		System.out.println( "on updateFleet, fleetsService.hashCode="+fleetsService.hashCode() );
//		if ( fleet != null ) {
//			System.out.println( "on updateFleet received fleet with name "+  fleet.getName()); 
//		}
		return fleetsService.update( fleet );
	}
	
	@Path("/createFleet")
	@Produces(MediaType.APPLICATION_JSON)
	@PUT
	public long createFleet ( @QueryParam("fleetName") String fleetName ) {
		return fleetsService.create ( fleetName );
	}
	
	@Path("/deleteFleet")
	@Produces(MediaType.APPLICATION_JSON)
	@DELETE
	public boolean deleteFleet ( @QueryParam("fleetId") long fleetId ) {
		return fleetsService.delete(fleetId );
	}
}
