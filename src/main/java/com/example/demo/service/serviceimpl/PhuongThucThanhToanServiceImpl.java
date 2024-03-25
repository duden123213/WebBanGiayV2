package com.example.demo.service.serviceimpl;

import com.example.demo.entity.PhuongThucThanhToan;
import com.example.demo.repository.PhuongThucThanhToanRepository;
import com.example.demo.service.PhuongThucThanhToanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class PhuongThucThanhToanServiceImpl implements PhuongThucThanhToanService {
    @Autowired
    PhuongThucThanhToanRepository paymentRepository;

    @Override
    public PhuongThucThanhToan getOne(UUID uuid) {
        return paymentRepository.findById(uuid).get();
    }
}
