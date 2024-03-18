package com.example.demo.repository;

import com.example.demo.entity.ChiTietHoaDon;
import com.example.demo.entity.HinhAnh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface HinhAnhRepository extends JpaRepository<HinhAnh, UUID> {
    boolean existsByTenHinhAnh(String tenHinhAnh);
    @Query("SELECT a FROM HinhAnh a " +
            "JOIN ChiTietSanPham b ON a.chiTietSanPham.idChiTietSanPham = b.idChiTietSanPham " +
            "WHERE b.idChiTietSanPham = ?1")
    List<HinhAnh> findChiTietSanPhamId(UUID id);
}
