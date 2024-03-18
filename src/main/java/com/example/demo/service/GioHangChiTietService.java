package com.example.demo.service;

import com.example.demo.entity.GioHangChiTiet;
import com.example.demo.entity.ViewModels.ViewGioHangChiTiet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public interface GioHangChiTietService {
    ArrayList<GioHangChiTiet> getAll();

    ArrayList<ViewGioHangChiTiet> getGioHangChiTietByCustomerId(UUID khachHangId);

    List<GioHangChiTiet> getGioHangChiTietByCusId(UUID khachHangId);

    GioHangChiTiet getOneGioHangChiTiet(UUID khachHangId, UUID chiTietSanPhamId);

    void save(GioHangChiTiet gioHangChiTiet);

    void delete(UUID id);

    void deleteAll(UUID id);

    void update(UUID id, GioHangChiTiet gioHangChiTiet);

    Optional<GioHangChiTiet> getOne(UUID id);

    boolean checkExistCartDetail(UUID khachHangId, UUID chiTietSanPhamId);
}
