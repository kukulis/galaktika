package lt.gt.galaktika.data;

import java.util.Collection;

public interface ILeadersSpace
{
	public Collection<ILeader> getLeaders();
	public ILeader getLeader ( long leaderId );
}
