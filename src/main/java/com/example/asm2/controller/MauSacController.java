package com.example.asm2.controller;

import com.example.asm2.entity.MauSac;
import com.example.asm2.entity.Size;
import com.example.asm2.repository.MauSacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class MauSacController {
    @Autowired
    MauSacRepository mauSacRepository;
    @GetMapping("/mau-sac")
    public String page(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model){
        Pageable pageable = PageRequest.of(pageNo, 5, Sort.unsorted());
        Page<MauSac> page = mauSacRepository.findAll(pageable);
        model.addAttribute("page", page);
        return "/mausac/index";
    }
//    public String hienThi(Model model){
//        List<MauSac> list = mauSacRepository.findAll();
//        model.addAttribute("list", list);
//        return "/mausac/index";
//    }

    @GetMapping("/mau-sac/insert")
    public String insert(){
        return "/mausac/add";
    }

    @PostMapping("/mau-sac/add")
    public String add(@ModelAttribute("mauSac") MauSac mauSac){
        mauSac.setNgayTao(new Date());
        mauSac.setNgaySua(new Date());
        mauSacRepository.save(mauSac);
        return "redirect:/mau-sac";
    }

    @GetMapping("/mau-sac/delete")
    public String delete(@RequestParam("id") Integer id){
        mauSacRepository.deleteById(id);
        return "redirect:/mau-sac";
    }

    @GetMapping("/mau-sac/detail")
    public String detail(@RequestParam("id") Integer id, Model model){
        model.addAttribute("mauSac", mauSacRepository.findById(id).get());
        return "/mausac/detail";
    }

    @PostMapping("/mau-sac/update")
    public String update(@ModelAttribute("mauSac") MauSac mauSac){
        mauSac.setNgayTao(new Date());
        mauSac.setNgaySua(new Date());
        mauSacRepository.save(mauSac);
        return "redirect:/mau-sac";
    }
}
