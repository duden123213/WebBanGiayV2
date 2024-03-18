package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "HinhAnh")
public class HinhAnh {
    @Id
    @Column(name = "IdHinhAnh")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idHinhAnh;


    @Column(name = "TenHinhAnh", unique = true)
    private String tenHinhAnh;

    @Column(name = "TrangThai")
    private Integer trangThai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ChiTietSanPhamId" ,referencedColumnName = "IdChiTietSanPham", nullable = true)
    private ChiTietSanPham chiTietSanPham;

    public HinhAnh(String tenHinhAnh, Integer trangThai, ChiTietSanPham chiTietSanPham) {
        this.tenHinhAnh = tenHinhAnh;
        this.trangThai = trangThai;
        this.chiTietSanPham = chiTietSanPham;
    }
}
