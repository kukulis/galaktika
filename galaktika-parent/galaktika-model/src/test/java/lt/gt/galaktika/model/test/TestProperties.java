package lt.gt.galaktika.model.test;

import java.util.Arrays;
import java.util.ResourceBundle;

import org.junit.Test;

public class TestProperties
{

	@Test
	public void testProperties ()
	{
		ResourceBundle appProp = ResourceBundle.getBundle("application");
		String[] packages = appProp.getString("entitymanager.packagesToScan").split(",\\s*");
		Arrays.asList(packages).forEach(p -> System.out.println(p));
	}
}
