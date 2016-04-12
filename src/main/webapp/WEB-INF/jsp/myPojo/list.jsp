<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <style>
        body {
            position: relative;
        }

        #section-1 {
            background-color: #ffffff;
        }

        #section-2 {
            padding-bottom: 10px;
            border-top: 1px solid #ddd;
            background-color: #f9f9f9;
        }
    </style>

    <title>List Pojos</title>
</head>
<body>

<div id="section-1" class="container" style="${list.isEmpty() ? 'display:none' : ''}">
    <h2>Random: #${id}</h2>
    <jsp:useBean id="list" scope="request" type="java.util.List<com.github.hiagoboliveira.entity.MyPojo>"/>
    <h2>Entidades:</h2>
    <table class="table table-striped" id="pojosTable">
        <thead>
        <tr>
            <th>Id</th>
            <th>Nome</th>
            <th>Descrição</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="item">
            <tr style=>
                <td>${item.id}</td>
                <td>${item.nome}</td>
                <td>${item.descricao}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div id="section-2" class="container">
    <h2>Adicionar uma nova entidade</h2>
    <form class="form-horizontal" role="form" action="${linkTo[MyPojoController].add}" method="post" id="addForm">
        <div class="form-group">
            <label class="control-label col-sm-1">Nome:</label>
            <div class="col-sm-4">
                <input required="required" type="text" class="form-control" name="myPojo.nome">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-1">Descrição:</label>
            <div class="col-sm-4">
                <input required="required" type="text" class="form-control" name="myPojo.descricao">
            </div>
        </div>
        <button id="btn-submit" type="submit" class="btn btn-primary col-sm-5">Add</button>
    </form>
</div>

<!-- Modal -->
<div id="sucessModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Sucesso!</h4>
            </div>
            <div class="modal-body">
                <p>Entidade adicionada com sucesso</p>
            </div>
            <div class="modal-footer">
                <button id="btn-modal-ok" type="button" class="btn btn-primary" data-dismiss="modal">Ok</button>
            </div>
        </div>
    </div>
</div>

<script>
    // Attach a submit handler to the form
    $("#addForm").submit(function (event) {

        // Stop form from submitting normally
        event.preventDefault();

        // Send the data using post
        var posting = $.post($('#addForm').attr("action"), $('#addForm').serialize());

        // Show result
        posting.done(function (data) {
            $("#section-1").show();
            $("#sucessModal").modal('show');

            $("[name='myPojo.nome']").val("");
            $("[name='myPojo.descricao']").val("")

            var myJson = data;
            $('#pojosTable tr:last').after("<tr><td>" + myJson.id + "</td><td>" + myJson.nome + "</td><td>" + myJson.descricao + "</td></tr>")
        });

        posting.fail(function (data) {
            alert("Erro ao adicionar entidade! ")
        });
    });
</script>

</body>
</html>
