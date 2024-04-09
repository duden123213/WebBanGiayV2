package com.example.demo.service.serviceimpl;

import com.example.demo.entity.HoaDon;
import com.example.demo.entity.TrangThaiHoaDon;
import com.example.demo.repository.ChiTietHoaDonRepository;
import com.example.demo.repository.HoaDonRepository;
import com.example.demo.repository.TrangThaiHoaDonRepository;
import com.example.demo.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;
@Service
public class HoaDonServiceImpl implements HoaDonService {
    @Autowired
    HoaDonRepository billRepository;
    @Autowired
    ChiTietHoaDonRepository billDetailRepository;

    @Autowired
    TrangThaiHoaDonRepository billStatusRepository;

    @Override
    public HoaDon add(HoaDon bill) {
        return billRepository.save(bill);
    }

    @Override
    public HoaDon update(HoaDon bill) {
        HoaDon bill1 = billRepository.findById(bill.getIdHoaDon()).get();
        bill1.setTenNguoiNhan(bill.getTenNguoiNhan());
        bill1.setTongTien(bill.getTongTien());
        bill1.setSoDienThoai(bill.getSoDienThoai());
        bill1.setDiaChi(bill.getDiaChi());
        return billRepository.save(bill1);
    }

    @Override
    public List<HoaDon> getAll() {
        return billRepository.findAll();
    }

    @Override
    public List<HoaDon> getAllByKhachHangId(UUID cusId) {
        return billRepository.getAllByCustomerId(cusId);
    }

    @Override
    public List<HoaDon> getAllByDate(Date startDate, Date endDate) {
        return billRepository.getAllBillsInDateRange(startDate, endDate);
    }


    @Override
    public HoaDon getOne(UUID billId) {
        return billRepository.findById(billId).get();
    }
    @Override
    public void changeStatus(UUID billId) {
        HoaDon bill = billRepository.findById(billId).get();
        TrangThaiHoaDon billStatusConfirmed = billStatusRepository.findById(UUID.fromString("159b8bc3-5489-47c0-a115-b94a0cf6286f")).get();
        TrangThaiHoaDon billStatusUnconfirmed = billStatusRepository.findById(UUID.fromString("259b8bc3-5489-47c0-a115-b94a0cf6286f")).get();
        System.out.println("Như nào"+bill.toString());
        if (bill.getTrangThaiHoaDon().getIdTrangThaiHoaDon()==billStatusUnconfirmed.getIdTrangThaiHoaDon()) {

            bill.setTrangThaiHoaDon(billStatusConfirmed);
            billRepository.save(bill);
        } else if (bill.getTrangThaiHoaDon().getIdTrangThaiHoaDon()==billStatusConfirmed.getIdTrangThaiHoaDon()) {
            bill.setTrangThaiHoaDon(billStatusRepository.findById(billStatusUnconfirmed.getIdTrangThaiHoaDon()).get());
            billRepository.save(bill);
        }
    }


    @Override
    public void delete(UUID billId) {
        billRepository.delete(billRepository.findById(billId).get());
    }
}
