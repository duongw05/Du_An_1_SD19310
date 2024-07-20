/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Product.Repository;
import com.Product.config.DBConnect;
import com.Product.entity.ChatLieu;
import com.Product.entity.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author ADMIN
 */
public class ChatLieuRepository {
    public ArrayList<ChatLieu> getAll(){
        ArrayList<ChatLieu> list = new ArrayList<>();
        String sql ="select id,ma_chat_lieu,ten_chat_lieu from ChatLieu";
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new ChatLieu(rs.getInt(1), rs.getString(2), rs.getString(3)) );
            }
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }
    
    public ChatLieu getChatLieuByTen(String ma1){
        String query = "select id,ma_chat_lieu,ten_chat_lieu from ChatLieu\n" +
"where ten_chat_lieu=?"
                      ;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            // Set gia tri cho dau hoi cham 
            ps.setObject(1, ma1);
            ResultSet rs = ps.executeQuery(); // Lay ket qua

            while (rs.next()) {
                ChatLieu cv = new ChatLieu(rs.getInt(1), rs.getString(2), rs.getString(3));
                return cv;
            }
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }

    
}
