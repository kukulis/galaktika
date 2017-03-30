package lt.gt.galaktika.model.entity.turn;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lt.gt.galaktika.model.entity.noturn.DNation;
import lt.gt.galaktika.utils.Utils;

@Entity
@Table ( name="technologies",
uniqueConstraints = {@UniqueConstraint(columnNames={"nationId", "turnNumber"})})
public class DTechnologies
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long technologiesId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nationId", nullable = true)
	private DNation owner;
	
	private int turnNumber;
	
	private double attack=1, defence=1, cargo=1, engines=1;
	
	public DTechnologies() {
	}
	
	public DTechnologies(double attack, double defence, double cargo, double engines) {
		this.attack = attack;
		this.defence = defence;
		this.cargo = cargo;
		this.engines = engines;
	}
	
	public DTechnologies(long technologiesId, DNation owner, int turnNumber, double attack, double defence,
			double cargo, double engines) {
		this.technologiesId = technologiesId;
		this.owner = owner;
		this.turnNumber = turnNumber;
		this.attack = attack;
		this.defence = defence;
		this.cargo = cargo;
		this.engines = engines;
	}

	public long getTechnologiesId() {
		return technologiesId;
	}

	public void setTechnologiesId(long technologiesId) {
		this.technologiesId = technologiesId;
	}

	public DNation getOwner() {
		return owner;
	}

	public void setOwner(DNation owner) {
		this.owner = owner;
	}

	public int getTurnNumber() {
		return turnNumber;
	}

	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}

	public double getAttack() {
		return attack;
	}

	public void setAttack(double attack) {
		this.attack = attack;
	}

	public double getDefence() {
		return defence;
	}

	public void setDefence(double defence) {
		this.defence = defence;
	}

	public double getCargo() {
		return cargo;
	}

	public void setCargo(double cargo) {
		this.cargo = cargo;
	}

	public double getEngines() {
		return engines;
	}

	public void setEngines(double engines) {
		this.engines = engines;
	}

	@Override
	public boolean equals(Object obj) {
		if ( obj instanceof DTechnologies ) {
			DTechnologies t = (DTechnologies) obj;
			if ( technologiesId > 0 && t.technologiesId > 0 )
				return technologiesId == t.technologiesId;
			else {
				boolean rez = lt.gt.math.Utils.same(attack, t.attack, Utils.EPSILON)
						&& lt.gt.math.Utils.same(cargo, t.cargo, Utils.EPSILON)
						&& lt.gt.math.Utils.same(defence, t.defence, Utils.EPSILON)
						&& lt.gt.math.Utils.same(engines, t.engines, Utils.EPSILON);
				return rez;
			}
		}
		else return false;
	}

	@Override
	public String toString() {
		long ownerId = 0;
		if ( owner != null )
			ownerId = owner.getNationId();
		return "DTechnologies [technologiesId=" + technologiesId + ", owner=" + ownerId + ", turnNumber=" + turnNumber
				+ ", attack=" + attack + ", defence=" + defence + ", cargo=" + cargo + ", engines=" + engines + "]";
	}
}
