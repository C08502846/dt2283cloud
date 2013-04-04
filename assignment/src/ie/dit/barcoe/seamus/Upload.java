package ie.dit.barcoe.seamus;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class Upload extends HttpServlet 

{
	private static final Logger log = Logger.getLogger(Upload.class.getName());
	private static final long serialVersionUID = 1L;
	private BlobstoreService blobstoreService = 
			BlobstoreServiceFactory.getBlobstoreService();
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException 
			{
		        UserService userService = UserServiceFactory.getUserService();
	            User user = userService.getCurrentUser();
	            
		        @SuppressWarnings("deprecation")
		        Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
		        BlobKey blobKey = blobs.get("myFile");
		        
		        if (blobKey == null) 
		        {
		        	res.sendRedirect("/");
		        } 
		        else
		        {
		        	System.out.println("Uploaded a file with blobKey: "+blobKey.getKeyString());
		        	res.sendRedirect("/serve?blob-key=" + blobKey.getKeyString());
		        	//display guestbook entry here 
		        }
		        
		        String content = req.getParameter("content");
		        if (content == null) {
		            content = "(No greeting)";
		        }
		        if (user != null) {
		            log.info("Comment posted by user " + user.getNickname() + ": " + content);
		        } else {
		            log.info("Comment posted anonymously: " + content);
		        }
		        res.sendRedirect("/index.jsp");
}
}