<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <title>List Pojos</title>
</head>
<body>
<h1>Pojos</h1>
<table border="1">
    <jsp:useBean id="list" scope="request" type="java.util.List<com.github.hiagoboliveira.entity.MyPojo>"/>
    <c:forEach items="${list}" var="item">
        <tr style=>
            <td>${item.id}</td>
            <td>${item.nome}</td>
            <td>${item.descricao}</td>
        </tr>
    </c:forEach>
</table>
<br>
<fieldset>
    <form action="${linkTo[MyPojoController].add}" method="post" id="addForm">
        <span>Nome: </span>
        <input type="input" name="myPojo.nome"  />
        <br><br>
        <span>Desc: </span>
        <input type="input" name="myPojo.descricao"/>
        <br><br>
        <button>Add</button>
    </form>
</fieldset>


<script>
    // Attach a submit handler to the form
    $("#addForm").submit(function (event) {

        // Stop form from submitting normally
        event.preventDefault();

        // Send the data using post
        var posting = $.post($('#addForm').attr("action"), $('#addForm').serialize());

        // Show result
        posting.done(function (data) {
            alert(data)
        });
    });
</script>

</body>
</html>