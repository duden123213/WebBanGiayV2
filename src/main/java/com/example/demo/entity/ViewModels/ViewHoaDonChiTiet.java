package com.example.demo.entity.ViewModels;

import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.HoaDon;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.UUID;

public class ViewHoaDonChiTiet {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;
    private String tenSanPham;
    private String tenMauSac;
    private Integer soLuong;
    private BigDecimal gia;
    private HoaDon hoaDon;
    private ChiTietSanPham chiTietSanPham;
}
