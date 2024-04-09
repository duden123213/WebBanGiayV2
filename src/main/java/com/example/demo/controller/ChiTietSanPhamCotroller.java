package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.request.SanPhamRequest;
import com.example.demo.service.serviceimpl.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Controller
@RequestMapping("/chiTietSanPham")
public class ChiTietSanPhamCotroller {
    @Autowired
    private SanPhamServiceImpl productServiceimpl;
    @Autowired
    private MauSacServiceImpl colorServiceimpl;
    @Autowired
    private KichCoServiceImpl kichCoServiceimpl;
    @Autowired
    private ChiTietSanPhamServiceImpl productDetailServiceimpl;
    @Autowired
    private HinhAnhServiceImpl productImageServiceimpl;
    @Autowired
    private ThuongHieuServiceImpl brandServiceimpl;
    @Autowired
    private PhanLoaiServiceImpl categoryServiceimpl;



    @GetMapping("/index")
    public String hienThi(Model model) {
        model.addAttribute("listProduct", productServiceimpl.getAll());
        model.addAttribute("listColor", colorServiceimpl.getAll());
        model.addAttribute("listKichCo", kichCoServiceimpl.getAll());
        model.addAttribute("listProductImage", productImageServiceimpl.getAll());
        model.addAttribute("listProductDetail", productDetailServiceimpl.getAll());
        model.addAttribute("ProductDetail", new ChiTietSanPham());
        model.addAttribute("view", "/ChiTietSanPham/index.jsp");
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("listProduct", productServiceimpl.getAll());
        model.addAttribute("listColor", colorServiceimpl.getAll());
        model.addAttribute("listKichCo", kichCoServiceimpl.getAll());
        model.addAttribute("listProductImage", productImageServiceimpl.getAll());
        model.addAttribute("listProductDetail", productDetailServiceimpl.getAll());
        model.addAttribute("ProductDetail", new ChiTietSanPham());
        model.addAttribute("view", "/ChiTietSanPham/createProductDetail.jsp");
        return "index";
    }

    @GetMapping("/indexcus/{productDetailId}")
    public String show_data_product_cus(@PathVariable("productDetailId") UUID productDetailId, Model model) {

        model.addAttribute("listProduct", productServiceimpl.getAllSanPham().subList(0, 4));
        model.addAttribute("productDetail", productDetailServiceimpl.getProductDetailById(productDetailId));
        model.addAttribute("image", productImageServiceimpl.getByChiTietSanPhamId(productDetailId).get(0));
        model.addAttribute("listImage", productImageServiceimpl.getByChiTietSanPhamId(productDetailId));
        model.addAttribute("listColor", colorServiceimpl.getAllByProductDetailId(productDetailId));
        model.addAttribute("listKichCo", kichCoServiceimpl.getAllByProductDetailId(productDetailId));
        model.addAttribute("view", "/detail/index.jsp");
        return "/giaodien/index";
    }

    @GetMapping("/increase/{productDetailId}")
    public String IncreaseProductDetail(@PathVariable("productDetailId") UUID productDetailId) {
        ChiTietSanPham productDetail = productDetailServiceimpl.getOne(productDetailId);

        if (productDetail == null) {
            // Xử lý trường hợp sản phẩm không tồn tại, ví dụ: thông báo lỗi.
            return "redirect:/error";
        }

        productDetail.setSoLuong(productDetail.getSoLuong() + 1);
        productDetailServiceimpl.update(productDetailId, productDetail);
        return "redirect:/chiTietSanPham/indexcus/{productDetailId}";
    }

    @GetMapping("/reduce/{productDetailId}")
    public String ReduceProductDetail(@PathVariable("productDetailId") UUID productDetailId) {
        ChiTietSanPham productDetail = productDetailServiceimpl.getOne(productDetailId);

        if (productDetail == null) {
            // Xử lý trường hợp sản phẩm không tồn tại, ví dụ: thông báo lỗi.
            return "redirect:/error";
        }

        if(productDetail.getSoLuong() > 1){
            productDetail.setSoLuong(productDetail.getSoLuong() - 1);
            productDetailServiceimpl.update(productDetailId, productDetail);
        }
        return "redirect:/chiTietSanPham/indexcus/{productDetailId}";
    }

    @PostMapping(value = "add")
    public String add(Model model,
                      @ModelAttribute("SanPhamRequest") SanPhamRequest sanPhamRequest,
                      @RequestParam("files") List<MultipartFile> files,
                      RedirectAttributes redirectAttributes
    ) throws IOException {

        ChiTietSanPham a = new ChiTietSanPham();
        a.setGiaNhap(sanPhamRequest.getGiaNhap());
        a.setGiaBan(sanPhamRequest.getGiaBan());
        a.setSoLuong(sanPhamRequest.getSoLuong());
        a.setTrangThai(0);
        a.setMoTa(sanPhamRequest.getMoTa());
        a.setNgayTao(java.sql.Date.valueOf(LocalDate.now()));
        a.setSanPham(SanPham.builder().idSanPham(sanPhamRequest.getSanPham()).build());
        a.setMauSac(MauSac.builder().idMauSac(sanPhamRequest.getMauSac()).build());
        a.setKichCo(KichCo.builder().idKichCo(sanPhamRequest.getKichCo()).build());
        productDetailServiceimpl.add(a);
        for (MultipartFile file : files) {
            HinhAnh spa = new HinhAnh();
            ClassPathResource resource = new ClassPathResource("static/assets/img/sanPham/");
            String uploadDir = resource.getFile().getAbsolutePath();
            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }
            spa.setTenHinhAnh(file.getOriginalFilename());
            spa.setTrangThai(0);
            spa.setChiTietSanPham(a);
            productImageServiceimpl.add(spa);
        }

        return "redirect:/chiTietSanPham/index";
    }
    @PostMapping(value = "update/{id}")
    public String update(Model model,
                         @ModelAttribute("SanPhamRequest") SanPhamRequest sanPhamRequest,
                         @RequestParam("files") List<MultipartFile> files,
                         @PathVariable("id") UUID id) throws IOException {
        System.out.println(id+"??");
        ChiTietSanPham existingProductDetail = productDetailServiceimpl.getOne(id);
//        if (existingProductDetail != null) {
        existingProductDetail.setGiaNhap(sanPhamRequest.getGiaNhap());
        existingProductDetail.setGiaBan(sanPhamRequest.getGiaBan());
        existingProductDetail.setSoLuong(sanPhamRequest.getSoLuong());
        existingProductDetail.setTrangThai(sanPhamRequest.getTrangThai());
        existingProductDetail.setMoTa(sanPhamRequest.getMoTa());
        existingProductDetail.setSanPham(SanPham.builder().idSanPham(sanPhamRequest.getSanPham()).build());
        existingProductDetail.setMauSac(MauSac.builder().idMauSac(sanPhamRequest.getMauSac()).build());
        existingProductDetail.setKichCo(KichCo.builder().idKichCo(sanPhamRequest.getKichCo()).build());
        existingProductDetail.setNgayTao(java.sql.Date.valueOf(LocalDate.now()));
        productDetailServiceimpl.update(id, existingProductDetail);
//            if (files != null && !files.isEmpty()) {
        System.out.println(existingProductDetail.getIdChiTietSanPham()+"hehe");
        for (MultipartFile file : files) {
            HinhAnh spa = new HinhAnh();
            ClassPathResource resource = new ClassPathResource("static/assets/img/sanPham/");
            String uploadDir = resource.getFile().getAbsolutePath();
            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }
            spa.setTenHinhAnh(file.getOriginalFilename());
            spa.setTrangThai(0);
            System.out.println(existingProductDetail.getIdChiTietSanPham()+"heheh");
            spa.setChiTietSanPham(existingProductDetail);
            productImageServiceimpl.add(spa);
//                }
        }
//        }
        return "redirect:/chiTietSanPham/index";
    }


    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @PathVariable("id") UUID id
    ) {
        model.addAttribute("listProduct", productServiceimpl.getAll());
        model.addAttribute("listColor", colorServiceimpl.getAll());
        model.addAttribute("listKichCo", kichCoServiceimpl.getAll());
        model.addAttribute("listProductImage", productImageServiceimpl.getAll());
        model.addAttribute("listProductDetail", productDetailServiceimpl.getAll());
        model.addAttribute("listCategory", categoryServiceimpl.getAll());
        model.addAttribute("listBrand", brandServiceimpl.getAll());
        model.addAttribute("detailSP", productDetailServiceimpl.getOne(id));
        model.addAttribute("view", "/ChiTietSanPham/editProductDetail.jsp");
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model,
                         @PathVariable("id") UUID id) {
        productDetailServiceimpl.delete(id);
        return "redirect:/chiTietSanPham/index";
    }
    @GetMapping("/deleteImage/{id}")
    public String deleteImg(Model model, HttpServletRequest request,
                            @PathVariable("id") UUID id) {
        productImageServiceimpl.delete(id);
        String referer = request.getHeader("Referer");
        // Chuyển hướng trở lại trang gọi đến (referer)
        return "redirect:" + referer;

    }
}
