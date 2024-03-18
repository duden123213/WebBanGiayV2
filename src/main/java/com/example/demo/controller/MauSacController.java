package com.example.demo.controller;

import com.example.demo.entity.MauSac;
import com.example.demo.entity.ThuongHieu;
import com.example.demo.repository.ThuongHieuRepository;
import com.example.demo.service.NhanVienService;
import com.example.demo.service.serviceimpl.MauSacServiceImpl;
import com.example.demo.service.serviceimpl.ThuongHieuServiceImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;
@Controller
@RequestMapping("/mauSac")
public class MauSacController {
    @Autowired
    private MauSacServiceImpl colorServiceimpl;
    @Autowired
    private ThuongHieuServiceImpl brandServiceimpl;
    @Autowired
    private ThuongHieuRepository brandResponsitory;


    @GetMapping("/index")
    public String hienThi(Model model, HttpSession session) {

            //////
            model.addAttribute("listColor", colorServiceimpl.getAll());
            model.addAttribute("listBrand", brandServiceimpl.getAll());
            model.addAttribute("view", "/MauSac/index.jsp");
            return "index";

    }

    @PostMapping("/add")
    public String add(Model model,
                      @RequestParam(value = "tenMauSac") String name,
                      @RequestParam(value = "gia") BigDecimal price,
                      @RequestParam(value = "hinhAnh") MultipartFile image,
                      @RequestParam(value = "trangThai") Integer status,
                      @RequestParam(value = "thuongHieu") String brand
    ) throws IOException {
        if (image != null && !image.isEmpty()) {
            String originalFileName = image.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(originalFileName);

            // Lưu tệp hình ảnh vào thư mục resources
            ClassPathResource resource = new ClassPathResource("static/assets/img/color/");
            String uploadDir = resource.getFile().getAbsolutePath();
            System.out.println("Cái này nha" + uploadDir);
            File uploadPath = new File(uploadDir);

            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            File img = new File(uploadPath, fileName);
            image.transferTo(img);
            ThuongHieu brand1 = brandResponsitory.findById(UUID.fromString(brand)).orElse(null);
            colorServiceimpl.save(new MauSac(name, price, fileName, status, brand1));
        }
        return "redirect:/mauSac/index";
    }

    @GetMapping("/delete/{idMauSac}")
    public String delete(Model model,
                         @PathVariable("idMauSac") String id
    ) {
        colorServiceimpl.delete(UUID.fromString(id));
        return "redirect:/mauSac/index";
    }

    @GetMapping("/detail/{idMauSac}")
    public String detail(Model model,
                         @PathVariable("idMauSac") UUID id) {
        model.addAttribute("listColor", colorServiceimpl.getAll());
        model.addAttribute("listBrand", brandServiceimpl.getAll());
        model.addAttribute("mau", colorServiceimpl.getOne(id));
        model.addAttribute("view", "/MauSac/index.jsp");
        return "index";
    }

    @PostMapping("/update/{idMauSac}")
    public String update(Model model,
                         @PathVariable("idMauSac") UUID id,
                         @RequestParam(value = "tenMauSac") String name,
                         @RequestParam(value = "gia") BigDecimal price,
                         @RequestParam(value = "hinhAnh") MultipartFile image,
                         @RequestParam(value = "trangThai") Integer status,
                         @RequestParam(value = "thuongHieu") String brand)
            throws IOException {
        MauSac color = colorServiceimpl.getOne(id);

        if (image != null && !image.isEmpty()) {
            String originalFileName = image.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(originalFileName);

            // Lưu tệp hình ảnh vào thư mục resources
            ClassPathResource resource = new ClassPathResource("static/assets/img/color/");
            String uploadDir = resource.getFile().getAbsolutePath();
            File uploadPath = new File(uploadDir);

            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            File img = new File(uploadPath, fileName);
            image.transferTo(img);

            color.setHinhAnh(fileName); // Cập nhật tên ảnh mới

            // Cập nhật các trường thông tin khác
            ThuongHieu brand1 = brandResponsitory.findById(UUID.fromString(brand)).orElse(null);
            color.setThuongHieu(brand1);
            color.setGia(price);
            color.setTrangThai(status);
            color.setTenMauSac(name);

            colorServiceimpl.update(id, color);
        } else {
            // Xử lý khi không có ảnh mới được tải lên
            ThuongHieu brand1 = brandResponsitory.findById(UUID.fromString(brand)).orElse(null);
            color.setThuongHieu(brand1);
            color.setGia(price);
            color.setTrangThai(status);
            color.setTenMauSac(name);

            colorServiceimpl.update(id, color);
        }

        return "redirect:/mauSac/index";
    }
}
