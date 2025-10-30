use vlxd
go

CREATE TABLE [dbo].[carts] (
    [id] INT IDENTITY PRIMARY KEY,
    [user_id] INT NOT NULL,
    [created_at] DATETIME DEFAULT GETDATE()
);


CREATE TABLE [dbo].[cart_items] (
    [id] INT IDENTITY PRIMARY KEY,
    [cart_id] INT NOT NULL,
    [product_id] INT NOT NULL,
    [quantity] INT NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES carts(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
