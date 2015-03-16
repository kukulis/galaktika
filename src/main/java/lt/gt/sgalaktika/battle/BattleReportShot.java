package lt.gt.sgalaktika.battle;

public class BattleReportShot {
	private long id;
	private long round_id;
	
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getRound_id() {
		return round_id;
	}
	public void setRound_id(long round_id) {
		this.round_id = round_id;
	}
	
	
}
