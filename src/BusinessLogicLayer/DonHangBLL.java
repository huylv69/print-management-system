/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogicLayer;

import DataAccessLayer.DonHangDAL;
import DataTranferObject.DonHang;
import PresentationLayer.QLIN;
import PresentationLayer.frmKeHoach;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HuyLV
 */
public class DonHangBLL {

    public static ResultSet LayTatCaDonHang() {

        ResultSet rs;
        rs = DonHangDAL.CauTruyVanLayTatCaDonHang();
        return rs;
    }

    public static void DoDuLieuvaoJtaBle(ResultSet rsDH, JTable tblDonHang) {
        Object[] obj = new Object[]{"STT", "Số Đơn Của Kan", "Ngày Đặt Hàng", "Mã Khách Hàng"};
        DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
        tblDonHang.setModel(tableModel);
        frmKeHoach.setSizeJtable(tblDonHang, frmKeHoach.sizeTblDonHang);
        try {
            while (rsDH.next()) {
                Object[] item = new Object[5];
                item[0] = tblDonHang.getRowCount() + 1;
                item[1] = rsDH.getString("sodon_kan");
                item[2] = rsDH.getString("ngaydat");
                item[3] = rsDH.getString("ma_kh");
                tableModel.addRow(item);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());

        }
    }

    public static boolean themDonHang(DonHang donHang) {
        boolean KQ = kiemTra(donHang);
        //Kiểm tra Mã Khách Hàng
        try {
            ResultSet rs = DonHangDAL.CauTruyVanLayDonHangTheoMa(donHang);
            if (rs.next()) {
                QLIN.ThongBao("Đơn Hàng đã có", "Thông Báo", 2);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBLL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Kiểm tra Lỗi");
        }

        if (KQ) {
            DonHangDAL.CauTruyVanThemDonHang(donHang);
            return true;
        }
        return false;
    }

    private static boolean kiemTra(DonHang donHang) {
        boolean kq = true;
        if (donHang.getMaKhachHang().trim().equals("")) {
            QLIN.ThongBao("chưa nhập mã khách hàng", "Thông Báo", 1);
            return false;
        } else if (donHang.getSoDonKan().trim().equals("")) {
            QLIN.ThongBao("Chưa Nhập Số Đơn Kan", "Thông Báo", 1);
            return false;
        }
        return kq;
    }

    public static boolean XoaDonHang(JTable tblDonHang) {
        int index[] = tblDonHang.getSelectedRows();
        if (index.length == 0) {
            QLIN.ThongBao("Hãy chọn một Đơn Hàng để xóa !", "Xòe rồi  ", 2);
            return false;

        } else {
            int choice = JOptionPane.showConfirmDialog(tblDonHang, "Bạn có muốn Xóa ?");
            if (choice == JOptionPane.YES_OPTION) {
                for (int i = 0; i < index.length; i++) {
                    String soDonKan = (String) tblDonHang.getValueAt(index[i], 1);
                    DonHangDAL.CauTruyVanXoaDonHang(soDonKan);
                }
                return true;
            }
            return false;
        }
    }

    public static boolean suaDonHang(DonHang donHang) {
        boolean KQ = kiemTra(donHang);
        //Kiểm tra Mã Đơn Hàng
        try {
            ResultSet rs = DonHangDAL.CauTruyVanLayDonHangTheoMa(donHang);
            if (rs.next() == false) {
                QLIN.ThongBao("Đơn Hàng không có mã trong danh sách", "Thông Báo", 2);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GiayInBLL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Kiểm tra Lỗi");
        }

        if (KQ) {
            DonHangDAL.CauTruyVanSuaDonHang(donHang);
            return true;
        }
        return false;
    }

    public static void TimKiemTatCa(String textSearch, JTable tblSearch) {
        DefaultTableModel tbDefaultTableModel = (DefaultTableModel) tblSearch.getModel();
        int n = tblSearch.getRowCount();
        int m = tblSearch.getColumnCount();
        for (int i = 0; i < n; i++) {
            boolean kiemtra = false;
            for (int j = 0; j < m; j++) {
                if (tblSearch.getModel().getValueAt(i, j).toString().contains(textSearch)) {
                    kiemtra = true;
                    break;
                } else {
                    kiemtra = false;
                }
            }
            if (!kiemtra) {
                tbDefaultTableModel.removeRow(i);
                i--;
                n--;
            }
        }
        tblSearch.setModel(tbDefaultTableModel);
    }

}
