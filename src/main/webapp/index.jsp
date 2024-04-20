<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <c:if test="${sessionScope.user != null}">
    <jsp:forward page="home.jsp"></jsp:forward>
	</c:if>

    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DocKeeper - Sign In</title>
    <link href="css/style.css" rel="stylesheet" />
</head>
<body style="background-color: black;">
    <div id="dk-main-img">
        <img src="images/main-image.webp"/>
    </div>
    <div class="dk-overlay"></div>
    <div id="dk-signin-wrapper">
        <div class="dk-logo">
            DocKeeper
        </div>
        <div id="dk-headline" class="w-500">
            Your All-in-One Document Management Solution
        </div>
        <form id ="dk-signin-form" class="w-300" method="post" action="/DocKeeper/login">
        
            <input class="dk-input" name="login" placeholder="Enter your username or email" type="text" value="${requestScope.login }"/>
            <div class="dk-msg">${requestScope.valError.get("username") }</div>
    
            <input class="dk-input" name="password" placeholder="Enter your password" type="password"  minlength=8 maxlength=8 value="${requestScope.password }"/>
            <div class="dk-msg">${requestScope.valError.get("password") }</div>
    
            <input class="dk-btn-round w-100" value="Sign In" type="submit" />
    
            <a href="register.jsp" class="dk-link">New user? Sign up here</a>
    
			<div class="dk-err">${requestScope.error}</div>
			
        </form>
    </div>
</body>
</html>