package com.example.demo.service.serviceimpl;

import com.example.demo.entity.ChiTietHoaDon;
import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.MauSac;
import com.example.demo.entity.SanPham;
import com.example.demo.entity.ViewModels.ViewHoaDonChiTiet;
import com.example.demo.repository.ChiTietHoaDonRepository;
import com.example.demo.repository.ChiTietSanPhamRepository;
import com.example.demo.repository.MauSacRepository;
import com.example.demo.repository.SanPhamRepository;
import com.example.demo.service.ChiTietHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ChiTietHoaDonServiceImpl implements ChiTietHoaDonService {
    @Autowired
    ChiTietHoaDonRepository billDetailRepository;
    @Autowired
    ChiTietSanPhamRepository productDetailResponsitory;
    @Autowired
    SanPhamRepository productResponsitory;
    @Autowired
    MauSacRepository colorResponsitory;


    @Override
    public void add(ChiTietHoaDon billDetail) {
        billDetailRepository.save(billDetail);
    }

    @Override
    public List<ChiTietHoaDon> getAllCTHD() {
        return billDetailRepository.findAll();
    }
    @Override
    public List<ChiTietHoaDon> getAllById(UUID billId) {
        return billDetailRepository.getAllByBillId(billId);
    }

    @Override

    public List<Object[]> getAllSale(Date startDate, Date endDate) {
        return billDetailRepository.getProductSalesData(startDate, endDate);
    }

    @Override
    public List<ViewHoaDonChiTiet> getByBillId(UUID billId) {
        List<ChiTietHoaDon> billDetails = billDetailRepository.getBillDetailByBillId(billId);

        List<ViewHoaDonChiTiet> billDetailViews = new ArrayList<>();

        for (ChiTietHoaDon billDetail : billDetails) {
            UUID productDetailId = billDetail.getChiTietSanPham().getIdChiTietSanPham();
            ChiTietSanPham productDetail = productDetailResponsitory.findById(productDetailId).orElse(null);

            if (productDetail != null) {
                UUID productId = productDetail.getSanPham().getIdSanPham();
                SanPham product = productResponsitory.findById(productId).orElse(null);
                UUID colorId = productDetail.getMauSac().getIdMauSac();
                MauSac color = colorResponsitory.findById(colorId).orElse(null);

                if (product != null && color != null) {
                    // Create a CartDetailView and set its properties
                    ViewHoaDonChiTiet c = new ViewHoaDonChiTiet();
                    c.setId(billDetail.getIdChiTietHoaDon());
                    c.setTenSanPham(product.getTenSanPham());
                    c.setTenMauSac(color.getTenMauSac());
                    c.setSoLuong(billDetail.getSoLuong());
                    c.setGia(billDetail.getGia());
                    c.setHoaDon(billDetail.getHoaDon());
                    c.setChiTietSanPham(billDetail.getChiTietSanPham());

                    billDetailViews.add(c);
                }
            }
        }

        return billDetailViews;
    }


    @Override
    public ChiTietHoaDon getOneCTHD(UUID billDetailId) {
        return billDetailRepository.findById(billDetailId).get();
    }


    @Override
    public void delete(UUID billDetailId) {
        billDetailRepository.deleteById(billDetailId);
    }
}
