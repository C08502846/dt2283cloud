<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<%BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService(); %>
<html>
    <head>
        <title>Picture Box</title>
    </head>
    <body>
    <center>
    <img src="picturebox.jpg" alt="title" >
    <h1>Uploaded Images:</h1>
            <table>
                <tr>
                    <td><a href="login">Click here to whatever</a></td>
                </tr>
             </table>
             
        </form>
        
    </body>
</html>