package lt.gt.math;

import lt.gt.math.fuzzy.SimplePlanePoint;

public class Circle {
	PlanePoint center;
	double radius;
	public Circle() {
	}
	
	public Circle (double x, double y, double radius ) {
		center = new SimplePlanePoint(x, y);
		this.radius = radius;
	}
	
	public Circle(PlanePoint center, double radius) {
		this.center = center;
		this.radius = radius;
	}
	
	public PlanePoint getCenter() {
		return center;
	}
	public void setCenter(PlanePoint center) {
		this.center = center;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
}
