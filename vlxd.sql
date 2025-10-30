
/*******************************************************************************
    VLXD Database - Reformatted for Compatibility with Chinook Format
*******************************************************************************/

IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'vlxd')
BEGIN
    ALTER DATABASE [vlxd] SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE [vlxd];
END
GO

CREATE DATABASE [vlxd];
GO

USE [vlxd];
GO

USE [master]
GO
/****** Object:  Database [vlxd]    Script Date: 19/06/2025 1:52:25 CH ******/
CREATE DATABASE [vlxd]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'vlxd', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\vlxd.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'vlxd_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\vlxd_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
EXEC sys.sp_db_vardecimal_storage_format N'vlxd', N'ON'
GO
USE [vlxd]
GO
/****** Object:  Table [dbo].[categories]    Script Date: 19/06/2025 1:52:25 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[categories](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[products]    Script Date: 19/06/2025 1:52:25 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[products](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NOT NULL,
	[description] [nvarchar](1000) NULL,
	[category_id] [int] NULL,
	[price] [int] NULL,
	[stock_quantity] [int] NULL,
	[unit] [nvarchar](50) NULL,
	[brand_name] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[categories] ON 

INSERT [dbo].[categories] ([id], [name]) VALUES (1, N'Vật liệu xây dựng thô')
INSERT [dbo].[categories] ([id], [name]) VALUES (2, N'Vật liệu hoàn thiện')
INSERT [dbo].[categories] ([id], [name]) VALUES (3, N'Sơn và hóa chất xây dựng')
INSERT [dbo].[categories] ([id], [name]) VALUES (4, N'Ống nước và phụ kiện')
INSERT [dbo].[categories] ([id], [name]) VALUES (5, N'Thiết bị điện và đèn chiếu sáng')
INSERT [dbo].[categories] ([id], [name]) VALUES (6, N'Cửa và phụ kiện cửa')
INSERT [dbo].[categories] ([id], [name]) VALUES (7, N'Trang trí nội/ngoại thất')
INSERT [dbo].[categories] ([id], [name]) VALUES (8, N'Vật tư sân vườn và ngoại thất')
SET IDENTITY_INSERT [dbo].[categories] OFF
GO
SET IDENTITY_INSERT [dbo].[products] ON 

INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (1, N'Gạch đỏ', N'Gạch nung truyền thống dùng để xây tường', 1, 1500, 10000, N'viên', N'Vĩnh Long')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (2, N'Gạch block', N'Gạch không nung dùng cho xây tường', 1, 1500, 8000, N'viên', N'Bình Minh')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (3, N'Xi măng Hà Tiên', N'Xi măng dùng cho xây dựng dân dụng', 1, 90000, 1200, N'bao', N'Hà Tiên')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (4, N'Cát vàng', N'Cát dùng cho bê tông', 1, 300000, 50, N'm³', N'Sông Đuống')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (5, N'Vôi xây', N'Vôi quét tường, trát', 1, 10000, 100, N'kg', N'Nội địa')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (6, N'Đá 1x2', N'Dùng để bê tông nhà, đường', 2, 300000, 40, N'm³', N'Đồng Nai')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (7, N'Đá 2x4', N'Dùng cho bê tông cốt thép mác cao', 2, 300000, 70, N'm³', N'Đồng Nai')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (8, N'Đá 4x6', N'Dùng để bê tông móng, nền đường', 2, 300000, 80, N'm³', N'Hòa An')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (9, N'Đá mi sàng', N'Làm nền móng, cấp phối nền', 2, 290000, 90, N'm³', N'Phú Tài')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (10, N'Đá mi bụi', N'San lấp, làm nền', 2, 280000, 200, N'm³', N'Đồng Nai')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (11, N'Đá granite', N'Ốp lát trang trí, mặt bếp', 2, 1200000, 100, N'm²', N'Thiên Sơn')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (12, N'Đá marble', N'Trang trí tường, sàn cao cấp', 2, 1250000, 80, N'm²', N'Thiên Sơn')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (13, N'Đá slate', N'Trang trí mặt tiền, sân vườn', 2, 950000, 120, N'm²', N'Phú Tài')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (14, N'Đá ốp xám', N'Ốp tường ngoại thất', 2, 950000, 100, N'm²', N'Thiên Sơn')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (15, N'Đá cubic', N'Lát sân vườn, lối đi', 2, 550000, 200, N'm²', N'Bình Định')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (16, N'Gạch lát nền 60x60', N'Gạch lát nền', 3, 300000, 100, N'm²', N'Prime')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (17, N'Sơn Dulux', N'Sơn nước nội thất', 3, 780000, 80, N'thùng', N'Dulux')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (18, N'Bột trét tường', N'Bột xử lý bề mặt tường trước khi sơn', 3, 120000, 100, N'bao', N'Joton')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (19, N'Tấm thạch cao', N'Tấm dùng làm trần hoặc vách ngăn', 3, 95000, 200, N'tấm', N'Vĩnh Tường')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (20, N'Tấm cách nhiệt', N'Vật liệu giúp cách nhiệt tường, mái', 3, 150000, 150, N'tấm', N'Cát Tường')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (21, N'Dây kẽm buộc', N'Dùng để buộc thép', 4, 12000, 200, N'kg', N'Thép Việt')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (22, N'Keo dán gạch', N'Keo chuyên dụng để dán gạch', 4, 75000, 100, N'bao', N'Mapei')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (23, N'Thép cuộn CB240-T', N'Thép cuộn CB240-T', 4, 15000, 5000, N'kg', N'Hòa Phát')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (24, N'Thép thanh CB300-V', N'Thép cốt bê tông, chịu lực cao', 4, 15000, 3000, N'kg', N'Hòa Phát')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (25, N'Thép thanh CB400-V', N'Chịu lực cao, công trình lớn', 4, 15000, 4000, N'kg', N'Hòa Phát')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (26, N'Thép cuộn phi 6, 8, 10', N'Dùng cho xây nhà dân', 4, 15000, 2000, N'kg', N'Hòa Phát')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (27, N'Thép cây phi 10, 12, 14', N'Thanh thép cắt sẵn', 4, 15000, 1000, N'kg', N'Hòa Phát')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (28, N'Thép Pomina 10mm', N'Thép Pomina 10mm', 4, 19500, 1000, N'cây', N'Pomina')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (29, N'Thép Hòa Phát 12mm', N'Thép tròn phi 12mm', 4, 19500, 300, N'cây', N'Hòa Phát')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (30, N'Lưới thép hàn', N'Lưới thép dùng cho sàn bê tông', 4, 450000, 100, N'tấm', N'Việt Mỹ')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (31, N'Ống nhựa PVC phi 60', N'Ống nhựa cấp thoát nước', 4, 50000, 1000, N'cái', N'Bình Minh')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (32, N'Ống nối góc 90°', N'Ống nhựa nối góc', 4, 2000, 1000, N'cái', N'Tiền Phong')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (33, N'Van khóa nước', N'Van đóng/mở nước', 4, 25000, 500, N'cái', N'Tiền Phong')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (34, N'Máy bơm nước', N'Bơm nước dân dụng', 4, 1250000, 30, N'cái', N'Panasonic')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (35, N'Dây điện Cadivi 2x2.5', N'Dây điện đôi lõi đồng', 5, 15000, 10000, N'm', N'Cadivi')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (36, N'Ống luồn dây điện', N'Ống bảo vệ dây điện', 5, 6500, 500, N'ống', N'Sino')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (37, N'CB chống giật 20A', N'Cầu dao tự động', 5, 85000, 500, N'cái', N'LS')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (38, N'Công tắc điện', N'Công tắc gắn tường', 5, 5000, 1000, N'cái', N'Panasonic')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (39, N'Đèn LED âm trần', N'Đèn LED tiết kiệm điện', 5, 150000, 500, N'cái', N'Rạng Đông')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (40, N'Cửa nhôm kính', N'Cửa kính khung nhôm', 6, 1500000, 200, N'cái', N'Xingfa')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (41, N'Tay nắm cửa inox', N'Tay nắm dùng cho cửa gỗ/nhôm', 6, 80000, 300, N'cái', N'Hafele')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (42, N'Kính cường lực 10mm', N'Tấm kính cửa hoặc lan can', 6, 750000, 100, N'm²', N'VSG')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (43, N'Gỗ MDF chống ẩm', N'Tấm ván dùng làm tủ, bàn', 6, 850000, 80, N'tấm', N'An Cường')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (44, N'Sàn gỗ công nghiệp', N'Sàn lát nhà bằng gỗ công nghiệp', 6, 250000, 100, N'm²', N'Wilson')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (45, N'Giấy dán tường Hàn Quốc', N'Trang trí nội thất bằng giấy', 6, 80000, 300, N'cuộn', N'DecoHome')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (46, N'Tấm PVC giả đá', N'Trang trí giả đá sang trọng', 6, 400000, 200, N'tấm', N'PVC Decor')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (47, N'Khóa cửa vân tay', N'Khóa điện tử cho cửa sắt/gỗ', 6, 2250000, 20, N'cái', N'Samsung')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (48, N'Tủ bếp nhôm kính', N'Tủ bếp chất liệu nhôm kính', 6, 3000000, 10, N'bộ', N'Nam Phong')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (49, N'Tôn lạnh màu xanh', N'Tôn lợp mái cách nhiệt', 7, 450000, 100, N'm²', N'Hoa Sen')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (50, N'Xốp dán tường 3D', N'Vật liệu trang trí tường', 7, 15000, 300, N'tấm', N'HappyHome')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (51, N'Tấm lợp lấy sáng', N'Tấm nhựa lấy sáng mái hiên', 7, 150000, 100, N'tấm', N'PolyPro')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (52, N'Chất chống thấm Sika', N'Chống thấm tường, sàn', 7, 95000, 150, N'chai', N'Sika')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (53, N'Pallet gỗ', N'Kệ hàng trong kho', 7, 250000, 150, N'cái', N'Gỗ Việt')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (54, N'Bạt che mưa', N'Bạt che mưa công trình', 7, 35000, 200, N'm²', N'INTOC')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (55, N'Dụng cụ xây dựng', N'Dây kẽm, bay, giàn giáo…', 7, 300000, 500, N'bộ', N'Hòa Bình')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (56, N'Xa gồ thép mạ kẽm', N'Thanh xà gồ lợp mái', 7, 120000, 700, N'm', N'An Cường')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (57, N'Gạch terrazzo', N'Gạch lát sân vườn', 8, 20000, 2000, N'viên', N'Đồng Tâm')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (58, N'Đèn năng lượng mặt trời', N'Đèn dùng năng lượng mặt trời', 8, 1500000, 100, N'cái', N'Suntek')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (59, N'Sơn chống nóng', N'Sơn phản nhiệt', 8, 1200000, 60, N'lon', N'Xanh Decor')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (60, N'Lưới chắn côn trùng', N'Lưới nhựa chắn côn trùng', 8, 35000, 300, N'm²', N'INTOC')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (61, N'Trang trí sân vườn', N'Trang trí tường, ban công', 8, 150000, 500, N'm²', N'GreenLife')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (62, N'Chòi gỗ sân vườn', N'Chòi nghỉ sân vườn', 8, 25000000, 15, N'bộ', N'Đá Mỹ Nghệ Ninh Bình')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (63, N'Lan can sắt nghệ thuật', N'Lan can sắt ngoài trời', 8, 1250000, 80, N'm', N'Sắt Mỹ Nghệ Phúc An')
INSERT [dbo].[products] ([id], [name], [description], [category_id], [price], [stock_quantity], [unit], [brand_name]) VALUES (64, N'Tấm poly rỗng ruột', N'Tấm lấy sáng cho mái hiên', 8, 315000, 80, N'tấm', N'PolyPro')
SET IDENTITY_INSERT [dbo].[products] OFF
GO
ALTER TABLE [dbo].[products]  WITH CHECK ADD FOREIGN KEY([category_id])
REFERENCES [dbo].[categories] ([id])
GO
USE [master]
GO
