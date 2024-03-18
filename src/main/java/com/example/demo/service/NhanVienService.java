package com.example.demo.service;

import com.example.demo.entity.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public interface NhanVienService {
    List<NhanVien> findAll();
    void add(NhanVien nv);
    void delete(UUID id);
    void update(NhanVien nv);
    NhanVien detail(UUID id);
    List<NhanVien> timKiem(String ten,String sdt);
    List<NhanVien> timKiem2(String ten,String sdt);
    List<NhanVien> getByUserName(String username);
    NhanVien login(String username, String password);
    NhanVien checkChucVu(String username);
    Page<NhanVien> listDesc(Integer pageNo);
    List<NhanVien> checkUserNameUpdate(String tenHT,String tenSua);
}
