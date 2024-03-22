package com.example.demo.controller;

import com.example.demo.entity.KichCo;
import com.example.demo.service.serviceimpl.KichCoServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/kichCo")
public class KichCoController {
    @Autowired
    private KichCoServiceImpl kichCoService;

    @GetMapping("/index")
    public String hienThi(Model model, HttpSession session){
//        if (session.getAttribute("Name") != null){
//            //Nếu đã đăng nhập vào trang index
//            String username = (String) session.getAttribute("username");
//            String password = (String) session.getAttribute("password");
//            NhanVien checkLogin = nhanVienService.login(username,password);
//            model.addAttribute("nhanvienLogin",checkLogin);
//            //////
        model.addAttribute("listKichCo",kichCoService.getAll());
        model.addAttribute("KichCo",new KichCo());
        model.addAttribute("view", "/KichCo/index.jsp");
        return "index";
//        }
////        //Nếu chưa đăng nhập thì return về trang logina
//        return "index";
    }

    @PostMapping("/add")
    public String add(Model model,
                      @RequestParam("tenKichCo") String tenKichCo,
                      @RequestParam("moTa") String moTa,
                      @RequestParam("trangThai") Integer trangThai
    ){
        KichCo kichCo = new KichCo(tenKichCo, moTa, trangThai);
        kichCoService.save(kichCo);
        return "redirect:/kichCo/index";
    }
    @GetMapping("/detail/{idKichCo}")
    public String detail(Model model,
                         @PathVariable("idKichCo") UUID id){
        model.addAttribute("listKichCo",kichCoService.getAll());
        model.addAttribute("kc",kichCoService.getOne(id));
        model.addAttribute("view", "/KichCo/index.jsp");
        return "index";
    }
    @GetMapping("/delete/{idKichCo}")
    public String delete(Model model,
                         @PathVariable("idKichCo") UUID id){
        kichCoService.delete(id);
        return "redirect:/kichCo/index";
    }
    @PostMapping("/update/{idKichCo}")
    public String update(Model model,
                         @PathVariable("idKichCo") UUID idKichCo,
                         @ModelAttribute("kichco") KichCo kichCo){
        kichCoService.update(idKichCo,kichCo);
        return "redirect:/kichCo/index";
    }
}
