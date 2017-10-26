package com.accenture.adf.businesstier.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.accenture.adf.businesstier.dao.EventDAO;
import com.accenture.adf.businesstier.entity.Event;
import com.accenture.adf.businesstier.entity.Visitor;

/**
 * <br/>
 * CLASS DESCRIPTION: <br/>
 * 
 * A service class that implements the EventFacade. Makes event-related data
 * requests to the EventDAO class <br/>
 * 
 */

public class EventServiceImpl implements EventFacade {

	// LOGGER for handling all exceptions
	private static Logger log = Logger.getLogger(EventServiceImpl.class);
  
	/**
	 * <br/>
	 * METHOD DESCRIPTION: 
	 * Used for displaying all events from DAO <br/>
	 * 
	 * PSEUDOCODE: <br/>
	 * Call showAllEvents () in a new DAO object and keep a reference to the returned List of event objects.<br/>
	 * Catch all possible exceptions and log to the error file the provided exception message.<br/>
	 * If no exceptions occur, return the reference.<br/>
	 * <br/>
	 * 
	 * @return List of  Event Object Arrays
	 *
	 */
	public List<Event> getAllEvents() {
       EventDAO eventDAO=new EventDAO();
       ArrayList<Event> eventList=new ArrayList<Event>();
       try
		{
			eventList=eventDAO.showAllEvents();
		} catch (ClassNotFoundException exception) {
			log.info("Exception is :" + exception.getMessage());
			return null;
		} catch (SQLException exception) {
			log.info("Exception is :" + exception.getMessage());
			return null;
		} catch (Exception exception) {
			log.info("Exception is :" + exception.getMessage());
			return null;
		}
		// TODO:  Add code here.....
		// TODO:  Pseudo-code are in the block comments above this method
		// TODO:  For more comprehensive pseudo-code with details, refer to the Component/Class Detailed Design Document
		
		return eventList; // This return value will vary depending on the coding based on Todos above. It may be ArrayList<Event[]>  
	}

	/**
	 * <br/>
	 * METHOD DESCRIPTION: <br/>
	 * Used for identifying all events for visitor<br/>
	 * 
	 * PSEUDOCODE: <br/>
	 * Call checkEventsofVisitor () in a new DAO object and keep a reference to the returned Boolean value.<br/>
	 * Catch all possible exceptions and log to the error file the provided exception message.<br/>
	 * If no exceptions occur, return the reference.<br/>
	 * <br/>
	 * 
	 * @param visitor (type Visitor)
	 * @param eventid (type int)
	 * 
	 * @return boolean
	 * 
	 */

	public boolean checkEventsofVisitor(Visitor visitor, int eventid) {
		boolean status=false;
		EventDAO eventDao=new EventDAO();
		// TODO:  Add code here.....
		// TODO:  Pseudo-code are in the block comments above this method
		// TODO:  For more comprehensive pseudo-code with details, refer to the Component/Class Detailed Design Document
		try
		{
			status=eventDao.checkEventsofVisitor(visitor, eventid);
		} catch (ClassNotFoundException exception) {
			log.info("Exception is :" + exception.getMessage());
		} catch (SQLException exception) {
			log.info("Exception is :" + exception.getMessage());
		} catch (Exception exception) {
			log.info("Exception is :" + exception.getMessage());
		}	
		return status;
		 // replace "false" with the "status" variable used in the code in 'TODO' section
	}

	/**
	 * <br/>
	 * METHOD DESCRIPTION: <br/>
	 * Used for deleting events for visitor <br/>
	 * 
	 * PSEUDOCODE: <br/>
	 * Call updateEventDeletions () in a new DAO object.<br/>
	 * Catch all possible exceptions and log to the error file the provided exception message.<br/>
	 * <br/>
	 * 
	 * @param eventid (type int)
	 *  
	 */
	
	public void updateEventDeletions(int eventid) {
		EventDAO eventDao=new EventDAO();
		try
		{
			eventDao.updateEventDeletions(eventid);
		} catch (ClassNotFoundException exception) {
			log.info("Exception is :" + exception.getMessage());
		} catch (SQLException exception) {
			log.info("Exception is :" + exception.getMessage());
		} catch (Exception exception) {
			log.info("Exception is :" + exception.getMessage());
		}	
		// TODO:  Add code here.....
		// TODO:  Pseudo-code are in the block comments above this method
		// TODO:  For more comprehensive pseudo-code with details, refer to the Component/Class Detailed Design Document
				
	}

}
