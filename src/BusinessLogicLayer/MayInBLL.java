/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogicLayer;

import DataAccessLayer.MayInDAL;
import DataTranferObject.CongThuc;
import DataTranferObject.MayIn;
import PresentationLayer.QLIN;
import PresentationLayer.frmKeHoach;
import static PresentationLayer.frmKeHoach.txtCtTienIn;
import static PresentationLayer.frmKeHoach.txtCtTienTho;
import static PresentationLayer.frmKeHoach.congThucTienIn;
import static PresentationLayer.frmKeHoach.congThucTienTho;
import static PresentationLayer.frmKeHoach.tblCongThucThanhPhanTienIn;
import static PresentationLayer.frmKeHoach.tblCongThucThanhPhanTienTho;
import static PresentationLayer.frmKeHoach.txtMaMayIn;
import static PresentationLayer.frmKeHoach.txtTenMayIn;
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
public class MayInBLL {

    public static void ThemMayIn(MayIn mayin) {

        boolean KQ = kiemTraThem(mayin);
        if (KQ) {
            MayInDAL.CauTruyVanThemMayIn(mayin);
            MayInDAL.ThemCongThucTienIn(congThucTienIn, tblCongThucThanhPhanTienIn);
            MayInDAL.ThemCongThucTienTho(congThucTienTho, tblCongThucThanhPhanTienTho);
            congThucTienIn = new CongThuc();
            congThucTienTho = new CongThuc();
            tblCongThucThanhPhanTienIn = new JTable();
            tblCongThucThanhPhanTienTho = new JTable();
            QLIN.ThongBao("Đã thêm thành công", "Ok rồi ", 1);
            txtMaMayIn.setText("");
            txtTenMayIn.setText("");
            txtCtTienIn.setText("");
            txtCtTienTho.setText("");

        }
    }

    private static boolean kiemTraThem(MayIn mayin) {
        boolean kq = true;
        try {
            ResultSet rs = MayInDAL.CauTruyVanLayMayInTheoMa(mayin);
            if (rs.next()) {
                QLIN.ThongBao("Máy In đã tồn tại", "Thông Báo", 2);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBLL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Kiểm tra mã máy in  Lỗi");
        }
        if (congThucTienIn == null) {
            QLIN.ThongBao("Hãy sửa lại công thức tiền in", "Thông Báo", 1);
            txtCtTienIn.setText("");
            txtCtTienTho.setText("");
            return false;
        }
        if (congThucTienTho == null) {
            QLIN.ThongBao("Hãy sửa lại công thức tiền công thợ", "Thông Báo", 1);
            return false;
        }
        if (mayin.getTenMayIn().trim().equals("")) {
            QLIN.ThongBao("Chưa nhập tên máy in", "Thông Báo", 1);
            return false;
        } else if (mayin.getCtTienIn().trim().equals("")) {
            QLIN.ThongBao("Chưa Nhập Công Thức Tiền In", "Thông Báo", 1);
            return false;
        } else if (mayin.getCtTienCongTho().trim().equals("")) {
            QLIN.ThongBao("Chưa nhập công thức tiền thợ", "Thông Báo", 1);
            return false;
        }
        return kq;
    }

    public static ResultSet LayTatCaMayIn() {
        ResultSet rs;
        rs = MayInDAL.CauTruyVanLayTatCaMayIn();
        return rs;
    }

    public static void DoDuLieuvaoJtaBle(ResultSet rs, JTable tblMayIn) {
        Object[] obj = new Object[]{"STT", "Mã Máy In", "Tên Máy In", "Công Thức Tiền In", "Công thức Tiền Thợ"};
        DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
        tblMayIn.setModel(tableModel);
        frmKeHoach.setSizeJtable(tblMayIn, frmKeHoach.sizeTblMayIn);

        try {
            while (rs.next()) {
                Object[] item = new Object[5];
                item[0] = tblMayIn.getRowCount() + 1;
                item[1] = rs.getString("ma_mayin");
                item[2] = rs.getString("ten_mayin");
                item[3] = rs.getString("ct_tienin");
                item[4] = rs.getString("ct_tientho");
                tableModel.addRow(item);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());

        }
    }

    public static void SuaMayIn(MayIn mayin) {
        boolean KQ = kiemTraSua(mayin);
        if (KQ) {
            MayInDAL.CauTruyVanSuaMayIn(mayin);
            MayInDAL.XoaCongThucTienIn(mayin.getMaMayIn());
            MayInDAL.XoaCongThucTienTho(mayin.getMaMayIn());
            MayInDAL.ThemCongThucTienIn(frmKeHoach.congThucTienIn, frmKeHoach.tblCongThucThanhPhanTienIn);
            MayInDAL.ThemCongThucTienTho(frmKeHoach.congThucTienTho, frmKeHoach.tblCongThucThanhPhanTienTho);
            congThucTienIn = new CongThuc();
            congThucTienTho = new CongThuc();
            tblCongThucThanhPhanTienIn = new JTable();
            tblCongThucThanhPhanTienTho = new JTable();
            //Thông báo 
            QLIN.ThongBao("Đã cập nhật thành công", "Ok rồi ", 2);
            txtMaMayIn.setText("");
            txtTenMayIn.setText("");
            txtCtTienIn.setText("");
            txtCtTienTho.setText("");
        }
    }

    private static boolean kiemTraSua(MayIn mayin) {
        boolean kq = true;
        //Kiểm tra Mã Máy In
        try {
            ResultSet rs = MayInDAL.CauTruyVanLayMayInTheoMa(mayin);
            if (rs.next() == false) {
                QLIN.ThongBao("Không tồn tại mã máy in", "Thông Báo", 2);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBLL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Kiểm tra mã máy in  Lỗi");
        }
        if (congThucTienIn == null) {
            QLIN.ThongBao("Hãy sửa lại công thức tiền in", "Thông Báo", 1);
            return false;
        }
        if (congThucTienTho == null) {
            QLIN.ThongBao("Hãy sửa lại công thức tiền công thợ", "Thông Báo", 1);
            return false;
        }
        if (mayin.getTenMayIn().trim().equals("")) {
            QLIN.ThongBao("Chưa nhập tên máy in", "Thông Báo", 1);
            return false;
        } else if (mayin.getCtTienIn().trim().equals("")) {
            QLIN.ThongBao("Chưa Nhập Công Thức Tiền In", "Thông Báo", 1);
            return false;
        } else if (mayin.getCtTienCongTho().trim().equals("")) {
            QLIN.ThongBao("Chưa nhập công thức tiền thợ", "Thông Báo", 1);
            return false;
        }
        return kq;
    }

    public static boolean XoaMayIN(JTable tblMayIn) {
        int[] index = tblMayIn.getSelectedRows();
        if (index.length == 0) {
            QLIN.ThongBao("Hãy chọn một lệnh in để xóa !", "Xòe rồi  ", 2);
            return false;
        } else {
            //tao bang hoi
            int choice = JOptionPane.showConfirmDialog(tblMayIn, "Are you sure?");
            if (choice == JOptionPane.YES_OPTION) {
                for (int i = 0; i < index.length; i++) {
                    String maMayIn = (String) tblMayIn.getValueAt(index[i], 1);
                    MayInDAL.CauTruyVanXoaMayIn(maMayIn);
                }
                return true;
            }
            return false;
        }

    }

}
