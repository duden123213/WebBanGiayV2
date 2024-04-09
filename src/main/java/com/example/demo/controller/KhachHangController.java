package com.example.demo.controller;

import com.example.demo.entity.GioHang;
import com.example.demo.entity.GioHangChiTiet;
import com.example.demo.entity.KhachHang;
import com.example.demo.entity.NhanVien;
import com.example.demo.service.GioHangService;
import com.example.demo.service.KhachHangService;
import com.example.demo.service.NhanVienService;
import com.example.demo.service.serviceimpl.GioHangChiTietServiceImpl;
import com.example.demo.service.serviceimpl.GioHangServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;
@Controller
@RequestMapping("/khachHang")
public class KhachHangController {
    @Autowired
    KhachHangService khachHangService;

    @Autowired
    NhanVienService nhanVienService;
    @Autowired
    GioHangServiceImpl cartService;
    @Autowired
    GioHangChiTietServiceImpl cartdetailService;



    @GetMapping("/index")
    public String HienThi(Model model, HttpSession session, @RequestParam(value = "pageNo", defaultValue = "0") Integer page) {
        if (session.getAttribute("Name") != null) {
            //Nếu đã đăng nhập vào trang index

            model.addAttribute("cusList", khachHangService.findAll(page));


            String username = (String) session.getAttribute("userName");
            String password = (String) session.getAttribute("passWord");
            NhanVien checkLogin = nhanVienService.login(username, password);
            session.setAttribute("checkRole", nhanVienService.checkChucVu(username));

            session.setAttribute("Name", checkLogin);
            model.addAttribute("empLogin", checkLogin);
            model.addAttribute("view", "/KhachHang/index.jsp");
            return "index";


        }
        //Nếu chưa đăng nhập thì return về trang logina
        return "login";

    }

    @GetMapping("/indexcus")
    public String show_data_customer_cus(Model model, HttpSession session) {
        String username = (String) session.getAttribute("CustomerName");
        String password = (String) session.getAttribute("CustomerPass");
        model.addAttribute("cus", khachHangService.login(username, password));
        model.addAttribute("cusList", khachHangService.findAll());
        model.addAttribute("view", "/account/index.jsp");
        return "/giaodien/index";
    }

    @GetMapping("/indexcus/changeAccountInfo")
    public String changeAccountInfo(Model model, HttpSession session) {
        String username = (String) session.getAttribute("CustomerName");
        String password = (String) session.getAttribute("CustomerPass");
        model.addAttribute("cus", khachHangService.login(username, password));
        model.addAttribute("cusList", khachHangService.findAll());
        model.addAttribute("view", "/changeAccountInfo/index.jsp");
        return "/giaodien/index";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("view", "/signup/index.jsp");
        return "/giaodien/signup/index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("view", "/login/index.jsp");
        return "/giaodien/login/index";
    }

    @PostMapping("/loginOK")
    public String loginOK(@RequestParam("userName") String username,
                          @RequestParam("passWord") String password,
                          HttpSession session,
                          Model model) {
        if (username == "" || password == "") {
            model.addAttribute("erTrongCustomer", "Vui lòng nhập thông tin đầy đủ !!!");
            return "/giaodien/login/index";
        } else {
            KhachHang checkLogin = khachHangService.login(username, password);
            if (!(checkLogin == null)) {
                session.setAttribute("CustomerName", username);
                session.setAttribute("CustomerPass", password);
                return "redirect:/home";
            } else {
                model.addAttribute("erCheckCustomer", "Tên người dùng và mật khẩu không chính xác");
                return "/giaodien/login/index";
            }
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("CustomerName");
        return "redirect:/khachHang/login";
    }

    @GetMapping("/viewAdd")
    public String viewAdd(Model model, HttpSession session) {
//        String username = (String) session.getAttribute("username");
//        String password = (String) session.getAttribute("password");
//        Employee checkLogin = employeeService.login(username, password);
//        session.setAttribute("checkRole", employeeService.checkRole(username));
//
//        session.setAttribute("Name", checkLogin);
//        model.addAttribute("empLogin", checkLogin);
        model.addAttribute("view", "/KhachHang/add.jsp");
        return "index";

    }

    public void valiAdd(HttpSession session, Model model, String fullname, String dateofbirth, String address, String phone, String email, String username1, String password1) {
        String username = (String) session.getAttribute("userName");
        String password = (String) session.getAttribute("passWord");
        NhanVien checkLogin = nhanVienService.login(username, password);
        session.setAttribute("checkRole", nhanVienService.checkChucVu(username));

        session.setAttribute("Name", checkLogin);
        model.addAttribute("empLogin", checkLogin);
        model.addAttribute("fullnameAdd", fullname);
        model.addAttribute("usernameAdd", username1);
        model.addAttribute("passwordAdd", password1);
        model.addAttribute("dateOfBirthAdd", dateofbirth);
        model.addAttribute("emailAdd", email);
        model.addAttribute("addressAdd", address);
        model.addAttribute("phoneNumberAdd", phone);
    }

    @PostMapping("/add")
    public String themMoi(Model model,
                          HttpSession session,
                          @RequestParam("tenKhachHang") String fullname,
                          @RequestParam("ngaySinh") String dateofbirth,
                          @RequestParam("diaChi") String address,
                          @RequestParam("soDienThoai") String phone,
                          @RequestParam("email") String email,
                          @RequestParam("gioiTinh") int gender,
                          @RequestParam("userName") String username1,
                          @RequestParam("passWord") String password1

    ) {
        if (session.getAttribute("Name") != null) {
            if (fullname.isBlank()) {
                valiAdd(session, model, fullname, dateofbirth, address, phone, email, username1, password1);
                model.addAttribute("errName", "Tên đầy đủ của người nhận không hợp lệ");
                model.addAttribute("view", "/KhachHang/add.jsp");
                return "index";

            }
            if (dateofbirth.isBlank()) {
                valiAdd(session, model, fullname, dateofbirth, address, phone, email, username1, password1);
                model.addAttribute("errDate", "Ngày sinh của người nhận không hợp lệ");
                model.addAttribute("view", "/KhachHang/add.jsp");
                return "index";
            }
            String Regex = "^(0|84)(2(0[3-9]|1[0-6|8|9]|2[0-2|5-9]|3[2-9]|4[0-9]|5[1|2|4-9]|6[0-3|9]|7[0-7]|8[0-9]|9[0-4|6|7|9])|3[2-9]|5[5|6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])([0-9]{7})$";

            if (phone.isBlank()) {
                valiAdd(session, model, fullname, dateofbirth, address, phone, email, username1, password1);
                model.addAttribute("errPhone", "Số điện thoại người nhận không hợp lệ");
                model.addAttribute("view", "/KhachHang/add.jsp");
                return "index";
            }
            if (email.isBlank()) {
                valiAdd(session, model, fullname, dateofbirth, address, phone, email, username1, password1);
                model.addAttribute("errEmail", "Email người nhận không hợp lệ");
                model.addAttribute("view", "/KhachHang/add.jsp");
                return "index";
            }
            if (address.isBlank()) {
                valiAdd(session, model, fullname, dateofbirth, address, phone, email, username1, password1);
                model.addAttribute("errAdd", "Địa chỉ người nhận không hợp lệ");
                model.addAttribute("view", "/KhachHang/add.jsp");
                return "index";
            }
            if (username1.isBlank()) {
                valiAdd(session, model, fullname, dateofbirth, address, phone, email, username1, password1);
                model.addAttribute("errUser", "Tên người dùng nhận không hợp lệ");
                model.addAttribute("view", "/KhachHang/add.jsp");
                return "index";
            }
            if (password1.isBlank()) {
                valiAdd(session, model, fullname, dateofbirth, address, phone, email, username1, password1);
                model.addAttribute("errPass", "Mật khẩu người nhận không hợp lệ");
                model.addAttribute("view", "/KhachHang/add.jsp");
                return "index";
            } else {
                List<KhachHang> checkUser = khachHangService.getByUserName(username1);
                if (!(checkUser.isEmpty())) {
                    valiAdd(session, model, fullname, dateofbirth, address, phone, email, username1, password1);
                    model.addAttribute("errUserTrung", "Tên người dùng trùng lặp !!! Vui lòng nhập tên người dùng khác");
                    model.addAttribute("view", "/KhachHang/add.jsp");
                    return "index";
                }
                Date currentDate = new Date(System.currentTimeMillis());
                Date birth = Date.valueOf(dateofbirth);
                if (birth.after(currentDate)) {
                    valiAdd(session, model, fullname, dateofbirth, address, phone, email, username1, password1);
                    model.addAttribute("errDateAfter", "Ngày sinh phải nhỏ hơn ngày hiện tại!!!");
                    model.addAttribute("view", "/KhachHang/add.jsp");
                    return "index";
                }
                if (!phone.matches(Regex)) {
                    valiAdd(session, model, fullname, dateofbirth, address, phone, email, username1, password1);
                    model.addAttribute("errPhoneErrr", "Cú pháp số điện thoại không chính xác !!!");
                    model.addAttribute("view", "/KhachHang/add.jsp");
                    return "index";
                }
                KhachHang customer = new KhachHang();
                customer.setTenKhachHang(fullname);
                customer.setNgaySinh(Date.valueOf(dateofbirth));
                customer.setDiaChi(address);
                customer.setSoDienThoai(phone);
                customer.setNgayTao(String.valueOf(currentDate));
                customer.setEmail(email);
                customer.setGioiTinh(gender);
                customer.setTrangThai(1);
                customer.setUserName(username1);
                customer.setPassWord(password1);
                khachHangService.add(customer);
                GioHang cart = new GioHang();
                cart.setSoLuong(0);
                cart.setTongTien(BigDecimal.valueOf(0));
                cart.setTrangThai(0);
                cart.setKhachHang(khachHangService.detail(customer.getIdKhachHang()));
                cartService.save(cart);
                GioHangChiTiet cartDetail = new GioHangChiTiet();
                cartDetail.setGia(BigDecimal.valueOf(0));
                cartDetail.setSoLuong(0);
                cartDetail.setChiTietSanPham(null);
                cartDetail.setGioHang(cart);
                cartdetailService.save(cartDetail);
                return "redirect:/khachHang/index";
            }

        } else {
            return "login";
        }
    }


    @PostMapping("/addLogin")
    public String themMoiLogin(Model model,
                               HttpSession session,
                               @RequestParam("tenKhachHang") String fullname,
                               @RequestParam("ngaySinh") String dateofbirth,
                               @RequestParam("diaChi") String address,
                               @RequestParam("soDienThoai") String phone,
                               @RequestParam("email") String email,
                               @RequestParam("gioiTinh") int gender,
                               @RequestParam("userName") String username1,
                               @RequestParam("passWord") String password1
    ) {
        if (fullname.isBlank()) {
            valiAdd(session, model, fullname, dateofbirth, address, phone, email, username1, password1);
            model.addAttribute("errName", "Tên đầy đủ của người nhận không hợp lệ");
            return "/giaodien/signup/index";

        }
        if (dateofbirth.isBlank()) {
            valiAdd(session, model, fullname, dateofbirth, address, phone, email, username1, password1);
            model.addAttribute("errDate", "Ngày sinh của người nhận không hợp lệ");
            return "/giaodien/signup/index";
        }
        String Regex = "^(0|84)(2(0[3-9]|1[0-6|8|9]|2[0-2|5-9]|3[2-9]|4[0-9]|5[1|2|4-9]|6[0-3|9]|7[0-7]|8[0-9]|9[0-4|6|7|9])|3[2-9]|5[5|6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])([0-9]{7})$";

        if (phone.isBlank()) {
            valiAdd(session, model, fullname, dateofbirth, address, phone, email, username1, password1);
            model.addAttribute("errPhone", "Số điện thoại người nhận không hợp lệ");
            return "/giaodien/signup/index";
        }
        if (email.isBlank()) {
            valiAdd(session, model, fullname, dateofbirth, address, phone, email, username1, password1);
            model.addAttribute("errEmail", "Email người nhận không hợp lệ");
            return "/giaodien/signup/index";
        }
        if (address.isBlank()) {
            valiAdd(session, model, fullname, dateofbirth, address, phone, email, username1, password1);
            model.addAttribute("errAdd", "Địa chỉ người nhận không hợp lệ");
            return "/giaodien/signup/index";
        }
        if (username1.isBlank()) {
            valiAdd(session, model, fullname, dateofbirth, address, phone, email, username1, password1);
            model.addAttribute("errUser", "Tên người dùng nhận không hợp lệ");
            return "/giaodien/signup/index";
        }
        if (password1.isBlank()) {
            valiAdd(session, model, fullname, dateofbirth, address, phone, email, username1, password1);
            model.addAttribute("errPass", "Mật khẩu người nhận không hợp lệ");
            return "/giaodien/signup/index";
        } else {
            List<KhachHang> checkUser = khachHangService.getByUserName(username1);
            if (!(checkUser.isEmpty())) {
                valiAdd(session, model, fullname, dateofbirth, address, phone, email, username1, password1);
                model.addAttribute("errUserTrung", "Tên người dùng trùng lặp !!! Vui lòng nhập tên người dùng khác");
                return "/giaodien/signup/index";
            }
            Date currentDate = new Date(System.currentTimeMillis());
            Date birth = Date.valueOf(dateofbirth);
            if (birth.after(currentDate)) {
                valiAdd(session, model, fullname, dateofbirth, address, phone, email, username1, password1);
                model.addAttribute("errDateAfter", "Ngày sinh phải nhỏ hơn ngày hiện tại !!!");
                return "/giaodien/signup/index";
            }
            if (!phone.matches(Regex)) {
                valiAdd(session, model, fullname, dateofbirth, address, phone, email, username1, password1);
                model.addAttribute("errPhoneErrr", "Cú pháp số điện thoại không chính xác !!!");
                return "/giaodien/signup/index";
            }
            KhachHang customer = new KhachHang();
            customer.setTenKhachHang(fullname);
            customer.setNgaySinh(Date.valueOf(dateofbirth));
            customer.setDiaChi(address);
            customer.setSoDienThoai(phone);
            customer.setNgayTao(String.valueOf(currentDate));
            customer.setEmail(email);
            customer.setGioiTinh(gender);
            customer.setTrangThai(1);
            customer.setUserName(username1);
            customer.setPassWord(password1);
            khachHangService.add(customer);
            GioHang cart = new GioHang();
            cart.setSoLuong(0);
//                  cart.setTotalMoney(BigDecimal.valueOf(0));
            cart.setTongTien(BigDecimal.valueOf(0));
            cart.setKhachHang(customer);
            cartService.save(cart);
            GioHangChiTiet cartDetail = new GioHangChiTiet();
            cartDetail.setGia(BigDecimal.valueOf(0));
            cartDetail.setSoLuong(0);
            cartDetail.setChiTietSanPham(null);
            cartDetail.setGioHang(cart);
            cartdetailService.save(cartDetail);
            model.addAttribute("sigsUp", "Đăng ký thành công!!! vui lòng hãy đăng nhập");
            return "/giaodien/login/index";
        }
    }

    public void valiUpdate(Model model, HttpSession session, UUID id) {
        model.addAttribute("cus", khachHangService.detail(id));
        String username = (String) session.getAttribute("userName");
        String password = (String) session.getAttribute("passWord");
        NhanVien checkLogin = nhanVienService.login(username, password);
        session.setAttribute("checkRole", nhanVienService.checkChucVu(username));

        session.setAttribute("Name", checkLogin);
        model.addAttribute("empLogin", checkLogin);
        model.addAttribute("view", "/KhachHang/detail.jsp");
    }

    public void valiUpdateCus(Model model, HttpSession session, UUID id) {
        model.addAttribute("cus", khachHangService.detail(id));
        String username = (String) session.getAttribute("userName");
        String password = (String) session.getAttribute("passWord");
        NhanVien checkLogin = nhanVienService.login(username, password);
        session.setAttribute("checkRole", nhanVienService.checkChucVu(username));

        session.setAttribute("Name", checkLogin);
        model.addAttribute("empLogin", checkLogin);
        model.addAttribute("view", "/changeAccountInfo/index.jsp");
    }

    @PostMapping("/update")
    public String update(Model model,
                         @RequestParam("idKhachHang") UUID id,
                         @RequestParam("tenKhachHang") String fullname,
                         @RequestParam("ngaySinh") String dateofbirth,
                         @RequestParam("diaChi") String address,
                         @RequestParam("soDienThoai") String phone,
                         @RequestParam("email") String email,
                         @RequestParam("gioiTinh") int gender,
                         @RequestParam("trangThai") Integer status,
                         @RequestParam("userName") String username1,
                         @RequestParam("passWord") String password1,
                         HttpSession session
    ) {
        if (fullname.isBlank()) {
            model.addAttribute("errName", "Tên đầy đủ của người nhận không hợp lệ");
            valiUpdate(model, session, id);
            return "index";

        }
        if (dateofbirth.isBlank()) {
            model.addAttribute("errDate", "Ngày sinh của người nhận không hợp lệ");
            valiUpdate(model, session, id);
            return "index";
        }
        String Regex = "^(0|84)(2(0[3-9]|1[0-6|8|9]|2[0-2|5-9]|3[2-9]|4[0-9]|5[1|2|4-9]|6[0-3|9]|7[0-7]|8[0-9]|9[0-4|6|7|9])|3[2-9]|5[5|6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])([0-9]{7})$";

        if (phone.isBlank()) {
            model.addAttribute("errPhone", "Số điện thoại người nhận không hợp lệ");
            valiUpdate(model, session, id);
            return "index";
        }
        if (email.isBlank()) {
            model.addAttribute("errEmail", "Email người nhận không hợp lệ");
            valiUpdate(model, session, id);
            return "index";
        }
        if (address.isBlank()) {
            model.addAttribute("errAdd", "Địa chỉ người nhận không hợp lệ");
            valiUpdate(model, session, id);
            return "index";
        }
        if (username1.isBlank()) {
            model.addAttribute("errUser", "Tên người dùng nhận không hợp lệ");
            valiUpdate(model, session, id);
            return "index";
        }
        if (password1.isBlank()) {
            model.addAttribute("errPass", "Mật khẩu người nhận không hợp lệ");
            valiUpdate(model, session, id);
            return "index";
        } else {
            List<KhachHang> checkUser = khachHangService.checkUserNameUpdate(khachHangService.detail(id).getUserName(), username1);
            if (!(checkUser.isEmpty())) {
                model.addAttribute("errUserTrung", "Tên người dùng trùng lặp !!! Vui lòng nhập tên người dùng khác");
                valiUpdate(model, session, id);
                return "index";
            }
            Date currentDate = new Date(System.currentTimeMillis());
            Date birth = Date.valueOf(dateofbirth);
            if (birth.after(currentDate)) {
                model.addAttribute("errDateAfter", "Ngày sinh phải nhỏ hơn ngày hiện tại!!!");
                valiUpdate(model, session, id);
                return "index";
            }
            if (!phone.matches(Regex)) {
                model.addAttribute("errPhoneErrr", "Cú pháp số điện thoại không chính xác!!!");
                valiUpdate(model, session, id);
                return "index";
            }
            KhachHang customer = new KhachHang();

            customer.setTenKhachHang(fullname);
            customer.setNgaySinh(Date.valueOf(dateofbirth));
            customer.setDiaChi(address);
            customer.setSoDienThoai(phone);
            customer.setIdKhachHang(id);
            customer.setEmail(email);
            customer.setGioiTinh(gender);
            customer.setTrangThai(status);
            customer.setUserName(username1);
            customer.setPassWord(password1);
            customer.setNgayTao(String.valueOf(currentDate));


            khachHangService.update(customer);
            return "redirect:/khachHang/index";
        }
    }


    @PostMapping("/updateCus/{idKhachHang}")
    public String updateCus(Model model,
                            @PathVariable("idKhachHang") UUID id,
                            @RequestParam("tenKhachHang") String fullname,
                            @RequestParam("ngaySinh") String dateofbirth,
                            @RequestParam("diaChi") String address,
                            @RequestParam("soDienThoai") String phone,
                            @RequestParam("email") String email,
                            @RequestParam("gioiTinh") int gender,
//                         @RequestParam("status") Integer status,
                            @RequestParam("userName") String username1,
                            @RequestParam("passWord") String password1,
                            HttpSession session
    ) {
        if (fullname.isBlank()) {
            model.addAttribute("errName", "Tên đầy đủ của người nhận không hợp lệ");
            valiUpdateCus(model, session, id);
            return "/giaodien/index";

        }
        if (dateofbirth.isBlank()) {
            model.addAttribute("errDate", "Invalid Receiver Date Of Birth");
            valiUpdateCus(model, session, id);
            return "/giaodien/index";
        }
        String Regex = "^(0|84)(2(0[3-9]|1[0-6|8|9]|2[0-2|5-9]|3[2-9]|4[0-9]|5[1|2|4-9]|6[0-3|9]|7[0-7]|8[0-9]|9[0-4|6|7|9])|3[2-9]|5[5|6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])([0-9]{7})$";

        if (phone.isBlank()) {
            model.addAttribute("errPhone", "Invalid Receiver Phone Number");
            valiUpdateCus(model, session, id);
            return "/giaodien/index";
        }
        if (email.isBlank()) {
            model.addAttribute("errEmail", "Invalid Receiver Email");
            valiUpdateCus(model, session, id);
            return "/giaodien/index";
        }
        if (address.isBlank()) {
            model.addAttribute("errAdd", "Invalid Receiver Address");
            valiUpdateCus(model, session, id);
            return "/giaodien/index";
        }
        if (username1.isBlank()) {
            model.addAttribute("errUser", "Invalid Receiver UserName");
            valiUpdateCus(model, session, id);
            return "/giaodien/index";
        }
        if (password1.isBlank()) {
            model.addAttribute("errPass", "Invalid Receiver Password");
            valiUpdateCus(model, session, id);
            return "/giaodien/index";
        } else {
            List<KhachHang> checkUser = khachHangService.checkUserNameUpdate(khachHangService.detail(id).getUserName(), username1);
            if (!(checkUser.isEmpty())) {
                model.addAttribute("errUserTrung", "Duplicate Username !!! Please enter another username");
                valiUpdateCus(model, session, id);
                return "/giaodien/index";
            }
            Date currentDate = new Date(System.currentTimeMillis());
            Date birth = Date.valueOf(dateofbirth);
            if (birth.after(currentDate)) {
                model.addAttribute("errDateAfter", "Date of birth must be less than current date !!!");
                valiUpdateCus(model, session, id);
                return "/giaodien/index";
            }
            if (!phone.matches(Regex)) {
                model.addAttribute("errPhoneErrr", "Phone number syntax is incorrect !!!");
                valiUpdateCus(model, session, id);
                return "/giaodien/index";
            }
            KhachHang customer = new KhachHang();

            customer.setTenKhachHang(fullname);
            customer.setNgaySinh(Date.valueOf(dateofbirth));
            customer.setDiaChi(address);
            customer.setSoDienThoai(phone);
            customer.setIdKhachHang(id);
            customer.setEmail(email);
            customer.setGioiTinh(gender);
            customer.setTrangThai(0);
            customer.setUserName(username1);
            customer.setPassWord(password1);
            customer.setNgayTao(String.valueOf(currentDate));


            khachHangService.update(customer);
            return "redirect:/khachHang/indexcus";
        }
    }


    @GetMapping("/detail/{idKhachHang}")
    public String detail(Model model, @PathVariable("idKhachHang") UUID id, HttpSession session) {
        model.addAttribute("cus", khachHangService.detail(id));
        String username = (String) session.getAttribute("userName");
        String password = (String) session.getAttribute("passWord");
        NhanVien checkLogin = nhanVienService.login(username, password);
        session.setAttribute("checkRole", nhanVienService.checkChucVu(username));

        session.setAttribute("Name", checkLogin);
        model.addAttribute("empLogin", checkLogin);
        model.addAttribute("view", "/KhachHang/detail.jsp");
        return "index";

    }

    @GetMapping("/timKiem")
    public String timKiem(Model model,
                          @RequestParam("phone1") String phone,
                          HttpSession session,
                          @RequestParam(value = "pageNo", defaultValue = "0") Integer page) {


        if (phone == "") {
            model.addAttribute("cusList", khachHangService.findAll(page));
        } else {
            model.addAttribute("tim", khachHangService.timKiem(phone));
        }
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        NhanVien checkLogin = nhanVienService.login(username, password);
        session.setAttribute("checkRole", nhanVienService.checkChucVu(username));

        session.setAttribute("Name", checkLogin);
        model.addAttribute("empLogin", checkLogin);
        model.addAttribute("view", "/KhachHang/index.jsp");
        return "index";

    }
}

