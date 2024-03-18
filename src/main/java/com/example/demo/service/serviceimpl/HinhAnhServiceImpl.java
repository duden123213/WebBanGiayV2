package com.example.demo.service.serviceimpl;

import com.example.demo.entity.HinhAnh;
import com.example.demo.repository.HinhAnhRepository;
import com.example.demo.service.HinhAnhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HinhAnhServiceImpl implements HinhAnhService {
    @Autowired
    HinhAnhRepository responsitory;
    @Override
    public void add(HinhAnh productImage) {
        responsitory.saveAndFlush(productImage);
    }

    @Override
    public void delete(UUID id) {
        responsitory.deleteById(id);
    }

    @Override
    public void update(UUID id, HinhAnh productImage) {
        HinhAnh image = getOne(id);
        image.setTenHinhAnh(productImage.getTenHinhAnh());
        image.setTrangThai(productImage.getTrangThai());
        image.setChiTietSanPham(productImage.getChiTietSanPham());
        responsitory.flush();
    }

    @Override
    public List<HinhAnh> getAll() {
        return responsitory.findAll();
    }

    @Override
    public List<HinhAnh> getByChiTietSanPhamId(UUID id) {
        return responsitory.findChiTietSanPhamId(id);
    }

    @Override
    public HinhAnh getOne(UUID id) {
        return responsitory.findById(id).get();
    }
}
