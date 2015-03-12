package lt.gt.sgalaktika.battle;

public class BattleReportShot {
	private String attackerShip;
	private String defenderShip;
	private boolean destroyed;
	
	
	public BattleReportShot() {
		
	}
	public BattleReportShot(String attackerShip, String defenderShip,
			boolean destroyed) {
		super();
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
	
	
}
