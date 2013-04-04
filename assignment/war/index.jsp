<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService(); %>
<html>
    <head>
        <title>Picture Box</title>
    </head>
    <body>
    <%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
      pageContext.setAttribute("user", user);
    %>

    <center>
    <img src="picturebox.jpg" alt="title" >
    <h1>Welcome to Picture Box</h1>
        <form action="<%= blobstoreService.createUploadUrl("/upload") %>" method="post" enctype="multipart/form-data">

            <input type="text" name="foo">
            <input type="file" name="myFile">
            <input type="submit" value="Submit">
            <center>Leave a comment below</center>
            <form action="/upload" method="post">
            <div><textarea name="content" rows="3" cols="50"></textarea></div>
            <div><input type="submit" value="Post Comment" /></div>
            </form>
            
            <table>
                <tr>
                    <td><p>Hello, ${fn:escapeXml(user.nickname)}! 
                    (You can
                    <a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">sign out</a>.)</p>
                    <%
                     } 
                     else 
                     {
                         %>
                         <p>Hello!
                         <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a>
                         to include your name with greetings you post.</p>
                         <%
                     }
                    %></td>
                </tr>
             </table>
             
        </form>
        
    </body>
    
</html>

