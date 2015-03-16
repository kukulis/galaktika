package lt.gt.sgalaktika.battle;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table( 
		name="battlereportround" )
public class BattleReportRound {
	private long id;
	private int roundNumber;
	private List < BattleReportShot> shots = new ArrayList<BattleReportShot>();
	
	public BattleReportRound() {
	}
	
	@Id @GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public int getRoundNumber() {
		return roundNumber;
	}
	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}
	
	@OneToMany(mappedBy="round" , cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
	@OrderBy("number")
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
