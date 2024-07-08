<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body class="container">
<form action="/mau-sac/add" method="post">
    <div class="mb-3">
        <label >Mã màu:</label>
        <input type="text"  class="form-control" name="maMau">
    </div>
    <div class="mb-3">
        <label >Tên màu:</label>
        <input type="text"  class="form-control" name="tenMau">
    </div>
    <div class="mb-3">
        <label class="form-label">Trạng thái:</label>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="trangThai" id="Active" value="Active" checked>
            <label class="form-check-label">Active</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="trangThai" id="Inactive" value="Inactive" >
            <label class="form-check-label">Inactive</label>
        </div>
    </div>

    <button type="submit" class="btn btn-success">Add</button>
</form>
</body>
</html>