package com.example.demo.service;

import com.example.demo.entity.GioHang;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
@Service
public interface GioHangService {
    ArrayList<GioHang> getAll();

    void save(GioHang gioHang);

    void delete(UUID id);

    void update(UUID id, GioHang gioHang);

    GioHang getOne(UUID id);
}
