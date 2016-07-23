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
		round.setReport( this);
	}
}
