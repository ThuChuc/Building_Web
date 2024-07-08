package com.example.asm2.repository;

import com.example.asm2.entity.HDCT;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HDCTRepository extends JpaRepository<HDCT, Integer>{
    List<HDCT> findByHoaDonId(Integer hoaDonId);

    HDCT findByHoaDonIdAndChiTietSanPhamId(Integer hoaDonId, Integer chiTietSanPhamId);



}
