package edu.zut.cs.software.sun.admin.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations="classpath:applicationContextTest-service.xml")
public class UserManagerTest2 extends AbstractJUnit4SpringContextTests{
	
	UserManager userManager;
	
	@Autowired
    public void setUserManager(UserManager userManager) {
		this.userManager=userManager;	
	}

	@Test
	public void testSayHello() {
		String expected = "hello,world!";
		String result=this.userManager.sayHello("world");
		assertEquals(expected,result);
	}

}
