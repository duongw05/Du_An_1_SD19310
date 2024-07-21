/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Product.Repository;
import com.Product.config.DBConnect;
import com.Product.entity.KichThuoc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author ADMIN
 */
public class KichThuocRepository {
     public ArrayList<KichThuoc> getAll(){
        ArrayList<KichThuoc> list = new ArrayList<>();
        String sql ="select id,ma_kich_thuoc,size from KichThuoc where trang_thai = 1";
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new KichThuoc(rs.getInt(1), rs.getString(2), rs.getString(3)) );
            }
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }
     
     public KichThuoc getKichThuocByTen(String ma1){
        String query = "select id,ma_kich_thuoc,size from KichThuoc\n" +
"where size=?"
                      ;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            // Set gia tri cho dau hoi cham 
            ps.setObject(1, ma1);
            ResultSet rs = ps.executeQuery(); // Lay ket qua

            while (rs.next()) {
                KichThuoc cv = new KichThuoc(rs.getInt(1), rs.getString(2), rs.getString(3));
                return cv;
            }
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
}
