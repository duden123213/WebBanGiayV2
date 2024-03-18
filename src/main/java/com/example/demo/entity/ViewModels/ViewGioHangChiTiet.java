package com.example.demo.entity.ViewModels;

import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.GioHang;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.UUID;

public class ViewGioHangChiTiet {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    private String ten;

    private String tenMau;

    private Integer soLuong;

    private BigDecimal gia;

    private BigDecimal giaSanPham;

    private String hinhAnh;

    private UUID sanPhamId;

    private UUID chiTietSanPhamid;

    private GioHang gioHang;

    private ChiTietSanPham chiTietSanPham;



    public ViewGioHangChiTiet() {

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

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getGia() {
        return gia;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

    public BigDecimal getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(BigDecimal giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public UUID getSanPhamId() {
        return sanPhamId;
    }

    public void setSanPhamId(UUID sanPhamId) {
        this.sanPhamId = sanPhamId;
    }

    public UUID getChiTietSanPhamid() {
        return chiTietSanPhamid;
    }

    public void setChiTietSanPhamid(UUID chiTietSanPhamid) {
        this.chiTietSanPhamid = chiTietSanPhamid;
    }

    public GioHang getGioHang() {
        return gioHang;
    }

    public void setGioHang(GioHang gioHang) {
        this.gioHang = gioHang;
    }

    public ChiTietSanPham getChiTietSanPham() {
        return chiTietSanPham;
    }

    public void setChiTietSanPham(ChiTietSanPham chiTietSanPham) {
        this.chiTietSanPham = chiTietSanPham;
    }
}
