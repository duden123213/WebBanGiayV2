package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "ChiTietSanPham")
public class ChiTietSanPham {
    @Id
    @Column(name = "IdChiTietSanPham")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idChiTietSanPham;

    @Column(name = "GiaNhap")
    private BigDecimal giaNhap;

    @Column(name = "GiaBan")
    private BigDecimal giaBan;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "TrangThai")
    private Integer trangThai;

    @Column(name = "MoTa")
    private String moTa;

    @ManyToOne()
    @JoinColumn(
            name = "KichCoId",
            referencedColumnName = "IdKichCo",
            nullable = true
    )
    private KichCo kichCo;

    @ManyToOne()
    @JoinColumn(
            name = "SanPhamId",
            referencedColumnName = "IdSanPham",
            nullable = true
    )
    private SanPham sanPham;

    @ManyToOne()
    @JoinColumn(
            name = "MauSacID",
            referencedColumnName = "IdMauSac",
            nullable = true
    )
    private MauSac mauSac;

    @OneToMany(mappedBy = "chiTietSanPham", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HinhAnh> lisHinhAnh = new ArrayList<>();

    public ChiTietSanPham(BigDecimal giaNhap, BigDecimal giaBan, Integer soLuong, Date ngayTao, Integer trangThai, String moTa, KichCo kichCo, SanPham sanPham, MauSac mauSac, List<HinhAnh> lisHinhAnh) {
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.moTa = moTa;
        this.kichCo = kichCo;
        this.sanPham = sanPham;
        this.mauSac = mauSac;
        this.lisHinhAnh = lisHinhAnh;
    }

    public UUID getIdChiTietSanPham() {
        return idChiTietSanPham;
    }

    public void setIdChiTietSanPham(UUID idChiTietSanPham) {
        this.idChiTietSanPham = idChiTietSanPham;
    }

    public BigDecimal getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(BigDecimal giaNhap) {
        this.giaNhap = giaNhap;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
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

    public KichCo getKichCo() {
        return kichCo;
    }

    public void setKichCo(KichCo kichCo) {
        this.kichCo = kichCo;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public MauSac getMauSac() {
        return mauSac;
    }

    public void setMauSac(MauSac mauSac) {
        this.mauSac = mauSac;
    }

    public List<HinhAnh> getLisHinhAnh() {
        return lisHinhAnh;
    }

    public void setLisHinhAnh(List<HinhAnh> lisHinhAnh) {
        this.lisHinhAnh = lisHinhAnh;
    }
}
