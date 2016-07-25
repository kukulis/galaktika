package lt.gt.galaktika.core.battle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BattleReport implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1140735103818537426L;

	private long id;
	private List <BattleReportRound> rounds=new ArrayList<BattleReportRound>();
	private String message;
	private boolean wonFleet1=false;
	private boolean wonFleet2=false;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<BattleReportRound> getRounds() {
		return rounds;
	}
	
	public void setRounds(List<BattleReportRound> rounds) {
		this.rounds = rounds;
	}

	public void addRound ( BattleReportRound round ) {
		rounds.add( round );
//		round.setReport( this);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isWonFleet1() {
		return wonFleet1;
	}

	public void setWonFleet1(boolean wonFleet1) {
		this.wonFleet1 = wonFleet1;
	}

	public boolean isWonFleet2() {
		return wonFleet2;
	}

	public void setWonFleet2(boolean wonFleet2) {
		this.wonFleet2 = wonFleet2;
	}
	
	
}
