package com.example.demo.service;

import com.example.demo.entity.MauSac;
import com.example.demo.entity.ViewModels.ViewMauSac;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
@Service
public interface MauSacService {
    ArrayList<MauSac> getAll();

    ArrayList<ViewMauSac> getAllByProductDetailId(UUID id);

    void save(MauSac mauSac);

    void delete(UUID id);

    void update(UUID id, MauSac mauSac);

    MauSac getOne(UUID id);
}
