package com.accenture.adf.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.adf.businesstier.controller.VisitorController;
import com.accenture.adf.businesstier.dao.VisitorDAO;
import com.accenture.adf.businesstier.entity.Visitor;

/**
 * Junit test case to test the class VisitorController
 *
 */
public class TestVisitorController {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private MockHttpSession session;
	private ModelAndView modelAndView;
	private VisitorController controller;
	private VisitorDAO visitorDao;
	private Visitor visitor;

	/**
	 * Set up initial methods required before execution of every method
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		modelAndView = new ModelAndView();
		controller = new VisitorController();
		session = new MockHttpSession();
		response = new MockHttpServletResponse();
		visitorDao =  new VisitorDAO();
		visitor=new Visitor();
	}

	/**
	 * Deallocate objects after execution of every method
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		request=null;
		response=null;
		session=null;
		modelAndView=null;
		controller=null;
		visitorDao=null;
	}

	/**
	 * Positive test case to test the method newVisitor
	 */
	@Test
	public void testNewVisitor_Positive() {
		try {
			request = new MockHttpServletRequest("GET", "/newVistor.htm");
			
			request.setParameter("USERNAME", "ylee");
			request.setParameter("PASSWORD", "password");
			request.setParameter("FIRSTNAME", "TestVFname");
			request.setParameter("LASTNAME", "lname");
			request.setParameter("EMAIL", "mail");
			request.setParameter("PHONENO", "11111");
			request.setParameter("ADDRESS", "testAddress");
			modelAndView = controller.newVisitor(request, response);
		} catch (Exception exception) {
			fail("Exception");
		}
		assertEquals("/registration.jsp", modelAndView.getViewName());
	}

	/**
	 * Negative test case to test the method newVisitor
	 */
	@Test
	public void testNewVisitor_Negative() {
		/**
		 * @TODO: Call newVisitor method by passing request object as null and 
		 * asserting the model view name
		 */	
		try {
			request = new MockHttpServletRequest("GET", "/newVistor.htm");
			
			request.setParameter("USERNAME", "ylee");
			request.setParameter("PASSWORD", "password");
			request.setParameter("FIRSTNAME", "TestVFname");
			request.setParameter("LASTNAME", "lname");
			request.setParameter("EMAIL", "mail");
			request.setParameter("PHONENO", "11111");
			request.setParameter("ADDRESS", "testAddress");
			modelAndView = controller.newVisitor(request, response);
		} catch (Exception exception) {
			fail("Exception");
		}
		assertEquals("/registration.jsp", modelAndView.getViewName());
	}
	
	/**
	 * Positive test case to test the method searchVisitor
	 * @throws Exception 
	 */
	@Test
	public void testSearchVisitor_Positive() throws Exception {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set request parameters for USERNAME and PASSWORD for valid values
		 * Call searchVisitor method and assert model view name 
		 */		
		request=new MockHttpServletRequest("GET","/searchVisitor.htm");
		request.setParameter("USERNAME","bsmith");
		request.setParameter("PASSWORD","password");
		modelAndView=controller.searchVisitor(request, response);
       
		assertEquals("/visitormain.jsp", modelAndView.getViewName());
		
	}
	
	/**
	 * Negative test case of invalid user for method searchVisitor
	 * @throws Exception 
	 */
	@Test
	public void testSearchVisitor_Negative_InvalidUser() throws Exception {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set request parameters for USERNAME and PASSWORD for invalid values
		 * Call searchVisitor method and assert model view name 
		 */		
		request=new MockHttpServletRequest("GET","/searchVisitor.htm");
		request.setParameter("USERNAME","xyz");
		request.setParameter("PASSWORD","password");
		modelAndView=controller.searchVisitor(request, response);
       
		assertEquals("/index.jsp", modelAndView.getViewName());
	}


	/**
	 * Negative test case for method searchVisitor
	 * @throws Exception 
	 */
	@Test
	public void testSearchVisitor_Negative() throws Exception {
		/**
		 * @TODO: Call searchVisitor method by passing request object as null and 
		 * asserting the model view name
		 */		
	/*	request=new MockHttpServletRequest("GET","/searchVisitor.htm");
		request.setParameter("USERNAME","bsmith");
		request.setParameter("PASSWORD","password");*/
		modelAndView=controller.searchVisitor(null, response);
       
		assertEquals("/visitormain.jsp", modelAndView.getViewName());
	}
	
	/**
	 * Positive test case for method registerVisitor
	 * @throws Exception 
	 */
	@Test
	public void testRegisterVisitor_Positive() throws Exception {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Set request parameters for USERNAME and PASSWORD for valid values
		 * Call registerVisitor method and assert model view name 
		 */		
		request=new MockHttpServletRequest("GET","/eventreg.htm");
		visitor=visitorDao.searchUser("bsmith", "password");
		session.setAttribute("VISITOR",visitor);
		request.setSession(session);
		request.setParameter("eventId","1001");
		modelAndView=controller.registerVisitor(request, response);
		assertEquals("/visitormain.jsp",modelAndView.getViewName());
		
	}	

	/**
	 * Negaative test case for method registerVisitor
	 * @throws Exception 
	 */
	@Test
	public void testRegisterVisitor_Negative() throws Exception {
		/**
		 * @TODO: Call registerVisitor method by passing request object as null and 
		 * asserting the model view name
		 */	
		/*request=new MockHttpServletRequest("GET","/eventreg.htm");
		visitor=visitorDao.searchUser("bsmith", "password");
		session.setAttribute("VISITOR",visitor);
		request.setParameter("eventId","1005");*/
		modelAndView=controller.registerVisitor(null, response);
		assertEquals("/visitormain.jsp",modelAndView.getViewName());
	}

	/**
	 * Positive test case for method updateVisitor
	 * @throws Exception 
	 */
	@Test
	public void testUpdateVisitor_Positive() throws Exception {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Set request parameters for all valid user values
		 * Call updateVisitor method and assert model view name 
		 */	
		request=new MockHttpServletRequest("GET","/updatevisitor.htm");
		visitor=visitorDao.searchUser("pratik", "pass");
		session.setAttribute("VISITOR",visitor);
		request.setSession(session);
		request.setParameter("username","Prt");
		request.setParameter("password","pass");
		request.setParameter("firstname","Flash");
		request.setParameter("lastname","Cool");
		request.setParameter("email","accn@gm.com");
		request.setParameter("phoneno","105636");
		request.setParameter("address","Hubli");
		modelAndView=controller.updateVisitor(request, response);
		assertEquals("/updatevisitor.jsp", modelAndView.getViewName());	
	}

	/**
	 * Negative test case for method updateVisitor
	 * @throws Exception 
	 */
	@Test
	public void testUpdateVisitor_Negative() throws Exception {
		/**
		 * @TODO: Call updateVisitor method by passing request object as null and 
		 * asserting the model view name
		 */		
		modelAndView=controller.updateVisitor(null, response);
		assertEquals("/updatevisitor.jsp", modelAndView.getViewName());	
	}
	
	/**
	 * Positive test case for method unregisterEvent
	 * @throws Exception 
	 */
	@Test
	public void testUnregisterEvent_Positive() throws Exception {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Set request parameters for all USERNAME, PASSWORD and eventId values
		 * Call unregisterEvent method and assert model view name 
		 */	
		request=new MockHttpServletRequest("GET","/eventunreg.htm");
		visitor=visitorDao.searchUser("npatel","password");
		session.setAttribute("VISITOR",visitor);
		request.setSession(session);
		request.setParameter("eventId","1004");
		modelAndView=controller.unregisterEvent(request, response);
		assertEquals("/visitormain.jsp", modelAndView.getViewName());

	}

	/**
	 * Negative test case for method unregisterEvent
	 * @throws Exception 
	 */
	@Test
	public void testUnregisterEvent_Negative() throws Exception {
		/**
		 * @TODO: Call unregisterEvent method by passing request object as null and 
		 * asserting the model view name
		 */		
		modelAndView=controller.unregisterEvent(null, response);
		assertEquals("/visitormain.jsp", modelAndView.getViewName());
	}

}
