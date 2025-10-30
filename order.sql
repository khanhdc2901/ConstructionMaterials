USE vlxd;
GO

-- Xoá bảng nếu tồn tại (sử dụng cú pháp mới)
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;

-- Tạo bảng orders
CREATE TABLE orders (
    id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT NOT NULL,
    order_date DATETIME DEFAULT GETDATE(),
    total_amount INT,
    status NVARCHAR(50) DEFAULT N'Đang xử lý',
    FOREIGN KEY (user_id) REFERENCES [user_login](UserId)
);

-- Tạo bảng order_items
CREATE TABLE order_items (
    id INT IDENTITY(1,1) PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    price_at_time INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
