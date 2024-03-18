package com.example.demo.service;

import com.example.demo.entity.HoaDon;
import com.example.demo.entity.TrangThaiHoaDon;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public interface TrangThaiHoaDonService {
    HoaDon getBillStatus();

    TrangThaiHoaDon findById(UUID id);

    List<TrangThaiHoaDon> getAllBillTT();
}
