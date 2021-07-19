/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Giao_Dien;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
//import Entities.DocGia;
//simport java.util.Date;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


 
public final class Man_Hinh_Chinh extends javax.swing.JFrame {

    /**
     * Creates new form Man_Hinh_Chinh
     */
    DefaultTableModel modelDG;
    DefaultTableModel modelSach;
    DefaultTableModel modelPhieuMuon;
    DefaultTableModel modelPhieuTra;
    DefaultTableModel modelPhieuPhat;
    public Man_Hinh_Chinh() {
        initComponents();
        setLocationRelativeTo(null);
        modelDG = (DefaultTableModel) jTableDocGia.getModel();
        modelSach = (DefaultTableModel) jTableSach.getModel();
        modelPhieuMuon = (DefaultTableModel) jTablePhieuMuon.getModel();
        modelPhieuTra = (DefaultTableModel) jTablePhieuTra.getModel();
        modelPhieuPhat = (DefaultTableModel) jTablePhieuPhat.getModel();
        showList_DocGia();
        showList_Sach();
        showList_PhieuMuon();
        showList_PhieuTra();
        showList_PhieuPhat();
    }
    public void showList_DocGia(){
        KetNoiCSDL KN = new KetNoiCSDL();
        try{
//                String URL;
//                URL = "jdbc:oracle:thin:@localhost:1522:NQL";
//                String username = "QUOCLUU";
//                String password = "abc";
                Connection conn = DriverManager.getConnection(KN.URL ,KN.username, KN.password);
                String sql = "SELECT * FROM DocGia";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    Object objList[]= {rs.getString("MaDocGia"),rs.getString("HoTen"),rs.getString("GioiTinh"),rs.getDate("NgSinh")
                            , rs.getString("SDT"),rs.getString("DiaChi"),rs.getString("Email"),rs.getString("VaiTro")
                        };
                    modelDG.addRow(objList);
                }
                ps.close();
                conn.close();
            }catch(SQLException ex){
                System.out.println(ex);
            }
    }
    public void showList_Sach(){
        KetNoiCSDL KN = new KetNoiCSDL();
        try{
//                String URL;
//                URL = "jdbc:oracle:thin:@localhost:1522:NQL";
//                String username = "QUOCLUU";
//                String password = "abc";
                Connection conn = DriverManager.getConnection(KN.URL ,KN.username, KN.password);
                String sql = "SELECT * FROM Sach";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Object objList[]= {rs.getString("MaSach"),rs.getString("TenSach"),rs.getString("TheLoai"),rs.getInt("SoTrang")
                            , rs.getString("TacGia"),rs.getString("NXB"),rs.getString("ViTri"),rs.getInt("SoLuong"),rs.getString("TinhTrang")
                        };
                    modelSach.addRow(objList);
                }
                ps.close();
                conn.close();
            }catch(SQLException ex){
                System.out.println(ex);
            }
    }
    ///////////
    public void showList_PhieuMuon(){
        KetNoiCSDL KN = new KetNoiCSDL();
        try{
//                String URL;
//                URL = "jdbc:oracle:thin:@localhost:1522:NQL";
//                String username = "QUOCLUU";
//                String password = "abc";
                Connection conn = DriverManager.getConnection(KN.URL ,KN.username, KN.password);
                String sql = "SELECT * FROM PhieuMuonSach";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    Object objList[]= {rs.getString("MaPhieuMuon"),rs.getString("MaDocGia"),rs.getString("MaSach")
                            ,rs.getDate("NgayMuonSach"), rs.getString("TinhTrangPM")};
                    modelPhieuMuon.addRow(objList);
                }
                ps.close();
                conn.close();
            }catch(SQLException ex){
                System.out.println(ex);
            }
    }
    ////////
    public void showList_PhieuTra(){
        KetNoiCSDL KN = new KetNoiCSDL();
        try{
//                String URL;
//                URL = "jdbc:oracle:thin:@localhost:1522:NQL";
//                String username = "QUOCLUU";
//                String password = "abc";
                Connection conn = DriverManager.getConnection(KN.URL ,KN.username, KN.password);
                String sql = "SELECT * FROM PhieuTraSach";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    Object objList[]= {rs.getString("MaPhieuTra"),rs.getString("MaPhieuMuon"),rs.getString("MaDocGia")
                            ,rs.getString("MaSach"),rs.getDate("NgayTraSach"),rs.getInt("SoNgayMuon")
                            , rs.getInt("SoNgayTraTre")};
                    modelPhieuTra.addRow(objList);
                }
                ps.close();
                conn.close();
            }catch(SQLException ex){
                System.out.println(ex);
            }
    }
    ////////////////
    public void showList_PhieuPhat(){
        KetNoiCSDL KN = new KetNoiCSDL();
        try{
//                String URL;
//                URL = "jdbc:oracle:thin:@localhost:1522:NQL";
//                String username = "QUOCLUU";
//                String password = "abc";
                Connection conn = DriverManager.getConnection(KN.URL ,KN.username, KN.password);
                String sql = "SELECT * FROM PhieuPhat";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    Object objList[]= {rs.getString("MaPhieuPhat"),rs.getString("MaDocGia"),rs.getString("MaPhieuTra")
                            , rs.getInt("TienPhat")};
                    modelPhieuPhat.addRow(objList);
                }
                ps.close();
                conn.close();
            }catch(SQLException ex){
                System.out.println(ex);
            }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButtonDangXuat = new javax.swing.JButton();
        jButtonDoiMatKhau = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDocGia = new javax.swing.JTable();
        jTextTimDocGia = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        ButtonXoaDocGia = new javax.swing.JButton();
        jButtonThemDocGia = new javax.swing.JButton();
        ButtonSuaDocGia = new javax.swing.JButton();
        ButtonTimKiemDocGia = new javax.swing.JButton();
        jButtonResetTimDocGia = new javax.swing.JButton();
        jButtonRefreshDocGia = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextTimSach = new javax.swing.JTextField();
        jButtonThemSach = new javax.swing.JButton();
        jButtonXoaSach = new javax.swing.JButton();
        jButtonSuaSach = new javax.swing.JButton();
        jButtonTimSach = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableSach = new javax.swing.JTable();
        jButtonResetTimSach = new javax.swing.JButton();
        jButtonRefreshSach = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablePhieuMuon = new javax.swing.JTable();
        jButtonResetTimPhieuMuon = new javax.swing.JButton();
        jButtonTimPhieuMuon = new javax.swing.JButton();
        jButtonRefreshPhieuMuon = new javax.swing.JButton();
        jButtonThemPhieuMuon = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jTextTimPhieuMuon = new javax.swing.JTextField();
        jButtonSuaPhieuMuon = new javax.swing.JButton();
        jButtonXoaPhieuMuon = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jTextTimPhieuTra = new javax.swing.JTextField();
        jButtonThemPhieuTra = new javax.swing.JButton();
        jButtonXoaPhieuTra = new javax.swing.JButton();
        jButtonSuaPhieuTra = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTablePhieuTra = new javax.swing.JTable();
        jButtonRefreshPhieuTra = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jButtonResetTimPhieuTra = new javax.swing.JButton();
        jButtonTimPhieuTra = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jTextTimPhieuPhat = new javax.swing.JTextField();
        jButtonXoaPhieuPhat = new javax.swing.JButton();
        jButtonTaoHoaDon = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTablePhieuPhat = new javax.swing.JTable();
        jButtonTimPhieuPhat = new javax.swing.JButton();
        jButtonRefreshPhieuPhat = new javax.swing.JButton();
        jButtonResetTimPhieuPhat = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jButtonThongKeSachMuonHet = new javax.swing.JButton();
        jButtonThongKeSachDangCon = new javax.swing.JButton();
        jButtonThongKeSachTheoCacNam = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButtonThongKeTop5 = new javax.swing.JButton();
        jButtonThongKeTungThang = new javax.swing.JButton();
        jTextThongKeTop5 = new javax.swing.JTextField();
        jTextThongKeTheoMaSach = new javax.swing.JTextField();
        jTextThongKeTungThang1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý thư viện");

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jButtonDangXuat.setBackground(new java.awt.Color(0, 204, 255));
        jButtonDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_exit_23px.png"))); // NOI18N
        jButtonDangXuat.setText("Đăng xuất     ");
        jButtonDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDangXuatActionPerformed(evt);
            }
        });

        jButtonDoiMatKhau.setBackground(new java.awt.Color(0, 204, 255));
        jButtonDoiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_password_reset_20px_1.png"))); // NOI18N
        jButtonDoiMatKhau.setText("Đổi mật khẩu");
        jButtonDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDoiMatKhauActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/books-155185_640.png"))); // NOI18N

        jPanel15.setBackground(new java.awt.Color(0, 204, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_home_40px.png"))); // NOI18N
        jLabel6.setText("Trang Chủ Quản Lý Thư Viện");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonDoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(101, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap(101, Short.MAX_VALUE))
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Trang chủ              ", jPanel1);

        jTableDocGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Độc Giả", "Họ Tên", "Giới Tính", "Ngày Sinh", "SĐT", "Địa Chỉ", "Email", "Vai trò"
            }
        ));
        jScrollPane1.setViewportView(jTableDocGia);

        jTextTimDocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextTimDocGiaActionPerformed(evt);
            }
        });

        jLabel1.setText("Từ khoá tìm kiếm: ");

        ButtonXoaDocGia.setBackground(new java.awt.Color(0, 204, 255));
        ButtonXoaDocGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_delete_23px.png"))); // NOI18N
        ButtonXoaDocGia.setText("Xoá");
        ButtonXoaDocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonXoaDocGiaActionPerformed(evt);
            }
        });

        jButtonThemDocGia.setBackground(new java.awt.Color(0, 204, 255));
        jButtonThemDocGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_add_23px.png"))); // NOI18N
        jButtonThemDocGia.setText("Thêm");
        jButtonThemDocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemDocGiaActionPerformed(evt);
            }
        });

        ButtonSuaDocGia.setBackground(new java.awt.Color(0, 204, 255));
        ButtonSuaDocGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_edit_23px.png"))); // NOI18N
        ButtonSuaDocGia.setText("Sửa");
        ButtonSuaDocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSuaDocGiaActionPerformed(evt);
            }
        });

        ButtonTimKiemDocGia.setBackground(new java.awt.Color(0, 204, 255));
        ButtonTimKiemDocGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_search_23px.png"))); // NOI18N
        ButtonTimKiemDocGia.setText("Tìm Kiếm");
        ButtonTimKiemDocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonTimKiemDocGiaActionPerformed(evt);
            }
        });

        jButtonResetTimDocGia.setBackground(new java.awt.Color(0, 204, 255));
        jButtonResetTimDocGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_renew_23px.png"))); // NOI18N
        jButtonResetTimDocGia.setText("Đặt lại");
        jButtonResetTimDocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetTimDocGiaActionPerformed(evt);
            }
        });

        jButtonRefreshDocGia.setBackground(new java.awt.Color(0, 204, 255));
        jButtonRefreshDocGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_refresh_23px.png"))); // NOI18N
        jButtonRefreshDocGia.setText("Làm mới");
        jButtonRefreshDocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshDocGiaActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(0, 204, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_read_online_40px.png"))); // NOI18N
        jLabel2.setText("Quản Lý Độc Giả");
        jLabel2.setPreferredSize(new java.awt.Dimension(176, 27));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(ButtonTimKiemDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonResetTimDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextTimDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ButtonXoaDocGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonSuaDocGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonThemDocGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRefreshDocGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextTimDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ButtonTimKiemDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonResetTimDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonThemDocGia)
                        .addGap(32, 32, 32)
                        .addComponent(ButtonXoaDocGia)
                        .addGap(33, 33, 33)
                        .addComponent(ButtonSuaDocGia)))
                .addGap(33, 33, 33)
                .addComponent(jButtonRefreshDocGia)
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Quản lý độc giả     ", jPanel2);

        jLabel3.setText("Từ khoá tìm kiếm: ");

        jTextTimSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextTimSachActionPerformed(evt);
            }
        });

        jButtonThemSach.setBackground(new java.awt.Color(0, 204, 255));
        jButtonThemSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_add_23px.png"))); // NOI18N
        jButtonThemSach.setText("Thêm");
        jButtonThemSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemSachActionPerformed(evt);
            }
        });

        jButtonXoaSach.setBackground(new java.awt.Color(0, 204, 255));
        jButtonXoaSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_delete_23px.png"))); // NOI18N
        jButtonXoaSach.setText("Xoá");
        jButtonXoaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoaSachActionPerformed(evt);
            }
        });

        jButtonSuaSach.setBackground(new java.awt.Color(0, 204, 255));
        jButtonSuaSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_edit_23px.png"))); // NOI18N
        jButtonSuaSach.setText("Sửa");
        jButtonSuaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuaSachActionPerformed(evt);
            }
        });

        jButtonTimSach.setBackground(new java.awt.Color(0, 204, 255));
        jButtonTimSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_search_23px.png"))); // NOI18N
        jButtonTimSach.setText("Tìm kiếm");
        jButtonTimSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTimSachActionPerformed(evt);
            }
        });

        jTableSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Thể loại", "Số trang", "Tác giả", "NXB", "Vị trí", "Số lượng", "Tình trạng"
            }
        ));
        jScrollPane2.setViewportView(jTableSach);

        jButtonResetTimSach.setBackground(new java.awt.Color(0, 204, 255));
        jButtonResetTimSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_renew_23px.png"))); // NOI18N
        jButtonResetTimSach.setText("Đặt lại");
        jButtonResetTimSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetTimSachActionPerformed(evt);
            }
        });

        jButtonRefreshSach.setBackground(new java.awt.Color(0, 204, 255));
        jButtonRefreshSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_refresh_23px.png"))); // NOI18N
        jButtonRefreshSach.setText("Làm mới");
        jButtonRefreshSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshSachActionPerformed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(0, 204, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_book_40px.png"))); // NOI18N
        jLabel11.setText("Quản Lý Sách");
        jLabel11.setPreferredSize(new java.awt.Dimension(176, 27));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(138, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButtonTimSach, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButtonResetTimSach, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextTimSach, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonThemSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonXoaSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSuaSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRefreshSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(123, Short.MAX_VALUE))
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextTimSach, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonThemSach))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonXoaSach)
                    .addComponent(jButtonTimSach, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonResetTimSach, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jButtonSuaSach)
                .addGap(27, 27, 27)
                .addComponent(jButtonRefreshSach)
                .addGap(56, 56, 56)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Quản lý sách          ", jPanel3);

        jTablePhieuMuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu mượn", "Mã độc giả", "Mã sách", "Ngày mượn sách", "Tình trạng"
            }
        ));
        jScrollPane3.setViewportView(jTablePhieuMuon);

        jButtonResetTimPhieuMuon.setBackground(new java.awt.Color(0, 204, 255));
        jButtonResetTimPhieuMuon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_renew_23px.png"))); // NOI18N
        jButtonResetTimPhieuMuon.setText("Đặt lại");
        jButtonResetTimPhieuMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetTimPhieuMuonActionPerformed(evt);
            }
        });

        jButtonTimPhieuMuon.setBackground(new java.awt.Color(0, 204, 255));
        jButtonTimPhieuMuon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_search_23px.png"))); // NOI18N
        jButtonTimPhieuMuon.setText("Tìm kiếm");
        jButtonTimPhieuMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTimPhieuMuonActionPerformed(evt);
            }
        });

        jButtonRefreshPhieuMuon.setBackground(new java.awt.Color(0, 204, 255));
        jButtonRefreshPhieuMuon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_refresh_23px.png"))); // NOI18N
        jButtonRefreshPhieuMuon.setText("Làm mới");
        jButtonRefreshPhieuMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshPhieuMuonActionPerformed(evt);
            }
        });

        jButtonThemPhieuMuon.setBackground(new java.awt.Color(0, 204, 255));
        jButtonThemPhieuMuon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_add_23px.png"))); // NOI18N
        jButtonThemPhieuMuon.setText("Thêm");
        jButtonThemPhieuMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemPhieuMuonActionPerformed(evt);
            }
        });

        jLabel13.setText("Từ khoá tìm kiếm:");
        jLabel13.setPreferredSize(new java.awt.Dimension(47, 16));

        jButtonSuaPhieuMuon.setBackground(new java.awt.Color(0, 204, 255));
        jButtonSuaPhieuMuon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_edit_23px.png"))); // NOI18N
        jButtonSuaPhieuMuon.setText("Sửa");
        jButtonSuaPhieuMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuaPhieuMuonActionPerformed(evt);
            }
        });

        jButtonXoaPhieuMuon.setBackground(new java.awt.Color(0, 204, 255));
        jButtonXoaPhieuMuon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_delete_23px.png"))); // NOI18N
        jButtonXoaPhieuMuon.setText("Xoá");
        jButtonXoaPhieuMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoaPhieuMuonActionPerformed(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(0, 204, 255));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_books_40px.png"))); // NOI18N
        jLabel16.setText("Quản Lý Mượn Sách");
        jLabel16.setPreferredSize(new java.awt.Dimension(176, 27));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButtonTimPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonResetTimPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextTimPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonThemPhieuMuon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonXoaPhieuMuon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSuaPhieuMuon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRefreshPhieuMuon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextTimPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonTimPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonResetTimPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButtonThemPhieuMuon)
                        .addGap(29, 29, 29)
                        .addComponent(jButtonXoaPhieuMuon)))
                .addGap(32, 32, 32)
                .addComponent(jButtonSuaPhieuMuon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jButtonRefreshPhieuMuon)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("   Quản lý mượn sách   ", jPanel4);

        jLabel17.setText("Mã phiếu trả:");

        jButtonThemPhieuTra.setBackground(new java.awt.Color(0, 204, 255));
        jButtonThemPhieuTra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_add_23px.png"))); // NOI18N
        jButtonThemPhieuTra.setText("Thêm");
        jButtonThemPhieuTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemPhieuTraActionPerformed(evt);
            }
        });

        jButtonXoaPhieuTra.setBackground(new java.awt.Color(0, 204, 255));
        jButtonXoaPhieuTra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_delete_23px.png"))); // NOI18N
        jButtonXoaPhieuTra.setText("Xoá");
        jButtonXoaPhieuTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoaPhieuTraActionPerformed(evt);
            }
        });

        jButtonSuaPhieuTra.setBackground(new java.awt.Color(0, 204, 255));
        jButtonSuaPhieuTra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_edit_23px.png"))); // NOI18N
        jButtonSuaPhieuTra.setText("Sửa");
        jButtonSuaPhieuTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuaPhieuTraActionPerformed(evt);
            }
        });

        jTablePhieuTra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu trả", "Mã phiếu mượn", "Mã độc giả", "Mã sách", "Ngày trả sách", "Số ngày mượn", "Số ngày trả trễ"
            }
        ));
        jScrollPane4.setViewportView(jTablePhieuTra);

        jButtonRefreshPhieuTra.setBackground(new java.awt.Color(0, 204, 255));
        jButtonRefreshPhieuTra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_refresh_23px.png"))); // NOI18N
        jButtonRefreshPhieuTra.setText("Làm mới");
        jButtonRefreshPhieuTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshPhieuTraActionPerformed(evt);
            }
        });

        jPanel14.setBackground(new java.awt.Color(0, 204, 255));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_books_40px_1.png"))); // NOI18N
        jLabel21.setText("Quản Lý Trả Sách");
        jLabel21.setPreferredSize(new java.awt.Dimension(176, 27));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jButtonResetTimPhieuTra.setBackground(new java.awt.Color(0, 204, 255));
        jButtonResetTimPhieuTra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_renew_23px.png"))); // NOI18N
        jButtonResetTimPhieuTra.setText("Đặt lại");
        jButtonResetTimPhieuTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetTimPhieuTraActionPerformed(evt);
            }
        });

        jButtonTimPhieuTra.setBackground(new java.awt.Color(0, 204, 255));
        jButtonTimPhieuTra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_search_23px.png"))); // NOI18N
        jButtonTimPhieuTra.setText("Tìm kiếm");
        jButtonTimPhieuTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTimPhieuTraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(47, 47, 47)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextTimPhieuTra, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jButtonTimPhieuTra, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonResetTimPhieuTra, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonThemPhieuTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonXoaPhieuTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSuaPhieuTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRefreshPhieuTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonThemPhieuTra)
                        .addComponent(jTextTimPhieuTra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonXoaPhieuTra)
                    .addComponent(jButtonResetTimPhieuTra, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTimPhieuTra, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jButtonSuaPhieuTra)
                .addGap(28, 28, 28)
                .addComponent(jButtonRefreshPhieuTra)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý trả sách     ", jPanel5);

        jLabel22.setText("Mã phiếu phạt:");

        jButtonXoaPhieuPhat.setBackground(new java.awt.Color(0, 204, 255));
        jButtonXoaPhieuPhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_delete_23px.png"))); // NOI18N
        jButtonXoaPhieuPhat.setText("Xoá");
        jButtonXoaPhieuPhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoaPhieuPhatActionPerformed(evt);
            }
        });

        jButtonTaoHoaDon.setBackground(new java.awt.Color(0, 204, 255));
        jButtonTaoHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_create_23px.png"))); // NOI18N
        jButtonTaoHoaDon.setText("Tạo hoá đơn");
        jButtonTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTaoHoaDonActionPerformed(evt);
            }
        });

        jTablePhieuPhat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu phạt", "Mã độc giả", "Mã phiếu trả", "Tiền phạt"
            }
        ));
        jScrollPane5.setViewportView(jTablePhieuPhat);

        jButtonTimPhieuPhat.setBackground(new java.awt.Color(0, 204, 255));
        jButtonTimPhieuPhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_search_23px.png"))); // NOI18N
        jButtonTimPhieuPhat.setText("Tìm kiếm");
        jButtonTimPhieuPhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTimPhieuPhatActionPerformed(evt);
            }
        });

        jButtonRefreshPhieuPhat.setBackground(new java.awt.Color(0, 204, 255));
        jButtonRefreshPhieuPhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_refresh_23px.png"))); // NOI18N
        jButtonRefreshPhieuPhat.setText("Làm mới");
        jButtonRefreshPhieuPhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshPhieuPhatActionPerformed(evt);
            }
        });

        jButtonResetTimPhieuPhat.setBackground(new java.awt.Color(0, 204, 255));
        jButtonResetTimPhieuPhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_renew_23px.png"))); // NOI18N
        jButtonResetTimPhieuPhat.setText("Đặt lại");
        jButtonResetTimPhieuPhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetTimPhieuPhatActionPerformed(evt);
            }
        });

        jPanel12.setBackground(new java.awt.Color(0, 204, 255));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_document_40px.png"))); // NOI18N
        jLabel25.setText("Quản Lý Phiếu Phạt");
        jLabel25.setPreferredSize(new java.awt.Dimension(176, 27));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(151, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButtonTimPhieuPhat, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonResetTimPhieuPhat, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextTimPhieuPhat, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonRefreshPhieuPhat)
                    .addComponent(jButtonTaoHoaDon)
                    .addComponent(jButtonXoaPhieuPhat, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonResetTimPhieuPhat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonTimPhieuPhat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(159, 159, 159))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextTimPhieuPhat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addComponent(jButtonXoaPhieuPhat)
                        .addGap(56, 56, 56)
                        .addComponent(jButtonRefreshPhieuPhat)
                        .addGap(58, 58, 58)))
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý phiếu phạt", jPanel6);

        jButtonThongKeSachMuonHet.setBackground(new java.awt.Color(0, 204, 255));
        jButtonThongKeSachMuonHet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonThongKeSachMuonHet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_stop_graph_report_23px.png"))); // NOI18N
        jButtonThongKeSachMuonHet.setText("Thống kê sách đã mượn hết");
        jButtonThongKeSachMuonHet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThongKeSachMuonHetActionPerformed(evt);
            }
        });

        jButtonThongKeSachDangCon.setBackground(new java.awt.Color(0, 204, 255));
        jButtonThongKeSachDangCon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonThongKeSachDangCon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_play_graph_report_23px.png"))); // NOI18N
        jButtonThongKeSachDangCon.setText("Thông kê sách đang có");
        jButtonThongKeSachDangCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThongKeSachDangConActionPerformed(evt);
            }
        });

        jButtonThongKeSachTheoCacNam.setBackground(new java.awt.Color(0, 204, 255));
        jButtonThongKeSachTheoCacNam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonThongKeSachTheoCacNam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_graph_report_script_23px_1.png"))); // NOI18N
        jButtonThongKeSachTheoCacNam.setText("Thống kê số lượt mượn sách theo các năm");
        jButtonThongKeSachTheoCacNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThongKeSachTheoCacNamActionPerformed(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(0, 204, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_edit_graph_report_40px.png"))); // NOI18N
        jLabel5.setText("Báo Cáo - Thống Kê");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jButtonThongKeTop5.setBackground(new java.awt.Color(0, 204, 255));
        jButtonThongKeTop5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonThongKeTop5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_graph_report_script_23px_1.png"))); // NOI18N
        jButtonThongKeTop5.setText("Thống kê Top 5 sách được mượn nhiều nhất theo năm");
        jButtonThongKeTop5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThongKeTop5ActionPerformed(evt);
            }
        });

        jButtonThongKeTungThang.setBackground(new java.awt.Color(0, 204, 255));
        jButtonThongKeTungThang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonThongKeTungThang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_graph_report_script_23px_1.png"))); // NOI18N
        jButtonThongKeTungThang.setText("Thống kê số lượt mượn theo từng tháng trong năm");
        jButtonThongKeTungThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThongKeTungThangActionPerformed(evt);
            }
        });

        jTextThongKeTop5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jTextThongKeTheoMaSach.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextThongKeTheoMaSach.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextThongKeTheoMaSach.setToolTipText("");
        jTextThongKeTheoMaSach.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextThongKeTheoMaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextThongKeTheoMaSachActionPerformed(evt);
            }
        });

        jTextThongKeTungThang1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextThongKeTungThang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextThongKeTungThang1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(227, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jButtonThongKeTop5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextThongKeTop5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButtonThongKeSachDangCon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonThongKeSachTheoCacNam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonThongKeTungThang, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                            .addComponent(jButtonThongKeSachMuonHet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextThongKeTheoMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextThongKeTungThang1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(jButtonThongKeSachMuonHet, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jButtonThongKeSachDangCon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonThongKeSachTheoCacNam, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextThongKeTheoMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonThongKeTungThang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextThongKeTungThang1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonThongKeTop5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextThongKeTop5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(146, 146, 146))
        );

        jTabbedPane1.addTab("Báo cáo, thống kê  ", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 694, Short.MAX_VALUE)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextTimDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextTimDocGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextTimDocGiaActionPerformed

    private void ButtonXoaDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonXoaDocGiaActionPerformed
    KetNoiCSDL KN = new KetNoiCSDL();
    Object[] options = {"Có", "Không"};
//    int dialogButton = JOptionPane.showOptionDialog(null,"Bạn có chắc chắn muốn xoá không?","Thông Báo!",
//                       JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
    int row = jTableDocGia.getSelectedRow();
    DefaultTableModel model = (DefaultTableModel) jTableDocGia.getModel();
    if(row<0){
        JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xoá!");
    }
    else{
        int dialogButton = JOptionPane.showOptionDialog(null,"Bạn có chắc chắn muốn xoá không?","Thông Báo!",
                       JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        if(dialogButton == 0) {
            String ID;
            ID = jTableDocGia.getValueAt(row, 0).toString();
            try{
//                String URL;
//                URL = "jdbc:oracle:thin:@localhost:1522:NQL";
//                String username = "QUOCLUU";
//                String password = "abc";
                Connection conn = DriverManager.getConnection(KN.URL ,KN.username, KN.password);
                String sql = "DELETE FROM DocGia WHERE MaDocGia = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ID);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    model.removeRow(row);
                    JOptionPane.showMessageDialog(this, "Xoá thành công!"); 
                }
                else{
                    JOptionPane.showMessageDialog(this, "Xoá thất bại!");
                }
                ps.close();
                conn.close();
            }catch(HeadlessException | SQLException ex){
                System.out.println(ex);
            }
//            jTextMaDocGia.setText(a); 
        } 
        if(dialogButton == 1) {
            remove(dialogButton);
        }
    }
    
    }//GEN-LAST:event_ButtonXoaDocGiaActionPerformed

    private void ButtonTimKiemDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonTimKiemDocGiaActionPerformed
        modelDG.fireTableDataChanged();//cập nhật lại toàn bộ dữ liệu bảng khi nút tìm được nhấn để hiển thị kết quả
        /* Sử dụng table sorter ở đây để lọc ra dòng có chứa từ khóa đã được nhập và giữ lại dòng có chứ từ khóa đó sau khi cập nhật bảng ở trên*/
        TableRowSorter sorter = new TableRowSorter(modelDG);
        jTableDocGia.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(jTextTimDocGia.getText()));
    }//GEN-LAST:event_ButtonTimKiemDocGiaActionPerformed

    private void jButtonThemDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemDocGiaActionPerformed
        Doc_Gia DG = new Doc_Gia();//khởi tạo đối tượng
        DG.setVisible(true);//hiển thị màn hình độc giả
        //this.dispose();//tắt màn hình hiện tại
        
    }//GEN-LAST:event_jButtonThemDocGiaActionPerformed

    private void jTextTimSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextTimSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextTimSachActionPerformed

    private void jButtonResetTimDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetTimDocGiaActionPerformed
        jTextTimDocGia.setText("");
    }//GEN-LAST:event_jButtonResetTimDocGiaActionPerformed

    private void ButtonSuaDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSuaDocGiaActionPerformed
        int row = jTableDocGia.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) jTableDocGia.getModel();
        if(row<0){
            JOptionPane.showMessageDialog(this, "Vui lòng chon dòng cần sửa!");
        }else{
            String ID;
            ID = jTableDocGia.getValueAt(row, 0).toString();
            Doc_Gia DG = new Doc_Gia();//khởi tạo đối tượng
            DG.Select_DocGia = ID;
            DG.setVisible(true);//hiển thị màn hình độc giả 
        }
//model.setRowCount(0);xoá dữ liệu trong 
        
    }//GEN-LAST:event_ButtonSuaDocGiaActionPerformed

    private void jButtonRefreshDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshDocGiaActionPerformed
        
        modelDG.setRowCount(0);
        showList_DocGia();
        
        
    }//GEN-LAST:event_jButtonRefreshDocGiaActionPerformed

    private void jButtonResetTimSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetTimSachActionPerformed
        jTextTimSach.setText("");
    }//GEN-LAST:event_jButtonResetTimSachActionPerformed

    private void jButtonThemSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemSachActionPerformed
        Form_ThongTinSach S = new Form_ThongTinSach();//khởi tạo đối tượng
        S.setVisible(true);//hiển thị màn hình độc giả
    }//GEN-LAST:event_jButtonThemSachActionPerformed

    private void jButtonRefreshSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshSachActionPerformed
        modelSach.setRowCount(0);
        showList_Sach();
    }//GEN-LAST:event_jButtonRefreshSachActionPerformed

    private void jButtonRefreshPhieuMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshPhieuMuonActionPerformed
        modelPhieuMuon.setRowCount(0);
        showList_PhieuMuon();
    }//GEN-LAST:event_jButtonRefreshPhieuMuonActionPerformed

    private void jButtonRefreshPhieuTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshPhieuTraActionPerformed
        modelPhieuTra.setRowCount(0);
        showList_PhieuTra();
    }//GEN-LAST:event_jButtonRefreshPhieuTraActionPerformed

    private void jButtonRefreshPhieuPhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshPhieuPhatActionPerformed
        modelPhieuPhat.setRowCount(0);
        showList_PhieuPhat();
    }//GEN-LAST:event_jButtonRefreshPhieuPhatActionPerformed

    private void jButtonTimSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimSachActionPerformed
        modelSach.fireTableDataChanged();//cập nhật lại toàn bộ dữ liệu bảng khi nút tìm được nhấn để hiển thị kết quả
        /* Sử dụng table sorter ở đây để lọc ra dòng có chứa từ khóa đã được nhập và giữ lại dòng có chứ từ khóa đó sau khi cập nhật bảng ở trên*/
        TableRowSorter sorter = new TableRowSorter(modelSach);
        jTableSach.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(jTextTimSach.getText()));
    }//GEN-LAST:event_jButtonTimSachActionPerformed

    private void jButtonTimPhieuMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimPhieuMuonActionPerformed
        modelPhieuMuon.fireTableDataChanged();//cập nhật lại toàn bộ dữ liệu bảng khi nút tìm được nhấn để hiển thị kết quả
        /* Sử dụng table sorter ở đây để lọc ra dòng có chứa từ khóa đã được nhập và giữ lại dòng có chứ từ khóa đó sau khi cập nhật bảng ở trên*/
        TableRowSorter sorter = new TableRowSorter(modelPhieuMuon);
        jTablePhieuMuon.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(jTextTimPhieuMuon.getText()));
    }//GEN-LAST:event_jButtonTimPhieuMuonActionPerformed

    private void jButtonTimPhieuTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimPhieuTraActionPerformed
        modelPhieuTra.fireTableDataChanged();//cập nhật lại toàn bộ dữ liệu bảng khi nút tìm được nhấn để hiển thị kết quả
        /* Sử dụng table sorter ở đây để lọc ra dòng có chứa từ khóa đã được nhập và giữ lại dòng có chứ từ khóa đó sau khi cập nhật bảng ở trên*/
        TableRowSorter sorter = new TableRowSorter(modelPhieuTra);
        jTablePhieuTra.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(jTextTimPhieuTra.getText()));
    }//GEN-LAST:event_jButtonTimPhieuTraActionPerformed

    private void jButtonTimPhieuPhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimPhieuPhatActionPerformed
        modelPhieuPhat.fireTableDataChanged();//cập nhật lại toàn bộ dữ liệu bảng khi nút tìm được nhấn để hiển thị kết quả
        /* Sử dụng table sorter ở đây để lọc ra dòng có chứa từ khóa đã được nhập và giữ lại dòng có chứ từ khóa đó sau khi cập nhật bảng ở trên*/
        TableRowSorter sorter = new TableRowSorter(modelPhieuPhat);
        jTablePhieuPhat.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(jTextTimPhieuPhat.getText()));
    }//GEN-LAST:event_jButtonTimPhieuPhatActionPerformed

    private void jButtonResetTimPhieuMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetTimPhieuMuonActionPerformed
        jTextTimPhieuMuon.setText("");
    }//GEN-LAST:event_jButtonResetTimPhieuMuonActionPerformed

    private void jButtonResetTimPhieuTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetTimPhieuTraActionPerformed
        jTextTimPhieuTra.setText("");
    }//GEN-LAST:event_jButtonResetTimPhieuTraActionPerformed

    private void jButtonResetTimPhieuPhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetTimPhieuPhatActionPerformed
        jTextTimPhieuPhat.setText("");
    }//GEN-LAST:event_jButtonResetTimPhieuPhatActionPerformed

    private void jButtonThemPhieuMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemPhieuMuonActionPerformed
        Form_ThongTinPhieuMuon PM = new Form_ThongTinPhieuMuon();
        PM.setVisible(true);
    }//GEN-LAST:event_jButtonThemPhieuMuonActionPerformed

    private void jButtonThemPhieuTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemPhieuTraActionPerformed
        Form_ThongTinPhieuTra PT = new Form_ThongTinPhieuTra();
        PT.setVisible(true);
    }//GEN-LAST:event_jButtonThemPhieuTraActionPerformed

    private void jButtonXoaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaSachActionPerformed
    KetNoiCSDL KN = new KetNoiCSDL();
        Object[] options = {"Có", "Không"};
    int row = jTableSach.getSelectedRow();
    DefaultTableModel model = (DefaultTableModel) jTableSach.getModel();
    if(row<0){
        JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xoá!");
    }
    else{
        int dialogButton = JOptionPane.showOptionDialog(null,"Bạn có chắc chắn muốn xoá không?","Thông Báo!",
                       JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        if(dialogButton == 0) {
            String ID;
            ID = jTableSach.getValueAt(row, 0).toString();
            try{
//                String URL;
//                URL = "jdbc:oracle:thin:@localhost:1522:NQL";
//                String username = "QUOCLUU";
//                String password = "abc";
                Connection conn = DriverManager.getConnection(KN.URL ,KN.username, KN.password);
                String sql = "DELETE FROM SACH WHERE MaSach = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ID);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    model.removeRow(row);
                    JOptionPane.showMessageDialog(this, "Xoá thành công!"); 
                }
                else{
                    JOptionPane.showMessageDialog(this, "Xoá thất bại!");
                }
                ps.close();
                conn.close();
            }catch(HeadlessException | SQLException ex){
                JOptionPane.showMessageDialog(this,ex);
            }
        } 
        if(dialogButton == 1) {
            remove(dialogButton);
        }
    }
    }//GEN-LAST:event_jButtonXoaSachActionPerformed

    private void jButtonXoaPhieuMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaPhieuMuonActionPerformed
    KetNoiCSDL KN = new KetNoiCSDL();
    Object[] options = {"Có", "Không"};
    int row = jTablePhieuMuon.getSelectedRow();
    DefaultTableModel model = (DefaultTableModel) jTablePhieuMuon.getModel();
    if(row<0){
        JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xoá!");
    }
    else{
        int dialogButton = JOptionPane.showOptionDialog(null,"Bạn có chắc chắn muốn xoá không?","Thông Báo!",
                       JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        if(dialogButton == 0) {
            String ID;
            ID = jTablePhieuMuon.getValueAt(row, 0).toString();
            try{
//                String URL;
//                URL = "jdbc:oracle:thin:@localhost:1522:NQL";
//                String username = "QUOCLUU";
//                String password = "abc";
                Connection conn = DriverManager.getConnection(KN.URL ,KN.username, KN.password);
                String sql = "DELETE FROM PHIEUMUONSACH WHERE MaPhieuMuon = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ID);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    model.removeRow(row);
                    JOptionPane.showMessageDialog(this, "Xoá thành công!"); 
                }
                else{
                    JOptionPane.showMessageDialog(this, "Xoá thất bại!");
                }
                ps.close();
                conn.close();
            }catch(HeadlessException | SQLException ex){
                JOptionPane.showMessageDialog(this, "Xoá thất bại!");
            }
        } 
        if(dialogButton == 1) {
            remove(dialogButton);
        }
    }
    }//GEN-LAST:event_jButtonXoaPhieuMuonActionPerformed

    private void jButtonXoaPhieuTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaPhieuTraActionPerformed
    KetNoiCSDL KN = new KetNoiCSDL();
    Object[] options = {"Có", "Không"};
    int row = jTablePhieuTra.getSelectedRow();
    DefaultTableModel model = (DefaultTableModel) jTablePhieuTra.getModel();
    if(row<0){
        JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xoá!");
    }
    else{
        int dialogButton = JOptionPane.showOptionDialog(null,"Bạn có chắc chắn muốn xoá không?","Thông Báo!",
                       JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        if(dialogButton == 0) {
            String ID;
            ID = jTablePhieuTra.getValueAt(row, 0).toString();
            try{
//                String URL;
//                URL = "jdbc:oracle:thin:@localhost:1522:NQL";
//                String username = "QUOCLUU";
//                String password = "abc";
                Connection conn = DriverManager.getConnection(KN.URL ,KN.username, KN.password);
                String sql = "DELETE FROM PHIEUTRASACH WHERE MaPhieuTra = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ID);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    model.removeRow(row);
                    JOptionPane.showMessageDialog(this, "Xoá thành công!"); 
                }
                else{
                    JOptionPane.showMessageDialog(this, "Xoá thất bại!");
                }
                ps.close();
                conn.close();
            }catch(HeadlessException | SQLException ex){
                JOptionPane.showMessageDialog(this, "Xoá thất bại!");
            }
        } 
        if(dialogButton == 1) {
            remove(dialogButton);
        }
    }
    }//GEN-LAST:event_jButtonXoaPhieuTraActionPerformed

    private void jButtonXoaPhieuPhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaPhieuPhatActionPerformed
    KetNoiCSDL KN = new KetNoiCSDL();
    Object[] options = {"Có", "Không"};
    int row = jTablePhieuPhat.getSelectedRow();
    DefaultTableModel model = (DefaultTableModel) jTablePhieuPhat.getModel();
    if(row<0){
        JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xoá!");
    }
    else{
        int dialogButton = JOptionPane.showOptionDialog(null,"Bạn có chắc chắn muốn xoá không?","Thông Báo!",
                       JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        if(dialogButton == 0) {
            String ID;
            ID = jTablePhieuPhat.getValueAt(row, 0).toString();
            try{
//                String URL;
//                URL = "jdbc:oracle:thin:@localhost:1522:NQL";
//                String username = "QUOCLUU";
//                String password = "abc";
                Connection conn = DriverManager.getConnection(KN.URL ,KN.username, KN.password);
                String sql = "DELETE FROM PHIEUPHAT WHERE MaPhieuPhat = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ID);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    model.removeRow(row);
                    JOptionPane.showMessageDialog(this, "Xoá thành công!"); 
                }
                else{
                    JOptionPane.showMessageDialog(this, "Xoá thất bại!");
                }
                ps.close();
                conn.close();
            }catch(HeadlessException | SQLException ex){
                JOptionPane.showMessageDialog(this, "Xoá thất bại!");
            }
        } 
        if(dialogButton == 1) {
            remove(dialogButton);
        }
    }
    }//GEN-LAST:event_jButtonXoaPhieuPhatActionPerformed

    private void jButtonSuaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuaSachActionPerformed
        int row = jTableSach.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) jTableSach.getModel();
        if(row<0){
            JOptionPane.showMessageDialog(this, "Vui lòng chon dòng cần sửa!");
        }else{
            String ID;
            ID = jTableSach.getValueAt(row, 0).toString();
            Form_ThongTinSach S = new Form_ThongTinSach();//khởi tạo đối tượng
            S.Select_Sach = ID;
            S.setVisible(true);
        }
    }//GEN-LAST:event_jButtonSuaSachActionPerformed

    private void jButtonSuaPhieuMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuaPhieuMuonActionPerformed
        int row = jTablePhieuMuon.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) jTablePhieuMuon.getModel();
        if(row<0){
            JOptionPane.showMessageDialog(this, "Vui lòng chon dòng cần sửa!");
        }else{
            String ID;
            ID = jTablePhieuMuon.getValueAt(row, 0).toString();
            Form_ThongTinPhieuMuon PM = new Form_ThongTinPhieuMuon();//khởi tạo đối tượng
            PM.Select_PhieuMuon = ID;
            PM.setVisible(true);
        }
    }//GEN-LAST:event_jButtonSuaPhieuMuonActionPerformed

    private void jButtonSuaPhieuTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuaPhieuTraActionPerformed
        int row = jTablePhieuTra.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) jTablePhieuTra.getModel();
        if(row<0){
            JOptionPane.showMessageDialog(this, "Vui lòng chon dòng cần sửa!");
        }else{
            String ID;
            ID = jTablePhieuTra.getValueAt(row, 0).toString();
            Form_ThongTinPhieuTra PT = new Form_ThongTinPhieuTra();//khởi tạo đối tượng
            PT.Select_PhieuTra = ID;
            PT.setVisible(true);
        }
    }//GEN-LAST:event_jButtonSuaPhieuTraActionPerformed

    private void jButtonDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDangXuatActionPerformed
        Dang_Nhap DN = new Dang_Nhap();//khởi tạo đối tượng
        DN.setVisible(true);//hiển thị màn hình độc giả
        this.dispose();//tắt màn hình hiện tại

    }//GEN-LAST:event_jButtonDangXuatActionPerformed

    private void jButtonDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDoiMatKhauActionPerformed
        Form_DoiMatKhau DMK = new Form_DoiMatKhau();//khởi tạo đối tượng
        DMK.setVisible(true);
    }//GEN-LAST:event_jButtonDoiMatKhauActionPerformed

    private void jButtonTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTaoHoaDonActionPerformed
        KetNoiCSDL KN = new KetNoiCSDL();
        int row = jTablePhieuPhat.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) jTablePhieuPhat.getModel();
        if(row<0){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu phạt!");
        }else{
            String ID;
            ID = jTablePhieuPhat.getValueAt(row, 0).toString();
            try{
            
//                String URL;
//                URL = "jdbc:oracle:thin:@localhost:1522:NQL";
//                String username = "QUOCLUU";
//                String password = "abc";
                Connection conn = DriverManager.getConnection(KN.URL ,KN.username, KN.password);
                Hashtable map =new Hashtable();
                JasperReport report = JasperCompileManager.compileReport("src\\Report\\hoadonphieuphat.jrxml");
                map.put("Mã Phiếu Phạt", ID);
                JasperPrint p = JasperFillManager.fillReport(report,map,conn);
                JasperViewer.viewReport(p,false);
                conn.close();
            
            }catch(JRException ex){
                System.out.println(ex.getMessage());
            } catch (SQLException ex) {
                Logger.getLogger(Man_Hinh_Chinh.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonTaoHoaDonActionPerformed

    private void jButtonThongKeSachMuonHetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThongKeSachMuonHetActionPerformed
        KetNoiCSDL KN = new KetNoiCSDL();
        try{
            
//            String URL;
//            URL = "jdbc:oracle:thin:@localhost:1522:NQL";
//            String username = "QUOCLUU";
//            String password = "abc";
            Connection conn = DriverManager.getConnection(KN.URL ,KN.username, KN.password);
           // Hashtable map =new Hashtable();
           //JasperDesign jD = JRXmlLoader.load("src\\Report\\SachCon1.jrxml");
            JasperReport report = JasperCompileManager.compileReport("src\\Report\\SachHet.jrxml");
            
            JasperPrint p = JasperFillManager.fillReport(report,null,conn);
            JasperViewer.viewReport(p,false);
            conn.close();
        }catch(JRException ex){
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(Man_Hinh_Chinh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonThongKeSachMuonHetActionPerformed

    private void jButtonThongKeSachDangConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThongKeSachDangConActionPerformed
        KetNoiCSDL KN = new KetNoiCSDL();
        try{
            
//            String URL;
//            URL = "jdbc:oracle:thin:@localhost:1522:NQL";
//            String username = "QUOCLUU";
//            String password = "abc";
            Connection conn = DriverManager.getConnection(KN.URL ,KN.username, KN.password);
           // Hashtable map =new Hashtable();
           //JasperDesign jD = JRXmlLoader.load("src\\Report\\SachCon1.jrxml");
            JasperReport report = JasperCompileManager.compileReport("src\\Report\\SachCon.jrxml");
            
            JasperPrint p = JasperFillManager.fillReport(report,null,conn);
            JasperViewer.viewReport(p,false);
            conn.close();
        }catch(JRException ex){
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(Man_Hinh_Chinh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonThongKeSachDangConActionPerformed

    private void jTextThongKeTheoMaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextThongKeTheoMaSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextThongKeTheoMaSachActionPerformed

    private void jButtonThongKeSachTheoCacNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThongKeSachTheoCacNamActionPerformed
        KetNoiCSDL KN = new KetNoiCSDL();
        String ID = jTextThongKeTheoMaSach.getText();
        try {
            Connection conn = DriverManager.getConnection(KN.URL, KN.username, KN.password);
            Hashtable map = new Hashtable();
            JasperReport report = JasperCompileManager.compileReport("src\\Report\\SachMuonNhieuNhatNamof.jrxml");
            map.put("Mã Sách", ID);
            JasperPrint p = JasperFillManager.fillReport(report, map, conn);
            JasperViewer.viewReport(p, false);
            conn.close();
        } catch (JRException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(Man_Hinh_Chinh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonThongKeSachTheoCacNamActionPerformed

    private void jButtonThongKeTungThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThongKeTungThangActionPerformed
        KetNoiCSDL KN = new KetNoiCSDL();
        String ID = jTextThongKeTungThang1.getText();
        try {

//            String URL;
//            URL = "jdbc:oracle:thin:@localhost:1522:NQL";
//            String username = "QUOCLUU";
//            String password = "abc";
            Connection conn = DriverManager.getConnection(KN.URL, KN.username, KN.password);
            Hashtable map = new Hashtable();
            JasperReport report = JasperCompileManager.compileReport("src\\Report\\SachMuonNhieuNhatThang.jrxml");
            map.put("Năm", ID);
            JasperPrint p = JasperFillManager.fillReport(report, map, conn);
            JasperViewer.viewReport(p, false);
            conn.close();
        } catch (JRException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(Man_Hinh_Chinh.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonThongKeTungThangActionPerformed

    private void jButtonThongKeTop5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThongKeTop5ActionPerformed
        KetNoiCSDL KN = new KetNoiCSDL();
        String ID = jTextThongKeTop5.getText();
        try {

//            String URL;
//            URL = "jdbc:oracle:thin:@localhost:1522:NQL";
//            String username = "QUOCLUU";
//            String password = "abc";
            Connection conn = DriverManager.getConnection(KN.URL, KN.username, KN.password);
            Hashtable map = new Hashtable();
            JasperReport report = JasperCompileManager.compileReport("src\\Report\\TopSachMuon.jrxml");
            map.put("Năm", ID);
            JasperPrint p = JasperFillManager.fillReport(report, map, conn);
            JasperViewer.viewReport(p, false);
            conn.close();
        } catch (JRException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(Man_Hinh_Chinh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonThongKeTop5ActionPerformed

    private void jTextThongKeTungThang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextThongKeTungThang1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextThongKeTungThang1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Man_Hinh_Chinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Man_Hinh_Chinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Man_Hinh_Chinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Man_Hinh_Chinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Man_Hinh_Chinh().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonSuaDocGia;
    private javax.swing.JButton ButtonTimKiemDocGia;
    private javax.swing.JButton ButtonXoaDocGia;
    private javax.swing.JButton jButtonDangXuat;
    private javax.swing.JButton jButtonDoiMatKhau;
    private javax.swing.JButton jButtonRefreshDocGia;
    private javax.swing.JButton jButtonRefreshPhieuMuon;
    private javax.swing.JButton jButtonRefreshPhieuPhat;
    private javax.swing.JButton jButtonRefreshPhieuTra;
    private javax.swing.JButton jButtonRefreshSach;
    private javax.swing.JButton jButtonResetTimDocGia;
    private javax.swing.JButton jButtonResetTimPhieuMuon;
    private javax.swing.JButton jButtonResetTimPhieuPhat;
    private javax.swing.JButton jButtonResetTimPhieuTra;
    private javax.swing.JButton jButtonResetTimSach;
    private javax.swing.JButton jButtonSuaPhieuMuon;
    private javax.swing.JButton jButtonSuaPhieuTra;
    private javax.swing.JButton jButtonSuaSach;
    private javax.swing.JButton jButtonTaoHoaDon;
    private javax.swing.JButton jButtonThemDocGia;
    private javax.swing.JButton jButtonThemPhieuMuon;
    private javax.swing.JButton jButtonThemPhieuTra;
    private javax.swing.JButton jButtonThemSach;
    private javax.swing.JButton jButtonThongKeSachDangCon;
    private javax.swing.JButton jButtonThongKeSachMuonHet;
    private javax.swing.JButton jButtonThongKeSachTheoCacNam;
    private javax.swing.JButton jButtonThongKeTop5;
    private javax.swing.JButton jButtonThongKeTungThang;
    private javax.swing.JButton jButtonTimPhieuMuon;
    private javax.swing.JButton jButtonTimPhieuPhat;
    private javax.swing.JButton jButtonTimPhieuTra;
    private javax.swing.JButton jButtonTimSach;
    private javax.swing.JButton jButtonXoaPhieuMuon;
    private javax.swing.JButton jButtonXoaPhieuPhat;
    private javax.swing.JButton jButtonXoaPhieuTra;
    private javax.swing.JButton jButtonXoaSach;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableDocGia;
    private javax.swing.JTable jTablePhieuMuon;
    private javax.swing.JTable jTablePhieuPhat;
    private javax.swing.JTable jTablePhieuTra;
    private javax.swing.JTable jTableSach;
    private javax.swing.JTextField jTextThongKeTheoMaSach;
    private javax.swing.JTextField jTextThongKeTop5;
    private javax.swing.JTextField jTextThongKeTungThang1;
    private javax.swing.JTextField jTextTimDocGia;
    private javax.swing.JTextField jTextTimPhieuMuon;
    private javax.swing.JTextField jTextTimPhieuPhat;
    private javax.swing.JTextField jTextTimPhieuTra;
    private javax.swing.JTextField jTextTimSach;
    // End of variables declaration//GEN-END:variables

}
