package com.example.demo.service;

import com.example.demo.entity.HinhAnh;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public interface HinhAnhService {
    void add(HinhAnh hinhAnh);
    void delete(UUID id);
    void update(UUID id, HinhAnh hinhAnh);
    List<HinhAnh> getAll();
    List<HinhAnh> getByChiTietSanPhamId(UUID id);
    HinhAnh getOne(UUID id);
}
