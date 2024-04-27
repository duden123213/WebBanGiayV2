package com.example.demo.service.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.entity.ViewModels.ViewGioHangChiTiet;
import com.example.demo.repository.*;
import com.example.demo.service.GioHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class GioHangChiTietServiceImpl implements GioHangChiTietService {
    @Autowired
    GioHangChiTIetRepository gioHangChiTIetRepository;
    @Autowired
    ChiTietSanPhamRepository chiTietSanPhamRepository;
    @Autowired
    SanPhamRepository sanPhamRepository;
    @Autowired
    HinhAnhRepository hinhAnhRepository;
    @Autowired
    GioHangRepository gioHangRepository;
    @Autowired
    MauSacRepository mauSacRepository;
    @Autowired
    KhachHangRepository khachHangRepository;

    @Override
    public ArrayList<GioHangChiTiet> getAll() {
        return (ArrayList<GioHangChiTiet>) gioHangChiTIetRepository.findAll();
    }
    @Override
    public long countItemsInCart(UUID customerId) {
        return gioHangChiTIetRepository.countByGioHang_IdGioHang(customerId);
    }

    @Override
    public ArrayList<ViewGioHangChiTiet> getGioHangChiTietByCustomerId(UUID khachHangId) {
        List<GioHangChiTiet> gioHangChiTiets = gioHangChiTIetRepository.getGioHangChiTietByKhachHangId(khachHangId);

        ArrayList<ViewGioHangChiTiet> cs = new ArrayList<>();

        for (GioHangChiTiet gioHangChiTiet : gioHangChiTiets) {
            UUID sanPhamChiTietId = gioHangChiTiet.getChiTietSanPham().getIdChiTietSanPham();
            ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(sanPhamChiTietId).orElse(null);

            if (chiTietSanPham != null) {
                UUID sanPhamId = chiTietSanPham.getSanPham().getIdSanPham();
                SanPham sanPham = sanPhamRepository.findById(sanPhamId).orElse(null);

                UUID mauSacId = chiTietSanPham.getMauSac().getIdMauSac();
                MauSac color = mauSacRepository.findById(mauSacId).orElse(null);

                if (sanPham != null) {
                    GioHang gioHang = gioHangRepository.findById(sanPhamChiTietId).orElse(null);

                    ViewGioHangChiTiet c = new ViewGioHangChiTiet();
                    c.setId(gioHangChiTiet.getIdGioHangChiTiet());
                    c.setTen(sanPham.getTenSanPham());
                    c.setTenMau(color.getTenMauSac());
                    c.setSoLuong(gioHangChiTiet.getSoLuong());
                    c.setGia(chiTietSanPham.getGiaNhap());
                    c.setGiaSanPham(chiTietSanPham.getGiaBan());
                    c.setSanPhamId(sanPham.getIdSanPham());
                    c.setChiTietSanPhamid(chiTietSanPham.getIdChiTietSanPham());
                    c.setGioHang(gioHang);
                    c.setChiTietSanPham(chiTietSanPham);

                    HinhAnh hinhAnh = hinhAnhRepository.findChiTietSanPhamId(chiTietSanPham.getIdChiTietSanPham()).get(0);

                    if (hinhAnh != null) {
                        c.setHinhAnh(hinhAnh.getTenHinhAnh());
                    }
                    else{
                        c.setHinhAnh("deafault.png");
                    }

                    cs.add(c);
                }
            }
        }

        return cs;
    }

    @Override
    public List<GioHangChiTiet> getGioHangChiTietByCusId(UUID khachHangId) {
        List<GioHangChiTiet> gioHangChiTiets = gioHangChiTIetRepository.getGioHangChiTietByKhachHangId(khachHangId);
        return gioHangChiTiets;
    }

    @Override
    public GioHangChiTiet getOneGioHangChiTiet(UUID khachHangId, UUID chiTietSanPhamId) {
        var gioHangChiTiet = gioHangChiTIetRepository.getOneGioHangChiTiet(khachHangId, chiTietSanPhamId);
        if(gioHangChiTiet == null){
            return new GioHangChiTiet();
        }
        return gioHangChiTiet;
    }

    @Override
    public void save(GioHangChiTiet gioHangChiTiet)   {
        gioHangChiTIetRepository.saveAndFlush(gioHangChiTiet);
    }

    @Override
    public void delete(UUID id)   {
        gioHangChiTIetRepository.deleteById(id);
    }

    @Override
    public void deleteAll(UUID id) {
        var gioHangChiTiets = gioHangChiTIetRepository.getGioHangChiTietByKhachHangId(id);
        for (GioHangChiTiet gioHangChiTiet: gioHangChiTiets) {
            gioHangChiTIetRepository.deleteById(gioHangChiTiet.getIdGioHangChiTiet());
        }
    }

    @Override
    public void update(UUID id, GioHangChiTiet gioHangChiTiet) {
        {
            GioHangChiTiet a = getOne(id).get();
            a.setChiTietSanPham(gioHangChiTiet.getChiTietSanPham());
            a.setSoLuong(gioHangChiTiet.getSoLuong());
            a.setGia(gioHangChiTiet.getGia());
            gioHangChiTIetRepository.flush();
        }
    }

    @Override
    public Optional<GioHangChiTiet> getOne(UUID id)  {
        return gioHangChiTIetRepository.findById(id);
    }

    @Override
    public boolean checkExistCartDetail(UUID customerId, UUID productDetailId) {
        var check = gioHangChiTIetRepository.getOneGioHangChiTiet(customerId, productDetailId);
        if(check == null){
            return false;
        }
        return true;
    }

}
