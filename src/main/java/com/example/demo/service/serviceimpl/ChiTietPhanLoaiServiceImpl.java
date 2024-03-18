package com.example.demo.service.serviceimpl;

import com.example.demo.entity.ChiTietPhanLoai;
import com.example.demo.repository.ChiTietPhanLoaiRepository;
import com.example.demo.service.ChiTietPhanLoaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChiTietPhanLoaiServiceImpl implements ChiTietPhanLoaiService {
    @Autowired
    ChiTietPhanLoaiRepository responsitory;
    @Override
    public void add(ChiTietPhanLoai categoryDetail) {
        responsitory.saveAndFlush(categoryDetail);
    }

    @Override
    public void delete(UUID id) {
        responsitory.deleteById(id);
    }

    @Override
    public void update(UUID id, ChiTietPhanLoai categoryDetail) {
        ChiTietPhanLoai detail = getOne(id);
        detail.setSoLuong(categoryDetail.getSoLuong());
        detail.setSanPham(categoryDetail.getSanPham());
        detail.setPhanLoai(categoryDetail.getPhanLoai());
        responsitory.flush();
    }

    @Override
    public List<ChiTietPhanLoai> getAll() {
        return responsitory.findAll();
    }

    @Override
    public ChiTietPhanLoai getOne(UUID id) {
        return responsitory.findById(id).get();
    }
}
