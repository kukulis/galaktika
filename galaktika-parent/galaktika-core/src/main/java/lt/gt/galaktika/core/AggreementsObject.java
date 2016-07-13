package lt.gt.galaktika.core;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AggreementsObject
{
	private Set<TwoNations> inWar = new HashSet<>();
	
	public boolean isInWar ( Nation nation1, Nation nation2 ) {
		return inWar.contains( new TwoNations (nation1, nation2));
	}
	
	public static class TwoNations {
		private Nation nation1, nation2;
		public TwoNations(Nation nation1, Nation nation2)
		{
			this.nation1 = nation1;
			this.nation2 = nation2;
		}

		public Nation getNation1 ()
		{
			return nation1;
		}

		public void setNation1 ( Nation nation1 )
		{
			this.nation1 = nation1;
		}

		public Nation getNation2 ()
		{
			return nation2;
		}

		public void setNation2 ( Nation nation2 )
		{
			this.nation2 = nation2;
		}

		@Override
		public int hashCode ()
		{
//			return (nation1.hashCode() >>> 16) ^ nation2.hashCode(); // asymmetrical WRONG
			return nation1.hashCode()  ^ nation2.hashCode(); // symmetrical hash code! OK
		}

		@Override
		public boolean equals ( Object obj )
		{
			if ( obj instanceof TwoNations ) {
				TwoNations tn = (TwoNations) obj;
				return nation1.equals( tn.getNation1() ) && nation2.equals(tn.getNation2() )
						|| nation2.equals( tn.getNation1() ) && nation1.equals(tn.getNation2() );
			}
			else return false;
		}
	}
	
	public void setInWar ( Nation nation1, Nation nation2 ) {
		inWar.add( new TwoNations( nation1, nation2));
	}
	
	public boolean isInWar ( Collection<Fleet> fleets, Fleet fleet ) {
		for ( Fleet f : fleets )
			if ( isInWar (f.getOwner(), fleet.getOwner() ) ) 
				return true;
		
		return false;
	}
}
