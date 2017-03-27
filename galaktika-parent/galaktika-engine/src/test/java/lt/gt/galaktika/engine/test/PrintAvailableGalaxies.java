package lt.gt.galaktika.engine.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lt.gt.galaktika.engine.config.MockDbConfig;
import lt.gt.galaktika.model.GalaxiesFilter;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.dao.IGalaxyDao;
import lt.gt.galaktika.model.entity.noturn.DGalaxy;

public class PrintAvailableGalaxies {
	public static void main (String args[]) {
		ApplicationContext context = new AnnotationConfigApplicationContext(  MockDbConfig.class, ModelBeansConfig.class );
		IGalaxyDao gdao = context.getBean( IGalaxyDao.class );
		List<DGalaxy> galaxies = gdao.find(new GalaxiesFilter());
		for ( DGalaxy g : galaxies ) {
			System.out.println( g.getGalaxyId()  + " " + g.getPurpose() );
		}
	}
}
