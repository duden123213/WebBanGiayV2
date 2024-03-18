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
@Table(name = "ChiTietHoaDon")
public class ChiTietHoaDon {
    @Id
    @Column(name = "IdChiTietHoaDon")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID IdChiTietHoaDon;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "Gia")
    private BigDecimal gia;

    @ManyToOne
    @JoinColumn(name = "HoaDonId",referencedColumnName = "IdHoaDon",nullable = true)
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "ChiTietSanPhamId",referencedColumnName = "IdChiTietSanPham",nullable = true)
    private ChiTietSanPham chiTietSanPham;

}
