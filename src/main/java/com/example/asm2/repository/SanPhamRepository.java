package com.example.asm2.repository;

import com.example.asm2.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Integer>{
//    List<SanPham> findAllByOrderByNgayTaoDesc();
}
