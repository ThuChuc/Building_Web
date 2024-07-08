package com.example.asm2.controller;

import com.example.asm2.entity.ChiTietSanPham;
import com.example.asm2.entity.HDCT;
import com.example.asm2.entity.Size;
import com.example.asm2.repository.ChiTietSanPhamRepository;
import com.example.asm2.repository.MauSacRepository;
import com.example.asm2.repository.SanPhamRepository;
import com.example.asm2.repository.SizeRepository;
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
public class ChiTietSanPhamController{
    @Autowired
    ChiTietSanPhamRepository chiTietSanPhamRepository;
    @Autowired
    SanPhamRepository sanPhamRepository;
    @Autowired
    MauSacRepository mauSacRepository;
    @Autowired
    SizeRepository sizeRepository;
    @GetMapping("ctsp")
    public String page(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model){
        Pageable pageable = PageRequest.of(pageNo, 5, Sort.unsorted());
        Page<ChiTietSanPham> page = chiTietSanPhamRepository.findAll(pageable);
        model.addAttribute("page", page);
        return "/ctsp/index";
    }
//    public String hienThi(Model model){
//        List<ChiTietSanPham> list = chiTietSanPhamRepository.findAll();
//        model.addAttribute("list", list);
//        return "/ctsp/index";
//    }

    @GetMapping("/ctsp/insert")
    public String insert(Model model) {
        model.addAttribute("listSP", sanPhamRepository.findAll());
        model.addAttribute("listMS", mauSacRepository.findAll());
        model.addAttribute("listS", sizeRepository.findAll());
        System.out.println(sanPhamRepository.findAll().toString());
        return "/ctsp/add";
    }

    @PostMapping("/ctsp/add")
    public String add(@ModelAttribute("hdct") ChiTietSanPham chiTietSanPham) {
        chiTietSanPham.setNgayTao(new Date());
        chiTietSanPham.setNgaySua(new Date());
        chiTietSanPhamRepository.save(chiTietSanPham);
        return "redirect:/ctsp";
    }


    @GetMapping("/ctsp/delete")
    public String delete(@RequestParam("id") Integer id){
        chiTietSanPhamRepository.deleteById(id);
        return "redirect:/ctsp";
    }

    @GetMapping("/ctsp/detail")
    public String detail(@RequestParam("id") Integer id, Model model){
        model.addAttribute("listSP", sanPhamRepository.findAll());
        model.addAttribute("listMS", mauSacRepository.findAll());
        model.addAttribute("listS", sizeRepository.findAll());
        model.addAttribute("ctsp", chiTietSanPhamRepository.findById(id).get());
        return "/ctsp/detail";
    }

    @PostMapping("/ctsp/update")
    public String update(@ModelAttribute ChiTietSanPham chiTietSanPham){
        chiTietSanPham.setNgayTao(new Date());
        chiTietSanPham.setNgaySua(new Date());
        chiTietSanPhamRepository.save(chiTietSanPham);
        return "redirect:/ctsp";
    }
}
