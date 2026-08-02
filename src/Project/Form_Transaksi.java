
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Project;

    import java.sql.Connection;
    import java.sql.SQLException;
    import Koneksi.Koneksi;
    import java.awt.HeadlessException;
    import java.text.SimpleDateFormat;
    import java.text.NumberFormat;
    import java.util.Date;
    import java.util.Locale;
    import javax.swing.JOptionPane;
    import javax.swing.table.DefaultTableModel;
    import javax.swing.table.TableColumn;
    import java.util.HashMap;
    import java.io.File;
    //import class untuk cetak ireport
    import net.sf.jasperreports.engine.JasperCompileManager;
    import net.sf.jasperreports.engine.JasperFillManager;
    import net.sf.jasperreports.engine.JasperPrint;
    import net.sf.jasperreports.engine.JasperReport;
    import net.sf.jasperreports.engine.design.JasperDesign;
    import net.sf.jasperreports.engine.xml.JRXmlLoader;
    import net.sf.jasperreports.view.JasperViewer;

public class Form_Transaksi extends javax.swing.JFrame 
{
    
      
    private void AUTO_NO_FAKTUR()
    {
        try {
            String sql="SELECT * FROM penjual ORDER BY no_faktur DESC";
            java.sql.Connection conn = (Connection)Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm .executeQuery(sql);
            if (res.next())
            {                
                String id = res.getString("no_faktur").substring(3);
                String AN =""+(Integer.parseInt(id)+1);
                String Nol ="";
                if(AN.length()==1)
                {Nol ="0000";}
                else if (AN.length()==2)
                {Nol ="000";}
                else if (AN.length()==3)
                {Nol ="00";}
                else if (AN.length()==4)
                {Nol ="0";}
                else if (AN.length()==5)
                {Nol ="";}
                txtnofaktur.setText("NFS"+Nol+AN);
            }else{
                txtnofaktur.setText("NFS00001");
            }                
        }catch(SQLException e) {
            System.out.println("Error :" +e.getMessage());
        }
    }
    
    private void Tampilkan_Buku()
    {
        
        DefaultTableModel modelBuku = new DefaultTableModel();
        modelBuku.addColumn("Kode");
        modelBuku.addColumn("Nama buku");
        modelBuku.addColumn("Harga");
        modelBuku.addColumn("Stok");
        
        String caribrg =txtcaribuku.getText();
        try{
            String sql = "SELECT * FROM buku WHERE kd_buku LIKE '%"+caribrg+"%'"+
                    "or nama_buku LIKE '%"+caribrg+"%'";
            java.sql.Connection conn = (Connection)Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm .executeQuery(sql);
            while (res.next())
                {                
                modelBuku.addRow(new Object[]{
                    res.getString(1),res.getString(2),
                    res.getString(4),res.getString(5)
                });
                
                    tabel_buku.setModel(modelBuku);
                    TableColumn column;
                    tabel_buku.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                    column = tabel_buku.getColumnModel().getColumn(0);
                    column.setPreferredWidth(80);
                    column = tabel_buku.getColumnModel().getColumn(1);
                    column.setPreferredWidth(170);
                    column = tabel_buku.getColumnModel().getColumn(2);
                    column.setPreferredWidth(70);
                    column = tabel_buku.getColumnModel().getColumn(3);
                    column.setPreferredWidth(45);
                }               
            }catch(SQLException e) 
            {
            System.out.println("Error :" +e.getMessage());        
            }
    }
    
    private void Judul_Kolom_Keranjang()
    {
            modelisi.addColumn("No");
            modelisi.addColumn("Stok Awal");
            modelisi.addColumn("Stok Akhir");
            modelisi.addColumn("Kode Buku");
            modelisi.addColumn("Nama Buku");
            modelisi.addColumn("harga");
            modelisi.addColumn("Jumlah");
            modelisi.addColumn("Sub Total");
            
            tabel_keranjang.setModel(modelisi);
            TableColumn column;
                tabel_keranjang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                column = tabel_keranjang.getColumnModel().getColumn(0);
                column.setPreferredWidth(50);
                column = tabel_keranjang.getColumnModel().getColumn(2);
                column.setPreferredWidth(70);
                column = tabel_keranjang.getColumnModel().getColumn(3);
                column.setPreferredWidth(70);
                column = tabel_keranjang.getColumnModel().getColumn(4);
                column.setPreferredWidth(190);
                column = tabel_keranjang.getColumnModel().getColumn(5);
                column.setPreferredWidth(80);
                column = tabel_keranjang.getColumnModel().getColumn(6);
                column.setPreferredWidth(55);
                column = tabel_keranjang.getColumnModel().getColumn(7);
                column.setPreferredWidth(90);
    }        
    
    private void Tanggal()
    {
        Date now = new Date();
        tgltransaksi.setDate(now);
    }
    
    private void Load_Data()
    {
        DefaultTableModel model = (DefaultTableModel) tabel_keranjang.getModel();
        Object obj []= new Object[8];
        obj [1] = txtstokawal.getText();
        obj [2] = txtstokakhir.getText();
        obj [3] = txtkodebuku.getText();
        obj [4] = txtnamabuku.getText();
        obj [5] = txtharga.getText();
        obj [6] = txtjumlah.getText();
        obj [7] = txtsubtotal.getText();
        
        model.addRow(obj);
        int baris = model.getRowCount();
        for(int a = 0; a < baris; a ++)
        {
        String no =String.valueOf(a + 1);
        model.setValueAt(no + ".", a, 0);
        }
        tabel_keranjang.setRowHeight(25);
        
    }

    
    private void Kosongkan_1()
    {
        txtkodebuku.setText("");
        txtstokawal.setText("");
        txtstokakhir.setText("");
        txtnamabuku.setText("");
        txtharga.setText("");
        txtjumlah.setText("");
        txtsubtotal.setText("");     
    }
    
    private void Kosongkan_2()
    {
        txtnofaktur.setText("");
        txttotal.setText("");
        txtbayar.setText("");
        txtkembalian.setText("");
        txttampiltotal.setText("");
        AUTO_NO_FAKTUR();
    }
    
    
    private void Total_Biaya()
    {
        int jumlahBaris = tabel_keranjang.getRowCount();
        int totalBiaya = 0;
        int jumlahBuku, hargaBuku;
        for (int i = 0; i < jumlahBaris; i ++){
            hargaBuku = Integer.parseInt (tabel_keranjang.getValueAt(i,5).toString());
            jumlahBuku = Integer.parseInt (tabel_keranjang.getValueAt(i,6).toString());
            totalBiaya = totalBiaya + (jumlahBuku * hargaBuku);
        }
        txttotal.setText(nf.format(totalBiaya));
        txttampiltotal.setText("Rp. "+nf.format(totalBiaya));
    }
    
    //deklarasi metod Tambah_Transaksi
    private void Tambah_Transaksi(){
        int jumlah, harga, total;
        jumlah = Integer.parseInt(txtjumlah.getText());
        harga = Integer.parseInt(txtharga.getText());
        total = jumlah * harga;
        
        txttotal.setText(nf.format(total));
        Total_Biaya();
        Kosongkan_1();
        txtkodebuku.requestFocus();
    }
    
    //deklarasi metod Kosongkan_Keranjang
    private void Kosongkan_Keranjang(){
        //getModel mengembalikan TableModel sebagai data tabel.
        DefaultTableModel model = (DefaultTableModel) tabel_keranjang.getModel();
        //getRowCount() mengembalikan nilai int yang merupakan jumlah baris tabel
        while (model.getRowCount()>0){
        //Menghapus baris pada baris dari model
            model.removeRow(0);
        }
    }
    
    public Form_Transaksi() {
        initComponents();
        //panggil fungsi metod
        AUTO_NO_FAKTUR();//ini di buat setelah berhasil simpan data 1 transaksi
        Tanggal();
        Tampilkan_Buku();
        Judul_Kolom_Keranjang();
        //setEnabled(false)untuk menonaktifkan komponen UI
        txtnofaktur.setEnabled(false);
        tgltransaksi.setEnabled(false);
        //txtiduser.setEnabled(false);
        txtuser.setEnabled(false);
        txtakses.setEnabled(false);
        txtstokawal.setEnabled(false);
        txtstokakhir.setEnabled(false);
        txtnamabuku.setEnabled(false);
        txtharga.setEnabled(false);
        txtsubtotal.setEnabled(false);
        txttotal.setEnabled(false);
        txtkembalian.setEnabled(false);
        txttampiltotal.setEnabled(false);
    }
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Project.Form_Transaksi.class.getName());
    //metode untuk membuat format mata uang
    NumberFormat nf = NumberFormat.getNumberInstance(new Locale("in","ID"));
    
    //modelisi untuk judul kolom tabel isi keranjang
    DefaultTableModel modelisi = new DefaultTableModel();
    
     /**
     * Creates new form Form_DataTransaksi
     */
    
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtnofaktur = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tgltransaksi = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtkodebuku = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtnamabuku = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtharga = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtjumlah = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtsubtotal = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtstokawal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtstokakhir = new javax.swing.JTextField();
        btntambahitem = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_keranjang = new javax.swing.JTable();
        btnhapusitem = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        txttampiltotal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtbayar = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtkembalian = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_buku = new javax.swing.JTable();
        btncetak_simpan = new javax.swing.JButton();
        txtcaribuku = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(">>>Aplikasi MInimarketQu<<<");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/001-point-of-service.png"))); // NOI18N
        jLabel1.setText("Pembayaran Buku");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/005-cashier-1.png"))); // NOI18N

        jLabel18.setText("Pekanbaru, Prov. Riau. Wa 0814512657212");

        jLabel20.setBackground(new java.awt.Color(0, 0, 0));
        jLabel20.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel20.setText("AVARTUBOOK");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(428, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(0, 343, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 60));

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));

        txtnofaktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnofakturActionPerformed(evt);
            }
        });

        jLabel2.setText("kode buku");

        tgltransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tgltransaksiMouseClicked(evt);
            }
        });
        tgltransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tgltransaksiKeyPressed(evt);
            }
        });

        jLabel3.setText("Tanggal Transaksi");

        txtiduser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtiduserActionPerformed(evt);
            }
        });
        txtiduser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtiduserKeyReleased(evt);
            }
        });

        jLabel21.setText("User Id");

        jLabel22.setText("User Nama");

        jLabel23.setText("Hak Akses");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel23))
                    .addComponent(jLabel21))
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtiduser)
                            .addComponent(txtnofaktur, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel22)))
                    .addComponent(txtakses, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtuser, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                    .addComponent(tgltransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(237, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtnofaktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addComponent(tgltransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtiduser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(txtuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(txtakses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 670, 130));

        jPanel4.setBackground(new java.awt.Color(153, 255, 255));

        txtkodebuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkodebukuActionPerformed(evt);
            }
        });
        txtkodebuku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtkodebukuKeyReleased(evt);
            }
        });

        jLabel4.setText("Kode buku");

        jLabel5.setText("Nama buku");

        jLabel6.setText("Harga");

        txtharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthargaActionPerformed(evt);
            }
        });

        jLabel7.setText("Jumlah ");

        txtjumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjumlahActionPerformed(evt);
            }
        });
        txtjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtjumlahKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtjumlahKeyTyped(evt);
            }
        });

        jLabel11.setText("Subtotal");

        txtsubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsubtotalActionPerformed(evt);
            }
        });

        jLabel12.setText("Stok Awal");

        txtstokawal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstokawalActionPerformed(evt);
            }
        });

        jLabel13.setText("Stok Akhir");

        txtstokakhir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstokakhirActionPerformed(evt);
            }
        });

        btntambahitem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/iconplus-sum-24.png"))); // NOI18N
        btntambahitem.setText("Tambah");
        btntambahitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahitemActionPerformed(evt);
            }
        });

        tabel_keranjang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "Stok Awal", "Stok Akhir", "Kode Buku", "Nama Buku"
            }
        ));
        tabel_keranjang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_keranjangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabel_keranjang);

        btnhapusitem.setBackground(new java.awt.Color(255, 0, 0));
        btnhapusitem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Editing-Delete-icon 24x24.png"))); // NOI18N
        btnhapusitem.setText("Hapus");
        btnhapusitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusitemActionPerformed(evt);
            }
        });

        jLabel14.setText("Isi Keranjang");

        jLabel8.setText("Total Belanja");

        txttotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txttotal.setText("0");
        txttotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtkodebuku))
                            .addComponent(jLabel13)
                            .addComponent(txtjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtstokakhir, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtstokawal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtnamabuku, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(btntambahitem)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))))))
                    .addComponent(jLabel7)
                    .addComponent(btnhapusitem))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel14))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnamabuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtkodebuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btntambahitem))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtstokawal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtstokakhir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnhapusitem, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 670, 350));

        jPanel6.setBackground(new java.awt.Color(153, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txttampiltotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txttampiltotal.setText("RP. 0");
        txttampiltotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttampiltotalActionPerformed(evt);
            }
        });
        jPanel6.add(txttampiltotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 100, -1));

        jLabel9.setText("Bayar");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, -1, -1));

        txtbayar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtbayar.setText("0");
        txtbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbayarActionPerformed(evt);
            }
        });
        jPanel6.add(txtbayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 98, 30));

        jLabel16.setText("TOTAL");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, -1, -1));

        txtkembalian.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtkembalian.setText("0");
        txtkembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkembalianActionPerformed(evt);
            }
        });
        jPanel6.add(txtkembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 98, -1));

        jLabel15.setText("Tekan Enter");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 340, -1, -1));

        jLabel10.setText("Kembalian");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, -1, -1));

        tabel_buku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Kode", "Nama Buku", "Harga", "stok"
            }
        ));
        tabel_buku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_bukuMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabel_bukuMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_buku);

        jPanel6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 300, 270));

        btncetak_simpan.setForeground(new java.awt.Color(0, 0, 255));
        btncetak_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Printer-icon.png"))); // NOI18N
        btncetak_simpan.setText("Cetak Struk & Simpan");
        btncetak_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetak_simpanActionPerformed(evt);
            }
        });
        jPanel6.add(btncetak_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 190, 30));

        txtcaribuku.setBackground(new java.awt.Color(204, 204, 204));
        txtcaribuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcaribukuActionPerformed(evt);
            }
        });
        txtcaribuku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcaribukuKeyReleased(evt);
            }
        });
        jPanel6.add(txtcaribuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 159, -1));

        jLabel17.setText("Cari Buku");
        jPanel6.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, 320, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btntambahitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahitemActionPerformed
        // TODO add your handling code here:
            if(txtjumlah.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Masukan Jumlah Buku");
                txtiduser.requestFocus();
            }else{
                Load_Data();
                Tambah_Transaksi();
                Kosongkan_1();
            }
                try {
                    int baris2 = tabel_keranjang.getRowCount();
                    for (int i = 0; i < baris2; i++){
                    String sqlup ="UPDATE buku SET stok='"+tabel_keranjang.getValueAt(i, 2).toString()
                        +"' WHERE kd_buku='"+tabel_keranjang.getValueAt(i, 3).toString()+"'";
                        java.sql.Connection conn = (Connection)Koneksi.configDB();
                        java.sql.PreparedStatement pstm=conn.prepareStatement(sqlup);
                        pstm.execute();
                        pstm.close();
                    }
                      JOptionPane.showMessageDialog(null, "Data Masuk Keranjang");
                      Tampilkan_Buku();
                    } catch (HeadlessException | SQLException e){
                      System.out.println(e);
                      JOptionPane.showMessageDialog(null,"Data Gagal..!");
                    }
    }//GEN-LAST:event_btntambahitemActionPerformed

    private void txtsubtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsubtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsubtotalActionPerformed

    private void txthargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthargaActionPerformed

    private void txtkodebukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkodebukuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkodebukuActionPerformed

    private void txtnofakturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnofakturActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnofakturActionPerformed

    private void txtjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjumlahActionPerformed

    private void btnhapusitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusitemActionPerformed
        // TODO add your handling code here:

        DefaultTableModel model = (DefaultTableModel) tabel_keranjang.getModel();
        try{
        int baris2 = tabel_keranjang.getRowCount();
        for (int i = 0; i < baris2; i++){
        String sqlup ="UPDATE buku SET stok='"+tabel_keranjang.getValueAt(i, 1).toString()
            +"' WHERE kd_buku='"+tabel_keranjang.getValueAt(i, 3).toString()+"'";
            java.sql.Connection conn = (Connection)Koneksi.configDB();
            java.sql.PreparedStatement pstm=conn.prepareStatement(sqlup);
            pstm.execute();
            pstm.close();
        }
            JOptionPane.showMessageDialog(null, "Transaksi Dibatalkan");
            Tampilkan_Buku();
            }catch(HeadlessException | SQLException e){
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"Data Gagal..!");
            }
        int baris = tabel_keranjang.getSelectedRow();
        model.removeRow(baris);
        tabel_keranjang.setRowHeight(25);
        Total_Biaya();
        txtbayar.setText("0");
        txtkembalian.setText("0");
        Kosongkan_1();
            
    }//GEN-LAST:event_btnhapusitemActionPerformed
    
    private void txttotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttotalActionPerformed

    private void txtkembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkembalianActionPerformed

    private void tabel_bukuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_bukuMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabel_bukuMouseReleased

    private void tabel_bukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_bukuMouseClicked
        // TODO add your handling code here
        if(txtiduser.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Pilih User or Kasir");
            txtiduser.requestFocus();
        }else{
        int baris =tabel_buku.rowAtPoint(evt.getPoint());
        String kode =tabel_buku.getValueAt(baris, 0).toString();
        txtkodebuku.setText(kode);
        String nama=tabel_buku.getValueAt(baris, 1).toString();
        txtnamabuku.setText(nama);
        String harga=tabel_buku.getValueAt(baris, 2).toString();
        txtharga.setText(harga);
        String stok =tabel_buku.getValueAt(baris, 3).toString();
        txtstokawal.setText(stok);
        }
    }//GEN-LAST:event_tabel_bukuMouseClicked

    private void txtstokakhirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstokakhirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstokakhirActionPerformed

    private void txttampiltotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttampiltotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttampiltotalActionPerformed

    private void txtcaribukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcaribukuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcaribukuActionPerformed

    private void txtbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbayarActionPerformed
        // TODO add your handling code here:
        int total, bayar, kembalian;
        total = Integer.parseInt(txttotal.getText().replace(".", ""));
        bayar = Integer.parseInt(txtbayar.getText().replace(".", ""));

        if (total > bayar){
            JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk pembayaran..!");
        }else{
            kembalian = bayar - total;
            //txtkembalian.setText(String.valueOf(kembalian));
            txtkembalian.setText(nf.format(kembalian));
            if (kembalian ==0){
                //txtkembalian dimanipulasi agar tidak error saat di cetak
                //kalau kembalian sama dengan=0
                txtkembalian.setText("0.1");
            }
            JOptionPane.showMessageDialog(null, "Terimakasih sudah membayar");
        }
    }//GEN-LAST:event_txtbayarActionPerformed

    private void txtiduserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtiduserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtiduserActionPerformed

    private void tgltransaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgltransaksiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgltransaksiKeyPressed

    private void tgltransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgltransaksiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tgltransaksiMouseClicked

    private void txtiduserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtiduserKeyReleased
        // TODO add your handling code here:
        try{
        java.sql.Connection conn =(Connection)Koneksi.configDB();
        String sql ="SELECT * FROM user WHERE id_user=?";
        java.sql.PreparedStatement pst=conn.prepareStatement(sql);
        pst.setString(1, txtiduser.getText());
        java.sql.ResultSet res = pst.executeQuery();
        if(res.next()){
            String nama =res.getString("username");
            txtuser.setText(nama);
            String akses =res.getString("akses");
            txtakses.setText(akses);
        }
        }catch (SQLException e){
            //tampilkan pesan di sistem kalau terjadi error
            System.out.println("Error :" +e.getMessage());
        }
    }//GEN-LAST:event_txtiduserKeyReleased

    private void txtkodebukuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtkodebukuKeyReleased
        // TODO add your handling code here:
        try{
            java.sql.Connection conn =(Connection)Koneksi.configDB();
            String sql ="SELECT * FROM buku WHERE kd_buku=?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1, txtkodebuku.getText());
            java.sql.ResultSet res = pst.executeQuery();
            if(res.next()){
                String namabrg =res.getString("nama_buku");
                txtnamabuku.setText(namabrg);
                String stokawal =res.getString("stok");
                txtstokawal.setText(stokawal);
                String hargabrg =res.getString("harga");
                txtharga.setText(hargabrg);
            }
            }catch (SQLException e){
                //tampilkan pesan di sistem kalau terjadi error
                System.out.println("Error :" +e.getMessage());
        }
    }//GEN-LAST:event_txtkodebukuKeyReleased

    private void txtjumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtjumlahKeyReleased
        // TODO add your handling code here:
        if (txtjumlah.getText().equals("")|| txtjumlah.getText().equals(0)){
        }else{
            int jumlah , harga, subtotal;
            jumlah = Integer.parseInt(txtjumlah.getText());
            harga = Integer.parseInt(txtharga.getText());
            subtotal = jumlah * harga;
            txtsubtotal.setText(nf.format(subtotal));
        }
        int jumlahbeli, stokawal, stokakhir;
        jumlahbeli = Integer.parseInt(txtjumlah.getText());
        stokawal= Integer.parseInt(txtstokawal.getText());
        if (jumlahbeli > stokawal) {
            JOptionPane.showMessageDialog(null, "Stok Buku Tidak Mencukupi...!");
            btntambahitem.setEnabled(false);
            txtstokakhir.setText("");
            txtjumlah.requestFocus();
            
        }else{
            stokakhir = (stokawal-jumlahbeli);
            txtstokakhir.setText(nf.format(stokakhir));
            btntambahitem.setEnabled(true);
        }       
    }//GEN-LAST:event_txtjumlahKeyReleased

    private void tabel_keranjangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_keranjangMouseClicked
        // TODO add your handling code here:
            int baris =tabel_keranjang.rowAtPoint(evt.getPoint());
            String stokawal=tabel_keranjang.getValueAt(baris, 1).toString();
            txtstokawal.setText(stokawal);
            String stokakhir=tabel_keranjang.getValueAt(baris, 2).toString();
            txtstokakhir.setText(stokakhir);
            String kode =tabel_keranjang.getValueAt(baris, 3).toString();
            txtkodebuku.setText(kode);
            String nama=tabel_keranjang.getValueAt(baris, 4).toString();
            txtnamabuku.setText(nama);
            String harga=tabel_keranjang.getValueAt(baris, 5).toString();
            txtharga.setText(harga);
            String jumlah=tabel_keranjang.getValueAt(baris, 6).toString();
            txtjumlah.setText(jumlah);
            String subtotal=tabel_keranjang.getValueAt(baris, 7).toString();
            txtsubtotal.setText(subtotal);
    }//GEN-LAST:event_tabel_keranjangMouseClicked

    private void btncetak_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncetak_simpanActionPerformed
        // TODO add your handling code here:
            int total, bayar;
        total = Integer.parseInt(txttotal.getText().replace(".", ""));
        bayar = Integer.parseInt(txtbayar.getText().replace(".", ""));

        if(bayar < total) {
        JOptionPane.showMessageDialog(null,"Lakukan Pembayaran");
        }else{
            //untuk format tanggal pada JDateChooser
            String tampilan ="yyyy-MM-dd";
            SimpleDateFormat fm =new SimpleDateFormat(tampilan);
            String tanggal=String.valueOf(fm.format(tgltransaksi.getDate()));
        try {
            int baris = tabel_keranjang.getRowCount();
            for (int i = 0; i < baris; i++) {
                String sql = "INSERT INTO penjual VALUES( NULL,'"
                + txtnofaktur.getText()+"','"
                + tanggal +"','"
                + tabel_keranjang.getValueAt(i, 3) +"','"
                + txtiduser.getText()+"','"
                + tabel_keranjang.getValueAt(i, 6) +"','"
                + tabel_keranjang.getValueAt(i, 7).toString().replace(".", "")+"')";
            java.sql.Connection conn = (Connection)Koneksi.configDB();
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();
            pstm.close();
            }
            JOptionPane.showMessageDialog(null, "Data Berhasil di Simpan");
        }catch(HeadlessException | SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Data Gagal Disimpan");
        }
        //Coding Cetak Struk
        JasperReport jr;
        JasperPrint jp;
        JasperDesign jd;
        try{
            //Map <String, Object> parameter = new HashMap <String, Object>();
            java.sql.Connection conn =(Connection)Koneksi.configDB();
            HashMap iniparameter = new HashMap();
            iniparameter.put("nofaktur", txtnofaktur.getText());
            iniparameter.put("totalbelanja", txttotal.getText());
            iniparameter.put("bayar", txtbayar.getText());
            iniparameter.put("kembalian", txtkembalian.getText());
                //menggambil file ireport strukbelanja yang ada pada folder src/laporan
            File report =new File("src/Laporan/Strukbelanja.jrxml");
            jd=JRXmlLoader.load(report);
            jr=JasperCompileManager.compileReport(jd);
            jp=JasperFillManager.fillReport(jr,iniparameter,conn);
            JasperViewer.viewReport(jp,false);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            }    
                Kosongkan_2();
                Kosongkan_Keranjang();
                Tampilkan_Buku();
                txttotal.setText("0");
                txtbayar.setText("0");
                txtkembalian.setText("0");
                txttampiltotal.setText("Rp. 0");
        }
    }//GEN-LAST:event_btncetak_simpanActionPerformed

    private void txtstokawalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstokawalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstokawalActionPerformed

    private void txtcaribukuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcaribukuKeyReleased
        // TODO add your handling code here:
        Tampilkan_Buku();
    }//GEN-LAST:event_txtcaribukuKeyReleased

    private void txtjumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtjumlahKeyTyped
        // TODO add your handling code here:
        char enter = evt.getKeyChar();
        if (!(Character.isDigit(enter))){
            evt.consume();
            JOptionPane.showMessageDialog(null, "Masukan Angka 0 s/d 9");
            txtjumlah.setText("0");
            txtstokakhir.setText("0");
        }
    }//GEN-LAST:event_txtjumlahKeyTyped

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
        java.awt.EventQueue.invokeLater(() -> new Form_Transaksi().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncetak_simpan;
    private javax.swing.JButton btnhapusitem;
    private javax.swing.JButton btntambahitem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tabel_buku;
    private javax.swing.JTable tabel_keranjang;
    private com.toedter.calendar.JDateChooser tgltransaksi;
    public static final javax.swing.JTextField txtakses = new javax.swing.JTextField();
    private javax.swing.JTextField txtbayar;
    private javax.swing.JTextField txtcaribuku;
    private javax.swing.JTextField txtharga;
    public static final javax.swing.JTextField txtiduser = new javax.swing.JTextField();
    private javax.swing.JTextField txtjumlah;
    private javax.swing.JTextField txtkembalian;
    private javax.swing.JTextField txtkodebuku;
    private javax.swing.JTextField txtnamabuku;
    private javax.swing.JTextField txtnofaktur;
    private javax.swing.JTextField txtstokakhir;
    private javax.swing.JTextField txtstokawal;
    private javax.swing.JTextField txtsubtotal;
    private javax.swing.JTextField txttampiltotal;
    private javax.swing.JTextField txttotal;
    public static final javax.swing.JTextField txtuser = new javax.swing.JTextField();
    // End of variables declaration//GEN-END:variables
}
