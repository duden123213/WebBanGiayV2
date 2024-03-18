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
@Table(name = "KhachHang")
public class KhachHang {
    @Id
    @Column(name = "IdKhachHang")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idKhachHang;

    @Column(name = "TenKhachHang")
    private String tenKhachHang;

    @Column(name = "NgaySinh")
    private Date ngaySinh;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "SoDienThoai")
    private String soDienThoai;

    @Column(name = "NgayTao")
    private String ngayTao;

    @Column(name = "Email")
    private String email;

    @Column(name = "GioiTinh")
    private Integer gioiTinh;

    @Column(name = "TrangThai")
    private Integer trangThai;

    @Column(name = "UserName")
    private String userName;

    @Column(name = "PassWord")
    private String passWord;
}
