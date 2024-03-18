package com.example.demo.service;

import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.ViewModels.ViewSanPhamChiTiet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public interface ChiTietSanPhamService {
    void add(ChiTietSanPham chiTietSanPham);
    void delete(UUID id);
    void update(UUID id, ChiTietSanPham chiTietSanPham);
    ArrayList<ViewSanPhamChiTiet> getAllProductDetail();
    ViewSanPhamChiTiet getProductDetailById(UUID id);
    List<ChiTietSanPham> getAll();
    ChiTietSanPham getOne(UUID id);
}
