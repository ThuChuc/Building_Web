package com.example.asm2.controller;

import com.example.asm2.entity.DanhMuc;
import com.example.asm2.entity.Size;
import com.example.asm2.repository.DanhMucRepository;
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
public class DanhMucController {
    @Autowired
    DanhMucRepository danhMucRepository;

    @GetMapping("/danh-muc/index")
    public String page(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model){
        Pageable pageable = PageRequest.of(pageNo, 5, Sort.unsorted());
        Page<DanhMuc> page = danhMucRepository.findAll(pageable);
        model.addAttribute("page", page);
        return "/danhmuc/index";
    }
//    public String hienThi(Model model){
//        List<DanhMuc> list = danhMucRepository.findAll();
//        model.addAttribute("list", list);
//        return "/danhmuc/index";
//    }

    @GetMapping("/danh-muc/insert")
    public String insert(){
        return "/danhmuc/add";
    }

    @PostMapping("/danh-muc/add")
    public String add(@ModelAttribute("danhMuc") DanhMuc danhMuc){
        danhMuc.setNgayTao(new Date());
        danhMuc.setNgaySua(new Date());
        danhMucRepository.save(danhMuc);
        return "redirect:/danh-muc/index";
    }

    @GetMapping("/danh-muc/delete")
    public String delete(@RequestParam("id") Integer id){
        danhMucRepository.deleteById(id);
        return "redirect:/danh-muc/index";
    }

    @GetMapping("/danh-muc/detail")
    public String detail(@RequestParam("id") Integer id, Model model){
        model.addAttribute("danhMuc",danhMucRepository.findById(id).get());
        return "/danhmuc/detail";
    }

    @PostMapping("/danh-muc/update")
    public String update(@ModelAttribute DanhMuc danhMuc){
        danhMuc.setNgayTao(new Date());
        danhMuc.setNgaySua(new Date());
        danhMucRepository.save(danhMuc);
        return "redirect:/danh-muc/index";
    }
}
