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
@Table(name = "PhanLoai")
public class PhanLoai {
    @Id
    @Column(name = "IdPhanLoai")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idPhanLoai;

    @Column(name = "TenPhanLoai")
    private String tenPhanLoai;


    @Column(name = "TrangThai")
    private Integer trangThai;

    public PhanLoai(String tenPhanLoai, Integer trangThai) {
        this.tenPhanLoai = tenPhanLoai;
        this.trangThai = trangThai;
    }
}
