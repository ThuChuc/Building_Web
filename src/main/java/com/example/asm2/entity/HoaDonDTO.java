package com.example.asm2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HoaDonDTO {
    @Id
    private Integer id;
    private String ho_ten;
    private Date ngay_tao;
    private Double tong_tien;
    private String trang_thai;
}
