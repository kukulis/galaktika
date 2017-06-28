package lt.gt.galaktika.core.test;

import org.junit.Test;

public class AaaaTest {
	public static void main ( String [] args ) {
		System.out.println( "Hello world" );
	}
	
	@Test
	public void abc() {
		System.out.println( "Hello world abc" );
	}
	
	@Test
	public void abc2() {
		System.out.println( "Hello world abc2" );
		inner();
		System.out.println( "After inner" );
		
	}
	private void inner() {
		System.out.println( "inner called");
	}
	//public void 
}
