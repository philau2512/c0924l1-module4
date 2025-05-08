<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Currency Converter</title>
</head>
<body>
<h2>Currency Converter (USD to VND)</h2>
<form action="/convert" method="post">
    <label>Exchange Rate (VND per USD):</label>
    <input type="number" name="rate" step="0.01" required><br><br>
    <label>USD Amount:</label>
    <input type="number" name="usd" step="0.01" required><br><br>
    <input type="submit" value="Convert">
</form>
</body>
</html>