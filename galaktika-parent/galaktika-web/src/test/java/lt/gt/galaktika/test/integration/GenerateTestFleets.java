package lt.gt.galaktika.test.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.model.service.FleetService;
import lt.gt.galaktika.web.GalaktikaApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GalaktikaApplication.class)
@WebAppConfiguration
public class GenerateTestFleets
{
	@Autowired
	private FleetService fleetService;
	
	@Test
	public void testGenerateFleets() {
		System.out.println ( "testGenerateFleets called" );
		Fleet fleetZalgiris=new Fleet("Žalgiris");
		Fleet fleetLietuvosRytas=new Fleet("Lietuvos Rytas");
		Fleet fleetAtletas=new Fleet("Atletas");
		Fleet fleetNevezis=new Fleet("Nevėžis");
	
		System.out.println ( "Zalgiris id="+ fleetService.save( fleetZalgiris, 0 ));
		System.out.println ( "Lietuvos Rytas id="+ fleetService.save( fleetLietuvosRytas, 0 ));
		System.out.println ( "Atletas id="+ fleetService.save( fleetAtletas, 0 ));
		System.out.println ( "Nevėžis id="+ fleetService.save( fleetNevezis, 0 ));
		
	}
}
