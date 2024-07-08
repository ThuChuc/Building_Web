package com.example.asm2.controller;

import com.example.asm2.entity.SanPham;
import com.example.asm2.entity.Size;
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
public class SizeController {
    @Autowired
    SizeRepository sizeRepository;

    @GetMapping("/size")
    public String page(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model){
        Pageable pageable = PageRequest.of(pageNo, 5, Sort.unsorted());
        Page<Size> page = sizeRepository.findAll(pageable);
        model.addAttribute("page", page);
        return "/size/index";
    }
//    public String hienThi(Model model){
//        List<Size> list = sizeRepository.findAll();
//        model.addAttribute("list", list);
//        return "/size/index";
//    }

    @GetMapping("/size/insert")
    public String insert(){
        return "/size/add";
    }

    @PostMapping("/size/add")
    public String add(@ModelAttribute("sanPham") Size size){
       size.setNgayTao(new Date());
       size.setNgaySua(new Date());
       sizeRepository.save(size);
        return "redirect:/size";
    }

    @GetMapping("/size/delete")
    public String delete(@RequestParam("id") Integer id){
        sizeRepository.deleteById(id);
        return "redirect:/size";
    }

    @GetMapping("/size/detail")
    public String detail(@RequestParam("id") Integer id, Model model){
        model.addAttribute("size", sizeRepository.findById(id).get());
        return "/size/detail";
    }

    @PostMapping("/size/update")
    public String update(@ModelAttribute Size size){
        size.setNgaySua(new Date());
        size.setNgaySua(new Date());
        sizeRepository.save(size);
        return "redirect:/size";
    }
}
