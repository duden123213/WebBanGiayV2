package com.example.demo.service.serviceimpl;

import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.KichCo;
import com.example.demo.entity.ViewModels.ViewKichCo;
import com.example.demo.repository.ChiTietSanPhamRepository;

import com.example.demo.repository.KichCoRepository;
import com.example.demo.service.KichCoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
@Service
public class KichCoServiceImpl implements KichCoService {
    @Autowired
    KichCoRepository responsitory;
    @Autowired
    ChiTietSanPhamRepository productDetailResponsitory;
    @Override
    public ArrayList<KichCo> getAll() {
        return (ArrayList<KichCo>) responsitory.findAll();
    }

    @Override
    public ArrayList<ViewKichCo> getAllByProductDetailId(UUID id) {
        var productDetail = productDetailResponsitory.findById(id).get();
        var productDetails = productDetailResponsitory.findBySanPhamId(productDetail.getSanPham().getIdSanPham());
        ArrayList<ViewKichCo> colors = new ArrayList<>();
        for (ChiTietSanPham pd : productDetails){
            ViewKichCo color = new ViewKichCo();
            color.setId(pd.getKichCo().getIdKichCo());
            color.setTen(pd.getKichCo().getTenKichCo());
            color.setMoTa(pd.getKichCo().getMoTa());
            color.setTrangThai(pd.getKichCo().getTrangThai());
            color.setChiTietSanPhamId(pd.getIdChiTietSanPham());
            colors.add(color);
        }
        return colors;
    }

    @Override
    public void save(KichCo color) {
        responsitory.saveAndFlush(color);
    }

    @Override
    public void delete(UUID id) {
        responsitory.deleteById(id);
    }

    @Override
    public void update(UUID id, KichCo color) {
        KichCo a = getOne(id);
        a.setTenKichCo(color.getTenKichCo());
        a.setMoTa(color.getMoTa());
        a.setTrangThai(color.getTrangThai());
        responsitory.flush();
    }

    @Override
    public KichCo getOne(UUID id) {
        return responsitory.findById(id).get();
    }
}
