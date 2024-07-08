package com.example.asm2.service;

import com.example.asm2.entity.HoaDon;
import com.example.asm2.entity.HoaDonDTO;
import com.example.asm2.entity.KhachHang;
import com.example.asm2.repository.HoaDonDTORepository;
import com.example.asm2.repository.HoaDonRepository;
import com.example.asm2.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BanHangService {
    @Autowired
    HoaDonDTORepository hoaDonDTORepository;

    @Autowired
    KhachHangRepository khachHangRepository;

    @Autowired
    HoaDonRepository hoaDonRepository;

    public List<HoaDonDTO> getAll() {
        return hoaDonDTORepository.findHoaDons();
    }

    public Page<HoaDonDTO> findPaginated(Pageable pageable) {
        List<HoaDonDTO> allItems =  getAll();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<HoaDonDTO> list;

        if (allItems.size() < startItem) {
            list = new ArrayList<>();
        } else {
            int toIndex = Math.min(startItem + pageSize, allItems.size());
            list = allItems.subList(startItem, toIndex);
        }
        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), allItems.size());
    }


    public Pageable pageable(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo,5, Sort.unsorted());
        return pageable;
    }

    public HoaDonDTO findHoaDonChuaThanhToanByID(Integer id){

        HoaDonDTO hoaDon = new HoaDonDTO();
        for(HoaDonDTO hd: hoaDonDTORepository.findHoaDons()){
            if(hd.getId() == id){
                hoaDon = hd;
            }
        }
        return hoaDon;
    }

    public HoaDonDTO findHoaDonChuaThanhToanBySDT(String sdt){
        HoaDonDTO hoaDon = new HoaDonDTO();
        for(KhachHang kh: khachHangRepository.findAllBySdt(sdt)){
            for(HoaDonDTO hd: hoaDonDTORepository.findHoaDons()){
                if(kh.getHoTen().equalsIgnoreCase(hd.getHo_ten())){
                    hoaDon = hd;
                }
            }
        }
        return hoaDon;
    }
    public KhachHang findByIDHD(Integer idHD){
        KhachHang kh = new KhachHang();
        for(HoaDon hd:hoaDonRepository.findAll()){
            if(hd.getId() == idHD){
                kh = hd.getKhachHang();
            }
        }
        return kh;
    }




}
