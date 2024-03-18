package com.example.demo.service.serviceimpl;

import com.example.demo.entity.ChucVu;
import com.example.demo.repository.ChucVuRepository;
import com.example.demo.service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class ChucVuServiceImpl implements ChucVuService {
    @Autowired
    ChucVuRepository chucVuRepository;

    @Override
    public ChucVu detail(UUID id) {
        return chucVuRepository.getById(id);
    }

    @Override
    public List<ChucVu> getAll() {

        return chucVuRepository.findAll();
    }
}
