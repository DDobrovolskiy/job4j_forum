<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<form name='edit' action="<c:url value='/posts/${post.id}/edit'/>" method='POST'>
    <table>
        <tr>
            <td>Тема:</td>
            <td><input type='text' name='name' value="${post.name}" required></td>
        </tr>
        <tr>
            <td>Описание:</td>
            <td><input type='text' name='description' value="${post.description}" required/></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="submit" /></td>
        </tr>
    </table>
</form>
</body>
</html>