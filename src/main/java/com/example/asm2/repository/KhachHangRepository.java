package com.example.asm2.repository;

import com.example.asm2.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Integer>{
    KhachHang findBySdt(String sdt);
    List<KhachHang> findAllBySdt(String sdt);
}
