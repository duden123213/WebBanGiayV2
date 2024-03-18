package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.entity.ViewModels.ViewSanPham;
import com.example.demo.repository.ChiTietPhanLoaiRepository;
import com.example.demo.repository.SanPhamRepository;
import com.example.demo.service.NhanVienService;
import com.example.demo.service.serviceimpl.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequestMapping("/sanPham")
public class SanPhamController {
    @Autowired
    private SanPhamServiceImpl productServiceimpl;
    @Autowired
    private ThuongHieuServiceImpl brandServiceimpl;
    @Autowired
    private PhanLoaiServiceImpl categoryServiceimpl;
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private ChiTietPhanLoaiRepository categoryDetailResponsitory;

    @GetMapping("/index")

    public String hienThi(Model model, HttpSession session) {

            model.addAttribute("listProduct", productServiceimpl.getAll());
            model.addAttribute("listBrand", brandServiceimpl.getAll());
            model.addAttribute("listCategory", categoryServiceimpl.getAll());
            model.addAttribute("Product", new SanPham());
            model.addAttribute("view", "/SanPham/index.jsp");
            return "index";

        //Nếu chưa đăng nhập thì return về trang logina

    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("listProduct", productServiceimpl.getAll());
        model.addAttribute("listBrand", brandServiceimpl.getAll());
        model.addAttribute("listCategory", categoryServiceimpl.getAll());
        model.addAttribute("Product", new SanPham());
        model.addAttribute("view", "/SanPham/createProduct.jsp");
        return "index";
    }

    @GetMapping("/indexcus")
    public String show_data_product_cus(Model model,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "8") int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<ViewSanPham> entitiesPage = productServiceimpl.getAllSanPhamWithPagination(pageable);
        model.addAttribute("entitiesPage", entitiesPage);
        model.addAttribute("listProduct", entitiesPage.getContent());
        model.addAttribute("quantityProduct", entitiesPage.getContent().size());
        model.addAttribute("listBrand", brandServiceimpl.getAll());
        model.addAttribute("view", "/sanPham/index.jsp");
        return "/giaodien/index";
    }

    @GetMapping("/indexcus/{tenSanPham}")
    public String show_data_product_cus_search(Model model,
                                               @PathVariable("tenSanPham") String name,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "8") int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<ViewSanPham> entitiesPage = productServiceimpl.getAllSanPhamByName(name, pageable);
        model.addAttribute("entitiesPage", entitiesPage);
        model.addAttribute("listProduct", entitiesPage.getContent());
        model.addAttribute("quantityProduct", entitiesPage.getContent().size());
        model.addAttribute("listBrand", brandServiceimpl.getAll());
        model.addAttribute("view", "/sanPham/index.jsp");
        return "/giaodien/index";
    }

    @GetMapping("/indexcus/thuongHieu/{thuongHieuId}")
    public String show_data_product_cus_brand(Model model,
                                              @PathVariable("thuongHieuId") UUID brandId,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "8") int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<ViewSanPham> entitiesPage = productServiceimpl.getAllSanPhamByBrand(brandId, pageable);
        model.addAttribute("entitiesPage", entitiesPage);
        model.addAttribute("listProduct", entitiesPage.getContent());
        model.addAttribute("quantityProduct", entitiesPage.getContent().size());
        model.addAttribute("listBrand", brandServiceimpl.getAll());
        model.addAttribute("view", "/sanPham/index.jsp");
        return "/giaodien/index";
    }

    @GetMapping("/indexcus/price/{min}/{max}")
    public String show_data_product_cus_price(Model model,
                                              @PathVariable("min") BigDecimal min,
                                              @PathVariable("max") BigDecimal max,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "8") int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<ViewSanPham> entitiesPage = productServiceimpl.getAllSanPhamByPrice(min, max, pageable);
        model.addAttribute("entitiesPage", entitiesPage);
        model.addAttribute("listProduct", entitiesPage.getContent());
        model.addAttribute("quantityProduct", entitiesPage.getContent().size());
        model.addAttribute("listBrand", brandServiceimpl.getAll());
        model.addAttribute("view", "/sanPham/index.jsp");
        return "/giaodien/index";
    }

    @GetMapping("/search")
    public String search(@RequestParam String name, RedirectAttributes attributes) {
        attributes.addAttribute("name", name);
        return "redirect:/sanPham/indexcus/{name}";
    }

    @PostMapping("/add")
    public String add(Model model,
                      @RequestParam("tenSanPham") String name,
                      @RequestParam("soLuong") Integer availableQuantity,
                      @RequestParam("daBan") Integer sold,
                      @RequestParam("luotThich") Integer likes,
                      @RequestParam("trangThai") Integer status,
                      @RequestParam("moTa") String descripTion,
                      @RequestParam("thuongHieu") UUID brandId,
                      @RequestParam("phanLoai") UUID categoryId
    ) {
        // Tạo một đối tượng Product
        SanPham newProduct = new SanPham();
        newProduct.setTenSanPham(name);
        newProduct.setSoLuong(availableQuantity);
        newProduct.setDaBan(sold);
        newProduct.setLuotThich(likes);
        newProduct.setTrangThai(status);
        newProduct.setMoTa(descripTion);
        newProduct.setNgayTao(java.sql.Date.valueOf(LocalDate.now()));
        // Tạo và lưu đối tượng Brand
        newProduct.setThuongHieu(ThuongHieu.builder().Id(brandId).build());
        newProduct = productServiceimpl.add(newProduct);

        // Lặp qua danh sách CategoryValue từ ProductRepuest và tạo các CategoryDetail
        // Tạo đối tượng Category từ categoryValue và gán cho CategoryDetail
//            System.out.println("Cai nay o dau ta" + categoryServiceimpl.getOne(idCategory));
//            System.out.println("id"+idCategory);
        PhanLoai category = categoryServiceimpl.getOne(categoryId);
        if (category == null) {
            System.out.println(category.getIdPhanLoai() + "Hương");
        } else {
            ChiTietPhanLoai categoryDetail = new ChiTietPhanLoai();
            categoryDetail.setPhanLoai(category);
            categoryDetail.setSoLuong(0);
            categoryDetail.setSanPham(newProduct);
            categoryDetailResponsitory.save(categoryDetail);
        }
        // Sau khi đã tạo sản phẩm và liên kết với danh mục, bạn có thể thực hiện bất kỳ hành động nào khác cần thiết, chẳng hạn chuyển hướng hoặc hiển thị thông báo thành công.
        return "redirect:/sanPham/index"; // Chuyển hướng đến trang danh sách sản phẩm hoặc trang khác tùy theo nhu cầu của bạn.
    }


    @GetMapping("/detail/{idSanPham}")
    public String detailSPCT(Model model,
                             @PathVariable("idSanPham") UUID id) {
        model.addAttribute("listProduct", productServiceimpl.getAll());
        model.addAttribute("listCategory", categoryServiceimpl.getAll());
        model.addAttribute("listBrand", brandServiceimpl.getAll());
        model.addAttribute("sp", productServiceimpl.getOne(id));
        model.addAttribute("view", "/SanPham/editProduct.jsp");
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model,
                         @PathVariable("id") UUID id) {
        productServiceimpl.delete(id);
        return "redirect:/sanPham/index";
    }
    //    @GetMapping("/detailSPCT/{id}")
//    public String detailSPCT(Model model,
//                         @PathVariable("id") UUID id
//    ) {
//        ProductDetail productDetail = productDetailServiceimpl.getOne(id);
//        model.addAttribute("productDetail", productDetail);
//        model.addAttribute("listProduct", productServiceimpl.getAll());
//        model.addAttribute("listColor", colorServiceimpl.getAll());
//        model.addAttribute("listProductImage", productImageServiceimpl.getAll());
//        model.addAttribute("listProductDetail", productDetailServiceimpl.getAll());
////        model.addAttribute("sp", productDetailServiceimpl.getOne(id));
//        model.addAttribute("view", "/ProductDetail/editProductDetail.jsp");
//        return "index";
//    }
    @PostMapping("/update/{id}")
    public String update(Model model,
                         @PathVariable("id") UUID id,
                         @ModelAttribute("sanPham") SanPham product) {
        productServiceimpl.update(id, product);
        return "redirect:/sanPham/index";
    }

}
