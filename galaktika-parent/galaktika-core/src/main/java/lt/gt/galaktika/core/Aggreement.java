package lt.gt.galaktika.core;

public class Aggreement
{
	private Nation participant1;
	private Nation participant2;
	private AggreementType type;
	public Nation getParticipant1 ()
	{
		return participant1;
	}
	public void setParticipant1 ( Nation participant1 )
	{
		this.participant1 = participant1;
	}
	public Nation getParticipant2 ()
	{
		return participant2;
	}
	public void setParticipant2 ( Nation participant2 )
	{
		this.participant2 = participant2;
	}
	public AggreementType getType ()
	{
		return type;
	}
	public void setType ( AggreementType type )
	{
		this.type = type;
	}
	
}
