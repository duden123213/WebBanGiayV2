package com.example.demo.service;

import com.example.demo.entity.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public interface ThuongHieuService {
    void add(ThuongHieu thuongHieu);
    void delete(UUID id);
    void update(UUID id, ThuongHieu thuongHieu);
    List<ThuongHieu> getAll();
    Page<ThuongHieu> getAllWithPagination(Pageable pageable);
    ThuongHieu getOne(UUID id);
}
