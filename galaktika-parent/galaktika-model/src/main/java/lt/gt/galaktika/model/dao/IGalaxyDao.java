package lt.gt.galaktika.model.dao;

import java.util.List;

import lt.gt.galaktika.model.GalaxiesFilter;
import lt.gt.galaktika.model.entity.noturn.DGalaxy;

public interface IGalaxyDao {
	/**
	 * 
	 * @param purpose
	 * @return
	 */
	public List<DGalaxy> find ( GalaxiesFilter filter );
}
