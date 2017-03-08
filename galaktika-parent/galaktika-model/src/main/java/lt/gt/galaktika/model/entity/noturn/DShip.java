package lt.gt.galaktika.model.entity.noturn;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ships")
public class DShip
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Access(AccessType.PROPERTY)
	long id;

	@NotNull
	@Size(min = 2, max = 32)
	private String name;
	private double attack;
	private int guns;
	private double defence;
	private double cargo;
	private double speed;

	private double totalMass;
	
	private int buildTurnId;


	// TODO relations with owner,turn,shiptype, techonologies?
	
	public DShip () {
		
	}
	
	public DShip (String name ) {
		this.name=name;
	}
	
	public DShip(long id, String name)
	{
		this.id = id;
		this.name = name;
	}

	

	public DShip(long id, String name, double attack, int guns, double defence, double cargo, double speed,
			double totalMass) {
		this.id = id;
		this.name = name;
		this.attack = attack;
		this.guns = guns;
		this.defence = defence;
		this.cargo = cargo;
		this.speed = speed;
		this.totalMass = totalMass;
	}

	public long getId ()
	{
		return id;
	}

	public void setId ( long id )
	{
		this.id = id;
	}

	public double getTotalMass ()
	{
		return totalMass;
	}

	public void setTotalMass ( double totalMass )
	{
		this.totalMass = totalMass;
	}

	public double getSpeed ()
	{
		return speed;
	}

	public void setSpeed ( double speed )
	{
		this.speed = speed;
	}

	public double getAttack ()
	{
		return attack;
	}

	public void setAttack ( double attack )
	{
		this.attack = attack;
	}

	public int getGuns ()
	{
		return guns;
	}

	public void setGuns ( int guns )
	{
		this.guns = guns;
	}

	public double getDefence ()
	{
		return defence;
	}

	public void setDefence ( double defence )
	{
		this.defence = defence;
	}

	public double getCargo ()
	{
		return cargo;
	}

	public void setCargo ( double cargo )
	{
		this.cargo = cargo;
	}

	public String getName ()
	{
		return name;
	}

	public void setName ( String name )
	{
		this.name = name;
	}
	
	public int getBuildTurnId() {
		return buildTurnId;
	}

	public void setBuildTurnId(int buildTurnId) {
		this.buildTurnId = buildTurnId;
	}

	@Override
	public String toString() {
		return "DShip [id=" + id + ", name=" + name + ", attack=" + attack + ", guns=" + guns + ", defence=" + defence
				+ ", cargo=" + cargo + ", speed=" + speed + ", totalMass=" + totalMass + "]";
	}

}
