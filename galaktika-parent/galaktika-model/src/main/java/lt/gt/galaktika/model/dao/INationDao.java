package lt.gt.galaktika.model.dao;

import java.util.List;

import lt.gt.galaktika.model.NationsFilter;
import lt.gt.galaktika.model.entity.noturn.DNation;

public interface INationDao
{
	 public DNation getNation ( long nationId );
	 public List<DNation> find ( NationsFilter filter);
}
