package com.example.demo.service.serviceimpl;

import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.DanhSachYeuThich;
import com.example.demo.entity.HinhAnh;
import com.example.demo.entity.SanPham;
import com.example.demo.entity.ViewModels.ViewDanhSachYeuThich;
import com.example.demo.repository.*;
import com.example.demo.service.DanhSachYeuThichService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DanhSachYeuThichServiceImpl implements DanhSachYeuThichService {
    @Autowired
    DanhSachYeuThichRepository responitory;
    @Autowired
    SanPhamRepository productResponitory;
    @Autowired
    ChiTietSanPhamRepository productDetailResponitory;
    @Autowired
    HinhAnhRepository productImageResponsitory;
    @Autowired
    KhachHangRepository customerResponsitory;

    @Override
    public void add(DanhSachYeuThich danhSachYeuThich) {
        responitory.saveAndFlush(danhSachYeuThich);
    }

    @Override
    public void delete(UUID id) {
        responitory.deleteById(id);
    }

    @Override
    public void update(UUID id, DanhSachYeuThich danhSachYeuThich) {
        DanhSachYeuThich a = getOne(id);
        a.setKhachHang(danhSachYeuThich.getKhachHang());
        a.setSanPham(danhSachYeuThich.getSanPham());
        responitory.flush();
    }

    @Override
    public List<DanhSachYeuThich> getAll() {
        return responitory.findAll();
    }

    @Override
    public void Like(UUID customerId, UUID productDetailId) {
        ChiTietSanPham productDetail = productDetailResponitory.findById(productDetailId).get();
        SanPham product = productResponitory.findById(productDetail.getSanPham().getIdSanPham()).get();
        Optional<DanhSachYeuThich> check = responitory.getWishListByCustomerLike(customerId, product.getIdSanPham());
        if(check.isPresent()){
            responitory.deleteById(check.get().getIdDanhSachYeuThich());
        }
        else{
            DanhSachYeuThich danhSachYeuThich = new DanhSachYeuThich();
            danhSachYeuThich.setIdDanhSachYeuThich(UUID.randomUUID());
            danhSachYeuThich.setKhachHang(customerResponsitory.findById(customerId).get());
            danhSachYeuThich.setSanPham(productResponitory.findById(product.getIdSanPham()).get());
            responitory.saveAndFlush(danhSachYeuThich);
        }
    }

    @Override
    public ArrayList<ViewDanhSachYeuThich> getAllByCustomerId(UUID id) {
        List<DanhSachYeuThich> danhSachYeuThiches = responitory.findByCustomerId(id);

        ArrayList<ViewDanhSachYeuThich> wlv = new ArrayList<>();

        for (DanhSachYeuThich danhSachYeuThich : danhSachYeuThiches) {
            SanPham product = productResponitory.findById(danhSachYeuThich.getSanPham().getIdSanPham()).get();
            if (product != null) {

                ChiTietSanPham productDetail = productDetailResponitory.findBySanPhamId(product.getIdSanPham()).get(0);

                if (productDetail != null) {
                    ViewDanhSachYeuThich viewDanhSachYeuThich = new ViewDanhSachYeuThich();
                    viewDanhSachYeuThich.setId(danhSachYeuThich.getIdDanhSachYeuThich());
                    viewDanhSachYeuThich.setKhachHangId(danhSachYeuThich.getKhachHang().getIdKhachHang());
                    viewDanhSachYeuThich.setSanPhamId(danhSachYeuThich.getSanPham().getIdSanPham());
                    viewDanhSachYeuThich.setChiTietSanPhamId(productDetail.getIdChiTietSanPham());
                    viewDanhSachYeuThich.setGia(productDetail.getGiaBan());
                    viewDanhSachYeuThich.setKhachHang(danhSachYeuThich.getKhachHang());
                    viewDanhSachYeuThich.setSanPham(danhSachYeuThich.getSanPham());

                    HinhAnh hinhAnh = productImageResponsitory.findChiTietSanPhamId(productDetail.getIdChiTietSanPham()).get(0);

                    if (hinhAnh != null) {
                        viewDanhSachYeuThich.setHinhAnh(hinhAnh.getTenHinhAnh());
                    } else {
                        viewDanhSachYeuThich.setHinhAnh("deafault.png");
                    }
                    wlv.add(viewDanhSachYeuThich);
                }
            }
        }

        return wlv;
    }

    @Override
    public Page<ViewDanhSachYeuThich> getAllByCustomerIdWithPagination(UUID id, Pageable pageable) {
        List<DanhSachYeuThich> danhSachYeuThiches = responitory.findByCustomerId(id);

        ArrayList<ViewDanhSachYeuThich> wlv = new ArrayList<>();

        for (DanhSachYeuThich danhSachYeuThich : danhSachYeuThiches) {
            SanPham product = productResponitory.findById(danhSachYeuThich.getSanPham().getIdSanPham()).get();
            if (product != null) {

                ChiTietSanPham productDetail = productDetailResponitory.findBySanPhamId(product.getIdSanPham()).get(0);

                if (productDetail != null) {
                    ViewDanhSachYeuThich viewdanhSachYeuThich = new ViewDanhSachYeuThich();
                    viewdanhSachYeuThich.setId(danhSachYeuThich.getIdDanhSachYeuThich());
                    viewdanhSachYeuThich.setKhachHangId(danhSachYeuThich.getKhachHang().getIdKhachHang());
                    viewdanhSachYeuThich.setSanPhamId(danhSachYeuThich.getSanPham().getIdSanPham());
                    viewdanhSachYeuThich.setChiTietSanPhamId(productDetail.getIdChiTietSanPham());
                    viewdanhSachYeuThich.setGia(productDetail.getGiaBan());
                    viewdanhSachYeuThich.setKhachHang(danhSachYeuThich.getKhachHang());
                    viewdanhSachYeuThich.setSanPham(danhSachYeuThich.getSanPham());

                    HinhAnh hinhAnh = productImageResponsitory.findChiTietSanPhamId(productDetail.getIdChiTietSanPham()).get(0);

                    if (hinhAnh != null) {
                        viewdanhSachYeuThich.setHinhAnh(hinhAnh.getTenHinhAnh());
                    } else {
                        viewdanhSachYeuThich.setHinhAnh("deafault.png");
                    }
                    wlv.add(viewdanhSachYeuThich);
                }
            }
        }
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), wlv.size());

        List<ViewDanhSachYeuThich> sublist = wlv.subList(start, end);
        return new PageImpl<>(sublist, pageable, wlv.size());
    }

    @Override
    public DanhSachYeuThich getOne(UUID id) {
        return responitory.findById(id).get();
    }
}
