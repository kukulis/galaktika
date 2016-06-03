package lt.gt.galaktika.web.controllers;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.exception.GalaktikaRuntimeException;
import lt.gt.galaktika.model.DataSearchLimits;
import lt.gt.galaktika.model.DataSearchResult;
import lt.gt.galaktika.model.service.FleetService;
import lt.gt.galaktika.web.various.UserSessionData;

@RestController
public class FleetListController implements ApplicationContextAware
{
	private ApplicationContext applicationContext;

	@Autowired
	private FleetService fleetService;

	@RequestMapping("/fleets")
	public DataSearchResult<Fleet> getFleets ( @RequestParam(name = "from", required = false ) Integer from,
			@RequestParam(name = "amount", required = false) Integer amount)
	{
		System.out.println("from=" + from + " amount=" + amount);

		UserSessionData usd = getUserSessionData();
		System.out.println("Nation from user session data=" + usd.getNation());
		System.out.println("galaktika user id=" + usd.getGalaktikaUser().getUserId());

		DataSearchLimits dsl = new DataSearchLimits(from, amount);
		System.out.println("dsl.from=" + dsl.getLimitFrom() + "  dsl.amount=" + dsl.getLimitAmount());
		return fleetService.getFleets(dsl, usd.getNation().getNationId());
	}

	@RequestMapping("/fleet")
	public Fleet getFleet ( @RequestParam(name = "id" ) long id)
	{
		UserSessionData usd = getUserSessionData();
		return fleetService.getFleet(id, usd.getNation().getNationId());
	}

	@RequestMapping(path = "/storeFleet", method = RequestMethod.POST, consumes = "application/json")
	public long storeFleet ( @RequestBody Fleet fleet )
	{
		System.out.println("name=" + fleet.getName() + " id=" + fleet.getFleetId());
		if (fleet.getName() != null)
		{
			UserSessionData usd = getUserSessionData();

			if (fleet.getFleetId() != 0)
			{
				if (isMyFleet(fleet.getFleetId()))
					return fleetService.save(fleet, usd.getNation().getNationId());
				else
					throw new GalaktikaRuntimeException("The fleet with id =" + fleet.getFleetId() + " is not yours ");
			}
			else
				return fleetService.save(fleet, usd.getNation().getNationId());
		}
		else
		{
			System.out.println("not inserting");
			return 0;
		}
	}

	@RequestMapping(path = "/deleteFleet", method = RequestMethod.DELETE)
	public boolean deleteFleet ( @RequestParam(name = "fleetId" ) long fleetId)
	{
		System.out.println("FleetListController deleteFleet called, fleetId=" + fleetId);

		if (isMyFleet(fleetId))
			return fleetService.deleteFleet( fleetId );
		else
			throw new GalaktikaRuntimeException("Not my fleet");
	}

	@Override
	public void setApplicationContext ( ApplicationContext arg0 ) throws BeansException
	{
		this.applicationContext = arg0;
	}

	private boolean isMyFleet ( long fleetId )
	{
		return fleetService.isFleet(fleetId, getUserSessionData().getNation().getNationId());
	}

	private UserSessionData getUserSessionData ()
	{
		return applicationContext.getBean("userSessionData", UserSessionData.class);
	}
}
