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
@Table(name = "ChucVu")
public class ChucVu {
    @Id
    @Column(name = "IdChucVu")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idChucVu;

    @Column(name = "TenChucVu")
    private String tenChucVu;


    @Column(name = "TrangThai")
    private Integer trangThai;
}
