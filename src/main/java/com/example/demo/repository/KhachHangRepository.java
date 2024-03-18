package com.example.demo.repository;

import com.example.demo.entity.ChiTietHoaDon;
import com.example.demo.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {
    @Query("SELECT c from KhachHang c WHERE c.soDienThoai=?1")
    List<KhachHang> timKiem(String soDienThoai);

    @Query("SELECT c from KhachHang c where c.userName=?1 and c.passWord=?2")
    KhachHang login(String username, String password);

    @Query("SELECT c from KhachHang c where c.userName = ?1")
    KhachHang getKhachHangByName(String userName);

    @Query("SELECT c from KhachHang c where c.userName=?1")
    List<KhachHang> getByUserName(String userName);

    @Query("select c from KhachHang c where c.idKhachHang not in (select d.idKhachHang from KhachHang d where d.userName =?1) and c.userName =?2")
    List<KhachHang> checkUserNameUpdate(String tenHT,String tenSua);
}
