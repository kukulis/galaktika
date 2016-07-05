package lt.gt.galaktika.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Diff<E>
{
	private Set<E> firstOnly;
	private Set<E> secondOnly;
	private Set<E> bothFirst;
	private Set<E> bothSecond;

	public Diff<E> diff ( Collection<E> first, Collection<E> second )
	{
		bothFirst = new HashSet<>(first);
		bothFirst.retainAll(second);
		
		bothSecond = new HashSet<>(second);
		bothSecond.retainAll( first);

		firstOnly = new HashSet<>(first);
		firstOnly.removeAll(second);

		secondOnly = new HashSet<>(second);
		secondOnly.removeAll(first);
		return this;
	}

	public Set<E> getFirstOnly ()
	{
		return firstOnly;
	}

	public Set<E> getSecondOnly ()
	{
		return secondOnly;
	}

	public Set<E> getBothFirst ()
	{
		return bothFirst;
	}

	public Set<E> getBothSecond ()
	{
		return bothSecond;
	}

}
