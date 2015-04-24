package lt.gt.sgalaktika.space;

public class Planet {
	
	private double x, y;
	private double size;
	private Race assignedRace;
	
	
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
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public Race getAssignedRace() {
		return assignedRace;
	}
	public void setAssignedRace(Race assignedRace) {
		this.assignedRace = assignedRace;
	}
}
