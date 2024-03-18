package com.example.demo.request;

import com.example.demo.entity.GiaTriPhanLoai;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamRequest {
    private List<MultipartFile> files;
    private BigDecimal giaNhap;
    private BigDecimal giaBan;
    private Integer soLuong;
    private Date ngayTao;
    private Integer trangThai;
    private String moTa;
    private UUID mauSac;
    private UUID sanPham;

}
