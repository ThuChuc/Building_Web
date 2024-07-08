package com.example.asm2.repository;

import com.example.asm2.entity.HoaDon;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer>{

    List<HoaDon> findHoaDonByTrangThai(String trangThai);
}
