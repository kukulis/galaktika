package lt.gt.galaktika.core.planet;

import java.util.List;
import java.util.Map;

import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.core.Technologies;
import lt.gt.galaktika.core.TechnologyType;
import lt.gt.math.Utils;

public class PlanetEngine
{
	public final static int RESEARH_POWER_FOR_UNIT = 100;

	public PlanetData excecute ( PlanetData planetData )
	{
		// TODO big method
		return planetData;
	}

	public Ship makeShip ( ShipDesign design, Technologies t )
	{
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

	public PlanetSurface executeIndustry ( PlanetSurface ps )
	{
		PlanetSurface resultPs = new PlanetSurface();
		// TODO
		return resultPs;
	}

	public PlanetData executeProduction ( PlanetData pd )
	{
		PlanetData resultPd = new PlanetData();
		// TODO
		return resultPd;
	}

	/**
	 * Increases technologies in the given map.
	 * 
	 * @param planetData
	 * @param nationTechnologies
	 */
	public void executeTechnologies ( List<PlanetData> planetData, Map<Nation, Technologies> nationTechnologies )
	{
		planetData.stream().map(pd -> pd.getSurface())
				.filter(s -> SurfaceActivities.TECHNOLOGIES.equals(s.getSurfaceCommand().getActivityType()))
				.forEach(s -> addReasearchPower(nationTechnologies.get(s.getNation()), getResearchPower(s),
						((SurfaceCommandTechnologies) s.getSurfaceCommand()).getTechnologyToUpgrade()));
	}

	/**
	 * Returns technologies map copy, after copying initial values from the
	 * given map.
	 * 
	 * @param planetData
	 * @param nationTechnologies
	 * @return
	 */
	public Map<Nation, Technologies> cloneAndExecuteTechnologies ( List<PlanetData> planetData,
			Map<Nation, Technologies> nationTechnologies )
	{
		Map<Nation, Technologies> resultMap = lt.gt.galaktika.utils.Utils.cloneMapValues(nationTechnologies);
		executeTechnologies(planetData, resultMap);
		return resultMap;
	}

	public static double getResearchPower ( PlanetSurface ps )
	{
		return Utils.avg(ps.getPopulation(), ps.getIndustry()) / RESEARH_POWER_FOR_UNIT;
	}

	public static void addReasearchPower ( Technologies t, double researchPower, TechnologyType tt )
	{
		if (TechnologyType.ATTACK == tt)
			t.setAttack(t.getAttack() + researchPower);
		else if (TechnologyType.CARGO == tt)
			t.setCargo(t.getCargo() + researchPower);
		else if (TechnologyType.DEFENCE == tt)
			t.setDefence(t.getDefence() + researchPower);
		else if (TechnologyType.ENGINE == tt)
			t.setEngines(t.getEngines());
	}

}
