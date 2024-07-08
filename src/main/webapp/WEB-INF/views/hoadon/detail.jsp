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

<form action="/hoa-don/update" method="post">

    <div class="mb-3">
        <label>Id:</label>
        <input type="text" class="form-control" name="id" value="${hoaDon.id}" readonly>
    </div>

    <div class="mb-3">
        <label for="disabledSelect" class="form-label">Khách hàng</label>
        <select id="disabledSelect" class="form-select" name="khachHang">
            <c:forEach items="${listKH}" var="l">
                <option value="${l.id}"
                        <c:if test="${hoaDon.khachHang.id == l.id}">selected</c:if>>
                        ${l.hoTen}</option>
            </c:forEach>
        </select>
    </div>
    <div class="mb-3">
        <label class="form-label">Địa chỉ</label>
        <input type="text" class="form-control" name="diaChi" value="${hoaDon.diaChi}">
    </div>
    <div class="mb-3">
        <label class="form-label">SDT</label>
        <input type="text" class="form-control" name="soDienThoai" value="${hoaDon.sdt}">
    </div>
    <div class="row">
        <p class="col-4"> Trạng thái
        </p>
        <div class="form-check col-4">
            <input class="form-check-input" type="radio" value="Chưa thanh toán" name="trangThai"
            <c:if test="${hoaDon.trangThai=='Chưa thanh toán'}">
                   checked
            </c:if>>
            <label class="form-check-label">
                Chưa thanh toán
            </label>
        </div>
        <div class="form-check col-4">
            <input class="form-check-input" type="radio" value="Đã thanh toán" name="trangThai"
            <c:if test="${hoaDon.trangThai=='Ðã thanh toán'}">
                   checked
            </c:if>>
            <label class="form-check-label">
                Đã thanh toán
            </label>
        </div>
    </div>

    <button type="submit" class="btn btn-success">Update</button>
</form>
</body>
</html>