package lt.gt.math;

import lt.gt.math.fuzzy.SimplePlanePoint;

public class Rectangle {
	PlanePoint minPoint;
	PlanePoint maxPoint;

	public Rectangle() {
	}
	public Rectangle(PlanePoint minPoint, PlanePoint maxPoint) {
		this.minPoint = minPoint;
		this.maxPoint = maxPoint;
	}
	public Rectangle ( double minX, double minY, double maxX, double maxY ) {
		this.minPoint = new SimplePlanePoint(minX, minY );
		this.maxPoint = new SimplePlanePoint(maxX, maxY );
	}
	
	public PlanePoint getMinPoint() {
		return minPoint;
	}
	public void setMinPoint(PlanePoint minPoint) {
		this.minPoint = minPoint;
	}
	public PlanePoint getMaxPoint() {
		return maxPoint;
	}
	public void setMaxPoint(PlanePoint maxPoint) {
		this.maxPoint = maxPoint;
	}
	public boolean isInside (PlanePoint point) {
		return point.getX() >= minPoint.getX() && point.getX() <= maxPoint.getX()
				&& point.getY() >= minPoint.getY() && point.getY() <= maxPoint.getY(); 
	}
}
