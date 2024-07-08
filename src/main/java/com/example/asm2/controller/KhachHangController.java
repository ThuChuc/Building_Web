package com.example.asm2.controller;

import com.example.asm2.entity.KhachHang;
import com.example.asm2.entity.Size;
import com.example.asm2.repository.KhachHangRepository;
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

import java.util.Date;
import java.util.List;

@Controller
public class KhachHangController {
    @Autowired
    KhachHangRepository khachHangRepository;

    @GetMapping("/khach-hang")
    public String page(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model){
        Pageable pageable = PageRequest.of(pageNo, 5, Sort.unsorted());
        Page<KhachHang> page = khachHangRepository.findAll(pageable);
        model.addAttribute("page", page);
        return "/khachhang/index";
    }
//    public String hienThi(Model model){
//        List<KhachHang> list = khachHangRepository.findAll();
//        model.addAttribute("list", list);
//        return "/khachhang/index";
//    }

    @GetMapping("/khach-hang/insert")
    public String insert(){
        return "/khachhang/add";
    }

    @PostMapping("/khach-hang/add")
    public String add(@ModelAttribute("khachHang") KhachHang khachHang){
        khachHang.setNgayTao(new Date());
        khachHang.setNgaySua(new Date());
        khachHangRepository.save(khachHang);
        return "redirect:/khach-hang";
    }

    @GetMapping("/khach-hang/delete")
    public String delete(@RequestParam("id") Integer id){
        khachHangRepository.deleteById(id);
        return "redirect:/khach-hang";
    }

    @GetMapping("/khach-hang/detail")
    public String detail(@RequestParam("id") Integer id, Model model){
        model.addAttribute("kh", khachHangRepository.findById(id).get());
        return "/khachhang/detail";
    }

    @PostMapping("/khach-hang/update")
    public String update(@ModelAttribute KhachHang khachHang){
        khachHang.setNgayTao(new Date());
        khachHang.setNgaySua(new Date());
        khachHangRepository.save(khachHang);
        return "redirect:/khach-hang";
    }
}
