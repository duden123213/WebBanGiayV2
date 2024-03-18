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
@Table(name = "TrangThaiHoaDon")
public class TrangThaiHoaDon {
    @Id
    @Column(name = "IdTrangThaiHoaDon")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID IdTrangThaiHoaDon;

    @Column(name = "TenTrangThaiHoaDon")
    private String tenTrangThaiHoaDon;

    @Column(name = "TrangThai")
    private Integer trangThai;
}
