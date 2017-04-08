package lt.gt.galaktika.core.engines;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.core.ShipGroup;
import lt.gt.galaktika.core.Technologies;
import lt.gt.galaktika.core.TechnologyType;
import lt.gt.galaktika.core.exception.GalaktikaRuntimeException;
import lt.gt.galaktika.core.planet.PlanetData;
import lt.gt.galaktika.core.planet.PlanetOrbit;
import lt.gt.galaktika.core.planet.PlanetSurface;
import lt.gt.galaktika.core.planet.ShipDesign;
import lt.gt.galaktika.core.planet.ShipFactory;
import lt.gt.galaktika.core.planet.SurfaceActivities;
import lt.gt.galaktika.core.planet.SurfaceCommandProduction;
import lt.gt.galaktika.core.planet.SurfaceCommandTechnologies;
import lt.gt.math.Utils;

// TODO split class to increase cohesion
// SPLIT depending on Surface command
public class PlanetEngine {
	final static Log LOG = LogFactory.getLog(PlanetEngine.class);

	public final static int RESEARH_POWER_FOR_UNIT = 100;
	public final static double POPULATION_GROWTH = 1.3;
	public final static double EPSILON = 0.001;

	public static PlanetData clonePlanetData(PlanetData pd) {
		PlanetData result = new PlanetData();
		// setting planet same as original
		result.setPlanet(pd.getPlanet());

		// orbit and surface are new, and copied from original
		result.setOrbit(new PlanetOrbit());
		result.setSurface(new PlanetSurface());
		try {
			BeanUtils.copyProperties(result.getOrbit(), pd.getOrbit());
			BeanUtils.copyProperties(result.getSurface(), pd.getSurface());
		} catch (Exception e) {
			throw new GalaktikaRuntimeException(e);
		}

		return result;
	}

	public Ship makeShip(ShipDesign design, Technologies t) {
		double totalMass = design.getAttackMass() * design.getGuns() + design.getDefenceMass() + design.getCargoMass()
				+ design.getEngineMass();

		double radius = lt.gt.math.Utils.sqrt3(totalMass);

		Ship ship = new Ship(design.getDesignName());
		ship.setAttack(design.getAttackMass() * t.getAttack());
		ship.setGuns(design.getGuns());
		ship.setDefence(design.getDefenceMass() * t.getDefence() / radius);
		ship.setCargo(lt.gt.math.Utils.pow(design.getCargoMass(), 1.5) * t.getCargo());
		ship.setTotalMass(totalMass);
		ship.setSpeed(design.getEngineMass() * t.getEngines() / totalMass);
		return ship;
	}

	public boolean validateShipDesign(ShipDesign design) {
		double totalMass = design.getAttackMass() * design.getGuns() + design.getDefenceMass() + design.getCargoMass()
				+ design.getEngineMass();

		if (totalMass < 1) {
			LOG.error("Invalid total mass");
			return false;
		}

		if (design.getAttackMass() > lt.gt.galaktika.utils.Utils.EPSILON && design.getGuns() == 0) {
			LOG.error("Zero guns for nonzero attack mass");
			return false;
		}
		// forbid values between 0 and 1
		if (Utils.between0And1(design.getAttackMass()/ design.getGuns(), lt.gt.galaktika.utils.Utils.EPSILON)) {
			LOG.error("Attack mass for a gun in (0;1)");
			return false;
		}

		if (Utils.between0And1(design.getDefenceMass(), lt.gt.galaktika.utils.Utils.EPSILON)) {
			LOG.error("Defence mass in (0;1)");
			return false;
		}
		if (Utils.between0And1(design.getCargoMass(), lt.gt.galaktika.utils.Utils.EPSILON)) {
			LOG.error("Cargo mass in (0;1)");
			return false;
		}
		if (Utils.between0And1(design.getEngineMass() , lt.gt.galaktika.utils.Utils.EPSILON)) {
			LOG.error("Engine mass in (0;1)");
			return false;
		}

		return true;
	}

	public void executeIndustry(PlanetData pd) {
		double unepmloyedPopulation = pd.getSurface().getPopulation();
		double unusedCapital = pd.getSurface().getCapital();
		double unusedIndustry = pd.getSurface().getIndustry();
		double requiredIndustry = pd.getPlanet().getPlanetSize() - pd.getSurface().getIndustry();

		if (requiredIndustry > 0) {
			// try to make industry from capital
			double industryFromCapital = Math.min(Math.min(unepmloyedPopulation, unusedCapital), requiredIndustry);
			pd.getSurface().setIndustry(
					Utils.limit(pd.getSurface().getIndustry() + industryFromCapital, pd.getPlanet().getPlanetSize()));

			unepmloyedPopulation -= industryFromCapital;
			unusedCapital -= industryFromCapital;
			requiredIndustry -= industryFromCapital;
		}

		// still not enough industry
		if (requiredIndustry > 0) {
			// industry power depends on planet richness
			double industryFromIndustry = Math.min(
					Math.min(unusedIndustry, unepmloyedPopulation) * pd.getPlanet().getRichness(), requiredIndustry);
			pd.getSurface().setIndustry(
					Utils.limit(pd.getSurface().getIndustry() + industryFromIndustry, pd.getPlanet().getPlanetSize()));
			unepmloyedPopulation -= industryFromIndustry;
			unusedIndustry -= industryFromIndustry;
			requiredIndustry -= industryFromIndustry;
		}

		// now let remaining population and industry build some capital
		if (requiredIndustry <= EPSILON) { // virtual zero
			double remainingCapital = Math.min(unepmloyedPopulation, unusedIndustry) * pd.getPlanet().getRichness();
			double totalCapital = unusedCapital + remainingCapital;

			pd.getSurface().setCapital(totalCapital);
		}

	}

	/**
	 * 
	 * @param pd
	 * @param t
	 */
	public void executeProduction(PlanetData pd, Technologies t) {
		double unepmloyedPopulation = pd.getSurface().getPopulation();
		double unusedCapital = pd.getSurface().getCapital();
		double unusedIndustry = pd.getSurface().getIndustry();

		if (pd.getPlanet() == null)
			throw new GalaktikaRuntimeException("The planet is null");

		SurfaceCommandProduction productionCommand = (SurfaceCommandProduction) pd.getSurface().getSurfaceCommand();
		if (productionCommand == null)
			throw new GalaktikaRuntimeException("The command is null planet=" + pd.getPlanet());

		if (productionCommand.getShipDesign() == null)
			throw new GalaktikaRuntimeException("No ship design assigned to production command " + pd.getPlanet());

		if (!validateShipDesign(productionCommand.getShipDesign()))
			throw new GalaktikaRuntimeException("Invalid ship design " + productionCommand.getShipDesign());

		Ship ship = null;
		if (pd.getSurface().getShipFactory() == null) {
			pd.getSurface().setShipFactory(new ShipFactory());
			pd.getSurface().getShipFactory().setShipDesign(productionCommand.getShipDesign());
		}
		// create ship factory if there is none
		if (pd.getSurface().getShipFactory().getDonePart() > EPSILON
				&& productionCommand.getShipDesign().equals(pd.getSurface().getShipFactory().getShipDesign())) {
			// if there is unfinished ship, then
			// continue the same ship
			ship = pd.getSurface().getShipFactory().getShip();
		} else {
			// create new ship
			ship = makeShip(productionCommand.getShipDesign(), t);
			pd.getSurface().getShipFactory().setShip(ship);
			pd.getSurface().getShipFactory().setShipDesign(productionCommand.getShipDesign());
			pd.getSurface().getShipFactory().setTechnologies(t);
			pd.getSurface().getShipFactory().setDonePart(0);
		}

		int shipsToBuild = productionCommand.getMaxShips();
		int shipsMade = 0;
		while (shipsToBuild > 0 && unepmloyedPopulation > 0 && unusedIndustry > 0) {
			// to build a ship you first need make capital, equal of the
			// half of the mass of the ship.
			double capitalNeededFor1Ship = ship.getTotalMass() / 2;
			double capitalMissing = capitalNeededFor1Ship - unusedCapital;
			if (capitalMissing > 0) {
				// build missing capital
				// building capital depends on planet richness
				double resourcesRequiredForCapital = capitalMissing / pd.getPlanet().getRichness();
				double resourcesUsedToBuildCapital = Math.min(Math.min(unepmloyedPopulation, unusedIndustry),
						resourcesRequiredForCapital);
				double capitalProduced = resourcesUsedToBuildCapital * pd.getPlanet().getRichness();
				unusedCapital += capitalProduced;
				unepmloyedPopulation -= resourcesUsedToBuildCapital;
				unusedIndustry -= resourcesRequiredForCapital;
			}

			// building ship itself requires resources (population and
			// industry), equal
			// to the half of the total ship mass
			if (unepmloyedPopulation > EPSILON && unusedIndustry > EPSILON) {
				// start building ship.
				// take all the capital, it requires to build
				unusedCapital -= capitalNeededFor1Ship;

				// also finish the previously started ship if there is one.
				double requiredWorkPower = (ship.getTotalMass() / 2)
						* (1 - pd.getSurface().getShipFactory().getDonePart());

				// building ship does not depend on planet richness
				double availableWorkPower = Math.min(Math.min(unusedIndustry, unusedIndustry), requiredWorkPower);

				// build ship
				unusedIndustry -= availableWorkPower;
				unepmloyedPopulation -= availableWorkPower;

				if (requiredWorkPower <= availableWorkPower + EPSILON) {
					// there is enough building power to finish a ship
					shipsToBuild--;
					shipsMade++;
					pd.getSurface().getShipFactory().setDonePart(0);
				} else // there is not enough power to finish a ship
					pd.getSurface().getShipFactory().setDonePart(availableWorkPower / requiredWorkPower);

			}
		}

		// build capital from remaining work power
		double remainingWorkPower = Math.min(unusedIndustry, unusedIndustry);
		if (remainingWorkPower > EPSILON) {
			double builtCapital = remainingWorkPower * pd.getPlanet().getRichness();
			unusedCapital += builtCapital;
			pd.getSurface().setCapital(unusedCapital);
		}

		// create orbit if none exists
		if ( pd.getOrbit() == null ) {
			pd.setOrbit( new PlanetOrbit());
		}
		
		// add the build ships to the orbit
		if (shipsMade > 0) {
			Fleet fleet = pd.getOrbit().findNationFleet(pd.getSurface().getNation());
			if (fleet == null) {
				fleet = new Fleet(ship.getName() + " fleet");
				fleet.setOwner(pd.getSurface().getNation());
				pd.getOrbit().getFleets().add(fleet);
			}

			fleet.addShipGroup(new ShipGroup(ship, shipsMade));
		}

	}

	/**
	 * Increases technologies in the given map.
	 * 
	 * @param planetData
	 * @param nationTechnologies
	 * @deprecated lets not use map.
	 */
	public void executeTechnologies(List<PlanetData> planetData, Map<Nation, Technologies> nationTechnologies) {
		planetData.stream().map(pd -> pd.getSurface())
				.filter(s -> SurfaceActivities.TECHNOLOGIES.equals(s.getSurfaceCommand().getActivityType()))
				.forEach(s -> addReasearchPower(nationTechnologies.get(s.getNation()), getResearchPower(s),
						((SurfaceCommandTechnologies) s.getSurfaceCommand()).getTechnologyToUpgrade()));
	}

	/**
	 * 
	 * @param planetData
	 * @param t
	 */
	public void executeTechnologies(PlanetData planetData, Technologies t) {
		addReasearchPower(t, getResearchPower(planetData.getSurface()),
				((SurfaceCommandTechnologies) planetData.getSurface().getSurfaceCommand()).getTechnologyToUpgrade());
	}

	/**
	 * Returns technologies map copy, after copying initial values from the
	 * given map.
	 * 
	 * @param planetData
	 * @param nationTechnologies
	 * @return
	 */
	public Map<Nation, Technologies> cloneAndExecuteTechnologies(List<PlanetData> planetData,
			Map<Nation, Technologies> nationTechnologies) {
		Map<Nation, Technologies> resultMap = lt.gt.galaktika.utils.Utils.cloneMapValues(nationTechnologies);
		executeTechnologies(planetData, resultMap);
		return resultMap;
	}

	/**
	 * The research power is average of industry and population, divided by
	 * coefficient. But only if population is enough.
	 * 
	 * @param ps
	 * @return
	 */
	public static double getResearchPower(PlanetSurface ps) {
		if (ps.getPopulation() > ps.getIndustry())
			return Utils.avg(ps.getPopulation(), ps.getIndustry()) / RESEARH_POWER_FOR_UNIT;
		else
			return ps.getPopulation() / RESEARH_POWER_FOR_UNIT;
	}

	public static void addReasearchPower(Technologies t, double researchPower, TechnologyType tt) {
		if (TechnologyType.ATTACK == tt)
			t.setAttack(t.getAttack() + researchPower);
		else if (TechnologyType.CARGO == tt)
			t.setCargo(t.getCargo() + researchPower);
		else if (TechnologyType.DEFENCE == tt)
			t.setDefence(t.getDefence() + researchPower);
		else if (TechnologyType.ENGINE == tt)
			t.setEngines(t.getEngines()+ researchPower);
	}

	public void executePopulation(PlanetData pd) {
		pd.getSurface().setPopulation(
				Utils.limit(pd.getSurface().getPopulation() * POPULATION_GROWTH, pd.getPlanet().getPlanetSize()));
	}

}
