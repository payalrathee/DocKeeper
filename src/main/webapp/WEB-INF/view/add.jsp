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
	<c:if test="${error != null }">${error }</c:if>
    <form class="dk-add-form" method="POST" action="<c:url value='/doc/' />" enctype="multipart/form-data">
        <h1>Add Document</h1>
        <input 
        	type="text"
        	name="name"
        	placeholder="Enter document name"
        	class="dk-input"
        	value="${doc.name != null ? doc.name : ''}"
        />
        	
        <input 
        	type="file" 
        	name="file"
        	class="dk-input"
        />
        
        <textarea 
        	name="note"
        	placeholder="Enter description" 
        	class="dk-textarea dk-input"
        	value="${doc.note != null ? doc.note : ''}"
        	>
        	${doc.note != null ? doc.note : ''}
        </textarea>
        
        <input type="submit" class="dk-btn-round" name="add" value="ADD"/>
    </form>
</body>
</html>