package com.example.demo.entity.ViewModels;

import com.example.demo.entity.ThuongHieu;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.UUID;

public class ViewMauSac {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;
    private String ten;
    private BigDecimal gia;
    private String hinhAnh;
    private Integer trangThai;
    private UUID chiTietSanPhamId;
    private ThuongHieu thuongHieu;

    public ViewMauSac() {
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public BigDecimal getGia() {
        return gia;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public UUID getChiTietSanPhamId() {
        return chiTietSanPhamId;
    }

    public void setChiTietSanPhamId(UUID chiTietSanPhamId) {
        this.chiTietSanPhamId = chiTietSanPhamId;
    }

    public ThuongHieu getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(ThuongHieu thuongHieu) {
        this.thuongHieu = thuongHieu;
    }
}
