package com.example.demo.controller;

import com.example.demo.entity.ChiTietHoaDon;
import com.example.demo.service.serviceimpl.ChiTietHoaDonServiceImpl;
import com.example.demo.service.serviceimpl.HoaDonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/chiTietHoaDon")
public class ChiTietHoaDonController {
    @Autowired
    ChiTietHoaDonServiceImpl billDetailService;
    @Autowired
    HoaDonServiceImpl billService;

    @GetMapping("/index/{billId}")
    public String hienthi(Model model, @PathVariable("billId") UUID billId) {
        model.addAttribute("listBill", billService.getAll());
        List<ChiTietHoaDon> billDetailList = billDetailService.getAllById(billId);
        double total = 0; // Khởi tạo biến tổng giá trị hóa đơn
        for (ChiTietHoaDon billDetail : billDetailList) {
            int quantity = billDetail.getSoLuong();
            BigDecimal price = billDetail.getChiTietSanPham().getGiaBan();
            BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(quantity));
            total += totalPrice.doubleValue();
            System.out.println("Số lượng" + quantity);
        }

        System.out.println(total);
        model.addAttribute("billDetailD", billDetailList);
        model.addAttribute("total", total);
        model.addAttribute("view", "/ChiTietHoaDon/index.jsp");
        return "index";
    }

}
