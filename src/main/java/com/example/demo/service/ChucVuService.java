package com.example.demo.service;

import com.example.demo.entity.ChucVu;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public interface ChucVuService {

    ChucVu detail(UUID id);

    List<ChucVu> getAll();
}
