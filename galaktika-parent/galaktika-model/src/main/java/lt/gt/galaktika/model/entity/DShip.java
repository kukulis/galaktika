package lt.gt.galaktika.model.entity;

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
	long id;
	
	private double totalMass;
	private double speed;
	private double attack;
	private int guns;
	private double defence;
	private double cargo;
	
	@NotNull
	@Size(min = 2, max = 32)
	private String name;
	
	// TODO relations with owner,turn,shiptype, techonologies?
	
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
	
	

}
