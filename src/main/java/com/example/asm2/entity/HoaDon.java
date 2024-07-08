package com.example.asm2.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hoa_don")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_khach_hang")
    private KhachHang khachHang;
    @Column(name = "trang_thai")
    private String trangThai;
    @Column(name = "ngay_tao")
    private Date ngayTao;
    @Column(name = "ngay_sua")
    private Date ngaySua;
    @Column(name = "dia_chi")
    private String diaChi;
    @Column(name = "so_dien_thoai")
    private String sdt;

    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL)
    private List<HDCT> hdct;

    public double tinhTongTien() {
        double totalTongTien = 0.0;
        if (hdct != null) {
            for (HDCT hdctItem : hdct) {
                totalTongTien += hdctItem.getTongTien();
            }
        }
        return totalTongTien;
    }

}
