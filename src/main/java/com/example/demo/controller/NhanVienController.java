package com.example.demo.controller;

import com.example.demo.entity.ChucVu;
import com.example.demo.entity.NhanVien;
import com.example.demo.service.ChucVuService;
import com.example.demo.service.NhanVienService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.List;
import java.util.UUID;
@Controller
public class NhanVienController {
    @Autowired
    NhanVienService nhanVienService;
    @Autowired
    ChucVuService chucVuService;

    @GetMapping("/Login")
    public String login(){
        return "login";
    }

    @GetMapping("/indexxx")
    public String indexxx(HttpSession session, Model model){
        String username = (String) session.getAttribute("userName");
        String password = (String) session.getAttribute("passWord");
        NhanVien checkLogin = nhanVienService.login(username,password);
        session.setAttribute("checkRole",nhanVienService.checkChucVu(username));

        session.setAttribute("Name", checkLogin);
        model.addAttribute("empLogin",checkLogin);
        return "index";


    }


    @PostMapping("/loginOK")
    public String loginOK(@RequestParam("userName")String username,
                          @RequestParam("passWord")String password,
                          HttpSession session,
                          Model model){
        if (username == ""||password == ""){
            model.addAttribute("erTrong", "Vui lòng nhập thông tin đầy đủ !!!");
            return "login";
        }
        else {
            session.setAttribute("userName",username);
            session.setAttribute("passWord",password);
            NhanVien checkLogin = nhanVienService.login(username,password);
            session.setAttribute("checkRole",nhanVienService.checkChucVu(username));
            if (!(checkLogin == null)){
                session.setAttribute("Name", checkLogin);
                model.addAttribute("empLogin",checkLogin);
                return "index";
            }
            else {
                model.addAttribute("erCheck","Tên người dùng và mật khẩu không chính xác");
                model.addAttribute("reUsername",username);
                model.addAttribute("rePassword",password);
                return "login";
            }
        }

    }
    @GetMapping("/logout")
    public String loguot(HttpSession session){
        session.removeAttribute("Name");
        return "redirect:/Login";
    }

    @GetMapping("/nhanVien/index")
    public String HienThi(Model model,HttpSession session,@RequestParam(value = "pageNo",defaultValue = "0")Integer page) {


        if (session.getAttribute("Name") != null){
            if (session.getAttribute("checkRole") == null){
                if (nhanVienService.findAll().isEmpty()){
                    model.addAttribute("erList","Empty list");
                }
                Page<NhanVien> customerList = nhanVienService.listDesc(page) ;
                model.addAttribute("empList", customerList);
                String username = (String) session.getAttribute("username");
                String password = (String) session.getAttribute("password");
                NhanVien checkLogin = nhanVienService.login(username,password);
                session.setAttribute("checkRole",nhanVienService.checkChucVu(username));

                session.setAttribute("Name", checkLogin);
                model.addAttribute("empLogin",checkLogin);

                model.addAttribute("view", "/NhanVien/index.jsp");
                return "index";
            }
            else {
                model.addAttribute("erCheckRole","Nhân viên không thể sử dụng chức năng này");
                return "login";
            }

        }
        return "login";


    }
    public void valiUpdate(Model model,HttpSession session,UUID id){
        model.addAttribute("emp", nhanVienService.detail(id));
        model.addAttribute("role", chucVuService.getAll());
        String username = (String) session.getAttribute("userName");
        String password = (String) session.getAttribute("passWord");
        NhanVien checkLogin = nhanVienService.login(username,password);
        session.setAttribute("checkRole",nhanVienService.checkChucVu(username));

        session.setAttribute("Name", checkLogin);
        model.addAttribute("empLogin",checkLogin);
        model.addAttribute("view", "/NhanVien/detail.jsp");
    }

    @PostMapping("/nhanVien/update")
    public String updte(Model model,
                        @RequestParam("idNhanVien") UUID id,
                        @RequestParam("tenNhanVien") String fullName,
                        @RequestParam("username1") String username,
                        @RequestParam("password1") String password,
                        @RequestParam("ngaySinh") String dateOfBirth,
                        @RequestParam("gioiTinh") int gender,
                        @RequestParam("soDienThoai") String phoneNumber,
                        @RequestParam("email") String email,
                        @RequestParam("diaChi") String address,
                        @RequestParam("trangThai") int status,
                        @RequestParam("idChucVu")String idRole,
                        HttpSession session
    ) {
        if (session.getAttribute("Name") != null){
            if (session.getAttribute("checkRole") == null){
                if (fullName.isBlank()){
                    model.addAttribute("errName","Tên đầy đủ của người nhận không hợp lệ");
                    valiUpdate(model,session,id);
                    return "index";
                }
                if (username.isBlank()){
                    model.addAttribute("errUser"," Tên người dùng nhận không hợp lệ");
                    valiUpdate(model,session,id);
                    return "index";
                }
                if (password.isBlank()){
                    model.addAttribute("errPass","Mật khẩu người nhận không hợp lệ");
                    valiUpdate(model,session,id);
                    return "index";
                }
                if (dateOfBirth.isBlank()){
                    model.addAttribute("errDate","Ngày sinh của người nhận không hợp lệ");
                    valiUpdate(model,session,id);
                    return "index";
                }
                if (phoneNumber.isBlank()){
                    model.addAttribute("errPhone","Số điện thoại người nhận không hợp lệ");
                    valiUpdate(model,session,id);
                    return "index";
                }
                if (email.isBlank()){
                    model.addAttribute("errEmail","Email người nhận không hợp lệ");
                    valiUpdate(model,session,id);
                    return "index";
                }
                if (address.isBlank()){
                    model.addAttribute("errAdd","Địa chỉ người nhận không hợp lệ");
                    valiUpdate(model,session,id);
                    return "index";
                }
                else {
                    List<NhanVien>  checkUser = nhanVienService.checkUserNameUpdate(nhanVienService.detail(id).getUserName(),username);
                    if (!(checkUser.isEmpty())){
                        model.addAttribute("errUserTrung","Tên người dùng trùng lặp !!! Vui lòng nhập tên người dùng khác");
                        valiUpdate(model,session,id);
                        return "index";
                    }

                    String Regex = "^(0|84)(2(0[3-9]|1[0-6|8|9]|2[0-2|5-9]|3[2-9]|4[0-9]|5[1|2|4-9]|6[0-3|9]|7[0-7]|8[0-9]|9[0-4|6|7|9])|3[2-9]|5[5|6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])([0-9]{7})$";


                    Date currentDate = new Date(System.currentTimeMillis());
                    Date birth = Date.valueOf(dateOfBirth);
                    if (birth.after(currentDate)){
                        model.addAttribute("errDateAfter","Ngày sinh phải nhỏ hơn ngày hiện tại!!!");
                        valiUpdate(model,session,id);
                        return "index";            }
                    if (!phoneNumber.matches(Regex)){
                        model.addAttribute("errPhoneErrr","Cú pháp số điện thoại không chính xác !!!");
                        valiUpdate(model,session,id);
                        return "index";
                    }
                    NhanVien employee = new NhanVien();


                    ChucVu rl = chucVuService.detail(UUID.fromString(idRole));
                    employee.setIdNhanVien(id);
                    employee.setTenNhanVien(fullName);
                    employee.setNgaySinh(dateOfBirth);
                    employee.setDiaChi(address);
                    employee.setSoDienThoai(phoneNumber);
                    employee.setEmail(email);
                    employee.setNgayTao(nhanVienService.detail(id).getNgayTao());
                    employee.setGioiTinh(gender);
                    employee.setTrangThai(status);
                    employee.setUserName(username);
                    employee.setPassWord(password);
                    employee.setChucVu(rl);
                    nhanVienService.add(employee);
                    return "redirect:/nhanVien/index";
                }
            }
            else {
                model.addAttribute("erCheckRole","Nhân viên không thể sử dụng chức năng này");
                return "login";
            }

        }else{
            return "login";
        }


    }
    public void vali(Model model,HttpSession session,String fullName,String password1,String username1,String dateOfBirth,String email,String address,String phoneNumber){
        model.addAttribute("role", chucVuService.getAll());
        String username = (String) session.getAttribute("userName");
        String password = (String) session.getAttribute("passWord");
        NhanVien checkLogin = nhanVienService.login(username,password);
        session.setAttribute("checkRole",nhanVienService.checkChucVu(username));
        session.setAttribute("Name", checkLogin);
        model.addAttribute("empLogin",checkLogin);
        model.addAttribute("fullnameAdd",fullName);
        model.addAttribute("usernameAdd",username1);
        model.addAttribute("passwordAdd",password1);
        model.addAttribute("dateOfBirthAdd",dateOfBirth);
        model.addAttribute("emailAdd",email);
        model.addAttribute("addressAdd",address);
        model.addAttribute("phoneNumberAdd",phoneNumber);

    }
    @PostMapping("/nhanVien/add")
    public String add(
            @RequestParam("tenNhanVien") String fullName,
            @RequestParam("username1") String username1,
            @RequestParam("password1") String password1,
            @RequestParam("ngaySinh") String dateOfBirth,
            @RequestParam("gioiTinh") int gender,
            @RequestParam("soDienThoai") String phoneNumber,
            @RequestParam("email") String email,
            @RequestParam("diaChi") String address,
            @RequestParam("idChucVu")String idRole,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpSession session
    ) {


        if (fullName.isBlank()){
            vali(model,session,fullName,password1,username1,dateOfBirth,email,address,phoneNumber);
            model.addAttribute("errName","Invalid Receiver FullName");
            model.addAttribute("view", "/NhanVien/add.jsp");
            return "index";

        }
        if (dateOfBirth.isBlank()){
            vali(model,session,fullName,password1,username1,dateOfBirth,email,address,phoneNumber);
            model.addAttribute("errDate","Ngày sinh của người nhận không hợp lệ");
            model.addAttribute("view", "/NhanVien/add.jsp");
            return "index";        }
        String Regex = "^(0|84)(2(0[3-9]|1[0-6|8|9]|2[0-2|5-9]|3[2-9]|4[0-9]|5[1|2|4-9]|6[0-3|9]|7[0-7]|8[0-9]|9[0-4|6|7|9])|3[2-9]|5[5|6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])([0-9]{7})$";

        if (phoneNumber.isBlank()){
            vali(model,session,fullName,password1,username1,dateOfBirth,email,address,phoneNumber);
            model.addAttribute("errPhone","Số điện thoại người nhận không hợp lệ");
            model.addAttribute("view", "/NhanVien/add.jsp");
            return "index";        }
        if (email.isBlank()){
            vali(model,session,fullName,password1,username1,dateOfBirth,email,address,phoneNumber);
            model.addAttribute("errEmail","Invalid Receiver Email");
            model.addAttribute("view", "/NhanVien/add.jsp");
            return "index";        }
        if (address.isBlank()){
            vali(model,session,fullName,password1,username1,dateOfBirth,email,address,phoneNumber);
            model.addAttribute("errAdd","Địa chỉ người nhận không hợp lệ");
            model.addAttribute("view", "/NhanVien/add.jsp");
            return "index";        }
        if (username1.isBlank()){
            vali(model,session,fullName,password1,username1,dateOfBirth,email,address,phoneNumber);
            model.addAttribute("errUser","Tên người dùng nhận không hợp lệ");
            model.addAttribute("view", "/NhanVien/add.jsp");
            return "index";        }
        if (password1.isBlank()){
            vali(model,session,fullName,password1,username1,dateOfBirth,email,address,phoneNumber);
            model.addAttribute("errPass","Mật khẩu người nhận không hợp lệ");
            model.addAttribute("view", "/NhanVien/add.jsp");
            return "index";        }
        else {
            List<NhanVien>  checkUser = nhanVienService.getByUserName(username1);
            if (!(checkUser.isEmpty())){
                vali(model,session,fullName,password1,username1,dateOfBirth,email,address,phoneNumber);
                model.addAttribute("errUserTrung","Tên người dùng trùng lặp !!! Vui lòng nhập tên người dùng khác");
                model.addAttribute("view", "/NhanVien/add.jsp");
                return "index";            }


            NhanVien employee = new NhanVien();
            Date currentDate = new Date(System.currentTimeMillis());
            Date birth = Date.valueOf(dateOfBirth);
            if (birth.after(currentDate)){
                vali(model,session,fullName,password1,username1,dateOfBirth,email,address,phoneNumber);
                model.addAttribute("errDateAfter","Ngày sinh phải nhỏ hơn ngày hiện tại !!!");
                model.addAttribute("view", "/NhanVien/add.jsp");
                return "index";            }
            if (!phoneNumber.matches(Regex)){
                vali(model,session,fullName,password1,username1,dateOfBirth,email,address,phoneNumber);
                model.addAttribute("errPhoneErrr"," Cú pháp số điện thoại không chính xác !!!");
                model.addAttribute("view", "/NhanVien/add.jsp");
                return "index";
            }


            employee.setTenNhanVien(fullName);
            employee.setNgaySinh(String.valueOf(dateOfBirth) );
            employee.setDiaChi(address);
            employee.setSoDienThoai(phoneNumber);
            employee.setNgayTao((currentDate));
            employee.setEmail(email);
            employee.setGioiTinh(gender);
            employee.setTrangThai(1);
            employee.setUserName(username1);
            employee.setPassWord(password1);
            employee.setChucVu(chucVuService.detail(UUID.fromString(idRole)));
            nhanVienService.add(employee);
            return "redirect:/nhanVien/index";
        }
    }


    @GetMapping("/nhanVien/detail/{idNhanVien}")
    public String detail(Model model, @PathVariable("idNhanVien") UUID id,HttpSession session) {
        model.addAttribute("emp", nhanVienService.detail(id));
        model.addAttribute("role", chucVuService.getAll());
        String username = (String) session.getAttribute("userName");
        String password = (String) session.getAttribute("passWord");
        NhanVien checkLogin = nhanVienService.login(username,password);
        session.setAttribute("checkRole",nhanVienService.checkChucVu(username));

        session.setAttribute("Name", checkLogin);
        model.addAttribute("empLogin",checkLogin);
        model.addAttribute("view", "/NhanVien/detail.jsp");
        return "index";


    }

    @GetMapping("/nhanVien/viewAdd")
    public String viewAdd(Model model,HttpSession session) {
        model.addAttribute("role", chucVuService.getAll());
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        NhanVien checkLogin = nhanVienService.login(username,password);
        session.setAttribute("checkRole",nhanVienService.checkChucVu(username));

        session.setAttribute("Name", checkLogin);
        model.addAttribute("empLogin",checkLogin);
        model.addAttribute("view", "/NhanVien/add.jsp");
        return "index";


    }

    @GetMapping("/nhanVien/role")
    public String role(Model model) {
        model.addAttribute("role",chucVuService.getAll());
        return "/NhanVien/role";

    }

    @GetMapping("/nhanVien/timKiem")
    public String timKiem(Model model,
                          @RequestParam("name1")String name,
                          @RequestParam("phone1")String phone,HttpSession session,
                          @RequestParam(value = "pageNo",defaultValue = "0")Integer page) {
        if (name.isBlank()&&phone.isBlank()) {
            model.addAttribute("empList", nhanVienService.listDesc(page));
            String username = (String) session.getAttribute("username");
            String password = (String) session.getAttribute("password");
            NhanVien checkLogin = nhanVienService.login(username, password);
            session.setAttribute("checkRole", nhanVienService.checkChucVu(username));

            session.setAttribute("Name", checkLogin);
            model.addAttribute("empLogin", checkLogin);
            model.addAttribute("view", "/NhanVien/index.jsp");
            return "index";

        }

        else {
            if (name.isBlank()||phone.isBlank()){
                model.addAttribute("tim",nhanVienService.timKiem2(name,phone));
                String username = (String) session.getAttribute("userName");
                String password = (String) session.getAttribute("passWord");
                NhanVien checkLogin = nhanVienService.login(username,password);
                session.setAttribute("checkRole",nhanVienService.checkChucVu(username));

                session.setAttribute("Name", checkLogin);
                model.addAttribute("empLogin",checkLogin);

                model.addAttribute("view", "/NhanVien/index.jsp");
                return "index";
            }
            else {
                model.addAttribute("tim", nhanVienService.timKiem(name, phone));
                String username = (String) session.getAttribute("userName");
                String password = (String) session.getAttribute("passWord");
                NhanVien checkLogin = nhanVienService.login(username, password);
                session.setAttribute("checkRole", nhanVienService.checkChucVu(username));

                session.setAttribute("Name", checkLogin);
                model.addAttribute("empLogin", checkLogin);

                model.addAttribute("view", "/NhanVien/index.jsp");
                return "index";
            }

        }
    }
}
