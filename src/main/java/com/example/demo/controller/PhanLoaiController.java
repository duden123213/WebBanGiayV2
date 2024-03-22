package com.example.demo.controller;

import com.example.demo.entity.PhanLoai;
import com.example.demo.service.serviceimpl.PhanLoaiServiceImpl;
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
@RequestMapping("/phanLoai")
public class PhanLoaiController {
        @Autowired
        private PhanLoaiServiceImpl phanLoaiService;

        @GetMapping("/index")
        public String hienThi(Model model, HttpSession session){
            model.addAttribute("listPhanLoai",phanLoaiService.getAll());
            model.addAttribute("PhanLoai",new PhanLoai());
            model.addAttribute("view", "/PhanLoai/index.jsp");
            return "index";
        }

        @PostMapping("/add")
        public String add(Model model,
                          @RequestParam("tenPhanLoai") String tenPhanLoai,
                          @RequestParam("trangThai") Integer trangThai
        ){
            PhanLoai phanLoai = new PhanLoai(tenPhanLoai, trangThai);
            phanLoaiService.save(phanLoai);
            return "redirect:/phanLoai/index";
        }
        @GetMapping("/detail/{idPhanLoai}")
        public String detail(Model model,
                             @PathVariable("idPhanLoai") UUID idPhanLoai){
            model.addAttribute("listPhanLoai",phanLoaiService.getAll());
            model.addAttribute("phanloai",phanLoaiService.getOne(idPhanLoai));
            model.addAttribute("view", "/PhanLoai/index.jsp");
            return "index";
        }
        @GetMapping("/delete/{idPhanLoai}")
        public String delete(Model model,
                             @PathVariable("idPhanLoai") UUID id){
            phanLoaiService.delete(id);
            return "redirect:/phanLoai/index";
        }
        @PostMapping("/update/{idPhanLoai}")
        public String update(Model model,
                             @PathVariable("idPhanLoai") UUID id,
                             @ModelAttribute("phanloai") PhanLoai phanLoai){
            phanLoaiService.update(id,phanLoai);
            return "redirect:/phanLoai/index";
        }

}
