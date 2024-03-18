package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "KichCo")
public class KichCo {
    @Id
    @Column(name = "IdKichCo")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idKichCo;

    @Column(name = "TenKichCo")
    private String tenKichCo;

    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "TrangThai")
    private Integer trangThai;

    public KichCo(String tenKichCo, String moTa, Integer trangThai) {
        this.tenKichCo = tenKichCo;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }
}
