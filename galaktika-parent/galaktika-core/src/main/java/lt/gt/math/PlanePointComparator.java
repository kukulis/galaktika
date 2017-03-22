package lt.gt.math;

import java.util.Comparator;

public class PlanePointComparator implements Comparator<PlanePoint>{

	@Override
	public int compare(PlanePoint o1, PlanePoint o2) {
		int rez = Double.compare( o1.getX(), o2.getX());
		if ( rez != 0 )
			return rez;
		return Double.compare( o1.getY(), o2.getY());
	}
}
