package com.example.demo.service;

import com.example.demo.entity.ChiTietHoaDon;
import com.example.demo.entity.ViewModels.ViewHoaDonChiTiet;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public interface ChiTietHoaDonService {
    void add(ChiTietHoaDon billDetail);

    void updateProductsForBill(UUID billId, List<ChiTietHoaDon> billDetails);

    void deleteProductsForBill(UUID billId);

    List<ChiTietHoaDon> getAllCTHD();

    List<ChiTietHoaDon> getAllById(UUID billId);

    List<ViewHoaDonChiTiet> getByBillId(UUID billId);

    List<Object[]> getAllSale(Date startDate, Date endDate);


    ChiTietHoaDon getOneCTHD(UUID billDetailId);

    void delete(UUID billDetailId);

}
