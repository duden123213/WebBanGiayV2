package com.example.demo.service.serviceimpl;

import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.HinhAnh;
import com.example.demo.entity.SanPham;
import com.example.demo.entity.ViewModels.ViewSanPham;
import com.example.demo.repository.ChiTietSanPhamRepository;
import com.example.demo.repository.HinhAnhRepository;
import com.example.demo.repository.SanPhamRepository;
import com.example.demo.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.View;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class SanPhamServiceImpl implements SanPhamService {
    @Autowired
    SanPhamRepository responsitory;
    @Autowired
    ChiTietSanPhamRepository chiTietSanPhamRepository;
    @Autowired
    HinhAnhRepository hinhAnhRepository;

    @Override
    public SanPham add(SanPham sanPham) {
        sanPham.setNgayTao(Date.valueOf(LocalDate.now()));
        return responsitory.saveAndFlush(sanPham);
    }

    @Override
    public void delete(UUID id) {
        responsitory.deleteById(id);
    }

    @Override
    public void update(UUID id, SanPham sanPham) {
        SanPham pro = getOne(id);
        pro.setTenSanPham(sanPham.getTenSanPham());
        pro.setMoTa(sanPham.getMoTa());
        pro.setSoLuong(sanPham.getSoLuong());
        pro.setDaBan(sanPham.getDaBan());
        pro.setLuotThich(sanPham.getLuotThich());
//        pro.setCreatedDate(product.getCreatedDate());
        pro.setTrangThai(sanPham.getTrangThai());
        pro.setMoTa(sanPham.getMoTa());
        pro.setThuongHieu(sanPham.getThuongHieu());
//        pro.setList(product.getList());
        responsitory.flush();
    }

    @Override
    public List<SanPham> getAll() {
        return responsitory.findAll();
    }

    @Override
    public SanPham getOne(UUID id) {

        return responsitory.findById(id).get();
    }

    @Override
    public ArrayList<ViewSanPham> getAllSanPham() {
        List<SanPham> sanPhams = responsitory.findAll();

        ArrayList<ViewSanPham> pv = new ArrayList<>();

        for (SanPham sanPham : sanPhams) {
            if (chiTietSanPhamRepository.findBySanPhamId(sanPham.getIdSanPham()).size() == 0) {
                continue;
            } else {
                ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findBySanPhamId(sanPham.getIdSanPham()).get(0);

                if (chiTietSanPham != null) {
                    ViewSanPham viewSanPham = new ViewSanPham();
                    viewSanPham.setId(sanPham.getIdSanPham());
                    viewSanPham.setTenSanPham(sanPham.getTenSanPham());
                    viewSanPham.setSoLuongCoSan(sanPham.getSoLuong());
                    viewSanPham.setGia(chiTietSanPham.getGiaBan());
                    viewSanPham.setDaBan(sanPham.getDaBan());
                    viewSanPham.setLuotThich(sanPham.getLuotThich());
                    viewSanPham.setNgayTao(sanPham.getNgayTao());
                    viewSanPham.setTrangThai(sanPham.getTrangThai());
                    viewSanPham.setMoTa(sanPham.getMoTa());
                    viewSanPham.setChiTietSanPhamId(chiTietSanPham.getIdChiTietSanPham());
                    viewSanPham.setThuongHieu(sanPham.getThuongHieu());

                    HinhAnh hinhAnh = hinhAnhRepository.findChiTietSanPhamId(chiTietSanPham.getIdChiTietSanPham()).get(0);

                    if (hinhAnh != null) {
                        viewSanPham.setHinhAnh(hinhAnh.getTenHinhAnh());
                    } else {
                        viewSanPham.setHinhAnh("deafault.png");
                    }
                    pv.add(viewSanPham);
                }
            }
        }
        return pv;
    }

    @Override
    public Page<ViewSanPham> getAllSanPhamWithPagination(Pageable pageable) {
        List<SanPham> sanPhams = responsitory.findAll();

        ArrayList<ViewSanPham> pv = new ArrayList<>();

        for (SanPham sanPham : sanPhams) {
            if (chiTietSanPhamRepository.findBySanPhamId(sanPham.getIdSanPham()).size() == 0) {
                continue;
            } else {
                ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findBySanPhamId(sanPham.getIdSanPham()).get(0);

                if (chiTietSanPham != null) {
                    ViewSanPham viewSanPham = new ViewSanPham();
                    viewSanPham.setId(sanPham.getIdSanPham());
                    viewSanPham.setTenSanPham(sanPham.getTenSanPham());
                    viewSanPham.setSoLuongCoSan(sanPham.getSoLuong());
                    viewSanPham.setGia(chiTietSanPham.getGiaBan());
                    viewSanPham.setDaBan(sanPham.getDaBan());
                    viewSanPham.setLuotThich(sanPham.getLuotThich());
                    viewSanPham.setNgayTao(sanPham.getNgayTao());
                    viewSanPham.setTrangThai(sanPham.getTrangThai());
                    viewSanPham.setMoTa(sanPham.getMoTa());
                    viewSanPham.setChiTietSanPhamId(chiTietSanPham.getIdChiTietSanPham());
                    viewSanPham.setThuongHieu(sanPham.getThuongHieu());

                    HinhAnh hinhAnh = hinhAnhRepository.findChiTietSanPhamId(chiTietSanPham.getIdChiTietSanPham()).get(0);

                    if (hinhAnh != null) {
                        viewSanPham.setHinhAnh(hinhAnh.getTenHinhAnh());
                    } else {
                        viewSanPham.setHinhAnh("deafault.png");
                    }
                    pv.add(viewSanPham);
                }
            }
        }
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), pv.size());

        List<ViewSanPham> sublist = pv.subList(start, end);
        return new PageImpl<>(sublist, pageable, pv.size());
    }

    @Override
    public Page<ViewSanPham> getAllSanPhamByName(String ten, Pageable pageable) {
        List<SanPham> sanPhams = responsitory.findByTenSanPham(ten);

        ArrayList<ViewSanPham> pv = new ArrayList<>();

        for (SanPham sanPham : sanPhams) {
            if (chiTietSanPhamRepository.findBySanPhamId(sanPham.getIdSanPham()).size() == 0) {
                continue;
            } else {
                ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findBySanPhamId(sanPham.getIdSanPham()).get(0);

                if (chiTietSanPham != null) {
                    ViewSanPham viewSanPham = new ViewSanPham();
                    viewSanPham.setId(sanPham.getIdSanPham());
                    viewSanPham.setTenSanPham(sanPham.getTenSanPham());
                    viewSanPham.setSoLuongCoSan(sanPham.getSoLuong());
                    viewSanPham.setGia(chiTietSanPham.getGiaBan());
                    viewSanPham.setDaBan(sanPham.getDaBan());
                    viewSanPham.setLuotThich(sanPham.getLuotThich());
                    viewSanPham.setNgayTao(sanPham.getNgayTao());
                    viewSanPham.setTrangThai(sanPham.getTrangThai());
                    viewSanPham.setMoTa(sanPham.getMoTa());
                    viewSanPham.setChiTietSanPhamId(chiTietSanPham.getIdChiTietSanPham());
                    viewSanPham.setThuongHieu(sanPham.getThuongHieu());

                    HinhAnh hinhAnh = hinhAnhRepository.findChiTietSanPhamId(chiTietSanPham.getIdChiTietSanPham()).get(0);

                    if (hinhAnh != null) {
                        viewSanPham.setHinhAnh(hinhAnh.getTenHinhAnh());
                    } else {
                        viewSanPham.setHinhAnh("deafault.png");
                    }
                    pv.add(viewSanPham);
                }
            }
        }
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), pv.size());

        List<ViewSanPham> sublist = pv.subList(start, end);
        return new PageImpl<>(sublist, pageable, pv.size());
    }

    @Override
    public Page<ViewSanPham> getAllSanPhamByBrand(UUID id, Pageable pageable) {
        List<SanPham> sanPhams = responsitory.findSanPhamByThuongHieu(id);

        ArrayList<ViewSanPham> pv = new ArrayList<>();

        for (SanPham sanPham : sanPhams) {
            if (chiTietSanPhamRepository.findBySanPhamId(sanPham.getIdSanPham()).size() == 0) {
                continue;
            } else {
                ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findBySanPhamId(sanPham.getIdSanPham()).get(0);

                if (chiTietSanPham != null) {
                    ViewSanPham viewSanPham = new ViewSanPham();
                    viewSanPham.setId(sanPham.getIdSanPham());
                    viewSanPham.setTenSanPham(sanPham.getTenSanPham());
                    viewSanPham.setSoLuongCoSan(sanPham.getSoLuong());
                    viewSanPham.setGia(chiTietSanPham.getGiaBan());
                    viewSanPham.setDaBan(sanPham.getDaBan());
                    viewSanPham.setLuotThich(sanPham.getLuotThich());
                    viewSanPham.setNgayTao(sanPham.getNgayTao());
                    viewSanPham.setTrangThai(sanPham.getTrangThai());
                    viewSanPham.setMoTa(sanPham.getMoTa());
                    viewSanPham.setChiTietSanPhamId(chiTietSanPham.getIdChiTietSanPham());
                    viewSanPham.setThuongHieu(sanPham.getThuongHieu());

                    HinhAnh hinhAnh = hinhAnhRepository.findChiTietSanPhamId(chiTietSanPham.getIdChiTietSanPham()).get(0);

                    if (hinhAnh != null) {
                        viewSanPham.setHinhAnh(hinhAnh.getTenHinhAnh());
                    } else {
                        viewSanPham.setHinhAnh("deafault.png");
                    }
                    pv.add(viewSanPham);
                }
            }
        }
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), pv.size());

        List<ViewSanPham> sublist = pv.subList(start, end);
        return new PageImpl<>(sublist, pageable, pv.size());
    }

    @Override
    public Page<ViewSanPham> getAllSanPhamByPrice(BigDecimal min, BigDecimal max, Pageable pageable) {
        List<SanPham> sanPhams = responsitory.findAll();

        ArrayList<ViewSanPham> pv = new ArrayList<>();

        for (SanPham sanPham : sanPhams) {
            if (chiTietSanPhamRepository.findBySanPhamId(sanPham.getIdSanPham()).size() == 0) {
                continue;
            } else {
                ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findBySanPhamId(sanPham.getIdSanPham()).get(0);

                if (chiTietSanPham != null) {
                    ViewSanPham viewSanPham = new ViewSanPham();
                    viewSanPham.setId(sanPham.getIdSanPham());
                    viewSanPham.setTenSanPham(sanPham.getTenSanPham());
                    viewSanPham.setSoLuongCoSan(sanPham.getSoLuong());
                    viewSanPham.setGia(chiTietSanPham.getGiaBan());
                    viewSanPham.setDaBan(sanPham.getDaBan());
                    viewSanPham.setLuotThich(sanPham.getLuotThich());
                    viewSanPham.setNgayTao(sanPham.getNgayTao());
                    viewSanPham.setTrangThai(sanPham.getTrangThai());
                    viewSanPham.setMoTa(sanPham.getMoTa());
                    viewSanPham.setChiTietSanPhamId(chiTietSanPham.getIdChiTietSanPham());
                    viewSanPham.setThuongHieu(sanPham.getThuongHieu());

                    HinhAnh hinhAnh = hinhAnhRepository.findChiTietSanPhamId(chiTietSanPham.getIdChiTietSanPham()).get(0);

                    if (hinhAnh != null) {
                        viewSanPham.setHinhAnh(hinhAnh.getTenHinhAnh());
                    } else {
                        viewSanPham.setHinhAnh("deafault.png");
                    }
                    pv.add(viewSanPham);
                }
            }
        }
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), pv.size());

        List<ViewSanPham> sublist = pv.subList(start, end);
        return new PageImpl<>(sublist, pageable, pv.size());
    }
}
