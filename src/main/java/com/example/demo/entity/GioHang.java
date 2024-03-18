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
@Table(name = "GioHang")
public class GioHang {
    @Id
    @Column(name = "IdGioHang")
    private UUID idGioHang;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "TongTien")
    private BigDecimal tongTien;

    @Column(name = "TrangThai")
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "KhachHangId", referencedColumnName = "IdKhachHang", nullable = true)
    private KhachHang khachHang;
}
