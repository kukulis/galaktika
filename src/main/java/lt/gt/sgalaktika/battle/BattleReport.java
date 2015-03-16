package lt.gt.sgalaktika.battle;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table( name="battlereport" )

public class BattleReport {
	
	private long id;
	
	private List <BattleReportRound> rounds=new ArrayList<BattleReportRound>();

	@Id  @GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToMany(mappedBy="report" , cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER )
	@OrderBy("roundNumber")
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
