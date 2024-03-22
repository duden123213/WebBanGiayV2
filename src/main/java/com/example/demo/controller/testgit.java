package com.example.demo.controller;

import com.example.demo.entity.KichCo;
import com.example.demo.service.serviceimpl.KichCoServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class testgit {
    @Autowired
    private KichCoServiceImpl kichCoService;

//    @GetMapping("/index")
//    public String hienThi(Model model, HttpSession session){
////        if (session.getAttribute("Name") != null){
////            //Nếu đã đăng nhập vào trang index
////            String username = (String) session.getAttribute("username");
////            String password = (String) session.getAttribute("password");
////            NhanVien checkLogin = nhanVienService.login(username,password);
////            model.addAttribute("nhanvienLogin",checkLogin);
////            //////
////        model.addAttribute("listKichCo",kichCoService.getAll());
////        model.addAttribute("KichCo",new KichCo());
////        model.addAttribute("view", "/KichCo/index.jsp");
////        return "index";
////        }
//////        //Nếu chưa đăng nhập thì return về trang logina
////        return "index";
//    }
}
