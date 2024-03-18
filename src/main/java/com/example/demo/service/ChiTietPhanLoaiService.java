package com.example.demo.service;

import com.example.demo.entity.ChiTietPhanLoai;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public interface ChiTietPhanLoaiService {
    void add(ChiTietPhanLoai chiTietPhanLoai);

    void delete(UUID id);

    void update(UUID id, ChiTietPhanLoai chiTietPhanLoai);

    List<ChiTietPhanLoai> getAll();

    ChiTietPhanLoai getOne(UUID id);
}
