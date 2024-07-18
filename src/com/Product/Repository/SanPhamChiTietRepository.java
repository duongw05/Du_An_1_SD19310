/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Product.Repository;

import com.Product.config.DBConnect;
import com.Product.form.NhanVienForm;
import com.Product.respone.SanPhamChiTietRespone;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class SanPhamChiTietRepository {
    public ArrayList<SanPhamChiTietRespone> getAll(){
              String sql ="SELECT  dbo.SanPhamChiTiet.id, dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, dbo.SanPhamChiTiet.ten_san_pham, dbo.ThuongHieu.ten_thuong_hieu, dbo.XuatXu.ten_nuoc, dbo.MauSac.ten_mau, dbo.KichThuoc.size, dbo.ChatLieu.ten_chat_lieu, \n" +
"                 dbo.CoAo.ten_co_ao, dbo.DoDay.ten_do_day, dbo.TinhLinhHoat.ten_tinh_linh_hoat, dbo.SanPhamChiTiet.gia_ban, dbo.SanPhamChiTiet.so_luong_ton, dbo.SanPhamChiTiet.trang_thai\n" +
"FROM      dbo.SanPhamChiTiet INNER JOIN\n" +
"                 dbo.ThuongHieu ON dbo.SanPhamChiTiet.id_thuong_hieu = dbo.ThuongHieu.id INNER JOIN\n" +
"                 dbo.XuatXu ON dbo.SanPhamChiTiet.id_xuat_xu = dbo.XuatXu.id INNER JOIN\n" +
"                 dbo.MauSac ON dbo.SanPhamChiTiet.id_mau_sac = dbo.MauSac.id INNER JOIN\n" +
"                 dbo.KichThuoc ON dbo.SanPhamChiTiet.id_kich_thuoc = dbo.KichThuoc.id INNER JOIN\n" +
"                 dbo.ChatLieu ON dbo.SanPhamChiTiet.id_chat_lieu = dbo.ChatLieu.id INNER JOIN\n" +
"                 dbo.CoAo ON dbo.SanPhamChiTiet.id_co_ao = dbo.CoAo.id INNER JOIN\n" +
"                 dbo.DoDay ON dbo.SanPhamChiTiet.id_do_day = dbo.DoDay.id INNER JOIN\n" +
"                 dbo.TinhLinhHoat ON dbo.SanPhamChiTiet.id_tinh_linh_hoat = dbo.TinhLinhHoat.id"
                          ;
        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new SanPhamChiTietRespone(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6),rs.getString(7), rs.getString(8)
                        , rs.getString(9), rs.getString(10), rs.getString(11), rs.getDouble(12)
                        , rs.getInt(13), rs.getBoolean(14)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }
    }

