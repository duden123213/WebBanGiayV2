package com.example.demo.service;

import com.example.demo.entity.KichCo;
import com.example.demo.entity.MauSac;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
@Service
public interface KichCoService {
    ArrayList<KichCo> getAll();



    void save(KichCo kichCo);

    void delete(UUID id);

    void update(UUID id, KichCo kichCo);

    MauSac getOne(UUID id);
}
