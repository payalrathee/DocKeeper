<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DocKeeper - Sign Up</title>
    <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet" />
</head>
<body style="background-color: black;">
    <div id="dk-main-img">
        <img src="<c:url value='/resources/images/main-image.webp' />"/>
    </div>
    <div class="dk-overlay"></div>
    <div id="dk-signin-wrapper">
        <div class="dk-logo">
            DocKeeper
        </div>
        <div id="dk-headline" class="w-500">
            Your All-in-One Document Management Solution
        </div>
        
        <form id ="dk-signin-form" class="w-300" method="post" action="<c:url value='/registerHandler' />">
        
            <input class="dk-input" name="username" placeholder="Enter your username" type="text" value="${requestScope.user.username }"/>
            <div class="dk-msg">${requestScope.valError.get("username")}</div>
          
           <input class="dk-input" name="email" placeholder="Enter your email" type="test" value="${requestScope.user.email }"/>  
           <div class="dk-msg">${requestScope.valError.get("email")}</div>
    
            <input class="dk-input" name="password" placeholder="Enter your password" type="password" required minlength=8 maxlength=8 value="${requestScope.user.password }"/>
            <div class="dk-msg">${requestScope.valError.get("password")}</div>
    
            <input class="dk-btn-round w-100" value="Register" type="submit" />
    
            <a href="<c:url value='/login' />" class="dk-link">Already have an account? Sign in here</a>
            
			<div class="dk-err">${requestScope.error}</div>
			
        </form>
    </div>
</body>
</html>