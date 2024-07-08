<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
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
<body>
<h1 class="text-center">Them san pham</h1>
<div class="col-sm-6 offset-md-3">
    <form action="/san-pham/add" method="post">
        <div class="mb-3">
            <label class="form-label">Id</label>
            <input type="text" class="form-control" name="id" value="${sanPham.id}" readonly>
        </div>
        <div class="mb-3">
            <label class="form-label">Ma san pham</label>
            <input type="text" class="form-control" name="maSp" value="${sanPham.maSp}">
        </div>
        <div class="mb-3">
            <label class="form-label">Ten san pham</label>
            <input type="text" class="form-control" name="tenSp" value="${sanPham.tenSp}">
        </div>
        <div class="mb-3">
            <label class="form-label">Trang thai</label>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="trangThai" value="Active"
                <c:if test="${sanPham.trangThai == 'Active'}">
                        checked
                </c:if>
                >
                <label class="form-check-label">Hoat dong</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="trangThai" value="Inactive"
                <c:if test="${sanPham.trangThai == 'Inactive'}">
                       checked
                </c:if>
                >
                <label class="form-check-label">Ngung hoat dong</label>
            </div>
        </div>
        <div class="mb-3">
            <label class="form-label">Ngay tao</label>
            <input type="date" class="form-control" name="ngayTao" value="${sanPham.ngayTao}">
        </div>
        <div class="mb-3">
            <label class="form-label">Ngay sua</label>
            <input type="date" class="form-control" name="ngaySua" value="${sanPham.ngaySua}">
        </div>
        <div class="mb-3">
            <label class="form-label">Danh muc</label>
            <select class="form-select" aria-label="Default select example" name="danhMuc">
                <c:forEach items="${listDanhMuc}" var="danhMuc">
                    <option value="${danhMuc.id}"
                            <c:if test="${sanPham.danhMuc.id == danhMuc.id}">
                                selected
                            </c:if>
                    >${danhMuc.tenDanhMuc}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <button type="submit" class="btn btn-success">Them san pham</button>
        </div>
    </form>
</div>
</body>
</html>