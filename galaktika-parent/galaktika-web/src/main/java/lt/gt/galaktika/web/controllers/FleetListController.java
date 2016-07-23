package lt.gt.galaktika.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.exception.EErrorCodes;
import lt.gt.galaktika.core.exception.GalaktikaRuntimeException;
import lt.gt.galaktika.model.DataSearchLimits;
import lt.gt.galaktika.model.DataSearchResult;
import lt.gt.galaktika.model.ESortMethods;
import lt.gt.galaktika.model.FleetSortData;
import lt.gt.galaktika.model.service.FleetService;
import lt.gt.galaktika.web.various.ResponseErrorBody;
import lt.gt.galaktika.web.various.UserSessionData;

@RestController
public class FleetListController implements ApplicationContextAware
{
	final static Logger LOG = LoggerFactory.getLogger(FleetListController.class);
	private ApplicationContext applicationContext;

	@Autowired
	private FleetService fleetService;

	@RequestMapping("/fleets")
	/**
	 * 
	 * @param from
	 *            what record number to begin loading from repository.
	 * @param amount
	 *            how many records load from repository.
	 * @param name
	 *            filter fleets by fleet name
	 * @param showDeleted
	 *            unfilter - show daleted.
	 * @param showAllUsers
	 *            unfilter - show all user's fleets.
	 * @param sortId
	 *            sort fleets by id.
	 * @param sortName
	 *            sort fleets by name.
	 * @return
	 */
	public DataSearchResult<Fleet> getFleets ( @RequestParam(name = "from", required = false) Integer from,
			@RequestParam(name = "amount", required = false) Integer amount,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "showDeleted", required = false) boolean showDeleted,
			@RequestParam(name = "showAllUsers", required = false) boolean showAllUsers,
			@RequestParam(name = "sortId", required = false) String sortId,
			@RequestParam(name = "sortName", required = false) String sortName )
	{
		System.out.println("from=" + from + " amount=" + amount);

		UserSessionData usd = getUserSessionData();
		System.out.println("Nation from user session data=" + usd.getNation());
		System.out.println("galaktika user id=" + usd.getGalaktikaUser().getUserId());

		DataSearchLimits dsl = new DataSearchLimits(from, amount);
		LOG.info("dsl.from=" + dsl.getLimitFrom() + "  dsl.amount=" + dsl.getLimitAmount());

		long nationId = 0;
		if (!showAllUsers)
			nationId = usd.getNation().getNationId();

		FleetSortData fsd = new FleetSortData();
		fsd.setIdSort(ESortMethods.valueOf(sortId != null ? sortId.toUpperCase() : "NONE"));
		fsd.setNameSort(ESortMethods.valueOf(sortName != null ? sortName.toUpperCase() : "NONE"));

		DataSearchResult<Fleet> fleets = fleetService.getFleets(dsl, name, nationId, showDeleted, fsd);
		
		LOG.trace( "before returning data search result, records in the result is "+fleets.getRecords() );
		// temporarily fake
//		fleets.setRecords(Arrays.asList( new Fleet("keistas")));
		return fleets;
	}

	@RequestMapping("/fleet")
	public Fleet getFleet ( @RequestParam(name = "id") long id )
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
	public boolean deleteFleet ( @RequestParam(name = "fleetId") long fleetId )
	{
		System.out.println("FleetListController deleteFleet called, fleetId=" + fleetId);

		if (isMyFleet(fleetId))
			return fleetService.deleteFleet(fleetId);
		else
			throw new GalaktikaRuntimeException(EErrorCodes.DELETE_UNOWN_FLEET, "Not my fleet");
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

	/**
	 * Method, which throws exception.
	 * 
	 * @return
	 */
	@RequestMapping("/testerror")
	public String test ()
	{
		throw new GalaktikaRuntimeException("Tipo klaida");
	}

	@ExceptionHandler(GalaktikaRuntimeException.class)
	public ResponseEntity<ResponseErrorBody> handleIOException ( GalaktikaRuntimeException ex )
	{
		// prepare responseEntity
		LOG.info("handleIOException called");

		ResponseErrorBody errorBody = new ResponseErrorBody(ex.getErrorCode().getCode(), ex.getMessage());
		ResponseEntity<ResponseErrorBody> responseEntity = new ResponseEntity<ResponseErrorBody>(errorBody,
				HttpStatus.EXPECTATION_FAILED
		// HttpStatus.INTERNAL_SERVER_ERROR
		);
		return responseEntity;
	}
}
