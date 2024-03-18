package com.example.demo.service.serviceimpl;

import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.MauSac;
import com.example.demo.entity.ViewModels.ViewMauSac;
import com.example.demo.repository.ChiTietSanPhamRepository;
import com.example.demo.repository.MauSacRepository;
import com.example.demo.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class MauSacServiceImpl implements MauSacService {
    @Autowired
    MauSacRepository responsitory;
    @Autowired
    ChiTietSanPhamRepository productDetailResponsitory;
    @Override
    public ArrayList<MauSac> getAll() {
        return (ArrayList<MauSac>) responsitory.findAll();
    }

    @Override
    public ArrayList<ViewMauSac> getAllByProductDetailId(UUID id) {
        var productDetail = productDetailResponsitory.findById(id).get();
        var productDetails = productDetailResponsitory.findBySanPhamId(productDetail.getSanPham().getIdSanPham());
        ArrayList<ViewMauSac> colors = new ArrayList<>();
        for (ChiTietSanPham pd : productDetails){
            ViewMauSac color = new ViewMauSac();
            color.setId(pd.getMauSac().getIdMauSac());
            color.setTen(pd.getMauSac().getTenMauSac());
            color.setGia(pd.getMauSac().getGia());
            color.setHinhAnh(pd.getMauSac().getHinhAnh());
            color.setTrangThai(pd.getMauSac().getTrangThai());
            color.setChiTietSanPhamId(pd.getIdChiTietSanPham());
            color.setThuongHieu(pd.getMauSac().getThuongHieu());
            colors.add(color);
        }
        return colors;
    }

    @Override
    public void save(MauSac color) {
        responsitory.saveAndFlush(color);
    }

    @Override
    public void delete(UUID id) {
        responsitory.deleteById(id);
    }

    @Override
    public void update(UUID id, MauSac color) {
        MauSac a = getOne(id);
        a.setTenMauSac(color.getTenMauSac());
        a.setGia(color.getGia());
        a.setHinhAnh(color.getHinhAnh());
        a.setTrangThai(color.getTrangThai());
        a.setThuongHieu(color.getThuongHieu());
        responsitory.flush();
    }

    @Override
    public MauSac getOne(UUID id) {
        return responsitory.findById(id).get();
    }

}
