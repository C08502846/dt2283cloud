package ie.dit.barcoe.seamus;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class Upload extends HttpServlet 

{
	private static final Logger log = Logger.getLogger(Upload.class.getName());
	private static final long serialVersionUID = 1L;
	private BlobstoreService blobstoreService =	BlobstoreServiceFactory.getBlobstoreService();
	private BlobInfoFactory bif = new BlobInfoFactory();
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException 
			{
		        UserService userService = UserServiceFactory.getUserService();
		        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		        ImagesService ImageServiceObject = ImagesServiceFactory.getImagesService();
		        
		        @SuppressWarnings("deprecation")
		        Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);		        
		        BlobKey blobKey = blobs.get("myFile");  
		        
		        if (blobKey == null) 
		        {
		        	res.sendRedirect("/");
		        } 
		        else
		        {
		        	User user = userService.getCurrentUser();
		            Date date = new Date();	
		            String description = req.getParameter("description");
		            String imageUrl =ImageServiceObject.getServingUrl(blobKey);
		            String imageName  = bif.loadBlobInfo(blobKey).getFilename();			        
		            String uploadImageFoo = "foo";					
					
			        Key imageKey = KeyFactory.createKey("image", imageName); // create a primary Key for datastore entity called imageName 
			       				
					Entity UploadImageEntity = new Entity("UploadedImage", imageName);	
			        	       
			        UploadImageEntity.setProperty("user", user); // Setting the properties of Entity called UploadImageEntity
			        UploadImageEntity.setProperty("date", date);
			        UploadImageEntity.setProperty("description", description);			        
					UploadImageEntity.setProperty("imageUrl", imageUrl);
					UploadImageEntity.setProperty("imageName", imageName);
					UploadImageEntity.setProperty("blobkey", blobKey);
			       
			        datastore.put(UploadImageEntity);				
					
		        	System.out.println("Uploaded a file with blobKey: "+blobKey.getKeyString()); // printing blobkey 
		        	System.out.println("UploadedImageEntity: "+UploadImageEntity); // Printing Entity to console
		        	res.sendRedirect("/serve?blob-key=" + blobKey.getKeyString());		        	
		        }
		        
//		        String content = req.getParameter("content");
//		        if (content == null) {
//		            content = "(No greeting)";
//		        }
//		        if (user != null) {
//		            log.info("Comment posted by user " + user.getNickname() + ": " + content);
//		        } else {
//		            log.info("Comment posted anonymously: " + content);
//		        }
//		        res.sendRedirect("/index.jsp");
		        
		        
}
}