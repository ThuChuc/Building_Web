<%--<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="container">
<form action="/ctsp/add" method="post">
    <div class="mb-3">
        <label class="form-label">Ten san pham</label>
        <select class="form-select" aria-label="Default select example" name="sanPham">
            <c:forEach items="${listSP}" var="sanPham">
                <option value="${sanPham.id}">${sanPham.tenSp}</option>
            </c:forEach>
        </select>
    </div>
    <div class="mb-3">
        <label class="form-label">Ten mau sac</label>
        <select class="form-select" aria-label="Default select example" name="mauSac">
            <c:forEach items="${listMS}" var="mauSac">
                <option value="${mauSac.id}">${mauSac.tenMau}</option>
            </c:forEach>
        </select>
    </div>
    <div class="mb-3">
        <label class="form-label">Ten size</label>
        <select class="form-select" aria-label="Default select example" name="size">
            <c:forEach items="${listS}" var="size">
                <option value="${size.id}">${size.tenSize}</option>
            </c:forEach>
        </select>
    </div>
    <div class="mb-3">
        <label class="form-label">Gia ban</label>
        <input type="text" class="form-control" name="giaBan">
    </div>
    <div class="mb-3">
        <label class="form-label">So luong ton</label>
        <input type="text" class="form-control" name="soLuongTon">
    </div>
    <div class="mb-3">
        <label class="form-label">Trang thai</label>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="trangThai" value="Hoat dong">
            <label class="form-check-label">Hoat dong</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="trangThai" value="Ngung hoat dong" checked>
            <label class="form-check-label">Ngung hoat dong</label>
        </div>
    </div>
    <div>
        <button type="submit" class="btn btn-success">Them san pham chi tiet</button>
    </div>
</form>
</body>
</html>