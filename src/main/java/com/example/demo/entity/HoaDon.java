package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "HoaDon")
public class HoaDon {
    @Id
    @Column(name = "IdHoaDon")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID IdHoaDon;

    @Column(name = "TenNguoiNhan")
    private String tenNguoiNhan;

    @Column(name = "TongTien")
    private BigDecimal tongTien;

    @Column(name = "SoDienThoai")
    private String soDienThoai;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @ManyToOne
    @JoinColumn(name = "TrangThaiHoaDonId", referencedColumnName = "IdTrangThaiHoaDon", nullable = true)
    private TrangThaiHoaDon trangThaiHoaDon;

    @ManyToOne
    @JoinColumn(name = "NhanVienId", referencedColumnName = "IdNhanVien", nullable = true)
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "KhachHangId", referencedColumnName = "IdKhachHang", nullable = true)
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "PhuongThucThanhToanId", referencedColumnName = "IdPhuongThucThanhToan", nullable = true)
    private PhuongThucThanhToan phuongThucThanhToan;
}
