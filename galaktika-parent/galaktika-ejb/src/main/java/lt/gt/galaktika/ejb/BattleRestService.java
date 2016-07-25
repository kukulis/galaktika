package lt.gt.galaktika.ejb;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.battle.BattleEngine;
import lt.gt.galaktika.core.battle.BattleReport;

@Path("/battle")
public class BattleRestService {
	@EJB
	FleetsServiceRemote fleetsService;

	@GET
	@Path("/do")
	@Produces(MediaType.APPLICATION_JSON)
	public BattleReport doBattle(@QueryParam("aFleetId") long aFleetId, @QueryParam("bFleetId") long bFleetId,
			@QueryParam("maxRounds") int maxRounds) {

		System.out.println("aFleetId="+aFleetId+"  bFleetId="+bFleetId+"   maxRounds="+maxRounds );
		Fleet aFleet = fleetsService.getById(aFleetId);
		Fleet bFleet = fleetsService.getById(bFleetId);

		BattleEngine battleEngine = new BattleEngine();
		return battleEngine.doBattle(aFleet, bFleet, maxRounds);
	}

}
