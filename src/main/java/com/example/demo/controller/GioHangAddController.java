package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.entity.ViewModels.DatHang;
import com.example.demo.entity.ViewModels.ViewGioHangChiTiet;
import com.example.demo.repository.HinhAnhRepository;
import com.example.demo.service.serviceimpl.ChiTietSanPhamServiceImpl;
import com.example.demo.service.serviceimpl.GioHangChiTietServiceImpl;
import com.example.demo.service.serviceimpl.GioHangServiceImpl;
import com.example.demo.service.serviceimpl.KhachHangServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class GioHangAddController {
    @Autowired
    private HttpSession httpSession;
    @Autowired
    ChiTietSanPhamServiceImpl productDetailServiceimpl;
    @Autowired
    HinhAnhRepository productImageResponsitory;
    @Autowired
    KhachHangServiceImpl customerService;
    @Autowired
    GioHangServiceImpl cartService;
    @Autowired
    GioHangChiTietServiceImpl cartDetailService;

    @GetMapping("/gioHang/add/{id}")
    public String themGioHang(@PathVariable("id") UUID id) {
        ChiTietSanPham productDetail = productDetailServiceimpl.getOne(id);

        if (productDetail == null) {
            // Xử lý trường hợp sản phẩm không tồn tại, ví dụ: thông báo lỗi.
            return "redirect:/error";
        }

        if (httpSession.getAttribute("CustomerName") != null) {
            String username = (String) httpSession.getAttribute("CustomerName");
            KhachHang customer = customerService.getKhachHangByName(username);
            var cart = cartService.getOne(customer.getIdKhachHang());
            var check = cartDetailService.checkExistCartDetail(customer.getIdKhachHang(), id);
            if (check) {
                cart.setTongTien(cart.getTongTien().add(productDetail.getGiaBan().multiply(new BigDecimal(productDetail.getSoLuong()))));
                cartService.update(cart.getIdGioHang(), cart);
                var cartDetail = cartDetailService.getOneGioHangChiTiet(customer.getIdKhachHang(), id);
                cartDetail.setSoLuong(cartDetail.getSoLuong() + productDetail.getSoLuong());
                cartDetail.setGia(cartDetail.getGia().add(productDetail.getGiaBan().multiply(new BigDecimal(productDetail.getSoLuong()))));
                cartDetailService.update(cartDetail.getIdGioHangChiTiet(), cartDetail);
            } else {
                cart.setSoLuong(cart.getSoLuong() + 1);
                cart.setTongTien(cart.getTongTien().add(productDetail.getGiaBan().multiply(new BigDecimal(productDetail.getSoLuong()))));
                cartService.update(cart.getIdGioHang(), cart);
                GioHangChiTiet cartDetail = new GioHangChiTiet();
                cartDetail.setIdGioHangChiTiet(UUID.randomUUID());
                cartDetail.setSoLuong(productDetail.getSoLuong());
                cartDetail.setGia(productDetail.getGiaBan().multiply(new BigDecimal(productDetail.getSoLuong())));
                cartDetail.setGioHang(cart);
                cartDetail.setChiTietSanPham(productDetail);
                cartDetailService.save(cartDetail);
            }
        } else {
            HinhAnh productImage = productImageResponsitory.findChiTietSanPhamId(id).get(0);
            GioHang cartSession = (GioHang) httpSession.getAttribute("GioHang");

            if (cartSession == null) {
                GioHang cart = new GioHang();

                UUID customerId = UUID.randomUUID();
                cart.setIdGioHang(customerId);
                cart.setSoLuong(1);
                cart.setTongTien(productDetail.getGiaBan().multiply(new BigDecimal(productDetail.getSoLuong())));
                cart.setTrangThai(0);

                httpSession.setAttribute("GioHang", cart);

                ArrayList<ViewGioHangChiTiet> lstCartDetailView = new ArrayList<ViewGioHangChiTiet>();

                ViewGioHangChiTiet cartDetailView = new ViewGioHangChiTiet();

                cartDetailView.setGioHang(cart);
                cartDetailView.setChiTietSanPham(productDetail);
                cartDetailView.setChiTietSanPhamid(productDetail.getIdChiTietSanPham());
                cartDetailView.setSanPhamId(productDetail.getSanPham().getIdSanPham());
                cartDetailView.setId(UUID.randomUUID());
                cartDetailView.setTen(productDetail.getSanPham().getTenSanPham());
                cartDetailView.setGiaSanPham(productDetail.getGiaBan());
                cartDetailView.setGia(productDetail.getGiaBan().multiply(new BigDecimal(productDetail.getSoLuong())));
                cartDetailView.setSoLuong(productDetail.getSoLuong());
                cartDetailView.setTenMau(productDetail.getMauSac().getTenMauSac());
                cartDetailView.setHinhAnh(productImage.getTenHinhAnh());

                lstCartDetailView.add(cartDetailView);

                httpSession.setAttribute("GioHangChiTiet", lstCartDetailView);
            } else {
                cartSession.setTongTien(cartSession.getTongTien().add(productDetail.getGiaBan().multiply(new BigDecimal(productDetail.getSoLuong()))));

                ArrayList<ViewGioHangChiTiet> cartDetailSession = (ArrayList<ViewGioHangChiTiet>) httpSession.getAttribute("GioHangChiTiet");

                boolean check = false;

                for (ViewGioHangChiTiet cartDetailView : cartDetailSession) {
                    if (cartDetailView.getChiTietSanPhamid().equals(id)) {
                        check = true;
                        break;
                    }
                }

                if (check) {
                    httpSession.setAttribute("GioHang", cartSession);

                    for (ViewGioHangChiTiet cartDetailView : cartDetailSession) {
                        if (cartDetailView.getChiTietSanPhamid().equals(productDetail.getIdChiTietSanPham())) {
                            ViewGioHangChiTiet cartDetailViewOld = cartDetailView;
                            cartDetailSession.remove(cartDetailViewOld);

                            ViewGioHangChiTiet cartDetailViewNew = cartDetailView;
                            cartDetailViewNew.setSoLuong(cartDetailView.getSoLuong() + productDetail.getSoLuong());
                            cartDetailViewNew.setGia(cartDetailView.getGia().add(productDetail.getGiaBan().multiply(new BigDecimal(productDetail.getSoLuong()))));
                            cartDetailViewNew.setGioHang(cartSession);
                            cartDetailSession.add(cartDetailViewNew);
                            break;
                        }
                    }
                } else {
                    cartSession.setSoLuong(cartSession.getSoLuong() + 1);
                    httpSession.setAttribute("GioHang", cartSession);

                    for (ViewGioHangChiTiet cartDetailView : cartDetailSession) {
                        cartDetailView.setGioHang(cartSession);
                    }

                    ViewGioHangChiTiet cartDetailView = new ViewGioHangChiTiet();

                    cartDetailView.setGioHang(cartSession);
                    cartDetailView.setChiTietSanPham(productDetail);
                    cartDetailView.setChiTietSanPhamid(productDetail.getIdChiTietSanPham());
                    cartDetailView.setSanPhamId(productDetail.getSanPham().getIdSanPham());
                    cartDetailView.setId(UUID.randomUUID());
                    cartDetailView.setTen(productDetail.getSanPham().getTenSanPham());
                    cartDetailView.setGiaSanPham(productDetail.getGiaBan());
                    cartDetailView.setGia(productDetail.getGiaBan().multiply(new BigDecimal(productDetail.getSoLuong())));
                    cartDetailView.setSoLuong(productDetail.getSoLuong());
                    cartDetailView.setTenMau(productDetail.getMauSac().getTenMauSac());
                    cartDetailView.setHinhAnh(productImage.getTenHinhAnh());

                    cartDetailSession.add(cartDetailView);
                }
                httpSession.setAttribute("GioHangChiTiet", cartDetailSession);
            }
        }
        productDetail.setSoLuong(1);
        productDetailServiceimpl.update(id, productDetail);
        return "redirect:/viewGioHang";
    }

    @GetMapping("/gioHang/increase/{id}")
    public String IncreaseCart(@PathVariable("id") UUID id) {
        ChiTietSanPham productDetail = productDetailServiceimpl.getOne(id);

        if (productDetail == null) {
            // Xử lý trường hợp sản phẩm không tồn tại, ví dụ: thông báo lỗi.
            return "redirect:/error";
        }

        if (httpSession.getAttribute("CustomerName") != null) {
            String username = (String) httpSession.getAttribute("CustomerName");
            KhachHang customer = customerService.getKhachHangByName(username);
            GioHangChiTiet cartDetail = cartDetailService.getOneGioHangChiTiet(customer.getIdKhachHang(), id);

            var cart = cartService.getOne(customer.getIdKhachHang());

            cart.setTongTien(cart.getTongTien().add(productDetail.getGiaBan()));
            cartService.update(cart.getIdGioHang(), cart);
            cartDetail.setSoLuong(cartDetail.getSoLuong() + 1);
            cartDetail.setGia(cartDetail.getGia().add(productDetail.getGiaBan()));
            cartDetailService.update(cartDetail.getIdGioHangChiTiet(), cartDetail);
        } else {
            GioHang cartSession = (GioHang) httpSession.getAttribute("GioHang");
            cartSession.setTongTien(cartSession.getTongTien().add(productDetail.getGiaBan()));
            httpSession.setAttribute("GioHang", cartSession);

            ArrayList<ViewGioHangChiTiet> cartDetailSession = (ArrayList<ViewGioHangChiTiet>) httpSession.getAttribute("CartDetail");

            if (cartDetailSession == null) {
                return "redirect:/viewGioHang";
            }

            for (ViewGioHangChiTiet cartDetailView : cartDetailSession) {
                if (cartDetailView.getChiTietSanPhamid().equals(productDetail.getIdChiTietSanPham())) {
                    ViewGioHangChiTiet cartDetailViewOld = cartDetailView;
                    cartDetailSession.remove(cartDetailViewOld);

                    ViewGioHangChiTiet cartDetailViewNew = cartDetailView;
                    cartDetailViewNew.setSoLuong(cartDetailView.getSoLuong() + 1);
                    cartDetailViewNew.setGia(cartDetailView.getGia().add(productDetail.getGiaBan()));
                    cartDetailViewNew.setGioHang(cartSession);
                    cartDetailSession.add(cartDetailViewNew);
                    break;
                }
            }
            httpSession.setAttribute("GioHangChiTiet", cartDetailSession);
        }
        return "redirect:/viewGioHang";
    }

    @GetMapping("/gioHang/reduce/{id}")
    public String ReduceCart(@PathVariable("id") UUID id, Model model) {
        ChiTietSanPham productDetail = productDetailServiceimpl.getOne(id);

        if (productDetail == null) {
            // Xử lý trường hợp sản phẩm không tồn tại, ví dụ: thông báo lỗi.
            return "redirect:/error";
        }
        String message = ""; // Biến lưu thông báo

        if (httpSession.getAttribute("CustomerName") != null) {
            String username = (String) httpSession.getAttribute("CustomerName");
            KhachHang customer = customerService.getKhachHangByName(username);
            GioHangChiTiet cartDetail = cartDetailService.getOneGioHangChiTiet(customer.getIdKhachHang(), id);

            var cart = cartService.getOne(customer.getIdKhachHang());

            cart.setTongTien(cart.getTongTien().subtract(productDetail.getGiaBan()));
            if (cartDetail.getSoLuong() > 1) {
                cartDetail.setSoLuong(cartDetail.getSoLuong() - 1);
                cartDetail.setGia(cartDetail.getGia().subtract(productDetail.getGiaBan()));
                cartDetailService.update(cartDetail.getIdGioHangChiTiet(), cartDetail);
            } else {
                cartDetailService.delete(cartDetail.getIdGioHangChiTiet());
                message = "Sản phẩm đã hết trong giỏ hàng.";
            }
            cartService.update(cart.getIdGioHang(), cart);
        } else {
            GioHang cartSession = (GioHang) httpSession.getAttribute("GioHang");
            cartSession.setTongTien(cartSession.getTongTien().subtract(productDetail.getGiaBan()));
            httpSession.setAttribute("GioHang", cartSession);

            ArrayList<ViewGioHangChiTiet> cartDetailSession = (ArrayList<ViewGioHangChiTiet>) httpSession.getAttribute("CartDetail");

            if (cartDetailSession == null) {
                return "redirect:/viewGioHang";
            }
            for (ViewGioHangChiTiet cartDetailView : cartDetailSession) {
                if (cartDetailView.getChiTietSanPhamid().equals(productDetail.getIdChiTietSanPham())) {
                    ViewGioHangChiTiet cartDetailViewOld = cartDetailView;
                    cartDetailSession.remove(cartDetailViewOld);
                    if (cartDetailView.getSoLuong() > 1) {
                        ViewGioHangChiTiet cartDetailViewNew = cartDetailView;
                        cartDetailViewNew.setSoLuong(cartDetailView.getSoLuong() - 1);
                        cartDetailViewNew.setGia(cartDetailView.getGia().subtract(productDetail.getGiaBan()));
                        cartDetailViewNew.setGioHang(cartSession);
                        cartDetailSession.add(cartDetailViewNew);
                        break;
                    } else if (cartDetailView.getSoLuong() <= 1 && cartSession.getSoLuong() > 1) {
                        httpSession.invalidate();
                    } else {
                        cartSession.setSoLuong(cartSession.getSoLuong() - 1);
                        httpSession.setAttribute("GioHang", cartSession);
                        break;
                    }
                }
            }
        }
        model.addAttribute("message", message); // Truyền thông báo đến view
        return "redirect:/viewGioHang";
    }

    @GetMapping("/gioHang/delete/{id}")
    public String deleteCart(@PathVariable("id") UUID id) {
        ChiTietSanPham productDetail = productDetailServiceimpl.getOne(id);

        if (productDetail == null) {
            // Xử lý trường hợp sản phẩm không tồn tại, ví dụ: thông báo lỗi.
            return "redirect:/error";
        }

        if (httpSession.getAttribute("CustomerName") != null) {
            String username = (String) httpSession.getAttribute("CustomerName");
            KhachHang customer = customerService.getKhachHangByName(username);
            GioHangChiTiet cartDetail = cartDetailService.getOneGioHangChiTiet(customer.getIdKhachHang(), id);

            var cart = cartService.getOne(customer.getIdKhachHang());

            cart.setSoLuong(cart.getSoLuong() - 1);
            cart.setTongTien(cart.getTongTien().subtract(cartDetail.getGia()));
            cartService.update(cart.getIdGioHang(), cart);
            cartDetailService.delete(cartDetail.getIdGioHangChiTiet());
        } else {
            GioHang cartSession = (GioHang) httpSession.getAttribute("GioHang");
            ArrayList<ViewGioHangChiTiet> cartDetailSession = (ArrayList<ViewGioHangChiTiet>) httpSession.getAttribute("CartDetail");

            if (cartDetailSession == null) {
                return "redirect:/viewGioHang";
            }

            if (cartSession.getSoLuong() > 1) {
                for (ViewGioHangChiTiet cartDetailView : cartDetailSession) {
                    if (cartDetailView.getChiTietSanPhamid().equals(productDetail.getIdChiTietSanPham())) {
                        ViewGioHangChiTiet cartDetailViewOld = cartDetailView;
                        cartDetailSession.remove(cartDetailViewOld);

                        cartSession.setSoLuong(cartSession.getSoLuong() - 1);
                        cartSession.setTongTien(cartSession.getTongTien().subtract(cartDetailViewOld.getGia()));
                        httpSession.setAttribute("GioHang", cartSession);
                        break;
                    }
                }
            } else {
                httpSession.invalidate();
            }
        }
        return "redirect:/viewGioHang";
    }

    @GetMapping("/gioHang/deleteAll")
    public String deleteAllCart() {
        if (httpSession.getAttribute("CustomerName") != null) {
            List<GioHangChiTiet> cartDetail = cartDetailService.getAll();

            if (cartDetail == null) {
                // Xử lý trường hợp sản phẩm không tồn tại, ví dụ: thông báo lỗi.
                return "redirect:/error";
            }

            String username = (String) httpSession.getAttribute("CustomerName");
            KhachHang customer = customerService.getKhachHangByName(username);

            var cart = cartService.getOne(customer.getIdKhachHang());

            cart.setSoLuong(0);
            cart.setTongTien(BigDecimal.ZERO);
            cartService.update(cart.getIdGioHang(), cart);
            cartDetailService.deleteAll(customer.getIdKhachHang());
        } else {
            httpSession.invalidate();
        }
        return "redirect:/viewGioHang";
    }

    @GetMapping("/viewGioHang")
    public String showCartItem(Model model) {
        if (httpSession.getAttribute("CustomerName") != null) {
            String username = (String) httpSession.getAttribute("CustomerName");
            KhachHang customer = customerService.getKhachHangByName(username);
            model.addAttribute("cart", cartService.getOne(customer.getIdKhachHang()));
            model.addAttribute("listCartDetail", cartDetailService.getGioHangChiTietByCustomerId(customer.getIdKhachHang()));
        } else {
            GioHang cartSession = (GioHang) httpSession.getAttribute("GioHang");
            ArrayList<ViewGioHangChiTiet> cartDetailSession = (ArrayList<ViewGioHangChiTiet>) httpSession.getAttribute("GioHangChiTiet");

            model.addAttribute("cart", cartSession);
            model.addAttribute("listCartDetail", cartDetailSession);
        }
        model.addAttribute("listProductDetail", productDetailServiceimpl.getAll());
        model.addAttribute("view", "/gioHang/index.jsp");
        return "/giaodien/index";
    }

    @GetMapping("/add/{id}")
    public String themGioHang1(@PathVariable("id") UUID id) {
        ChiTietSanPham productDetail = productDetailServiceimpl.getOne(id);
        DatHang cartSession = (DatHang) httpSession.getAttribute("DatHang");
        if (productDetail == null) {
            // Xử lý trường hợp sản phẩm không tồn tại, ví dụ: thông báo lỗi.
            return "redirect:/error";
        }

        UUID productId1 = productDetail.getIdChiTietSanPham();
        String tenSanPham = productDetail.getSanPham().getTenSanPham();
        BigDecimal price = productDetail.getGiaBan();
        ChiTietHoaDon item = new ChiTietHoaDon();
        item.setGia(price);
        item.setChiTietSanPham(productDetailServiceimpl.getOne(productId1));
        item.setSoLuong(1);

        if (cartSession == null) {
            DatHang cart = new DatHang();
            ArrayList<ChiTietHoaDon> list = new ArrayList<>();
            list.add(item);
            cart.setChiTietHoaDons(list);
            httpSession.setAttribute("DatHang", cart);
        } else {
            if (cartSession == null) {
                // Xử lý trường hợp giỏ hàng không tồn tại, ví dụ: thông báo lỗi.
                return "redirect:/error";
            }

            ArrayList<ChiTietHoaDon> listItem = cartSession.getChiTietHoaDons();
            if (listItem == null) {
                // Tạo danh sách mới nếu nó chưa tồn tại.
                listItem = new ArrayList<>();
            }

            for (ChiTietHoaDon itemTmp : listItem) {
                if (itemTmp.getChiTietSanPham().getIdChiTietSanPham().equals(productId1)) {
                    itemTmp.setSoLuong(itemTmp.getSoLuong() + 1);
                    itemTmp.setGia(price.multiply(BigDecimal.valueOf(itemTmp.getSoLuong())));
                    return "redirect:/viewGioHang";
                }
            }
            listItem.add(item);
            cartSession.setChiTietHoaDons(listItem);
        }
        return "redirect:/viewGioHang";
    }



}