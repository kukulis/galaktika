package lt.gt.galaktika.model.dao;

import java.util.List;

import lt.gt.galaktika.model.entity.DNation;

public interface INationDao
{
	 public List<DNation> getNationByUser ( long userId );
	 
	 public DNation getNation ( long nationId );
}
