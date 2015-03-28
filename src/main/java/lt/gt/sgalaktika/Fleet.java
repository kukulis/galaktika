package lt.gt.sgalaktika;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PostLoad;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table( name="fleets" )
@NamedQueries(
		@NamedQuery(name = "fleet.findAll", query = "from Fleet")
		) 
public class Fleet {
	@Id @GeneratedValue
	private long id;
	
	@OneToMany(mappedBy="fleet" , cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER )
	@OrderBy("numberInFleet")
	private List <ShipGroup> groups = new ArrayList<ShipGroup> ();
	
	/**
	 * TODO use counter, which will be updated in "add" and "remove" methods.
	 * @return
	 */
	public int calculateShips () {
		int count = 0;
		for ( ShipGroup group : groups ) {
			count += group.getAmount();
		}
		return count;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void addShipGroup ( ShipGroup group ) {
		groups.add(group);
		group.setFleet( this );
		group.setNumberInFleet( groups.size());
	}
	
	public void resetShotsAmount () {
		for ( ShipGroup group : groups ) {
			group.setShotedShips( 0 );
		}
	}
	
	/**
	 * TODO use counters for performance
	 * @return
	 */
	public int calculateShots() {
		int shots = 0;
		for ( ShipGroup group : groups ) {
			shots += group.getShotedShips();
		}
		
		return shots;
	}
	
	public int calculateShipsWithGuns () {
		int count = 0;
		for ( ShipGroup group : groups ) {
			if ( group.getShip().getGuns() > 0 ) {
				count += group.getAmount();
			}
		}
		return count;
	}
	
	public int calculateAbleShotShipCount () {
		int count = 0;
		for ( ShipGroup group : groups ) {
			count += group.getAbleShotAmount();
		}
		return count;
	}
	
	public ShipGroup selectAttackerGroup (int shipNumber) {
		int i =0;
		
		while (  groups.size() > i && (shipNumber > 0 || groups.get(i).getAbleShotAmount() == 0 )  ) {
			ShipGroup group = groups.get( i );
			if ( group.getShip().getGuns() > 0 ) {
				shipNumber = shipNumber - group.getAbleShotAmount();
			}
			i++;
		}
		if ( i < groups.size() )
			return groups.get(i);
		else
			return null;
		
	}
	
	public ShipGroup selectAnyGroup (int shipNumber) {
		int i =0;
		
		while (  groups.size() > i && (shipNumber > 0 || groups.get(i).getAmount() == 0 )  ) {
			ShipGroup group = groups.get( i );
			shipNumber = shipNumber - group.getAmount();
			i++;
		}
		if ( i < groups.size() )
			return groups.get(i);
		else
			return null;
		
	}
	
	public List <ShipGroup> getShipGroups () {
		return groups;
	}
	
}
