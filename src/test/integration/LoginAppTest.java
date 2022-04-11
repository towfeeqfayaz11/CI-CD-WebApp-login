package test.integration;

 
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.GenericXmlContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
 
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.xml.sax.SAXException;

import com.data.server.LoginServlet;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.*;
  
 
  
 public class LoginAppTest {     
	 
	 private ServletRunner servletRunner;
 	
    @Before
    public void setUp() {    	
    	 
    	 File webXML = new File("WebContent/WEB-INF/web.xml");
         assertTrue(webXML.exists());     	
         try {
			servletRunner = new ServletRunner(webXML);			
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("The exception is  "+e.getMessage());
			//e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
    	 
   	 
      }
    
    @After
    public void close() {
    	
    	//servletRunner.shutDown();
    	 
      }

    @Test
    public void testLoginPage() throws Exception {       	 
     
    	 
        //sr.registerServlet( "login", LoginServlet.class.getName() );
          ServletUnitClient sc = servletRunner.newClient();
        WebRequest request   = new PostMethodWebRequest( "http://localhost/login" );
        request.setParameter( "user", "viju" );
        request.setParameter( "password", "SeKreT100" );
        WebResponse response = sc.getResponse( request );
        assertNotNull( "No response received", response );
        assertEquals( "content type", "text/plain", response.getContentType() );
        assertEquals( "requested resource", "You selected red", response.getText() );
    }

    
}
