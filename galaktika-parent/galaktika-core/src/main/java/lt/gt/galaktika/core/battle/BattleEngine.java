package lt.gt.galaktika.core.battle;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.core.ShipGroup;
import lt.gt.math.Function;
import lt.gt.math.fuzzy.FuzzyFactory;

public class BattleEngine
{
	public final static int INFINITY=100;
	// private final static Logger logger=Logger.getLogger( BattleEngine.class
	// );


	private boolean debug = false;
	// private int maxTurnsCount = 1000;
	// private int maxRoundsCount = 20;

	private Function shootFunction;

	public BattleEngine()
	{
		initializeShootFunction();
	}

	private void initializeShootFunction ()
	{
		shootFunction = FuzzyFactory.getInstance().createBattleFunction();
	}

	/**
	 * Battle until maximum rounds reached or one of the fleets is destroyed.
	 * 
	 * @param fleet1
	 * @param fleet2
	 * @param maxRoundsCount
	 * @return
	 */
	public BattleReport doBattle ( Fleet fleet1, Fleet fleet2, int maxRoundsCount )
	{
		BattleReport battleReport = new BattleReport();
		int rounds = 0;

		while (!isBattleFinished(fleet1, fleet2) && rounds < maxRoundsCount)
			battleReport.addRound(doOneRound(rounds++, fleet1, fleet2));

		return battleReport;
	}

	private boolean isBattleFinished ( Fleet fleet1, Fleet fleet2 )
	{
		int fleet1Ships = fleet1.calculateShips();
		int fleet2Ships = fleet2.calculateShips();
		int fleet1GunShips = fleet1.calculateShipsWithGuns();
		int fleet2GunShips = fleet2.calculateShipsWithGuns();

		return fleet1Ships == 0 || fleet2Ships == 0 // one fleed destroyed
				|| (fleet1GunShips == 0 && fleet2GunShips == 0); // or both
																	// fleets
																	// have no
																	// ships
																	// with guns
	}

	/**
	 * Each ship from each group from both fleets shoots from all guns once.
	 * 
	 * @param roundNumber
	 * @param fleet1
	 * @param fleet2
	 * @return
	 */
	private BattleReportRound doOneRound ( int roundNumber, Fleet fleet1, Fleet fleet2 )
	{
		BattleReportRound currentRound = new BattleReportRound();
		currentRound.setRoundNumber(roundNumber);
		fleet1.resetShotsAmount();
		fleet2.resetShotsAmount();
		AttackerAndDefender aad = randomSelectAttackerAndDefender(fleet1, fleet2);
		while (aad != null && aad.attackerShipGroup != null)
		{
			if (aad.attackerShipGroup.getAbleShotAmount() == 0) 
				throw new RuntimeException("group with zero shot able ships selected");

			shootAllGuns(currentRound, aad.attackerShipGroup, aad.defenderFleet );
			aad.attackerShipGroup.increaseShotedShipsAmount();
			aad = randomSelectAttackerAndDefender(fleet1, fleet2);
		}
		return currentRound;
	}

	/**
	 * One ship of the given group shoots from all his guns to random targets.
	 * 
	 * @param currentRound
	 * @param attackerShipGroup
	 * @param defenderFleet
	 */
	private void shootAllGuns ( BattleReportRound currentRound, ShipGroup attackerShipGroup, Fleet defenderFleet )
	{
		for (int i = 0; i < attackerShipGroup.getShip().getGuns(); i++)
		{
			ShipGroup defenderShipGroup = randomSelectDefender(defenderFleet);
			if (defenderShipGroup == null)
				break;

			if (defenderShipGroup.getCount() == 0) // additional check
				throw new RuntimeException("group with zero amount selected");

			boolean destroyed = shoot(attackerShipGroup.getShip(), defenderShipGroup.getShip());

			currentRound.addShot(new BattleReportShot(attackerShipGroup.getShip().getName(),
					defenderShipGroup.getShip().getName(), destroyed));

			if (destroyed)
				defenderShipGroup.decreaseAmount();
		}
	}

	/**
	 * Class for storing function results.
	 * 
	 */
	public static class AttackerAndDefender
	{
		public ShipGroup attackerShipGroup;
		public Fleet defenderFleet;
	}

	public AttackerAndDefender randomSelectAttackerAndDefender ( Fleet fleet1, Fleet fleet2 )
	{
		int ableShotShipsCount = calculateAbleShotShipsCount(fleet1, fleet2);

		if (ableShotShipsCount <= 0)
			return new AttackerAndDefender();

		int shipNumber = 0;
		try
		{
			if (!debug)
				shipNumber = Flipper.randomInt(ableShotShipsCount);
		} catch (IllegalArgumentException iae)
		{
			// logger.fatal( "Exception with
			// ableShotShipsCount="+ableShotShipsCount, iae );
		}

		AttackerAndDefender aad = selectAttackerAndDefender(fleet1, fleet2, shipNumber);

		return aad;
	}

	public int calculateAbleShotShipsCount ( Fleet fleet1, Fleet fleet2 )
	{
		return fleet1.calculateAbleShotShipCount() + fleet2.calculateAbleShotShipCount();
	}

	public AttackerAndDefender selectAttackerAndDefender ( Fleet fleet1, Fleet fleet2, int shipNumber )
	{

		AttackerAndDefender aad = new AttackerAndDefender();

		int count1 = fleet1.calculateShipsWithGuns();
		int shots1 = fleet1.calculateShots();
		int freeCount1 = count1 - shots1;

		if (shipNumber < freeCount1)
		{
			// select ship from the first fleet
			ShipGroup group = fleet1.selectAttackerGroup(shipNumber);
			aad.defenderFleet = fleet2;
			aad.attackerShipGroup = group;
		}
		else
		{
			// select ship from the seccond fleet
			ShipGroup group = fleet2.selectAttackerGroup(shipNumber - freeCount1);
			aad.defenderFleet = fleet1;
			aad.attackerShipGroup = group;
		}

		return aad;
	}

	/**
	 * 
	 * @param attacker
	 * @param defender
	 * @return
	 */
	public boolean shoot ( Ship attacker, Ship defender )
	{
		double ratio = 0;
		
		if (defender.getDefence() == 0)
			ratio = INFINITY; 
		else
			ratio = ((double) attacker.getAttack()) / defender.getDefence();
		
		double probability = shootProbability(ratio);
		return Flipper.yes(probability);
	}

	/**
	 * 
	 * @param fleet
	 * @return
	 */
	public ShipGroup randomSelectDefender ( Fleet fleet )
	{
		int shipsCount = fleet.calculateShips();
		if (shipsCount <= 0)
			return null;

		int shipNumber = 0;

		try
		{
			shipNumber = Flipper.randomInt(shipsCount);
		} catch (IllegalArgumentException iae)
		{
			// logger.fatal( "Exception with shipsCount="+shipsCount, iae );
			throw iae;
		}
		// logger.trace( "shipsCount="+shipsCount+" shipsNumber="+shipNumber );
		return fleet.selectAnyGroup(shipNumber);
	}

	/**
	 * 
	 * @param ratio
	 *            how many times attack is bigger than defence
	 * @return
	 */
	public double shootProbability ( double ratio )
	{
		return shootFunction.calculate(ratio);
	}
}
