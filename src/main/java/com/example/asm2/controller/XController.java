package com.example.asm2.controller;

import com.example.asm2.entity.*;
import com.example.asm2.repository.*;
import com.example.asm2.service.BanHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class XController {
    private Integer idHoaDon;

    private String sdtTemp;
    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    HoaDonDTORepository hoaDonDTORepository;
    @Autowired
    ChiTietSanPhamRepository chiTietSanPhamRepository;
    @Autowired
    HDCTRepository hdctRepository;
    @Autowired
    KhachHangRepository khachHangRepository;

    @Autowired
    BanHangService banHangService;

    HoaDonDTO hoaDon = new HoaDonDTO();


    KhachHang khachHang = new KhachHang();

    Integer pageNoo = 0;
    List<HDCT> hdctList = new ArrayList<>();


    String soLuongTon;

    @GetMapping("/ban-hang")
    public String page(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model){
//        Pageable pageable = PageRequest.of(pageNo, 5, Sort.unsorted());
//        Page<HoaDon> page = hoaDonRepository.findAll(pageable);

        model.addAttribute("hoaDonChiTiet", hdctList);
        if(hoaDon.getHo_ten()!=null){
            model.addAttribute("hoaDon",hoaDon);
        }
        if(khachHang.getHoTen()!=null){
            model.addAttribute("khachHang",khachHang);
        }


        model.addAttribute("page", banHangService.findPaginated(banHangService.pageable(pageNo+pageNoo)));
        pageNoo = 0;
        List<ChiTietSanPham> chiTietSanPhamList = chiTietSanPhamRepository.findAll();
        model.addAttribute("CTSP", chiTietSanPhamList);


        return "x";
    }

    @GetMapping("/ban-hang/detailHD")
    public String detailHoaDon(@RequestParam("id") Integer id, Model model,@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {
        idHoaDon = id;
        pageNoo = pageNo;
        hoaDon = banHangService.findHoaDonChuaThanhToanByID(id);
        khachHang =  banHangService.findByIDHD(id);
        hdctList = hdctRepository.findByHoaDonId(id);

        return "redirect:/ban-hang";
    }


    @PostMapping("/ban-hang/add")
    public String taoHoaDon(@ModelAttribute("hoaDon") HoaDon hoaDon) {
        KhachHang kh = khachHangRepository.findBySdt(sdtTemp);
        hoaDon.setKhachHang(kh);
        hoaDon.setNgayTao(new Date());
        hoaDon.setNgaySua(new Date());
        hoaDon.setTrangThai("Chua thanh toan");
        hoaDonRepository.save(hoaDon);
        return "redirect:/ban-hang";
    }

    @GetMapping("/ban-hang/thanh-toan")
    public String thanhToanHoaDon(@RequestParam("idHoaDon") Integer hoaDonId) {
        HoaDon hd = hoaDonRepository.findById(hoaDonId).get();
        if (hd != null) {
            hd.setNgaySua(new Date());
            hd.setTrangThai("Da thanh toan");
            // Cập nhật trạng thái của từng mục trong hóa đơn chi tiết
            List<HDCT> hdctList = hdctRepository.findByHoaDonId(hoaDonId);
            for (HDCT hdct : hdctList) {
                hdct.setNgayTao(new Date());
                hdct.setNgaySua(new Date());
                hdct.setTrangThai("Da thanh toan");
                hdctRepository.save(hdct);
            }
            // Lưu cập nhật
            hoaDonRepository.save(hd);
        }
        return "redirect:/ban-hang";
    }



    @GetMapping("/ban-hang/search")
    public String searchBySdt(@RequestParam("sdt") String sdt, Model model) {
        sdtTemp = sdt;
        khachHang = new KhachHang();
        if(khachHangRepository.findBySdt(sdt)!=null){
            khachHang = khachHangRepository.findBySdt(sdt);
        }
        hoaDon = new HoaDonDTO();
        hoaDon.setHo_ten("");

        return "redirect:/ban-hang";
    }


    @GetMapping("/ban-hang/delete")
    public String delete(@RequestParam("idhdct") Integer idhdct
           ) {

        HDCT hdct = hdctRepository.findById(idhdct).get();

        ChiTietSanPham ctsp = chiTietSanPhamRepository.findById(hdct.getChiTietSanPham().getId()).get();


            // Cập nhật số lượng tồn


            int currentQuantity = ctsp.getSoLuongTon();


            int quantityToRestore = hdct.getSoLuongMua();

        ctsp.setSoLuongTon(currentQuantity + quantityToRestore);
            // Lưu cập nhật vào database
            chiTietSanPhamRepository.save(ctsp);
            // Xóa hóa đơn chi tiết
            hdctRepository.deleteById(idhdct);


        hdctList = hdctRepository.findByHoaDonId(idHoaDon);
        hoaDon = banHangService.findHoaDonChuaThanhToanByID(idHoaDon);
        return "redirect:/ban-hang";
    }

    @PostMapping("/ban-hang/updateQuantity")
    public String updateQuantity(@RequestParam("productId") Integer productId,
                                 @RequestParam("quantity") Integer quantity,
                                 @RequestParam("hoaDonId") Integer hoaDonId) {
        // Lấy thông tin chi tiết sản phẩm
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(productId).get();
        if (chiTietSanPham == null) {
            // Xử lý khi sản phẩm không tồn tại
            return "redirect:/ban-hang";
        }

        // Kiểm tra số lượng tồn
        if (chiTietSanPham.getSoLuongTon() < quantity) {
            // Xử lý khi số lượng tồn không đủ
            return "redirect:/ban-hang";
        }

        // Kiểm tra xem sản phẩm đã tồn tại trong hóa đơn chi tiết chưa
        HDCT existingItem = hdctRepository.findByHoaDonIdAndChiTietSanPhamId(hoaDonId, productId);

        if (existingItem != null) {
            // Sản phẩm đã tồn tại trong hóa đơn chi tiết
            // Cập nhật số lượng
            existingItem.setSoLuongMua(existingItem.getSoLuongMua() + quantity);
            // Cập nhật tổng tiền
            existingItem.setTongTien(existingItem.getSoLuongMua() * existingItem.getGiaBan());

            chiTietSanPham.setSoLuongTon(chiTietSanPham.getSoLuongTon()-quantity);
            hdctRepository.save(existingItem);
            chiTietSanPhamRepository.save(chiTietSanPham);
        } else {
            // Sản phẩm chưa tồn tại trong hóa đơn chi tiết, thêm mới
            // Cập nhật số lượng tồn
            chiTietSanPham.setSoLuongTon(chiTietSanPham.getSoLuongTon() - quantity);
            chiTietSanPhamRepository.save(chiTietSanPham);

            // Tạo mới hóa đơn chi tiết
            HDCT hdct = new HDCT();
            // Tính tổng tiền
            Double tongTienHDCT = quantity * chiTietSanPham.getGiaBan();
            hdct.setChiTietSanPham(chiTietSanPham);
            hdct.setSoLuongMua(quantity);
            hdct.setGiaBan(chiTietSanPham.getGiaBan());
            hdct.setTongTien(tongTienHDCT);

            // Tính toán tổng tiền của hóa đơn chi tiết và cập nhật vào đối tượng HDCT
            HoaDon hoaDon = hoaDonRepository.findById(hoaDonId).get();
            if (hoaDon == null) {
                // Xử lý khi hóa đơn không tồn tại
                return "redirect:/ban-hang";
            }
            hdct.setHoaDon(hoaDon);
            hdctRepository.save(hdct);
        }
        return "redirect:/ban-hang/detailHD?id=" + hoaDonId;
    }


}
