package com.example.savis_intern_project.controller;


import com.example.savis_intern_project.entity.*;
import com.example.savis_intern_project.entity.ViewModels.ProductView;
import com.example.savis_intern_project.entity.ViewModels.WishListView;
import com.example.savis_intern_project.service.serviceimpl.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/favor")
public class FavoriteProductsController {

    @Autowired
    private HttpSession httpSession;
    @Autowired
    WishListServiceimpl wishListServiceimpl;
    @Autowired
    ProductServiceimpl productServiceimpl;
    @Autowired
    CustomerServiceImpl customerServiceimpl;

    @GetMapping("/index")
    public String hienThi(Model model) {

        model.addAttribute("listFavor", wishListServiceimpl.getAll());
        model.addAttribute("FavoriteProducts", new FavoriteProductsController());
        model.addAttribute("listProduct", productServiceimpl.getAll());
        model.addAttribute("listCustomer", customerServiceimpl.findAll());
        model.addAttribute("view", "/FavoriteProduct/index.jsp");
        return "index";
    }

    @GetMapping("/indexcus")
    public String show_data_favor_cus(Model model,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "8") int size) {

        if (httpSession.getAttribute("CustomerName") != null) {
            String username = (String) httpSession.getAttribute("CustomerName");
            Customer customer = customerServiceimpl.getCustomerByName(username);
            Pageable pageable = Pageable.ofSize(size).withPage(page);
            Page<WishListView> entitiesPage = wishListServiceimpl.getAllByCustomerIdWithPagination(customer.getId(), pageable);
            model.addAttribute("entitiesPage", entitiesPage);
            model.addAttribute("listFavor", entitiesPage.getContent());
            model.addAttribute("quantityFavor", entitiesPage.getContent().size());
        }
        model.addAttribute("view", "/wishlist/index.jsp");
        return "/customerFE/index";
    }

    @PostMapping("/like/{id}")
    public String like(@PathVariable("id") UUID id) {
        if (httpSession.getAttribute("CustomerName") != null) {
            String username = (String) httpSession.getAttribute("CustomerName");
            Customer customer = customerServiceimpl.getCustomerByName(username);
            wishListServiceimpl.Like(customer.getId(), id);
        }
        return "redirect:/favor/indexcus";
    }

    @PostMapping("/add")
    public String add(Model model,

                      @RequestParam("customer") Customer customer,
                      @RequestParam("product") Product product


    ) {
        WishList wishList = new WishList(customer, product);
        wishListServiceimpl.add(wishList);
        return "redirect:/favor/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @PathVariable("id") UUID id) {
        model.addAttribute("listFavor", wishListServiceimpl.getAll());
        model.addAttribute("spyt", wishListServiceimpl.getOne(id));
        model.addAttribute("listCustomer", customerServiceimpl.findAll());
        model.addAttribute("listProduct", productServiceimpl.getAll());
        model.addAttribute("view", "/FavoriteProduct/index.jsp");
        return "index";
    }

    @GetMapping("delete")
    public String delete(Model model,
                         @RequestParam("id") UUID id) {
        wishListServiceimpl.delete(id);
        return "redirect:/favor/index";
    }

    @PostMapping("/update/{id}")
    public String update(Model model,
                         @PathVariable("id") UUID id,
                         @ModelAttribute("favoriteProducts") WishList wishList) {
        wishListServiceimpl.update(id, wishList);
        return "redirect:/favor/index";
    }
}
