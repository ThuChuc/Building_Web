package com.example.asm2.controller;

import com.example.asm2.entity.HDCT;
import com.example.asm2.entity.HoaDon;
import com.example.asm2.entity.Size;
import com.example.asm2.repository.ChiTietSanPhamRepository;
import com.example.asm2.repository.HDCTRepository;
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
public class HDCTController {
    @Autowired
    HDCTRepository hdctRepository;
    @Autowired
    HoaDonRepository hoaDonRepository;
    @Autowired
    ChiTietSanPhamRepository chiTietSanPhamRepository;

    @GetMapping("/hdct")
    public String page(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model){
        Pageable pageable = PageRequest.of(pageNo, 5, Sort.unsorted());
        Page<HDCT> page = hdctRepository.findAll(pageable);
        model.addAttribute("page", page);
        return "/hdct/index";
    }
//    public String hienThi(Model model) {
//        List<HDCT> list = hdctRepository.findAll();
//        model.addAttribute("list", list);
//        return "/hdct/index";
//    }

    @GetMapping("/hdct/insert")
    public String insert(Model model) {
        model.addAttribute("listHD", hoaDonRepository.findAll());
        model.addAttribute("listCTSP", chiTietSanPhamRepository.findAll());
        return "/hdct/add";
    }

    @PostMapping("/hdct/add")
    public String add(@ModelAttribute("hdct") HDCT hdct) {
        hdct.setNgayTao(new Date());
        hdct.setNgaySua(new Date());
        hdctRepository.save(hdct);
        return "redirect:/hdct";
    }

    @GetMapping("/hdct/delete")
    public String delete(@RequestParam("id") Integer id){
        hdctRepository.deleteById(id);
        return "redirect:/hdct";
    }

    @GetMapping("/hdct/detail")
    public String detail(@RequestParam("id") Integer id, Model model){
        model.addAttribute("listHD", hoaDonRepository.findAll());
        model.addAttribute("listCTSP", chiTietSanPhamRepository.findAll());
        model.addAttribute("cthd", hdctRepository.findById(id).get());
        return "/hdct/detail";
    }

    @PostMapping("/hdct/update")
    public String update(@ModelAttribute HDCT hdct){
        hdct.setNgayTao(new Date());
        hdct.setNgaySua(new Date());
        hdctRepository.save(hdct);
        return "redirect:/hdct";
    }
}
