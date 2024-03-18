package com.example.demo.service.serviceimpl;

import com.example.demo.entity.GioHang;
import com.example.demo.repository.GioHangRepository;
import com.example.demo.service.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
@Service
public class GioHangServiceImpl implements GioHangService {
    @Autowired
    GioHangRepository gioHangRepository;

    @Override
    public ArrayList<GioHang> getAll() {
        return (ArrayList<GioHang>) gioHangRepository.findAll();
    }

    @Override
    public void save(GioHang gioHang) {
        gioHangRepository.saveAndFlush(gioHang);
    }

    @Override
    public void delete(UUID id) {
        gioHangRepository.deleteById(id);
    }

    @Override
    public void update(UUID id, GioHang gioHang) {
        GioHang a = getOne(id);
        a.setSoLuong(gioHang.getSoLuong());
        a.setTongTien(gioHang.getTongTien());
        a.setTrangThai(gioHang.getTrangThai());
        gioHangRepository.flush();

    }

    @Override
    public GioHang getOne(UUID id) {
        return gioHangRepository.findById(id).get();
    }

}
