


create table  DANH_MUC
(
	MaDM int IDENTITY(1,1) primary key ,
	TenDanhMuc nvarchar(50) not null,
	TrangThai bit not null
)
GO


CREATE TABLE SAN_PHAM
(
	MaSP int IDENTITY(1,1),
	TenSP NVARCHAR(100),
	NgayNhap VARCHAR(20),
	NgayCapNhat VARCHAR(20),
	TrangThai NVARCHAR(30),
	MaDM int,
	PRIMARY KEY (MaSP),
	FOREIGN KEY (MaDM) REFERENCES DANH_MUC(MaDM)
)
GO


CREATE TABLE CHAT_LIEU_DE_GIAY(
	MaCLDe INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	TenChatLieuDe NVARCHAR(40),
	TrangThai BIT,
)
GO

CREATE TABLE SIZE(
	MaSize INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	KichThuoc INT,
	TrangThai BIT,
)
GO


CREATE TABLE MAU_SAC(
	MaMS INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	TenMau NVARCHAR(20),
	TrangThai BIT
)
GO


CREATE TABLE CHAT_LIEU(
	MaCL INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	TenChatLieu NVARCHAR(40),
	TrangThai BIT,
)
GO


create TABLE CHI_TIET_SAN_PHAM
(
	MaCTSP int IDENTITY(1,1),
	SoLuong INT,
	DonGia float,
	MaSP int,
	MaCLDe int,
	MaSize INT,
	MaMS INT,
	MaCL INT,
	MoTa NVARCHAR(100),
	PRIMARY KEY (MaCTSP),
	FOREIGN KEY (MaSP) REFERENCES SAN_PHAM(MaSP),
	FOREIGN KEY (MaCLDe) REFERENCES CHAT_LIEU_DE_GIAY(MaCLDe),
	FOREIGN KEY (MaSize) REFERENCES SIZE(MaSize),
	FOREIGN KEY (MaMS) REFERENCES MAU_SAC(MaMS),
	FOREIGN KEY (MaCL) REFERENCES CHAT_LIEU(MaCL)
)
GO


create table NHAN_VIEN(
	MaNV int IDENTITY(1,1) primary key,
	TenDangNhap varchar(50) UNIQUE not null,
	MatKhau varchar(8) not null,
	HoTen	nvarchar(50) not null,
	GioiTinh bit not null,
	DienThoai varchar(10) not null,
	Email nvarchar(50) not null,
	NgayTao date not null,
	ChucVu bit not null,
	TrangThai bit
)


CREATE TABLE THONG_TIN_KH
(
	MaTTKH int IDENTITY(1,1),
	TenKH NVARCHAR(50),
	SDT VARCHAR(10),
	NgayCN DATE,
	TrangThai bit
	PRIMARY KEY (MaTTKH)
)
GO


create table HOA_DON(
	MaHD int IDENTITY(1,1) primary key,
	NgayTao VARCHAR(20),	
	TongTien float,
	TrangThai BIT DEFAULT(0),
	MaTTKH int,
	MaNV INT not null,
	foreign key (MaTTKH) references  THONG_TIN_KH(MaTTKH),
	foreign key (MaNV) references  NHAN_VIEN(MaNV),
)
GO

CREATE TABLE HOA_DON_KHUYEN_MAI
(
    MaHDKM INT int IDENTITY(1,1) primary key,
    MaHD INT,
    MaKM INT,
    SotienConLai FLOAT
)


create table HOA_DON_CHI_TIET
(
	MaHDCT int IDENTITY(1,1) primary key,
	MaCTSP INT,
	MaHD int,
	MaTT int,
	SoLuong INT,
	DonGia FLOAT,
	MaKM int,
	GhiChu NVARCHAR (70),
	foreign key (MaCTSP) references  CHI_TIET_SAN_PHAM(MaCTSP),
	foreign key (MaHD) references  HOA_DON(MaHD),
	foreign key (MaTT) references  THANH_TOAN(MaTT),
	foreign key (MaKM ) references  KHUYEN_MAI(MaKM )
)
go

CREATE table THANH_TOAN(
	MaTT int IDENTITY(1,1) primary key,
	HinhThucThanhToan nvarchar(20) not null
)
go


CREATE TABLE KHUYEN_MAI
(
	MaKM int IDENTITY(1,1),
	TenKhuyenMai NVARCHAR(100),
	NgayBD DATE,
	NgayKT DATE,
	MucGiam FLOAT,
	MaGiam NVARCHAR(8),
	DonVi bit,
	TrangThai BIT,
	PRIMARY KEY (MaKM)
)
GO

create table Giam_Gia
(
MaGG int identity(1,1) primary key,
TenMaGiam nvarchar(50), 
MucGiam float,
NgayBatDau date,
NgayKetThuc date,
GhiChu nvarchar(100)
)