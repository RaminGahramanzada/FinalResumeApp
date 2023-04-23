<%@ page import="com.company.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 4/22/2023
  Time: 9:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) session.getAttribute("loggedInUser");
%>

<%="Welcome, "+user.getName()%>
