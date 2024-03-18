package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "GioHangChiTiet")
public class GioHangChiTiet {
    @Id
    @Column(name = "IdGioHangChiTiet")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID IdGioHangChiTiet;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "Gia")
    private BigDecimal Gia;

    @ManyToOne
    @JoinColumn(name = "GioHangId",referencedColumnName = "IdGioHang",nullable = true)
    private GioHang gioHang;

    @ManyToOne
    @JoinColumn(name = "ChiTietSanPhamId",referencedColumnName = "IdChiTietSanPham",nullable = true)
    private ChiTietSanPham chiTietSanPham;
}
