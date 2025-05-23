<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Calculator</title>
</head>
<body>
<h1>Calculator</h1>
<form action="/calculate" method="post">
    <input type="text" name="num1" value="${num1 != null ? num1 : ''}" />
    <input type="text" name="num2" value="${num2 != null ? num2 : ''}" /><br><br>

    <button type="submit" name="operator" value="addition">Addition(+)</button>
    <button type="submit" name="operator" value="subtract">Subtraction(-)</button>
    <button type="submit" name="operator" value="multiply">Multiplication(X)</button>
    <button type="submit" name="operator" value="divide">Division(/)</button>
</form>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<c:if test="${not empty result}">
    <p>Result ${operation} : ${result}</p>
</c:if>
</body>
</html>
