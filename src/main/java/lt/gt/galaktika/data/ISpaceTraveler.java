package lt.gt.galaktika.data;

/**
 * Protects from getting IFleet info which is not loaded.
 *  
 */
public interface ISpaceTraveler
{
	public double getSpeed();
	public void setSpeed(double speed);
	public IPlace getPlace();
	public void setPlace (IPlace p); 
}
