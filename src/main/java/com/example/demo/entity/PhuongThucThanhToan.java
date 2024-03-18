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
@Table(name = "PhuongThucThanhToan")
public class PhuongThucThanhToan {
    @Id
    @Column(name = "IdPhuongThucThanhToan")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID IdPhuongThucThanhToan;

    @Column(name = "TenPhuongThucThanhToan")
    private String tenPhuongThucThanhToan;

    @Column(name = "TrangThai")
    private Integer trangThai;
}
