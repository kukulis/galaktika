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
	public List<PlanePoint> getRectanglePoints(Rectangle rectangle) {
		return planePoints.stream().filter(p -> rectangle.isInside(p)).collect(Collectors.toList());
	}

	@Override
	public List<PlanePoint> getCirclePoints(Circle circle) {
		return planePoints.stream()
				.filter(p -> circle.isInside(p))
				.collect(Collectors.toList());
	}

	@Override
	public PlanePoint getAnyCirclePoint(Circle circle) {
		for ( PlanePoint pp : planePoints )
			if( circle.isInside( pp ))
				return pp;
		
		return null;
	}
	
	

}
