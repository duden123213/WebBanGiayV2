package com.example.demo.service.serviceimpl;

import com.example.demo.entity.KhachHang;
import com.example.demo.repository.KhachHangRepository;
import com.example.demo.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class KhachHangServiceImpl  implements KhachHangService {
    @Autowired
    KhachHangRepository khachHangRepository;

    @Override
    public Page<KhachHang> findAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo,3);
        return khachHangRepository.findAll(pageable);
    }

    @Override
    public List<KhachHang> findAll() {
        return khachHangRepository.findAll();
    }

    @Override
    public List<KhachHang> timKiem(String sdt) {
        return khachHangRepository.timKiem(sdt);
    }

    @Override
    public void add(KhachHang khachHang) {
        khachHangRepository.save(khachHang);
    }

    @Override
    public void delete(UUID id) {
        khachHangRepository.deleteById(id);
    }




    @Override
    public KhachHang detail(UUID id) {
        return khachHangRepository.findById(id).get();
    }

    @Override
    public void update(KhachHang khachHang) {
        khachHangRepository.save(khachHang);

    }

    @Override
    public KhachHang login(String userName, String passWord) {
        return khachHangRepository.login(userName,passWord);
    }

    @Override
    public KhachHang getKhachHangByName(String userName) { return khachHangRepository.getKhachHangByName(userName); }

    @Override
    public List<KhachHang> getByUserName(String userName) {
        return khachHangRepository.getByUserName(userName);
    }

    @Override
    public List<KhachHang> checkUserNameUpdate(String tenHT, String tenSua) {
        return khachHangRepository.checkUserNameUpdate(tenHT,tenSua);
    }
}
