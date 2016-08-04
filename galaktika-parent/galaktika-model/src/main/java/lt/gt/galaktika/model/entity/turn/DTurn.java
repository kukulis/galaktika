package lt.gt.galaktika.model.entity.turn;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "turns")
public class DTurn
{
	@Id
	@Access(AccessType.PROPERTY)
	int turnNumber;

	public DTurn() {
	}

	public DTurn(int turnNumber) {
		this.turnNumber = turnNumber;
	}

	public int getTurnNumber() {
		return turnNumber;
	}

	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}

	@Override
	public String toString() {
		return "DTurn["+turnNumber+"]";
	}
	
	
}
