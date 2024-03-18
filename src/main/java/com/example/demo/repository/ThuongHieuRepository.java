package com.example.demo.repository;

import com.example.demo.entity.ChiTietHoaDon;
import com.example.demo.entity.ThuongHieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ThuongHieuRepository extends JpaRepository<ThuongHieu, UUID> {
}
