package com.example.demo.repository;

import com.example.demo.entity.ChiTietHoaDon;
import com.example.demo.entity.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, UUID> {
    @Query("SELECT nv from NhanVien nv WHERE nv.tenNhanVien=?1 and nv.soDienThoai=?2")
    List<NhanVien> timKiem(String ten,String sdt);


    @Query("SELECT nv from NhanVien nv WHERE nv.tenNhanVien=?1 and nv.soDienThoai=?2")
    List<NhanVien> timKiem2(String ten,String sdt);

    @Query("SELECT nv from NhanVien nv where nv.userName=?1")
    List<NhanVien> getByUserName(String username);

    @Query("SELECT nv from NhanVien nv where nv.userName=?1 and nv.passWord=?2")
    NhanVien login(String username, String password);

    @Query("SELECT nv from NhanVien nv where nv.userName=?1 and nv.chucVu.tenChucVu='Staff'")
    NhanVien checkChucVu(String username);

    @Query("select nv from NhanVien nv order by nv.ngayTao desc ")
    Page<NhanVien> listDesc(Pageable pageable);

    @Query("select nv from NhanVien nv where nv.idNhanVien not in (select n.idNhanVien from NhanVien n where n.userName =?1) and nv.userName =?2")
    List<NhanVien> checkUserNameUpdate(String tenHT,String tenSua);
}
