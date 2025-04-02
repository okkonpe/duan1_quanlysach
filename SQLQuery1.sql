select * from TacGia
select * from sach
select * from NhaXuatBan
select * from TheLoai
select * from NhanVien
select * from HoaDon
select * from GiamGia
select * from KhachHang
select * from ChiTietHoaDon
delete from HoaDon where MaHoaDon= 'HD0234WL'
UPDATE HoaDon SET  makh=null WHERE MaHoaDon = 'HD0591SY'
drop table GioHang
SELECT [MaHoaDon]
                      ,[MaKH]
                     ,[MaNV]
                      ,[MaGiamGia]
                     ,[GiamGia]
                     ,[ThanhTien]
                      ,[NgayTao]
                      ,[NgaySua]
                      ,[TrangThai]
                  FROM [dbo].[HoaDon]

insert into Sach
values('MS01','Nha gia kim','nxb01','tl01','tg01',2020,100,150000,null,null,null,null)
insert into Sach
values('MS02','Dac nhan tam','nxb01','tl01','tg02',2020,200,250000,null,null,null,null)
insert into Sach
values('MS03','De men','nxb02','tl02','tg01',2022,150,200000,null,null,null,null)
SELECT h.MaHoaDon,h.MaSach,h.soLuong,h.donGia,h.thanhTien,h.ngaySua,h.ngayTao,h.trangThai,s.tensach from ChiTietHoaDon h inner join sach s on h.masach=s.masach
SELECT h.MaHoaDon,h.MaSach,h.soLuong,h.donGia,h.thanhTien,h.ngaySua,h.ngayTao,h.trangThai,s.tensach from ChiTietHoaDon h inner join sach s on h.masach=s.masach where h.MaHoaDon='HD0002EA'
select * from HoaDon where MaHoaDon='HD0002EA'
select h.MaSach,h.SoLuong,h.DonGia,h.ThanhTien,s.TenSach,t.TenTG,x.tenNXB,tl.TenTL from chitiethoadon h inner join sach s on h.masach=s.masach inner join tacgia t on s.matg=t.matg inner join theloai tl on s.matl=tl.matl inner join nhaxuatban x on s.manxb=x.manxb   