package com.example.demo.service.serviceimpl;

import com.example.demo.entity.PhanLoai;
import com.example.demo.repository.PhanLoaiRepository;
import com.example.demo.service.PhanLoaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class PhanLoaiServiceImpl implements PhanLoaiService {
    @Autowired
    PhanLoaiRepository responsitory;
    @Override
    public ArrayList<PhanLoai> getAll() {
        return (ArrayList<PhanLoai>) responsitory.findAll();
    }

    @Override
    public void save(PhanLoai category) {
        responsitory.saveAndFlush(category);
    }

    @Override
    public void delete(UUID id) {
        responsitory.deleteById(id);
    }

    @Override
    public void update(UUID id, PhanLoai category) {
        PhanLoai a = getOne(id);
        a.setTenPhanLoai(category.getTenPhanLoai());
        a.setTrangThai(category.getTrangThai());
        responsitory.flush();
    }

    @Override
    public PhanLoai getOne(UUID id) {
        Optional<PhanLoai> categoryOptional = responsitory.findById(id);
        if (categoryOptional.isPresent()) {
            return categoryOptional.get();
        } else {
            return categoryOptional.get();
        }
    }
}
