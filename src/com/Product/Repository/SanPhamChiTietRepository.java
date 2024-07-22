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
        String sql = "SELECT dbo.SanPhamChiTiet.id, \n"
                + "       dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, \n"
                + "       dbo.SanPham.ten_san_pham, \n"
                + "       dbo.ThuongHieu.ten_thuong_hieu, \n"
                + "       dbo.XuatXu.ten_nuoc, \n"
                + "       dbo.MauSac.ten_mau, \n"
                + "       dbo.KichThuoc.size, \n"
                + "       dbo.ChatLieu.ten_chat_lieu, \n"
                + "       dbo.CoAo.ten_co_ao, \n"
                + "       dbo.DoDay.ten_do_day, \n"
                + "       dbo.TinhLinhHoat.ten_tinh_linh_hoat, \n"
                + "       dbo.SanPhamChiTiet.gia_ban, \n"
                + "       dbo.SanPhamChiTiet.so_luong_ton, \n"
                + "       dbo.SanPhamChiTiet.trang_thai \n"
                + "FROM dbo.SanPhamChiTiet \n"
                + "INNER JOIN dbo.SanPham ON dbo.SanPhamChiTiet.id_san_pham = dbo.SanPham.id\n"
                + "INNER JOIN dbo.ThuongHieu ON dbo.SanPhamChiTiet.id_thuong_hieu = dbo.ThuongHieu.id \n"
                + "INNER JOIN dbo.XuatXu ON dbo.SanPhamChiTiet.id_xuat_xu = dbo.XuatXu.id \n"
                + "INNER JOIN dbo.MauSac ON dbo.SanPhamChiTiet.id_mau_sac = dbo.MauSac.id \n"
                + "INNER JOIN dbo.KichThuoc ON dbo.SanPhamChiTiet.id_kich_thuoc = dbo.KichThuoc.id \n"
                + "INNER JOIN dbo.ChatLieu ON dbo.SanPhamChiTiet.id_chat_lieu = dbo.ChatLieu.id \n"
                + "INNER JOIN dbo.CoAo ON dbo.SanPhamChiTiet.id_co_ao = dbo.CoAo.id \n"
                + "INNER JOIN dbo.DoDay ON dbo.SanPhamChiTiet.id_do_day = dbo.DoDay.id \n"
                + "INNER JOIN dbo.TinhLinhHoat ON dbo.SanPhamChiTiet.id_tinh_linh_hoat = dbo.TinhLinhHoat.id";

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new SanPhamChiTietRespone(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getDouble(12),
                        rs.getInt(13), rs.getBoolean(14)));
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

        String sql = "insert into SanPhamChiTiet(id_san_pham,id_thuong_hieu,id_xuat_xu,id_mau_sac,id_kich_thuoc,id_chat_lieu,id_co_ao,id_do_day,id_tinh_linh_hoat,gia_ban,so_luong_ton,trang_thai)\n"
                + "values (?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, spct.getSanPhamID());
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
        String sql = "SELECT dbo.SanPhamChiTiet.id, dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, dbo.SanPham.ten_san_pham, \n"
                + "       dbo.ThuongHieu.ten_thuong_hieu, dbo.XuatXu.ten_nuoc, dbo.MauSac.ten_mau, dbo.KichThuoc.size, \n"
                + "       dbo.ChatLieu.ten_chat_lieu, dbo.CoAo.ten_co_ao, dbo.DoDay.ten_do_day, dbo.TinhLinhHoat.ten_tinh_linh_hoat, \n"
                + "       dbo.SanPhamChiTiet.gia_ban, dbo.SanPhamChiTiet.so_luong_ton, dbo.SanPhamChiTiet.trang_thai\n"
                + "FROM dbo.SanPhamChiTiet\n"
                + "INNER JOIN dbo.SanPham ON dbo.SanPhamChiTiet.id_san_pham = dbo.SanPham.id\n"
                + "INNER JOIN dbo.ThuongHieu ON dbo.SanPhamChiTiet.id_thuong_hieu = dbo.ThuongHieu.id\n"
                + "INNER JOIN dbo.XuatXu ON dbo.SanPhamChiTiet.id_xuat_xu = dbo.XuatXu.id\n"
                + "INNER JOIN dbo.MauSac ON dbo.SanPhamChiTiet.id_mau_sac = dbo.MauSac.id\n"
                + "INNER JOIN dbo.KichThuoc ON dbo.SanPhamChiTiet.id_kich_thuoc = dbo.KichThuoc.id\n"
                + "INNER JOIN dbo.ChatLieu ON dbo.SanPhamChiTiet.id_chat_lieu = dbo.ChatLieu.id\n"
                + "INNER JOIN dbo.CoAo ON dbo.SanPhamChiTiet.id_co_ao = dbo.CoAo.id\n"
                + "INNER JOIN dbo.DoDay ON dbo.SanPhamChiTiet.id_do_day = dbo.DoDay.id\n"
                + "INNER JOIN dbo.TinhLinhHoat ON dbo.SanPhamChiTiet.id_tinh_linh_hoat = dbo.TinhLinhHoat.id\n"
                + "WHERE dbo.SanPhamChiTiet.ma_san_pham_chi_tiet LIKE ?\n"
                + "  OR dbo.SanPham.ten_san_pham LIKE ?\n"
                + "  OR dbo.ThuongHieu.ten_thuong_hieu LIKE ?\n"
                + "  OR dbo.XuatXu.ten_nuoc LIKE ?\n"
                + "  OR dbo.MauSac.ten_mau LIKE ?\n"
                + "  OR dbo.KichThuoc.size LIKE ?\n"
                + "  OR dbo.ChatLieu.ten_chat_lieu LIKE ?\n"
                + "  OR dbo.CoAo.ten_co_ao LIKE ?\n"
                + "  OR dbo.DoDay.ten_do_day LIKE ?\n"
                + "  OR dbo.TinhLinhHoat.ten_tinh_linh_hoat LIKE ?\n"
                + "  OR dbo.SanPhamChiTiet.gia_ban LIKE ?\n"
                + "  OR dbo.SanPhamChiTiet.so_luong_ton LIKE ?\n"
                + "  OR dbo.SanPhamChiTiet.trang_thai LIKE ?";

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
                String ten_san_pham = rs.getString("ten_san_pham");
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

                SanPhamChiTietRespone sanPham = new SanPhamChiTietRespone(id, ma_san_pham_chi_tiet, ten_san_pham, thuong_hieu, ten_nuoc, ten_mau, size, ten_chat_lieu, ten_co_ao, ten_do_day, ten_tinh_linh_hoat, giaBan, so_luong_ton, true);
                lists.add(sanPham);
            }
        } catch (Exception e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
            e.printStackTrace(); // In ra stack trace để xem chi tiết lỗi
        }
        return lists;
    }

    public ArrayList<SanPhamChiTietRespone> getAllGiamDan() {
        String sql = "SELECT dbo.SanPhamChiTiet.id, \n"
                + "       dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, \n"
                + "       dbo.SanPham.ten_san_pham, \n"
                + "       dbo.ThuongHieu.ten_thuong_hieu, \n"
                + "       dbo.XuatXu.ten_nuoc, \n"
                + "       dbo.MauSac.ten_mau, \n"
                + "       dbo.KichThuoc.size, \n"
                + "       dbo.ChatLieu.ten_chat_lieu, \n"
                + "       dbo.CoAo.ten_co_ao, \n"
                + "       dbo.DoDay.ten_do_day, \n"
                + "       dbo.TinhLinhHoat.ten_tinh_linh_hoat, \n"
                + "       dbo.SanPhamChiTiet.gia_ban, \n"
                + "       dbo.SanPhamChiTiet.so_luong_ton, \n"
                + "       dbo.SanPhamChiTiet.trang_thai \n"
                + "FROM dbo.SanPhamChiTiet \n"
                + "INNER JOIN dbo.SanPham ON dbo.SanPhamChiTiet.id_san_pham = dbo.SanPham.id\n"
                + "INNER JOIN dbo.ThuongHieu ON dbo.SanPhamChiTiet.id_thuong_hieu = dbo.ThuongHieu.id \n"
                + "INNER JOIN dbo.XuatXu ON dbo.SanPhamChiTiet.id_xuat_xu = dbo.XuatXu.id \n"
                + "INNER JOIN dbo.MauSac ON dbo.SanPhamChiTiet.id_mau_sac = dbo.MauSac.id \n"
                + "INNER JOIN dbo.KichThuoc ON dbo.SanPhamChiTiet.id_kich_thuoc = dbo.KichThuoc.id \n"
                + "INNER JOIN dbo.ChatLieu ON dbo.SanPhamChiTiet.id_chat_lieu = dbo.ChatLieu.id \n"
                + "INNER JOIN dbo.CoAo ON dbo.SanPhamChiTiet.id_co_ao = dbo.CoAo.id \n"
                + "INNER JOIN dbo.DoDay ON dbo.SanPhamChiTiet.id_do_day = dbo.DoDay.id \n"
                + "INNER JOIN dbo.TinhLinhHoat ON dbo.SanPhamChiTiet.id_tinh_linh_hoat = dbo.TinhLinhHoat.id \n"
                + "ORDER BY dbo.SanPhamChiTiet.ngay_tao DESC";

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new SanPhamChiTietRespone(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getDouble(12),
                        rs.getInt(13), rs.getBoolean(14)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    public ArrayList<SanPhamChiTietRespone> locTheoTenSanPham(String tenSanPham) {
        String sql = "SELECT dbo.SanPhamChiTiet.id, \n"
                + "       dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, \n"
                + "       dbo.SanPham.ten_san_pham, \n"
                + "       dbo.ThuongHieu.ten_thuong_hieu, \n"
                + "       dbo.XuatXu.ten_nuoc, \n"
                + "       dbo.MauSac.ten_mau, \n"
                + "       dbo.KichThuoc.size, \n"
                + "       dbo.ChatLieu.ten_chat_lieu, \n"
                + "       dbo.CoAo.ten_co_ao, \n"
                + "       dbo.DoDay.ten_do_day, \n"
                + "       dbo.TinhLinhHoat.ten_tinh_linh_hoat, \n"
                + "       dbo.SanPhamChiTiet.gia_ban, \n"
                + "       dbo.SanPhamChiTiet.so_luong_ton, \n"
                + "       dbo.SanPhamChiTiet.trang_thai \n"
                + "FROM dbo.SanPhamChiTiet \n"
                + "INNER JOIN dbo.SanPham ON dbo.SanPhamChiTiet.id_san_pham = dbo.SanPham.id\n"
                + "INNER JOIN dbo.ThuongHieu ON dbo.SanPhamChiTiet.id_thuong_hieu = dbo.ThuongHieu.id \n"
                + "INNER JOIN dbo.XuatXu ON dbo.SanPhamChiTiet.id_xuat_xu = dbo.XuatXu.id \n"
                + "INNER JOIN dbo.MauSac ON dbo.SanPhamChiTiet.id_mau_sac = dbo.MauSac.id \n"
                + "INNER JOIN dbo.KichThuoc ON dbo.SanPhamChiTiet.id_kich_thuoc = dbo.KichThuoc.id \n"
                + "INNER JOIN dbo.ChatLieu ON dbo.SanPhamChiTiet.id_chat_lieu = dbo.ChatLieu.id \n"
                + "INNER JOIN dbo.CoAo ON dbo.SanPhamChiTiet.id_co_ao = dbo.CoAo.id \n"
                + "INNER JOIN dbo.DoDay ON dbo.SanPhamChiTiet.id_do_day = dbo.DoDay.id \n"
                + "INNER JOIN dbo.TinhLinhHoat ON dbo.SanPhamChiTiet.id_tinh_linh_hoat = dbo.TinhLinhHoat.id \n"
                + "WHERE dbo.SanPham.ten_san_pham LIKE ?"; // Thêm điều kiện WHERE để lọc theo tên sản phẩm

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + tenSanPham + "%"); // Thay đổi tham số đầu vào cho LIKE
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new SanPhamChiTietRespone(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getDouble(12),
                        rs.getInt(13), rs.getBoolean(14)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    public ArrayList<SanPhamChiTietRespone> locTheoTenTinhLinhHoat(String tenTinhLinhHoat) {
        String sql = "SELECT dbo.SanPhamChiTiet.id, \n"
                + "       dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, \n"
                + "       dbo.SanPham.ten_san_pham, \n"
                + "       dbo.ThuongHieu.ten_thuong_hieu, \n"
                + "       dbo.XuatXu.ten_nuoc, \n"
                + "       dbo.MauSac.ten_mau, \n"
                + "       dbo.KichThuoc.size, \n"
                + "       dbo.ChatLieu.ten_chat_lieu, \n"
                + "       dbo.CoAo.ten_co_ao, \n"
                + "       dbo.DoDay.ten_do_day, \n"
                + "       dbo.TinhLinhHoat.ten_tinh_linh_hoat, \n"
                + "       dbo.SanPhamChiTiet.gia_ban, \n"
                + "       dbo.SanPhamChiTiet.so_luong_ton, \n"
                + "       dbo.SanPhamChiTiet.trang_thai \n"
                + "FROM dbo.SanPhamChiTiet \n"
                + "INNER JOIN dbo.SanPham ON dbo.SanPhamChiTiet.id_san_pham = dbo.SanPham.id\n"
                + "INNER JOIN dbo.ThuongHieu ON dbo.SanPhamChiTiet.id_thuong_hieu = dbo.ThuongHieu.id \n"
                + "INNER JOIN dbo.XuatXu ON dbo.SanPhamChiTiet.id_xuat_xu = dbo.XuatXu.id \n"
                + "INNER JOIN dbo.MauSac ON dbo.SanPhamChiTiet.id_mau_sac = dbo.MauSac.id \n"
                + "INNER JOIN dbo.KichThuoc ON dbo.SanPhamChiTiet.id_kich_thuoc = dbo.KichThuoc.id \n"
                + "INNER JOIN dbo.ChatLieu ON dbo.SanPhamChiTiet.id_chat_lieu = dbo.ChatLieu.id \n"
                + "INNER JOIN dbo.CoAo ON dbo.SanPhamChiTiet.id_co_ao = dbo.CoAo.id \n"
                + "INNER JOIN dbo.DoDay ON dbo.SanPhamChiTiet.id_do_day = dbo.DoDay.id \n"
                + "INNER JOIN dbo.TinhLinhHoat ON dbo.SanPhamChiTiet.id_tinh_linh_hoat = dbo.TinhLinhHoat.id \n"
                + "WHERE dbo.TinhLinhHoat.ten_tinh_linh_hoat LIKE ?"; // Thêm điều kiện WHERE để lọc theo tên tính linh hoạt

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + tenTinhLinhHoat + "%"); // Thay đổi tham số đầu vào cho LIKE
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new SanPhamChiTietRespone(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getDouble(12),
                        rs.getInt(13), rs.getBoolean(14)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    public ArrayList<SanPhamChiTietRespone> locTheoTenThuongHieu(String tenThuongHieu) {
        String sql = "SELECT dbo.SanPhamChiTiet.id, \n"
                + "       dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, \n"
                + "       dbo.SanPham.ten_san_pham, \n"
                + "       dbo.ThuongHieu.ten_thuong_hieu, \n"
                + "       dbo.XuatXu.ten_nuoc, \n"
                + "       dbo.MauSac.ten_mau, \n"
                + "       dbo.KichThuoc.size, \n"
                + "       dbo.ChatLieu.ten_chat_lieu, \n"
                + "       dbo.CoAo.ten_co_ao, \n"
                + "       dbo.DoDay.ten_do_day, \n"
                + "       dbo.TinhLinhHoat.ten_tinh_linh_hoat, \n"
                + "       dbo.SanPhamChiTiet.gia_ban, \n"
                + "       dbo.SanPhamChiTiet.so_luong_ton, \n"
                + "       dbo.SanPhamChiTiet.trang_thai \n"
                + "FROM dbo.SanPhamChiTiet \n"
                + "INNER JOIN dbo.SanPham ON dbo.SanPhamChiTiet.id_san_pham = dbo.SanPham.id\n"
                + "INNER JOIN dbo.ThuongHieu ON dbo.SanPhamChiTiet.id_thuong_hieu = dbo.ThuongHieu.id \n"
                + "INNER JOIN dbo.XuatXu ON dbo.SanPhamChiTiet.id_xuat_xu = dbo.XuatXu.id \n"
                + "INNER JOIN dbo.MauSac ON dbo.SanPhamChiTiet.id_mau_sac = dbo.MauSac.id \n"
                + "INNER JOIN dbo.KichThuoc ON dbo.SanPhamChiTiet.id_kich_thuoc = dbo.KichThuoc.id \n"
                + "INNER JOIN dbo.ChatLieu ON dbo.SanPhamChiTiet.id_chat_lieu = dbo.ChatLieu.id \n"
                + "INNER JOIN dbo.CoAo ON dbo.SanPhamChiTiet.id_co_ao = dbo.CoAo.id \n"
                + "INNER JOIN dbo.DoDay ON dbo.SanPhamChiTiet.id_do_day = dbo.DoDay.id \n"
                + "INNER JOIN dbo.TinhLinhHoat ON dbo.SanPhamChiTiet.id_tinh_linh_hoat = dbo.TinhLinhHoat.id \n"
                + "WHERE dbo.ThuongHieu.ten_thuong_hieu LIKE ?"; // Thêm điều kiện WHERE để lọc theo tên thương hiệu

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + tenThuongHieu + "%"); // Thay đổi tham số đầu vào cho LIKE
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new SanPhamChiTietRespone(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getDouble(12),
                        rs.getInt(13), rs.getBoolean(14)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    public ArrayList<SanPhamChiTietRespone> locTheoTenXuatXu(String xuatXu) {
        String sql = "SELECT dbo.SanPhamChiTiet.id, \n"
                + "       dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, \n"
                + "       dbo.SanPham.ten_san_pham, \n"
                + "       dbo.ThuongHieu.ten_thuong_hieu, \n"
                + "       dbo.XuatXu.ten_nuoc, \n"
                + "       dbo.MauSac.ten_mau, \n"
                + "       dbo.KichThuoc.size, \n"
                + "       dbo.ChatLieu.ten_chat_lieu, \n"
                + "       dbo.CoAo.ten_co_ao, \n"
                + "       dbo.DoDay.ten_do_day, \n"
                + "       dbo.TinhLinhHoat.ten_tinh_linh_hoat, \n"
                + "       dbo.SanPhamChiTiet.gia_ban, \n"
                + "       dbo.SanPhamChiTiet.so_luong_ton, \n"
                + "       dbo.SanPhamChiTiet.trang_thai \n"
                + "FROM dbo.SanPhamChiTiet \n"
                + "INNER JOIN dbo.SanPham ON dbo.SanPhamChiTiet.id_san_pham = dbo.SanPham.id\n"
                + "INNER JOIN dbo.ThuongHieu ON dbo.SanPhamChiTiet.id_thuong_hieu = dbo.ThuongHieu.id \n"
                + "INNER JOIN dbo.XuatXu ON dbo.SanPhamChiTiet.id_xuat_xu = dbo.XuatXu.id \n"
                + "INNER JOIN dbo.MauSac ON dbo.SanPhamChiTiet.id_mau_sac = dbo.MauSac.id \n"
                + "INNER JOIN dbo.KichThuoc ON dbo.SanPhamChiTiet.id_kich_thuoc = dbo.KichThuoc.id \n"
                + "INNER JOIN dbo.ChatLieu ON dbo.SanPhamChiTiet.id_chat_lieu = dbo.ChatLieu.id \n"
                + "INNER JOIN dbo.CoAo ON dbo.SanPhamChiTiet.id_co_ao = dbo.CoAo.id \n"
                + "INNER JOIN dbo.DoDay ON dbo.SanPhamChiTiet.id_do_day = dbo.DoDay.id \n"
                + "INNER JOIN dbo.TinhLinhHoat ON dbo.SanPhamChiTiet.id_tinh_linh_hoat = dbo.TinhLinhHoat.id \n"
                + "WHERE dbo.XuatXu.ten_nuoc LIKE ?"; // Thêm điều kiện WHERE để lọc theo xuất xứ

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + xuatXu + "%"); // Thay đổi tham số đầu vào cho LIKE
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new SanPhamChiTietRespone(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getDouble(12),
                        rs.getInt(13), rs.getBoolean(14)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }
    
    public ArrayList<SanPhamChiTietRespone> giaTangDan() {
    String sql = "SELECT dbo.SanPhamChiTiet.id, \n"
            + "       dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, \n"
            + "       dbo.SanPham.ten_san_pham, \n"
            + "       dbo.ThuongHieu.ten_thuong_hieu, \n"
            + "       dbo.XuatXu.ten_nuoc, \n"
            + "       dbo.MauSac.ten_mau, \n"
            + "       dbo.KichThuoc.size, \n"
            + "       dbo.ChatLieu.ten_chat_lieu, \n"
            + "       dbo.CoAo.ten_co_ao, \n"
            + "       dbo.DoDay.ten_do_day, \n"
            + "       dbo.TinhLinhHoat.ten_tinh_linh_hoat, \n"
            + "       dbo.SanPhamChiTiet.gia_ban, \n"
            + "       dbo.SanPhamChiTiet.so_luong_ton, \n"
            + "       dbo.SanPhamChiTiet.trang_thai \n"
            + "FROM dbo.SanPhamChiTiet \n"
            + "INNER JOIN dbo.SanPham ON dbo.SanPhamChiTiet.id_san_pham = dbo.SanPham.id\n"
            + "INNER JOIN dbo.ThuongHieu ON dbo.SanPhamChiTiet.id_thuong_hieu = dbo.ThuongHieu.id \n"
            + "INNER JOIN dbo.XuatXu ON dbo.SanPhamChiTiet.id_xuat_xu = dbo.XuatXu.id \n"
            + "INNER JOIN dbo.MauSac ON dbo.SanPhamChiTiet.id_mau_sac = dbo.MauSac.id \n"
            + "INNER JOIN dbo.KichThuoc ON dbo.SanPhamChiTiet.id_kich_thuoc = dbo.KichThuoc.id \n"
            + "INNER JOIN dbo.ChatLieu ON dbo.SanPhamChiTiet.id_chat_lieu = dbo.ChatLieu.id \n"
            + "INNER JOIN dbo.CoAo ON dbo.SanPhamChiTiet.id_co_ao = dbo.CoAo.id \n"
            + "INNER JOIN dbo.DoDay ON dbo.SanPhamChiTiet.id_do_day = dbo.DoDay.id \n"
            + "INNER JOIN dbo.TinhLinhHoat ON dbo.SanPhamChiTiet.id_tinh_linh_hoat = dbo.TinhLinhHoat.id \n"
            + "ORDER BY dbo.SanPhamChiTiet.gia_ban ASC"; // Sắp xếp theo giá bán từ thấp đến cao

    ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
    try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lists.add(new SanPhamChiTietRespone(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                    rs.getString(9), rs.getString(10), rs.getString(11), rs.getDouble(12),
                    rs.getInt(13), rs.getBoolean(14)));
        }
    } catch (Exception e) {
        e.printStackTrace(System.out); // nem loi khi xay ra 
    }
    return lists;
}
    
    public ArrayList<SanPhamChiTietRespone> giaGiamDan() {
    String sql = "SELECT dbo.SanPhamChiTiet.id, \n"
            + "       dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, \n"
            + "       dbo.SanPham.ten_san_pham, \n"
            + "       dbo.ThuongHieu.ten_thuong_hieu, \n"
            + "       dbo.XuatXu.ten_nuoc, \n"
            + "       dbo.MauSac.ten_mau, \n"
            + "       dbo.KichThuoc.size, \n"
            + "       dbo.ChatLieu.ten_chat_lieu, \n"
            + "       dbo.CoAo.ten_co_ao, \n"
            + "       dbo.DoDay.ten_do_day, \n"
            + "       dbo.TinhLinhHoat.ten_tinh_linh_hoat, \n"
            + "       dbo.SanPhamChiTiet.gia_ban, \n"
            + "       dbo.SanPhamChiTiet.so_luong_ton, \n"
            + "       dbo.SanPhamChiTiet.trang_thai \n"
            + "FROM dbo.SanPhamChiTiet \n"
            + "INNER JOIN dbo.SanPham ON dbo.SanPhamChiTiet.id_san_pham = dbo.SanPham.id\n"
            + "INNER JOIN dbo.ThuongHieu ON dbo.SanPhamChiTiet.id_thuong_hieu = dbo.ThuongHieu.id \n"
            + "INNER JOIN dbo.XuatXu ON dbo.SanPhamChiTiet.id_xuat_xu = dbo.XuatXu.id \n"
            + "INNER JOIN dbo.MauSac ON dbo.SanPhamChiTiet.id_mau_sac = dbo.MauSac.id \n"
            + "INNER JOIN dbo.KichThuoc ON dbo.SanPhamChiTiet.id_kich_thuoc = dbo.KichThuoc.id \n"
            + "INNER JOIN dbo.ChatLieu ON dbo.SanPhamChiTiet.id_chat_lieu = dbo.ChatLieu.id \n"
            + "INNER JOIN dbo.CoAo ON dbo.SanPhamChiTiet.id_co_ao = dbo.CoAo.id \n"
            + "INNER JOIN dbo.DoDay ON dbo.SanPhamChiTiet.id_do_day = dbo.DoDay.id \n"
            + "INNER JOIN dbo.TinhLinhHoat ON dbo.SanPhamChiTiet.id_tinh_linh_hoat = dbo.TinhLinhHoat.id \n"
            + "ORDER BY dbo.SanPhamChiTiet.gia_ban DESC"; // Sắp xếp theo giá bán giảm dần

    ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
    try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lists.add(new SanPhamChiTietRespone(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                    rs.getString(9), rs.getString(10), rs.getString(11), rs.getDouble(12),
                    rs.getInt(13), rs.getBoolean(14)));
        }
    } catch (Exception e) {
        e.printStackTrace(System.out); // nem loi khi xay ra 
    }
    return lists;
}



}
