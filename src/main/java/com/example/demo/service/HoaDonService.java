package com.example.demo.service;

import com.example.demo.entity.HoaDon;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;
@Service
public interface HoaDonService {
    HoaDon add(HoaDon hoaDon);

    HoaDon update(HoaDon hoaDon);

    List<HoaDon> getAll();

    List<HoaDon> getAllByKhachHangId(UUID KhachHangId);

    List<HoaDon> getAllByDate(Date startDate, Date endDate);

    HoaDon getOne(UUID hoaDonId);

    void changeStatus(UUID hoaDonId);

    void delete(UUID HoaDonId);
}
