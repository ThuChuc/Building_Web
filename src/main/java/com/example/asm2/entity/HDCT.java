package com.example.asm2.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hdct")
public class HDCT {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_hoa_don")
    private HoaDon hoaDon;
    @ManyToOne
    @JoinColumn(name = "id_ctsp")
    private ChiTietSanPham chiTietSanPham;
    @Column(name = "so_luong_mua")
    private Integer soLuongMua;
    @Column(name = "gia_ban")
    private double giaBan;
    @Column(name = "tong_tien")
    private double tongTien;
    @Column(name = "trang_thai")
    private String trangThai;
    @Column(name = "ngay_tao")
    private Date ngayTao;
    @Column(name = "ngay_sua")
    private Date ngaySua;
}
