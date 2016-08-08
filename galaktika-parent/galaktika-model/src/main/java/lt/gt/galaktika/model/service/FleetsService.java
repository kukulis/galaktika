package lt.gt.galaktika.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.model.dao.IDAO;
import lt.gt.galaktika.model.dao.IFleetDataDao;

@Service
public class FleetsService {
	
	@Autowired
	@Qualifier("dao")
	IDAO dao;
	
	@Autowired
    IFleetDataDao dFleetDataDao;
	
	public Fleet storeFleet ( Fleet fleet ) {
		
		// TODO
		return null;
	}

}
