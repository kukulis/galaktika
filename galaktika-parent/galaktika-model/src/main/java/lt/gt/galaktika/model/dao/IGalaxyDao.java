package lt.gt.galaktika.model.dao;

import java.util.List;

import lt.gt.galaktika.model.entity.noturn.DGalaxy;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;

public interface IGalaxyDao {
	/**
	 * 
	 * @param purpose
	 * @return
	 */
	public List<DGalaxy> find ( EGalaxyPurposes purpose );
}
