package com.example.demo.service.serviceimpl;

import com.example.demo.entity.KichCo;
import com.example.demo.repository.KichCoRepository;
import com.example.demo.service.KichCoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class KichCoServiceImpl implements KichCoService {

    @Autowired
    KichCoRepository kichCoRepository;

    @Override
    public ArrayList<KichCo> getAll() {
        return (ArrayList<KichCo>) kichCoRepository.findAll();
    }

    @Override
    public Page<KichCo> getAllWithPagination(Pageable pageable) {
        return kichCoRepository.findAll(pageable);
    }

    @Override
    public void save(KichCo kichCo) {
        kichCoRepository.saveAndFlush(kichCo);
    }

    @Override
    public void delete(UUID idKichCo) {
        kichCoRepository.deleteById(idKichCo);
    }

    @Override
    public void update(UUID idKichCo, KichCo kichCo) {
        KichCo kc = getOne(idKichCo);
        kc.setTenKichCo(kichCo.getTenKichCo());
        kc.setMoTa(kichCo.getMoTa());
        kc.setTrangThai(kichCo.getTrangThai());
        kichCoRepository.flush();
    }

    @Override
    public KichCo getOne(UUID idKichCo) {
        return kichCoRepository.findById(idKichCo).get();
    }
}
