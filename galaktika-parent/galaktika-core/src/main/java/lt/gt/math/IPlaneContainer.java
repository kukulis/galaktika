package lt.gt.math;

import java.util.List;

public interface IPlaneContainer {
	List <PlanePoint> getAllPoints();
	void indexAll();
	
	/**
	 * Must add point and put it in to correct index.
	 * @param point
	 */
	void add(PlanePoint point);
	
	List<PlanePoint> getRectanglePoints( PlanePoint minPoint, PlanePoint maxPoint );
	List<PlanePoint> getCirclePoints(PlanePoint center, double radius);
}
