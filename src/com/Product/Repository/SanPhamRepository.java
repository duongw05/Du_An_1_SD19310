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
    public ArrayList<SanPham> getAll(){
        ArrayList<SanPham> list = new ArrayList<>();
        String sql ="select id,ma_san_pham,ten_san_pham,mo_ta,trang_thai,ngay_tao\n" +
"from SanPham";
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new SanPham(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getBoolean(5), rs.getDate(6)));
            }
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }
    
    public ArrayList<SanPham> getSanPhamDangBan(){
        ArrayList<SanPham> list = new ArrayList<>();
        String sql ="select id,ma_san_pham,ten_san_pham,mo_ta,trang_thai,ngay_tao\n" +
"from SanPham\n" +
"where trang_thai = 1";
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new SanPham(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getBoolean(5), rs.getDate(6)));
            }
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }
    
    public ArrayList<SanPham> getSanPhamNgungBan(){
        ArrayList<SanPham> list = new ArrayList<>();
        String sql ="select id,ma_san_pham,ten_san_pham,mo_ta,trang_thai,ngay_tao\n" +
"from SanPham\n" +
"where trang_thai = 0";
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new SanPham(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getBoolean(5), rs.getDate(6)));
            }
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }
    
    public boolean add(SanPham sanpham){
        int check = 0;

        String sql ="insert into SanPham(ten_san_pham,mo_ta)\n" +
"values(?,?)";
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
    
     public ArrayList<SanPham> getAllGiamDan(){
        ArrayList<SanPham> list = new ArrayList<>();
        String sql ="select id,ma_san_pham,ten_san_pham,mo_ta,trang_thai,ngay_tao\n" +
"from SanPham\n" +
"ORDER BY ngay_tao desc";
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new SanPham(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getBoolean(5), rs.getDate(6)));
            }
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }
    
    public boolean update(SanPham newSanPham, Integer id) {
        int check = 0;
        String sql = "update SanPham\n" +
"set ten_san_pham=?,mo_ta=?\n" +
"where id =?";
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
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
        String sql = "update SanPham \n" +
"set trang_thai=0\n" +
"where id=?";
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
}
