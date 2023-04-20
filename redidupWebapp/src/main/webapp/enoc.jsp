<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="/enoc" method="post">

    <label>Car Name</label>
    <input type="text" name="name" id="name">

    <button type="submit">Register</button>

</form>

<label>${requestScope.message}</label>

<c:if test="${requestScope.validator eq true}">
<h2>IF ERA TRUE</h2>
</c:if>

</body>
</html>