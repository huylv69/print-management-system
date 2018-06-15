/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogicLayer;

import DataAccessLayer.GiaCongNDAL;
import DataTranferObject.GiaCongNgoai;
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
public class GiaCongNBLL {

    public static boolean themGiaCong(GiaCongNgoai giacong) {
        boolean KQ = kiemTra(giacong);
        //Kiểm tra Tên Gia Công
        try {
            ResultSet rs = GiaCongNDAL.CauTruyVanLayGiaCongTheoTen(giacong);
            if (rs.next()) {
                QLIN.ThongBao("Tên Gia Công Đã Có", "Thông Báo", 2);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBLL.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (KQ) {
            GiaCongNDAL.CauTruyVanThemGiaCong(giacong);
            return true;
        }
        return false;
    }

    public static ResultSet LayTatCaGiaCongN() {
        ResultSet rs;
        rs = GiaCongNDAL.CauTruyVanLayTatCaGiaCong();
        return rs;
    }

    public static boolean updateGiaCongN(GiaCongNgoai giacong) {
        boolean KQ = kiemTra(giacong);
        //Kiểm tra Tên Gia Công
        try {
            ResultSet rs = GiaCongNDAL.CauTruyVanLayGiaCongTheoTen(giacong);
            if (rs.next() == false) {
                QLIN.ThongBao("Không tồn tại tên gia công", "Thông Báo", 2);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (KQ) {
            GiaCongNDAL.CauTruyVanSuaGiaCongN(giacong);
            return true;
        }
        return false;
    }

    private static boolean kiemTra(GiaCongNgoai giacong) {
        boolean kq = true;
        if (giacong.getTenGiaCongNgoai().trim().equals("")) {
            QLIN.ThongBao("chưa nhập tên gia công", "Thông Báo", 1);
            return false;
        } else if (giacong.getDiaChi().trim().equals("")) {
            QLIN.ThongBao("Chưa Nhập Địa Chỉ", "Thông Báo", 1);
            return false;
        }
        return kq;
    }

    public static void DoDuLieuvaoJtaBle(ResultSet rsGiaCongN, JTable tblGiaCongN) {
        Object[] obj = new Object[]{"STT", "Tên Gia Công", "Địa Chỉ"};
        DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
        tblGiaCongN.setModel(tableModel);
        frmKeHoach.setSizeJtable(tblGiaCongN, frmKeHoach.sizeTblGiaCongNgoai);

        try {
            while (rsGiaCongN.next()) {
                Object[] item = new Object[5];
                item[0] = tblGiaCongN.getRowCount() + 1;
                item[1] = rsGiaCongN.getString("ten_gc_ngoai");
                item[2] = rsGiaCongN.getString("diachi");
                tableModel.addRow(item);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());

        }
    }

    public static boolean XoaGiaCongN(JTable tblGiaCongN) {
        int index[] = tblGiaCongN.getSelectedRows();
        if (index.length == 0) {
            QLIN.ThongBao("Hãy chọn một nơi gia công để xóa !", "Xòe rồi  ", 2);
            return false;
        } else {
            int choice = JOptionPane.showConfirmDialog(tblGiaCongN, "Bạn có muốn Xóa ?");
            if (choice == JOptionPane.YES_OPTION) {
                for (int i = 0; i < index.length; i++) {
                    String tenGiaCong = (String) tblGiaCongN.getValueAt(index[i], 1);
                    if (GiaCongNDAL.CauTruyVanXoaGiaCong(tenGiaCong)) {
                        return true;
                    } else {
                        QLIN.ThongBao("Đã có lệnh in xử dụng nơi gia công! Hãy xóa lệnh in trước", "Xòe rồi  ", 2);
                        return false;
                    }
                }
            }
            return false;
        }

    }
}
