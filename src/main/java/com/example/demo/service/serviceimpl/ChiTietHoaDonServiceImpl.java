package com.example.demo.service.serviceimpl;

import com.example.demo.entity.*;
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
    private ChiTietHoaDonRepository billDetailRepository;

    @Autowired
    private HoaDonServiceImpl billService;

    @Autowired
    private ChiTietSanPhamRepository productDetailRepository;

    @Autowired
    private SanPhamRepository productRepository;

    @Autowired
    private MauSacRepository colorRepository;

    @Override
    public void add(ChiTietHoaDon billDetail) {
        billDetailRepository.save(billDetail);
    }

    @Override
    public void updateProductsForBill(UUID billId, List<ChiTietHoaDon> billDetails) {
        // Lấy thông tin hóa đơn từ billId
        HoaDon hoaDon = billService.getOne(billId);

        if (hoaDon == null) {
            // Xử lý trường hợp không tìm thấy hóa đơn
            return;
        }

        // Xóa tất cả chi tiết hóa đơn hiện tại
        deleteProductsForBill(billId);

        // Thêm hoặc cập nhật chi tiết hóa đơn mới
        for (ChiTietHoaDon detail : billDetails) {
            detail.setHoaDon(hoaDon); // Gán hóa đơn đã lấy cho chi tiết hóa đơn
            billDetailRepository.save(detail);
        }
    }

    @Override
    public void deleteProductsForBill(UUID billId) {
        billDetailRepository.deleteAllByHoaDonId(billId);
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
            ChiTietSanPham productDetail = productDetailRepository.findById(productDetailId).orElse(null);

            if (productDetail != null) {
                UUID productId = productDetail.getSanPham().getIdSanPham();
                SanPham product = productRepository.findById(productId).orElse(null);
                UUID colorId = productDetail.getMauSac().getIdMauSac();
                MauSac color = colorRepository.findById(colorId).orElse(null);

                if (product != null && color != null) {
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
        return billDetailRepository.findById(billDetailId).orElse(null);
    }

    @Override
    public void delete(UUID billDetailId) {
        billDetailRepository.deleteById(billDetailId);
    }
}
