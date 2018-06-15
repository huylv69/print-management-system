/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import BusinessLogicLayer.GiaCongNBLL;
import BusinessLogicLayer.GiayInBLL;
import BusinessLogicLayer.KhachHangBLL;
import BusinessLogicLayer.LenhInBLL;
import BusinessLogicLayer.MayInBLL;
import DataAccessLayer.ConnectionDB;
import DataAccessLayer.DonHangDAL;
import DataAccessLayer.GiayInDAL;
import DataAccessLayer.KhachHangDAL;
import DataAccessLayer.LenhInDAL;
import DataAccessLayer.MayInDAL;
import DataTranferObject.LenhIn;
import DataTranferObject.User;
import static PresentationLayer.frmDangNhap.user;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HuyLV
 */
public final class frmLenhIn extends javax.swing.JFrame {

    /**
     * Creates new form frmLenhIn
     */
    public frmLenhIn() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    public frmLenhIn(User user, String lenh, ResultSet rs) {
        initComponents();
        showDate();
        labelName.setText(user.getTenUser());
        this.setLocationRelativeTo(null);
        btnThemLenh.setVisible(false);
        try {
            //Show Thông tin .
            if (rs.next()) {
                txtDonGia.setText(rs.getString("dongia"));
                cbxMaKhachHang.setSelectedItem(rs.getObject("ma_kh"));
                txtTenKhachHang.setText(rs.getString("ma_kh"));
                txtMaMatHang.setText(rs.getString("ma_mh"));
                cbxSoDonKan.setSelectedItem(rs.getString("sodonkan"));
                txtSoDonKhach.setText(rs.getString("sodonkhach"));
                cbxGiayIn.setSelectedItem(rs.getString("loaigiay"));
                cbxKhoGiay.setSelectedItem(rs.getString("khogiay"));
                cbxTrangThai.setSelectedItem(rs.getString("tinhtrang"));
                txtLuuYGiaCong.setText(rs.getString("luu_y_giacong"));
                cbxKhuonIn.setSelectedItem(rs.getString("khuon_in"));
                txtTenHangIn.setText(rs.getString("tenhangin"));
                txtBinhBan.setText(rs.getString("binhban"));
                txtLuuYDacBietIn.setText(rs.getString("luu_y_dacbiet"));
                cbxNoiIn.setSelectedItem(rs.getString("noi_in"));
                cbxNoiGiaCongNgoai.setSelectedItem(rs.getString("ten_gc_ngoai"));
                txtXuatPhim.setText(rs.getString("xuat_phim"));
                txtDienGiaiChiPhi.setText(rs.getString("chiphikhac"));
                txtKhoXuatGiay.setText(rs.getString("kho_xuatgiay"));
                txtSoLenh.setText(String.valueOf(rs.getInt("solenh")));
                txtTongSoGiay.setText(String.valueOf(rs.getInt("tongsogiay")));
                cbxTro.setSelectedItem(String.valueOf(rs.getInt("tro")));
                txtSoBanPhoi.setText(String.valueOf(rs.getInt("so_ban_phoi")));
                txtSoLuongTPYCIn.setText(String.valueOf(rs.getInt("sl_thanhpham_yc_in")));
                txtBuHaoBanIn.setText(String.valueOf(rs.getInt("buhao_banin")));
                txtSoLuongTPYC.setText(String.valueOf(rs.getInt("sl_thanhpham_yc")));
                txtBuHaoGiaCong.setText(String.valueOf(rs.getInt("buhao_giacong")));
                txtSoLuotIn.setText(String.valueOf(rs.getInt("so_luot_in")));
                txtTienGiaCongNgoai.setText(String.valueOf(rs.getInt("tiencong_ngoai")));
                txtTienCongIn.setText(String.valueOf(rs.getInt("tiencong_in")));
                txtTienCongTho.setText(String.valueOf(rs.getInt("tiencong_thoin")));
                txtTienCongKan.setText(String.valueOf(rs.getInt("tiencong_kan")));
                txtTienPhim.setText(String.valueOf(rs.getInt("tien_phim")));
                txtTienGiay.setText(String.valueOf(rs.getInt("tien_giay")));
                txtVanChuyen.setText(String.valueOf(rs.getInt("vanchuyen")));
                txtChiPhiKhac.setText(String.valueOf(rs.getInt("chiphikhac")));
                txtThangTinhLuongTho.setText(String.valueOf(rs.getInt("thang_tinhluong_thoin")));
                txtLaiThuan.setText(String.valueOf(rs.getDouble("laithuan")));
                txtTiLeLai.setText(String.valueOf(rs.getDouble("tilelai")));
                txtTongChiPhi.setText(String.valueOf(rs.getDouble("tong_chiphi")));
                dateNgayLap.setDate(rs.getDate("ngaylap"));
                dateNgayTraHang.setDate(rs.getDate("ngaygiaohang_dukien"));
                String sql = "select * from tbltiencongkan where solenh = '" + rs.getInt("solenh") + "'";
                ResultSet rstbl = ConnectionDB.ExcuteQueryGetTable(sql);
                int i = 0;
                DefaultTableModel tb = (DefaultTableModel) tblTienCongKan.getModel();
                while (rstbl.next()) {
                    Object[] item = new Object[2];
                    item[0] = rstbl.getString("giaidoan");
                    item[1] = rstbl.getString("tiencong");
                    tb.addRow(item);
                    i++;
                }
                tblTienCongKan.setModel(tb);
                spGiaiDoan.setValue(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmLenhIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public frmLenhIn(User user, String lenh) {
        initComponents();
        showDate();
        labelName.setText(user.getTenUser());
        this.setLocationRelativeTo(null);
        btnCapNhat.setVisible(false);
        //Lấy số lệnh tiếp theo.
        ResultSet rs = LenhInBLL.LaySoLenhTiepTheo();
        try {
            if (rs.next()) {
                txtSoLenh.setText(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmLenhIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showDate() {
        new Timer(WIDTH, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                Calendar cal = Calendar.getInstance();
                lbTime.setText(dateFormat.format(cal.getTime()));
            }
        }).start();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("src/Image/background-Part.png");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        cbxTrangThai = new javax.swing.JComboBox<>();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        labelName = new javax.swing.JLabel();
        lbTime = new javax.swing.JLabel();
        btnDangXuat4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane(){
            ImageIcon icon = new ImageIcon("src/Image/background.png");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jPanel1 = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("src/Image/background.png");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jLabel7 = new javax.swing.JLabel();
        cbxMaKhachHang = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        txtSoLenh = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMaMatHang = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        cbxSoDonKan = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtSoDonKhach = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        txtTenHangIn = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        dateNgayLap = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("src/Image/background-Part.png");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jLabel15 = new javax.swing.JLabel();
        cbxGiayIn = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtTongSoGiay = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtBuHaoBanIn = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtSoLuongTPYCIn = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        cbxKhoGiay = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtLuuYDacBietIn = new javax.swing.JTextArea();
        jPanel8 = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("src/Image/background-Part.png");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jLabel22 = new javax.swing.JLabel();
        cbxKhuonIn = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        cbxTro = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtBinhBan = new javax.swing.JTextArea();
        jLabel47 = new javax.swing.JLabel();
        txtSoBanPhoi = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("src/Image/background-Part.png");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jLabel20 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtVanChuyen = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtTienCongIn = new javax.swing.JTextField();
        txtTienGiay = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txtTienPhim = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtXuatPhim = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDienGiaiChiPhi = new javax.swing.JTextArea();
        txtTienGiaCongNgoai = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtLaiThuan = new javax.swing.JTextField();
        txtTienCongTho = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        txtChiPhiKhac = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        txtThangTinhLuongTho = new javax.swing.JTextField();
        txtTongChiPhi = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        txtTiLeLai = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("src/Image/background-Part.png");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        txtTienCongKan = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        spGiaiDoan = new javax.swing.JSpinner();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblTienCongKan = new javax.swing.JTable();
        btnTienCongIn = new javax.swing.JButton();
        btnTienCongTho = new javax.swing.JButton();
        btnTiLeLai = new javax.swing.JButton();
        btnTongChiPhi = new javax.swing.JButton();
        btnLaiThuan = new javax.swing.JButton();
        btnTienGiay = new javax.swing.JButton();
        btnDangXuat3 = new javax.swing.JButton();
        btnThemLenh = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("src/Image/background-Part.png");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        cbxNoiGiaCongNgoai = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        txtSoLuongTPYC = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtLuuYGiaCong = new javax.swing.JTextArea();
        jLabel33 = new javax.swing.JLabel();
        cbxNoiIn = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        txtBuHaoGiaCong = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtKhoXuatGiay = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        dateNgayTraHang = new com.toedter.calendar.JDateChooser();
        jLabel36 = new javax.swing.JLabel();
        txtSoLuotIn = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jButton3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton3.setText("Tính tiền thợ");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton6.setText("Tính tiền");
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cbxTrangThai.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cbxTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chuẩn Bị", "Chờ In", "Gia Công", "Xong", "Hủy" }));

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(0, 51, 204));
        jLabel48.setText("Trạng Thái : ");

        jLabel49.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 0, 51));
        jLabel49.setText("Lệnh In");

        jLabel50.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel50.setText("Xin Chào : ");

        jLabel51.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel51.setText("Ngày : ");

        labelName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        labelName.setText("Sản Xuất");

        lbTime.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbTime.setText("Time");

        btnDangXuat4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Apps-Dialog-Logout-icon.png"))); // NOI18N
        btnDangXuat4.setText("Back");
        btnDangXuat4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuat4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnDangXuat4))
                .addGap(143, 143, 143)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel50)
                    .addComponent(jLabel51))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelName, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .addComponent(lbTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(btnDangXuat4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50)
                            .addComponent(labelName)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 204));
        jLabel7.setText("1. Mã Khách Hàng :");

        cbxMaKhachHang.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        cbxMaKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxMaKhachHangActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 204));
        jLabel8.setText("2.Tên Khách Hàng :");

        txtTenKhachHang.setEditable(false);
        txtTenKhachHang.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 204));
        jLabel1.setText("3 .Số lệnh :");

        txtSoLenh.setEditable(false);
        txtSoLenh.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 204));
        jLabel4.setText("4. Mã hàng");

        txtMaMatHang.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 204));
        jLabel5.setText("5. Số đơn KAN :");

        cbxSoDonKan.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 204));
        jLabel6.setText("6.Số đơn của Khách:");

        txtSoDonKhach.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtSoDonKhach.setText("Không có.");

        jSeparator6.setToolTipText("");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 51, 204));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("7.Tên Hàng In:");

        txtTenHangIn.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtTenHangIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenHangInActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 51, 204));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("8.Ngày lập");

        dateNgayLap.setDateFormatString("dd-MM-yyyy");
        dateNgayLap.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateNgayLapPropertyChange(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Vật Tư", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14), new java.awt.Color(0, 204, 0))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 51, 204));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("9.Loại Giấy In :");

        cbxGiayIn.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        cbxGiayIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxGiayInActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 51, 204));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("10.Khổ Giấy :");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 51, 204));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("11.Tổng số giấy :");

        txtTongSoGiay.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtTongSoGiay.setText("0");
        txtTongSoGiay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTongSoGiayKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 51, 204));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("12.Bù Hao Bản In:");

        txtBuHaoBanIn.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtBuHaoBanIn.setText("0");
        txtBuHaoBanIn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuHaoBanInKeyReleased(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 51, 204));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("13.Thành Phẩm Yêu Cầu Khâu In (11-12)  :");
        jLabel21.setAutoscrolls(true);
        jLabel21.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtSoLuongTPYCIn.setEditable(false);
        txtSoLuongTPYCIn.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtSoLuongTPYCIn.setText("0");
        txtSoLuongTPYCIn.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtSoLuongTPYCInPropertyChange(evt);
            }
        });

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        cbxKhoGiay.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 51, 204));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("14. Lưu ý đặc biệt (thợ in)  :");

        txtLuuYDacBietIn.setColumns(20);
        txtLuuYDacBietIn.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtLuuYDacBietIn.setRows(5);
        txtLuuYDacBietIn.setText("  ");
        jScrollPane4.setViewportView(txtLuuYDacBietIn);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxGiayIn, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxKhoGiay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBuHaoBanIn, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTongSoGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(44, 44, 44)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(txtSoLuongTPYCIn, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbxGiayIn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel18)
                                .addComponent(txtTongSoGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxKhoGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBuHaoBanIn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoLuongTPYCIn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 47, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(6, 6, 6))))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbxGiayIn, txtBuHaoBanIn, txtSoLuongTPYCIn, txtTongSoGiay});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel15, jLabel17, jLabel18, jLabel19});

        jLabel21.getAccessibleContext().setAccessibleName("13.Thành Phẩm Yêu Cầu Khâu In\n (11-12)  :"); // NOI18N

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Chế Bản", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14), new java.awt.Color(0, 204, 0))); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 51, 204));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("15.Khuôn In :");

        cbxKhuonIn.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        cbxKhuonIn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Film ", "Film Cũ", "Can", "Kẽm Cũ" }));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 51, 204));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("17.Trở :");

        cbxTro.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        cbxTro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2" }));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 51, 204));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("16.Bình Bản (Yêu cầu Bình) :");

        txtBinhBan.setColumns(20);
        txtBinhBan.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtBinhBan.setRows(5);
        txtBinhBan.setText("  ");
        jScrollPane5.setViewportView(txtBinhBan);

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 51, 204));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("18.Số bản phơi:");

        txtSoBanPhoi.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtSoBanPhoi.setText("1");
        txtSoBanPhoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoBanPhoiActionPerformed(evt);
            }
        });
        txtSoBanPhoi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoBanPhoiKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxKhuonIn, 0, 146, Short.MAX_VALUE)
                        .addGap(155, 155, 155)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxTro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoBanPhoi, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbxKhuonIn, cbxTro});

        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxKhuonIn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxTro, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSoBanPhoi, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Chi Phí ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14), new java.awt.Color(0, 204, 0))); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 51, 204));
        jLabel20.setText("32.Vận Chuyển:");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 51, 204));
        jLabel27.setText("30.Xuất Phim");

        txtVanChuyen.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtVanChuyen.setText("0");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 51, 204));
        jLabel28.setText("29.Tiền Phim :");

        txtTienCongIn.setEditable(false);
        txtTienCongIn.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtTienCongIn.setText("0");

        txtTienGiay.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtTienGiay.setText("0");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 51, 204));
        jLabel39.setText("31.Tiền Giấy (9,10,11):");

        txtTienPhim.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtTienPhim.setText("0");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 51, 204));
        jLabel41.setText("28.Tiền Công Thợ In:");

        txtXuatPhim.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtXuatPhim.setText("0");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 51, 204));
        jLabel42.setText("34.Tiền Gia Công Ngoài :");

        txtDienGiaiChiPhi.setColumns(20);
        txtDienGiaiChiPhi.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtDienGiaiChiPhi.setRows(5);
        txtDienGiaiChiPhi.setText("  ");
        jScrollPane2.setViewportView(txtDienGiaiChiPhi);

        txtTienGiaCongNgoai.setEditable(false);
        txtTienGiaCongNgoai.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtTienGiaCongNgoai.setText("0");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 51, 204));
        jLabel43.setText("37. Diễn giải chi phí khác :");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 51, 204));
        jLabel46.setText("39.Lãi Thuần(20(A*B)-38):");

        txtLaiThuan.setEditable(false);
        txtLaiThuan.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtLaiThuan.setText("0");

        txtTienCongTho.setEditable(false);
        txtTienCongTho.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtTienCongTho.setText("0");

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(0, 51, 204));
        jLabel52.setText("27.Tiền Công In :");

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(0, 51, 204));
        jLabel53.setText("36.Chi Phí Khác :");

        txtChiPhiKhac.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtChiPhiKhac.setText("0");

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(0, 51, 204));
        jLabel54.setText("38.Tổng chi phí :");

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(0, 51, 204));
        jLabel55.setText("35.Tháng tính lương thợ in :");

        txtThangTinhLuongTho.setEditable(false);
        txtThangTinhLuongTho.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtThangTinhLuongTho.setText("0");

        txtTongChiPhi.setEditable(false);
        txtTongChiPhi.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtTongChiPhi.setText("0");
        txtTongChiPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongChiPhiActionPerformed(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(0, 51, 204));
        jLabel56.setText("40.Tỉ lệ lãi (39/38) :");

        txtTiLeLai.setEditable(false);
        txtTiLeLai.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtTiLeLai.setText("0");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "33.Tiền Công Kan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 51, 204))); // NOI18N

        txtTienCongKan.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtTienCongKan.setText("0");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 51, 204));
        jLabel40.setText("Tổng :");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 51, 204));
        jLabel44.setText("Số Giai Đoạn :");

        spGiaiDoan.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        spGiaiDoan.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spGiaiDoanStateChanged(evt);
            }
        });

        tblTienCongKan.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        tblTienCongKan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Giai Đoạn", "Tiền Công"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblTienCongKan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblTienCongKanKeyReleased(evt);
            }
        });
        jScrollPane6.setViewportView(tblTienCongKan);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spGiaiDoan, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTienCongKan, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(spGiaiDoan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienCongKan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnTienCongIn.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnTienCongIn.setText("Tính tiền in ");
        btnTienCongIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTienCongInActionPerformed(evt);
            }
        });

        btnTienCongTho.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnTienCongTho.setText("Tính tiền thợ");
        btnTienCongTho.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTienCongTho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTienCongThoActionPerformed(evt);
            }
        });

        btnTiLeLai.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnTiLeLai.setText("Tính Lãi");
        btnTiLeLai.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTiLeLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTiLeLaiActionPerformed(evt);
            }
        });

        btnTongChiPhi.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnTongChiPhi.setText("Tính Lãi");
        btnTongChiPhi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTongChiPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTongChiPhiActionPerformed(evt);
            }
        });

        btnLaiThuan.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnLaiThuan.setText("Tính Tiền");
        btnLaiThuan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLaiThuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaiThuanActionPerformed(evt);
            }
        });

        btnTienGiay.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnTienGiay.setText("Tính tiền");
        btnTienGiay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTienGiay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTienGiayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTienPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtXuatPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienCongTho, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienCongIn, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnTienCongIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTienCongTho, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))))
                .addGap(32, 32, 32)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtTongChiPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLaiThuan, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtLaiThuan, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTongChiPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtTiLeLai, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTiLeLai, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtThangTinhLuongTho, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtChiPhiKhac, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTienGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtVanChuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTienGiaCongNgoai, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTienGiay)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel27, jLabel28, jLabel41});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtChiPhiKhac, txtLaiThuan, txtTiLeLai, txtTongChiPhi});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel20, jLabel39, jLabel42, jLabel43, jLabel46, jLabel53, jLabel54, jLabel55, jLabel56});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLaiThuan, btnTiLeLai, btnTongChiPhi});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTienCongIn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTienCongIn))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTienCongTho, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTienCongTho))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTienPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtXuatPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienGiay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTienGiay))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVanChuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienGiaCongNgoai, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtThangTinhLuongTho, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtChiPhiKhac, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLaiThuan)
                            .addComponent(txtTongChiPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTongChiPhi)
                            .addComponent(txtLaiThuan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTiLeLai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTiLeLai))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel20, jLabel39, jLabel42, jLabel43, jLabel46, jLabel53, jLabel54, jLabel55, jLabel56});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtChiPhiKhac, txtLaiThuan, txtThangTinhLuongTho, txtTiLeLai, txtTienGiaCongNgoai, txtTienGiay, txtTongChiPhi, txtVanChuyen});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtTienCongIn, txtTienCongTho, txtTienPhim, txtXuatPhim});

        btnDangXuat3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Apps-Dialog-Logout-icon.png"))); // NOI18N
        btnDangXuat3.setText("Back");
        btnDangXuat3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuat3ActionPerformed(evt);
            }
        });

        btnThemLenh.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        btnThemLenh.setForeground(new java.awt.Color(51, 51, 255));
        btnThemLenh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/add.png"))); // NOI18N
        btnThemLenh.setText("Thêm Lệnh In");
        btnThemLenh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLenhActionPerformed(evt);
            }
        });

        btnCapNhat.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        btnCapNhat.setForeground(new java.awt.Color(51, 51, 255));
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/update.png"))); // NOI18N
        btnCapNhat.setText("Cập Nhật ");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Phần In - Gia Công", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14), new java.awt.Color(0, 204, 0))); // NOI18N

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 51, 204));
        jLabel29.setText("20.Số lượng thành phẩm yêu cầu (A):");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 51, 204));
        jLabel30.setText("24.Kho xuất giấy :");

        cbxNoiGiaCongNgoai.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        cbxNoiGiaCongNgoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Không Có" }));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 51, 204));
        jLabel31.setText("21.Bù hao gia công :");

        txtSoLuongTPYC.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtSoLuongTPYC.setText("0");
        txtSoLuongTPYC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongTPYCActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 51, 204));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("26. Ghi Chú Gia Công:");

        txtLuuYGiaCong.setColumns(20);
        txtLuuYGiaCong.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtLuuYGiaCong.setRows(5);
        jScrollPane3.setViewportView(txtLuuYGiaCong);

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 51, 204));
        jLabel33.setText("22.Nơi gia công ngoài :");

        cbxNoiIn.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        cbxNoiIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxNoiInActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 51, 204));
        jLabel34.setText("19.Nơi in :");

        txtBuHaoGiaCong.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtBuHaoGiaCong.setText("0");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 51, 204));
        jLabel35.setText("23. Ngày trả hàng dự tính :");

        txtKhoXuatGiay.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtKhoXuatGiay.setText(" ");

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        dateNgayTraHang.setDateFormatString("dd-MM-yyyy");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 51, 204));
        jLabel36.setText("25.Tổng số lượt in (13*17*18) :");

        txtSoLuotIn.setEditable(false);
        txtSoLuotIn.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtSoLuotIn.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtSoLuotInPropertyChange(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 51, 204));
        jLabel37.setText("- Đơn Giá (B):");

        txtDonGia.setFont(new java.awt.Font("Tiffany", 0, 15)); // NOI18N
        txtDonGia.setText("0");
        txtDonGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDonGiaActionPerformed(evt);
            }
        });

        jButton1.setText("Tính");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbxNoiIn, 0, 174, Short.MAX_VALUE)
                                .addComponent(txtBuHaoGiaCong))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(txtSoLuongTPYC, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtKhoXuatGiay)
                        .addComponent(dateNgayTraHang, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                        .addComponent(cbxNoiGiaCongNgoai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtSoLuotIn, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addGap(13, 13, 13))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxNoiIn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSoLuongTPYC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBuHaoGiaCong, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbxNoiGiaCongNgoai, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dateNgayTraHang, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtKhoXuatGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtSoLuotIn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 17, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbxNoiIn, txtBuHaoGiaCong, txtKhoXuatGiay, txtSoLuongTPYC});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxMaKhachHang, 0, 160, Short.MAX_VALUE)
                    .addComponent(txtTenKhachHang))
                .addGap(40, 40, 40)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSoLenh, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(txtMaMatHang))
                .addGap(46, 46, 46)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxSoDonKan, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoDonKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator6)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnDangXuat3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThemLenh, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(126, 126, 126))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenHangIn, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel7, jLabel8});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoLenh, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaMatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxSoDonKan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoDonKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txtTenHangIn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23))
                    .addComponent(dateNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemLenh, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDangXuat3))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel4, jLabel5, jLabel7, jLabel8});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtMaMatHang, txtSoDonKhach, txtSoLenh, txtTenKhachHang});

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1171, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1206, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        jScrollPane1.getVerticalScrollBar().setUnitIncrement(15);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // Đổ dữ liệu vào combobox Mã Khách Hàng
        ResultSet rsKH = KhachHangBLL.LayTatCaKhachHang();
        try {
            while (rsKH.next()) {
                String maKH;
                maKH = rsKH.getString("ma_kh");
                cbxMaKhachHang.addItem(maKH);
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmLenhIn.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Đổ dữ liệu vào cbx Giấy In
        ResultSet rsGiay = GiayInBLL.LayTatCaGiay();
        try {
            while (rsGiay.next()) {
                String loaiGiay = rsGiay.getString("loai");
                cbxGiayIn.addItem(loaiGiay);
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmLenhIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Đổ dữ liệu vào cbx Nơi In
        ResultSet rsMayIn = MayInBLL.LayTatCaMayIn();
        try {
            while (rsMayIn.next()) {
                String tenMayIn = rsMayIn.getString("ten_mayin");
                cbxNoiIn.addItem(tenMayIn);
            }
            cbxNoiIn.addItem("Gia Công Ngoài");
        } catch (SQLException ex) {
            Logger.getLogger(frmLenhIn.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Tháng tính lương thợ in :
        int month = dateNgayLap.getDate().getMonth();
        System.out.println(dateNgayLap.getDate());
        System.out.println(month);
        txtThangTinhLuongTho.setText(Integer.toString(month + 1));

//        //Đổ dữ liệu nơi gia công ngoài
//        if(cbxNoiIn.getSelectedItem().equals("Gia Công Ngoài")){
//            ResultSet rsGiaCongNgoai = GiaCongNBLL.LayTatCaGiaCongN();
//            try {
//                while (rsGiaCongNgoai.next()){
//                    String tenGiaCongNgoai=rsGiaCongNgoai.getString("ten_gc_ngoai");
//                    cbxNoiGiaCongNgoai.addItem(tenGiaCongNgoai);
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(frmLenhIn.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }

    }//GEN-LAST:event_formWindowOpened

    private void cbxNoiInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxNoiInActionPerformed
        //Nếu chọn gia công ngoài
        // Đổ dữ liệu vào cbx Gia Công Ngoài
        cbxNoiGiaCongNgoai.removeAllItems();
        if (cbxNoiIn.getSelectedItem() == "Gia Công Ngoài") {
            cbxNoiGiaCongNgoai.removeAllItems();
            ResultSet rsGiaCong = GiaCongNBLL.LayTatCaGiaCongN();
            try {
                while (rsGiaCong.next()) {
                    String giaCong = rsGiaCong.getString("ten_gc_ngoai");
                    cbxNoiGiaCongNgoai.addItem(giaCong);
                }
            } catch (SQLException ex) {
                Logger.getLogger(frmLenhIn.class.getName()).log(Level.SEVERE, null, ex);
            }
            txtTienCongIn.setEditable(true);
            btnTienCongIn.setEnabled(false);
            btnTienCongTho.setEnabled(false);
            txtTienGiaCongNgoai.setEditable(true);
            txtTienCongTho.setText("0");
        } else {
            cbxNoiGiaCongNgoai.addItem("Không Gia Công Ngoài");
            txtTienCongIn.setEditable(false);
            btnTienCongIn.setEnabled(true);
            btnTienCongTho.setEnabled(true);
            txtTienGiaCongNgoai.setEditable(false);
            txtTienGiaCongNgoai.setText("0");
        }
    }//GEN-LAST:event_cbxNoiInActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // Kiểm tra Lệnh In
        try {
            if (txtTienCongKan.getText().equals("0")) {
                QLIN.ThongBao("Hãy nhập tiên công Kan", "Chú ý", WIDTH);
            } else {
                String userName = user.getUserName();
                String makhachHang = cbxMaKhachHang.getSelectedItem().toString();
                String tenKhachHang = txtTenKhachHang.getText();
                String maMatHang = txtMaMatHang.getText();
                String soDonKan = cbxSoDonKan.getSelectedItem().toString();
                String soDonKhach = txtSoDonKhach.getText();
                String loaiGiay = cbxGiayIn.getSelectedItem().toString();
                String khoGiay = cbxKhoGiay.getSelectedItem().toString();
                String tinhTrang = cbxTrangThai.getSelectedItem().toString();
                String luuYGiaCong = txtLuuYGiaCong.getText();
                String khuonIn = cbxKhuonIn.getSelectedItem().toString();
                String tenHangIn = txtTenHangIn.getText();
                String binhBan = txtBinhBan.getText();
                String luuYDacBiet = txtLuuYDacBietIn.getText();
                String noiIn = cbxNoiIn.getSelectedItem().toString();
                String tenGiaCongNgoai = cbxNoiGiaCongNgoai.getSelectedItem().toString();
                String xuatPhim = txtXuatPhim.getText();
                String dienGiaiChiPhiKhac = txtDienGiaiChiPhi.getText();
                String khoXuatgiay = txtKhoXuatGiay.getText();
                int donGia = Integer.parseInt(txtDonGia.getText());
                int soLenh = Integer.parseInt(txtSoLenh.getText());
                int tongSoGiay = Integer.parseInt(txtTongSoGiay.getText());
                int tro = Integer.parseInt(cbxTro.getSelectedItem().toString());
                int soBanPhoi = Integer.parseInt(txtSoBanPhoi.getText());
                int soLuongTPIn = Integer.parseInt(txtSoLuongTPYCIn.getText());
                int buHaoBanIn = Integer.parseInt(txtBuHaoBanIn.getText());
                int soLuongTPYC = Integer.parseInt(txtSoLuongTPYC.getText());
                int buHaoGiaCong = Integer.parseInt(txtBuHaoGiaCong.getText());
                int soLuotIn = Integer.parseInt(txtSoLuotIn.getText());
                int tienCongNgoai = Integer.parseInt(txtTienGiaCongNgoai.getText());
                int tienCongIn = Integer.parseInt(txtTienCongIn.getText());
                int tienCongThoIn = Integer.parseInt(txtTienCongTho.getText());
                int tienCongKan = Integer.parseInt(txtTienCongKan.getText());
                int tienPhim = Integer.parseInt(txtTienPhim.getText());
                int tienGiay = Integer.parseInt(txtTienGiay.getText());
                int vanChuyen = Integer.parseInt(txtVanChuyen.getText());
                int chiPhiKhac = Integer.parseInt(txtChiPhiKhac.getText());
                int thangTinhLuongThoIn = Integer.parseInt(txtThangTinhLuongTho.getText());
                double laiThuan = Double.parseDouble(txtLaiThuan.getText());
                double tiLeLai = Double.parseDouble(txtTiLeLai.getText());
                double tongChiPhi = Double.parseDouble(txtTongChiPhi.getText());
                Date ngayLap = dateNgayLap.getDate();
                Date ngayGiaoDuKien = dateNgayTraHang.getDate();
                String sql = "delete from lenhin where solenh='" + soLenh + "'";
                ConnectionDB.ExcuteQueryUpdateDB(sql);
                String sql2 = "delete from tblTienCongKan where solenh='" + soLenh + "'";
                ConnectionDB.ExcuteQueryUpdateDB(sql2);
                LenhIn lenhIn = new LenhIn(soLenh, userName, makhachHang, tenKhachHang, maMatHang, soDonKan, soDonKhach, ngayLap, loaiGiay, khoGiay, tongSoGiay, tinhTrang, luuYGiaCong, khuonIn, tenHangIn, tro, soBanPhoi, soLuongTPIn, buHaoBanIn, soLuongTPYC, buHaoGiaCong, soLuotIn, binhBan, luuYDacBiet, noiIn, tenGiaCongNgoai, tienCongNgoai, tienCongIn, tienCongThoIn, tienCongKan, tienPhim, xuatPhim, tienGiay, vanChuyen, chiPhiKhac, dienGiaiChiPhiKhac, khoXuatgiay, ngayGiaoDuKien, laiThuan, tiLeLai, tongChiPhi, thangTinhLuongThoIn, donGia);
                LenhInBLL.ThemLenhIn(lenhIn);
                //Thêm bảng tiền công Kan
                for (int i = 0; i < tblTienCongKan.getRowCount(); i++) {
                    String giaiDoan = tblTienCongKan.getValueAt(i, 0).toString();
                    int tienCong = Integer.parseInt(tblTienCongKan.getValueAt(i, 1).toString());
                    String sqltp = "INSERT INTO tblTienCongKan(solenh, giaidoan, tiencong) VALUES (" + soLenh + "," + giaiDoan + "," + tienCong + ")";
                    ConnectionDB.ExcuteQueryUpdateDB(sqltp);
                }
                QLIN.ThongBao("Đã cập nhật thành công ", "Thông báo", 1);
                frmCacLenhIn frm = new frmCacLenhIn(user);
                frm.setVisible(true);
                this.setVisible(false);
            }

        } catch (NumberFormatException e) {
            QLIN.ThongBao("Đề nghị điền đủ thông tin ", "Chú ý !", 2);
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnThemLenhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLenhActionPerformed
        // Thêm Lệnh In
        try {
            if (txtTienCongKan.getText().equals("0")) {
                QLIN.ThongBao("Hãy nhập tiên công Kan", "Chú ý", 2);
            } else {
                String userName = user.getUserName();
                String makhachHang = cbxMaKhachHang.getSelectedItem().toString();
                String tenKhachHang = txtTenKhachHang.getText();
                String maMatHang = txtMaMatHang.getText();
                String soDonKan = cbxSoDonKan.getSelectedItem().toString();
                String soDonKhach = txtSoDonKhach.getText();
                String loaiGiay = cbxGiayIn.getSelectedItem().toString();
                String khoGiay = cbxKhoGiay.getSelectedItem().toString();
                String tinhTrang = cbxTrangThai.getSelectedItem().toString();
                String luuYGiaCong = txtLuuYGiaCong.getText();
                String khuonIn = cbxKhuonIn.getSelectedItem().toString();
                String tenHangIn = txtTenHangIn.getText();
                String binhBan = txtBinhBan.getText();
                String luuYDacBiet = txtLuuYDacBietIn.getText();
                String noiIn = cbxNoiIn.getSelectedItem().toString();
                String tenGiaCongNgoai = cbxNoiGiaCongNgoai.getSelectedItem().toString();
                String xuatPhim = txtXuatPhim.getText();
                String dienGiaiChiPhiKhac = txtDienGiaiChiPhi.getText();
                String khoXuatgiay = txtKhoXuatGiay.getText();
                int donGia = Integer.parseInt(txtDonGia.getText());
                int soLenh = Integer.parseInt(txtSoLenh.getText());
                int tongSoGiay = Integer.parseInt(txtTongSoGiay.getText());
                int tro = Integer.parseInt(cbxTro.getSelectedItem().toString());
                int soBanPhoi = Integer.parseInt(txtSoBanPhoi.getText());
                int soLuongTPIn = Integer.parseInt(txtSoLuongTPYCIn.getText());
                int buHaoBanIn = Integer.parseInt(txtBuHaoBanIn.getText());
                int soLuongTPYC = Integer.parseInt(txtSoLuongTPYC.getText());
                int buHaoGiaCong = Integer.parseInt(txtBuHaoGiaCong.getText());
                int soLuotIn = Integer.parseInt(txtSoLuotIn.getText());
                int tienCongNgoai = Integer.parseInt(txtTienGiaCongNgoai.getText());
                int tienCongIn = Integer.parseInt(txtTienCongIn.getText());
                int tienCongThoIn = Integer.parseInt(txtTienCongTho.getText());
                int tienCongKan = Integer.parseInt(txtTienCongKan.getText());
                int tienPhim = Integer.parseInt(txtTienPhim.getText());
                int tienGiay = Integer.parseInt(txtTienGiay.getText());
                int vanChuyen = Integer.parseInt(txtVanChuyen.getText());
                int chiPhiKhac = Integer.parseInt(txtChiPhiKhac.getText());
                int thangTinhLuongThoIn = Integer.parseInt(txtThangTinhLuongTho.getText());
                double laiThuan = Double.parseDouble(txtLaiThuan.getText());
                double tiLeLai = Double.parseDouble(txtTiLeLai.getText());
                double tongChiPhi = Double.parseDouble(txtTongChiPhi.getText());
                Date ngayLap = dateNgayLap.getDate();
                Date ngayGiaoDuKien = dateNgayTraHang.getDate();
                LenhIn lenhIn = new LenhIn(soLenh, userName, makhachHang, tenKhachHang, maMatHang, soDonKan, soDonKhach, ngayLap, loaiGiay, khoGiay, tongSoGiay, tinhTrang, luuYGiaCong, khuonIn, tenHangIn, tro, soBanPhoi, soLuongTPIn, buHaoBanIn, soLuongTPYC, buHaoGiaCong, soLuotIn, binhBan, luuYDacBiet, noiIn, tenGiaCongNgoai, tienCongNgoai, tienCongIn, tienCongThoIn, tienCongKan, tienPhim, xuatPhim, tienGiay, vanChuyen, chiPhiKhac, dienGiaiChiPhiKhac, khoXuatgiay, ngayGiaoDuKien, laiThuan, tiLeLai, tongChiPhi, thangTinhLuongThoIn, donGia);
                LenhInBLL.ThemLenhIn(lenhIn);

                //Thêm bảng tiền công Kan
                for (int i = 0; i < tblTienCongKan.getRowCount(); i++) {
                    String giaiDoan = tblTienCongKan.getValueAt(i, 0).toString();
                    int tienCong = Integer.parseInt(tblTienCongKan.getValueAt(i, 1).toString());
                    String sqltp = "INSERT INTO tblTienCongKan(solenh, giaidoan, tiencong) VALUES (" + soLenh + "," + giaiDoan + "," + tienCong + ")";
                    System.out.println(sqltp);
                    ConnectionDB.ExcuteQueryUpdateDB(sqltp);
                }
                QLIN.ThongBao("Đã thêm thành công ", "Thông báo", 1);
                frmCacLenhIn frm = new frmCacLenhIn(user);
                frm.setVisible(true);
                this.setVisible(false);
            }
        } catch (NumberFormatException e) {
            QLIN.ThongBao("Đề nghị điền đủ thông tin ", "Chú ý !", 2);
        }

        /*
        try {
            JasperReport jr = JasperCompileManager.compileReport("C:\\Users\\HuyLV\\Documents\\NetBeansProjects\\QL_IN_LVH\\src\\Report\\LenhIn.jrxml");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("SoLenh", lenhIn.getSoLenh());
            Connection con = ConnectionDB.getConnection();
            JasperPrint jp = JasperFillManager.fillReport(jr, params, con);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(frmLenhIn.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
    }//GEN-LAST:event_btnThemLenhActionPerformed

    private void btnDangXuat3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuat3ActionPerformed
        // TODO add your handling code here:
        frmCacLenhIn frm = new frmCacLenhIn(user);
        frm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnDangXuat3ActionPerformed

    private void cbxGiayInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxGiayInActionPerformed
        cbxKhoGiay.removeAllItems();
        String loai = (String) cbxGiayIn.getSelectedItem();
        ResultSet rs = GiayInDAL.CauTruyVanLayGiayTheoLoai(loai);
        try {
            while (rs.next()) {
                String kho = rs.getString("kho");
                cbxKhoGiay.addItem(kho);
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmLenhIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbxGiayInActionPerformed

    private void cbxMaKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMaKhachHangActionPerformed
        String maKH = (String) cbxMaKhachHang.getSelectedItem();
        // Set tên Khách Hàng theo mã Khách Hàng
        ResultSet rs = KhachHangDAL.CauTruyVanLayKhachHangTheoMa(maKH);
        try {
            if (rs.next()) {
                txtTenKhachHang.setText(rs.getString("ten_kh"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmLenhIn.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Set Số đơn Kan theo mã Khách Hàng
        cbxSoDonKan.removeAllItems();
        ResultSet rsKAN = DonHangDAL.CauTruyVanLaySoDonTheoMa(maKH);
        try {
            while (rsKAN.next()) {
                String soDon = rsKAN.getString("sodon_kan");
                cbxSoDonKan.addItem(soDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmLenhIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbxMaKhachHangActionPerformed

    private void spGiaiDoanStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spGiaiDoanStateChanged
        DefaultTableModel tb = (DefaultTableModel) tblTienCongKan.getModel();
        tb.setRowCount((int) spGiaiDoan.getModel().getValue());
    }//GEN-LAST:event_spGiaiDoanStateChanged

    private void tblTienCongKanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblTienCongKanKeyReleased
        int rowCounts = tblTienCongKan.getRowCount(); // Đếm số dòng của bảng . 
        int tong = 0, i = 0;
        try {
            if (tblTienCongKan.getModel().getValueAt(rowCounts - 1, 1) != null && tblTienCongKan.getModel().getValueAt(rowCounts - 1, 0) != null) {
                while (i < rowCounts) {
                    if (getDataTable(tblTienCongKan, i, 1) != 0) {
                        tong += getDataTable(tblTienCongKan, i, 1);
                        i++;
                    }
                }
                txtTienCongKan.setText(Integer.toString(tong));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblTienCongKanKeyReleased

    private void txtTongSoGiayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTongSoGiayKeyReleased
        int n = Integer.parseInt(txtTongSoGiay.getText()) - Integer.parseInt(txtBuHaoBanIn.getText());
        txtSoLuongTPYCIn.setText(String.valueOf(n));
    }//GEN-LAST:event_txtTongSoGiayKeyReleased

    private void txtBuHaoBanInKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuHaoBanInKeyReleased
        int n = Integer.parseInt(txtTongSoGiay.getText()) - Integer.parseInt(txtBuHaoBanIn.getText());
        txtSoLuongTPYCIn.setText(String.valueOf(n));
    }//GEN-LAST:event_txtBuHaoBanInKeyReleased

    private void txtSoBanPhoiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoBanPhoiKeyReleased
        int n = Integer.parseInt(txtSoLuongTPYCIn.getText()) * Integer.parseInt(cbxTro.getSelectedItem().toString()) * Integer.parseInt(txtSoBanPhoi.getText());
        txtSoLuotIn.setText(String.valueOf(n));
    }//GEN-LAST:event_txtSoBanPhoiKeyReleased

    private void btnTienCongThoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTienCongThoActionPerformed
        //Thêm vào bảng tạm
        String soLuotIn = txtSoLuotIn.getText();
        String soBanPhoi = txtSoBanPhoi.getText();
        LenhInDAL.themVaoBangTam(soLuotIn, soBanPhoi);
        //Lầy công thức 
        String ct = MayInDAL.layCongThucTienTho(cbxNoiIn.getSelectedItem());
        ct = ct.replace("Số bản phơi", "sobanphoi");
        ct = ct.replace("Số lượt in", "soluotin");
        int TienCongTho;
        if (ct.contains("MAX")) {
            ct = ct.replace("MAX", "GREATEST");
            TienCongTho = LenhInDAL.tinhTienMAX(ct);
        } else {
            ct = ct.replace("MIN", "LEAST");
            TienCongTho = LenhInDAL.tinhTienMIN(ct);
        }
        //Tính toán 
        txtTienCongTho.setText(String.valueOf(TienCongTho));
        LenhInDAL.xoaBangTam();
    }//GEN-LAST:event_btnTienCongThoActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnTiLeLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTiLeLaiActionPerformed
        if (txtLaiThuan.getText().equals("") || txtTongChiPhi.getText().equals("")) {
            QLIN.ThongBao("Hãy tính lãi thuần và tính tổng chi phí trước", "Cẩn trọng", 2);
        } else {
            txtTiLeLai.setText(String.valueOf(Double.parseDouble(txtLaiThuan.getText()) / Double.parseDouble(txtTongChiPhi.getText())));
        }
    }//GEN-LAST:event_btnTiLeLaiActionPerformed

    private void btnTongChiPhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTongChiPhiActionPerformed
        if (txtSoLuongTPYC.getText().equals("0") || txtDonGia.getText().equals("0") || txtTongChiPhi.getText().equals("")) {
            QLIN.ThongBao("Hãy nhập số lượng thành phẩm yêu cầu , đơn giá và tính tổng chi phí trước", "Cẩn trọng", 2);
        } else {
            double lai = Integer.parseInt(txtSoLuongTPYC.getText()) * Integer.parseInt(txtDonGia.getText()) - Double.parseDouble(txtTongChiPhi.getText());
            txtLaiThuan.setText(String.valueOf(lai));
        }
    }//GEN-LAST:event_btnTongChiPhiActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnLaiThuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaiThuanActionPerformed
        double tong = Double.valueOf(txtTienCongKan.getText()) + Integer.valueOf(txtTienGiaCongNgoai.getText()) + Integer.valueOf(txtTienCongIn.getText()) + Integer.valueOf(txtTienPhim.getText()) + Integer.valueOf(txtTienGiay.getText()) + Integer.valueOf(txtVanChuyen.getText()) + Integer.valueOf(txtChiPhiKhac.getText());
        txtTongChiPhi.setText(Double.toString(tong));
    }//GEN-LAST:event_btnLaiThuanActionPerformed

    private void btnTienGiayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTienGiayActionPerformed
        if (txtTongSoGiay.getText().equals("") || Integer.parseInt(txtTongSoGiay.getText()) == 0) {
            QLIN.ThongBao("Hãy nhập tổng số giấy", "Cẩn thận ", 2);
        } else {
            int giaTien = GiayInDAL.layGiaTienGiay(cbxGiayIn.getSelectedItem().toString(), cbxKhoGiay.getSelectedItem().toString());
            txtTienGiay.setText(String.valueOf(giaTien * Integer.parseInt(txtTongSoGiay.getText())));
        }
    }//GEN-LAST:event_btnTienGiayActionPerformed

    private void btnTienCongInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTienCongInActionPerformed
        //Thêm vào bảng tạm
        String soLuotIn = txtSoLuotIn.getText();
        String soBanPhoi = txtSoBanPhoi.getText();
        LenhInDAL.themVaoBangTam(soLuotIn, soBanPhoi);
        //Lầy công thức 
        String ct = MayInDAL.layCongThucTienIn(cbxNoiIn.getSelectedItem());
        ct = ct.replace("Số bản phơi", "sobanphoi");
        ct = ct.replace("Số lượt in", "soluotin");
        int TienCongIn;
        if (ct.contains("MAX")) {
            ct = ct.replace("MAX", "GREATEST");
            TienCongIn = LenhInDAL.tinhTienMAX(ct);
        } else {
            ct = ct.replace("MIN", "LEAST");
            TienCongIn = LenhInDAL.tinhTienMIN(ct);
        }
        //Tính toán 
        txtTienCongIn.setText(String.valueOf(TienCongIn));
        LenhInDAL.xoaBangTam();
    }//GEN-LAST:event_btnTienCongInActionPerformed

    private void dateNgayLapPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateNgayLapPropertyChange
        txtThangTinhLuongTho.setText(Integer.toString(dateNgayLap.getDate().getMonth() + 1));
    }//GEN-LAST:event_dateNgayLapPropertyChange

    private void txtDonGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDonGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDonGiaActionPerformed

    private void txtSoLuotInPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtSoLuotInPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuotInPropertyChange

    private void txtSoBanPhoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoBanPhoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoBanPhoiActionPerformed

    private void txtSoLuongTPYCInPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtSoLuongTPYCInPropertyChange
        int n = Integer.parseInt(txtSoLuongTPYCIn.getText()) * Integer.parseInt(cbxTro.getSelectedItem().toString()) * Integer.parseInt(txtSoBanPhoi.getText());
        txtSoLuotIn.setText(String.valueOf(n));
    }//GEN-LAST:event_txtSoLuongTPYCInPropertyChange

    private void txtTongChiPhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongChiPhiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongChiPhiActionPerformed

    private void txtSoLuongTPYCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongTPYCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongTPYCActionPerformed

    private void btnDangXuat4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuat4ActionPerformed
        // TODO add your handling code here:
        frmCacLenhIn frm = new frmCacLenhIn(user);
        frm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnDangXuat4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int n = Integer.parseInt(txtSoLuongTPYCIn.getText()) * Integer.parseInt(cbxTro.getSelectedItem().toString()) * Integer.parseInt(txtSoBanPhoi.getText());
        txtSoLuotIn.setText(String.valueOf(n));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTenHangInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenHangInActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenHangInActionPerformed
    public int getDataTable(JTable table, int row_index, int col_index) {
        return (int) table.getModel().getValueAt(row_index, col_index);
    }

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
            java.util.logging.Logger.getLogger(frmLenhIn.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLenhIn.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLenhIn.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLenhIn.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLenhIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnDangXuat3;
    private javax.swing.JButton btnDangXuat4;
    private javax.swing.JButton btnLaiThuan;
    private javax.swing.JButton btnThemLenh;
    private javax.swing.JButton btnTiLeLai;
    private javax.swing.JButton btnTienCongIn;
    private javax.swing.JButton btnTienCongTho;
    private javax.swing.JButton btnTienGiay;
    private javax.swing.JButton btnTongChiPhi;
    private javax.swing.JComboBox<String> cbxGiayIn;
    private javax.swing.JComboBox<String> cbxKhoGiay;
    private javax.swing.JComboBox<String> cbxKhuonIn;
    private javax.swing.JComboBox<String> cbxMaKhachHang;
    private javax.swing.JComboBox<String> cbxNoiGiaCongNgoai;
    private javax.swing.JComboBox<String> cbxNoiIn;
    private javax.swing.JComboBox<String> cbxSoDonKan;
    private javax.swing.JComboBox<String> cbxTrangThai;
    private javax.swing.JComboBox<String> cbxTro;
    private com.toedter.calendar.JDateChooser dateNgayLap;
    private com.toedter.calendar.JDateChooser dateNgayTraHang;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel lbTime;
    private javax.swing.JSpinner spGiaiDoan;
    private javax.swing.JTable tblTienCongKan;
    private javax.swing.JTextArea txtBinhBan;
    private javax.swing.JTextField txtBuHaoBanIn;
    private javax.swing.JTextField txtBuHaoGiaCong;
    private javax.swing.JTextField txtChiPhiKhac;
    private javax.swing.JTextArea txtDienGiaiChiPhi;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtKhoXuatGiay;
    private javax.swing.JTextField txtLaiThuan;
    private javax.swing.JTextArea txtLuuYDacBietIn;
    private javax.swing.JTextArea txtLuuYGiaCong;
    private javax.swing.JTextField txtMaMatHang;
    private javax.swing.JTextField txtSoBanPhoi;
    private javax.swing.JTextField txtSoDonKhach;
    private javax.swing.JTextField txtSoLenh;
    private javax.swing.JTextField txtSoLuongTPYC;
    private javax.swing.JTextField txtSoLuongTPYCIn;
    private javax.swing.JTextField txtSoLuotIn;
    private javax.swing.JTextField txtTenHangIn;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtThangTinhLuongTho;
    private javax.swing.JTextField txtTiLeLai;
    private javax.swing.JTextField txtTienCongIn;
    private javax.swing.JTextField txtTienCongKan;
    private javax.swing.JTextField txtTienCongTho;
    private javax.swing.JTextField txtTienGiaCongNgoai;
    private javax.swing.JTextField txtTienGiay;
    private javax.swing.JTextField txtTienPhim;
    private javax.swing.JTextField txtTongChiPhi;
    private javax.swing.JTextField txtTongSoGiay;
    private javax.swing.JTextField txtVanChuyen;
    private javax.swing.JTextField txtXuatPhim;
    // End of variables declaration//GEN-END:variables

}
