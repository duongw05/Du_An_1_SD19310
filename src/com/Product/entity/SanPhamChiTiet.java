/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Product.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ADMIN
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class SanPhamChiTiet {
    private Integer id;
    private Integer sanPhamID;
    private Integer thuongHieu;
    private Integer chatLieuID;
    private Integer kichThuocID;
    private Integer coAoID;
    private Integer mauSacID;
    private Integer doDayID;
    private Integer tinhLinhHoatID;
    private Integer XuatXu;
    private String tenSanPham;
    private Double giaBan;
    private Integer soLuongTon;
//    private String hinhAnh;
    private boolean trangThai;
    
    
}
