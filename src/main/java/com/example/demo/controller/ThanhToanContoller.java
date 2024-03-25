package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.entity.ViewModels.ViewGioHangChiTiet;
import com.example.demo.service.serviceimpl.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequestMapping("/hoaDon")
public class ThanhToanContoller {
    @Autowired
    HttpSession session;
    @Autowired
    KhachHangServiceImpl customerService;
    @Autowired
    GioHangServiceImpl cartService;
    @Autowired
    GioHangChiTietServiceImpl cartDetailService;
    @Autowired
    SanPhamServiceImpl productService;
    @Autowired
    TrangThaiHoaDonServiceImpl billStatusService;
    @Autowired
    HoaDonServiceImpl billService;
    @Autowired
    ChiTietHoaDonServiceImpl billDetailService;
    @Autowired
    ChiTietSanPhamServiceImpl productDetailServiceimpl;
    @Autowired
    PhuongThucThanhToanServiceImpl paymentService;

    @GetMapping("/thanhtoan")
    public String checkout(Model model) {
        if (session.getAttribute("CustomerName") != null) {
            String username = (String) session.getAttribute("CustomerName");
            KhachHang customer = customerService.getKhachHangByName(username);
            var cart = cartService.getOne(customer.getIdKhachHang());
            var lstCartDetailView = cartDetailService.getGioHangChiTietByCusId(customer.getIdKhachHang());
            model.addAttribute("customer", customer);
            model.addAttribute("cart", cart);
            model.addAttribute("cartDetail", lstCartDetailView);
        } else {
            KhachHang customer = new KhachHang();
            customer.setTenKhachHang("");
            customer.setSoDienThoai("");
            customer.setDiaChi("");
            GioHang cartSession = (GioHang) session.getAttribute("GioHang");
            ArrayList<ViewGioHangChiTiet> cartDetailSession = (ArrayList<ViewGioHangChiTiet>) session.getAttribute("GioHangChiTiet");

            model.addAttribute("customer", customer);
            model.addAttribute("cart", cartSession);
            model.addAttribute("cartDetail", cartDetailSession);
        }
        model.addAttribute("view", "/thanhToan/index.jsp");
        return "/giaodien/index";
    }

    @PostMapping("/placeorder")
    public String confirm(Model model,
                          @RequestParam("receiverName") String receiverName,
                          @RequestParam("customerPhone") String customerPhone,
                          @RequestParam("PaymentId") UUID PaymentId,
                          @RequestParam("addressDelivery") String addressDelivery,

                          RedirectAttributes redirectAttributes
    ) {
        boolean hasError = false; // Biến kiểm tra có lỗi nào xảy ra

        if (receiverName.isEmpty() || !receiverName.matches("^[a-zA-Z\\p{IsAlphabetic} ]+$")) {
            redirectAttributes.addFlashAttribute("receiverNameError", "Invalid Receiver Name (must not be blank and must only contain letters and spaces)");
            hasError = true; // Đặt biến lỗi thành true
        } else {
            redirectAttributes.addAttribute("inputReceiverName", receiverName); // Giữ nguyên giá trị người dùng
        }

        if (customerPhone.isEmpty() || !customerPhone.matches("^\\d+$")) {
            redirectAttributes.addFlashAttribute("customerPhoneError", "Invalid KhachHang Phone (must not be blank and must be numeric)");
            hasError = true; // Đặt biến lỗi thành true
        }else {
            redirectAttributes.addAttribute("inputCustomerPhone", customerPhone); // Giữ nguyên giá trị người dùng
        }

        if (addressDelivery.isEmpty()) {
            redirectAttributes.addFlashAttribute("addressDeliveryError", "Address Delivery is required");
            hasError = true; // Đặt biến lỗi thành true
        }else {
            redirectAttributes.addAttribute("inputAddressDelivery", addressDelivery); // Giữ nguyên giá trị người dùng
        }

        if (hasError) {
            return "redirect:/hoaDon/thanhToan"; // Chuyển hướng đến trang thanh toán
        }

        Date currentDate = new Date(System.currentTimeMillis());

        if (session.getAttribute("CustomerName") != null) {
            String username = (String) session.getAttribute("CustomerName");
            KhachHang customer = customerService.getKhachHangByName(username);
            GioHang cart = cartService.getOne(customer.getIdKhachHang());
            ArrayList<ViewGioHangChiTiet> lstCartDetailView = cartDetailService.getGioHangChiTietByCustomerId(customer.getIdKhachHang());
            HoaDon bill = new HoaDon();

            bill.setIdHoaDon(UUID.randomUUID());
            bill.setTenNguoiNhan(receiverName);
            bill.setSoDienThoai(customerPhone);
            bill.setDiaChi(addressDelivery);
            bill.setNgayTao(currentDate);
            bill.setKhachHang(customer);
            bill.setPhuongThucThanhToan(paymentService.getOne(PaymentId));
            bill.setTrangThaiHoaDon(billStatusService.findById(UUID.fromString("259b8bc3-5489-47c0-a115-b94a0cf6286f")));
            HoaDon b;

            bill.setTongTien(cart.getTongTien());

            b = billService.add(bill);



            // Tạo danh sách Chi Tiết Hóa Đơn
            for (var lstItem : lstCartDetailView) {
                SanPham product = productService.getOne(lstItem.getSanPhamId());
                product.setSoLuong(product.getSoLuong() - lstItem.getSoLuong());
                product.setDaBan(product.getDaBan() + lstItem.getSoLuong());

                ChiTietHoaDon billDetail = new ChiTietHoaDon();
                billDetail.setIdChiTietHoaDon(UUID.randomUUID());
                billDetail.setSoLuong(lstItem.getSoLuong());
                billDetail.setGia(lstItem.getGia());
                billDetail.setHoaDon(b);
                billDetail.setChiTietSanPham(productDetailServiceimpl.getOne(lstItem.getChiTietSanPhamid()));

                // Lưu Chi Tiết Hóa Đơn vào cơ sở dữ liệu
                billDetailService.add(billDetail);
                productService.update(product.getIdSanPham(), product);
                cartDetailService.delete(lstItem.getId());
            }

            cart.setSoLuong(0);
            cart.setTongTien(BigDecimal.ZERO);
            cart.setTrangThai(1);
            cartService.update(cart.getIdGioHang(), cart);
            // Truyền tham số qua RedirectAttributes
            redirectAttributes.addAttribute("billId", b.getIdHoaDon() );
        } else {
            GioHang cartSession = (GioHang) session.getAttribute("GioHang");
            ArrayList<ViewGioHangChiTiet> cartDetailViewSession = (ArrayList<ViewGioHangChiTiet>) session.getAttribute("GioHangChiTiet");
            KhachHang customer = new KhachHang();
            HoaDon bill = new HoaDon();

            customer.setTenKhachHang(receiverName);
            customer.setSoDienThoai(customerPhone);
            customer.setDiaChi(addressDelivery);

            bill.setIdHoaDon(UUID.randomUUID());
            bill.setTenNguoiNhan(receiverName);
            bill.setSoDienThoai(customerPhone);
            bill.setDiaChi(addressDelivery);
            bill.setNgayTao(currentDate);
            bill.setPhuongThucThanhToan(paymentService.getOne(PaymentId));
            bill.setTrangThaiHoaDon(billStatusService.findById(UUID.fromString("259b8bc3-5489-47c0-a115-b94a0cf6286f")));
            HoaDon b;

            bill.setTongTien(cartSession.getTongTien());

            b = billService.add(bill);





            // Tạo danh sách Chi Tiết Hóa Đơn
            for (var lstItem : cartDetailViewSession) {
                SanPham product = productService.getOne(lstItem.getSanPhamId());
                product.setSoLuong(product.getSoLuong() - lstItem.getSoLuong());
                product.setDaBan(product.getDaBan() + lstItem.getSoLuong());

                ChiTietHoaDon billDetail = new ChiTietHoaDon();
                billDetail.setIdChiTietHoaDon(UUID.randomUUID());
                billDetail.setSoLuong(lstItem.getSoLuong());
                billDetail.setGia(lstItem.getGia());
                billDetail.setHoaDon(b);
                billDetail.setChiTietSanPham(productDetailServiceimpl.getOne(lstItem.getChiTietSanPhamid()));

                // Lưu Chi Tiết Hóa Đơn vào cơ sở dữ liệu
                billDetailService.add(billDetail);
                productService.update(product.getIdSanPham(), product);
                ViewGioHangChiTiet cartDetailViewOld = lstItem;
                cartDetailViewSession.remove(cartDetailViewOld);
                if (cartDetailViewSession.size() == 0){
                    break;
                }
            }
            session.invalidate();
            // Truyền tham số qua RedirectAttributes
            redirectAttributes.addAttribute("billId", b.getIdHoaDon());
        }
        return "redirect:/hoaDon/orderComplete/{billId}";
    }

    @GetMapping("/orderComplete/{billId}")
    public String orderComplete(@PathVariable("billId") UUID billId, Model model) {
        model.addAttribute("bill", billService.getOne(billId));
        model.addAttribute("billDetail", billDetailService.getByBillId(billId));
        model.addAttribute("view", "/checkout/index.jsp");
        return "/giaodien/index";
    }

}
