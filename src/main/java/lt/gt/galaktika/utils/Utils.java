package lt.gt.galaktika.utils;

import lt.gt.galaktika.battle.BattleReport;
import lt.gt.galaktika.battle.BattleReportRound;
import lt.gt.galaktika.battle.BattleReportShot;

public class Utils {
	public static void printReport ( BattleReport report ) {
		for ( BattleReportRound round : report.getRounds() ) {
			System.out.println ( "round:"+ round.getRoundNumber() );
			for ( BattleReportShot shot : round.getShots() ) {
				System.out.println ( "Ship "+shot.getAttackerShip()+ " shots at " + shot.getDefenderShip()+ " destroyed:"+shot.isDestroyed() );
			}
		}
	}
}
