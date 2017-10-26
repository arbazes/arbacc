package com.accenture.adf.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import com.accenture.adf.businesstier.dao.VisitorDAO;
import com.accenture.adf.businesstier.entity.Event;
import com.accenture.adf.businesstier.entity.Visitor;
import com.accenture.adf.helper.FERSDataConnection;
import com.accenture.adf.helper.FERSDbQuery;



/**
 * JUnit test case for VisitorDAO class for testing all repository methods to
 * call database sub-routines
 * 
 */
public class TestVisitorDAO {
	
	private Visitor visitor;
	private VisitorDAO visitorDAO;
	private ArrayList<Event> registeredEvents;
	private Connection connection = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	private FERSDbQuery query;

	/**
	 * Setting up initial objects 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		visitor = new Visitor();
		visitorDAO = new VisitorDAO();
		registeredEvents = new ArrayList<Event>();
	}

	/**
	 * Deallocating objects after execution of every method
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		/**
		 * @TODO: Release all the objects here by assigning them null  
		 */
		visitor=null;
		visitorDAO=null;
		registeredEvents=null;
		
	}

	/**
	 * Test case for method insertData
	 */
	@Test
	public void testInsertData() {
		/**
		 * @TODO: Create visitor object by setting appropriate values
		 * Call insertData method by passing this visitor object
		 * Search this new visitor object by calling searchUser method
		 * Assert the values of username
		 */	
		visitor.setUserName("tastasl");
		visitor.setFirstName("asxsaxx");
		visitor.setLastName("sasss");
		visitor.setPassword("ppsap");
	
		boolean flag=false;
		try {
			 flag= visitorDAO.insertData(visitor);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail("RUNTIME");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true,flag);
		
	}	

	/**
	 * Test case for method searchUser
	 */
	@Test
	public void testSearchUser() {
		/**
		 * @TODO: Call searchUser method for valid values of username
		 * and password and assert the value of username for the returned type of method
		 */		
		String user="bsmith";
		String pass="password";
		
		boolean flag=false;
		try {
			Visitor v=visitorDAO.searchUser(user, pass);
			assertEquals(user,v.getUserName());
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("CL\t"+e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("S\t"+e.getMessage());
		}
		
	}
	

	/**
	 * Test case for method registerVisitorToEvent
	 */
	@Test
	public void testRegisterVisitorToEvent() {
		/**
		 * @TODO: Fetch visitor object by calling searchUser for valid values of username and password
		 * Pass this visitor object and valid eventid to registerVisitorToEvent method
		 * and assert the value
		 */		
		
		try {

			String a = "bsmith";
			String b = "password";
			int eid=1003;
			visitor = visitorDAO.searchUser(a, b);
			visitorDAO.registerVisitorToEvent(visitor, eid);		 

			String qry = "SELECT COUNT(*) FROM EVENTSIGNUP WHERE EVENTID = ? AND VISITORID = ? ;";
			connection = FERSDataConnection.createConnection();
			statement = connection.prepareStatement(qry);
			statement.setInt(1, eid);
			statement.setInt(2, visitor.getVisitorId()); 
			resultSet = statement.executeQuery();		 
			resultSet.next();		 
			
			int count = resultSet.getInt(1);	 
			assertEquals(1, count);			 
			} catch (ClassNotFoundException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
			} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
			} catch (Exception e) 
			{

			e.printStackTrace();

			}

			 

			}
		
		

	/**
	 * Test case for method registeredEvents
	 */
	@Test
	public void testRegisteredEvents() {
		/**
		 * @TODO: Fetch visitor object by calling searchUser for valid values of username and password
		 * Pass this visitor object and valid eventid to registeredEvents method
		 * and assert the value
		 */		 Visitor v=new Visitor(); 
		 ArrayList<Event> arr=new ArrayList<Event>(); 
			try {
				 v=visitorDAO.searchUser("bsmith", "password");
				 System.out.println(v.getVisitorId());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				arr=visitorDAO.registeredEvents(v)	;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			assertEquals(3,arr.size());
			
	}

	/**
	 * Test case for method updateVisitor
	 */
	@Test
	public void testUpdateVisitor() {
		/**
		 * @TODO: Fetch visitor object by calling searchUser for valid values of username and password
		 * Update the value in this visitor object
		 * Pass this visitor object to updateVisitor method
		 * and assert the value of changed value
		 */		
		Visitor v=new Visitor();
		int retval=0;
		try {
			 v=visitorDAO.searchUser("bsmith", "password");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(v!=null)
		{
			try {
			 retval=visitorDAO.updateVisitor(v);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		assertEquals(1, retval);
	}

	/**
	 * Test case for method registeredEvents
	 */
	@Test
	public void testUnregisterEvent() {
		/**
		 * @TODO: Fetch visitor object by calling searchUser for valid values of username and password
		 * Pass this visitor object and valid eventid to unregisterEvent method
		 * and assert the value
	
		 */	
		 int status = 1;
		 try {
			 visitor = visitorDAO.searchUser("bsmith", "password");
		try {
			visitorDAO.unregisterEvent(visitor, 1001);;
			String qry = "SELECT COUNT(*) AS EVENTCOUNT FROM EVENTSIGNUP WHERE EVENTID=1002 AND VISITORID=1001 ;";
			connection = FERSDataConnection.createConnection();
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();
			resultSet.next();
			status = resultSet.getInt(1);
			System.out.println(status);
			}
			catch (Exception e) {

		// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {

		// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {

		// TODO Auto-generated catch block
			e.printStackTrace();

		}
		 assertEquals(0, status);

		}

}
