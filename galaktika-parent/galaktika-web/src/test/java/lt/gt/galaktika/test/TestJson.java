package lt.gt.galaktika.test;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lt.gt.galaktika.core.Fleet;

public class TestJson
{
	// @Test
	public void test ()
	{
		System.out.println("test called");

		ObjectMapper mapper = new ObjectMapper();

		TestData testData = new TestData();
		testData.setX(1);
		testData.setS("vienas");

		String s = null;
		try
		{
			s = mapper.writeValueAsString(testData);
		} catch (JsonProcessingException e)
		{
			e.printStackTrace();
		}
		System.out.println(s);
	}

	public static class TestData
	{
		private int x;
		private String s;

		public int getX ()
		{
			return x;
		}

		public void setX ( int x )
		{
			this.x = x;
		}

		public String getS ()
		{
			return s;
		}

		public void setS ( String s )
		{
			this.s = s;
		}
	}

	@Test
	public void testFleetsJson ()
	{
		System.out.println("testFleetsJson called");
		Fleet f = new Fleet(1, "pirmas");
		ObjectMapper mapper = new ObjectMapper();

		try
		{
			System.out.println(mapper.writeValueAsString(f));
		} catch (JsonProcessingException e)
		{
			e.printStackTrace();
		}
	}
}
