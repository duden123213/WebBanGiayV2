-- Tạo Database
IF OBJECT_ID('Test') IS NOT NULL
	DROP DATABASE hi
GO
CREATE DATABASE hi
GO
-- Sử dụng database
USE hi
GO

--Tạo bảng Thương hiệu (Brand)
IF OBJECT_ID('ThuongHieu') IS NOT NULL
DROP TABLE ThuongHieu
    GO
CREATE TABLE ThuongHieu (
                            IdThuongHieu UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                            TenThuongHieu NVARCHAR(50) NOT NULL,
                            HinhAnh NVARCHAR(MAX),
                            MoTa NVARCHAR(200) NOT NULL,
                            TrangThai INT NOT NULL,
);

-- Tạo bảng Sản phẩm (Product)
IF OBJECT_ID('SanPham') IS NOT NULL
DROP TABLE SanPham
    GO
CREATE TABLE SanPham (
                         IdSanPham UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                         TenSanPham NVARCHAR(255) NOT NULL,
                         SoLuong INT NOT NULL,
                         DaBan INT NOT NULL,
                         LuotThich INT NOT NULL,
                         NgayTao DATE NOT NULL,
                         TrangThai INT NOT NULL,
                         MoTa NVARCHAR(MAX),
                         ThuongHieuId UNIQUEIDENTIFIER,
                         FOREIGN KEY (ThuongHieuId) REFERENCES ThuongHieu(IdThuongHieu),
);

--Tạo bảng Danh mục (Category)
IF OBJECT_ID('PhanLoai') IS NOT NULL
DROP TABLE PhanLoai
    GO
CREATE TABLE PhanLoai (
                          IdPhanLoai UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                          TenPhanLoai NVARCHAR(50) NOT NULL,
                          TrangThai INT NOT NULL,
);

--Tạo bảng Chi tiết Danh mục (ChiTietPhanLoai)
IF OBJECT_ID('ChiTietPhanLoai') IS NOT NULL
DROP TABLE ChiTietPhanLoai
    GO
CREATE TABLE ChiTietPhanLoai (
                                 IdChiTietPhanLoai UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                                 SoLuong INT NOT NULL,
                                 SanPhamId UNIQUEIDENTIFIER,
                                 PhanLoaiId UNIQUEIDENTIFIER,
                                 FOREIGN KEY (SanPhamId) REFERENCES SanPham(IdSanPham),
                                 FOREIGN KEY (PhanLoaiId) REFERENCES PhanLoai(IdPhanLoai),
);

--Tạo bảng Màu sắc (Color)
IF OBJECT_ID('MauSac') IS NOT NULL
DROP TABLE MauSac
    GO
CREATE TABLE MauSac (
                        IdMauSac UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                        TenMauSac NVARCHAR(50) NOT NULL,
                        Gia DECIMAL(10, 2) NOT NULL,
                        HinhAnh NVARCHAR(MAX) NOT NULL,
                        TrangThai INT NOT NULL,
                        ThuongHieuId UNIQUEIDENTIFIER,
                        FOREIGN KEY (ThuongHieuId) REFERENCES ThuongHieu(IdThuongHieu),
);
--Tạo bảng KichCo (Color)
IF OBJECT_ID('KichCo') IS NOT NULL
DROP TABLE KichCo
    GO
CREATE TABLE KichCo (
                        IdKichCo UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                        TenKichCo NVARCHAR(50) NOT NULL,
                        MoTa NVARCHAR(200) NOT NULL,
                        TrangThai INT NOT NULL,
);


-- Tạo bảng Chi tiết Sản phẩm (ProductDetail)
IF OBJECT_ID('ChiTietSanPham') IS NOT NULL
DROP TABLE ChiTietSanPham
    GO
CREATE TABLE ChiTietSanPham (
                                IdChiTietSanPham UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                                GiaNhap DECIMAL(10, 2) NOT NULL,
                                GiaBan DECIMAL(10, 2) NOT NULL,
                                SoLuong INT NOT NULL,
                                NgayTao DATE NOT NULL,
                                TrangThai INT NOT NULL,
                                MoTa NVARCHAR(MAX),
                                KichCoId UNIQUEIDENTIFIER,
                                SanPhamId UNIQUEIDENTIFIER,
                                MauSacId UNIQUEIDENTIFIER,
                                FOREIGN KEY (KichCoId) REFERENCES KichCo(IdKichCo),
                                FOREIGN KEY (SanPhamId) REFERENCES SanPham(IdSanPham),
                                FOREIGN KEY (MauSacId) REFERENCES MauSac(IdMauSac),

);

-- Tạo bảng Hình ảnh Sản phẩm (ProductImage)
IF OBJECT_ID('HinhAnh') IS NOT NULL
DROP TABLE HinhAnh
    GO
CREATE TABLE HinhAnh (
                         IdHinhAnh UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                         TenHinhAnh NVARCHAR(MAX) NOT NULL,
                         TrangThai INT NOT NULL,
                         ChiTietSanPhamId UNIQUEIDENTIFIER,
                         FOREIGN KEY (ChiTietSanPhamId) REFERENCES ChiTietSanPham(IdChiTietSanPham),
);

-- Tạo bảng Khách hàng (Customer)
IF OBJECT_ID('KhachHang') IS NOT NULL
DROP TABLE KhachHang
    GO
CREATE TABLE KhachHang (
                           IdKhachHang UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                           TenKhachHang NVARCHAR(100) NOT NULL,
                           UserName NVARCHAR(50) NOT NULL,
                           PassWord NVARCHAR(50) NOT NULL,
                           GioiTinh INT NOT NULL,
                           SoDienThoai NVARCHAR(15) NOT NULL,
                           Email NVARCHAR(100) NOT NULL,
                           NgaySinh DATE NOT NULL,
                           NgayTao DATE NOT NULL,
                           DiaChi NVARCHAR(MAX),
                           TrangThai INT NOT NULL,
);

-- Tạo bảng Danh sách Yêu Thích (WishList)
IF OBJECT_ID('DanhSachYeuThich') IS NOT NULL
DROP TABLE DanhSachYeuThich
    GO
CREATE TABLE DanhSachYeuThich (
                                  IdDanhSachYeuThich UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                                  SanPhamId UNIQUEIDENTIFIER,
                                  KhachHangId UNIQUEIDENTIFIER,
                                  FOREIGN KEY (SanPhamId) REFERENCES SanPham(IdSanPham),
                                  FOREIGN KEY (KhachHangId) REFERENCES KhachHang(IdKhachHang),
);

-- Tạo bảng Giỏ hàng (Cart)
IF OBJECT_ID('GioHang') IS NOT NULL
DROP TABLE GioHang
    GO
CREATE TABLE GioHang (
                         IdGioHang UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                         SoLuong INT NOT NULL,
                         TongTien DECIMAL(10, 2) NOT NULL,
                         TrangThai INT NOT NULL,
                         KhachHangId UNIQUEIDENTIFIER,
                         FOREIGN KEY (KhachHangId) REFERENCES KhachHang(IdKhachHang),
);

-- Tạo bảng Chi tiết Giỏ hàng (CartDetail)
IF OBJECT_ID('GioHangChiTiet') IS NOT NULL
DROP TABLE GioHangChiTiet
    GO
CREATE TABLE GioHangChiTiet (
                                IdGioHangChiTiet UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                                SoLuong INT NOT NULL,
                                Gia DECIMAL(10, 2) NOT NULL,
                                GioHangId UNIQUEIDENTIFIER,
                                ChiTietSanPhamId UNIQUEIDENTIFIER,
                                FOREIGN KEY (GioHangId) REFERENCES GioHang(IdGioHang),
                                FOREIGN KEY (ChiTietSanPhamId) REFERENCES ChiTietSanPham(IdChiTietSanPham),
);

-- Tạo bảng Chức vụ (ChucVu)
IF OBJECT_ID('ChucVu') IS NOT NULL
DROP TABLE ChucVu
    GO
CREATE TABLE ChucVu (
                        IdChucVu UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                        TenChucVu NVARCHAR(30) NOT NULL,
                        TrangThai INT NOT NULL,
);

-- Tạo bảng  (NhanVien)
IF OBJECT_ID('NhanVien') IS NOT NULL
DROP TABLE NhanVien
    GO
CREATE TABLE NhanVien (
                          IdNhanVien UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                          TenNhanVien NVARCHAR(100) NOT NULL,
                          UserName NVARCHAR(50) NOT NULL,
                          PassWord NVARCHAR(50) NOT NULL,
                          GioiTinh INT NOT NULL,
                          SoDienThoai NVARCHAR(15) NOT NULL,
                          Email NVARCHAR(100) NOT NULL,
                          HinhAnh NVARCHAR(MAX),
                          NgaySinh DATE NOT NULL,
                          NgayTao DATE NOT NULL,
                          DiaChi NVARCHAR(MAX),
                          TrangThai INT NOT NULL,
                          ChucVuId UNIQUEIDENTIFIER,
                          FOREIGN KEY (ChucVuId) REFERENCES ChucVu(IdChucVu),
);

-- Tạo bảng Trạng thái Hóa Đơn (BillStatus)
IF OBJECT_ID('TrangThaiHoaDon') IS NOT NULL
DROP TABLE TrangThaiHoaDon
    GO
CREATE TABLE TrangThaiHoaDon (
                                 IdTrangThaiHoaDon UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                                 TenTrangThaiHoaDon NVARCHAR(50) NOT NULL,
                                 TrangThai INT NOT NULL
);

-- Tạo bảng Phương thức thanh toán (Payment)
IF OBJECT_ID('PhuongThucThanhToan') IS NOT NULL
DROP TABLE PhuongThucThanhToan
    GO
CREATE TABLE PhuongThucThanhToan (
                                     IdPhuongThucThanhToan UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                                     TenPhuongThucThanhToan NVARCHAR(50) NOT NULL,
                                     TrangThai INT NOT NULL
);

-- Tạo bảng Hóa Đơn (Bill)
IF OBJECT_ID('HoaDon') IS NOT NULL
DROP TABLE HoaDon
    GO
CREATE TABLE HoaDon (
                        IdHoaDon UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                        TenNguoiNhan NVARCHAR(100) NOT NULL,
                        TongTien DECIMAL(10, 2) NOT NULL,
                        SoDienThoai NVARCHAR(50) NOT NULL,
                        DiaChi NVARCHAR(100) NOT NULL,
                        NgayTao DATE NOT NULL,
                        TrangThaiHoaDonId UNIQUEIDENTIFIER,
                        PhuongThucThanhToanId UNIQUEIDENTIFIER,
                        NhanVienId UNIQUEIDENTIFIER,
                        KhachHangId UNIQUEIDENTIFIER,
                        FOREIGN KEY (TrangThaiHoaDonId) REFERENCES TrangThaiHoaDon(IdTrangThaiHoaDon),
                        FOREIGN KEY (PhuongThucThanhToanId) REFERENCES PhuongThucThanhToan(IdPhuongThucThanhToan),
                        FOREIGN KEY (NhanVienId) REFERENCES NhanVien(IdNhanVien),
                        FOREIGN KEY (KhachHangId) REFERENCES KhachHang(IdKhachHang),
);

-- Tạo bảng Chi tiết Hóa Đơn (BillDetail)
IF OBJECT_ID('ChiTietHoaDon') IS NOT NULL
DROP TABLE ChiTietHoaDon
    GO
CREATE TABLE ChiTietHoaDon (
                               IdChiTietHoaDon UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                               SoLuong INT NOT NULL,
                               Gia DECIMAL(10, 2) NOT NULL,
                               HoaDonId UNIQUEIDENTIFIER,
                               ChiTietSanPhamId UNIQUEIDENTIFIER,
                               FOREIGN KEY (HoaDonId) REFERENCES HoaDon(IdHoaDon),
                               FOREIGN KEY (ChiTietSanPhamId) REFERENCES ChiTietSanPham(IdChiTietSanPham),
);


--Nhập liệu các bảng

INSERT INTO  ThuongHieu(IdThuongHieu,TenThuongHieu,HinhAnh,MoTa,TrangThai) VALUES
('15333be6-0d95-4b7c-b0e9-ee00c5110101', 'NIKE', 'nike.png','Dne tu Ha Noi', 0),
('15333be6-0d95-4b7c-b0e9-ee00c5110102', 'ADIDAS', 'adidas.png','Dne tu Ha Noi', 0),
('15333be6-0d95-4b7c-b0e9-ee00c5110103', 'CONVERS', 'convers.png','Dne tu Ha Noi', 0),
('15333be6-0d95-4b7c-b0e9-ee00c5110104', 'PUMA', 'puma.png','Dne tu Ha Noi', 0),
('15333be6-0d95-4b7c-b0e9-ee00c5110105', 'GUCCI', 'gucci.png','Dne tu Ha Noi', 0)
    GO

--Nhập dữ liệu bảng Sản phẩm (Product)
INSERT INTO SanPham VALUES
    ('67357ae4-342e-4673-b80f-1f1d1f030c0c', 'Giày Nike Black', 100, 0, 1, '09/10/2023', 0, '', '15333be6-0d95-4b7c-b0e9-ee00c5110101'),
    ('67357ae4-342e-4673-b80f-1f1d1f020c0c', 'Giày Adidas Pureboost 23', 99, 1, 1, '09/10/2023', 0, '', '15333be6-0d95-4b7c-b0e9-ee00c5110102'),
    ('67357ae4-342e-4673-b80f-1f1d1f010c0c', 'Giày Converse x Rick Owens', 98, 2, 1, '09/10/2023', 0, '', '15333be6-0d95-4b7c-b0e9-ee00c5110103'),
    ('67357ae4-342e-4673-b80f-1f1d1f000c0c', 'Giày PUMA Clyde', 100, 0, 1, '09/10/2023', 0, '', '15333be6-0d95-4b7c-b0e9-ee00c5110104'),
    ('67357ae4-342e-4673-b80f-1f1d1f070c0c', 'Giày Sneaker Gucci Men', 99, 1, 1, '09/10/2023', 0, '', '15333be6-0d95-4b7c-b0e9-ee00c5110105'),
    ('8958bbdc-bea0-4bb3-96c7-ebc6885f4839', 'Giày Sneraker Nike ', 100, 0, 0, '09/10/2023', 0, '', '15333be6-0d95-4b7c-b0e9-ee00c5110101'),
    ('8958bbdc-bea0-4bb3-96c7-ebc6885f4838', 'Giày Adidas Country', 99, 1, 0, '09/10/2023', 0, '', '15333be6-0d95-4b7c-b0e9-ee00c5110102'),
    ('8958bbdc-bea0-4bb3-96c7-ebc6885f4837', 'Giày Converse Golf Le Fleur', 100, 0, 0, '09/10/2023', 0, '', '15333be6-0d95-4b7c-b0e9-ee00c5110103'),
    ('8958bbdc-bea0-4bb3-96c7-ebc6885f4836', 'Giày PUMA Deviate', 100, 0, 0, '09/10/2023', 0, '', '15333be6-0d95-4b7c-b0e9-ee00c5110104'),
    ('8958bbdc-bea0-4bb3-96c7-ebc6885f4835', 'Giày Sneaker Gucci', 100, 0, 0, '09/10/2023', 0, '', '15333be6-0d95-4b7c-b0e9-ee00c5110105')
    GO

--Nhập dữ liệu bảng Danh mục (Category)
INSERT INTO PhanLoai VALUES
    ('67357ae4-342e-4673-b80f-1f1d1f068c9c', 'Low Top', 0),
    ('67357ae4-342e-4673-b80f-1f1d1f068c0c', 'High Top', 0),
    ('67357ae4-342e-4673-b80f-1f1d1f069c0c', 'High Top', 0),
    ('67357ae4-342e-4673-b80f-1f1d1f067c0c', 'High Top', 0),
    ('67357ae4-342e-4673-b80f-1f1d1f066c0c', 'High Top', 0),
    ('67357ae4-342e-4673-b80f-1f1d1f065c0c', 'High Top', 0),
    ('67357ae4-342e-4673-b80f-1f1d1f064c0c', 'High Top', 0),
    ('67357ae4-342e-4673-b80f-1f1d1f063c0c', 'High Top', 0),
    ('2d31bef4-d3b7-46b4-b963-08463d55d3a8', 'High Top', 0)
    GO

--Nhập dữ liệu bảng Chi tiết Danh mục (CategoryDetail)
INSERT INTO ChiTietPhanLoai VALUES
    ('653ef875-5059-486e-b13d-2cc1b2a78ae1', 4, '67357ae4-342e-4673-b80f-1f1d1f030c0c', '67357ae4-342e-4673-b80f-1f1d1f068c9c'),
    ('653ef875-5059-486e-b13d-2cc1b2a78ae2', 4, '67357ae4-342e-4673-b80f-1f1d1f020c0c', '67357ae4-342e-4673-b80f-1f1d1f068c0c'),
    ('653ef875-5059-486e-b13d-2cc1b2a78ae3', 2, '67357ae4-342e-4673-b80f-1f1d1f010c0c', '67357ae4-342e-4673-b80f-1f1d1f069c0c'),
    ('653ef875-5059-486e-b13d-2cc1b2a78ae4', 1, '67357ae4-342e-4673-b80f-1f1d1f010c0c', '67357ae4-342e-4673-b80f-1f1d1f064c0c'),
    ('653ef875-5059-486e-b13d-2cc1b2a78ae5', 2, '67357ae4-342e-4673-b80f-1f1d1f000c0c', '67357ae4-342e-4673-b80f-1f1d1f067c0c'),
    ('653ef875-5059-486e-b13d-2cc1b2a78ae6', 4, '67357ae4-342e-4673-b80f-1f1d1f070c0c', '67357ae4-342e-4673-b80f-1f1d1f068c0c'),
    ('705abbe1-6072-4d14-abc0-3edc7f9ee4c4', 2, '8958bbdc-bea0-4bb3-96c7-ebc6885f4839', '67357ae4-342e-4673-b80f-1f1d1f069c0c'),
    ('705abbe1-6072-4d14-abc0-3edc7f9ee4c5', 1, '8958bbdc-bea0-4bb3-96c7-ebc6885f4839', '67357ae4-342e-4673-b80f-1f1d1f066c0c'),
    ('705abbe1-6072-4d14-abc0-3edc7f9ee4c6', 4, '8958bbdc-bea0-4bb3-96c7-ebc6885f4838', '67357ae4-342e-4673-b80f-1f1d1f068c0c'),
    ('705abbe1-6072-4d14-abc0-3edc7f9ee4c7', 2, '8958bbdc-bea0-4bb3-96c7-ebc6885f4837', '2d31bef4-d3b7-46b4-b963-08463d55d3a8'),
    ('705abbe1-6072-4d14-abc0-3edc7f9ee4c8', 2, '8958bbdc-bea0-4bb3-96c7-ebc6885f4836', '2d31bef4-d3b7-46b4-b963-08463d55d3a8'),
    ('705abbe1-6072-4d14-abc0-3edc7f9ee4c9', 2, '8958bbdc-bea0-4bb3-96c7-ebc6885f4835', '67357ae4-342e-4673-b80f-1f1d1f067c0c')
    GO

--Nhập dữ liệu bảng Màu sắc (Color)
INSERT INTO MauSac(IdMauSac,TenMauSac,Gia,HinhAnh, TrangThai,ThuongHieuId) VALUES
    ('67357ae4-342e-4673-b80f-1f1d1f062c0c', 'Màu đen N', 2, '1.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110101'),
    ('67357ae4-342e-4673-b80f-1f1d1f061c0c', 'Màu đen A', 2, '2.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110102'),
    ('67357ae4-342e-4673-b80f-1f1d1f060c0c', 'Màu đen C', 2, '3.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110103'),
    ('67357ae4-342e-4673-b80f-1f1d1f050c0c', 'Màu đen P', 2, '4.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110104'),
    ('67357ae4-342e-4673-b80f-1f1d1f040c0c', 'Xám đen G', 2, '5.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110105'),
    ('67357ae4-342e-4673-b80f-1f1d1f000c0c', 'Màu trắng N', 2, '6.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110101'),
    ('67357ae4-342e-4673-b80f-1f1d2f000c0c', 'Màu trắng A', 2, '7.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110102'),
    ('67357ae4-342e-4673-b80f-1f1d3f000c0c', 'Màu trắng C', 2, '8.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110103'),
    ('67357ae4-342e-4673-b80f-1f1d4f000c0c', 'Màu trắng P', 2, '9.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110104'),
    ('ba2a17f3-5b4d-4a35-894e-e3ebbf27c310', 'Màu trắng G', 2, '10.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110105'),
    ('ba2a17f3-5b4d-4a35-894e-e3ebbf27c311', 'Màu hồng N', 2, '11.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110101'),
    ('ba2a17f3-5b4d-4a35-894e-e3ebbf27c312', 'Màu hồng A', 2, '12.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110102'),
    ('ba2a17f3-5b4d-4a35-894e-e3ebbf27c313', 'Màu hồng C', 2, '13.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110103'),
    ('ba2a17f3-5b4d-4a35-894e-e3ebbf27c314', 'Màu hồng P', 2, '14.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110104'),
    ('ba2a17f3-5b4d-4a35-894e-e3ebbf27c315', 'Màu hồng G', 2, '15.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110105'),
    ('ba2a17f3-5b4d-4a35-894e-e3ebbf27c316', 'Màu xám N', 2, '16.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110101'),
    ('7d80bc43-7764-4c0f-9429-f4d3d675d63d', 'Màu xám A', 1, '17.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110102'),
    ('7d80bc43-7764-4c0f-9429-f4d3d675d64d', 'Màu xám C', 1, '18.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110103'),
    ('7d80bc43-7764-4c0f-9429-f4d3d675d65d', 'Màu xám P', 1, '19.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110104'),
    ('7d80bc43-7764-4c0f-9429-f4d3d675d66d', 'Màu xám G', 1, '20.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110105'),
    ('7d80bc43-7764-4c0f-9429-f4d3d675d67d', 'Màu xanh N', 1, '21.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110101'),
    ('7d80bc43-7764-4c0f-9429-f4d3d675d68d', 'Màu xanh A', 1, '22.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110102'),
    ('7d80bc43-7764-4c0f-9429-f4d3d675d69d', 'Màu xanh C', 1, '23.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110103'),
    ('7d80bc43-7764-4c0f-9429-f4d3d675d70d', 'Màu xanh P', 1, '24.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110104'),
    ('7d80bc43-7764-4c0f-9429-f4d3d675d71d', 'Màu xanh G', 1, '25.jpg', 0, '15333be6-0d95-4b7c-b0e9-ee00c5110105')

    GO

--Nhập dữ liệu bảng KichCo (Color)
INSERT INTO KichCo VALUES

    ('7d80ae43-7764-4c0f-9429-f4d3d675d63d', '20','',0),
    ('7d80ae43-7764-4c0f-9429-f4d3d675d64d', '21','',0),
    ('7d80ae43-7764-4c0f-9429-f4d3d675d65d', '22','',0),
    ('7d80ae43-7764-4c0f-9429-f4d3d675d66d', '23','',0),
    ('7d80ae43-7764-4c0f-9429-f4d3d675d67d', '24','',0),
    ('7d80ae43-7764-4c0f-9429-f4d3d675d68d', '25','',0),
    ('7d80ae43-7764-4c0f-9429-f4d3d675d69d', '26','',0),
    ('7d80ae43-7764-4c0f-9429-f4d3d675d70d', '27','',0),
    ('7d80ae43-7764-4c0f-9429-f4d3d675d71d', '28','',0),
    ('7d80ae43-7764-4c0f-9429-f4d3d675d73d', '29','',0),
    ('7d80ae43-7764-4c0f-9429-f4d3d675d74d', '30','',0),
    ('7d80ae43-7764-4c0f-9429-f4d3d675d75d', '31','',0),
    ('7d80ae43-7764-4c0f-9429-f4d3d675d76d', '32','',0),
    ('7d80ae43-7764-4c0f-9429-f4d3d675d77d', '33','',0),
    ('7d80ae43-7764-4c0f-9429-f4d3d675d78d', '34','',0)

    GO

--Nhập dữ liệu bảng Chi tiết Sản phẩm (ProductDetail)
INSERT INTO ChiTietSanPham VALUES
    ('f548c39d-d212-45c3-b191-a2a80f8d9d1b', 250000, 499000, 1, '09/10/2023', 0, '','7d80ae43-7764-4c0f-9429-f4d3d675d63d', '67357ae4-342e-4673-b80f-1f1d1f030c0c', '67357ae4-342e-4673-b80f-1f1d1f062c0c'),
    ('f548c39d-d212-45c3-b191-a2a80f8d9d2b', 250000, 499000, 1, '09/10/2023', 0, '','7d80ae43-7764-4c0f-9429-f4d3d675d63d', '67357ae4-342e-4673-b80f-1f1d1f030c0c', '67357ae4-342e-4673-b80f-1f1d1f000c0c'),
    ('f548c39d-d212-45c3-b191-a2a80f8d9d3b', 250000, 499000, 1, '09/10/2023', 0, '','7d80ae43-7764-4c0f-9429-f4d3d675d63d', '67357ae4-342e-4673-b80f-1f1d1f030c0c', 'ba2a17f3-5b4d-4a35-894e-e3ebbf27c311'),
    ('f548c39d-d212-45c3-b191-a2a80f8d9d4b', 250000, 499000, 1, '09/10/2023', 0, '','7d80ae43-7764-4c0f-9429-f4d3d675d63d', '67357ae4-342e-4673-b80f-1f1d1f030c0c', 'ba2a17f3-5b4d-4a35-894e-e3ebbf27c316'),
    ('f548c39d-d212-45c3-b191-a2a80f8d9d5b', 250000, 499000, 1, '09/10/2023', 0, '','7d80ae43-7764-4c0f-9429-f4d3d675d63d', '67357ae4-342e-4673-b80f-1f1d1f020c0c', '67357ae4-342e-4673-b80f-1f1d1f061c0c'),
    ('f548c39d-d212-45c3-b191-a2a80f8d9d6b', 250000, 499000, 1, '09/10/2023', 0, '','7d80ae43-7764-4c0f-9429-f4d3d675d63d', '67357ae4-342e-4673-b80f-1f1d1f020c0c', '67357ae4-342e-4673-b80f-1f1d2f000c0c'),
    ('f548c39d-d212-45c3-b191-a2a80f8d9d7b', 250000, 499000, 1, '09/10/2023', 0, '','7d80ae43-7764-4c0f-9429-f4d3d675d63d', '67357ae4-342e-4673-b80f-1f1d1f020c0c', 'ba2a17f3-5b4d-4a35-894e-e3ebbf27c312'),
    ('f548c39d-d212-45c3-b191-a2a80f8d9d8b', 250000, 1500000, 1, '09/10/2023', 0, '','7d80ae43-7764-4c0f-9429-f4d3d675d63d', '67357ae4-342e-4673-b80f-1f1d1f020c0c', '7d80bc43-7764-4c0f-9429-f4d3d675d63d'),
    ('f548c39d-d212-45c3-b191-a2a80f8d9d9b', 250000, 1500000, 1, '09/10/2023', 0, '','7d80ae43-7764-4c0f-9429-f4d3d675d63d', '67357ae4-342e-4673-b80f-1f1d1f010c0c', '67357ae4-342e-4673-b80f-1f1d1f060c0c'),
    ('5baaeb15-d505-4927-b7fd-eea39f61dc80', 250000, 1500000, 1, '09/10/2023', 0, '','7d80ae43-7764-4c0f-9429-f4d3d675d63d', '67357ae4-342e-4673-b80f-1f1d1f010c0c', '67357ae4-342e-4673-b80f-1f1d3f000c0c'),
    ('5baaeb15-d505-4927-b7fd-eea39f61dc81', 250000, 1500000, 1, '09/10/2023', 0, '','7d80ae43-7764-4c0f-9429-f4d3d675d63d', '67357ae4-342e-4673-b80f-1f1d1f010c0c', 'ba2a17f3-5b4d-4a35-894e-e3ebbf27c313'),
    ('5baaeb15-d505-4927-b7fd-eea39f61dc82', 250000, 1500000, 1, '09/10/2023', 0, '','7d80ae43-7764-4c0f-9429-f4d3d675d63d', '67357ae4-342e-4673-b80f-1f1d1f010c0c', '7d80bc43-7764-4c0f-9429-f4d3d675d64d'),
    ('5baaeb15-d505-4927-b7fd-eea39f61dc83', 250000, 1500000, 1, '09/10/2023', 0, '', '7d80ae43-7764-4c0f-9429-f4d3d675d63d','67357ae4-342e-4673-b80f-1f1d1f000c0c', '67357ae4-342e-4673-b80f-1f1d1f050c0c'),
    ('5baaeb15-d505-4927-b7fd-eea39f61dc84', 250000, 1500000, 1, '09/10/2023', 0, '','7d80ae43-7764-4c0f-9429-f4d3d675d63d', '67357ae4-342e-4673-b80f-1f1d1f000c0c', '67357ae4-342e-4673-b80f-1f1d4f000c0c'),
    ('5baaeb15-d505-4927-b7fd-eea39f61dc85', 250000, 2100000, 1, '09/10/2023', 0, '', '7d80ae43-7764-4c0f-9429-f4d3d675d63d','67357ae4-342e-4673-b80f-1f1d1f000c0c', 'ba2a17f3-5b4d-4a35-894e-e3ebbf27c314'),
    ('5baaeb15-d505-4927-b7fd-eea39f61dc86', 250000, 2100000, 1, '09/10/2023', 0, '', '7d80ae43-7764-4c0f-9429-f4d3d675d63d','67357ae4-342e-4673-b80f-1f1d1f000c0c', '7d80bc43-7764-4c0f-9429-f4d3d675d65d'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e62', 250000, 2100000, 1, '09/10/2023', 0, '', '7d80ae43-7764-4c0f-9429-f4d3d675d63d','67357ae4-342e-4673-b80f-1f1d1f070c0c', 'ba2a17f3-5b4d-4a35-894e-e3ebbf27c310'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e63', 250000, 2100000, 1, '09/10/2023', 0, '', '7d80ae43-7764-4c0f-9429-f4d3d675d63d','67357ae4-342e-4673-b80f-1f1d1f070c0c', 'ba2a17f3-5b4d-4a35-894e-e3ebbf27c315'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e64', 250000, 2100000, 1, '09/10/2023', 0, '', '7d80ae43-7764-4c0f-9429-f4d3d675d63d','67357ae4-342e-4673-b80f-1f1d1f070c0c', '7d80bc43-7764-4c0f-9429-f4d3d675d66d'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e65', 250000, 2100000, 1, '09/10/2023', 0, '','7d80ae43-7764-4c0f-9429-f4d3d675d63d', '67357ae4-342e-4673-b80f-1f1d1f070c0c', '67357ae4-342e-4673-b80f-1f1d1f062c0c'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e66', 250000, 2100000, 1, '09/10/2023', 0, '', '7d80ae43-7764-4c0f-9429-f4d3d675d63d','8958bbdc-bea0-4bb3-96c7-ebc6885f4839', '67357ae4-342e-4673-b80f-1f1d1f000c0c'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e67', 250000, 2100000, 1, '09/10/2023', 0, '', '7d80ae43-7764-4c0f-9429-f4d3d675d63d','8958bbdc-bea0-4bb3-96c7-ebc6885f4839', 'ba2a17f3-5b4d-4a35-894e-e3ebbf27c311'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e68', 250000, 2100000, 1, '09/10/2023', 0, '', '7d80ae43-7764-4c0f-9429-f4d3d675d63d','8958bbdc-bea0-4bb3-96c7-ebc6885f4839', 'ba2a17f3-5b4d-4a35-894e-e3ebbf27c316'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e69', 250000, 2100000, 1, '09/10/2023', 0, '', '7d80ae43-7764-4c0f-9429-f4d3d675d63d','8958bbdc-bea0-4bb3-96c7-ebc6885f4839', '67357ae4-342e-4673-b80f-1f1d1f061c0c'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e70', 250000, 2100000, 1, '09/10/2023', 0, '','7d80ae43-7764-4c0f-9429-f4d3d675d63d', '8958bbdc-bea0-4bb3-96c7-ebc6885f4838', '67357ae4-342e-4673-b80f-1f1d2f000c0c'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e71', 250000, 2100000, 1, '09/10/2023', 0, '','7d80ae43-7764-4c0f-9429-f4d3d675d63d', '8958bbdc-bea0-4bb3-96c7-ebc6885f4838', 'ba2a17f3-5b4d-4a35-894e-e3ebbf27c312'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e72', 250000, 3100000, 1, '09/10/2023', 0, '', '7d80ae43-7764-4c0f-9429-f4d3d675d63d','8958bbdc-bea0-4bb3-96c7-ebc6885f4838', '7d80bc43-7764-4c0f-9429-f4d3d675d63d'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e73', 250000, 3100000, 1, '09/10/2023', 0, '', '7d80ae43-7764-4c0f-9429-f4d3d675d63d','8958bbdc-bea0-4bb3-96c7-ebc6885f4838', '67357ae4-342e-4673-b80f-1f1d1f060c0c'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e74', 250000, 3100000, 1, '09/10/2023', 0, '', '7d80ae43-7764-4c0f-9429-f4d3d675d63d','8958bbdc-bea0-4bb3-96c7-ebc6885f4837', '67357ae4-342e-4673-b80f-1f1d3f000c0c'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e75', 250000, 3100000, 1, '09/10/2023', 0, '', '7d80ae43-7764-4c0f-9429-f4d3d675d63d','8958bbdc-bea0-4bb3-96c7-ebc6885f4837', 'ba2a17f3-5b4d-4a35-894e-e3ebbf27c313'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e76', 250000, 3100000, 1, '09/10/2023', 0, '', '7d80ae43-7764-4c0f-9429-f4d3d675d63d','8958bbdc-bea0-4bb3-96c7-ebc6885f4837', '7d80bc43-7764-4c0f-9429-f4d3d675d64d'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e77', 250000, 3100000, 1, '09/10/2023', 0, '', '7d80ae43-7764-4c0f-9429-f4d3d675d63d','8958bbdc-bea0-4bb3-96c7-ebc6885f4837', '67357ae4-342e-4673-b80f-1f1d1f050c0c'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e78', 250000, 3100000, 1, '09/10/2023', 0, '', '7d80ae43-7764-4c0f-9429-f4d3d675d63d','8958bbdc-bea0-4bb3-96c7-ebc6885f4836', '67357ae4-342e-4673-b80f-1f1d1f050c0c'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e79', 250000, 3100000, 1, '09/10/2023', 0, '', '7d80ae43-7764-4c0f-9429-f4d3d675d63d','8958bbdc-bea0-4bb3-96c7-ebc6885f4836', 'ba2a17f3-5b4d-4a35-894e-e3ebbf27c314'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e80', 250000, 3100000, 1, '09/10/2023', 0, '', '7d80ae43-7764-4c0f-9429-f4d3d675d63d','8958bbdc-bea0-4bb3-96c7-ebc6885f4836', '7d80bc43-7764-4c0f-9429-f4d3d675d65d'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e81', 250000, 3100000, 1, '09/10/2023', 0, '', '7d80ae43-7764-4c0f-9429-f4d3d675d63d','8958bbdc-bea0-4bb3-96c7-ebc6885f4836', '67357ae4-342e-4673-b80f-1f1d1f040c0c'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e82', 250000, 3100000, 1, '09/10/2023', 0, '', '7d80ae43-7764-4c0f-9429-f4d3d675d63d','8958bbdc-bea0-4bb3-96c7-ebc6885f4835', 'ba2a17f3-5b4d-4a35-894e-e3ebbf27c310'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e83', 250000, 3100000, 1, '09/10/2023', 0, '', '7d80ae43-7764-4c0f-9429-f4d3d675d63d','8958bbdc-bea0-4bb3-96c7-ebc6885f4835', 'ba2a17f3-5b4d-4a35-894e-e3ebbf27c315'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e84', 250000, 3100000, 1, '09/10/2023', 0, '', '7d80ae43-7764-4c0f-9429-f4d3d675d63d','8958bbdc-bea0-4bb3-96c7-ebc6885f4835', '7d80bc43-7764-4c0f-9429-f4d3d675d71d'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e85', 250000, 3100000, 1, '09/10/2023', 0, '', '7d80ae43-7764-4c0f-9429-f4d3d675d63d','8958bbdc-bea0-4bb3-96c7-ebc6885f4835', '7d80bc43-7764-4c0f-9429-f4d3d675d71d'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e86', 250000, 3100000, 1, '09/10/2023', 0, '','7d80ae43-7764-4c0f-9429-f4d3d675d63d', '8958bbdc-bea0-4bb3-96c7-ebc6885f4835', '7d80bc43-7764-4c0f-9429-f4d3d675d71d'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e87', 250000, 3100000, 1, '09/10/2023', 0, '','7d80ae43-7764-4c0f-9429-f4d3d675d63d', '8958bbdc-bea0-4bb3-96c7-ebc6885f4835', '7d80bc43-7764-4c0f-9429-f4d3d675d71d'),
    ('79cebd85-1aea-4938-9f9a-9b15e9969e88', 250000, 3100000, 1, '09/10/2023', 0, '','7d80ae43-7764-4c0f-9429-f4d3d675d63d', '8958bbdc-bea0-4bb3-96c7-ebc6885f4835', '7d80bc43-7764-4c0f-9429-f4d3d675d71d')
    GO

--Nhập dữ liệu bảng Hình ảnh Sản phẩm (ProductImage)
INSERT INTO HinhAnh VALUES
    ('c823ef0b-8047-4713-b76a-66340c095221', '1.jgp', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d1b'),
    ('c823ef0b-8047-4713-b76a-66340c095222', '2.jgp', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d1b'),
    ('c823ef0b-8047-4713-b76a-66340c095223', '3.jgp', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d1b'),
    ('c823ef0b-8047-4713-b76a-66340c095224', '4.jgp', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d2b'),
    ('c823ef0b-8047-4713-b76a-66340c095225', '5.jgp', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d2b'),
    ('c823ef0b-8047-4713-b76a-66340c095226', '6.jgp', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d2b'),
    ('c823ef0b-8047-4713-b76a-66340c095227', '7.jpg', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d3b'),
    ('c823ef0b-8047-4713-b76a-66340c095228', '8.jpg', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d3b'),
    ('c823ef0b-8047-4713-b76a-66340c095229', '9.jpg', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d3b'),
    ('c823ef0b-8047-4713-b76a-66340c095240', '10.jpg', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d4b'),
    ('c823ef0b-8047-4713-b76a-66340c095250', '11.jpg', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d5b'),
    ('c823ef0b-8047-4713-b76a-66340c095260', '12.jpg', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d8b'),
    ('c823ef0b-8047-4713-b76a-66340c095270', '13.jpg', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d8b'),
    ('c823ef0b-8047-4713-b76a-66340c095280', '14.jpg', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d8b'),
    ('c823ef0b-8047-4713-b76a-66340c095200', '15.jpg', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d9b'),
    ('c823ef0b-8047-4713-b76a-66340c096000', '16.jpg', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d9b'),
    ('c823ef0b-8047-4713-b76a-66340c097000', '17.jpg', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d9b'),
    ('c823ef0b-8047-4713-b76a-66340c095900', '18.jpg', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d6b'),
    ('c823ef0b-8047-4713-b76a-66340c091000', '19.jpg', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d6b'),
    ('c823ef0b-8047-4713-b76a-66340c092000', '20.jpg', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d6b'),
    ('c823ef0b-8047-4713-b76a-66340c093000', '21.jpg', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d7b'),
    ('c823ef0b-8047-4713-b76a-66340c094000', '22.jpg', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d7b'),
    ('c823ef0b-8047-4713-b76a-66340c095000', '23.jpg', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d7b'),
    ('03e07e56-4b08-46a7-a2f7-7dc0d4e437cf', '24.jpg', 0, '5baaeb15-d505-4927-b7fd-eea39f61dc80'),
    ('03e07e56-4b08-46a7-a2f7-7dc0d4e438cf', '25.jpg', 0, '5baaeb15-d505-4927-b7fd-eea39f61dc80'),
    ('03e07e56-4b08-46a7-a2f7-7dc0d4e439cf', '26.jpg', 0, '5baaeb15-d505-4927-b7fd-eea39f61dc80'),
    ('03e07e56-4b08-46a7-a2f7-7dc0d4e440cf', '27.jpg', 0, '5baaeb15-d505-4927-b7fd-eea39f61dc81'),
    ('03e07e56-4b08-46a7-a2f7-7dc0d4e441cf', '28.jpg', 0, '5baaeb15-d505-4927-b7fd-eea39f61dc81'),
    ('03e07e56-4b08-46a7-a2f7-7dc0d4e442cf', '29.jpg', 0, '5baaeb15-d505-4927-b7fd-eea39f61dc81'),
    ('03e07e56-4b08-46a7-a2f7-7dc0d4e443cf', '30.jpg', 0, '5baaeb15-d505-4927-b7fd-eea39f61dc82'),
    ('03e07e56-4b08-46a7-a2f7-7dc0d4e444cf', '31.jpg', 0, '5baaeb15-d505-4927-b7fd-eea39f61dc82'),
    ('03e07e56-4b08-46a7-a2f7-7dc0d4e445cf', '32.jpg', 0, '5baaeb15-d505-4927-b7fd-eea39f61dc82'),
    ('03e07e56-4b08-46a7-a2f7-7dc0d4e446cf', '33.jpg', 0, '5baaeb15-d505-4927-b7fd-eea39f61dc83'),
    ('03e07e56-4b08-46a7-a2f7-7dc0d4e447cf', '34.jpg', 0, '5baaeb15-d505-4927-b7fd-eea39f61dc83'),
    ('03e07e56-4b08-46a7-a2f7-7dc0d4e448cf', '35.jpg', 0, '5baaeb15-d505-4927-b7fd-eea39f61dc83'),
    ('03e07e56-4b08-46a7-a2f7-7dc0d4e449cf', '36.jpg', 0, '5baaeb15-d505-4927-b7fd-eea39f61dc84'),
    ('03e07e56-4b08-46a7-a2f7-7dc0d4e450cf', '37.jpg', 0, '5baaeb15-d505-4927-b7fd-eea39f61dc84'),
    ('03e07e56-4b08-46a7-a2f7-7dc0d4e451cf', '38.jpg', 0, '5baaeb15-d505-4927-b7fd-eea39f61dc84'),
    ('03e07e56-4b08-46a7-a2f7-7dc0d4e452cf', '39.jpg', 0, '5baaeb15-d505-4927-b7fd-eea39f61dc85'),
    ('03e07e56-4b08-46a7-a2f7-7dc0d4e453cf', '40.jpg', 0, '5baaeb15-d505-4927-b7fd-eea39f61dc85'),
    ('03e07e56-4b08-46a7-a2f7-7dc0d4e454cf', '41.jpg', 0, '5baaeb15-d505-4927-b7fd-eea39f61dc85'),
    ('03e07e56-4b08-46a7-a2f7-7dc0d4e455cf', '42.jpg', 0, '5baaeb15-d505-4927-b7fd-eea39f61dc86'),
    ('03e07e56-4b08-46a7-a2f7-7dc0d4e456cf', '43.jpg', 0, '5baaeb15-d505-4927-b7fd-eea39f61dc86'),
    ('03e07e56-4b08-46a7-a2f7-7dc0d4e457cf', '44.jpg', 0, '5baaeb15-d505-4927-b7fd-eea39f61dc86'),
    ('c823ef0b-8047-4713-b76a-66340c095500', '45.jpg', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d4b'),
    ('c823ef0b-8047-4713-b76a-66340c095600', '46.jpg', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d4b'),
    ('c823ef0b-8047-4713-b76a-66340c095700', '47.jpg', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d5b'),
    ('c823ef0b-8047-4713-b76a-66340c095800', '1.jpg', 0, 'f548c39d-d212-45c3-b191-a2a80f8d9d5b'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8531f', '2.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e62'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8532f', '3.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e62'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8533f', '4.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e62'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8534f', '5.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e63'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8535f', '6.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e63'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8536f', '7.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e63'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8537f', '8.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e64'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8538f', '9.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e64'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8539f', '10.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e64'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8540f', '11.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e65'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8541f', '12.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e65'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8542f', '13.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e65'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8543f', '14.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e66'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8544f', '15.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e66'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8545f', '16.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e66'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8546f', '17.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e67'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8547f', '18.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e67'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8548f', '19.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e67'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8549f', '20.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e68'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8550f', '21.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e68'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8551f', '22.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e68'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8552f', '23.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e69'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8553f', '24.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e69'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8554f', '25.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e69'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8555f', '26.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e70'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8556f', '27.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e70'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8557f', '28.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e70'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8558f', '29.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e71'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8559f', '30.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e71'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8560f', '31.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e71'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8561f', '32.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e72'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8562f', '33.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e72'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8563f', '34.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e73'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8564f', '35.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e73'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8565f', '36.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e73'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8566f', '37.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e74'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8567f', '38.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e74'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8568f', '39.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e74'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8569f', '40.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e75'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8570f', '41.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e75'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8571f', '42.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e75'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8572f', '43.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e76'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8573f', '44.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e76'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8574f', '45.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e76'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8575f', '46.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e77'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8576f', '47.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e77'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8577f', '1.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e77'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8578f', '1.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e78'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8579f', '3.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e78'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8580f', '4.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e79'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8581f', '5.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e79'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8582f', '6.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e79'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8583f', '7.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e80'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8584f', '8.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e80'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8585f', '9.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e80'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8586f', '10.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e81'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8587f', '11.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e81'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8588f', '12.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e81'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8589f', '13.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e82'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8590f', '14.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e82'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8591f', '15.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e82'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8592f', '16.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e83'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8593f', '17.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e83'),
    ('d1ce59c7-b512-4081-93cb-92e2ead8594f', '18.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e83'),
    ('d1ce59c7-b512-4081-93cb-92e2ead1100f', '19.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e84'),
    ('d1ce59c7-b512-4081-93cb-92e2ead1200f', '20.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e84'),
    ('d1ce59c7-b512-4081-93cb-92e2ead1300f', '21.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e84'),
    ('d1ce59c7-b512-4081-93cb-92e2ead1400f', '22.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e85'),
    ('d1ce59c7-b512-4081-93cb-92e2ead1500f', '23.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e85'),
    ('d1ce59c7-b512-4081-93cb-92e2ead1600f', '24.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e85'),
    ('d1ce59c7-b512-4081-93cb-92e2ead1700f', '25.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e86'),
    ('d1ce59c7-b512-4081-93cb-92e2ead1800f', '26.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e86'),
    ('d1ce59c7-b512-4081-93cb-92e2ead1900f', '27.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e86'),
    ('d1ce59c7-b512-4081-93cb-92e2ead2000f', '28.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e87'),
    ('d1ce59c7-b512-4081-93cb-92e2ead2100f', '29.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e87'),
    ('d1ce59c7-b512-4081-93cb-92e2ead2200f', '30.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e87'),
    ('d1ce59c7-b512-4081-93cb-92e2ead2300f', '31.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e88'),
    ('d1ce59c7-b512-4081-93cb-92e2ead2400f', '31.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e88'),
    ('d1ce59c7-b512-4081-93cb-92e2ead2500f', '32.jpg', 0, '79cebd85-1aea-4938-9f9a-9b15e9969e88')
    GO

--Nhập dữ liệu bảng Người dùng (Customer)
INSERT INTO KhachHang VALUES
    ('67357ae4-342e-4673-b80f-1f1d1f068c5c', 'Nguyen Duc Du', 'dund', '123456', 0, '0987654323', 'dund@gmail.com', '01/01/2003', '09/10/2023', 'Hanoi', 0),
    ('67357ae4-342e-4673-b80f-1f1d1f068c6c', 'Nguyen Du Duc', 'ducnd', '123456', 0, '0987654324', 'ducnd@gmail.com', '01/01/2003', '09/10/2023', 'Hanoi', 0)
    GO

--Nhập dữ liệu bảng Danh sách Yêu Thích (WishList)
INSERT INTO DanhSachYeuThich VALUES
    ('6d95a8ae-1e83-4c7d-a813-a5a3640bc711', '67357ae4-342e-4673-b80f-1f1d1f030c0c', '67357ae4-342e-4673-b80f-1f1d1f068c5c'),
    ('6d95a8ae-1e83-4c7d-a813-a5a3640bc712', '67357ae4-342e-4673-b80f-1f1d1f070c0c', '67357ae4-342e-4673-b80f-1f1d1f068c5c'),
    ('6d95a8ae-1e83-4c7d-a813-a5a3640bc713', '67357ae4-342e-4673-b80f-1f1d1f020c0c', '67357ae4-342e-4673-b80f-1f1d1f068c6c'),
    ('6d95a8ae-1e83-4c7d-a813-a5a3640bc714', '67357ae4-342e-4673-b80f-1f1d1f010c0c', '67357ae4-342e-4673-b80f-1f1d1f068c6c'),
    ('6d95a8ae-1e83-4c7d-a813-a5a3640bc715', '67357ae4-342e-4673-b80f-1f1d1f000c0c', '67357ae4-342e-4673-b80f-1f1d1f068c6c')
    GO

--Nhập dữ liệu bảng Giỏ hàng (Cart)
INSERT INTO GioHang VALUES
    ('67357ae4-342e-4673-b80f-1f1d1f068c5c', 3, 71, 0, '67357ae4-342e-4673-b80f-1f1d1f068c5c'),
    ('67357ae4-342e-4673-b80f-1f1d1f068c6c', 1, 23, 0, '67357ae4-342e-4673-b80f-1f1d1f068c6c')
    GO

--Nhập dữ liệu bảng Chi Tiết Giỏ hàng (CartDetail)
INSERT INTO GioHangChiTiet VALUES
    ('6109da61-a526-4776-a82c-3dec24717b1a', 1, 18, '67357ae4-342e-4673-b80f-1f1d1f068c5c', 'f548c39d-d212-45c3-b191-a2a80f8d9d4b'),
    ('6109da61-a526-4776-a82c-3dec24717b2a', 1, 20, '67357ae4-342e-4673-b80f-1f1d1f068c5c', 'f548c39d-d212-45c3-b191-a2a80f8d9d6b'),
    ('6109da61-a526-4776-a82c-3dec24717b3a', 1, 33, '67357ae4-342e-4673-b80f-1f1d1f068c5c', 'f548c39d-d212-45c3-b191-a2a80f8d9d9b'),
    ('6109da61-a526-4776-a82c-3dec24717b4a', 1, 23, '67357ae4-342e-4673-b80f-1f1d1f068c6c', 'f548c39d-d212-45c3-b191-a2a80f8d9d1b')
    GO

--Nhập dữ liệu bảng Chức vụ (Role)
INSERT INTO ChucVu VALUES
    ('67357ae4-342e-4673-b80f-1f1d1f068c1c', 'Staff', 0),
    ('67357ae4-342e-4673-b80f-1f1d1f068c2c', 'Manage', 0)
    GO

--Nhập dữ liệu bảng  (Employee)
INSERT INTO NhanVien VALUES
    ('9c7cf1ed-8f0a-44c3-8dc1-a652a37c0279', 'Truong Thi Thu Huong', 'huongttt', '123456', 0, '0987654321', 'huongttt@gmail.com', 'avatar1.jpg', '01/01/2003', '09/10/2023', 'Hanoi', 0, '67357ae4-342e-4673-b80f-1f1d1f068c1c'),
    ('9c7cf1ed-8f0a-44c3-8dc1-a652a37c0278', 'Truong Thu Huong', 'huongtt', '123456', 0, '0987654322', 'huongtt@gmail.com', 'avatar2.jpg', '01/01/2003', '09/10/2023', 'Hanoi', 0, '67357ae4-342e-4673-b80f-1f1d1f068c2c')
    GO

--Nhập dữ liệu bảng Trạng thái hóa đơn (BillStatus)
INSERT INTO TrangThaiHoaDon VALUES
    ('159b8bc3-5489-47c0-a115-b94a0cf6286f', 'Confirmed', 0),
    ('259b8bc3-5489-47c0-a115-b94a0cf6286f', 'Unconfirmed', 0)
    GO

--Nhập dữ liệu bảng Phương thức thanh toán (Payment)
INSERT INTO PhuongThucThanhToan VALUES
    ('1f7fbcf3-3007-4180-a5fe-84d2bcdf171b', 'Bank transfer', 0),
    ('2f7fbcf3-3007-4180-a5fe-84d2bcdf171b', 'Ship (COD)', 0)
    GO

--Nhập dữ liệu bảng Hóa đơn (Bill)
INSERT INTO HoaDon VALUES
    ('126195bb-2b4f-4a91-ab4f-0acf00306616', 'Nguyen Duc Du', 45, '0987654323', 'Hanoi', '10/09/2023', '159b8bc3-5489-47c0-a115-b94a0cf6286f', '2f7fbcf3-3007-4180-a5fe-84d2bcdf171b', '9c7cf1ed-8f0a-44c3-8dc1-a652a37c0279', '67357ae4-342e-4673-b80f-1f1d1f068c5c'),
    ('226195bb-2b4f-4a91-ab4f-0acf00306616', 'Nguyen Duc Du', 50, '0987654323', 'Hanoi', '10/09/2023', '159b8bc3-5489-47c0-a115-b94a0cf6286f', '2f7fbcf3-3007-4180-a5fe-84d2bcdf171b', '9c7cf1ed-8f0a-44c3-8dc1-a652a37c0278', '67357ae4-342e-4673-b80f-1f1d1f068c6c'),
    ('326195bb-2b4f-4a91-ab4f-0acf00306616', 'Nguyen Duc Du', 21, '0987654323', 'Hanoi', '10/09/2023', '159b8bc3-5489-47c0-a115-b94a0cf6286f', '2f7fbcf3-3007-4180-a5fe-84d2bcdf171b', '9c7cf1ed-8f0a-44c3-8dc1-a652a37c0279', '67357ae4-342e-4673-b80f-1f1d1f068c5c'),
    ('426195bb-2b4f-4a91-ab4f-0acf00306616', 'Nguyen Duc Du', 33, '0987654323', 'Hanoi', '10/09/2023', '159b8bc3-5489-47c0-a115-b94a0cf6286f', '2f7fbcf3-3007-4180-a5fe-84d2bcdf171b', '9c7cf1ed-8f0a-44c3-8dc1-a652a37c0278', '67357ae4-342e-4673-b80f-1f1d1f068c6c'),
    ('526195bb-2b4f-4a91-ab4f-0acf00306616', 'Nguyen Duc Du', 52, '0987654323', 'Hanoi', '10/09/2023', '159b8bc3-5489-47c0-a115-b94a0cf6286f', '2f7fbcf3-3007-4180-a5fe-84d2bcdf171b', '9c7cf1ed-8f0a-44c3-8dc1-a652a37c0278', '67357ae4-342e-4673-b80f-1f1d1f068c6c')
    GO

--Nhập dữ liệu bảng Chi tiết Hóa đơn (BillDetail)
INSERT INTO ChiTietHoaDon VALUES
    ('ada72d63-1447-4d82-bb84-d6b0103c7230', 1, 20, '126195bb-2b4f-4a91-ab4f-0acf00306616', 'f548c39d-d212-45c3-b191-a2a80f8d9d6b'),
    ('ada72d63-1447-4d82-bb84-d6b0103c7231', 1, 25, '126195bb-2b4f-4a91-ab4f-0acf00306616', '79cebd85-1aea-4938-9f9a-9b15e9969e68'),
    ('ada72d63-1447-4d82-bb84-d6b0103c7232', 1, 20, '226195bb-2b4f-4a91-ab4f-0acf00306616', 'f548c39d-d212-45c3-b191-a2a80f8d9d7b'),
    ('ada72d63-1447-4d82-bb84-d6b0103c7233', 1, 18, '226195bb-2b4f-4a91-ab4f-0acf00306616', 'f548c39d-d212-45c3-b191-a2a80f8d9d5b'),
    ('ada72d63-1447-4d82-bb84-d6b0103c7234', 1, 12, '226195bb-2b4f-4a91-ab4f-0acf00306616', '5baaeb15-d505-4927-b7fd-eea39f61dc80'),
    ('ada72d63-1447-4d82-bb84-d6b0103c7235', 1, 12, '326195bb-2b4f-4a91-ab4f-0acf00306616', '5baaeb15-d505-4927-b7fd-eea39f61dc82'),
    ('ada72d63-1447-4d82-bb84-d6b0103c7236', 1, 9, '326195bb-2b4f-4a91-ab4f-0acf00306616', '79cebd85-1aea-4938-9f9a-9b15e9969e85'),
    ('ada72d63-1447-4d82-bb84-d6b0103c7237', 1, 36, '426195bb-2b4f-4a91-ab4f-0acf00306616', '79cebd85-1aea-4938-9f9a-9b15e9969e72'),
    ('ada72d63-1447-4d82-bb84-d6b0103c7238', 1, 25, '526195bb-2b4f-4a91-ab4f-0acf00306616', '79cebd85-1aea-4938-9f9a-9b15e9969e69'),
    ('ada72d63-1447-4d82-bb84-d6b0103c7239', 1, 29, '526195bb-2b4f-4a91-ab4f-0acf00306616', '79cebd85-1aea-4938-9f9a-9b15e9969e80')
    GO
