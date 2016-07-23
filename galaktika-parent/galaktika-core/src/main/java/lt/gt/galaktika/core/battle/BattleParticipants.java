package lt.gt.galaktika.core.battle;

import java.io.Serializable;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.GalaxyLocation;

public class BattleParticipants implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3364444327763997843L;
	private Fleet participantA, participantB;
	private GalaxyLocation battleLocation;

	public BattleParticipants()
	{
	}
	
	public BattleParticipants(Fleet participantA, Fleet participantB, GalaxyLocation battleLocation)
	{
		this.participantA = participantA;
		this.participantB = participantB;
		this.battleLocation = battleLocation;
	}

	public Fleet getParticipantA ()
	{
		return participantA;
	}

	public Fleet getParticipantB ()
	{
		return participantB;
	}

	public GalaxyLocation getBattleLocation ()
	{
		return battleLocation;
	}

}
