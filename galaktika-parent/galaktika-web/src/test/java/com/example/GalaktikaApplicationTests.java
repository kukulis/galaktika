package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lt.gt.galaktika.model.dao.IUserDao;
import lt.gt.galaktika.model.entity.User;
import lt.gt.galaktika.web.GalaktikaApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GalaktikaApplication.class)
@WebAppConfiguration
public class GalaktikaApplicationTests {

	@Autowired
	private IUserDao userDao;
	
	@Test
	public void contextLoads() {
		System.out.println ( "contextLoads called" );
		
		User user=userDao.getByEmail( "pieva@pieva.lt" );
		System.out.println ( "user name="+ user.getName());
	}

}
