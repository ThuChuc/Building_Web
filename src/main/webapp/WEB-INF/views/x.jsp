
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<style>
    .error-message {
        color: red;
    }
</style>
<body>
<div class="row">
    <div class="col-7">
        <h2>Danh sách hoá đơn</h2>
        <table class="table">
            <thead>
            <tr>
                <td>STT</td>
                <td>ID</td>
                <td>Tên khách hàng</td>
                <td>Ngày tạo</td>
                <td>Tổng tiền</td>
                <td>Trạng Thái</td>
                <td>Chức năng</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.content}" var="s" varStatus="i">
                <tr>
                    <td>${i.index + 1}</td>
                    <td>${s.id}</td>
                    <td>${s.ho_ten}</td>
                    <td>${s.ngay_tao}</td>
                    <td>${s.tong_tien}</td>
                    <td>${s.trang_thai}</td>
                    <td>
                        <a href="/ban-hang/detailHD?id=${s.id}&&pageNo=${page.number }" class="btn btn-success">Detail</a>
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
        <h2>Danh sách hoá đơn chi tiết</h2>
        <table class="table">
            <thead>
            <tr>
                <td>STT</td>
                <td>ID</td>
                <td>Tên sản phẩm</td>
                <td>Số lượng</td>
                <td>Giá bán</td>
                <td>Tổng tiền</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${hoaDonChiTiet}" var="hd" varStatus="i">
                <tr>
                    <td>${i.index + 1}</td>
                    <td>${hd.id}</td>
                    <td>${hd.chiTietSanPham.sanPham.tenSp}</td>
                    <td>${hd.soLuongMua}</td>
                    <td>${hd.giaBan}</td>
                    <td>${hd.soLuongMua * hd.giaBan}</td>
                    <td>
                        <a href="/ban-hang/delete?idhdct=${hd.id}" class="btn btn-success">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-5">
        <h2>Tạo hoá đơn</h2>
        <div class="row">
            <form action="/ban-hang/search" method="get">
                <div>
                    <label class="mb-3 col-3">Số điện thoại</label>
                    <input type="text" class="col-7" name="sdt">
                </div>
                <button type="submit" class="btn btn-success">Search</button>

                <div class="mb-3">
                    <label class="col-3">Tên khách hàng</label>
                    <input type="text" class="col-7" readonly value="${khachHang.hoTen}">
                </div>
            </form>
            <form action="/ban-hang/add" method="post">
<%--                <div class="mb-3">--%>
<%--                    <label class="col-3">Tên khách hàng</label>--%>
<%--                    <input type="text" class="col-7" value="${hoaDon.khachHang.hoTen}" name="id">--%>
<%--                </div>--%>
                <div class="mb-3">
                    <label class="col-3">ID Hoá đơn</label>
                    <input type="text" class="col-7" value="${hoaDon.id}">
                </div>
                <div class="mb-3">
                    <label class="col-3">Tổng tiền</label>
                    <input type="text" class="col-7" value="${hoaDon.tong_tien}">
                </div>
                <div>
                    <button class="btn btn-primary">Tạo hoá đơn</button>
                    <a href="/ban-hang/thanh-toan?idHoaDon=${hoaDon.id}" class="btn btn-primary">Thanh toán</a>
                </div>
            </form>
        </div>
    </div>
</div>
<div>
    <h2>Danh sách chi tiết sản phẩm</h2>
    <table class="table">
        <thead>
        <tr>
            <td>STT</td>
            <td>ID CTSP</td>
            <td>Tên sản phẩm</td>
            <td>Màu sắc</td>
            <td>Size</td>
            <td>Giá bán</td>
            <td>Số lượng tồn</td>
            <td>Trạng Thái</td>
            <td>Chức năng</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${CTSP}" var="sp" varStatus="i">
            <tr>
                <td>${i.index + 1}</td>
                <td>${sp.id}</td>
                <td>${sp.sanPham.tenSp}</td>
                <td>${sp.mauSac.tenMau}</td>
                <td>${sp.size.tenSize}</td>
                <td>${sp.giaBan}</td>
                <td>${sp.soLuongTon}</td>
                <td>${sp.trangThai}</td>
                <td>
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal"
                            data-id="${sp.id}" data-ten="${sp.sanPham.tenSp}">
                        Chọn mua
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Modal -->
    <!-- Modal -->
    <form action="/ban-hang/updateQuantity" method="post">
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Chọn Số Lượng</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="quantity" class="form-label">Số lượng</label>
                            <input type="number" class="form-control" id="quantity" name="quantity" min="1" max="100">
                        </div>
                        <input type="hidden" id="productId" name="productId">
                        <input type="hidden" id="hoaDonId" name="hoaDonId" value="${hoaDon.id}">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            var exampleModal = document.getElementById('exampleModal');
            exampleModal.addEventListener('show.bs.modal', function (event) {
                var button = event.relatedTarget;
                var productId = button.getAttribute('data-id');
                var modalProductId = exampleModal.querySelector('#productId');
                modalProductId.value = productId;
            });
        });
    </script>

</div>
</body>
</html>

