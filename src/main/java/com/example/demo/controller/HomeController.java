package com.example.demo.controller;

import com.example.demo.entity.ThuongHieu;
import com.example.demo.entity.ViewModels.ViewSanPham;
import com.example.demo.service.serviceimpl.MauSacServiceImpl;
import com.example.demo.service.serviceimpl.SanPhamServiceImpl;
import com.example.demo.service.serviceimpl.ThuongHieuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    MauSacServiceImpl colorServiceimpl;
    @Autowired
    ThuongHieuServiceImpl brandServiceimpl;
    @Autowired
    SanPhamServiceImpl productServiceimpl;
    @GetMapping("/home" )
    public String home(Model model){

        ArrayList<ViewSanPham> productSortBySold = productServiceimpl.getAllSanPham();
        Collections.sort(productSortBySold, (p1, p2) -> Integer.compare(p2.getLuotThich(), p1.getLuotThich()));

        ArrayList<ViewSanPham> productSortByCreatedDate = productServiceimpl.getAllSanPham();
        Collections.sort(productSortByCreatedDate, (p1, p2) -> p1.getNgayTao().compareTo(p2.getNgayTao()));

        ArrayList<ViewSanPham> listProduct = productServiceimpl.getAllSanPham();

        Map<ThuongHieu, ViewSanPham> firstProductInEachCategory = new LinkedHashMap<>();

        listProduct.forEach(product -> firstProductInEachCategory
                .putIfAbsent(product.getThuongHieu(), product)
        );

        ArrayList<ViewSanPham> uniqueQuantityProducts = new ArrayList<>(firstProductInEachCategory.values());

        model.addAttribute("productSortBySold", productSortBySold.subList(0, 4));
        model.addAttribute("productSortByCreatedDate", productSortByCreatedDate.subList(0, 4));
        model.addAttribute("uniqueQuantityProducts", uniqueQuantityProducts.subList(0, 4));
        model.addAttribute("view", "/home/index.jsp");
        return "/giaodien/index";
    }

    @GetMapping("/blog" )
    public String blog(Model model){
        model.addAttribute("view", "/blog/index.jsp");
        return "/giaodien/index";
    }

    @GetMapping("/blogPage1" )
    public String blogPage1(Model model){
        model.addAttribute("view", "/blogPage1/index.jsp");
        return "/giaodien/index";
    }

    @GetMapping("/blogPage2" )
    public String blogPage2(Model model){
        model.addAttribute("view", "/blogPage2/index.jsp");
        return "/giaodien/index";
    }

    @GetMapping("/blogPage3" )
    public String blogPage3(Model model){
        model.addAttribute("view", "/blogPage3/index.jsp");
        return "/giaodien/index";
    }

    @GetMapping("/blogPage4" )
    public String blogPage4(Model model){
        model.addAttribute("view", "/blogPage4/index.jsp");
        return "/giaodien/index";
    }

    @GetMapping("/blogPage5" )
    public String blogPage5(Model model){
        model.addAttribute("view", "/blogPage5/index.jsp");
        return "/giaodien/index";
    }

    @GetMapping("/aboutus" )
    public String aboutUs(Model model){
        model.addAttribute("view", "/aboutus/index.jsp");
        return "/giaodien/index";
    }
}
