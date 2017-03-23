
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
<form action="servlet/login" method="post">
    username<input type="text" name="username" value="<%=session.getAttribute("customers") %>">
    <br>password
    <input type="password" name="password">
    <br>
   <%-- <input type="hidden" name="method" value="validate">--%>
    <input type="submit"  name="method" value="validate">
    <input type="submit"  name="method" value="insertDate">
    <input type="reset" value="cancel"/>
</form>
</body>
</html>
