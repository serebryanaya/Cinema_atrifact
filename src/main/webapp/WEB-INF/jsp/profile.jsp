<%@ page import="edu.school21.cinema.models.User" %><%--
  Created by IntelliJ IDEA.
  User: pveeta
  Date: 17.02.2023
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!DOCTYPE html><html>
<head>
    <meta charset=\"UTF-8\" />
    <title>Profile</title>

</head>
<body>
<% User user = (User) request.getSession().getAttribute("user");%>
<h2>firstname " + <%=user.getFirstName()%> + ".</h2>
<h2>lastname " + <%=user.getLastName()%>  + ".</h2>
<h2>phone number " + <%=user.getPhoneNumber()%> + ".</h2>
<a href=\"/\">exit</a>
<p>
    <%= new java.util.Date()%>
</p>
</body>
</html>
}