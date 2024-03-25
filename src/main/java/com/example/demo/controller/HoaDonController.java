package com.example.demo.controller;

import com.example.demo.entity.ChiTietHoaDon;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.KhachHang;
import com.example.demo.service.serviceimpl.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/hoaDon")
public class HoaDonController {
    @Autowired
    HoaDonServiceImpl billService;
    @Autowired
    ChiTietHoaDonServiceImpl billDetailService;
    @Autowired
    KhachHangServiceImpl customerService;
    @Autowired
    NhanVienServiceImpl employeeService;

    @Autowired
    TrangThaiHoaDonServiceImpl billStatusService;

    @GetMapping("/index")
    public String show_data_bill(Model model, HttpSession session) {

            model.addAttribute("listBill", billService.getAll());
            model.addAttribute("listCustomer", customerService.findAll());
            model.addAttribute("listEmployee", employeeService.findAll());
            model.addAttribute("listBillStatus", billStatusService.getBillStatus());
            model.addAttribute("view", "/HoaDon/index.jsp");
            for (HoaDon bill : billService.getAll()
            ) {
                System.out.println(bill.getTenNguoiNhan());
                System.out.println(bill.getSoDienThoai());
                System.out.println(bill.getDiaChi());
                System.out.println(bill.getTongTien());
                System.out.println(bill.getNgayTao());
                System.out.println(bill.getTrangThaiHoaDon());
            }
            return "index";
        }





    @GetMapping("/indexcus")
    public String show_data_bill_cus(Model model, HttpSession session/*,@PathVariable("idCus")UUID idCus*/) {

        if (session.getAttribute("CustomerName") != null)
        {
            String username = (String) session.getAttribute("CustomerName");
            KhachHang customer = customerService.getKhachHangByName(username);
            model.addAttribute("listCustomer", customer);
            model.addAttribute("listBill", billService.getAllByKhachHangId(customer.getIdKhachHang()));
            model.addAttribute("listEmployee", employeeService.findAll());
            model.addAttribute("listBillStatus", billStatusService.getBillStatus());
        }
        model.addAttribute("view", "/hoaDon/index.jsp");
        return "/giaodien/index";
    }

    @GetMapping("/index/{billId}")
    public String show_data_bill(Model model, @PathVariable("billId") UUID billId) {
        model.addAttribute("listBill", billService.getAll());
        model.addAttribute("listCustomer", customerService.findAll());
        model.addAttribute("listEmployee", employeeService.findAll());
        model.addAttribute("listBillStatus", billStatusService.getBillStatus());
        model.addAttribute("billD", billService.getOne(billId));
        List<ChiTietHoaDon> billDetailList = billDetailService.getAllById(billId);
        double allPrice = 0; // Khởi tạo biến tổng giá trị hóa đơn

        System.out.println(allPrice);
        model.addAttribute("billDetailD", billDetailList);
        model.addAttribute("allPrice", allPrice);


        model.addAttribute("view", "/HoaDon/index.jsp");
        return "index";
    }

    @GetMapping("delete_bill/{billId}")
    public String delete_bill(Model model, @PathVariable("billId") UUID billId) {
        billService.delete(billId);
        return "redirect:/hoaDon/index";
    }

    @PostMapping("change_bill_status/{billId}")
    public String change_bill_status(Model model, @PathVariable("billId") UUID billId) {
        billService.changeStatus(billId);
        return "redirect:/hoaDon/index";
    }

}
