package com.cgi.admin.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

public class CsrTest {

    private static Csr agent;

    @BeforeAll
    public static void setUp() throws  Exception {
    	agent = new Csr();
    	agent.setCsrId(1);
    	agent.setName("Ainz");
    	agent.setUserName("Dan");
    	agent.setPassword("pass");
    	agent.setEnabled(true);
    	agent.setAdmin(false);
    }

    @Test
    public void testBean() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(Csr.class);
    }

    @Test
    public void testCsrIdShouldNotBeEmpty() {
        assertTrue(agent.getCsrId()==1,"CsrId Should be 1");
    }
    @Test
    public void testNameShouldNotBeEmpty() {
        assertTrue(agent.getName().equalsIgnoreCase("Ainz"),"Name should be Ainz");
    }
    @Test
    public void testUserNameShouldNotBeEmpty() {
        assertTrue(agent.getUserName().equalsIgnoreCase("Dan"),"UserName Should be Dan");
    }

    @Test
    public void testPasswordShouldNotBeEmpty() {
        assertTrue(agent.getPassword().equalsIgnoreCase("pass"),"Password should be pass");
    }

    @Test
    public void testEnabledShouldBeTrue() {
        assertTrue(agent.isEnabled()==true,"Enabled should be true");
    }

    @Test
    public void testAdminShouldBeFalse() {
        assertTrue(agent.isAdmin()==false,"Admin should be false");
    }
}
