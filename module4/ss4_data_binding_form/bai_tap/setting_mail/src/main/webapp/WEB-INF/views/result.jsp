<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>Settings Result</title>
  <meta charset="UTF-8"/>
</head>
<body>
<h2>Settings Updated</h2>

<p><strong>Language:</strong> <c:out value="${setting.language}"/></p>
<p><strong>Page Size:</strong> <c:out value="${setting.pageSize}"/></p>
<p><strong>Spam Filter Enabled:</strong> <c:out value="${setting.spamFilterEnabled}"/></p>
<p><strong>Signature:</strong></p>
<pre><c:out value="${setting.signature}"/></pre>

<a href="/">Back to Settings</a>
</body>
</html>
