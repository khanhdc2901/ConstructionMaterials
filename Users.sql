USE vlxd;
GO

-- XÓA BẢNG tokenForgetPassword NẾU TỒN TẠI (do có khóa ngoại phụ thuộc user_login)
IF OBJECT_ID('dbo.tokenForgetPassword', 'U') IS NOT NULL
    DROP TABLE dbo.tokenForgetPassword;
GO

-- XÓA BẢNG user_login NẾU TỒN TẠI
IF OBJECT_ID('dbo.user_login', 'U') IS NOT NULL
    DROP TABLE dbo.user_login;
GO

-- TẠO LẠI BẢNG user_login
CREATE TABLE dbo.user_login (
    UserID INT IDENTITY(1,1) PRIMARY KEY,
    FullName NVARCHAR(255) NOT NULL,
    PhoneNumber NVARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(100),
    Status VARCHAR(20),
    Role VARCHAR(50)
);
GO

-- TẠO LẠI BẢNG tokenForgetPassword
CREATE TABLE dbo.tokenForgetPassword (
    id INT IDENTITY(1,1) PRIMARY KEY,
    token VARCHAR(255) NOT NULL,
    isUsed BIT NOT NULL,
    userId INT NOT NULL,
    expiryTime DATETIME NOT NULL,
    FOREIGN KEY (userId) REFERENCES dbo.user_login(UserID)
);
GO

ALTER TABLE user_login
ADD avatar VARCHAR(255) NULL;


INSERT INTO dbo.user_login (FullName, PhoneNumber, email, password, Status, Role)
VALUES 
(N'Nguyễn Văn A', '0909123456', 'vana@example.com', 'password123', 'active', 'admin'),
(N'Lê Thị B',     '0911222333', 'leb@example.com',   '123456',       'active', 'user'),
(N'Trần Văn C',   '0988777666', 'tranc@example.com', NULL,           'inactive', 'user');

-- Giả sử UserID lần lượt là 1, 2, 3 tương ứng với các dòng trên
INSERT INTO dbo.tokenForgetPassword (token, isUsed, userId, expiryTime)
VALUES
('abc123token', 0, 1, DATEADD(DAY, 1, GETDATE())),
('def456token', 1, 2, DATEADD(HOUR, -1, GETDATE())),
('ghi789token', 0, 3, DATEADD(DAY, 2, GETDATE()));
