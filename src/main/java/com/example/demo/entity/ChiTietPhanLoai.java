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
@Table(name = "ChiTietPhanLoai")
public class ChiTietPhanLoai {
    @Id
    @Column(name = "IdChiTietPhanLoai")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idChiTietPhanLoai;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @ManyToOne()
    @JoinColumn(
            name = "SanPhamId",
            referencedColumnName = "IdSanPham",
            nullable = true
    )
    private SanPham SanPham;

    @ManyToOne()
    @JoinColumn(
            name = "PhanLoaiId",
            referencedColumnName = "IdPhanLoai",
            nullable = true
    )
    private PhanLoai phanLoai;

    public ChiTietPhanLoai(Integer soLuong, com.example.demo.entity.SanPham sanPham, PhanLoai phanLoai) {
        this.soLuong = soLuong;
        SanPham = sanPham;
        this.phanLoai = phanLoai;
    }
}
