--------------Tạo bảng---------------------
CREATE TABLE DOCGIA
(
    MaDocGia varchar(10) not null,
    MatKhau varchar(20),
    HoTen varchar2(100) not null,
    GioiTinh varchar2(20),
    NgSinh date,
    SDT varchar(20),
    DiaChi varchar2(200) not null,
    Email varchar(30),
    VaiTro varchar2(50) not null,
  CONSTRAINT PK_DOCGIA
  PRIMARY KEY (MaDocGia)
)

CREATE TABLE SACH
(
    MaSach VARCHAR(10) not null,
    TenSach VARCHAR2(200) not null,
    TheLoai varchar2(100),
    SoTrang int,
    TacGia varchar2(200) not null,
    NXB varchar2(100) not null,
    ViTri varchar2(20)not null,
    SoLuong int,
    TinhTrang varchar2(20)not null,
  CONSTRAINT PK_SACH
  PRIMARY KEY (MaSach)
)

CREATE TABLE PHIEUMUONSACH
(
    MaPhieuMuon VARCHAR(10),
    MaDocGia VARCHAR(10),
    MaSach VARCHAR(10),
    NgayMuonSach date,
    TinhTrangPM varchar2(30)not null,
  CONSTRAINT PK_PHIEUMUON
  PRIMARY KEY (MaPhieuMuon)
)

CREATE TABLE PHIEUTRASACH
(
    MaPhieuTra VARCHAR(10),
    MaPhieuMuon VARCHAR(10),
    MaDocGia varchar(10),
    MaSach VARCHAR(10),
    NgayTraSach date,
    SoNgayMuon int,
    SoNgayTraTre int,
  CONSTRAINT PK_PHIEUTRA
  PRIMARY KEY (MaPhieuTra)
)

CREATE TABLE PHIEUPHAT
(
    MaPhieuPhat VARCHAR(10),
    MaDocGia varchar(10),
    MaPhieuTra VARCHAR(10),
    TienPhat INT,
    CONSTRAINT PK_PHAT
    PRIMARY KEY (MaPhieuPhat)
)
ALTER TABLE PHIEUMUONSACH
	ADD CONSTRAINT FPK_Muonsach_DocGia 	FOREIGN KEY (MaDocgia)
	REFERENCES DOCGIA(MaDocGia)
	ADD CONSTRAINT FPK_Muonsach_sach FOREIGN KEY (MaSach)
	REFERENCES SACH(MaSach)

ALTER TABLE PHIEUTRASACH
	ADD CONSTRAINT FPK_TraSach_MuonSach FOREIGN KEY (MaPhieuMuon)
	REFERENCES PHIEUMUONSACH(MaPhieuMuon)
	ADD CONSTRAINT FPK_TraSach_Docgia FOREIGN KEY (MaDocGia)
	REFERENCES DOCGIA(MaDocGia)
	ADD CONSTRAINT FPK_TraSach_Sach FOREIGN KEY (MaSach)
	REFERENCES SACH(MaSach)

ALTER TABLE PHIEUPHAT
	ADD CONSTRAINT FPK_Phat_Docgia FOREIGN KEY (MaDocGia)
	REFERENCES DOCGIA(MaDocGia)
	ADD CONSTRAINT FPK_Phat_Tra FOREIGN KEY (MaPhieuTra)
	REFERENCES PHIEUTRASACH(MaPhieuTra)

ALTER TABLE DOCGIA
  MODIFY MatKhau DEFAULT '0000';
  
  CREATE SEQUENCE s_phieuphat_id
   MINVALUE 1 
   MAXVALUE 9999999 
   INCREMENT BY 1 
   START WITH 5001
   NOCACHE 
   NOORDER 
   NOCYCLE;
   
---Trigger: Tăng mã phiếu phạt tự động
CREATE OR REPLACE TRIGGER AUTO_INCREMENT_TRIGGER
BEFORE INSERT ON
PHIEUPHAT
REFERENCING NEW AS NEW
    FOR EACH ROW BEGIN SELECT
    CONCAT('PP',s_phieuphat_id.NEXTVAL) INTO :NEW.MAPHIEUPHAT
    FROM DUAL;
END;  

-----------------------------------
--------Tình trạng phiếu mượn chỉ nhận 2 giá trị “Đã trả sách” hoặc “Đang mượn sách”
ALTER TABLE PHIEUMUONSACH 
  ADD CONSTRAINT CHECK_TinhTrangPM CHECK(TinhTrangPM='Đã trả' OR TinhTrangPM='Đang mượn');
-----------------------------------
-----------Trigger: Khi số lượng sách = 0 thì cập nhật tình trạng “Hết” và khi  số lượng sách > 0 thì cập nhật tình trạng “Còn” 
CREATE OR REPLACE TRIGGER update_TinhTrangSach BEFORE
  INSERT OR
  UPDATE ON SACH FOR EACH ROW 
DECLARE
    -- variable
BEGIN 
  IF :NEW.SOLUONG > 0 
  THEN 
    :NEW.TINHTRANG := 'Còn';
  END IF;
  IF :NEW.SOLUONG   = 0 
  THEN
    :NEW.TINHTRANG := 'Hết';
  END IF;
END;
-----------------------------------
---------Trigger: Số ngày trả trễ = Số ngày mượn – 30(Nếu < 0 thì gán = 0)
CREATE OR REPLACE TRIGGER update_SONGAYTRATRE BEFORE INSERT OR UPDATE ON PHIEUTRASACH FOR EACH ROW 
DECLARE
    -- variable
BEGIN
  IF :NEW.SONGAYMUON - 30 > 0 
  THEN 
    :NEW.SONGAYTRATRE := :NEW.SONGAYMUON - 30;
  ELSE
    :NEW.SONGAYTRATRE := 0;
  END IF;
END;
-------------------------------
------Trigger: Số lượng sách <0 thì gán = 0
CREATE OR REPLACE TRIGGER update_SOLUONGSACH BEFORE INSERT OR UPDATE 
ON SACH FOR EACH ROW 
DECLARE
    -- variable
BEGIN
  IF (:NEW.SOLUONG < 0) 
  THEN 
    :NEW.SOLUONG := 0;
  END IF;
END;
---------------------------------------------------------
------Trigger: Số ngày mượn = Ngày trả sách – Ngày mượn sách 
CREATE OR REPLACE TRIGGER update_SONGAYMUON AFTER UPDATE
ON PHIEUMUONSACH FOR EACH ROW 
DECLARE
    SoNgM INT;
    TEMP INT;
BEGIN
    SELECT COUNT(*) INTO TEMP
    FROM PHIEUTRASACH PT WHERE :NEW.MAPHIEUMUON = PT.MAPHIEUMUON;
    IF(TEMP>0) THEN
        select PT.NGAYTRASACH - :NEW.NGAYMUONSACH
        INTO SoNgM FROM PHIEUTRASACH PT WHERE :NEW.MAPHIEUMUON = PT.MAPHIEUMUON;
        UPDATE PHIEUTRASACH SET SONGAYMUON = SoNgM WHERE MAPHIEUMUON = :NEW.MAPHIEUMUON;
    END IF;
END;
---------------
CREATE OR REPLACE TRIGGER update_SONGAYMUON1 BEFORE INSERT OR UPDATE 
ON PHIEUTRASACH FOR EACH ROW 
DECLARE
    SoNgM INT;
BEGIN
    select :NEW.NGAYTRASACH - PM.NGAYMUONSACH
    INTO SoNgM from PHIEUMUONSACH PM where :NEW.MAPHIEUMUON = PM.MAPHIEUMUON;
    :NEW.SONGAYMUON := SoNgM;
END;
-----------------------------------------------------
----Trigger: Tự động tạo phiếu phạt khi ngay trả trễ > 0
CREATE OR REPLACE TRIGGER TaoPhieuPhat AFTER INSERT OR UPDATE 
ON PHIEUTRASACH FOR EACH ROW 
DECLARE
    SoNgTraTre INT;
    SoTienPhat INT;
    TEMP INT;
BEGIN
    select :NEW.NGAYTRASACH - PM.NGAYMUONSACH - 30
    INTO SoNgTraTre from PHIEUMUONSACH PM where :NEW.MAPHIEUMUON = PM.MAPHIEUMUON;
    SoTienPhat :=1000*SoNgTraTre;
    SELECT COUNT(*) INTO TEMP FROM PHIEUPHAT PP WHERE PP.MAPHIEUTRA = :NEW.MAPHIEUTRA;
    IF(SoNgTraTre>0)THEN 
        IF(TEMP=0)THEN
            INSERT INTO PHIEUPHAT(MADOCGIA,MAPHIEUTRA,TIENPHAT) VALUES (:NEW.MADOCGIA,:NEW.MAPHIEUTRA,SoTienPhat);
        ELSE
            UPDATE PHIEUPHAT SET MADOCGIA = :NEW.MADOCGIA,
                                 MAPHIEUTRA = :NEW.MAPHIEUTRA,
                                 TIENPHAT = SoTienPhat
            WHERE MAPHIEUTRA = :NEW.MAPHIEUTRA;
        END IF;
    ELSE
        IF(TEMP>0)THEN
            DELETE FROM PHIEUPHAT WHERE MAPHIEUTRA = :NEW.MAPHIEUTRA;
        END IF;
    END IF;
END;

----------------------------------------------------
---------Trigger: Khi mượn sách thì số lượng sách trong kho sẽ giảm và ngược lại
CREATE OR REPLACE TRIGGER UPDATE_SACH AFTER INSERT OR UPDATE OF MASACH OR DELETE
ON PHIEUMUONSACH FOR EACH ROW 
BEGIN
    CASE
        WHEN INSERTING THEN   
            UPDATE SACH SET SOLUONG = SOLUONG -1
            WHERE MASACH = :NEW.MASACH;
        WHEN UPDATING('MASACH') THEN
            UPDATE SACH SET SOLUONG = SOLUONG -1
            WHERE MASACH = :NEW.MASACH;
            UPDATE SACH SET SOLUONG = SOLUONG +1
            WHERE MASACH = :OLD.MASACH;
        WHEN DELETING THEN
            UPDATE SACH SET SOLUONG = SOLUONG +1
            WHERE MASACH = :NEW.MASACH;
    END CASE;
END;
-----------------
CREATE OR REPLACE TRIGGER UPDATE_SACH1 AFTER INSERT OR DELETE
ON PHIEUTRASACH FOR EACH ROW 
BEGIN
    CASE
        WHEN INSERTING THEN   
            UPDATE SACH SET SOLUONG = SOLUONG + 1
            WHERE MASACH = :NEW.MASACH;
        WHEN DELETING THEN
            UPDATE SACH SET SOLUONG = SOLUONG - 1
            WHERE MASACH = :NEW.MASACH;
    END CASE;
END;



  --------------------------------------
---------Store Procedure
-----Procedure Mượn sách: Số lượng sách = 0 thì k đc mượn
CREATE OR REPLACE PROCEDURE INSERT_PHIEUMUON
(
    ID_PhieuMuon IN PHIEUMUONSACH.MaPhieuMuon%TYPE, 
    ID_DocGia IN PHIEUMUONSACH.MaDocGia%TYPE, 
    ID_Sach IN PHIEUMUONSACH.MaSach%TYPE, 
    NgMuonSach IN PHIEUMUONSACH.NgayMuonSach%TYPE, 
    TinhTrang IN PHIEUMUONSACH.TinhTrangPM%TYPE 
)
AS
    TEMP INT;
BEGIN 
    SELECT SOLUONG INTO TEMP
    FROM SACH WHERE MASACH = ID_Sach;
    IF(TEMP>0)THEN
        INSERT INTO PHIEUMUONSACH (MaPhieuMuon, MaDocGia, MaSach, NgayMuonSach, TinhTrangPM) 
        VALUES (ID_PhieuMuon,ID_DocGia,ID_Sach,NgMuonSach,TinhTrang);
    END IF;
END;
--EXECUTE INSERT_PHIEUMUON('PM1031','DG1001','S1501',TO_DATE('17/09/2020','DD/MM/YYYY'),'Đang mượn');
-------------------------------------------
-----Procedure trả sách:	Phiếu trả chỉ trả cho 1 phiếu mượn
CREATE OR REPLACE PROCEDURE INSERT_PHIEUTRA
(
    ID_PhieuTra IN PHIEUTRASACH.MaPhieuTra%TYPE, 
    ID_PhieuMuon IN PHIEUTRASACH.MaPhieuMuon%TYPE, 
    ID_DocGia IN PHIEUTRASACH.MaDocGia%TYPE, 
    ID_Sach IN PHIEUTRASACH.MaSach%TYPE, 
    NgTraSach IN PHIEUTRASACH.NgayTraSach%TYPE
)
AS
    TEMP INT;
BEGIN 
    SELECT COUNT(*) INTO TEMP
    FROM PHIEUTRASACH WHERE MAPHIEUMUON = ID_PhieuMuon;
    IF(TEMP=0)THEN
        INSERT INTO PHIEUTRASACH (MaPhieuTra, MaPhieuMuon, MaDocGia, MaSach, NgayTraSach) 
        VALUES (ID_PhieuTra,ID_PhieuMuon,ID_DocGia,ID_Sach,NgTraSach);
    END IF;
END;

-------------------------------------
--------Procedure đổi mật khẩu:
CREATE OR REPLACE PROCEDURE DOI_MAT_KHAU
(
  ID_DocGia IN DOCGIA.MaDocGia%TYPE, 
  MK_DocGia IN DOCGIA.MatKhau%TYPE
)
AS
BEGIN 
  UPDATE DocGia SET MatKhau = MK_DocGia  WHERE MaDocGia = ID_DocGia;
END;
---EXEC DOI_MAT_KHAU('1','0000');
--------------------
--Procedure thêm độc giả
CREATE OR REPLACE PROCEDURE INSERT_DOCGIA
(
  ID_DocGia IN DOCGIA.MaDocGia%TYPE, 
  HOTEN_DG IN DOCGIA.HOTEN%TYPE,
  GIOITINH_DG IN DOCGIA.GIOITINH%TYPE,
  NGSINH_DG IN DOCGIA.NGSINH%TYPE,
  SDT_DG IN DOCGIA.SDT%TYPE,
  DIACHI_DG IN DOCGIA.DIACHI%TYPE,
  EMAIL_DG IN DOCGIA.EMAIL%TYPE,
  VAITRO_DG IN DOCGIA.VAITRO%TYPE
)
AS
BEGIN 
  INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
  VALUES (ID_DocGia,HOTEN_DG,GIOITINH_DG,NGSINH_DG,SDT_DG,DIACHI_DG,EMAIL_DG,VAITRO_DG);
END;
--EXEC INSERT_DOC_GIA('1111','Nguyen Van C','Nam',TO_DATE('21/10/2001','DD/MM/YYYY'),'124545','sgfdsgds','asd@sds','Th? th?')
--------------------------------
--Procedure xoá độc giả
CREATE OR REPLACE PROCEDURE DELETE_DOCGIA
(
  ID_DocGia IN DOCGIA.MaDocGia%TYPE
)
AS
BEGIN 
  DELETE FROM DOCGIA  
  WHERE MADOCGIA = ID_DocGia;
END;
--EXEC DELETE_DOC_GIA('2');
-----------------------
--Procedure Cập nhật độc giả
CREATE OR REPLACE PROCEDURE UPDATE_DOCGIA
(
  ID_DocGia IN DOCGIA.MaDocGia%TYPE,
  HOTEN_DG IN DOCGIA.HOTEN%TYPE,
  GIOITINH_DG IN DOCGIA.GIOITINH%TYPE,
  NGSINH_DG IN DOCGIA.NGSINH%TYPE,
  SDT_DG IN DOCGIA.SDT%TYPE,
  DIACHI_DG IN DOCGIA.DIACHI%TYPE,
  EMAIL_DG IN DOCGIA.EMAIL%TYPE,
  VAITRO_DG IN DOCGIA.VAITRO%TYPE
)
AS
BEGIN 
  UPDATE DOCGIA  
  SET HOTEN = HOTEN_DG,
      GIOITINH = GIOITINH_DG,
      NGSINH = NGSINH_DG,
      SDT = SDT_DG,
      DIACHI = DIACHI_DG,
      EMAIL = EMAIL_DG,
      VAITRO = VAITRO_DG
  WHERE MADOCGIA = ID_DocGia;
END;
--EXEC UPDATE_DOC_GIA('1','Nguyen Van C','Nam',TO_DATE('21/10/2001','DD/MM/YYYY'),'124545','sgfdsgds','asd@sds','TT');
------------------------------------
---------------------------------




--------------------------------------------------------------

-------------------INSERT dữ liệu---------------------------
--INSERT 30 Đôc giả
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1001','Nguyễn Quốc Lưu','Nam',TO_DATE('21/10/2001','DD/MM/YYYY'),'0399912414','KTX khu A, Khu phố 6 - Phường Linh Trung - TP. Thủ Đức - TP.HCM','quocluu2k1@gmail.com','Độc giả');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1002','Nguyễn Văn Thịnh','Nam',TO_DATE('2/10/2001','DD/MM/YYYY'),'0319912415', 'Long Bình, Thành phố Biên Hòa, Đồng Nai','Thinh2k1@gmail.com','Độc giả');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1003','Nguyễn Hải Linh','Nam',TO_DATE('21/1/2001','DD/MM/YYYY'),'094912416', 'Long Bình, Thành phố Biên Hòa, Đồng Nai','linh2001@gmail.com','Độc giả');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1004','Nguyễn Quốc Thắng','Nam',TO_DATE('10/10/2001','DD/MM/YYYY'),'094812417','KTX khu A, Khu phố 6 - Phường Linh Trung - TP. Thủ Đức - TP.HCM','thangdz@gmail.com','Độc giả');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1005','Trần Hưng','Nam',TO_DATE('11/10/2001','DD/MM/YYYY'),'0329912418', 'Long Bình, Thành phố Biên Hòa, Đồng Nai, Việt Nam','Hung2k1@gmail.com','Độc giả');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1006','Nguyễn Tiến Bình','Nam',TO_DATE('21/3/2001','DD/MM/YYYY'),'0812342419','KTX khu A, Khu phố 6 - Phường Linh Trung - TP. Thủ Đức - TP.HCM','Binhhocgioi@gmail.com','Độc giả');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1007','Đặng Quốc Bảo','Nam',TO_DATE('1/1/2001','DD/MM/YYYY'),'0336912411', '89 Đường Thảo Điền, Thảo Điền, Quận 2, Thành phố Hồ Chí Minh, Việt Nam','Bao2k1@gmail.com','Độc giả');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1008','Nguyễn Ngọc Thịnh ','Nam',TO_DATE('4/8/2001','DD/MM/YYYY'),'0379912412','KTX khu A, Khu phố 6 - Phường Linh Trung - TP. Thủ Đức - TP.HCM','ngocthinh2k1@gmail.com','Độc giả');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1009','Nguyễn Tiến Linh','Nam',TO_DATE('1/5/2000','DD/MM/YYYY'),'0359912413','KTX khu A, Khu phố 6 - Phường Linh Trung - TP. Thủ Đức - TP.HCM','TLinh2k@gmail.com','Độc giả');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1010','Nguyển Thị Hiền','Nữ',TO_DATE('21/4/2001','DD/MM/YYYY'),'0709912444', 'Hẻm 66, Phú Lợi, Thủ Dầu Một, Bình Dương, Việt Nam','hien2k1@gmail.com','Độc giả');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1011','Nguyễn Thu Hằng','Nữ',TO_DATE('15/7/2001','DD/MM/YYYY'),'0779912453','KTX khu A, Khu phố 6 - Phường Linh Trung - TP. Thủ Đức - TP.HCM','thuhang2k1@gmail.com','Độc giả');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1012','Trần Quốc An ','Nam',TO_DATE('16/1/2001','DD/MM/YYYY'),'0769912617', '59 Hoàng Diệu, Phường Linh Trung, Thủ Đức, TP.HCM','qan2k1@gmail.com','Độc giả');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1013','Nguyễn Thị Thu Yến','Nữ',TO_DATE('21/3/2001','DD/MM/YYYY'),'0319917419','KTX khu A, Khu phố 6 - Phường Linh Trung - TP. Thủ Đức - TP.HCM','tyen2213@gmail.com','Độc giả');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1014','Nguyễn Cao Thắng','Nam',TO_DATE('2/10/2001','DD/MM/YYYY'),'0399982411','KTX khu A, Khu phố 6 - Phường Linh Trung - TP. Thủ Đức - TP.HCM','19521123@gmail.com','Độc giả');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1015','Nguyễn Viết Lưu','Nam',TO_DATE('1/12/2001','DD/MM/YYYY'),'0799612414', 'Lê Quý Đôn, Tân Hiệp, Thành phố Biên Hòa, Đồng Nai, Việt Nam','195211231@gmail.com','Độc giả');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1016','Trần Thị Sáu','Nữ',TO_DATE('21/10/2000','DD/MM/YYYY'),'0833912419','KTX khu A, Khu phố 6 - Phường Linh Trung - TP. Thủ Đức - TP.HCM','652k1@gmail.com','Độc giả');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1017','Trịnh Ngọc Đạo','Nam',TO_DATE('21/10/2005','DD/MM/YYYY'),'084912421','KTX khu B, Phường Linh Trung - TP. Thủ Đức - TP.HCM','NGDao21@gmail.com','Độc giả');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1018','Nguyễn Tien Tien','Nam',TO_DATE('4/10/1998','DD/MM/YYYY'),'0399972423', '519 Nguyễn Ảnh Thủ, Tân Chánh Hiệp, Quận 12, Thành phố Hồ Chí Minh, Việt Nam','tientien1@gmail.com','Độc giả');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1019','Nguyễn Cát Tường','Nữ',TO_DATE('21/10/1999','DD/MM/YYYY'),'0859918444','KTX khu B, Phường Linh Trung - TP. Thủ Đức - TP.HCM','catuong@gmail.com','Độc giả');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1020','Nguyễn Ngọc Khánh Ngân','Nữ',TO_DATE('23/4/1996','DD/MM/YYYY'),'0829992464','KTX khu A, Khu phố 6 - Phường Linh Trung - TP. Thủ Đức - TP.HCM','khngan13@gmail.com','Độc giả');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1021','Đặng An Nhiên','Nữ',TO_DATE('29/10/2002','DD/MM/YYYY'),'0799412489','KTX khu A, Khu phố 6 - Phường Linh Trung - TP. Thủ Đức - TP.HCM','annhien@gmail.com','Thủ thư');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1022','Nguyễn Hoài Thương','Nữ',TO_DATE('20/2/2001','DD/MM/YYYY'),'0769912409','KTX khu A, Khu phố 6 - Phường Linh Trung - TP. Thủ Đức - TP.HCM','hoaithuongk1@gmail.com','Thủ thư');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1023','Nguyễn Đăng Khoa','Nam',TO_DATE('2/5/2000','DD/MM/YYYY'),'0789912434','KTX khu A, Khu phố 6 - Phường Linh Trung - TP. Thủ Đức - TP.HCM','dangkhoa1@gmail.com','Thủ thư');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1024','Nguyễn Bảo Long:','Nam',TO_DATE('7/7/2001','DD/MM/YYYY'),'0399912489', '20 Đường số 6, Khu đô thị Him Lam, Quận 7, Thành phố Hồ Chí Minh, Việt Nam','baolong@gmail.com','Thủ thư');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1025','Nguyễn Tuấn Kiệt','Nam',TO_DATE('28/8/2001','DD/MM/YYYY'),'0839932417','KTX khu A, Khu phố 6 - Phường Linh Trung - TP. Thủ Đức - TP.HCM','tuankiet1@gmail.com','Thủ thư');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1026','Đặng Quang Khải ','Nam',TO_DATE('6/3/2001','DD/MM/YYYY'),'0343912412','KTX khu A, Khu phố 6 - Phường Linh Trung - TP. Thủ Đức - TP.HCM','quangkhai2@gmail.com','Độc giả');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1027','Trần Đức Duy ','Nam',TO_DATE('24/2/2000','DD/MM/YYYY'),'0949912726','KTX khu A, Khu phố 6 - Phường Linh Trung - TP. Thủ Đức - TP.HCM','ducduy98@gmail.com','Độc giả');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1028','Nguyễn Minh Triết ','Nam',TO_DATE('26/9/1999','DD/MM/YYYY'),'0319926459', '23 Nguyễn Bính, Phước Nguyễn, Bà Rịa, Bà Rịa - Vũng Tàu, Việt Nam','minhtriet2@gmail.com','Độc giả');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1029','Nguyễn Đức Toàn ','Nam',TO_DATE('21/12/1996','DD/MM/YYYY'),'0709462664','KTX khu B, Phường Linh Trung - TP. Thủ Đức - TP.HCM','ducctoan97@gmail.com','Độc giả');
INSERT INTO DOCGIA (MADOCGIA,HOTEN,GIOITINH,NGSINH,SDT,DIACHI,EMAIL,VAITRO) 
VALUES ('DG1030','Nguyễn Quốc Trung ','Nam',TO_DATE('24/5/1998','DD/MM/YYYY'),'0849912454','KTX khu A, Khu phố 6 - Phường Linh Trung - TP. Thủ Đức - TP.HCM','quotrung98@gmail.com','Độc giả');

--INSERT 30 Sách
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1001','Giáo Trình Kinh Tế Học Chính Trị Mác - Lênin','Chính Trị - Luật Pháp',691,'Hội Đồng Trung Ương Chỉ Đạo Giáo Trình Quốc Gia','Chính Trị Quốc Gia','KF2632',24,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1002','Sơ học lý luận','luận lý',69,'Trần Trọng Kim','NXB Trẻ','KF2633',2,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1003','Hai số phận','tiếu thuyết',60,'kane abel','NXB văn học','KF2634',0,'Hết');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1004','Nhà giả kim','cuộc đời',123,'Paul kim','NXB Văn học','KF2635',4,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1005','Tăng huyết áp','Sức khỏe',100,'Quách tuấn vinh','NXB Học','KF2636',5,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1006','Đời thay đổi khi chúng ta thay đổi','Cuộc đời',600,'Andrew Matthews','NXB Trẻ','KF2637',6,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1007','Harry Potter và Bảo Bối Tử Thần','KHVT',656,'Rowling, J.K.','NXB Trẻ','KF2638',4,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1008','Dạy Con Làm Giàu','Cuộc đời',354,'Robert Kiyosaki ','NXB Trẻ','KF2639',7,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1009','Cho tôi xin một vé đi tuổi thơ','triết lý',365,'Nguyễn Nhật Ánh','NXB Trẻ','KF2631',4,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1011','Dữ liệu lớn','Công nghệ',345,'Viktor Mayer','NXB Trẻ','KF2642',20,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1021','Sơn Tinh Thủy Tinh','Văn học dân gian',657,'Mai Long','NXB Kim Đồng','KF2652',21,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1031','Sự tích dưa hấu','Văn học dân gian',342,'Nguyễn Công Hoan','NXB Kim Đồng','KF2662',22,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1041','Who? Chuyện kể về danh nhân thế giới - Bill Gate','Kiến thức - khoa hoc',346,'Ahn Hyungmo','NXB Kim Đồng','KF2672',7,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1051','Cân bằng cảm xúc','Kiến thức - khoa hoc',102,'Fiona Slater','NXB Kim Đồng','KF2682',4,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1061','Luân lí giáo khoa thư','Kiến thức - khoa hoc',113,'Nguyễn Văn Ngọc','NXB Kim Đồng','KF2692',1,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1071','80 ngày ăn khắp thế giới','Kiến thức - khoa hoc',146,'Phan Anh (Esheep)','NXB Kim Đồng','KF2612',4,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1081','Dr. Stone - Tập 3','MAnga-comic',69,'Riichiro Inagaki','NXB Kim Đồng','KF2732',8,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1991','Cà Nóng chu du Trường Sa','Văn học VN',91,'Bùi Tiểu Quyên','NXB Kim Đồng','KF2832',24,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1141','Nhật kí muỗi','Truyện tranh',61,'Trần Mộng Mẫn','NXB Kim Đồng','KF2932',6,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1201','Trí tuệ giả tạo','Công nghệ',200,'Nicholas Carr','NXB Trẻ','KF2655',09,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1301','Kể chuyện Bác Hồ','Lịch sử - truyền thống',225,'Hoàng Nguyên Cát','NXB Kim Đồng','KF2656',2,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1401','Vật liệu và thiết bị NANO' ,'Công nghệ',304,'Trương Văn Tân','NXB Trẻ','KF2657',6,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1501','Trên nẻo đường giao liên','Lịch sử - truyền thống',40,'Bùi Tự Lực','NXB Kim Đồng','KF2658',0,'Hết');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1601','Những sư tử non','Lịch sử - truyền thống',30,'Đắc Trung','NXB Kim Đồng','KF2659',0,'Hết');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1701','Vừ A Dính','Lịch sử - truyền thống',460,'Hoài Lộc','NXB Kim Đồng','KF2661',0,'Hết');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S8001','Quốc gia khởi nghiệp','Kinh tế',121,' Dan Senor','Chính Trị Quốc Gia','KF2662',3,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1801','Luật Tố cáo (hiện hành)','Chính Trị - Luật Pháp',54,'Quốc hội','Chính Trị Quốc Gia','KF2663',6,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1901','Luật Bình đẳng giới (hiện hành)','Chính Trị - Luật Pháp',234,'Quốc hội','Chính Trị Quốc Gia','KF2664',7,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1111','Luật Luật sư (hiện hành)','Chính Trị - Luật Pháp',289,'Quốc hội','Chính Trị Quốc Gia','KF2665',9,'Còn');
INSERT INTO SACH (MaSach, TenSach, TheLoai, SoTrang, TacGia, NXB, ViTri, SoLuong, TinhTrang) 
VALUES ('S1281','Luật Các tổ chức tín dụng (hiện hành)','Chính Trị - Luật Pháp',208,'Quốc hội','Chính Trị Quốc Gia','KF2677',10,'Còn');

--INSERT 20 phiếu muọn
INSERT INTO PHIEUMUONSACH (MaPhieuMuon, MaDocGia, MaSach, NgayMuonSach, TinhTrangPM) 
VALUES ('PM1001','DG1001','S1001',TO_DATE('06/04/2021','DD/MM/YYYY'),'Đang mượn'); 
INSERT INTO PHIEUMUONSACH (MaPhieuMuon, MaDocGia, MaSach, NgayMuonSach, TinhTrangPM)
VALUES ('PM1002','DG1002','S1002',TO_DATE('14/03/2021','DD/MM/YYYY'),'Đã trả');
INSERT INTO PHIEUMUONSACH (MaPhieuMuon, MaDocGia, MaSach, NgayMuonSach, TinhTrangPM)
VALUES ('PM1003','DG1003','S1003',TO_DATE('01/04/2021','DD/MM/YYYY'),'Đang mượn'); 
INSERT INTO PHIEUMUONSACH (MaPhieuMuon, MaDocGia, MaSach, NgayMuonSach, TinhTrangPM)
VALUES ('PM1004','DG1004','S1004',TO_DATE('1/03/2021','DD/MM/YYYY'),'Đã trả');
INSERT INTO PHIEUMUONSACH (MaPhieuMuon, MaDocGia, MaSach, NgayMuonSach, TinhTrangPM)
VALUES ('PM1005','DG1005','S1005',TO_DATE('05/04/2021','DD/MM/YYYY'),'Đang mượn'); 
INSERT INTO PHIEUMUONSACH (MaPhieuMuon, MaDocGia, MaSach, NgayMuonSach, TinhTrangPM)
VALUES ('PM1006','DG1006','S1006',TO_DATE('14/05/2021','DD/MM/YYYY'),'Đã trả');
INSERT INTO PHIEUMUONSACH (MaPhieuMuon, MaDocGia, MaSach, NgayMuonSach, TinhTrangPM)
VALUES ('PM1007','DG1007','S1007',TO_DATE('06/05/2021','DD/MM/YYYY'),'Đang mượn'); 
INSERT INTO PHIEUMUONSACH (MaPhieuMuon, MaDocGia, MaSach, NgayMuonSach, TinhTrangPM)
VALUES ('PM1008','DG1008','S1008',TO_DATE('14/01/2021','DD/MM/YYYY'),'Đã trả');
INSERT INTO PHIEUMUONSACH (MaPhieuMuon, MaDocGia, MaSach, NgayMuonSach, TinhTrangPM)
VALUES ('PM1009','DG1009','S1009',TO_DATE('01/02/2021','DD/MM/YYYY'),'Đang mượn'); 
INSERT INTO PHIEUMUONSACH (MaPhieuMuon, MaDocGia, MaSach, NgayMuonSach, TinhTrangPM)
VALUES ('PM1010','DG1010','S1011',TO_DATE('11/03/2021','DD/MM/YYYY'),'Đã trả');
INSERT INTO PHIEUMUONSACH (MaPhieuMuon, MaDocGia, MaSach, NgayMuonSach, TinhTrangPM)
VALUES ('PM1011','DG1011','S1021',TO_DATE('02/04/2021','DD/MM/YYYY'),'Đã trả'); 
INSERT INTO PHIEUMUONSACH (MaPhieuMuon, MaDocGia, MaSach, NgayMuonSach, TinhTrangPM)
VALUES ('PM1012','DG1012','S1031',TO_DATE('09/03/2021','DD/MM/YYYY'),'Đã trả');
INSERT INTO PHIEUMUONSACH (MaPhieuMuon, MaDocGia, MaSach, NgayMuonSach, TinhTrangPM)
VALUES ('PM1013','DG1013','S1041',TO_DATE('04/04/2021','DD/MM/YYYY'),'Đã trả'); 
INSERT INTO PHIEUMUONSACH (MaPhieuMuon, MaDocGia, MaSach, NgayMuonSach, TinhTrangPM)
VALUES ('PM1014','DG1014','S1051',TO_DATE('09/02/2021','DD/MM/YYYY'),'Đã trả');
INSERT INTO PHIEUMUONSACH (MaPhieuMuon, MaDocGia, MaSach, NgayMuonSach, TinhTrangPM)
VALUES ('PM1015','DG1015','S1061',TO_DATE('06/02/2021','DD/MM/YYYY'),'Đã trả'); 
INSERT INTO PHIEUMUONSACH (MaPhieuMuon, MaDocGia, MaSach, NgayMuonSach, TinhTrangPM)
VALUES ('PM1016','DG1016','S1071',TO_DATE('14/01/2021','DD/MM/YYYY'),'Đã trả');
INSERT INTO PHIEUMUONSACH (MaPhieuMuon, MaDocGia, MaSach, NgayMuonSach, TinhTrangPM)
VALUES ('PM1017','DG1017','S1081',TO_DATE('05/04/2021','DD/MM/YYYY'),'Đã trả'); 
INSERT INTO PHIEUMUONSACH (MaPhieuMuon, MaDocGia, MaSach, NgayMuonSach, TinhTrangPM)
VALUES ('PM1018','DG1018','S1991',TO_DATE('20/03/2021','DD/MM/YYYY'),'Đã trả');
INSERT INTO PHIEUMUONSACH (MaPhieuMuon, MaDocGia, MaSach, NgayMuonSach, TinhTrangPM)
VALUES ('PM1019','DG1019','S1141',TO_DATE('10/04/2021','DD/MM/YYYY'),'Đã trả'); 
INSERT INTO PHIEUMUONSACH (MaPhieuMuon, MaDocGia, MaSach, NgayMuonSach, TinhTrangPM)
VALUES ('PM1020','DG1020','S1201',TO_DATE('12/03/2021','DD/MM/YYYY'),'Đã trả');

--INSERT 15 phiếu trả
INSERT INTO PHIEUTRASACH (MaPhieuTra, MaPhieuMuon, MaDocGia, MaSach, NgayTraSach) 
VALUES ('PT1001','PM1002','DG1002','S1002',TO_DATE('12/04/2021','DD/MM/YYYY'));
INSERT INTO PHIEUTRASACH (MaPhieuTra, MaPhieuMuon, MaDocGia, MaSach, NgayTraSach) 
VALUES ('PT1002','PM1004','DG1004','S1004',TO_DATE('12/09/2021','DD/MM/YYYY'));
INSERT INTO PHIEUTRASACH (MaPhieuTra, MaPhieuMuon, MaDocGia, MaSach, NgayTraSach) 
VALUES ('PT1003','PM1006','DG1006','S1006',TO_DATE('12/08/2021','DD/MM/YYYY'));
INSERT INTO PHIEUTRASACH (MaPhieuTra, MaPhieuMuon, MaDocGia, MaSach, NgayTraSach) 
VALUES ('PT1004','PM1008','DG1008','S1008',TO_DATE('12/07/2021','DD/MM/YYYY'));
INSERT INTO PHIEUTRASACH (MaPhieuTra, MaPhieuMuon, MaDocGia, MaSach, NgayTraSach) 
VALUES ('PT1005','PM1010','DG1010','S1011',TO_DATE('13/08/2021','DD/MM/YYYY'));
INSERT INTO PHIEUTRASACH (MaPhieuTra, MaPhieuMuon, MaDocGia, MaSach, NgayTraSach) 
VALUES ('PT1006','PM1011','DG1011','S1021',TO_DATE('01/12/2021','DD/MM/YYYY'));
INSERT INTO PHIEUTRASACH (MaPhieuTra, MaPhieuMuon, MaDocGia, MaSach, NgayTraSach) 
VALUES ('PT1007','PM1012','DG1012','S1031',TO_DATE('10/07/2021','DD/MM/YYYY'));
INSERT INTO PHIEUTRASACH (MaPhieuTra, MaPhieuMuon, MaDocGia, MaSach, NgayTraSach) 
VALUES ('PT1008','PM1013','DG1013','S1041',TO_DATE('15/11/2021','DD/MM/YYYY'));
INSERT INTO PHIEUTRASACH (MaPhieuTra, MaPhieuMuon, MaDocGia, MaSach, NgayTraSach) 
VALUES ('PT1009','PM1014','DG1014','S1051',TO_DATE('12/10/2021','DD/MM/YYYY'));
INSERT INTO PHIEUTRASACH (MaPhieuTra, MaPhieuMuon, MaDocGia, MaSach, NgayTraSach) 
VALUES ('PT1010','PM1015','DG1015','S1061',TO_DATE('13/08/2021','DD/MM/YYYY'));
INSERT INTO PHIEUTRASACH (MaPhieuTra, MaPhieuMuon, MaDocGia, MaSach, NgayTraSach) 
VALUES ('PT1011','PM1016','DG1016','S1071',TO_DATE('03/10/2021','DD/MM/YYYY'));
INSERT INTO PHIEUTRASACH (MaPhieuTra, MaPhieuMuon, MaDocGia, MaSach, NgayTraSach) 
VALUES ('PT1012','PM1017','DG1017','S1081',TO_DATE('21/11/2021','DD/MM/YYYY'));
INSERT INTO PHIEUTRASACH (MaPhieuTra, MaPhieuMuon, MaDocGia, MaSach, NgayTraSach) 
VALUES ('PT1013','PM1018','DG1018','S1991',TO_DATE('30/12/2021','DD/MM/YYYY'));
INSERT INTO PHIEUTRASACH (MaPhieuTra, MaPhieuMuon, MaDocGia, MaSach, NgayTraSach) 
VALUES ('PT1014','PM1019','DG1019','S1141',TO_DATE('22/08/2021','DD/MM/YYYY'));
INSERT INTO PHIEUTRASACH (MaPhieuTra, MaPhieuMuon, MaDocGia, MaSach, NgayTraSach) 
VALUES ('PT1015','PM1020','DG1020','S1201',TO_DATE('14/09/2021','DD/MM/YYYY'));


