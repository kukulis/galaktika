package lt.gt.sgalaktika;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table( 
		name="ships",
		uniqueConstraints=@UniqueConstraint ( name="uniqueShipSerie", columnNames={"shipSerie" } ))
@NamedQueries({
	@NamedQuery(name = "ships.findAll", query ="from Ship"),
	@NamedQuery(name = "ships.findById", query ="from Ship where id = :id")
})
@NoArgsConstructor
public class Ship {
	
	private static NumberFormat format=new DecimalFormat("##0.00");
	
	private long id;
	
	/**
	 * Race+Model+Date.
	 */
	private String shipSerie;
	
	private double attack;
	private int guns;
	private double deffence;
	private double cargo;
	private double enginePower;
	private double brutoMass;
	private double loadAmount;
	
	
	public Ship ( String serie ) {
		this.shipSerie = serie;
	}
	
	@Id
	@GeneratedValue
	@GenericGenerator(name="increment", strategy = "increment")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "shipSerie", nullable = false, length = 32)
	public String getShipSerie() {
		return shipSerie;
	}

	public void setShipSerie(String shipSerie) {
		this.shipSerie = shipSerie;
	}

	public double getAttack() {
		return attack;
	}

	public void setAttack(double attack) {
		this.attack = attack;
	}

	public int getGuns() {
		return guns;
	}

	public void setGuns(int guns) {
		this.guns = guns;
	}

	public double getDeffence() {
		return deffence;
	}

	public void setDeffence(double deffence) {
		this.deffence = deffence;
	}

	public double getCargo() {
		return cargo;
	}

	public void setCargo(double cargo) {
		this.cargo = cargo;
	}

	public double getEnginePower() {
		return enginePower;
	}

	public void setEnginePower(double enginePower) {
		this.enginePower = enginePower;
	}

	public double getBrutoMass() {
		return brutoMass;
	}

	public void setBrutoMass(double brutoMass) {
		this.brutoMass = brutoMass;
	}

	public double getLoadAmount() {
		return loadAmount;
	}

	public void setLoadAmount(double loadAmount) {
		this.loadAmount = loadAmount;
	}

	@Override
	public String toString() {
		
		return shipSerie + ":["+format.format(attack)+"x"+guns+";"
				+format.format(deffence)+";"
				+format.format(enginePower) +";"
				+format.format(cargo) + "|"
				+format.format(brutoMass) + "]";  
	}
	
}
