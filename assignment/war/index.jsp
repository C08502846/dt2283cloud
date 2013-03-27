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
    <h1>Welcome to Picture Box</h1>
        <form action="<%= blobstoreService.createUploadUrl("/upload") %>" method="post" enctype="multipart/form-data">

            <input type="text" name="foo">
            <input type="file" name="myFile">
            <input type="submit" value="Submit">
            <table>
                <tr>
                    <td><a href="logout">Click here to logout</a></td>
                </tr>
             </table>
             
        </form>
        
    </body>
</html>