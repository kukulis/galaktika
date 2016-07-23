package lt.gt.galaktika.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.ShipGroup;

@Path("/ships")
public class ShipsRestService {
	@EJB
	FleetsServiceRemote fleetsService;

	@Path("/{fleetId}/getall")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<ShipGroup> getAll(@PathParam("fleetId") long fleetId) {
		return fleetsService.getById(fleetId).getShipGroups();
	}

	@Path("/{fleetId}/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PUT
	public long createShipGroup(@PathParam("fleetId") long fleetId, ShipGroup shipGroup) {
		Fleet fleet = fleetsService.getById(fleetId);
		if (fleet == null)
			return -1;

		return fleetsService.addShipGroup(fleet, shipGroup);
	}

	@Path("/{fleetId}/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public boolean updateShipGroup(@PathParam("fleetId") long fleetId, ShipGroup shipGroup) {
		return fleetsService.updateShipGroup(fleetId, shipGroup);
	}

	@Path("/{fleetId}/delete/{shipId}")
	@Produces(MediaType.APPLICATION_JSON)
	@DELETE
	public boolean deleteShipGroup(@PathParam("fleetId") long fleetId, @PathParam("shipId") long shipId) {
		return fleetsService.deleteShipGroup(fleetId, shipId);
	}

}