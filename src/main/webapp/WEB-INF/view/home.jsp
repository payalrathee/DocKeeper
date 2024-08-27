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
	<div class="dk-docs-wrapper dk-container">
		<a href="<c:url value='/doc/form' />" class="dk-btn-round dk-link">Add Doc</a>
		<c:forEach var="doc" items ="${docs }">
	        <div class="dk-wrapper dk-doc">
	            <div class="dk-doc-header">
	                <span class="dk-doc-name">${doc.name }</span>
	                <span class="dk-doc-toolbar">
	                	<a href="<c:url value='/doc/form?id=${doc.id }' />"><i style="color:green" class="fa-solid fa-pen-to-square"></i></a>
	                	<a href="<c:url value='/doc/view?id=${doc.id }' />"><i style="color:blue" class="fa-solid fa-eye"></i></a>
	                    <a href="<c:url value='/doc/delete?id=${doc.id }' />"><i style="color:red" class="fa-solid fa-trash"></i></a>
	                </span>
	            </div>
	            <div class="dk-doc-desc">
					${doc.note }
	            </div>
	        </div>
        </c:forEach>
    </div>
</body>
</html>