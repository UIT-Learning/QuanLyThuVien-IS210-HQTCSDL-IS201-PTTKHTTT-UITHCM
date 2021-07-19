/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Giao_Dien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.RowFilter;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;



 
public final class Man_Hinh_Phu extends javax.swing.JFrame {

    /**
     * Creates new form Man_Hinh_Chinh
     */
    DefaultTableModel modelSach;

    public Man_Hinh_Phu() {
        initComponents();
        setLocationRelativeTo(null);
        modelSach = (DefaultTableModel) jTableSach.getModel();
        showList_Sach();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextTimSach = new javax.swing.JTextField();
        jButtonTimSach = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableSach = new javax.swing.JTable();
        jButtonResetTimSach = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jButtonDangXuat.setBackground(new java.awt.Color(0, 204, 255));
        jButtonDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_exit_20px.png"))); // NOI18N
        jButtonDangXuat.setText("Đăng xuất");
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

        jPanel2.setBackground(new java.awt.Color(0, 204, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_home_50px_1.png"))); // NOI18N
        jLabel1.setText("Trang Chủ Thư Viện");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/books-155185_640.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(104, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap(123, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonDoiMatKhau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonDangXuat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jLabel2)
                .addGap(137, 137, 137))
        );

        jTabbedPane1.addTab("Trang chủ       ", jPanel1);

        jLabel3.setText("Từ khoá tìm kiếm: ");

        jTextTimSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextTimSachActionPerformed(evt);
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

        jPanel4.setBackground(new java.awt.Color(0, 204, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imges/icons8_book_40px.png"))); // NOI18N
        jLabel11.setText("Thông Tin Sách");
        jLabel11.setPreferredSize(new java.awt.Dimension(176, 27));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButtonTimSach, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonResetTimSach, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextTimSach, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextTimSach, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonTimSach, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonResetTimSach, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(129, 129, 129)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Thông tin sách", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 819, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonResetTimSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetTimSachActionPerformed
        jTextTimSach.setText("");
    }//GEN-LAST:event_jButtonResetTimSachActionPerformed

    private void jButtonTimSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimSachActionPerformed
        modelSach.fireTableDataChanged();//cập nhật lại toàn bộ dữ liệu bảng khi nút tìm được nhấn để hiển thị kết quả
        /* Sử dụng table sorter ở đây để lọc ra dòng có chứa từ khóa đã được nhập và giữ lại dòng có chứ từ khóa đó sau khi cập nhật bảng ở trên*/
        TableRowSorter sorter = new TableRowSorter(modelSach);
        jTableSach.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(jTextTimSach.getText()));
    }//GEN-LAST:event_jButtonTimSachActionPerformed

    private void jTextTimSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextTimSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextTimSachActionPerformed

    private void jButtonDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDoiMatKhauActionPerformed
        Form_DoiMatKhau DMK = new Form_DoiMatKhau();//khởi tạo đối tượng
        DMK.setVisible(true);
    }//GEN-LAST:event_jButtonDoiMatKhauActionPerformed

    private void jButtonDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDangXuatActionPerformed
        Dang_Nhap DN = new Dang_Nhap();//khởi tạo đối tượng
        DN.setVisible(true);//hiển thị màn hình độc giả
        this.dispose();//tắt màn hình hiện tại

    }//GEN-LAST:event_jButtonDangXuatActionPerformed

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
            java.util.logging.Logger.getLogger(Man_Hinh_Phu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Man_Hinh_Phu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Man_Hinh_Phu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Man_Hinh_Phu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Man_Hinh_Phu().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDangXuat;
    private javax.swing.JButton jButtonDoiMatKhau;
    private javax.swing.JButton jButtonResetTimSach;
    private javax.swing.JButton jButtonTimSach;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableSach;
    private javax.swing.JTextField jTextTimSach;
    // End of variables declaration//GEN-END:variables

}