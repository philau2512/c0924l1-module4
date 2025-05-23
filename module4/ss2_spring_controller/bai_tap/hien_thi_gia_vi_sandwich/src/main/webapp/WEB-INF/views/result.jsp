<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Selected Condiments</title>
</head>
<body>
<h1>Selected Condiments</h1>

<c:choose>
    <c:when test="${not empty condiments}">
        <ul>
            <c:forEach items="${condiments}" var="item">
                <li>${item}</li>
            </c:forEach>
        </ul>
    </c:when>
    <c:otherwise>
        <p>No condiments selected.</p>
    </c:otherwise>
</c:choose>

</body>
</html>
