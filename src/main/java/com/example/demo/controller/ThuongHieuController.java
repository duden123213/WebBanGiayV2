package com.example.demo.controller;

import com.example.demo.entity.ThuongHieu;
import com.example.demo.service.serviceimpl.ThuongHieuServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@Controller
@RequestMapping("/thuongHieu")
public class ThuongHieuController {
    @Autowired
    private ThuongHieuServiceImpl serviceimpl;



    @GetMapping("/index")
    public String hienThi(Model model, HttpSession session){

            //////
            model.addAttribute("listBrand",serviceimpl.getAll());
            model.addAttribute("Brand",new ThuongHieu());
            model.addAttribute("view", "/ThuongHieu/index.jsp");
            return "index";



    }

    @GetMapping("/indexcus" )
    public String show_data_product_cus(Model model,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "12") int size){
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<ThuongHieu> entitiesPage = serviceimpl.getAllWithPagination(pageable);
        model.addAttribute("entitiesPage", entitiesPage);
        model.addAttribute("listBrand",entitiesPage.getContent());
        model.addAttribute("quantityBrand",entitiesPage.getContent().size());
        model.addAttribute("view", "/thuonghieu/index.jsp");
        return "/giaodien/index";
    }



    @PostMapping("/add")
    public String add(Model model,
                      @RequestParam("tenThuongHieu") String tenThuongHieu,
                      @RequestParam("hinhAnh") String hinhAnh,
                      @RequestParam("hinhAnh") String moTa,
                      @RequestParam("trangThai") Integer trangThai
    ){
        ThuongHieu brand = new ThuongHieu(tenThuongHieu,hinhAnh, moTa,trangThai);
        serviceimpl.add(brand);
        return "redirect:/thuongHieu/index";
    }
    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @PathVariable("id") UUID id){
        model.addAttribute("listBrand",serviceimpl.getAll());
        model.addAttribute("bra",serviceimpl.getOne(id));
        model.addAttribute("view", "/ThuongHieu/index.jsp");
        return "index";
    }
    @GetMapping("/delete/{id}")
    public String delete(Model model,
                         @PathVariable("id") UUID id){
        serviceimpl.delete(id);
        return "redirect:/thuongHieu/index";
    }
    @PostMapping("/update/{id}")
    public String update(Model model,
                         @PathVariable("id") UUID id,
                         @ModelAttribute("thuongHieu") ThuongHieu brand){
        serviceimpl.update(id,brand);
        return "redirect:/thuongHieu/index";
    }
}
