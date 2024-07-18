/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Product.Repository;

import com.Product.config.DBConnect;
import com.Product.entity.ThuocTinhSanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ThuocTinhSanPhamRepository {
    public ArrayList<ThuocTinhSanPham> getAll(){
        ArrayList<ThuocTinhSanPham> list = new ArrayList<>();
        String sql =  "SELECT ma_chat_lieu, ten_chat_lieu FROM ChatLieu UNION ALL " +
                       "SELECT ma_co_ao, ten_co_ao FROM CoAo UNION ALL " +
                       "SELECT ma_do_day, ten_do_day FROM DoDay UNION ALL " +
                       "SELECT ma_kich_thuoc, size FROM KichThuoc UNION ALL " +
                       "SELECT ma_mau_sac, ten_mau FROM MauSac UNION ALL " +
                       "SELECT ma_thuong_hieu, ten_thuong_hieu FROM ThuongHieu UNION ALL " +
                       "SELECT ma_tinh_linh_hoat, ten_tinh_linh_hoat FROM TinhLinhHoat UNION ALL " +
                       "SELECT ma_xuat_xu, ten_nuoc FROM XuatXu";
        try (Connection conn = DBConnect.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new ThuocTinhSanPham(rs.getString(1), rs.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }
    
    public ArrayList<ThuocTinhSanPham> getChatLieu(){
        ArrayList<ThuocTinhSanPham> list = new ArrayList<>();
        String sql =  "SELECT ma_chat_lieu, ten_chat_lieu FROM ChatLieu";
        try (Connection conn = DBConnect.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new ThuocTinhSanPham(rs.getString(1), rs.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }
    
    public ArrayList<ThuocTinhSanPham> getCoAO(){
        ArrayList<ThuocTinhSanPham> list = new ArrayList<>();
        String sql =  "SELECT ma_co_ao, ten_co_ao FROM CoAo";
        try (Connection conn = DBConnect.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new ThuocTinhSanPham(rs.getString(1), rs.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }
    
    public ArrayList<ThuocTinhSanPham> getDoDay(){
        ArrayList<ThuocTinhSanPham> list = new ArrayList<>();
        String sql =  "SELECT ma_do_day, ten_do_day FROM DoDay";
        try (Connection conn = DBConnect.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new ThuocTinhSanPham(rs.getString(1), rs.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }
    
    public ArrayList<ThuocTinhSanPham> getKichThuoc(){
        ArrayList<ThuocTinhSanPham> list = new ArrayList<>();
        String sql =  "SELECT ma_kich_thuoc, size FROM KichThuoc";
        try (Connection conn = DBConnect.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new ThuocTinhSanPham(rs.getString(1), rs.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }
    public ArrayList<ThuocTinhSanPham> getMauSac(){
        ArrayList<ThuocTinhSanPham> list = new ArrayList<>();
        String sql =  "SELECT ma_mau_sac, ten_mau FROM MauSac";
        try (Connection conn = DBConnect.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new ThuocTinhSanPham(rs.getString(1), rs.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }
    public ArrayList<ThuocTinhSanPham> getThuongHieu(){
        ArrayList<ThuocTinhSanPham> list = new ArrayList<>();
        String sql =  "SELECT ma_thuong_hieu, ten_thuong_hieu FROM ThuongHieu";
        try (Connection conn = DBConnect.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new ThuocTinhSanPham(rs.getString(1), rs.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }
    
    public ArrayList<ThuocTinhSanPham> getTinhLinhHoat(){
        ArrayList<ThuocTinhSanPham> list = new ArrayList<>();
        String sql =  "SELECT ma_tinh_linh_hoat, ten_tinh_linh_hoat FROM TinhLinhHoat";
        try (Connection conn = DBConnect.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new ThuocTinhSanPham(rs.getString(1), rs.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }
    
    public ArrayList<ThuocTinhSanPham> getXuatXu(){
        ArrayList<ThuocTinhSanPham> list = new ArrayList<>();
        String sql =  "SELECT ma_xuat_xu, ten_nuoc FROM XuatXu";
        try (Connection conn = DBConnect.getConnection();PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new ThuocTinhSanPham(rs.getString(1), rs.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }
    
     public boolean insertCoAo(ThuocTinhSanPham ttsp) {
        int check = 0;
        String sql = "insert into CoAo(ten_co_ao)\n" +
"values(?)";
        // KHI THEM 1 BAN GHI MOI => DEFAULT LA TRANG THAI = 1 
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ttsp.getTenThuocTinhSanPham());
           
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
     public boolean insertChatLieu(ThuocTinhSanPham ttsp) {
        int check = 0;
        String sql ="insert into ChatLieu(ten_chat_lieu)\n" +
"values(?)";
        // KHI THEM 1 BAN GHI MOI => DEFAULT LA TRANG THAI = 1 
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ttsp.getTenThuocTinhSanPham());
           
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
     
     public boolean insertThuongHieu(ThuocTinhSanPham ttsp) {
        int check = 0;
        String sql ="insert into ThuongHieu(ten_thuong_hieu)\n" +
"values(?)";
        // KHI THEM 1 BAN GHI MOI => DEFAULT LA TRANG THAI = 1 
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ttsp.getTenThuocTinhSanPham());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
     
     public boolean insertKichThuoc(ThuocTinhSanPham ttsp) {
        int check = 0;
        String sql ="insert into KichThuoc(size)\n" +
"values(?)";
        // KHI THEM 1 BAN GHI MOI => DEFAULT LA TRANG THAI = 1 
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ttsp.getTenThuocTinhSanPham());
           
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
     
      public boolean insertMauSac(ThuocTinhSanPham ttsp) {
        int check = 0;
        String sql ="insert into MauSac(ten_mau)\n" +
"values(?)";
        // KHI THEM 1 BAN GHI MOI => DEFAULT LA TRANG THAI = 1 
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ttsp.getTenThuocTinhSanPham());
           
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
      
      public boolean insertDoDay(ThuocTinhSanPham ttsp) {
        int check = 0;
        String sql ="insert into DoDay(ten_do_day)\n" +
"values(?)";
        // KHI THEM 1 BAN GHI MOI => DEFAULT LA TRANG THAI = 1 
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ttsp.getTenThuocTinhSanPham());
           
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
      
      public boolean insertPhongCach(ThuocTinhSanPham ttsp) {
        int check = 0;
        String sql ="insert into TinhLinhHoat(ten_tinh_linh_hoat)\n" +
"values(?)";
        // KHI THEM 1 BAN GHI MOI => DEFAULT LA TRANG THAI = 1 
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ttsp.getTenThuocTinhSanPham());
           
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
      
      public boolean insertXuatXu(ThuocTinhSanPham ttsp) {
        int check = 0;
        String sql ="insert into XuatXu(ten_nuoc)\n" +
"values(?)";
        // KHI THEM 1 BAN GHI MOI => DEFAULT LA TRANG THAI = 1 
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ttsp.getTenThuocTinhSanPham());
           
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
      
//     public boolean update(ThuocTinhSanPham ttsp, Integer id) {
//        int check = 0;
//        String sql = "update SanPham\n" +
//"set ten_san_pham=?,mo_ta=?\n" +
//"where id =?";
//        try (Connection con = DBConnect.getConnection();
//                PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setObject(1, ttsp.getTenThuocTinhSanPham());
//            ps.setObject(2, ttsp.);
//            ps.setObject(3, id);
//            check = ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        }
//        return check > 0;
//    }
}

    
