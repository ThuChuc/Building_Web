package com.example.asm2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "san_pham")
public class SanPham {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "ma_san_pham")
    private String maSp;
    @Column(name = "ten_san_pham")
    private String tenSp;
    @Column(name = "trang_thai")
    private String trangThai;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngay_tao")
    private Date ngayTao;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngay_sua")
    private Date ngaySua;
    @ManyToOne
    @JoinColumn(name = "id_danh_muc")
    private DanhMuc danhMuc;

    @Override
    public String toString() {
        return "SanPham{" +
                "id=" + id +
                ", maSp='" + maSp + '\'' +
                ", tenSp='" + tenSp + '\'' +
                ", trangThai='" + trangThai + '\'' +
                ", ngayTao=" + ngayTao +
                ", ngaySua=" + ngaySua +
                ", danhMuc=" + danhMuc +
                '}';
    }
}
