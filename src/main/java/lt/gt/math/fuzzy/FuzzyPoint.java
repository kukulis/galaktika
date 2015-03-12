package lt.gt.math.fuzzy;

public class FuzzyPoint {
	private double x, y;
	
	public FuzzyPoint () {
		
	}
	
	public FuzzyPoint ( double pX, double pY ) {
		this.x = pX;
		this.y = pY;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
