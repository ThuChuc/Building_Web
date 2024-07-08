<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<form action="/size/update" method="post">
    <div class="mb-3">
        <label>Id:</label>
        <input type="text" class="form-control" name="id" value="${size.id}" readonly>
    </div>
    <div class="mb-3">
        <label>Mã size:</label>
        <input type="text" class="form-control" name="maSize" value="${size.maSize}">
    </div>

    <div class="mb-3">
        <label>Tên size:</label>
        <input type="text" class="form-control" name="tenSize" value="${size.tenSize}">
    </div>

    <div class="mb-3">
        <label class="form-label">Trạng thái:</label>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="trangThai" id="Active" value="Active"
            <c:if test="${size.trangThai == 'Active'}">
                   checked
            </c:if>>
            <label class="form-check-label">Active</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="trangThai" id="Inactive" value="Inactive"
            <c:if test="${size.trangThai == 'Inactive'}">
                   checked
            </c:if>>
            <label class="form-check-label">Inactive</label>
        </div>
    </div>

    <button type="submit" class="btn btn-success">Update</button>
</form>

</body>
</html>