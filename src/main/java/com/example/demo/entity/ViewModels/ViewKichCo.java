package com.example.demo.entity.ViewModels;

import com.example.demo.entity.ThuongHieu;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
@Data
public class ViewKichCo {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;
    private String ten;
    private String moTa;
    private Integer trangThai;
    private UUID chiTietSanPhamId;


}
