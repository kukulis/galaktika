package lt.gt.sgalaktika.battle;

import java.util.ArrayList;
import java.util.List;

public class BattleReportRound {
	private int roundNumber;
	private List < BattleReportShot> shots = new ArrayList<BattleReportShot>();
	
	public int getRoundNumber() {
		return roundNumber;
	}
	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}
	public List<BattleReportShot> getShots() {
		return shots;
	}
}
