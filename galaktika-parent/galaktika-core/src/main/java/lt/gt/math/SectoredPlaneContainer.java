package lt.gt.math;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lt.gt.galaktika.core.exception.GalaktikaException;
import lt.gt.math.fuzzy.SimplePlanePoint;

/**
 * Divides plane by sectors. This fastens search of points, because it searches not the whole points list, but only in several sectors.
 *
 */
public class SectoredPlaneContainer implements IPlaneContainer {
	private List<PlanePoint> planePoints = new ArrayList<>();
	
	private List<PlanePoint> [][] sectors;
	private long sectorStepX;
	private long minSectorLimitX;
	private int sectorsCountX;
	private long sectorStepY;
	private long minSectorLimitY;
	private int sectorsCountY;
	

	public SectoredPlaneContainer(Initializer initializer) throws GalaktikaException 	// This is more assert than exception 
	{
		initializer.haltUninitialized();
		this.sectorStepX = initializer.sectorStepX;
		this.minSectorLimitX = initializer.minSectorLimitX;
		this.sectorsCountX = initializer.sectorsCountX;
		this.sectorStepY = initializer.sectorStepY;
		this.minSectorLimitY = initializer.minSectorLimitY;
		this.sectorsCountY = initializer.sectorsCountY;
		
		initializeSectors();
	}
	
	private void initializeSectors () {
		sectors = new List[sectorsCountX][sectorsCountY];
		
		for ( int x =0; x<sectorsCountX; x++)
			for ( int y=0; y<sectorsCountY; y++)
				sectors[x][y] = new ArrayList<>();
	}

	@Override
	public List<PlanePoint> getAllPoints() {
		return planePoints;
	}

	@Override
	public void indexAll() {
		initializeSectors();
		for ( PlanePoint point : planePoints ) {
			SectorKey key = calculateSectorKey(point);
			sectors[key.getXi()][key.getYi()].add( point );
		}
	}

	@Override
	public void add(PlanePoint point) {
		planePoints.add( point );
		SectorKey key = calculateSectorKey(point);
		sectors[key.getXi()][key.getYi()].add( point );
	}

	@Override
	public List<PlanePoint> getRectanglePoints(Rectangle rectangle) {
		List<PlanePoint> rez = new ArrayList<>();
		List <SectorKey> keys = getRectangleKeys(rectangle);
		for ( SectorKey key : keys )
			for ( PlanePoint point : sectors[key.xi][key.yi])
				if ( rectangle.isInside(point) )
					rez.add(point);
		return rez;
	}

	@Override
	public List<PlanePoint> getCirclePoints(Circle circle) {
		List<PlanePoint> rez = new ArrayList<>();
		List <SectorKey> keys = getCircleKeys(circle);
		for ( SectorKey key : keys )
			for ( PlanePoint point : sectors[key.xi][key.yi])
				if ( circle.isInside(point) )
					rez.add(point);
		return rez;
	}
	
	@Override
	public PlanePoint getAnyCirclePoint(Circle circle) {
		List <SectorKey> keys = getCircleKeys(circle);
		for (SectorKey key: keys)
			for (  PlanePoint point : sectors[key.xi][key.yi] )
				if( circle.isInside( point ))
					return point;
		return null;
	}

	public static class Initializer {
		public Initializer() {
		}
		
		private boolean sectorStepXInitialized=false;
		private boolean minSectorLimitXInitialized=false;
		private boolean sectorsCountXInitialized=false;
		private boolean sectorStepYInitialized=false;
		private boolean minSectorLimitYInitialized=false;
		private boolean sectorsCountYInitialized=false;
		
		private long sectorStepX;
		private long minSectorLimitX;
		private int sectorsCountX;
		private long sectorStepY;
		private long minSectorLimitY;
		private int sectorsCountY;
		

		public Initializer setMinLimits ( long fromX, long fromY ) {
			this.minSectorLimitX = fromX;
			this.minSectorLimitXInitialized= true;
			this.minSectorLimitY = fromY;
			this.minSectorLimitYInitialized = true;
			return this;
		}
		
		public Initializer setSteps ( long stepX, long stepY ) {
			this.sectorStepX = stepX;
			this.sectorStepXInitialized = true;
			this.sectorStepY = stepY;
			this.sectorStepYInitialized = true;
			return this;
		}
		
		public Initializer setCounts ( int countX, int countY) {
			this.sectorsCountX = countX;
			this.sectorsCountXInitialized = true;
			this.sectorsCountY = countY;
			this.sectorsCountYInitialized = true;
			return this;
		}
		
		
		public Initializer setSectorStepX(long sectorStepX) {
			this.sectorStepX = sectorStepX;
			this.sectorStepXInitialized = true;
			return this;
		}
		public Initializer setMinSectorLimitX(long minSectorLimitX) {
			this.minSectorLimitX = minSectorLimitX;
			this.minSectorLimitXInitialized= true;
			return this;
		}
		public Initializer setSectorsCountX(int sectorsCountX) {
			this.sectorsCountX = sectorsCountX;
			this.sectorsCountXInitialized = true;
			return this;
		}
		public Initializer setSectorStepY(long sectorStepY) {
			this.sectorStepY = sectorStepY;
			this.sectorStepYInitialized = true;
			return this;
		}
		public Initializer setMinSectorLimitY(long minSectorLimitY) {
			this.minSectorLimitY = minSectorLimitY;
			this.minSectorLimitYInitialized = true;
			return this;
		}
		public Initializer setSectorsCountY(int sectorsCountY) {
			this.sectorsCountY = sectorsCountY;
			this.sectorsCountYInitialized = true;
			return this;
		}
		
		public String checkInitialized() {
			if (!sectorStepXInitialized) return "sectorStepX";
			if (!minSectorLimitXInitialized) return "minSectorLimitX";
			if (!sectorsCountXInitialized) return "sectorsCountX";
			if (!sectorStepYInitialized) return "sectorStepY";
			if (!minSectorLimitYInitialized) return "minSectorLimitY";
			if (!sectorsCountYInitialized) return "sectorsCountY";
			return null;
		}
		
		public void haltUninitialized() throws GalaktikaException {
			String rez = checkInitialized();
			if ( rez != null )
				throw new GalaktikaException( "Uninitialized SectoredPlaneContainer "+ rez );
		}
	}
	
	public static class SectorKey {
		private int xi, yi;
		
		public SectorKey(int xi, int yi) {
			this.xi = xi;
			this.yi = yi;
		}

		public int getXi() {
			return xi;
		}

		public int getYi() {
			return yi;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + xi;
			result = prime * result + yi;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SectorKey other = (SectorKey) obj;
			if (xi != other.xi)
				return false;
			if (yi != other.yi)
				return false;
			return true;
		}
	}
	
	public SectorKey calculateSectorKey(PlanePoint planePoint) {
		return new SectorKey(Utils.calculateSector(minSectorLimitX, sectorStepX, sectorsCountX, planePoint.getX()),
				Utils.calculateSector(minSectorLimitY, sectorStepY, sectorsCountY, planePoint.getY()));
	}
	
	public List<SectorKey> getRectangleKeys (Rectangle rectangle) {
		SectorKey minKey = calculateSectorKey(rectangle.getMinPoint() );
		SectorKey maxKey = calculateSectorKey(rectangle.getMaxPoint() );
		
		if ( minKey.equals(maxKey))
			return Collections.singletonList( minKey );
		else {
			List<SectorKey> rez = new ArrayList<>();
			for ( int xi=minKey.xi; xi <= maxKey.xi; xi++)
				for ( int yi = minKey.yi; yi <= maxKey.yi; yi++ )
					rez.add( new SectorKey(xi, yi));
			return rez;
		}
	}
	
	public List<SectorKey> getCircleKeys(Circle circle) {
	    PlanePoint minPoint = new SimplePlanePoint( circle.getCenter().getX() - circle.getRadius(), circle.getCenter().getY() - circle.getRadius() );
	    PlanePoint maxPoint = new SimplePlanePoint( circle.getCenter().getX() + circle.getRadius(), circle.getCenter().getY() + circle.getRadius() );
	    
	    Rectangle rectangle = new Rectangle( minPoint, maxPoint );
	    return getRectangleKeys( rectangle );
	}
	
	public List<PlanePoint> getSectorPoints(int xi, int yi) {
		return this.sectors[xi][yi];
	}
	
	public void printSectorsStatistics ( PrintStream ps ) {
		for ( int xi=0; xi < sectorsCountX; xi++) {
			for ( int yi=0; yi < sectorsCountY; yi ++)
				ps.print( sectors[xi][yi].size() + ", " );
			ps.println();
		}
	}
}
