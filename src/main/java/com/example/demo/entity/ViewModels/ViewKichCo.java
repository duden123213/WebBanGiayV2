package com.example.demo.entity.ViewModels;

import com.example.demo.entity.ThuongHieu;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
@Data
public class ViewKichCo {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;
    private String ten;
    private String moTa;
    private Integer trangThai;
    private UUID chiTietSanPhamId;

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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
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

    public ViewKichCo(UUID id, String ten, String moTa, Integer trangThai, UUID chiTietSanPhamId) {
        Id = id;
        this.ten = ten;
        this.moTa = moTa;
        this.trangThai = trangThai;
        this.chiTietSanPhamId = chiTietSanPhamId;
    }

    public ViewKichCo() {

    }
}
