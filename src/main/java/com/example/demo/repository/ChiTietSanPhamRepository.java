package com.example.demo.repository;

import com.example.demo.entity.ChiTietHoaDon;
import com.example.demo.entity.ChiTietSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, UUID> {
    @Query("SELECT a FROM ChiTietSanPham a " +
            "JOIN SanPham b ON a.sanPham.idSanPham = b.idSanPham " +
            "WHERE b.idSanPham = ?1")
    List<ChiTietSanPham> findBySanPhamId(UUID id);
}
