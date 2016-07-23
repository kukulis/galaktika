package lt.gt.galaktika.core.battle;

import java.io.Serializable;

public class BattleReportShot implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 739017286578240585L;

	private long id;
	
	private String attackerShip;
	private String defenderShip;
	private boolean destroyed;
	private int number;
	/**
	 * parent round
	 */
	private BattleReportRound round;
	
	
	public BattleReportShot() {
		
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
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
