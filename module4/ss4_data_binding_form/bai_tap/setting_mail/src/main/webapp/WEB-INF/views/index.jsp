<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Settings Form</title>
</head>
<body>
<h2>Settings</h2>

<!-- binding với command object "settings" -->
<form:form action="/save-settings" method="post" modelAttribute="setting">

    <label>Languages:</label>
    <form:select path="language">
        <form:option value="English" label="English"/>
        <form:option value="Vietnamese" label="Vietnamese"/>
        <form:option value="Japanese" label="Japanese"/>
        <form:option value="Chinese" label="Chinese"/>
    </form:select>
    <br/><br/>

    <label>Page Size:</label>
    Show
    <form:select path="pageSize">
        <form:option value="5" label="5"/>
        <form:option value="10" label="10"/>
        <form:option value="15" label="15"/>
        <form:option value="25" label="25"/>
        <form:option value="50" label="50"/>
        <form:option value="100" label="100"/>
    </form:select>
    emails per page
    <br/><br/>

    <label>Spams filter:</label>
    <form:checkbox path="spamFilterEnabled"/> Enable spams filter
    <br/><br/>

    <label>Signature:</label>
    <br/>
    <form:textarea path="signature" rows="5" cols="40"/>

    <br/><br/>
    <input type="submit" value="Update"/>
    <input type="reset" value="Cancel"/>

</form:form>

</body>
</html>
