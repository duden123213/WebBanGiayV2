package com.example.demo.service;

import com.example.demo.entity.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public interface KhachHangService {
    Page<KhachHang> findAll(Integer pageNo);
    List<KhachHang> findAll();
    List<KhachHang> timKiem(String sdt);
    void add(KhachHang khachHang);
    void delete(UUID id);
    void update(KhachHang khachHang);
    KhachHang detail(UUID id);
    KhachHang login(String userName, String passWord);
    KhachHang getKhachHangByName(String userName);
    List<KhachHang> getByUserName(String userName);
    List<KhachHang> checkUserNameUpdate(String tenHT,String tenSua);
}
