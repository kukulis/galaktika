package lt.gt.sgalaktika.battle;

import java.util.ArrayList;
import java.util.List;

public class BattleReportRound {
	private long id;
	private int roundNumber;
	private List < BattleReportShot> shots = new ArrayList<BattleReportShot>();
	
	public BattleReportRound() {
	}
	public int getRoundNumber() {
		return roundNumber;
	}
	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}
	public List<BattleReportShot> getShots() {
		return shots;
	}
	public void setShots(List<BattleReportShot> shots) {
		this.shots = shots;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
