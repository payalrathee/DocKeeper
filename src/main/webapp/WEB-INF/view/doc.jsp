<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DocKeeper</title>
</head>
<body>
	<jsp:include page="base.jsp" />
	<c:if test='${doc == null }'>
		<p class="dk-error"> ${error } </p>
	</c:if>
	<c:if test='${doc != null }'>
	    <div class="dk-single-doc dk-container">
	        <div class="dk-doc-right-sb">
	            <div class="dk-rb-item dk-doc-name">
	                ${doc.name }
	            </div>
	            <div class="dk-separator"></div>
	            
	            <div class="dk-rb-item">
	                ${doc.note }
	            </div>
	            <div class="dk-rb-item">
	                <a href="<c:url value='/doc/form?id=${doc.id }' />" class="dk-link dk-btn-round">Update</a>
	            </div>
	            <div class="dk-rb-item">
	                <a href="<c:url value='/${doc.url }' />" style="background:blue" class="dk-link dk-btn-round" download="${doc.name }">Download</a>
	            </div>
	            <div class="dk-rb-item">
	                <a href="<c:url value='/doc/delete?id=${doc.id }' />" style="background:red" class="dk-link dk-btn-round">Delete</a>
	            </div>
	        </div>
	        <div class="dk-doc-preview">
	        	<iframe src="<c:url value='/${doc.url }' />"></iframe>
	        </div>
	    </div>
    </c:if>
</body>
</html>