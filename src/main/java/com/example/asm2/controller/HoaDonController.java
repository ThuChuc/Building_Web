package com.example.asm2.controller;

import com.example.asm2.entity.HoaDon;
import com.example.asm2.entity.KhachHang;
import com.example.asm2.entity.Size;
import com.example.asm2.repository.HoaDonRepository;
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
public class HoaDonController {
    @Autowired
    HoaDonRepository hoaDonRepository;
    @Autowired
    KhachHangRepository khachHangRepository;

    @GetMapping("/hoa-don")
    public String page(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model){
        Pageable pageable = PageRequest.of(pageNo, 5, Sort.unsorted());
        Page<HoaDon> page = hoaDonRepository.findAll(pageable);
        model.addAttribute("page", page);
        return "/hoadon/index";
    }
//    public String hienThi(Model model){
//        List<HoaDon> list = hoaDonRepository.findAll();
//        model.addAttribute("list", list);
//        return "/hoadon/index";
//    }
    @GetMapping("/hoa-don/insert")
    public String insert(Model model){
        model.addAttribute("listKH", khachHangRepository.findAll());
        return "/hoadon/add";
    }

    @PostMapping("/hoa-don/add")
    public String add(@ModelAttribute("hoaDon") HoaDon hoaDon){
        hoaDon.setNgayTao(new Date());
        hoaDon.setNgaySua(new Date());
        hoaDonRepository.save(hoaDon);
        return "redirect:/hoa-don";
    }

    @GetMapping("/hoa-don/delete")
    public String delete(@RequestParam("id") Integer id){
        hoaDonRepository.deleteById(id);
        return "redirect:/hoa-don";
    }

    @GetMapping("/hoa-don/detail")
    public String detail(@RequestParam("id") Integer id, Model model){
        model.addAttribute("hoaDon", hoaDonRepository.findById(id).get());
        return "/hoadon/detail";
    }

    @PostMapping("/hoa-don/update")
    public String update(@ModelAttribute HoaDon hoaDon){
        hoaDon.setNgayTao(new Date());
        hoaDon.setNgaySua(new Date());
        hoaDonRepository.save(hoaDon);
        return "redirect:/hoa-don";
    }
}
