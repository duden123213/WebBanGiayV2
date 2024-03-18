package com.example.demo.entity.ViewModels;

import com.example.demo.entity.ThuongHieu;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

public class ViewSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String tenSanPham;
    private Integer soLuongCoSan;
    private BigDecimal gia;
    private Integer daBan;
    private Integer luotThich;
    private String hinhAnh;
    private Date ngayTao;
    private Integer trangThai;
    private String moTa;
    private UUID chiTietSanPhamId;
    private ThuongHieu thuongHieu;

    public ViewSanPham() {
    }

    public ViewSanPham(UUID id, String tenSanPham, Integer soLuongCoSan, BigDecimal gia, Integer daBan, Integer luotThich, String hinhAnh, Date ngayTao, Integer trangThai, String moTa, UUID chiTietSanPhamId, ThuongHieu thuongHieu) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.soLuongCoSan = soLuongCoSan;
        this.gia = gia;
        this.daBan = daBan;
        this.luotThich = luotThich;
        this.hinhAnh = hinhAnh;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.moTa = moTa;
        this.chiTietSanPhamId = chiTietSanPhamId;
        this.thuongHieu = thuongHieu;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public Integer getSoLuongCoSan() {
        return soLuongCoSan;
    }

    public void setSoLuongCoSan(Integer soLuongCoSan) {
        this.soLuongCoSan = soLuongCoSan;
    }

    public BigDecimal getGia() {
        return gia;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

    public Integer getDaBan() {
        return daBan;
    }

    public void setDaBan(Integer daBan) {
        this.daBan = daBan;
    }

    public Integer getLuotThich() {
        return luotThich;
    }

    public void setLuotThich(Integer luotThich) {
        this.luotThich = luotThich;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
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
