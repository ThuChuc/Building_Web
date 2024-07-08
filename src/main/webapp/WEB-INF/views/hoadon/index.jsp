<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Document</title>
</head>
<body class="container">
<h1>Day la trang hoa don</h1>
<a href="/hoa-don/insert" class="btn btn-success mt-5 mb-5">Insert</a>
<table class="table">
    <thead>
    <tr>
        <th>ID</th>
        <th>ID khach hang</th>
        <th>Trạng thái</th>
        <th>Ngay Tao</th>
        <th>Ngay Sua</th>
        <th>Địa chỉ</th>
        <th>SDT</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.content}" var="s" varStatus="i">
        <tr>
            <td>${s.id}</td>
            <td>${s.khachHang.id}</td>
            <td>${s.trangThai}</td>
            <td>${s.ngayTao}</td>
            <td>${s.ngaySua}</td>
            <td>${s.diaChi}</td>
            <td>${s.sdt}</td>
            <td>
                <a href="/hoa-don/delete?id=${s.id}" class="btn btn-danger">Xoa</a>
                <a href="/hoa-don/detail?id=${s.id}" class="btn btn-info">Chi tiet</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div style="display: flex; justify-content: center">
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item">
                <a class="page-link" href="?page=0">&laquo;&laquo;</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="?pageNo=${page.number -1}">Previous</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="?pageNo=${page.number +1}">Next</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="?pageNo=${page.totalPages -1}">&raquo;&raquo;</a>
            </li>
        </ul>
    </nav>
</div>
</body>
</html>