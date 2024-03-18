package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "NhanVien")
public class NhanVien {
    @Id
    @Column(name = "IdNhanVien")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idNhanVien;

    @Column(name = "TenNhanVien")
    private String tenNhanVien;

    @Column(name = "UserName")
    private String userName;

    @Column(name = "PassWord")
    private String passWord;

    @Column(name = "HinhAnh")
    private String hinhAnh;

    @Column(name = "NgaySinh")
    private String ngaySinh;

    @Column(name = "GioiTinh")
    private Integer gioiTinh;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "SoDienThoai")
    private String soDienThoai;

    @Column(name = "Email")
    private String email;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "TrangThai")
    private Integer trangThai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "ChucVuId",
            referencedColumnName = "IdChucVu",
            nullable = true
    )
    private ChucVu chucVu;
}
