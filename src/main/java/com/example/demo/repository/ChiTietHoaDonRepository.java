package com.example.demo.repository;

import com.example.demo.entity.ChiTietHoaDon;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ChiTietHoaDonRepository extends JpaRepository<ChiTietHoaDon, UUID> {
    @Query("SELECT BD.chiTietSanPham.sanPham.tenSanPham, SUM(BD.soLuong) AS TotalQuantitySold\n" +
            "FROM ChiTietHoaDon BD\n" +
            "WHERE BD.hoaDon.ngayTao >= :startDate AND BD.hoaDon.ngayTao <= :endDate\n" +
            "GROUP BY BD.chiTietSanPham.sanPham.tenSanPham")
    List<Object[]> getProductSalesData(
            @Param("startDate") java.sql.Date startDate,
            @Param("endDate") java.sql.Date endDate);

    @Query("SELECT c from ChiTietHoaDon c where c.hoaDon.IdHoaDon = ?1")
    List<ChiTietHoaDon> getAllByBillId(UUID id);

    @Query("SELECT a FROM ChiTietHoaDon a " +
            "JOIN HoaDon b ON a.hoaDon.IdHoaDon = b.IdHoaDon " +
            "JOIN ChiTietSanPham c ON a.chiTietSanPham.idChiTietSanPham = c.idChiTietSanPham " +
            "WHERE b.IdHoaDon = ?1")
    List<ChiTietHoaDon> getBillDetailByBillId(UUID id);

    @Modifying
    @Transactional
    @Query("DELETE FROM ChiTietHoaDon c WHERE c.hoaDon.IdHoaDon = :billId")
    void deleteAllByHoaDonId(UUID billId);
}
