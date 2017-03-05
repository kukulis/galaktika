package lt.gt.galaktika.core;

import java.util.Arrays;
import java.util.List;

import lt.gt.math.PlaneVector;

public class Galaxy
{
	private double sizeX, sizeY;
	private int turn;
	
	public Galaxy(double sizeX, double sizeY)
	{
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}
	
	public Galaxy()
	{
	}

	public double getSizeX ()
	{
		return sizeX;
	}

	public void setSizeX ( double sizeX )
	{
		this.sizeX = sizeX;
	}

	public double getSizeY ()
	{
		return sizeY;
	}

	public void setSizeY ( double sizeY )
	{
		this.sizeY = sizeY;
	}

	public double calculateDistance ( GalaxyLocation pointA, GalaxyLocation pointB )
	{
		return 0;
	}

	public double xDistance ( double x1, double x2, double sizex )
	{
		double minX = Math.min(x1, x2);
		double maxX = Math.max(x1, x2);

		double directDistanceX = maxX - minX;
		double edgeDistanceX = (minX + sizex) - maxX;
		return Math.min(directDistanceX, edgeDistanceX);
	}

	public double yDistance ( double y1, double y2, double sizey )
	{
		return xDistance(y1, y2, sizey);
	}

	public PlaneVector getShortestVector ( GalaxyLocation pointA, GalaxyLocation pointB )
	{
		PlaneVector direct = new PlaneVector(pointA, pointB);
		GalaxyLocation fixedSidePoint = fixX(pointB.getX() > pointA.getX(), pointB, sizeX);
		PlaneVector side = new PlaneVector(pointA, fixedSidePoint);
		PlaneVector upOrBottom = new PlaneVector(pointA, fixY(pointB.getY() > pointA.getY(), pointB, sizeY));
		PlaneVector diagonal = new PlaneVector(pointA, fixY(pointB.getY() > pointA.getY(), fixedSidePoint, sizeY));
		List<PlaneVector> vectorsList= Arrays.asList(direct, side, upOrBottom, diagonal);
		// findShortest
		PlaneVector result = vectorsList.stream()
				.min( ( a, b ) -> Double.compare(a.mod(), b.mod())).get();

		return result;
	}

	private GalaxyLocation fixX ( boolean greater, GalaxyLocation orig, double fixAmount )
	{
		if (greater)
			return new SpaceLocation(orig.getX() - fixAmount, orig.getY());
		else
			return new SpaceLocation(orig.getX() + fixAmount, orig.getY());
	}

	private GalaxyLocation fixY ( boolean greater, GalaxyLocation orig, double fixAmount )
	{
		if (greater)
			return new SpaceLocation(orig.getX(), orig.getY() - fixAmount);
		else
			return new SpaceLocation(orig.getX(), orig.getY() + fixAmount);
	}
	
	public GalaxyLocation normalize( double x, double y) {
		if ( x > sizeX )
			x -= sizeX;
		if ( y > sizeY )
			y -= sizeY;
		
		if ( x < 0 )
			x += sizeX;
		if ( y < 0 )
			y += sizeY;
		
		return new SpaceLocation( x, y );
	}
	
	public GalaxyLocation normalize ( GalaxyLocation orig ) {
		return new SpaceLocation( orig.getX(), orig.getY() );
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}
}
