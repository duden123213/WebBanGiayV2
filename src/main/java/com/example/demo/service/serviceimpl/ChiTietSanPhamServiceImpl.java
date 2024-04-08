package com.example.demo.service.serviceimpl;

import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.KichCo;
import com.example.demo.entity.MauSac;
import com.example.demo.entity.SanPham;
import com.example.demo.entity.ViewModels.ViewSanPhamChiTiet;
import com.example.demo.repository.ChiTietSanPhamRepository;
import com.example.demo.repository.KichCoRepository;
import com.example.demo.repository.MauSacRepository;
import com.example.demo.repository.SanPhamRepository;
import com.example.demo.service.ChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {
    @Autowired
    ChiTietSanPhamRepository responsitory;
    @Autowired
    SanPhamRepository productResponsitory;
    @Autowired
    MauSacRepository colorResponsitory;
    @Autowired
    KichCoRepository kichCoRepository;

    @Override
    public void add(ChiTietSanPham productDetail) {
        productDetail.setNgayTao(Date.valueOf(LocalDate.now()));
        responsitory.saveAndFlush(productDetail);
    }
    @Override
    public void delete(UUID id) {
        responsitory.deleteById(id);
    }

    @Override
    public void update(UUID id, ChiTietSanPham productDetail) {
        ChiTietSanPham detail = getOne(id);
        detail.setGiaNhap(productDetail.getGiaNhap());
        detail.setGiaBan(productDetail.getGiaBan());
        detail.setSoLuong(productDetail.getSoLuong());
        detail.setTrangThai(productDetail.getTrangThai());
        detail.setMoTa(productDetail.getMoTa());
        detail.setSanPham(productDetail.getSanPham());
        detail.setMauSac(productDetail.getMauSac());
        detail.setKichCo(productDetail.getKichCo());

//        detail.setListImages(productDetail.getListImages());
        responsitory.flush();
    }

    @Override
    public ArrayList<ViewSanPhamChiTiet> getAllProductDetail() {
        List<ChiTietSanPham> productDetails = responsitory.findAll();

        ArrayList<ViewSanPhamChiTiet> pdv = new ArrayList<>();

        for (ChiTietSanPham productDetail : productDetails) {
            SanPham product = productResponsitory.findById(productDetail.getSanPham().getIdSanPham()).get();
            MauSac color = colorResponsitory.findById(productDetail.getMauSac().getIdMauSac()).get();
            KichCo kichCo = kichCoRepository.findById(productDetail.getKichCo().getIdKichCo()).get();

            if (product != null && color != null && product.getThuongHieu() == color.getThuongHieu()) {
                ViewSanPhamChiTiet productDetailView = new ViewSanPhamChiTiet();
                productDetailView.setId(productDetail.getIdChiTietSanPham());
                productDetailView.setTen(product.getTenSanPham());
                productDetailView.setGiaNhap(productDetail.getGiaNhap());
                productDetailView.setGiaBan(productDetail.getGiaBan());
                productDetailView.setSoLuong(productDetail.getSoLuong());
                productDetailView.setSoLuongTon(product.getSoLuong());
                productDetailView.setDaBan(product.getDaBan());
                productDetailView.setNgayTao(product.getNgayTao());
                productDetailView.setTrangThai(product.getTrangThai());
                productDetailView.setMoTa(product.getMoTa());
                productDetailView.setSanPham(product);
                productDetailView.setMauSac(color);
                productDetailView.setKichCo(kichCo);

                pdv.add(productDetailView);
            }
        }

        return pdv;
    }

    @Override
    public ViewSanPhamChiTiet getProductDetailById(UUID id) {
        ChiTietSanPham productDetail = responsitory.findById(id).get();

        ViewSanPhamChiTiet productDetailView = new ViewSanPhamChiTiet();

        productDetailView.setId(productDetail.getIdChiTietSanPham());
        productDetailView.setGiaNhap(productDetail.getGiaNhap());
        productDetailView.setGiaBan(productDetail.getGiaBan());
        productDetailView.setSoLuong(productDetail.getSoLuong());
        productDetailView.setNgayTao(productDetail.getNgayTao());
        productDetailView.setTrangThai(productDetail.getTrangThai());
        productDetailView.setMoTa(productDetail.getMoTa());

        SanPham product = productResponsitory.findById(productDetail.getSanPham().getIdSanPham()).get();
        MauSac color = colorResponsitory.findById(productDetail.getMauSac().getIdMauSac()).get();
        KichCo kichCo = kichCoRepository.findById(productDetail.getKichCo().getIdKichCo()).get();

        if (product != null && color != null /*&& product.getBrand() == color.getBrand()*/) {
            productDetailView.setTen(product.getTenSanPham());
            productDetailView.setSoLuongTon(product.getSoLuong());
            productDetailView.setDaBan(product.getDaBan());
            productDetailView.setSanPham(product);
            productDetailView.setMauSac(color);
            productDetailView.setKichCo(kichCo);

        }

        return productDetailView;
    }

    @Override
    public List<ChiTietSanPham> getAll() {
        return responsitory.findAll();
    }

    @Override
    public ChiTietSanPham getOne(UUID id) {
        return responsitory.findById(id).get();
    }


}
