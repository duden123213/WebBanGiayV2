package com.example.demo.repository;

import com.example.demo.entity.ChiTietHoaDon;
import com.example.demo.entity.ChiTietPhanLoai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ChiTietPhanLoaiRepository extends JpaRepository<ChiTietPhanLoai, UUID> {
}
