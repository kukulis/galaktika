package lt.gt.math;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimplePlaneContainer implements IPlaneContainer {

	private List<PlanePoint> planePoints = new ArrayList<>();

	@Override
	public List<PlanePoint> getAllPoints() {
		return planePoints;
	}

	@Override
	public void indexAll() {
		// nothing
	}

	@Override
	public void add(PlanePoint point) {
		planePoints.add(point);

	}

	@Override
	public List<PlanePoint> getRectanglePoints(PlanePoint minPoint, PlanePoint maxPoint) {
		return planePoints.stream().filter(p -> p.getX() >= minPoint.getX() && p.getX() <= maxPoint.getX()
				&& p.getY() >= minPoint.getY() && p.getY() <= maxPoint.getY()).collect(Collectors.toList());
	}

	@Override
	public List<PlanePoint> getCirclePoints(PlanePoint center, double radius) {
		double squareRadius = radius * radius;
		return planePoints.stream()
				.filter(p -> (p.getX() - center.getX()) * (p.getX() - center.getX())
						+ (p.getY() - center.getY()) * (p.getY() - center.getY()) <= squareRadius)
				.collect(Collectors.toList());
	}

}
