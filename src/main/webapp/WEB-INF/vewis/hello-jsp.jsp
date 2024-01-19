<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>집에 가고 싶습니다</title>
</head>
<body>
    <p>data(EL문법) : ${myData}</p>
    <p>data(java코드) :
        <%
        String getData = (String)request.getAttribute("myData");
        out.print(getData);
        %>
    </p>
</body>
</html>