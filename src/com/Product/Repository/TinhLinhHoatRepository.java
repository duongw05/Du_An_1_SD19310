/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Product.Repository;

import com.Product.config.DBConnect;
import com.Product.entity.TinhLinhHoat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class TinhLinhHoatRepository {

    public ArrayList<TinhLinhHoat> getAll() {
        ArrayList<TinhLinhHoat> list = new ArrayList<>();
        String sql = "SELECT id, ma_tinh_linh_hoat, ten_tinh_linh_hoat\n"
                + "FROM TinhLinhHoat\n"
                + "WHERE trang_thai = 1\n"
                + "ORDER BY id DESC";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TinhLinhHoat(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public TinhLinhHoat getTinhLinhHoatByTen(String ma1) {
        String query = "select id,ma_tinh_linh_hoat,ten_tinh_linh_hoat from TinhLinhHoat\n"
                + "where ten_tinh_linh_hoat=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            // Set gia tri cho dau hoi cham 
            ps.setObject(1, ma1);
            ResultSet rs = ps.executeQuery(); // Lay ket qua

            while (rs.next()) {
                TinhLinhHoat cv = new TinhLinhHoat(rs.getInt(1), rs.getString(2), rs.getString(3));
                return cv;
            }
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean isTinhLinhHoatExist(String ten) {
        String sql = "SELECT COUNT(*) FROM TinhLinhHoat WHERE ten_tinh_linh_hoat=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ten);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
