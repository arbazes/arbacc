package com.accenture.adf.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.accenture.adf.businesstier.dao.EventDAO;
import com.accenture.adf.businesstier.entity.Event;
import com.accenture.adf.businesstier.entity.Visitor;
import com.accenture.adf.exceptions.FERSGenericException;
import com.accenture.adf.helper.FERSDataConnection;

/**
 * Junit test class for EventDAO class
 *
 */
public class TestEventDAO {

	private static Connection connection = null;
	private static PreparedStatement statement = null;
	private static ResultSet resultSet = null;
	private ArrayList<Event> showAllEvents;
	private EventDAO dao;	

	/**
	 * Sets up database connection before other methods are executed in this class
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpDatabaseConnection() throws Exception {
		connection = FERSDataConnection.createConnection();
	}

	/**
	 * Closes the database connection after all the methods are executed
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownDatabaseConnection() throws Exception {
		connection.close();
	}

	/**
	 * Sets up the objects required in other methods
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		showAllEvents = new ArrayList<Event>();
		dao = new EventDAO();
	}

	/**
	 * Deallocate the resources after execution of method
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		dao=null;
		showAllEvents=null;
	}

	/**
	 * Positive test case to test the method showAllEvents
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testShowAllEvents_Positive() throws ClassNotFoundException, SQLException {
		/**
		 * @TODO: Call showAllEvents method and assert it for
		 * size of returned type list
		 */	
		int r=0;
		showAllEvents=dao.showAllEvents();
        assertEquals(6,showAllEvents.size());
	}	

	/**
	 * Junit test case to test positive case for updateEventDeletions
	 * @throws Exception 
	 * @throws ClassNotFoundException 
	 */
	@Test	
	public void testUpdateEventDeletions_Positive() throws ClassNotFoundException, Exception {
		/**
		 * @TODO: Find out seats available for an event by opening a connection
		 * and calling the query SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = ?
		 * Call the updateEventDeletions for eventId
		 * Again find out the seats available for this event
		 * testSeatsAvailableBefore should be 1 more then testSeatsAvailableAfter
		 */		
		int i=0,j=0;
		statement=connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = ?");
		statement.setInt(1,1001);
		resultSet=statement.executeQuery();
		while(resultSet.next())
		{
			i=resultSet.getInt(1);
		}
		
		dao.updateEventDeletions(1001);
		
		PreparedStatement statement2=connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = ?");
		statement2.setInt(1,1001);
		ResultSet resultSet2=statement2.executeQuery();
		while(resultSet2.next())
		{
			j=resultSet2.getInt(1);
		}
		
		assertEquals(i+1,j);
		

		
	}

	/**
	 * Negative test case for method updateEventDeletions
	 * @throws Exception 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testUpdateEventDeletions_Negative() throws ClassNotFoundException, Exception {
		/**
		 * @TODO: Call updateEventDeletions for incorrect eventid and it should
		 * throw an exception
		 */	
		int i=0,j=0;
		statement=connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = ?");
		statement.setInt(1,2000);
		resultSet=statement.executeQuery();
		while(resultSet.next())
		{
			i=resultSet.getInt(1);
		}
		dao.updateEventDeletions(2000);
		PreparedStatement statement2=connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = ?");
		statement2.setInt(1,2000);
		ResultSet resultSet2=statement2.executeQuery();
		while(resultSet2.next())
		{
			j=resultSet2.getInt(1);
		}
		assertEquals(i,j+1);
	}

	/**
	 * Positive test case for method updateEventNominations
	 * @throws Exception 
	 * @throws FERSGenericException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testUpdateEventNominations_Positive() throws ClassNotFoundException, FERSGenericException, Exception {
		/**
		 * @TODO: Find out seats available for an event by opening a connection
		 * and calling the query SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = ?
		 * Call the updateEventNominations for eventId
		 * Again find out the seats available for this event
		 * testSeatsAvailableBefore should be 1 less then testSeatsAvailableAfter
		 */		
		int i=0,j=0;
		statement=connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = ?");
		statement.setInt(1,1001);
		resultSet=statement.executeQuery();
		while(resultSet.next())
		{
			i=resultSet.getInt(1);
		}
		dao.updateEventNominations(1001);
		PreparedStatement statement2=connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = ?");
		statement2.setInt(1,1001);
		ResultSet resultSet2=statement2.executeQuery();
		while(resultSet2.next())
		{
			j=resultSet2.getInt(1);
		}
		
		assertEquals(i,j+1);
	}

	/**
	 * Negative test case for method updateEventNominations
	 * @throws Exception 
	 * @throws FERSGenericException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testUpdateEventNominations_Negative() throws ClassNotFoundException, FERSGenericException, Exception {
		/**
		 * @TODO: Call updateEventNominations for incorrect eventid and it should
		 * throw an exception
		 */	
		int i=0,j=0;
		statement=connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = ?");
		statement.setInt(1,2000);
		resultSet=statement.executeQuery();
		while(resultSet.next())
		{
			i=resultSet.getInt(1);
		}
		dao.updateEventNominations(2000);
		PreparedStatement statement2=connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = ?");
		statement2.setInt(1,2000);
		ResultSet resultSet2=statement2.executeQuery();
		while(resultSet2.next())
		{
			j=resultSet2.getInt(1);
		}
		
		
		
	}

	/**
	 * Positive test case for method checkEventsofVisitor
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testCheckEventsOfVisitor_Positive() throws ClassNotFoundException, SQLException {
		/**
		 * @TODO: Create visitor object by setting appropriate values
		 * Call checkEventsofVisitor method by passing this visitor object and
		 * valid eventId
		 * Assert the value of return type 
		 */		
		Visitor visitor=new Visitor();
		visitor.setVisitorId(1005);
		dao.checkEventsofVisitor(visitor, 1001);
		
		assertEquals(true,dao.checkEventsofVisitor(visitor, 1001));
		
		
	}

}
