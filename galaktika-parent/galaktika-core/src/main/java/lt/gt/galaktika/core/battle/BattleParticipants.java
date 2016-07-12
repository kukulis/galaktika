package lt.gt.galaktika.core.battle;

import java.util.Arrays;
import java.util.List;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.GalaxyLocation;

public class BattleParticipants
{
	private List<Fleet> participantsA, participantsB;
	private GalaxyLocation battleLocation;

	public BattleParticipants()
	{
	}
	
	public BattleParticipants(Fleet participantA, Fleet participantB, GalaxyLocation battleLocation)
	{
		this.participantsA = Arrays.asList(participantA);
		this.participantsB = Arrays.asList(participantB);
		this.battleLocation = battleLocation;
	}

	public List<Fleet> getParticipantsA ()
	{
		return participantsA;
	}

	public void setParticipantsA ( List<Fleet> participantsA )
	{
		this.participantsA = participantsA;
	}

	public List<Fleet> getParticipantsB ()
	{
		return participantsB;
	}

	public void setParticipantsB ( List<Fleet> participantsB )
	{
		this.participantsB = participantsB;
	}

	public GalaxyLocation getBattleLocation ()
	{
		return battleLocation;
	}

	public void setBattleLocation ( GalaxyLocation battleLocation )
	{
		this.battleLocation = battleLocation;
	}
	
}
