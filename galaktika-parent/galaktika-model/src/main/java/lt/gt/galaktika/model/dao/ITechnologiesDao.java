package lt.gt.galaktika.model.dao;

import lt.gt.galaktika.model.entity.turn.DTechnologies;

public interface ITechnologiesDao {
	DTechnologies getNationTechnologies(long nationId, int turn);
}
