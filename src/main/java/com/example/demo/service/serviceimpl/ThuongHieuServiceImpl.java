package com.example.demo.service.serviceimpl;

import com.example.demo.entity.ThuongHieu;
import com.example.demo.repository.ThuongHieuRepository;
import com.example.demo.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ThuongHieuServiceImpl implements ThuongHieuService {
    @Autowired
    ThuongHieuRepository responsitory;
    @Override
    public void add(ThuongHieu brand) {
        responsitory.saveAndFlush(brand);
    }

    @Override
    public void delete(UUID id) {
        responsitory.deleteById(id);
    }

    @Override
    public void update(UUID id, ThuongHieu brand) {
        ThuongHieu bra = getOne(id);
        bra.setTenThuongHieu(brand.getTenThuongHieu());
        bra.setHinhAnh(brand.getHinhAnh());
        bra.setTrangThai(brand.getTrangThai());
        responsitory.flush();

    }

    @Override
    public List<ThuongHieu> getAll() {
        return responsitory.findAll();
    }

    @Override
    public Page<ThuongHieu> getAllWithPagination(Pageable pageable) {
        return responsitory.findAll(pageable);
    }

    @Override
    public ThuongHieu getOne(UUID id) {
        return responsitory.findById(id).get();
    }
}
