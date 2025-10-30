-- Kiểm tra và xóa bảng 'category' nếu nó tồn tại
DROP TABLE IF EXISTS category;

CREATE TABLE category (
    id INT PRIMARY KEY IDENTITY,
    name NVARCHAR(255),
    icon_class NVARCHAR(255),
    text_color NVARCHAR(50)
);

INSERT INTO category (name, icon_class, text_color) VALUES
(N'Raw building materialss', 'bi-bricks', 'text-primary'),
(N'Complete materials', 'bi-house-door', 'text-success'),
(N'Paints and construction chemicals', 'bi-paint-bucket', 'text-warning'),
(N'Water pipes and accessories', 'bi-droplet-half', 'text-info'),
(N'Electrical equipment and lighting', 'bi-lightbulb', 'text-warning'),
(N'Doors and door accessories', 'bi-door-open', 'text-secondary'),
(N'Interior/exterior decoration', 'bi-flower2', 'text-danger'),
(N'Garden and outdoor supplies', 'bi-tree', 'text-success');
