package lt.gt.galaktika.core.battle;

import java.util.ArrayList;
import java.util.List;

public class BattleReportRound {
	private long id;
	private int roundNumber;
	private List < BattleReportShot> shots = new ArrayList<BattleReportShot>();
	
	private BattleReport report; 
	
	public BattleReportRound() {
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public BattleReport getReport() {
		return report;
	}

	public void setReport(BattleReport report) {
		this.report = report;
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
	
	
	public void addShot ( BattleReportShot shot ) {
		this.shots.add ( shot );
		shot.setRound( this );
		shot.setNumber( this.shots.size() );
	}
}
