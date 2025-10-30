use vlxd
go

-- Xóa bảng Sale nếu đã tồn tại
IF OBJECT_ID('Sale', 'U') IS NOT NULL
    DROP TABLE Sale;
GO

CREATE TABLE Sale (
    id INT PRIMARY KEY,
    name NVARCHAR(255) NOT NULL,
    discount INT NOT NULL,
    typeOfDiscount INT NOT NULL,     -- 0: %, 1: tiền mặt
    soLuong INT NOT NULL,
    coHanSuDung BIT NOT NULL,        -- 1: có hạn, 0: không
    dateStart DATETIME NULL,
    dateEnd DATETIME NULL
);

INSERT INTO Sale (id, name, discount, typeOfDiscount, soLuong, coHanSuDung, dateStart, dateEnd) VALUES
(1, N'Giảm 10% xi măng', 10, 0, 100, 1, '2025-06-01', '2025-07-01'),
(2, N'Giảm 20.000 cho sơn Jotun', 20000, 1, 50, 1, '2025-06-10', '2025-07-10'),
(3, N'Mua ống nhựa tặng 5%', 5, 0, 200, 0, NULL, NULL),
(4, N'Combo gạch men giảm 15%', 15, 0, 75, 1, '2025-05-15', '2025-06-30'),
(5, N'Tặng 30.000 khi mua >500k', 30000, 1, 999, 1, '2025-06-01', '2025-12-31'),
(6, N'Giảm 8% cho đơn đầu tiên', 8, 0, 2147483647, 1, '2025-01-01', '2025-12-31'),
(7, N'Flash sale đá granite', 50000, 1, 30, 1, '2025-06-19', '2025-06-20'),
(8, N'Khuyến mãi sắt thép 10%', 10, 0, 100, 1, '2025-06-01', '2025-07-01'),
(9, N'Miễn phí vận chuyển', 25000, 1, 500, 0, NULL, NULL),
(10, N'Siêu sale 50% phụ kiện', 50, 0, 20, 1, '2025-06-25', '2025-07-05'),
(11, N'Voucher 10k gạch lát', 10000, 1, 1000, 0, NULL, NULL),
(12, N'Mua combo trần thạch cao -10%', 10, 0, 40, 1, '2025-06-01', '2025-08-01'),
(13, N'Ưu đãi đại lý 20%', 20, 0, 2147483647, 0, NULL, NULL),
(14, N'Khuyến mãi gạch bông', 15, 0, 80, 1, '2025-06-15', '2025-07-15'),
(15, N'Tặng thêm 5m dây điện', 0, 1, 60, 1, '2025-06-20', '2025-06-30'),
(16, N'Giảm 5% hóa đơn > 2 triệu', 5, 0, 500, 1, '2025-06-10', '2025-08-10'),
(17, N'Giảm 100k đơn hàng nội thất', 100000, 1, 30, 1, '2025-06-01', '2025-07-01'),
(18, N'Giảm giá mùa hè - vật liệu xây', 12, 0, 2147483647, 1, '2025-06-01', '2025-08-31'),
(19, N'Ưu đãi cát đá xây dựng', 7, 0, 150, 1, '2025-06-01', '2025-07-15'),
(20, N'Sale 15% vật tư điện nước', 15, 0, 90, 1, '2025-06-20', '2025-07-10');
