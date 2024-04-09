package com.example.demo.repository;

import com.example.demo.entity.ChiTietHoaDon;
import com.example.demo.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface GioHangChiTIetRepository extends JpaRepository<GioHangChiTiet, UUID> {
    @Query("SELECT a FROM GioHangChiTiet a " +
            "JOIN GioHang b ON a.gioHang.idGioHang = b.idGioHang " +
            "JOIN ChiTietSanPham c ON a.chiTietSanPham.idChiTietSanPham = c.idChiTietSanPham " +
            "WHERE b.khachHang.idKhachHang = ?1")
    List<GioHangChiTiet> getGioHangChiTietByKhachHangId(UUID khachHangId);
    @Query("SELECT a FROM GioHangChiTiet a " +
            "JOIN GioHang b ON a.gioHang.idGioHang = b.idGioHang " +
            "JOIN ChiTietSanPham c ON a.chiTietSanPham.idChiTietSanPham = c.idChiTietSanPham " +
            "WHERE b.khachHang.idKhachHang = :khachHangId AND c.idChiTietSanPham = :chiTietSanPhamId")
    GioHangChiTiet getOneGioHangChiTiet(UUID khachHangId, UUID chiTietSanPhamId);

    long countByGioHang_IdGioHang(UUID idGioHang);
}
