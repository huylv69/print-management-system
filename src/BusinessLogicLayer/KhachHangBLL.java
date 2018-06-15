/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogicLayer;

import DataAccessLayer.DonHangDAL;
import DataAccessLayer.KhachHangDAL;
import DataTranferObject.KhachHang;
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
public class KhachHangBLL {

    public static boolean XoaKhachHang(JTable tblKhachHang) {
        int[] index = tblKhachHang.getSelectedRows();
        if (index.length == 0) {
            QLIN.ThongBao("Hãy chọn một Khách hàng để xóa !", "Xòe rồi  ", 2);
            return false;
        } else {
            //tao bang hoi
            int choice = JOptionPane.showConfirmDialog(tblKhachHang, "Bạn sẽ xóa khách hàng này và các đơn hàng của họ ?");
            if (choice == JOptionPane.YES_OPTION) {
                for (int i = 0; i < index.length; i++) {
                    int maKhachHang = (int) tblKhachHang.getValueAt(index[i], 1);
                    DonHangDAL.CauTruyVanXoaDonHangTheoMa(maKhachHang);
                    KhachHangDAL.CauTruyVanXoaKhachHang(maKhachHang);
                }
                return true;
            }
            return false;
        }
    }

    public static ResultSet LayTatCaKhachHang() {
        ResultSet rs;
        rs = KhachHangDAL.CauTruyVanLayTatCaKhachHang();
        return rs;
    }

    public static void DoDuLieuvaoJtaBle(ResultSet rs, JTable tblKhachHang) {
        Object[] obj = new Object[]{"STT", "Mã Khách Hàng", "Tên Khách Hàng", "Địa Chỉ", "Số PO"};
        DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
        tblKhachHang.setModel(tableModel);
        frmKeHoach.setSizeJtable(tblKhachHang, frmKeHoach.sizeTblKhachHang);

        try {
            while (rs.next()) {
                Object[] item = new Object[5];
                item[0] = tblKhachHang.getRowCount() + 1;
                item[1] = rs.getInt("ma_kh");
                item[2] = rs.getString("ten_kh");
                item[3] = rs.getString("diachi_kh");
                item[4] = rs.getInt("soPO");
                tableModel.addRow(item);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public static boolean ThemKhachHang(KhachHang khachHang) {
        boolean KQ = kiemTra(khachHang);
        //Kiểm tra Mã Khách Hàng
        try {
            ResultSet rs = KhachHangDAL.CauTruyVanLayKhachHangTheoMa(khachHang);
            if (rs.next()) {
                QLIN.ThongBao("Khách Hàng đã đăng ký", "Thông Báo", 2);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBLL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Kiểm tra Lỗi");
        }

        if (KQ) {
            KhachHangDAL.CauTruyVanThemKhachHang(khachHang);
            return true;
        }
        return false;
    }

    private static boolean kiemTra(KhachHang khachHang) {
        boolean kq = true;
        if (khachHang.getTenKhachHang().trim().equals("")) {
            QLIN.ThongBao("chưa nhập tên", "Thông Báo", 1);
            return false;
        } else if (khachHang.getDiaChi().trim().equals("")) {
            QLIN.ThongBao("Chưa Nhập Địa Chỉ", "Thông Báo", 1);
            return false;
        }
        return kq;
    }

    public static boolean SuaKhachHang(KhachHang khachhang) {
        boolean KQ = kiemTra(khachhang);
        //Kiểm tra Mã Khách Hàng
        try {
            ResultSet rs = KhachHangDAL.CauTruyVanLayKhachHangTheoMa(khachhang);
            if (rs.next() == false) {
                QLIN.ThongBao("Không tồn tại mã Khách Hàng", "Thông Báo", 2);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBLL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Kiểm tra Lỗi");
        }
        if (KQ) {
            KhachHangDAL.CauTruyVanSuaKhachHang(khachhang);
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
