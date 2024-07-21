/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Product.Repository;

import com.Product.config.DBConnect;
import com.Product.entity.SanPham;
import com.Product.entity.SanPhamChiTiet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class SanPhamRepository {

    public ArrayList<SanPham> getAll() {
        ArrayList<SanPham> list = new ArrayList<>();
        String sql = "select id,ma_san_pham,ten_san_pham,mo_ta,so_luong,trang_thai,ngay_tao from SanPham";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SanPham(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getBoolean(6), rs.getDate(7)));
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public ArrayList<SanPham> getSanPhamDangBan() {
        ArrayList<SanPham> list = new ArrayList<>();
        String sql = "select id,ma_san_pham,ten_san_pham,mo_ta,so_luong,trang_thai,ngay_tao\n"
                + "from SanPham\n"
                + "where trang_thai = 1";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SanPham(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getBoolean(6), rs.getDate(7)));
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public ArrayList<SanPham> getSanPhamNgungBan() {
        ArrayList<SanPham> list = new ArrayList<>();
        String sql = "select id,ma_san_pham,ten_san_pham,mo_ta,so_luong,trang_thai,ngay_tao\n"
                + "from SanPham\n"
                + "where trang_thai = 0";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SanPham(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getBoolean(6), rs.getDate(7)));
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public boolean add(SanPham sanpham) {
        int check = 0;

        String sql = "insert into SanPham(ten_san_pham,mo_ta)\n"
                + "values(?,?)";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Object la cha cua cac loai kieu du lieu 
            ps.setObject(1, sanpham.getTenSanPham());
            ps.setObject(2, sanpham.getMoTa());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return check > 0;
    }

    public ArrayList<SanPham> getAllGiamDan() {
        ArrayList<SanPham> list = new ArrayList<>();
        String sql = "select id,ma_san_pham,ten_san_pham,mo_ta,so_luong,trang_thai,ngay_tao\n"
                + "from SanPham\n"
                + "ORDER BY ngay_tao desc";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SanPham(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getBoolean(6), rs.getDate(7)));
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public boolean update(SanPham newSanPham, Integer id) {
        int check = 0;
        String sql = "update SanPham\n"
                + "set ten_san_pham=?,mo_ta=?\n"
                + "where id =?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, newSanPham.getTenSanPham());
            ps.setObject(2, newSanPham.getMoTa());
            ps.setObject(3, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean remove(Integer id) {
        int check = 0;
        String sql = "update SanPham \n"
                + "set trang_thai=0\n"
                + "where id=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public ArrayList<SanPham> searchh(String maSP) {
        String sql = "SELECT id, ma_san_pham, ten_san_pham, mo_ta, so_luong, trang_thai, ngay_tao "
                + "FROM SanPham "
                + "WHERE ma_san_pham LIKE ? OR ten_san_pham LIKE ? OR mo_ta LIKE ? OR trang_thai LIKE ? OR ngay_tao LIKE ?";
        ;

        ArrayList<SanPham> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            String searchString = "%" + maSP + "%"; // Thêm % vào để tạo thành mô phỏng tìm kiếm
            // Đặt giá trị cho các tham số trong câu truy vấn SQL
            for (int i = 1; i <= 5; i++) {
                ps.setString(i, searchString);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ma_san_pham = rs.getString("ma_san_pham");
                String ten_san_pham = rs.getString("ten_san_pham");
                String mo_ta = rs.getString("mo_ta");
                Integer so_luong = rs.getInt("so_luong");
                Boolean trang_thai = rs.getBoolean("trang_thai");
                Date ngay_tao = rs.getDate("ngay_tao");

                SanPham sanPham = new SanPham(id, ma_san_pham, ten_san_pham, mo_ta, so_luong, true, ngay_tao);
                lists.add(sanPham);
            }
        } catch (Exception e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
            e.printStackTrace(); // In ra stack trace để xem chi tiết lỗi
        }
        return lists;
    }

    public SanPham getSanPhamByTen(String ma1) {
        String query = "select  id,ma_san_pham,ten_san_pham,so_luong,trang_thai,mo_ta,ngay_tao from SanPham\n"
                + "where ten_san_pham =  ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            // Set gia tri cho dau hoi cham 
            ps.setObject(1, ma1);
            ResultSet rs = ps.executeQuery(); // Lay ket qua

            while (rs.next()) {
                SanPham cv = new SanPham(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getBoolean(6), rs.getDate(7));
                return cv;
            }
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean updateSoLuong(SanPham sp) {
        int check = 0;
        String sql = "update SanPham\n"
                + "set so_luong =?\n"
                + "where id=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, sp.getSoLuong());
            ps.setObject(2, sp.getId());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;

    }
}
