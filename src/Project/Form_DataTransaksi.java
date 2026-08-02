/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Project;
    // mengimpor beberapa kelas penting dari paket java.sql
    import java.sql.SQLException;
    import javax.swing.table.DefaultTableModel;
    import Koneksi.Koneksi;
    import java.sql.Connection;
    import java.text.NumberFormat;
    import java.util.Locale;
    import javax.swing.table.TableColumn;

/**
 *
 * @author LENOVO
 */
public class Form_DataTransaksi extends javax.swing.JFrame {
    
    private void TampilkanData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("No Faktur");
        model.addColumn("Tanggal");
        model.addColumn("Id User");
        model.addColumn("Nama");
        model.addColumn("E-mail");
        model.addColumn("User Name");
        model.addColumn("Akses");
        model.addColumn("Kode Buku");
        model.addColumn("Nama Buku");
        model.addColumn("Satuan");
        model.addColumn("Harga");
        model.addColumn("Stok");
        model.addColumn("Jumlah");
        model.addColumn("Sub Total");

        String caridata = txtcaridata.getText();
        try {
            int no = 1;
            String sql = "SELECT * FROM relasidatatransaksi WHERE nama_buku LIKE "
                    + "'%"+ caridata + "%' or username LIKE '%" + caridata + "%'"
                    + "or no_faktur LIKE '%" + caridata + "%' ORDER BY no_faktur ASC";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            System.out.print(sql);

            while (res.next()) {
                 model.addRow(new Object[]{no++, res.getString(1),
                        res.getString(2), res.getString(3), res.getString(4),
                        res.getString(5), res.getString(6), res.getString(7),
                        res.getString(8), res.getString(9), res.getString(10),
                        res.getString(11), res.getString(12), res.getString(13),
                        res.getString(14)
                });          
            tabel_transaksi.setModel(model);
            TableColumn column;
            tabel_transaksi.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = tabel_transaksi.getColumnModel().getColumn(0);
            column.setPreferredWidth(40);
            column = tabel_transaksi.getColumnModel().getColumn(1);
            column.setPreferredWidth(70);
            column = tabel_transaksi.getColumnModel().getColumn(2);
            column.setPreferredWidth(80);
            column = tabel_transaksi.getColumnModel().getColumn(3);
            column.setPreferredWidth(60);
            column = tabel_transaksi.getColumnModel().getColumn(4);
            column.setPreferredWidth(75);
            column = tabel_transaksi.getColumnModel().getColumn(5);
            column.setPreferredWidth(120);
            column = tabel_transaksi.getColumnModel().getColumn(6);
            column.setPreferredWidth(80);
            column = tabel_transaksi.getColumnModel().getColumn(7);
            column.setPreferredWidth(50);
            column = tabel_transaksi.getColumnModel().getColumn(8);
            column.setPreferredWidth(70);
            column = tabel_transaksi.getColumnModel().getColumn(9);
            column.setPreferredWidth(170);
            column = tabel_transaksi.getColumnModel().getColumn(10);
            column.setPreferredWidth(50);
            column = tabel_transaksi.getColumnModel().getColumn(11);
            column.setPreferredWidth(70);
            column = tabel_transaksi.getColumnModel().getColumn(12);
            column.setPreferredWidth(40);  
            column = tabel_transaksi.getColumnModel().getColumn(13);
            column.setPreferredWidth(50);
            column = tabel_transaksi.getColumnModel().getColumn(14);
            column.setPreferredWidth(75);
        }
        System.out.println("RowCount = " + model.getRowCount());
        System.out.println("ColumnCount = " + model.getColumnCount());  
        long totalsubtotal = 0, totaljumlah = 0;
        int total = model.getRowCount(); // gunakan model, bukan tabel_transaksi
        for (int i = 0; i < total; i++) {
            totalsubtotal = totalsubtotal + Long.parseLong(model.getValueAt(i, 14).toString());
            totaljumlah = totaljumlah + Long.parseLong(model.getValueAt(i, 13).toString());
        }
        txtjumlahpembelian.setText(String.valueOf(totaljumlah));
        txtsubtotal.setText("Rp. " + nf.format(totalsubtotal));
    } catch (SQLException e) {
        System.out.println("Error :" + e.getMessage());
    }
}
    

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Form_DataTransaksi.class.getName());
    NumberFormat nf = NumberFormat.getNumberInstance(new Locale("in", "ID"));

    /**
     * Creates new form Form_DataTransaksi
     */
    public Form_DataTransaksi() {
        initComponents();
        TampilkanData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_transaksi = new javax.swing.JTable();
        btnrefresh = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtjumlahpembelian = new javax.swing.JTextField();
        txtsubtotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtcaridata = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(">>>Aplikasi BUKU<<<");
        setBackground(new java.awt.Color(204, 204, 204));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel20.setBackground(new java.awt.Color(0, 0, 0));
        jLabel20.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel20.setText("AVARTUBOOK");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/001-point-of-service.png"))); // NOI18N
        jLabel1.setText("Manajemen Data Buku");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(84, 84, 84)
                .addComponent(jLabel20)
                .addContainerGap(178, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addComponent(jLabel20))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, -1));

        jPanel2.setBackground(new java.awt.Color(153, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabel_transaksi.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No ", "No Faktur", "Tanggal", "Id User", "Nama", "E-Mail", "User", "Aksesll", "Kode Buku", "Nama Buku", "Satuan", "Harga", "Stok", "Jumlah", "Sub Total"
            }
        ));
        tabel_transaksi.setColumnSelectionAllowed(true);
        tabel_transaksi.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabel_transaksi);
        tabel_transaksi.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 66, 767, 364));

        btnrefresh.setBackground(new java.awt.Color(51, 51, 255));
        btnrefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Sign-Refresh-icon 24x24.png"))); // NOI18N
        btnrefresh.setText("Refresh");
        btnrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefreshActionPerformed(evt);
            }
        });
        jPanel2.add(btnrefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(591, 6, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jLabel3.setText("Data Transaksi Penjualan");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 43, -1, -1));

        jLabel4.setText("Jumlah Pembeli");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 453, 99, -1));

        jLabel5.setText("Sub Total");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 453, -1, -1));

        txtjumlahpembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjumlahpembelianActionPerformed(evt);
            }
        });
        jPanel2.add(txtjumlahpembelian, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 450, 139, -1));

        txtsubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsubtotalActionPerformed(evt);
            }
        });
        jPanel2.add(txtsubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 450, 139, -1));

        jLabel2.setText("Cari Nama Buku/User/No Faktur");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(201, 13, -1, -1));

        txtcaridata.setBackground(new java.awt.Color(204, 204, 204));
        txtcaridata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcaridataActionPerformed(evt);
            }
        });
        txtcaridata.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcaridataKeyReleased(evt);
            }
        });
        jPanel2.add(txtcaridata, new org.netbeans.lib.awtextra.AbsoluteConstraints(378, 10, 207, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 92, 720, 630));

        getAccessibleContext().setAccessibleName(">>>Aplikasi BUKU Qu<<<");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtcaridataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcaridataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcaridataActionPerformed

    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
        // TODO add your handling code here:
        txtcaridata.setText("");
        TampilkanData();
    }//GEN-LAST:event_btnrefreshActionPerformed

    private void txtjumlahpembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjumlahpembelianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjumlahpembelianActionPerformed

    private void txtsubtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsubtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsubtotalActionPerformed

    private void txtcaridataKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcaridataKeyReleased
        // TODO add your handling code here:
        TampilkanData();
    }//GEN-LAST:event_txtcaridataKeyReleased

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Form_DataTransaksi().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnrefresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabel_transaksi;
    private javax.swing.JTextField txtcaridata;
    private javax.swing.JTextField txtjumlahpembelian;
    private javax.swing.JTextField txtsubtotal;
    // End of variables declaration//GEN-END:variables
}
