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
@Table(name = "DanhSachYeuThich")
public class DanhSachYeuThich {
    @Id
    @Column(name = "IdDanhSachYeuThich")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idDanhSachYeuThich;

    @ManyToOne()
    @JoinColumn(
            name = "KhachHangId",
            referencedColumnName = "IdKhachHang",
            nullable = true
    )
    private KhachHang khachHang;

    @ManyToOne()
    @JoinColumn(
            name = "SanPhamId",
            referencedColumnName = "IdSanPham",
            nullable = true
    )
    private SanPham sanPham;

    public DanhSachYeuThich(KhachHang khachHang, SanPham sanPham) {
        this.khachHang = khachHang;
        this.sanPham = sanPham;
    }

}
