/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogicLayer;

import DataAccessLayer.GiayInDAL;
import DataTranferObject.GiayIn;
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
public class GiayInBLL {

    public static ResultSet LayTatCaGiay() {
        ResultSet rs;
        rs = GiayInDAL.CauTruyVanLayThongTinGiay();
        return rs;
    }

    public static void DoDuLieuvaoJtaBle(ResultSet rs, JTable tblGiayIn) {
        Object[] obj = new Object[]{"STT", "Mã Giấy In", "Khổ Giấy In", "Loại Giấy", "Giá tiền"};
        DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
        tblGiayIn.setModel(tableModel);
        frmKeHoach.setSizeJtable(tblGiayIn, frmKeHoach.sizeTblGiayIn);

        try {
            while (rs.next()) {
                Object[] item = new Object[5];
                item[0] = tblGiayIn.getRowCount() + 1;
                item[1] = rs.getInt("ma_giayin");
                item[2] = rs.getString("kho");
                item[3] = rs.getString("loai");
                item[4] = rs.getInt("gia");
                tableModel.addRow(item);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());

        }
    }

    public static boolean themGiayIn(GiayIn giayIn) {
        boolean KQ = kiemTra(giayIn);
        //Kiểm tra Mã Giấy In
        try {
            ResultSet rs = GiayInDAL.CauTruyVanLayTatCaGiayIn(giayIn);
            System.out.println("OK");
            if (rs.next()) {
                QLIN.ThongBao("Giấy In đã có trong danh sách", "Thông Báo", 2);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GiayInBLL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Kiểm tra Lỗi");
        }

        if (KQ) {
            GiayInDAL.CauTruyVanThemGiayIn(giayIn);
            return true;
        }
        return false;
    }

    private static boolean kiemTra(GiayIn giayIn) {
        boolean kq = true;
        if (giayIn.getMaGiay().trim().equals("")) {
            QLIN.ThongBao("Chưa nhập mã giấy", "Thông Báo", 1);
            return false;
        } else if (giayIn.getKhoGiay().trim().equals("")) {
            QLIN.ThongBao("Chưa Nhập Khổ Giấy", "Thông Báo", 1);
            return false;
        } else if (giayIn.getGiaGiay() == 0) {
            QLIN.ThongBao("Chưa Nhập Giá Tiền", "Thông Báo", 1);
            return false;
        } else if (giayIn.getLoaiGiay().trim().equals("")) {
            QLIN.ThongBao("Chưa Nhập Loại Giấy", "Thông Báo", 1);
            return false;
        }
        return kq;
    }

    public static boolean suaGiayIn(GiayIn giayIn) {
        boolean KQ = kiemTra(giayIn);
        //Kiểm tra Mã Khách Hàng
        try {
            ResultSet rs = GiayInDAL.CauTruyVanLayTatCaGiayIn(giayIn);
            if (rs.next() == false) {
                QLIN.ThongBao("Giấy In không có mã trong danh sách", "Thông Báo", 2);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GiayInBLL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Kiểm tra Lỗi");
        }

        if (KQ) {
            GiayInDAL.CauTruyVanSuaGiayIn(giayIn);
            return true;
        }
        return false;
    }

    public static boolean XoaGiayIn(JTable tblGiayIn) {
        int index[] = tblGiayIn.getSelectedRows();
        if (index.length == 0) {
            QLIN.ThongBao("Hãy chọn một Giấy In để xóa !", "Xòe rồi  ", 2);
            return false;
        } else {
            int choice = JOptionPane.showConfirmDialog(tblGiayIn, "Bạn có muốn Xóa ?");
            if (choice == JOptionPane.YES_OPTION) {
                for (int i = 0; i < index.length; i++) {
                    int maGiayIn = (int) tblGiayIn.getValueAt(index[i], 1);
                    GiayInDAL.CauTruyVanXoaGiayIn(maGiayIn);
                }
                return true;
            }
            return false;
        }
    }

}
