package com.example.demo.entity.ViewModels;

import com.example.demo.entity.KhachHang;
import com.example.demo.entity.SanPham;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
@Data
public class ViewDanhSachYeuThich {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID khachHangId;
    private UUID sanPhamId;
    private UUID chiTietSanPhamId;
    private BigDecimal gia;
    private String hinhAnh;
    private KhachHang khachHang;
    private SanPham sanPham;
}
