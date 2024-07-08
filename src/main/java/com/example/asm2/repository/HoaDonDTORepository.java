package com.example.asm2.repository;

import com.example.asm2.entity.HDCT;
import com.example.asm2.entity.HoaDonDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonDTORepository extends JpaRepository<HoaDonDTO,Integer> {
    @Query(value =
            "SELECT hd.id, kh.ho_ten, hd.ngay_tao, COALESCE(SUM(hdct.so_luong_mua * hdct.gia_ban), 0) AS tong_tien, hd.trang_thai \n" +
                    "FROM hoa_don hd \n" +
                    "LEFT JOIN hdct ON hd.id = hdct.id_hoa_don\n" +
                    "join khach_hang kh on kh.id = hd.id_khach_hang \n" +
                    "where hd.trang_thai = 'Chua thanh toan'\n" +
                    "GROUP BY hd.id ,kh.ho_ten, hd.ngay_tao, hd.trang_thai;", nativeQuery = true)
    List<HoaDonDTO> findHoaDons();

}
