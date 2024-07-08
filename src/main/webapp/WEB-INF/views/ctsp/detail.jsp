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
<body>
<form action="/ctsp/update" method="post">
    <div class="mb-3">
        <label class="form-label">ID</label>
        <input type="text" class="form-control" name="id" value="${ctsp.id}" readonly>
    </div>

    <div class="mb-3">
        <label  class="form-label">Sản phẩm</label>
        <select class="form-select" name="sanPham">
            <c:forEach items="${listSP}" var="l">
                <option value="${l.id}" <c:if test="${ctsp.sanPham.id == l.id}"> selected </c:if>
                >${l.tenSp}</option>
            </c:forEach>
        </select>
    </div>

    <div class="mb-3">
        <label  class="form-label">Màu sắc</label>
        <select class="form-select" name="mauSac">
            <c:forEach items="${listMS}" var="l">
                <option value="${l.id}" <c:if test="${ctsp.mauSac.id == l.id}"> selected </c:if>
                >${l.tenMau}</option>
            </c:forEach>
        </select>
    </div>

    <div class="mb-3">
        <label  class="form-label">Size</label>
        <select class="form-select" name="size">
            <c:forEach items="${listS}" var="l">
                <option value="${l.id}" <c:if test="${ctsp.size.id == l.id}"> selected </c:if>
                >${l.tenSize}</option>
            </c:forEach>
        </select>
    </div>

    <div class="mb-3">
        <label class="form-label">Giá bán</label>
        <input type="number" class="form-control" name="giaBan" value="${ctsp.giaBan}">
    </div>
    <div class="mb-3">
        <label class="form-label">Số lượng</label>
        <input type="number" class="form-control" name="soLuongTon" value="${ctsp.soLuongTon}">
    </div>


    <div class="row">
        <p class="col-4"> Trang thai
        </p>
        <div class="form-check col-4">
            <input class="form-check-input" type="radio" value="Active" name="trangThai"
            <c:if test="${ctsp.trangThai=='Active'}">
                checked
            </c:if>>
            <label class="form-check-label">
                Active
            </label>
        </div>
        <div class="form-check col-4">
            <input class="form-check-input" type="radio" value="Inactive" name="trangThai"
            <c:if test="${ctsp.trangThai=='Inactive'}">
                checked
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