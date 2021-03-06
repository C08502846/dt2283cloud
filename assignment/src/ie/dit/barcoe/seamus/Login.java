package ie.dit.barcoe.seamus;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class Login extends HttpServlet 

{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException 
	{
		resp.setContentType("text/plain");
		
		UserService userService = UserServiceFactory.getUserService();
		Principal myPrincipal = req.getUserPrincipal();
		String emailAddress = null;
		String thisURL = req.getRequestURI();
		String loginURL = userService.createLoginURL(thisURL);
		String logoutURL = userService.createLogoutURL(thisURL);
		String successLogin = "index.jsp";
		resp.setContentType("text/html");
		
		//increase wait time after testing
				
		
		if(myPrincipal == null)
		{			
		    resp.getWriter().println("<center><p> You are not currently logged in</p>");
		    resp.getWriter().println("<p>You can <a href=\""+loginURL+
		    "\">sign in here</a>.</p></center>");
		    
		} // end if not logged in
		
		if(myPrincipal != null) 
		{
			emailAddress = myPrincipal.getName();
		    resp.getWriter().println("<center><p>You are Logged in as (email): "+emailAddress+"</p>");
		    resp.getWriter().println("<p>You can <a href=\"" + logoutURL +
		    "\">sign out</a>.</p></center> ");
		    resp.sendRedirect(successLogin);
		    
		    //redirect to Upload Servlet
		} // end if logged in
	}
}
