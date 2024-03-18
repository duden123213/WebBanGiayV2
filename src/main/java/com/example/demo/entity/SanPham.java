package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "SanPham")
public class SanPham {
    @Id
    @Column(name = "IdSanPham")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idSanPham;
    @Column(name = "TenSanPham")
    private String tenSanPham;
    @Column(name = "SoLuong")
    private Integer soLuong;
    @Column(name = "DaBan")
    private Integer daBan;
    @Column(name = "LuotThich")
    private Integer luotThich;
    @Column(name = "NgayTao")
    private Date ngayTao;
    @Column(name = "TrangThai")
    private Integer trangThai;
    @Column(name = "MoTa")
    private String moTa;

    @ManyToOne()
    @JoinColumn(
            name = "ThuongHieuId",
            referencedColumnName = "IdThuongHieu",
            nullable = true
    )
    private ThuongHieu thuongHieu;

    public SanPham(String tenSanPham, Integer soLuong, Integer daBan, Integer luotThich, Date ngayTao, Integer trangThai, String moTa, ThuongHieu thuongHieu) {
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.daBan = daBan;
        this.luotThich = luotThich;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.moTa = moTa;
        this.thuongHieu = thuongHieu;
    }
}
