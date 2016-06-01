package lt.gt.galaktika.test;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.model.entity.DFleet;

public class TestBeanUtils
{
	@Test
	public void testCopyFleets() throws Exception {
		System.out.println ( "testCopyFleets called" );
		Fleet fleet=new Fleet ("Grybavicius");
		DFleet dFleet = new DFleet();
		
		BeanUtils.copyProperties(dFleet, fleet);
		
		System.out.println ( "dFleet.name="+dFleet.getName() );
	}
}
