package lt.gt.galaktika.core.battle;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.GalaxyLocation;

public class BattleParticipants
{
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
