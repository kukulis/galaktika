package lt.gt.galaktika.core;

import java.io.Serializable;

public class FlightCommand implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6363586219704469424L;
	private GalaxyLocation source;
	private GalaxyLocation destination;
	
	
	public FlightCommand()
	{
	}
	public FlightCommand(GalaxyLocation source, GalaxyLocation destination)
	{
		this.source = source;
		this.destination = destination;
	}
	public GalaxyLocation getSource ()
	{
		return source;
	}
	public void setSource ( GalaxyLocation source )
	{
		this.source = source;
	}
	public GalaxyLocation getDestination ()
	{
		return destination;
	}
	public void setDestination ( GalaxyLocation destination )
	{
		this.destination = destination;
	}
}
