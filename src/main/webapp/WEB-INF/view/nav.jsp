<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<body>
    <div class="dk-nav">
        <span class="dk-logo">DocKeeper</span>
        <span class="dk-menu-item"><a href="<c:url value='/doc/all' />" class="dk-link">My Docs</a></span>
        <span class="dk-menu-item"><a href="<c:url value='/doc/form' />" class="dk-link">New Doc</a></span>
    </div>
</body>
</html>