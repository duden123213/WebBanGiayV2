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
}
