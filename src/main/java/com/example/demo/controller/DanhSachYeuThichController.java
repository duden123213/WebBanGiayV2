package com.example.demo.controller;


import com.example.demo.entity.DanhSachYeuThich;
import com.example.demo.entity.KhachHang;
import com.example.demo.entity.SanPham;
import com.example.demo.service.serviceimpl.DanhSachYeuThichServiceImpl;
import com.example.demo.service.serviceimpl.KhachHangServiceImpl;
import com.example.demo.service.serviceimpl.SanPhamServiceImpl;
import com.example.demo.entity.ViewModels.ViewDanhSachYeuThich;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/dsyt")
public class DanhSachYeuThichController {

    @Autowired
    private HttpSession httpSession;
    @Autowired
    DanhSachYeuThichServiceImpl dsytimpl;
    @Autowired
    SanPhamServiceImpl productServiceimpl;
    @Autowired
    KhachHangServiceImpl customerServiceimpl;

    @GetMapping("/index")
    public String hienThi(Model model) {

        model.addAttribute("listFavor", dsytimpl.getAll());
        model.addAttribute("DanhSachYeuThichs", new DanhSachYeuThichController());
        model.addAttribute("listProduct", productServiceimpl.getAll());
        model.addAttribute("listCustomer", customerServiceimpl.findAll());
        model.addAttribute("view", "/DanhSachYeuThich/index.jsp");
        return "index";
    }

    @GetMapping("/indexcus")
    public String show_data_favor_cus(Model model,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "8") int size) {

        if (httpSession.getAttribute("CustomerName") != null) {
            String username = (String) httpSession.getAttribute("CustomerName");
            KhachHang customer = customerServiceimpl.getKhachHangByName(username);
            Pageable pageable = Pageable.ofSize(size).withPage(page);
            Page<ViewDanhSachYeuThich> entitiesPage = dsytimpl.getAllByCustomerIdWithPagination(customer.getIdKhachHang(), pageable);
            model.addAttribute("entitiesPage", entitiesPage);
            model.addAttribute("listFavor", entitiesPage.getContent());
            model.addAttribute("quantityFavor", entitiesPage.getContent().size());
        }
        model.addAttribute("view", "/dsyt/index.jsp");
        return "/giaodien/index";
    }

    @PostMapping("/like/{id}")
    public String like(@PathVariable("id") UUID id) {
        if (httpSession.getAttribute("CustomerName") != null) {
            String username = (String) httpSession.getAttribute("CustomerName");
            KhachHang customer = customerServiceimpl.getKhachHangByName(username);
            dsytimpl.Like(customer.getIdKhachHang(), id);
        }
        return "redirect:/dsyt/indexcus";
    }

    @PostMapping("/add")
    public String add(Model model,

                      @RequestParam("customer") KhachHang khachHang,
                      @RequestParam("product") SanPham sanPham


    ) {
        DanhSachYeuThich danhSachYeuThich = new DanhSachYeuThich(khachHang, sanPham);
        dsytimpl.add(danhSachYeuThich);
        return "redirect:/dsyt/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @PathVariable("id") UUID id) {
        model.addAttribute("listFavor", dsytimpl.getAll());
        model.addAttribute("spyt", dsytimpl.getOne(id));
        model.addAttribute("listCustomer", customerServiceimpl.findAll());
        model.addAttribute("listProduct", productServiceimpl.getAll());
        model.addAttribute("view", "/DanhSachYeuThich/index.jsp");
        return "index";
    }

    @GetMapping("delete")
    public String delete(Model model,
                         @RequestParam("id") UUID id) {
        dsytimpl.delete(id);
        return "redirect:/dsyt/index";
    }

    @PostMapping("/update/{id}")
    public String update(Model model,
                         @PathVariable("id") UUID id,
                         @ModelAttribute("favoriteProducts") DanhSachYeuThich wishList) {
        dsytimpl.update(id, wishList);
        return "redirect:/dsyt/index";
    }
}
