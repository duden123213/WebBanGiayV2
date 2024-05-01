package com.example.demo.service;

import com.example.demo.entity.PhanLoai;
import com.example.demo.entity.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
@Service
public interface PhanLoaiService {
    ArrayList<PhanLoai> getAll();

    void save(PhanLoai phanLoai);

    void delete(UUID id);

    void update(UUID id, PhanLoai phanLoai);

    PhanLoai getOne(UUID id);

    Page<PhanLoai> getAllWithPagination(Pageable pageable);
}

