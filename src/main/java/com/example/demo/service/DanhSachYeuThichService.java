package com.example.demo.service;

import com.example.demo.entity.DanhSachYeuThich;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public interface DanhSachYeuThichService {
    void add(DanhSachYeuThich danhSachYeuThich);
    void delete(UUID id);
    void update(UUID id, DanhSachYeuThich danhSachYeuThich);
    void Like(UUID khachHangId, UUID chiTietSanPhamId);
    List<DanhSachYeuThich> getAll();
    Page<DanhSachYeuThich> getAllByCustomerIdWithPagination(UUID id, Pageable pageable);
    DanhSachYeuThich getOne(UUID id);
}
