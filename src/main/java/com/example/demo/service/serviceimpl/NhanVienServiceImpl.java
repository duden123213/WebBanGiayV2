package com.example.demo.service.serviceimpl;

import com.example.demo.entity.NhanVien;
import com.example.demo.repository.NhanVienRepository;
import com.example.demo.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class NhanVienServiceImpl implements NhanVienService {
    @Autowired
    NhanVienRepository nhanVienRepository;

    @Override
    public List<NhanVien> findAll() {
        return nhanVienRepository.findAll();
    }

    @Override
    public void add(NhanVien nhanVien) {
        nhanVienRepository.save(nhanVien);
    }

    @Override
    public void delete(UUID id) {
        nhanVienRepository.deleteById(id);
    }




    @Override
    public NhanVien detail(UUID id) {
        return nhanVienRepository.findById(id).get();
    }

    @Override
    public List<NhanVien> timKiem(String ten, String sdt) {

        return nhanVienRepository.timKiem(ten,sdt);
    }

    @Override
    public List<NhanVien> timKiem2(String ten, String sdt) {

        return nhanVienRepository.timKiem2(ten,sdt);
    }

    @Override
    public List<NhanVien> getByUserName(String username) {
        return nhanVienRepository.getByUserName(username);
    }

    @Override
    public NhanVien login(String username, String password) {
        return nhanVienRepository.login(username,password);
    }

    @Override
    public NhanVien checkChucVu(String username) {
        return nhanVienRepository.checkChucVu(username);
    }

    @Override
    public Page<NhanVien> listDesc(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo,3);
        return nhanVienRepository.listDesc(pageable);
    }

    @Override
    public List<NhanVien> checkUserNameUpdate(String tenHT, String tenSua) {
        return nhanVienRepository.checkUserNameUpdate(tenHT, tenSua);
    }

    @Override
    public void update(NhanVien cs) {

        this.nhanVienRepository.save(cs);

    }
}
