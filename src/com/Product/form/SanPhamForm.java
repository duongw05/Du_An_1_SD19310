package com.Product.form;

import com.Product.Repository.SanPhamChiTietRepository;
import com.Product.Repository.SanPhamRepository;
import com.Product.Repository.ThuocTinhSanPhamRepository;
import com.Product.cell.TableActionCellEditor;
import com.Product.cell.TableActionCellRender;
import com.Product.cell.TableActionEvent;
import com.Product.component.Menu;
import com.Product.entity.SanPham;
import com.Product.entity.SanPhamChiTiet;
import com.Product.entity.ThuocTinhSanPham;
import com.Product.respone.SanPhamChiTietRespone;
import com.Product.swing.MenuAnimation;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import net.miginfocom.swing.MigLayout;

public class SanPhamForm extends javax.swing.JPanel {
    
    private DefaultComboBoxModel dcbm;

    private DefaultTableModel dtmSanPhamChiTiet;
    private SanPhamChiTietRepository sanPhamChiTietRepository;

    private DefaultTableModel dtmSanPham;
    private SanPhamRepository sanPhamRepository;

    private DefaultTableModel dtmThuocTinhSanPham;
    private ThuocTinhSanPhamRepository thuocTinhSanPhamRepository;

    public SanPhamForm() {
        initComponents();
        setOpaque(false);
        
        dcbm = new DefaultComboBoxModel();
        
        dcbm = (DefaultComboBoxModel) cbb_TenSPCT.getModel();
        
//        showCombobox();


       
        jpn_Them_SPCT.setVisible(false);

        sanPhamChiTietRepository = new SanPhamChiTietRepository();
        dtmSanPhamChiTiet = (DefaultTableModel) tbl_San_Pham_Chi_Tiet.getModel();
        showTableSanPhamChiTiet(sanPhamChiTietRepository.getAll());

        sanPhamRepository = new SanPhamRepository();
        dtmSanPham = (DefaultTableModel) tbl_SanPham.getModel();
        showTableSanPham(sanPhamRepository.getAllGiamDan());

        thuocTinhSanPhamRepository = new ThuocTinhSanPhamRepository();
        dtmThuocTinhSanPham = (DefaultTableModel) tbl_Thuoc_Tinh_San_Pham.getModel();
        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getAll());

        TableActionEvent eventSanPham = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                int respone = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa không?");
                if (respone == JOptionPane.YES_OPTION) {
                    System.out.println("hello");
                }
            }

            @Override
            public void onDelete(int row) {
                int respone = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không?");
                if (tbl_SanPham.isEditing()) {
                    tbl_SanPham.getCellEditor().stopCellEditing();
                }
            }

        };

        TableActionEvent eventSanPhamChiTiet = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                int respone = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa không vậy????");
                if (respone == JOptionPane.YES_OPTION) {
                    System.out.println("hello");
                }
            }

            @Override
            public void onDelete(int row) {
                int respone = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không vậy?");
                if (tbl_SanPham.isEditing()) {
                    tbl_SanPham.getCellEditor().stopCellEditing();
                }
            }

        };
        tbl_SanPham.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
        tbl_SanPham.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor(eventSanPham));

        tbl_San_Pham_Chi_Tiet.getColumnModel().getColumn(14).setCellRenderer(new TableActionCellRender());
        tbl_San_Pham_Chi_Tiet.getColumnModel().getColumn(14).setCellEditor(new TableActionCellEditor(eventSanPhamChiTiet));

    }

    private void showTableSanPhamChiTiet(ArrayList<SanPhamChiTietRespone> lists) {
        dtmSanPhamChiTiet.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> dtmSanPhamChiTiet.addRow(new Object[]{
            index.getAndIncrement(), s.getMaSPCT(), s.getTenSPCT(),
            s.getThuongHieu(), s.getXuatXu(),
            s.getMauSac(),
            s.getKichThuoc(), s.getChatLieu(), s.getCoAo(), s.getDoDay(), s.getPhongCach(),
            s.getGiaBan(), s.getSoLuong(),
            s.isTrangThai() ? "Còn hàng" : "Hết hàng"
        }));
    }

    private void showTableSanPham(ArrayList<SanPham> lists) {
        dtmSanPham.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> dtmSanPham.addRow(new Object[]{
            index.getAndIncrement(), s.getMaSanPham(), s.getTenSanPham(),
            s.getMoTa(), s.isTrangThai() ? "Còn hàng" : "Hết hàng",
            s.getNgayTao(),}));
    }

    private void showTableThuocTinhSanPham(ArrayList<ThuocTinhSanPham> lists) {
        dtmThuocTinhSanPham.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> dtmThuocTinhSanPham.addRow(new Object[]{
            index.getAndIncrement(), s.getMaThuocTinhSanPham(), s.getTenThuocTinhSanPham(),}));
    }
    
//    private void showCombobox() {
//    dcbm.removeAllElements();
//    ArrayList<SanPhamChiTietRespone> sanPhamChiTietList = sanPhamChiTietRepository.getAll();
//    for (SanPhamChiTietRespone s : sanPhamChiTietList) {
//        dcbm.addElement(s.getTenSPCT());
//        dcbm.addElement(s.getThuongHieu());
//        dcbm.addElement(s.getChatLieu());
//        dcbm.addElement(s.getKichThuoc());
//        dcbm.addElement(s.getCoAo());
//        dcbm.addElement(s.getMauSac());
//        dcbm.addElement(s.getDoDay());
//        dcbm.addElement(s.getXuatXu());
//    }
//}


    private SanPham getFormData() {
        // builder 
        if (txtTenSanPham.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Bạn Chưa Nhập Sản Phẩm", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        SanPham kh = SanPham.builder()
                .maSanPham(txtMaSanPham.getText())
                .tenSanPham(txtTenSanPham.getText())
                .moTa(txtMoTaSanPham.getText())
                .build();
        // Tuong ung voi contructor khong tham so
//        KhachHang kh1 = new KhachHang();
        return kh;
    }

    private ThuocTinhSanPham getFormDataThuocTinhSP() {
        if (txtTenThuocTinh.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập tên thuộc tính", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        ThuocTinhSanPham ttsp = ThuocTinhSanPham.builder()
                .maThuocTinhSanPham(txtMaSanPham.getText())
                .tenThuocTinhSanPham(txtTenThuocTinh.getText())
                .build();

        return ttsp;
    }

    private void detailSanPham(int index) {
        SanPham sp = sanPhamRepository.getAllGiamDan().get(index);
        txtMaSanPham.setText(sp.getMaSanPham());
        txtTenSanPham.setText(sp.getTenSanPham());
        txtMoTaSanPham.setText(sp.getMoTa());

    }
    
    private void detailSanPhamChiTiet(int index){
        SanPhamChiTietRespone spctrp = sanPhamChiTietRepository.getAll().get(index);
        cbb_TenSPCT.setSelectedItem(spctrp.getTenSPCT());
        cbb_ThuongHieuSPCT.setSelectedItem(spctrp.getThuongHieu());
        cbb_ChatLieuSPCT.setSelectedItem(spctrp.getChatLieu());
        cbb_KichThuoc.setSelectedItem(spctrp.getKichThuoc());
        cbb_CoAoSPCT.setSelectedItem(spctrp.getCoAo());
        cbb_MauSac.setSelectedItem(spctrp.getMauSac());
        cbb_DoDay.setSelectedItem(spctrp.getDoDay());
        cbb_XuatXu.setSelectedItem(spctrp.getXuatXu());
        Double giaBan = spctrp.getGiaBan();
        txt_GiaBanSPCT.setText(giaBan.toString());
        Integer soLuong = spctrp.getSoLuong();
        txt_SoLuongSPCT.setText(soLuong.toString());
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgSanPham = new javax.swing.ButtonGroup();
        btgThuocTinhSanPham = new javax.swing.ButtonGroup();
        tabbedPaneCustomm1 = new com.Product.GUI.tabbed.TabbedPaneCustomm();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtMaSanPham = new com.Product.GUI.textfield.TextField();
        txtTenSanPham = new com.Product.GUI.textfield.TextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btn_ThemSanPham = new com.Product.swing.ButtonBadges();
        btn_SuaSanPham = new com.Product.swing.ButtonBadges();
        btn_XoaSanPham = new com.Product.swing.ButtonBadges();
        btn_LamMoiSP = new com.Product.swing.ButtonBadges();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTaSanPham = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        rdo_DangBan = new javax.swing.JRadioButton();
        rdo_TatCa = new javax.swing.JRadioButton();
        rdo_NgungBan = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_SanPham = new com.Product.GUI.Table();
        jPanel10 = new javax.swing.JPanel();
        textField4 = new com.Product.GUI.textfield.TextField();
        jLabel8 = new javax.swing.JLabel();
        buttonBadges6 = new com.Product.swing.ButtonBadges();
        jpn_SPCT = new javax.swing.JPanel();
        jpn_Them_SPCT = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        buttonBadges3 = new com.Product.swing.ButtonBadges();
        cbb_ThuongHieuSPCT = new com.Product.GUI.combo_suggestion.ComboBoxSuggestion();
        cbb_ChatLieuSPCT = new com.Product.GUI.combo_suggestion.ComboBoxSuggestion();
        cbb_TenSPCT = new com.Product.GUI.combo_suggestion.ComboBoxSuggestion();
        cbb_KichThuoc = new com.Product.GUI.combo_suggestion.ComboBoxSuggestion();
        cbb_MauSac = new com.Product.GUI.combo_suggestion.ComboBoxSuggestion();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbb_DoDay = new com.Product.GUI.combo_suggestion.ComboBoxSuggestion();
        cbb_XuatXu = new com.Product.GUI.combo_suggestion.ComboBoxSuggestion();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbb_CoAoSPCT = new com.Product.GUI.combo_suggestion.ComboBoxSuggestion();
        txt_SoLuongSPCT = new com.Product.GUI.textfield.TextField();
        txt_GiaBanSPCT = new com.Product.GUI.textfield.TextField();
        btn_ThemSPCT = new com.Product.swing.ButtonBadges();
        btn_ThemSPCT1 = new com.Product.swing.ButtonBadges();
        jpn_Thong_Tin_SPCT = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbl_San_Pham_Chi_Tiet = new com.Product.GUI.Table();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        combobox16 = new com.Product.GUI.Combobox();
        combobox17 = new com.Product.GUI.Combobox();
        combobox18 = new com.Product.GUI.Combobox();
        combobox19 = new com.Product.GUI.Combobox();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        textField11 = new com.Product.GUI.textfield.TextField();
        btn_ThemMoiSPCT3 = new com.Product.swing.ButtonBadges();
        btn_Quet_QR3 = new com.Product.swing.ButtonBadges();
        btn_TaiMa_QR3 = new com.Product.swing.ButtonBadges();
        btn_LamMoiSPCT3 = new com.Product.swing.ButtonBadges();
        combobox20 = new com.Product.GUI.Combobox();
        buttonBadges4 = new com.Product.swing.ButtonBadges();
        buttonBadges1 = new com.Product.swing.ButtonBadges();
        jCheckBoxCustom1 = new com.Product.GUI.checkbox.JCheckBoxCustom();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txtTenThuocTinh = new com.Product.GUI.textfield.TextField();
        txtMaThuocTinh = new com.Product.GUI.textfield.TextField();
        jPanel8 = new javax.swing.JPanel();
        rdoThuongHieu = new javax.swing.JRadioButton();
        rdoChatLieu = new javax.swing.JRadioButton();
        rdoKichThuoc = new javax.swing.JRadioButton();
        rdoMauSac = new javax.swing.JRadioButton();
        rdoDoDay = new javax.swing.JRadioButton();
        rdoPhongCach = new javax.swing.JRadioButton();
        rdoCoAo = new javax.swing.JRadioButton();
        rdoXuatXu = new javax.swing.JRadioButton();
        rdoTatCaThuocTinhSP = new javax.swing.JRadioButton();
        btn_ThemThuocTinhSanPham = new com.Product.swing.ButtonBadges();
        buttonBadges12 = new com.Product.swing.ButtonBadges();
        buttonBadges13 = new com.Product.swing.ButtonBadges();
        btn_LamMoiThuocTinhSanPham = new com.Product.swing.ButtonBadges();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_Thuoc_Tinh_San_Pham = new com.Product.GUI.Table();

        tabbedPaneCustomm1.setBackground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustomm1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tabbedPaneCustomm1.setSelectedColor(new java.awt.Color(100, 202, 235));
        tabbedPaneCustomm1.setUnselectedColor(new java.awt.Color(204, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Thông Tin Sản Phẩm");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        txtMaSanPham.setEnabled(false);
        txtMaSanPham.setLabelText("Mã sản phẩm");

        txtTenSanPham.setLabelText("Nhập tên sản phẩm");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Mô tả sản phẩm");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Mã sản phẩm");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Tên sản phẩm");

        btn_ThemSanPham.setBackground(new java.awt.Color(153, 255, 255));
        btn_ThemSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/taohoadon.png"))); // NOI18N
        btn_ThemSanPham.setText("Thêm");
        btn_ThemSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_ThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemSanPhamActionPerformed(evt);
            }
        });

        btn_SuaSanPham.setBackground(new java.awt.Color(153, 255, 255));
        btn_SuaSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/sua.png"))); // NOI18N
        btn_SuaSanPham.setText("Sửa");
        btn_SuaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_SuaSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_SuaSanPhamMouseClicked(evt);
            }
        });
        btn_SuaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaSanPhamActionPerformed(evt);
            }
        });

        btn_XoaSanPham.setBackground(new java.awt.Color(153, 255, 255));
        btn_XoaSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/xoa.png"))); // NOI18N
        btn_XoaSanPham.setText("Xóa");
        btn_XoaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_XoaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaSanPhamActionPerformed(evt);
            }
        });

        btn_LamMoiSP.setBackground(new java.awt.Color(153, 255, 255));
        btn_LamMoiSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/lammoi.png"))); // NOI18N
        btn_LamMoiSP.setText("Làm Mới");
        btn_LamMoiSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_LamMoiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiSPActionPerformed(evt);
            }
        });

        txtMoTaSanPham.setColumns(20);
        txtMoTaSanPham.setRows(5);
        jScrollPane2.setViewportView(txtMoTaSanPham);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(btn_ThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(btn_SuaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(btn_XoaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(115, 115, 115)
                        .addComponent(btn_LamMoiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_SuaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_XoaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_LamMoiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Trạng thái");

        rdo_DangBan.setBackground(new java.awt.Color(255, 255, 255));
        btgSanPham.add(rdo_DangBan);
        rdo_DangBan.setText("Còn hàng");
        rdo_DangBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdo_DangBanMouseClicked(evt);
            }
        });
        rdo_DangBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_DangBanActionPerformed(evt);
            }
        });

        btgSanPham.add(rdo_TatCa);
        rdo_TatCa.setSelected(true);
        rdo_TatCa.setText("Tất Cả");
        rdo_TatCa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdo_TatCaMouseClicked(evt);
            }
        });

        btgSanPham.add(rdo_NgungBan);
        rdo_NgungBan.setText("Hết hàng");
        rdo_NgungBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdo_NgungBanMouseClicked(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 204), 3));

        tbl_SanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Mô tả", "Trạng Thái", "Ngày Tạo", "Hành Động"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_SanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbl_SanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_SanPham);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        textField4.setLabelText("Tìm kiếm tại đây");
        textField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField4ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Tìm kiếm sản phẩm");

        buttonBadges6.setBackground(new java.awt.Color(255, 204, 255));
        buttonBadges6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/timkiem.png"))); // NOI18N
        buttonBadges6.setText("Tìm ");
        buttonBadges6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(buttonBadges6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(249, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonBadges6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdo_TatCa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdo_DangBan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdo_NgungBan))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(121, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(392, 392, 392))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(rdo_DangBan)
                    .addComponent(rdo_TatCa)
                    .addComponent(rdo_NgungBan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1300, 1300, 1300))
        );

        tabbedPaneCustomm1.addTab("Sản phẩm", jPanel2);

        jpn_SPCT.setBackground(new java.awt.Color(255, 255, 255));

        jpn_Them_SPCT.setBackground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 0, 0));
        jLabel20.setText("Thêm Sản Phẩm Chi Tiết");

        buttonBadges3.setBackground(new java.awt.Color(102, 255, 255));
        buttonBadges3.setText("Back");
        buttonBadges3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonBadges3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBadges3ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Tên Sản Phẩm ");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Thương hiệu");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Chất Liệu");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Kích Thước");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Cổ Áo");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Màu sắc");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Độ dày");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Xuất Xứ");

        txt_SoLuongSPCT.setLabelText("Số Lượng");

        txt_GiaBanSPCT.setLabelText("Giá Bán");

        btn_ThemSPCT.setBackground(new java.awt.Color(51, 255, 255));
        btn_ThemSPCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/taohoadon.png"))); // NOI18N
        btn_ThemSPCT.setText("Thêm");
        btn_ThemSPCT.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        btn_ThemSPCT1.setBackground(new java.awt.Color(51, 255, 255));
        btn_ThemSPCT1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/sua.png"))); // NOI18N
        btn_ThemSPCT1.setText("Sửa");
        btn_ThemSPCT1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        javax.swing.GroupLayout jpn_Them_SPCTLayout = new javax.swing.GroupLayout(jpn_Them_SPCT);
        jpn_Them_SPCT.setLayout(jpn_Them_SPCTLayout);
        jpn_Them_SPCTLayout.setHorizontalGroup(
            jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                                .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbb_CoAoSPCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                                        .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbb_TenSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                                        .addGap(168, 168, 168)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                                        .addGap(73, 73, 73)
                                        .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(cbb_ThuongHieuSPCT, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cbb_MauSac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_SoLuongSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(97, 97, 97)
                                        .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbb_ChatLieuSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbb_DoDay, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_ThemSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                                                .addGap(91, 91, 91)
                                                .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(cbb_KichThuoc, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                                    .addComponent(cbb_XuatXu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addComponent(btn_ThemSPCT1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                                .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(txt_GiaBanSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 884, Short.MAX_VALUE))))
                    .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(buttonBadges3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jpn_Them_SPCTLayout.setVerticalGroup(
            jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addGap(48, 48, 48)
                .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(7, 7, 7)
                .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbb_ThuongHieuSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_TenSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_ChatLieuSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_KichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpn_Them_SPCTLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbb_XuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(108, 108, 108))
                            .addGroup(jpn_Them_SPCTLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbb_DoDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(106, 106, 106)))
                        .addGap(97, 97, 97)
                        .addComponent(buttonBadges3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpn_Them_SPCTLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbb_CoAoSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbb_MauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(jpn_Them_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_GiaBanSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_SoLuongSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_ThemSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_ThemSPCT1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(1782, Short.MAX_VALUE))
        );

        jpn_Thong_Tin_SPCT.setBackground(new java.awt.Color(255, 255, 255));

        tbl_San_Pham_Chi_Tiet.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        tbl_San_Pham_Chi_Tiet.setForeground(new java.awt.Color(255, 255, 255));
        tbl_San_Pham_Chi_Tiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SPCT", "Tên SPCT", "Thương Hiệu", "Xuất Xứ", "Màu Sắc", "Kích Thước", "Chất Liệu", "Cổ Áo", "Độ Dày", "Phong Cách", "Giá Bán", "Số Lượng", "Trạng Thái", "Hành Động"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_San_Pham_Chi_Tiet.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jScrollPane7.setViewportView(tbl_San_Pham_Chi_Tiet);

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 0, 0));
        jLabel26.setText("Thông Tin Sản Phẩm Chi Tiết");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setText("Tìm kiếm:");

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bộ lọc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setText("Sản phẩm");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setText("Thương hiệu");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel30.setText("Phong cách");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel31.setText("Xuất xứ");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addComponent(combobox18, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combobox16, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(combobox19, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(combobox17, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 34, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel29)
                            .addComponent(combobox16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combobox18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)
                            .addComponent(combobox19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(combobox17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31))))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        textField11.setLabelText("Nhập tìm kiếm tại đây");
        textField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField11ActionPerformed(evt);
            }
        });

        btn_ThemMoiSPCT3.setBackground(new java.awt.Color(102, 255, 255));
        btn_ThemMoiSPCT3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/taohoadon.png"))); // NOI18N
        btn_ThemMoiSPCT3.setText("Thêm Mới");
        btn_ThemMoiSPCT3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_ThemMoiSPCT3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ThemMoiSPCT3MouseClicked(evt);
            }
        });
        btn_ThemMoiSPCT3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemMoiSPCT3ActionPerformed(evt);
            }
        });

        btn_Quet_QR3.setBackground(new java.awt.Color(51, 255, 255));
        btn_Quet_QR3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/qr.png"))); // NOI18N
        btn_Quet_QR3.setText("Quét QR");
        btn_Quet_QR3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btn_TaiMa_QR3.setBackground(new java.awt.Color(0, 255, 255));
        btn_TaiMa_QR3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/import.png"))); // NOI18N
        btn_TaiMa_QR3.setText("Tải Mã QR");
        btn_TaiMa_QR3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_TaiMa_QR3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TaiMa_QR3ActionPerformed(evt);
            }
        });

        btn_LamMoiSPCT3.setBackground(new java.awt.Color(51, 255, 255));
        btn_LamMoiSPCT3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/lammoi.png"))); // NOI18N
        btn_LamMoiSPCT3.setText("Làm Mới");
        btn_LamMoiSPCT3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        combobox20.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Giá từ cao đến thấp", "Giá từ thấp đến cao" }));

        buttonBadges4.setBackground(new java.awt.Color(102, 255, 255));
        buttonBadges4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/xuatfile.png"))); // NOI18N
        buttonBadges4.setText("Xuất Excel");
        buttonBadges4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        buttonBadges1.setBackground(new java.awt.Color(255, 102, 255));
        buttonBadges1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/timkiem.png"))); // NOI18N
        buttonBadges1.setText("Tìm ");
        buttonBadges1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jCheckBoxCustom1.setText("ALL");

        javax.swing.GroupLayout jpn_Thong_Tin_SPCTLayout = new javax.swing.GroupLayout(jpn_Thong_Tin_SPCT);
        jpn_Thong_Tin_SPCT.setLayout(jpn_Thong_Tin_SPCTLayout);
        jpn_Thong_Tin_SPCTLayout.setHorizontalGroup(
            jpn_Thong_Tin_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpn_Thong_Tin_SPCTLayout.createSequentialGroup()
                .addGroup(jpn_Thong_Tin_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpn_Thong_Tin_SPCTLayout.createSequentialGroup()
                        .addGroup(jpn_Thong_Tin_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpn_Thong_Tin_SPCTLayout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(jpn_Thong_Tin_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jpn_Thong_Tin_SPCTLayout.createSequentialGroup()
                                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(textField11, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jpn_Thong_Tin_SPCTLayout.createSequentialGroup()
                                .addGap(200, 200, 200)
                                .addComponent(btn_ThemMoiSPCT3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(86, 86, 86)
                                .addComponent(btn_Quet_QR3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(96, 96, 96)
                                .addComponent(btn_TaiMa_QR3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(87, 87, 87)
                                .addComponent(btn_LamMoiSPCT3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpn_Thong_Tin_SPCTLayout.createSequentialGroup()
                                .addGap(383, 383, 383)
                                .addGroup(jpn_Thong_Tin_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(buttonBadges1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpn_Thong_Tin_SPCTLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1229, Short.MAX_VALUE))
                    .addGroup(jpn_Thong_Tin_SPCTLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(buttonBadges4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBoxCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(combobox20, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpn_Thong_Tin_SPCTLayout.setVerticalGroup(
            jpn_Thong_Tin_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpn_Thong_Tin_SPCTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addGroup(jpn_Thong_Tin_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpn_Thong_Tin_SPCTLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jpn_Thong_Tin_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(textField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpn_Thong_Tin_SPCTLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(buttonBadges1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jpn_Thong_Tin_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ThemMoiSPCT3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Quet_QR3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TaiMa_QR3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_LamMoiSPCT3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jpn_Thong_Tin_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combobox20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonBadges4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1508, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpn_SPCTLayout = new javax.swing.GroupLayout(jpn_SPCT);
        jpn_SPCT.setLayout(jpn_SPCTLayout);
        jpn_SPCTLayout.setHorizontalGroup(
            jpn_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpn_Thong_Tin_SPCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpn_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpn_SPCTLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jpn_Them_SPCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jpn_SPCTLayout.setVerticalGroup(
            jpn_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpn_Thong_Tin_SPCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpn_SPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpn_SPCTLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jpn_Them_SPCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        tabbedPaneCustomm1.addTab("Sản phẩm chi tiết", jpn_SPCT);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("Thuộc tính sản phẩm");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        txtTenThuocTinh.setLabelText("Tên thuộc tính");
        txtTenThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenThuocTinhActionPerformed(evt);
            }
        });

        txtMaThuocTinh.setEnabled(false);
        txtMaThuocTinh.setLabelText("Mã thuộc tính");

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh mục thuộc tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 0, 255))); // NOI18N

        btgThuocTinhSanPham.add(rdoThuongHieu);
        rdoThuongHieu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoThuongHieu.setText("Thương hiệu");
        rdoThuongHieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoThuongHieuMouseClicked(evt);
            }
        });
        rdoThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoThuongHieuActionPerformed(evt);
            }
        });

        btgThuocTinhSanPham.add(rdoChatLieu);
        rdoChatLieu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoChatLieu.setText("Chất liệu");
        rdoChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoChatLieuMouseClicked(evt);
            }
        });

        btgThuocTinhSanPham.add(rdoKichThuoc);
        rdoKichThuoc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoKichThuoc.setText("Kích thước ");
        rdoKichThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoKichThuocMouseClicked(evt);
            }
        });

        btgThuocTinhSanPham.add(rdoMauSac);
        rdoMauSac.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoMauSac.setText("Màu sắc");
        rdoMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoMauSacMouseClicked(evt);
            }
        });

        btgThuocTinhSanPham.add(rdoDoDay);
        rdoDoDay.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoDoDay.setText("Đồ dày");
        rdoDoDay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoDoDayMouseClicked(evt);
            }
        });

        btgThuocTinhSanPham.add(rdoPhongCach);
        rdoPhongCach.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoPhongCach.setText("Phong cách");
        rdoPhongCach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoPhongCachMouseClicked(evt);
            }
        });

        btgThuocTinhSanPham.add(rdoCoAo);
        rdoCoAo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoCoAo.setText("Cổ áo");
        rdoCoAo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoCoAoMouseClicked(evt);
            }
        });

        btgThuocTinhSanPham.add(rdoXuatXu);
        rdoXuatXu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoXuatXu.setText("Xuất xứ");
        rdoXuatXu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoXuatXuMouseClicked(evt);
            }
        });

        rdoTatCaThuocTinhSP.setBackground(new java.awt.Color(255, 255, 255));
        btgThuocTinhSanPham.add(rdoTatCaThuocTinhSP);
        rdoTatCaThuocTinhSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoTatCaThuocTinhSP.setSelected(true);
        rdoTatCaThuocTinhSP.setText("Tất Cả");
        rdoTatCaThuocTinhSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoTatCaThuocTinhSPMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoCoAo)
                    .addComponent(rdoTatCaThuocTinhSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoThuongHieu)
                    .addComponent(rdoMauSac))
                .addGap(47, 47, 47)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoChatLieu)
                    .addComponent(rdoDoDay))
                .addGap(52, 52, 52)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(rdoKichThuoc)
                        .addGap(122, 122, 122))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(rdoPhongCach)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(rdoXuatXu)
                        .addGap(22, 22, 22))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoThuongHieu)
                    .addComponent(rdoChatLieu)
                    .addComponent(rdoKichThuoc)
                    .addComponent(rdoTatCaThuocTinhSP))
                .addGap(35, 35, 35)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoMauSac)
                    .addComponent(rdoDoDay)
                    .addComponent(rdoPhongCach)
                    .addComponent(rdoXuatXu)
                    .addComponent(rdoCoAo))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btn_ThemThuocTinhSanPham.setBackground(new java.awt.Color(255, 204, 255));
        btn_ThemThuocTinhSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/taohoadon.png"))); // NOI18N
        btn_ThemThuocTinhSanPham.setText("Thêm");
        btn_ThemThuocTinhSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_ThemThuocTinhSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemThuocTinhSanPhamActionPerformed(evt);
            }
        });

        buttonBadges12.setBackground(new java.awt.Color(255, 204, 255));
        buttonBadges12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/sua.png"))); // NOI18N
        buttonBadges12.setText("Sửa");
        buttonBadges12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        buttonBadges13.setBackground(new java.awt.Color(255, 204, 255));
        buttonBadges13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/xoa.png"))); // NOI18N
        buttonBadges13.setText("Xóa");
        buttonBadges13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        btn_LamMoiThuocTinhSanPham.setBackground(new java.awt.Color(255, 204, 255));
        btn_LamMoiThuocTinhSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/lammoi.png"))); // NOI18N
        btn_LamMoiThuocTinhSanPham.setText("Làm Mới");
        btn_LamMoiThuocTinhSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_LamMoiThuocTinhSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiThuocTinhSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(btn_ThemThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(buttonBadges12, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(buttonBadges13, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(btn_LamMoiThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(213, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(762, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonBadges13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ThemThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_LamMoiThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonBadges12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(172, Short.MAX_VALUE)))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 204), 2));

        tbl_Thuoc_Tinh_San_Pham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã Thuộc Tính", "Tên Thuộc Tính"
            }
        ));
        tbl_Thuoc_Tinh_San_Pham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbl_Thuoc_Tinh_San_Pham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_Thuoc_Tinh_San_PhamMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_Thuoc_Tinh_San_Pham);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 133, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1628, Short.MAX_VALUE))
        );

        tabbedPaneCustomm1.addTab("Thuộc tính sản phẩm", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustomm1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustomm1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenThuocTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenThuocTinhActionPerformed

    private void textField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField4ActionPerformed

    private void tbl_SanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SanPhamMouseClicked
        // TODO add your handling code here:
        detailSanPham(tbl_SanPham.getSelectedRow());

    }//GEN-LAST:event_tbl_SanPhamMouseClicked

    private void rdoThuongHieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoThuongHieuMouseClicked
        // TODO add your handling code here:
        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getThuongHieu());
    }//GEN-LAST:event_rdoThuongHieuMouseClicked

    private void rdoChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoChatLieuMouseClicked
        // TODO add your handling code here:
        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getChatLieu());
    }//GEN-LAST:event_rdoChatLieuMouseClicked

    private void rdoKichThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoKichThuocMouseClicked
        // TODO add your handling code here:
        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getKichThuoc());
    }//GEN-LAST:event_rdoKichThuocMouseClicked

    private void rdoCoAoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoCoAoMouseClicked
        // TODO add your handling code here:
        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getCoAO());
    }//GEN-LAST:event_rdoCoAoMouseClicked

    private void rdoMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoMauSacMouseClicked
        // TODO add your handling code here:
        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getMauSac());
    }//GEN-LAST:event_rdoMauSacMouseClicked

    private void rdoDoDayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoDoDayMouseClicked
        // TODO add your handling code here:
        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getDoDay());
    }//GEN-LAST:event_rdoDoDayMouseClicked

    private void rdoPhongCachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoPhongCachMouseClicked
        // TODO add your handling code here:
        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getTinhLinhHoat());
    }//GEN-LAST:event_rdoPhongCachMouseClicked

    private void rdoXuatXuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoXuatXuMouseClicked
        // TODO add your handling code here:
        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getXuatXu());
    }//GEN-LAST:event_rdoXuatXuMouseClicked

    private void tbl_Thuoc_Tinh_San_PhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_Thuoc_Tinh_San_PhamMouseClicked
        // TODO add your handling code here:
        int i = tbl_Thuoc_Tinh_San_Pham.getSelectedRow();
        txtMaThuocTinh.setText(tbl_Thuoc_Tinh_San_Pham.getValueAt(i, 1).toString());
        txtTenThuocTinh.setText(tbl_Thuoc_Tinh_San_Pham.getValueAt(i, 2).toString());
    }//GEN-LAST:event_tbl_Thuoc_Tinh_San_PhamMouseClicked

    private void btn_LamMoiThuocTinhSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiThuocTinhSanPhamActionPerformed
        // TODO add your handling code here:
        txtMaThuocTinh.setText("");
        txtTenThuocTinh.setText("");
    }//GEN-LAST:event_btn_LamMoiThuocTinhSanPhamActionPerformed

    private void rdoTatCaThuocTinhSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoTatCaThuocTinhSPMouseClicked
        // TODO add your handling code here:
        showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getAll());
    }//GEN-LAST:event_rdoTatCaThuocTinhSPMouseClicked

    private void rdo_DangBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_DangBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_DangBanActionPerformed

    private void rdo_DangBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdo_DangBanMouseClicked
        // TODO add your handling code here:
        showTableSanPham(sanPhamRepository.getSanPhamDangBan());
    }//GEN-LAST:event_rdo_DangBanMouseClicked

    private void rdo_NgungBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdo_NgungBanMouseClicked
        // TODO add your handling code here:
        showTableSanPham(sanPhamRepository.getSanPhamNgungBan());
    }//GEN-LAST:event_rdo_NgungBanMouseClicked

    private void rdo_TatCaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdo_TatCaMouseClicked
        // TODO add your handling code here:
        showTableSanPham(sanPhamRepository.getAll());
    }//GEN-LAST:event_rdo_TatCaMouseClicked

    private void btn_ThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemSanPhamActionPerformed
        // Thêm sản phẩm mới vào kho

        SanPham sanpham = getFormData();
        if (sanpham == null) {
            return;
        }
        try {
            sanPhamRepository.add(sanpham);
            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công");
            showTableSanPham(sanPhamRepository.getAllGiamDan());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi thêm sản phẩm" + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btn_ThemSanPhamActionPerformed

    private void btn_ThemThuocTinhSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemThuocTinhSanPhamActionPerformed
        // TODO add your handling code here:
        ThuocTinhSanPham ttsp = getFormDataThuocTinhSP();
        if (ttsp == null) {
            return;
        }
        try {
            if (rdoCoAo.isSelected()) {
                thuocTinhSanPhamRepository.insertCoAo(ttsp);
                JOptionPane.showMessageDialog(null, "Thêm thuộc tính sản phẩm thành công");
                showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getCoAO());
            } else if (rdoChatLieu.isSelected()) {
                thuocTinhSanPhamRepository.insertChatLieu(ttsp);
                JOptionPane.showMessageDialog(null, "Thêm thuộc tính sản phẩm thành công");
                showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getChatLieu());
            }else if(rdoDoDay.isSelected()){
                thuocTinhSanPhamRepository.insertDoDay(ttsp);
                JOptionPane.showMessageDialog(null, "Thêm thuộc tính sản phẩm thành công");
                showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getAll());
            }else if(rdoKichThuoc.isSelected()){
                thuocTinhSanPhamRepository.insertKichThuoc(ttsp);
                JOptionPane.showMessageDialog(null, "Thêm thuộc tính sản phẩm thành công");
                showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getKichThuoc());
            }else if(rdoMauSac.isSelected()){
                thuocTinhSanPhamRepository.insertMauSac(ttsp);
                JOptionPane.showMessageDialog(null, "Thêm thuộc tính sản phẩm thành công");
                showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getMauSac());
            }else if(rdoPhongCach.isSelected()){
                thuocTinhSanPhamRepository.insertPhongCach(ttsp);
                JOptionPane.showMessageDialog(null, "Thêm thuộc tính sản phẩm thành công");
                showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getTinhLinhHoat());
            }else if(rdoThuongHieu.isSelected()){
                thuocTinhSanPhamRepository.insertThuongHieu(ttsp);
                JOptionPane.showMessageDialog(null, "Thêm thuộc tính sản phẩm thành công");
                showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getThuongHieu());
            }else if(rdoXuatXu.isSelected()){
                thuocTinhSanPhamRepository.insertXuatXu(ttsp);
                JOptionPane.showMessageDialog(null, "Thêm thuộc tính sản phẩm thành công");
                showTableThuocTinhSanPham(thuocTinhSanPhamRepository.getXuatXu());
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi thêm thuộc tính sản phẩm" + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_ThemThuocTinhSanPhamActionPerformed

    private void buttonBadges3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBadges3ActionPerformed
        // TODO add your handling code here:
        jpn_Them_SPCT.setVisible(false);
        jpn_Thong_Tin_SPCT.setVisible(true);
    }//GEN-LAST:event_buttonBadges3ActionPerformed

    private void textField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField11ActionPerformed

    private void btn_ThemMoiSPCT3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThemMoiSPCT3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ThemMoiSPCT3MouseClicked

    private void btn_ThemMoiSPCT3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemMoiSPCT3ActionPerformed
        // TODO add your handling code here:
        jpn_Thong_Tin_SPCT.setVisible(false);
        jpn_Them_SPCT.setVisible(true);

    }//GEN-LAST:event_btn_ThemMoiSPCT3ActionPerformed

    private void btn_TaiMa_QR3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TaiMa_QR3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_TaiMa_QR3ActionPerformed

    private void btn_SuaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaSanPhamActionPerformed
        // TODO add your handling code here:
        int index = tbl_SanPham.getSelectedRow();
        SanPham newSanPham = getFormData();

        if (newSanPham == null) {
            return;
        }
        try {
            SanPham sp = sanPhamRepository.getAllGiamDan().get(index);
            sanPhamRepository.update(getFormData(), sp.getId());
            showTableSanPham(sanPhamRepository.getAllGiamDan());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi thêm sản phẩm" + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btn_SuaSanPhamActionPerformed

    private void btn_SuaSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SuaSanPhamMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_btn_SuaSanPhamMouseClicked

    private void btn_XoaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaSanPhamActionPerformed
        // TODO add your handling code here:
        int index = tbl_SanPham.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        SanPham newSanPham = getFormData();
        if (newSanPham == null) {
            return;
        }

        try {
            ArrayList<SanPham> sanPhamList = sanPhamRepository.getAllGiamDan();
            if (sanPhamList == null || sanPhamList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Không có sản phẩm nào để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            SanPham sp = sanPhamList.get(index);
            if (sp == null) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm với chỉ số đã chọn.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa sản phẩm '" + sp.getTenSanPham() + "'?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                sanPhamRepository.remove(sp.getId());
                showTableSanPham(sanPhamList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_XoaSanPhamActionPerformed

    private void btn_LamMoiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiSPActionPerformed
        // TODO add your handling code here:
        txtMaSanPham.setText("");
        txtTenSanPham.setText("");
        txtMoTaSanPham.setText("");
    }//GEN-LAST:event_btn_LamMoiSPActionPerformed

    private void rdoThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoThuongHieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoThuongHieuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgSanPham;
    private javax.swing.ButtonGroup btgThuocTinhSanPham;
    private com.Product.swing.ButtonBadges btn_LamMoiSP;
    private com.Product.swing.ButtonBadges btn_LamMoiSPCT3;
    private com.Product.swing.ButtonBadges btn_LamMoiThuocTinhSanPham;
    private com.Product.swing.ButtonBadges btn_Quet_QR3;
    private com.Product.swing.ButtonBadges btn_SuaSanPham;
    private com.Product.swing.ButtonBadges btn_TaiMa_QR3;
    private com.Product.swing.ButtonBadges btn_ThemMoiSPCT3;
    private com.Product.swing.ButtonBadges btn_ThemSPCT;
    private com.Product.swing.ButtonBadges btn_ThemSPCT1;
    private com.Product.swing.ButtonBadges btn_ThemSanPham;
    private com.Product.swing.ButtonBadges btn_ThemThuocTinhSanPham;
    private com.Product.swing.ButtonBadges btn_XoaSanPham;
    private com.Product.swing.ButtonBadges buttonBadges1;
    private com.Product.swing.ButtonBadges buttonBadges12;
    private com.Product.swing.ButtonBadges buttonBadges13;
    private com.Product.swing.ButtonBadges buttonBadges3;
    private com.Product.swing.ButtonBadges buttonBadges4;
    private com.Product.swing.ButtonBadges buttonBadges6;
    private com.Product.GUI.combo_suggestion.ComboBoxSuggestion cbb_ChatLieuSPCT;
    private com.Product.GUI.combo_suggestion.ComboBoxSuggestion cbb_CoAoSPCT;
    private com.Product.GUI.combo_suggestion.ComboBoxSuggestion cbb_DoDay;
    private com.Product.GUI.combo_suggestion.ComboBoxSuggestion cbb_KichThuoc;
    private com.Product.GUI.combo_suggestion.ComboBoxSuggestion cbb_MauSac;
    private com.Product.GUI.combo_suggestion.ComboBoxSuggestion cbb_TenSPCT;
    private com.Product.GUI.combo_suggestion.ComboBoxSuggestion cbb_ThuongHieuSPCT;
    private com.Product.GUI.combo_suggestion.ComboBoxSuggestion cbb_XuatXu;
    private com.Product.GUI.Combobox combobox16;
    private com.Product.GUI.Combobox combobox17;
    private com.Product.GUI.Combobox combobox18;
    private com.Product.GUI.Combobox combobox19;
    private com.Product.GUI.Combobox combobox20;
    private com.Product.GUI.checkbox.JCheckBoxCustom jCheckBoxCustom1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JPanel jpn_SPCT;
    private javax.swing.JPanel jpn_Them_SPCT;
    private javax.swing.JPanel jpn_Thong_Tin_SPCT;
    private javax.swing.JRadioButton rdoChatLieu;
    private javax.swing.JRadioButton rdoCoAo;
    private javax.swing.JRadioButton rdoDoDay;
    private javax.swing.JRadioButton rdoKichThuoc;
    private javax.swing.JRadioButton rdoMauSac;
    private javax.swing.JRadioButton rdoPhongCach;
    private javax.swing.JRadioButton rdoTatCaThuocTinhSP;
    private javax.swing.JRadioButton rdoThuongHieu;
    private javax.swing.JRadioButton rdoXuatXu;
    private javax.swing.JRadioButton rdo_DangBan;
    private javax.swing.JRadioButton rdo_NgungBan;
    private javax.swing.JRadioButton rdo_TatCa;
    private com.Product.GUI.tabbed.TabbedPaneCustomm tabbedPaneCustomm1;
    private com.Product.GUI.Table tbl_SanPham;
    private com.Product.GUI.Table tbl_San_Pham_Chi_Tiet;
    private com.Product.GUI.Table tbl_Thuoc_Tinh_San_Pham;
    private com.Product.GUI.textfield.TextField textField11;
    private com.Product.GUI.textfield.TextField textField4;
    private com.Product.GUI.textfield.TextField txtMaSanPham;
    private com.Product.GUI.textfield.TextField txtMaThuocTinh;
    private javax.swing.JTextArea txtMoTaSanPham;
    private com.Product.GUI.textfield.TextField txtTenSanPham;
    private com.Product.GUI.textfield.TextField txtTenThuocTinh;
    private com.Product.GUI.textfield.TextField txt_GiaBanSPCT;
    private com.Product.GUI.textfield.TextField txt_SoLuongSPCT;
    // End of variables declaration//GEN-END:variables
}
