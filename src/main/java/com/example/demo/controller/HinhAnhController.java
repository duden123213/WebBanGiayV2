package com.example.demo.controller;

import com.example.demo.entity.HinhAnh;
import com.example.demo.service.HinhAnhService;
import com.example.demo.service.serviceimpl.ChiTietSanPhamServiceImpl;
import com.example.demo.service.serviceimpl.SanPhamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
@Controller
@RequestMapping("/hinhAnh")
public class HinhAnhController {
    @Autowired
    private SanPhamServiceImpl productServiceimpl;
    @Autowired
    private HinhAnhService hinhAnhService;
    @Autowired
    private ChiTietSanPhamServiceImpl productDetailServiceimpl;

    private final Path root = Paths.get("src/main/resources/static/assets/img/sanPham");

    @GetMapping("/index")
    public String hienThi(Model model) {
        model.addAttribute("listProduct", productServiceimpl.getAll());
        model.addAttribute("listProductDetail", productDetailServiceimpl.getAll());
        model.addAttribute("listProductImage", hinhAnhService.getAll());
        model.addAttribute("ProductImage", new HinhAnh());
        model.addAttribute("view", "/HinhAnh/index.jsp");
        return "index";
    }


    @PostMapping(value = "add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(Model model,
                         @RequestParam("files") MultipartFile[] files,
                         @RequestParam("trangThai") Integer staTus,
                         @RequestParam("chiTietSanPham") UUID id) {

        String message = "";
        try {
            List<String> fileNames = new ArrayList<>();

            Arrays.asList(files).stream().forEach(file -> {
                HinhAnh a = new HinhAnh();
                File uploadRootDir = new File(String.valueOf(root));
                if (!uploadRootDir.exists()) {
                    uploadRootDir.mkdirs();
                }
                try {
                    String filename = file.getOriginalFilename();
                    File serverFile = new File(uploadRootDir.getAbsoluteFile() + File.separator + filename);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(file.getBytes());
                    a.setTenHinhAnh(filename);
                    a.setTrangThai(staTus);
                    a.setChiTietSanPham(productDetailServiceimpl.getOne(id));
                    hinhAnhService.add(a);
                    stream.close();
                } catch (Exception e) {

                }
            });

            message = "Uploaded the files successfully: " + fileNames;
        } catch (Exception e) {
            message = "Fail to upload files!";
        }
        return "redirect:/hinhAnh/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @PathVariable("id") UUID id) {
        model.addAttribute("listProduct", productServiceimpl.getAll());
        model.addAttribute("listProductDetail", productDetailServiceimpl.getAll());
        model.addAttribute("listProductImage", hinhAnhService.getAll());
        model.addAttribute("imageSP", hinhAnhService.getOne(id));
        model.addAttribute("view", "/HinhAnh/index.jsp");
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model,
                         @PathVariable("id") UUID id) {
        hinhAnhService.delete(id);
        return "redirect:/hinhAnh/index";
    }


    @PostMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String update(Model model,
                         @PathVariable("id") UUID id,
                         @RequestParam("files") List<MultipartFile> files,
                         @RequestParam("trangThai") Integer staTus,
                         @RequestParam("chiTietSanPham") UUID idSP) {

        String message = "";
        try {
            List<String> fileNames = new ArrayList<>();

            for (MultipartFile file : files) {
                HinhAnh a = new HinhAnh();
                File uploadRootDir = new File(String.valueOf(root));
                if (!uploadRootDir.exists()) {
                    uploadRootDir.mkdirs();
                }
                try {
                    String filename = file.getOriginalFilename();
                    File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + filename);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(file.getBytes());
                    a.setTenHinhAnh(filename);
                    a.setTrangThai(staTus);
                    a.setChiTietSanPham(productDetailServiceimpl.getOne(idSP));
                    hinhAnhService.update(id, a);
                    stream.close();
                    fileNames.add(filename);
                } catch (Exception e) {
                    // Xử lý lỗi tải lên ảnh
                    // Ví dụ: ghi log hoặc thông báo lỗi
                    e.printStackTrace();
                }
            }

            message = "Uploaded the files successfully: " + fileNames;
        } catch (Exception e) {
            // Xử lý lỗi chung
            // Ví dụ: ghi log hoặc thông báo lỗi
            e.printStackTrace();
            message = "Fail to upload files!";
        }
        return "redirect:/hinhAnh/index";
    }
}
