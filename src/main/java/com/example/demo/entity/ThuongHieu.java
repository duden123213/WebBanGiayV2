package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "ThuongHieu")
public class ThuongHieu {
    @Id
    @Column(name = "IdThuongHieu")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    @Column(name = "TenThuongHieu")
    private String tenThuongHieu;

    @Column(name = "HinhAnh")
    private String hinhAnh;

    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "TrangThai")
    private Integer trangThai;








    public ThuongHieu(String tenThuongHieu, String hinhAnh, String moTa, Integer trangThai) {

        this.tenThuongHieu = tenThuongHieu;
        this.hinhAnh = hinhAnh;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }


}
