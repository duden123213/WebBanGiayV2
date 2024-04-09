package com.example.demo.controller;

import com.example.demo.entity.ChiTietHoaDon;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.SanPham;
import com.example.demo.service.serviceimpl.ChiTietHoaDonServiceImpl;
import com.example.demo.service.serviceimpl.HoaDonServiceImpl;
import com.example.demo.service.serviceimpl.SanPhamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    SanPhamServiceImpl sanPhamService;


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
    @PostMapping("/addProductToBill")
    public String addProductToBill(@RequestParam("billId") UUID billId,
                                   @RequestParam("productId") UUID productId,
                                   @RequestParam("quantity") int quantity) {
        // Lấy hóa đơn từ cơ sở dữ liệu
        HoaDon bill = billService.getOne(billId);

        // Kiểm tra xem hóa đơn và sản phẩm tồn tại
        if (bill != null) {
            // Lấy thông tin sản phẩm từ cơ sở dữ liệu
            SanPham product = sanPhamService.getOne(productId);
            if (product != null) {
                // Tạo một chi tiết hóa đơn mới
                ChiTietHoaDon billDetail = new ChiTietHoaDon();
                billDetail.setIdChiTietHoaDon(product.getIdSanPham());
                billDetail.setSoLuong(quantity);

                // Thêm chi tiết hóa đơn vào danh sách chi tiết hóa đơn của hóa đơn
                billDetailService.add(billDetail);

                // Cập nhật hóa đơn trong cơ sở dữ liệu
                billService.update(bill);

                // Chuyển hướng đến trang thành công hoặc hiển thị thông báo thành công
                return "redirect:/successPage";
            }
        }
        // Nếu không tìm thấy hóa đơn hoặc sản phẩm, chuyển hướng đến trang lỗi hoặc hiển thị thông báo lỗi
        return "redirect:/errorPage";
    }

}
