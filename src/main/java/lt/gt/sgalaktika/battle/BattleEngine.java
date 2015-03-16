package lt.gt.sgalaktika.battle;

import org.apache.log4j.Logger;

import lt.gt.math.Function;
import lt.gt.math.fuzzy.FuzzyFunction;
import lt.gt.math.fuzzy.FuzzyPoint;
import lt.gt.sgalaktika.Fleet;
import lt.gt.sgalaktika.Ship;
import lt.gt.sgalaktika.ShipGroup;

public class BattleEngine {
	private final static Logger logger=Logger.getLogger( BattleEngine.class );
	
	private BattleReport battleReport;
	private BattleReportRound currentRound;
	
	private boolean debug=false;
//	private int maxTurnsCount = 1000;
//	private int maxRoundsCount = 20;
	
	private Function shootFunction;
	
	public BattleEngine ( ) {
		initializeShootFunction ();
	}
	
	private void initializeShootFunction () {
		FuzzyFunction ff = new FuzzyFunction();
		
		ff.addPoint( new FuzzyPoint(0.25, 0 ) );
		ff.addPoint( new FuzzyPoint(1, 0.5 ) );
		ff.addPoint( new FuzzyPoint(4, 1 ) );
		
		shootFunction = ff;
	}
	
	/**
	 * 
	 * @param fleet1
	 * @param fleet2
	 * @param maxRoundsCount
	 * @return
	 */
	public int doBattle ( Fleet fleet1, Fleet fleet2, int maxRoundsCount ) {
		logger.trace( "----------- start ----------------" );
		
		battleReport = new BattleReport();
		boolean battleFinished = false;
		int rounds=0;
		
		
		if ( fleet1.calculateShips() == 0 || fleet2.calculateShips() == 0
				|| ( fleet1.calculateShipsWithGuns() == 0 && fleet2.calculateShipsWithGuns() == 0 )
				) 
		{
			battleFinished = true;
		}

		currentRound = new BattleReportRound();
		currentRound.setRoundNumber( rounds );
		battleReport.addRound( currentRound );
		
		while ( ! battleFinished && rounds < maxRoundsCount ) {
			logger.trace( "rounds="+rounds );
			AttackerAndDefender aad = randomSelectAttackerAndDefender ( fleet1, fleet2 );
			if ( aad == null || aad.attackerShipGroup == null ) {
				logger.trace( "attacker null, reseting shots" );
				fleet1.resetShotsAmount();
				fleet2.resetShotsAmount();
				rounds ++;
				currentRound = new BattleReportRound();
				currentRound.setRoundNumber( rounds );
				battleReport.addRound( currentRound );
				continue;
			}
			else {
				logger.trace( "attacker name="+aad.attackerShipGroup.getName() );
			}
			
			if ( aad.attackerShipGroup.getAbleShotAmount() == 0  ) {
				logger.error( "Wrong selection of group - it has 0 shots" );
				throw new RuntimeException ( "group with zero shot able ships selected" );
			}

			
			for ( int i = 0; i < aad.attackerShipGroup.getShip().getGuns(); i ++ ) {
				ShipGroup defenderShipGroup = randomSelectDefender ( aad.defenderFleet );
				
				if ( defenderShipGroup != null ) {
					logger.trace( "defenderShipGroup="+defenderShipGroup.getName() );
					if ( defenderShipGroup.getAmount() == 0 ) {
						logger.error( "Wrong selection of group - it has 0 ships" );
						throw new RuntimeException ( "group with zero amount selected" );
					}
					
					
					boolean destroyed =  shoot ( aad.attackerShipGroup.getShip(), defenderShipGroup.getShip() );
					
					currentRound.addShot( new BattleReportShot(
							aad.attackerShipGroup.getShip().getShipSerie(),
							defenderShipGroup.getShip().getShipSerie(), 
							destroyed) );
					
					logger.trace( "attacker has shots:"+aad.attackerShipGroup.getShotedShips() );
					if ( destroyed ) {
						defenderShipGroup.decreaseAmount();
					}
				}
				else {
					logger.trace( "defenderShipGroup is null" );
					break;
				}
			}
			aad.attackerShipGroup.increaseShotedShipsAmount();
			
			int fleet1Ships = fleet1.calculateShips();
			int fleet2Ships = fleet2.calculateShips();
			int fleet1GunShips = fleet1.calculateShipsWithGuns();
			int fleet2GunShips = fleet2.calculateShipsWithGuns();
			
			logger.trace( "amounts:"+fleet1GunShips+" "+fleet2GunShips );
			
			if ( fleet1Ships == 0 || fleet2Ships == 0 // one fleed destroyed
					|| ( fleet1GunShips == 0 && fleet2GunShips == 0 ) // or both fleets have no ships with guns
					) 
			{
				battleFinished = true;
			}
		} // while
		
		return rounds;
	}
	
	/**
	 * Class for storing function results.
	 * 
	 */
	public static class AttackerAndDefender {
		public ShipGroup attackerShipGroup;
		public Fleet defenderFleet;
	}
	
	public AttackerAndDefender randomSelectAttackerAndDefender ( Fleet fleet1 , Fleet fleet2 ) {
		int ableShotShipsCount = calculateAbleShotShipsCount ( fleet1, fleet2 );
		
		if ( ableShotShipsCount <= 0 ) {
			
			return new AttackerAndDefender();
		}
		
		int shipNumber = 0;
		try {
		if ( ! debug ) {
			shipNumber = Flipper.randomInt( ableShotShipsCount );
		}
		} catch ( IllegalArgumentException iae ) {
			logger.fatal( "Exception with ableShotShipsCount="+ableShotShipsCount, iae );
		}
		
		AttackerAndDefender aad = selectAttackerAndDefender ( fleet1 , fleet2, shipNumber );
		
		return aad;
	}
	
	public int calculateAbleShotShipsCount ( Fleet fleet1, Fleet fleet2 ) {
//		int count1 = fleet1.calculateShipsWithGuns();
//		int count2 = fleet2.calculateShipsWithGuns();
//		int shots1 = fleet1.calculateShots();
//		int shots2 = fleet2.calculateShots();
//		
//		int freeCount1 = count1 - shots1;
//		int freeCount2 = count2 - shots2;
//		
//		int freeShipsCount = freeCount1 + freeCount2;
		
		return fleet1.calculateAbleShotShipCount() + fleet2.calculateAbleShotShipCount();
	}
	
	
	public AttackerAndDefender selectAttackerAndDefender ( Fleet fleet1 , Fleet fleet2, int shipNumber ) {

		AttackerAndDefender aad = new AttackerAndDefender();
		
		int count1 = fleet1.calculateShipsWithGuns();
		int shots1 = fleet1.calculateShots();
		int freeCount1 = count1 - shots1;
		
		if ( shipNumber < freeCount1 ) {
			// select ship from the first fleet
			ShipGroup group = fleet1.selectAttackerGroup ( shipNumber );
			aad.defenderFleet = fleet2;
			aad.attackerShipGroup = group;
		}
		else 
		{
			// select ship from the seccond fleet
			ShipGroup group = fleet2.selectAttackerGroup ( shipNumber - freeCount1 );
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
	public boolean shoot ( Ship attacker, Ship defender ) {
		
		double ratio = 0;
		if ( defender.getDeffence() == 0 ) {
			ratio = 100;
		}
		else {
			ratio = ((double)attacker.getAttack() ) / defender.getDeffence();
		}
		double probability = shootProbability( ratio );
		
		return Flipper.yes( probability );
	}
	
	/**
	 * 
	 * @param fleet
	 * @return
	 */
	public ShipGroup randomSelectDefender ( Fleet fleet ) {
		int shipsCount = fleet.calculateShips();
		if ( shipsCount <=  0 )
			return null;
		
		int shipNumber=0;
		
		try {
			shipNumber = Flipper.randomInt( shipsCount );
		} catch ( IllegalArgumentException iae ) {
			logger.fatal( "Exception with shipsCount="+shipsCount, iae );
			throw iae;
		}
		logger.trace( "shipsCount="+shipsCount+"  shipsNumber="+shipNumber );
		return fleet.selectAnyGroup( shipNumber );
	}
	
	/**
	 * 
	 * @param ratio how many times attack is bigger than defence
	 * @return
	 */
	public double shootProbability ( double ratio ) {
		return shootFunction.calculate( ratio );
	}

	public BattleReport getBattleReport() {
		return battleReport;
	}
	
	
}
