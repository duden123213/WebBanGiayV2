package com.example.demo.service;

import com.example.demo.entity.KichCo;
import com.example.demo.entity.MauSac;
import com.example.demo.entity.ViewModels.ViewKichCo;
import com.example.demo.entity.ViewModels.ViewMauSac;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
@Service
public interface KichCoService {
    ArrayList<KichCo> getAll();

    ArrayList<ViewKichCo> getAllByProductDetailId(UUID id);

    void save(KichCo kichCo);

    void delete(UUID id);

    void update(UUID id, KichCo kichCo);

    KichCo getOne(UUID id);
}
