package com.example.demo.service;

import com.example.demo.entity.PhuongThucThanhToan;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public interface PhuongThucThanhToanService {
    PhuongThucThanhToan getOne(UUID uuid);
}
