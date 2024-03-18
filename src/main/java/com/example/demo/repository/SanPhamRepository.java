package com.example.demo.repository;

import com.example.demo.entity.ChiTietHoaDon;
import com.example.demo.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, UUID> {
    @Query("SELECT a FROM SanPham a " +
            "JOIN ThuongHieu b ON a.thuongHieu.Id = b.Id " +
            "WHERE UPPER(a.tenSanPham) LIKE CONCAT('%', UPPER(:tenSanPham), '%')")
    List<SanPham> findByTenSanPham(String tenSanPham);
    @Query("SELECT a FROM SanPham a " +
            "JOIN ThuongHieu b ON a.thuongHieu.Id = b.Id " +
            "WHERE b.Id = ?1")
    List<SanPham> findSanPhamByThuongHieu(UUID id);
}
