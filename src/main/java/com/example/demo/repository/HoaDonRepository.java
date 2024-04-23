package com.example.demo.repository;

import com.example.demo.entity.ChiTietHoaDon;
import com.example.demo.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;
@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, UUID> {
    @Query("SELECT B FROM HoaDon B WHERE B.ngayTao >= :startDate  AND B.ngayTao <= :endDate " +
            "AND B.trangThaiHoaDon.tenTrangThaiHoaDon ='Confirmed'")
    List<HoaDon> getAllBillsInDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT c from HoaDon c where c.khachHang.idKhachHang = ?1")
    List<HoaDon>getAllByCustomerId(UUID cusId);

}
