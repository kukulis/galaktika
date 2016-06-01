package lt.gt.galaktika.data.impl;

import java.util.ArrayList;
import java.util.List;

import lt.gt.galaktika.data.IShipContainer;
import lt.gt.galaktika.data.IShipGroup;
//import lt.gt.sgalaktika.ShipGroup;

public class ShipContainer implements IShipContainer
{
	private long ID;
	private List<IShipGroup> shipGroups = new ArrayList<IShipGroup>();

	@Override
	public long getID ()
	{
		return ID;
	}

	@Override
	public void setID ( long id )
	{
		ID = id;
	}

	@Override
	public List<IShipGroup> getShipGroups ()
	{
		return shipGroups;
	}

	/**
	 * TODO use counter, which will be updated in "add" and "remove" methods (in
	 * battle only because shots are not a part of the ship container ) .
	 * 
	 * @return
	 */
	public int calculateShips ()
	{
		int count = 0;
		for (IShipGroup group : shipGroups)
		{
			count += group.getAmount();
		}
		return count;
	}

	public void addShipGroup ( ShipGroup group )
	{
		shipGroups.add(group);
		// group.setFleet( this );
		// group.setNumberInFleet( groups.size());
	}

	public void resetShotsAmount ()
	{
		for (IShipGroup group : shipGroups)
		{
			group.setShotedShips(0);
		}
	}

	/**
	 * TODO use counters for performance ( in the battle only, because the shots
	 * is not a part of the fleet container )
	 * 
	 * @return
	 */
	public int calculateShots ()
	{
		int shots = 0;
		for (IShipGroup group : shipGroups)
		{
			shots += group.getShotedShips();
		}

		return shots;
	}

	public int calculateShipsWithGuns ()
	{
		int count = 0;
		for (IShipGroup group : shipGroups)
		{
			if (group.getShip().getGuns() > 0)
			{
				count += group.getAmount();
			}
		}
		return count;
	}

	public int calculateAbleShotShipCount ()
	{
		int count = 0;
		for (IShipGroup group : shipGroups)
		{
			count += group.getAbleShotAmount();
		}
		return count;
	}

	public IShipGroup selectAttackerGroup ( int shipNumber )
	{
		int i = 0;

		while (shipGroups.size() > i && (shipNumber > 0 || shipGroups.get(i).getAbleShotAmount() == 0))
		{
			IShipGroup group = shipGroups.get(i);
			if (group.getShip().getGuns() > 0)
			{
				shipNumber = shipNumber - group.getAbleShotAmount();
			}
			i++;
		}
		if (i < shipGroups.size())
			return shipGroups.get(i);
		else
			return null;

	}

	public IShipGroup selectAnyGroup ( int shipNumber )
	{
		int i = 0;

		while (shipGroups.size() > i && (shipNumber > 0 || shipGroups.get(i).getAmount() == 0))
		{
			IShipGroup group = shipGroups.get(i);
			shipNumber = shipNumber - group.getAmount();
			i++;
		}
		if (i < shipGroups.size())
			return shipGroups.get(i);
		else
			return null;
	}

	/**
	 * @param group
	 */
	public void addShipGroup ( IShipGroup group )
	{
		shipGroups.add(group);
		group.setContainerId(this.ID);
		group.setNumberInContainer(shipGroups.size());
	}

}
