<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>
<form action="/hdct/update" method="post">

    <div class="mb-3">
        <label class="form-label">ID</label>
        <input type="text" class="form-control" name="id" value="${cthd.id}" readonly>
    </div>

    <div class="mb-3">
        <label  class="form-label">Hóa đơn</label>
        <select class="form-select" name="hoaDon">
            <c:forEach items="${listHD}" var="l">
                <option value="${l.id}" <c:if test="${cthd.hoaDon.id == l.id}"> selected </c:if>
                >${l.id}</option>
            </c:forEach>
        </select>
    </div>

    <div class="mb-3">
        <label  class="form-label">Chi tiết sản phẩm</label>
        <select class="form-select" name="chiTietSanPham">
            <c:forEach items="${listCTSP}" var="l">
                <option value="${l.id}" <c:if test="${cthd.chiTietSanPham.id == l.id}"> selected </c:if>
                >${l.sanPham.tenSp}</option>
            </c:forEach>
        </select>
    </div>

    <div class="mb-3">
        <label class="form-label">Số lượng mua</label>
        <input type="number" class="form-control" name="soLuongMua" value="${cthd.soLuongMua}">
    </div>




    <div class="mb-3">
        <label class="form-label">Giá bán</label>
        <input type="number" class="form-control" name="giaBan" value="${cthd.giaBan}">
    </div>

    <div class="mb-3">
        <label class="form-label">Tổng tiền</label>
        <input type="number" class="form-control" name="tongTien" value="${cthd.tongTien}">
    </div>


    <div class="row">
        <p class="col-4"> Trang thai
        </p>
        <div class="form-check col-4">
            <input class="form-check-input" type="radio" value="Active" name="trangThai"
            <c:if test="${cthd.trangThai=='Active'}"> CHECKED

            </c:if>>
            <label class="form-check-label">
                Active
            </label>
        </div>
        <div class="form-check col-4">
            <input class="form-check-input" type="radio" value="Inactive" name="trangThai"
            <c:if test="${cthd.trangThai=='Inactive'}"> CHECKED

            </c:if>>
            <label class="form-check-label">
                Inactive
            </label>
        </div>
    </div>


    <button type="submit" class="btn btn-primary">Update</button>
</form>
</body>
</html>