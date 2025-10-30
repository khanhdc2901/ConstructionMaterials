-- Kiểm tra và xóa cột 'image' nếu đã tồn tại
IF EXISTS (
    SELECT * 
    FROM INFORMATION_SCHEMA.COLUMNS 
    WHERE TABLE_NAME = 'products' AND COLUMN_NAME = 'image'
)
BEGIN
    ALTER TABLE [dbo].[products] DROP COLUMN [image];
END
go

-- Thêm lại cột 'image'
ALTER TABLE [dbo].[products] ADD [image] NVARCHAR(255) NULL;
go
UPDATE products SET image = 'gachdo.jpg' WHERE id = 1;
UPDATE products SET image = 'gachblock.jpg' WHERE id = 2;
UPDATE products SET image = 'ximanghatien.jpg' WHERE id = 3;
UPDATE products SET image = 'catvang.jpg' WHERE id = 4;
UPDATE products SET image = 'voixay.jpg' WHERE id = 5;
UPDATE products SET image = 'da1x2.jpg' WHERE id = 6;
UPDATE products SET image = 'da2x4.jpg' WHERE id = 7;
UPDATE products SET image = 'da4x6.jpg' WHERE id = 8;
UPDATE products SET image = 'damisang.jpg' WHERE id = 9;
UPDATE products SET image = 'damibui.jpg' WHERE id = 10;
UPDATE products SET image = 'dagranite.jpg' WHERE id = 11;
UPDATE products SET image = 'damarble.jpg' WHERE id = 12;
UPDATE products SET image = 'daslate.jpg' WHERE id = 13;
UPDATE products SET image = 'daopxam.jpg' WHERE id = 14;
UPDATE products SET image = 'dacubic.jpg' WHERE id = 15;
UPDATE products SET image = 'gachlatnen.jpg' WHERE id = 16;
UPDATE products SET image = 'sondulux.jpg' WHERE id = 17;
UPDATE products SET image = 'bottrettuong.jpg' WHERE id = 18;
UPDATE products SET image = 'tamthachcao.jpg' WHERE id = 19;
UPDATE products SET image = 'tamcachnhiet.jpg' WHERE id = 20;
UPDATE products SET image = 'daykem.jpg' WHERE id = 21;
UPDATE products SET image = 'keodangach.jpg' WHERE id = 22;
UPDATE products SET image = 'thepcuonCB240.jpg' WHERE id = 23;
UPDATE products SET image = 'thepthanhCB300.jpg' WHERE id = 24;
UPDATE products SET image = 'thepthanhCB400.jpg' WHERE id = 25;
UPDATE products SET image = 'thepcuonphi6810.jpg' WHERE id = 26;
UPDATE products SET image = 'thepcayphi101214.jpg' WHERE id = 27;
UPDATE products SET image = 'theppomica.jpg' WHERE id = 28;
UPDATE products SET image = 'thephoaphat.jpg' WHERE id = 29;
UPDATE products SET image = 'luoithephang.jpg' WHERE id = 30;
UPDATE products SET image = 'ongnhuapvc.jpg' WHERE id = 31;
UPDATE products SET image = 'ongnoigoc90.jpg' WHERE id = 32;
UPDATE products SET image = 'vankhoanuoc.jpg' WHERE id = 33;
UPDATE products SET image = 'maybomnuoc.jpg' WHERE id = 34;
UPDATE products SET image = 'daydiencandivi.jpg' WHERE id = 35;
UPDATE products SET image = 'ongluongdaydien.jpg' WHERE id = 36;
UPDATE products SET image = 'CBchonggiat.jpg' WHERE id = 37;
UPDATE products SET image = 'congtacdien.jpg' WHERE id = 38;
UPDATE products SET image = 'ledamtran.jpg' WHERE id = 39;
UPDATE products SET image = 'cuanhomkinh.jpg' WHERE id = 40;
UPDATE products SET image = 'taynaminox.jpg' WHERE id = 41;
UPDATE products SET image = 'kinhcuongluc.jpg' WHERE id = 42;
UPDATE products SET image = 'gochongam.jpg' WHERE id = 43;
UPDATE products SET image = 'sangocongnghiep.jpg' WHERE id = 44;
UPDATE products SET image = 'giaydantuong.jpg' WHERE id = 45;
UPDATE products SET image = 'pvcgiada.jpg' WHERE id = 46;
UPDATE products SET image = 'khoacuavantay.jpg' WHERE id = 47;
UPDATE products SET image = 'tubepnhomkinh.jpg' WHERE id = 48;
UPDATE products SET image = 'tonlanhxanh.jpg' WHERE id = 49;
UPDATE products SET image = 'xopdantuong.jpg' WHERE id = 50;
UPDATE products SET image = 'tamloplaysang.jpg' WHERE id = 51;
UPDATE products SET image = 'chongtham.jpg' WHERE id = 52;
UPDATE products SET image = 'palletgo.jpg' WHERE id = 53;
UPDATE products SET image = 'bacchemua.jpg' WHERE id = 54;
UPDATE products SET image = 'dungcuxaydung.jpg' WHERE id = 55;
UPDATE products SET image = 'xago.jpg' WHERE id = 56;
UPDATE products SET image = 'terrazzo.jpg' WHERE id = 57;
UPDATE products SET image = 'dennangluongmattroi.jpg' WHERE id = 58;
UPDATE products SET image = 'sonchongnong.jpg' WHERE id = 59;
UPDATE products SET image = 'luoichongcontrung.jpg' WHERE id = 60;
UPDATE products SET image = 'trangtrisanvuon.jpg' WHERE id = 61;
UPDATE products SET image = 'choigo.jpg' WHERE id = 62;
UPDATE products SET image = 'lancansat.jpg' WHERE id = 63;
UPDATE products SET image = 'tampolyrongruot.jpg' WHERE id = 64;

