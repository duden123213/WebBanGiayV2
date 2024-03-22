package com.example.demo.service;

import com.example.demo.entity.KichCo;
import com.example.demo.entity.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
@Service
public interface KichCoService {
    ArrayList<KichCo> getAll();

    Page<KichCo> getAllWithPagination(Pageable pageable);

    void save(KichCo kichCo);

    void delete(UUID idKichCo);

    void update(UUID idKichCo, KichCo kichCo);

    KichCo getOne(UUID idKichCo);
}
