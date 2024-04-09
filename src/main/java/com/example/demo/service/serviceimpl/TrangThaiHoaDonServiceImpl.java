package com.example.demo.service.serviceimpl;

import com.example.demo.entity.HoaDon;
import com.example.demo.entity.TrangThaiHoaDon;
import com.example.demo.repository.TrangThaiHoaDonRepository;
import com.example.demo.service.TrangThaiHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TrangThaiHoaDonServiceImpl implements TrangThaiHoaDonService {
    @Autowired
    TrangThaiHoaDonRepository billStatusRepository;

    @Override
    public HoaDon getBillStatus() {
        return null;
    }

    @Override
    public TrangThaiHoaDon findById(UUID id) {
        return billStatusRepository.findById(id).get();
    }

    @Override
    public List<TrangThaiHoaDon> getAllBillTT() {
        return billStatusRepository.findAll();
    }
}
