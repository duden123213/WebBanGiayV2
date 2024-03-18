package com.example.demo.service;

import com.example.demo.entity.SanPham;
import com.example.demo.entity.ViewModels.ViewSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public interface SanPhamService {
    SanPham add(SanPham sanPham);

    void delete(UUID id);

    void update(UUID id, SanPham sanPham);

    ArrayList<ViewSanPham> getAllSanPham();

    Page<ViewSanPham> getAllSanPhamWithPagination(Pageable pageable);

    Page<ViewSanPham> getAllSanPhamByName(String ten, Pageable pageable);

    Page<ViewSanPham> getAllSanPhamByBrand(UUID id, Pageable pageable);

    Page<ViewSanPham> getAllSanPhamByPrice(BigDecimal min, BigDecimal max, Pageable pageable);

    List<SanPham> getAll();

    SanPham getOne(UUID id);
}
