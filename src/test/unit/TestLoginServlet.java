package test.unit;

import static org.junit.Assert.*;

import org.data.service.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.data.server.LoginServlet;
 
import org.springframework.test.util.ReflectionTestUtils;

public class TestLoginServlet {
	
	private LoginServlet loginObj ;
	
	 @Mock
	 private HttpServletRequest request;
	 @Mock
	 private HttpServletResponse response;
	 @Mock
	 private HttpSession session;

	 @Mock
	 private RequestDispatcher rd;
	 
	 @Mock
	 private UserService serviceMock;
	 
	 //init mocks
	 
	 @Before
	 public void setUp() throws Exception {
	  MockitoAnnotations.initMocks(this);
	  //create the mock object
      serviceMock = mock(UserService.class);
      loginObj = new LoginServlet();
    //Inject the mock in the controller
      ReflectionTestUtils.setField(loginObj, "service", serviceMock);
	 }


	@Test
	@Ignore
	public void testDestroy() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoginServlet() {		
		
		assertNotNull(loginObj);
	}

	@Test
	@Ignore
	public void testInitServletConfig() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() {
		  
		//configure mocks to play back the values
		  when(request.getParameter("user")).thenReturn("ashok");
		  when(request.getParameter("password")).thenReturn("morYa");
		  when(request.getParameter("rememberMe")).thenReturn("Yes-SuRe");
		  when(request.getSession()).thenReturn(session);
		  when(request.getRequestDispatcher("/user.do")).thenReturn(rd);
		  
		  //configure the service mock to have expected behivour
		  when(serviceMock.validateUser("ashok", "morYa")).thenReturn(true).thenReturn(false);

		  StringWriter sw = new StringWriter();
		  PrintWriter pw = new PrintWriter(sw);
		  
		  try {
			when(response.getWriter()).thenReturn(pw);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}  
		   
		  
		  //Invoke the method to test on LoginServlet object
		  
		  try {
			loginObj.doPost(request, response);
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  
		  //Verify the session attribute value
		  verify(session).setAttribute("user", "ashok");
		  
		  try {
			verify(rd).forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  String result = sw.getBuffer().toString().trim();

		  System.out.println("Result: "+result);
		  
		  assertEquals("Login successfull...", result);
		 }	
	
	@Test
	public void testDoGetHttpServletRequestHttpServletResponse() {
		  
		 
		  StringWriter sw = new StringWriter();
		  PrintWriter pw = new PrintWriter(sw);			  
		  
		  
		  try {
			when(response.getWriter()).thenReturn(pw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  //Invoke the method to test on LoginServlet object
		  
		  loginObj.doGet(request, response);			  
		  
		  
		  String result = sw.getBuffer().toString().trim();

		  System.out.println("Result: "+result);
		  
		  assertEquals("We donot expose get method", result);
		 }	

}
