/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.gt.galaktika.ejb.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.ejb.FleetsServiceRemote;

public class EjbTester {

    BufferedReader brConsoleReader = null;
    Properties props;
    InitialContext ctx;

    {
        props = new Properties();
//        try {
        	 ResourceBundle bundle = ResourceBundle.getBundle( "jndi" );
        	 Enumeration<String> keys = bundle.getKeys();
        	 while (keys.hasMoreElements()) {
        		 String key = keys.nextElement();
        		 String value = bundle.getString( key );
        		 props.put( key, value);
        	 }
//        	 
//        	 
////            props.load(new FileInputStream("jndi.properties"));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
        
        System.out.println ("value of java.naming.factory.initial="+ props.getProperty( "java.naming.factory.initial" ));
        try {
            ctx = new InitialContext(props);
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        brConsoleReader
                = new BufferedReader(new InputStreamReader(System.in));
        
    }

    public static void main(String[] args) {

        EjbTester ejbTester = new EjbTester();

        ejbTester.testStatelessEjb();
    }

    private void testStatelessEjb() {
        try {
//            HelloWorld helloBean
//                    = (HelloWorld) ctx.lookup("HelloWorldBean/remote");
//            System.out.println ( helloBean.sayHello() );
        	
        	FleetsServiceRemote fleetsService = (FleetsServiceRemote) ctx.lookup( "FleetsServiceRemote/remote");
        	List<Fleet> fleets = fleetsService.getFleets();
        	System.out.println( "received "+fleets.size() + " fleets" );

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (brConsoleReader != null) {
                    brConsoleReader.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
