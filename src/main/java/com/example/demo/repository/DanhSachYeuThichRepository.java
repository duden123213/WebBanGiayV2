package com.example.demo.repository;

import com.example.demo.entity.ChiTietHoaDon;
import com.example.demo.entity.DanhSachYeuThich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface DanhSachYeuThichRepository extends JpaRepository<DanhSachYeuThich, UUID> {
    @Query("SELECT a FROM DanhSachYeuThich a " +
            "JOIN KhachHang b ON a.khachHang.idKhachHang = b.idKhachHang " +
            "JOIN SanPham c ON a.sanPham.idSanPham = c.idSanPham " +
            "WHERE b.idKhachHang = :idSanPham")
    List<DanhSachYeuThich> findByCustomerId(UUID id);
    @Query("SELECT a FROM DanhSachYeuThich a " +
            "JOIN KhachHang b ON a.khachHang.idKhachHang = b.idKhachHang " +
            "JOIN SanPham c ON a.sanPham.idSanPham = c.idSanPham " +
            "WHERE a.khachHang.idKhachHang = ?1 AND a.sanPham.idSanPham = ?2")
    Optional<DanhSachYeuThich> getWishListByCustomerLike(UUID customerId, UUID productId);
}
