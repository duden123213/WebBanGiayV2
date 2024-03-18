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
@Table(name = "MauSac")
public class MauSac {
    @Id
    @Column(name = "IdMauSac")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idMauSac;

    @Column(name = "TenMauSac")
    private String tenMauSac;

    @Column(name = "Gia")
    private BigDecimal gia;

    @Column(name = "HinhAnh")
    private String hinhAnh;

    @Column(name = "TrangThai")
    private Integer trangThai;

    @ManyToOne()
    @JoinColumn(
            name = "ThuongHieuId",
            referencedColumnName = "IdThuongHieu",
            nullable = true
    )
    private ThuongHieu thuongHieu;

    public MauSac(String tenMauSac, BigDecimal gia, String hinhAnh, Integer trangThai, ThuongHieu thuongHieu) {
        this.tenMauSac = tenMauSac;
        this.gia = gia;
        this.hinhAnh = hinhAnh;
        this.trangThai = trangThai;
        this.thuongHieu = thuongHieu;
    }
}
