package com.example.asm2.controller;

import com.example.asm2.entity.DanhMuc;
import com.example.asm2.entity.SanPham;
import com.example.asm2.repository.DanhMucRepository;
import com.example.asm2.repository.SanPhamRepository;
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

@Controller
public class SanPhamController {
    @Autowired
    SanPhamRepository sanPhamRepository;


    //    public String hienThi(Model model){
//        List<SanPham> list = sanPhamRepository.findAll();
//        model.addAttribute("list", list);
//        return "/sanpham/index";
//    }
    @Autowired
    DanhMucRepository danhMucRepository;

    @GetMapping("/san-pham/index")
    public String page(
            @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
            Model model
    ) {
        Pageable pageable = PageRequest.of(pageNo, 3, Sort.unsorted());
        Page<SanPham> page = sanPhamRepository.findAll(pageable);
        model.addAttribute("page", page);
        return "/sanpham/index";
    }

    @GetMapping("/san-pham/insert")
    public String insert(Model model) {
        model.addAttribute("listDanhMuc", danhMucRepository.findAll());
        return "/sanpham/add";
    }

    @PostMapping("/san-pham/add")
    public String add(@ModelAttribute("sanPham") SanPham sanPham){
        sanPham.setNgayTao(new Date());
        sanPham.setNgaySua(new Date());
        sanPhamRepository.save(sanPham);
        return "redirect:/san-pham/index";
    }

    @GetMapping("/san-pham/delete")
    public String delete(@RequestParam("id") Integer id){
        sanPhamRepository.deleteById(id);
        return "redirect:/san-pham/index";
    }

    @GetMapping("/san-pham/detail")
    public String detail(@RequestParam("id") Integer id, Model model){
        model.addAttribute("sanPham", sanPhamRepository.findById(id).get());
        model.addAttribute("listDanhMuc", danhMucRepository.findAll());
        return "/sanpham/detail";
    }

    @PostMapping("/san-pham/update")
    public String update(@ModelAttribute SanPham sanPham){
        sanPham.setNgaySua(new Date());
        sanPhamRepository.save(sanPham);
        return "redirect:/san-pham/hien-thi";
    }
}
