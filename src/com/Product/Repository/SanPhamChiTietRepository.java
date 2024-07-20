/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Product.Repository;

import com.Product.config.DBConnect;
import com.Product.entity.SanPhamChiTiet;
import com.Product.form.NhanVienForm;
import com.Product.respone.SanPhamChiTietRespone;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class SanPhamChiTietRepository {

    public ArrayList<SanPhamChiTietRespone> getAll() {
        String sql = "SELECT dbo.SanPhamChiTiet.id, dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, dbo.ThuongHieu.ten_thuong_hieu, " +
             "dbo.XuatXu.ten_nuoc, dbo.MauSac.ten_mau, dbo.KichThuoc.size, dbo.ChatLieu.ten_chat_lieu, dbo.CoAo.ten_co_ao, " +
             "dbo.DoDay.ten_do_day, dbo.TinhLinhHoat.ten_tinh_linh_hoat, dbo.SanPhamChiTiet.gia_ban, dbo.SanPhamChiTiet.so_luong_ton, " +
             "dbo.SanPhamChiTiet.trang_thai " +
             "FROM dbo.SanPhamChiTiet " +
             "INNER JOIN dbo.ThuongHieu ON dbo.SanPhamChiTiet.id_thuong_hieu = dbo.ThuongHieu.id " +
             "INNER JOIN dbo.XuatXu ON dbo.SanPhamChiTiet.id_xuat_xu = dbo.XuatXu.id " +
             "INNER JOIN dbo.MauSac ON dbo.SanPhamChiTiet.id_mau_sac = dbo.MauSac.id " +
             "INNER JOIN dbo.KichThuoc ON dbo.SanPhamChiTiet.id_kich_thuoc = dbo.KichThuoc.id " +
             "INNER JOIN dbo.ChatLieu ON dbo.SanPhamChiTiet.id_chat_lieu = dbo.ChatLieu.id " +
             "INNER JOIN dbo.CoAo ON dbo.SanPhamChiTiet.id_co_ao = dbo.CoAo.id " +
             "INNER JOIN dbo.DoDay ON dbo.SanPhamChiTiet.id_do_day = dbo.DoDay.id " +
             "INNER JOIN dbo.TinhLinhHoat ON dbo.SanPhamChiTiet.id_tinh_linh_hoat = dbo.TinhLinhHoat.id";

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new SanPhamChiTietRespone(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getString(10), rs.getDouble(11),
                        rs.getInt(12), rs.getBoolean(13))); 
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

//    public ArrayList<SanPhamChiTietRespone> search(String keyword, Integer trangThai) {
//        String sql = """
//                    SELECT  hd.Id ,hd.IdKH ,hd.IdNV ,
//                        hd.TinhTrang ,hd.TongTien ,hd.Ma,
//                        kh.Ma ,kh.Ten ,nv.Ma ,nv.Ten 
//                    FROM HoaDon hd ,KhachHang kh , NhanVien nv 
//                    WHERE  hd.IdKH  = kh.Id 
//                    AND hd.IdNV  = nv.Id 
//                    AND hd.TinhTrang = ?
//                    """;
//        // check neu keyword k nhap gi => k can them gi ca 
//        // nhap => moi can cong them vao sql 
//        if (keyword.length() > 0) { // isempty
//            sql += """
//                  AND 
//                  	(hd.Ma LIKE ?
//                        OR 
//                        nv.Ma LIKE ?
//                        OR 
//                        nv.Ten LIKE ?
//                        OR 
//                        kh.Ma LIKE ?
//                        OR 
//                        kh.Ten LIKE ? 
//                        OR 
//                        kh.Sdt LIKE ?
//                  	)
//                  """;
//        }
////        -- ma hoa don, ma nv, ten nv, ma kh, ten kh, sdt
////        -- DK 1 DK2 DK 3 DK4 
////        -- AND => AND 4 DK
////        -- 09
//        // thuoc tinh IS NULL 
//        ArrayList<HoaDonResponse> lists = new ArrayList<>();
//        try (Connection con = DBConnect.getConnection();
//                PreparedStatement ps = con.prepareStatement(sql)) {
//            int index = 1; // Vi tri cua dau hoi cham dau tien 
//            ps.setObject(index++, trangThai);
//            if (keyword.length() > 0) {
//                String value = "%" + keyword + "%";
//                // search 1 o input nhieu truong
//                ps.setObject(index++, value);
//                ps.setObject(index++, value);
//                ps.setObject(index++, value);
//                ps.setObject(index++, value);
//                ps.setObject(index++, value);
//                ps.setObject(index++, value);
//            }
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                HoaDonResponse response = HoaDonResponse.builder()
//                        .id(rs.getInt(1))
//                        .khachHangID(rs.getInt(2))
//                        .nhanVienID(rs.getInt(3))
//                        .trangThai(rs.getInt(4))
//                        .tongTien(rs.getDouble(5))
//                        .ma(rs.getString(6))
//                        .maKhachHang(rs.getString(7))
//                        .tenKhachHang(rs.getString(8))
//                        .maNhanVien(rs.getString(9))
//                        .tenNhanVien(rs.getString(10))
//                        .build();
//                lists.add(response);
//            }
//        } catch (Exception e) {
//            e.printStackTrace(System.out); // nem loi khi xay ra 
//        }
//        return lists;
//    }
    public boolean add(SanPhamChiTiet spct) {
        int check = 0;

         String sql = "INSERT INTO SanPhamChiTiet "
               + "(ten_san_pham, id_thuong_hieu, id_xuat_xu, id_mau_sac, id_kich_thuoc, id_chat_lieu, id_co_ao, id_do_day, id_tinh_linh_hoat, gia_ban, so_luong_ton, trang_thai) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, spct.getTenSanPham());
            ps.setObject(2, spct.getThuongHieuID());
            ps.setObject(3, spct.getXuatXuID());
            ps.setObject(4, spct.getMauSacID());
            ps.setObject(5, spct.getKichThuocID());
            ps.setObject(6, spct.getChatLieuID());
            ps.setObject(7, spct.getCoAoID());
            ps.setObject(8, spct.getDoDayID());
            ps.setObject(9, spct.getTinhLinhHoatID());
            ps.setObject(10, spct.getGiaBan());
            ps.setObject(11, spct.getSoLuongTon());
            ps.setObject(12, spct.isTrangThai());
            

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return check > 0;
    }

    public ArrayList<SanPhamChiTietRespone> searchh(String maSP) {
        String sql = "SELECT dbo.SanPhamChiTiet.id, dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, "
                + "dbo.ThuongHieu.ten_thuong_hieu, dbo.XuatXu.ten_nuoc, dbo.MauSac.ten_mau, dbo.KichThuoc.size, "
                + "dbo.ChatLieu.ten_chat_lieu, dbo.CoAo.ten_co_ao, dbo.DoDay.ten_do_day, dbo.TinhLinhHoat.ten_tinh_linh_hoat, "
                + "dbo.SanPhamChiTiet.gia_ban, dbo.SanPhamChiTiet.so_luong_ton, dbo.SanPhamChiTiet.trang_thai "
                + "FROM dbo.SanPhamChiTiet "
                + "INNER JOIN dbo.ThuongHieu ON dbo.SanPhamChiTiet.id_thuong_hieu = dbo.ThuongHieu.id "
                + "INNER JOIN dbo.XuatXu ON dbo.SanPhamChiTiet.id_xuat_xu = dbo.XuatXu.id "
                + "INNER JOIN dbo.MauSac ON dbo.SanPhamChiTiet.id_mau_sac = dbo.MauSac.id "
                + "INNER JOIN dbo.KichThuoc ON dbo.SanPhamChiTiet.id_kich_thuoc = dbo.KichThuoc.id "
                + "INNER JOIN dbo.ChatLieu ON dbo.SanPhamChiTiet.id_chat_lieu = dbo.ChatLieu.id "
                + "INNER JOIN dbo.CoAo ON dbo.SanPhamChiTiet.id_co_ao = dbo.CoAo.id "
                + "INNER JOIN dbo.DoDay ON dbo.SanPhamChiTiet.id_do_day = dbo.DoDay.id "
                + "INNER JOIN dbo.TinhLinhHoat ON dbo.SanPhamChiTiet.id_tinh_linh_hoat = dbo.TinhLinhHoat.id "
                + "WHERE ma_san_pham_chi_tiet LIKE ? OR ten_thuong_hieu LIKE ? OR ten_nuoc LIKE ? OR ten_mau LIKE ? OR size LIKE ? OR ten_chat_lieu LIKE ? OR ten_co_ao LIKE ? OR ten_do_day LIKE ? OR ten_tinh_linh_hoat LIKE ? OR gia_ban LIKE ? OR so_luong_ton LIKE ? OR dbo.SanPhamChiTiet.trang_thai LIKE ?";

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            String searchString = "%" + maSP + "%"; // Thêm % vào để tạo thành mô phỏng tìm kiếm
            // Đặt giá trị cho các tham số trong câu truy vấn SQL
            for (int i = 1; i <= 13; i++) {
                ps.setString(i, searchString);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ma_san_pham_chi_tiet = rs.getString("ma_san_pham_chi_tiet");
                String thuong_hieu = rs.getString("ten_thuong_hieu");
                String ten_nuoc = rs.getString("ten_nuoc");
                String ten_mau = rs.getString("ten_mau");
                String size = rs.getString("size");
                String ten_chat_lieu = rs.getString("ten_chat_lieu");
                String ten_co_ao = rs.getString("ten_co_ao");
                String ten_do_day = rs.getString("ten_do_day");
                String ten_tinh_linh_hoat = rs.getString("ten_tinh_linh_hoat");
                Double giaBan = rs.getDouble("gia_ban");
                Integer so_luong_ton = rs.getInt("so_luong_ton");
                Boolean trang_thai = rs.getBoolean("trang_thai");

                SanPhamChiTietRespone sanPham = new SanPhamChiTietRespone(id, ma_san_pham_chi_tiet, thuong_hieu, ten_nuoc, ten_mau, size, ten_chat_lieu, ten_co_ao, ten_do_day, ten_tinh_linh_hoat, giaBan, so_luong_ton, trang_thai);
                lists.add(sanPham);
            }
        } catch (Exception e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
            e.printStackTrace(); // In ra stack trace để xem chi tiết lỗi
        }
        return lists;
    }

}
