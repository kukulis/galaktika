package lt.gt.sgalaktika.battle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BattleReportShot {
	private long id;
	
	@Column( length = 32)
	private String attackerShip;
	@Column( length = 32)
	private String defenderShip;
	private boolean destroyed;
	private int number;
	/**
	 * parent round
	 */
	private BattleReportRound round;
	
	
	public BattleReportShot() {
		
	}

	@Id @GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne
	public BattleReportRound getRound() {
		return round;
	}
	public void setRound(BattleReportRound round) {
		this.round = round;
	}

	public BattleReportShot(String attackerShip, String defenderShip,
			boolean destroyed) {
		
		this.attackerShip = attackerShip;
		this.defenderShip = defenderShip;
		this.destroyed = destroyed;
	}
	public String getAttackerShip() {
		return attackerShip;
	}
	public void setAttackerShip(String attackerShip) {
		this.attackerShip = attackerShip;
	}
	public String getDefenderShip() {
		return defenderShip;
	}
	public void setDefenderShip(String defenderShip) {
		this.defenderShip = defenderShip;
	}
	public boolean isDestroyed() {
		return destroyed;
	}
	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
}
