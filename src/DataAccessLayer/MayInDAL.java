/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import DataTranferObject.CongThuc;
import DataTranferObject.MayIn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author HuyLV
 */
public class MayInDAL {

    public static ResultSet CauTruyVanLayMayInTheoMa(MayIn mayin) {
        ResultSet rs;
        String SQL = "select * from mayin where ma_mayin='" + mayin.getMaMayIn() + "'";
        rs = ConnectionDB.ExcuteQueryGetTable(SQL);
        return rs;
    }

    public static void CauTruyVanThemMayIn(MayIn mayin) {
        String SQLthem = "insert into mayin(ma_mayin,ten_mayin,ct_tienin,ct_tientho) values ('" + mayin.getMaMayIn() + "','" + mayin.getTenMayIn() + "','" + mayin.getCtTienIn() + "','" + mayin.getCtTienCongTho() + "')";
        ConnectionDB.ExcuteQueryUpdateDB(SQLthem);
    }

    public static ResultSet CauTruyVanLayTatCaMayIn() {
        ResultSet rs;
        String SQL = "select * from mayin";
        rs = ConnectionDB.ExcuteQueryGetTable(SQL);
        return rs;

    }

    public static void CauTruyVanSuaMayIn(MayIn mayin) {
        String SQLSua = "update mayin set ct_tientho='" + mayin.getCtTienCongTho() + "',ten_mayin='" + mayin.getTenMayIn() + "',ct_tienin='" + mayin.getCtTienIn() + "' where ma_mayin='" + mayin.getMaMayIn() + "'";
        ConnectionDB.ExcuteQueryUpdateDB(SQLSua);
    }

    public static void CauTruyVanXoaMayIn(String maMayIn) {
        String sql = "delete from mayin where ma_mayin='" + maMayIn + "'";
        ConnectionDB.ExcuteQueryUpdateDB(sql);
    }

    public static ResultSet CauTruyVanLayMayInTheoMa(String maMayIn) {
        ResultSet rs;
        String SQL = "select * from mayin where ma_mayin='" + maMayIn + "'";
        rs = ConnectionDB.ExcuteQueryGetTable(SQL);
        return rs;
    }

    public static void ThemCongThucTienIn(CongThuc congThucTienIn, JTable tblCongThucThanhPhanTienIn) {
        String sql = "insert into congthuc(ten_ct, loai, ma_mayin, ma_ct) VALUES ('" + congThucTienIn.getTenCongThuc() + "','" + congThucTienIn.getLoai() + "','" + congThucTienIn.getMaMayIn() + "','" + congThucTienIn.getMaCongThuc() + "')";
        ConnectionDB.ExcuteQueryUpdateDB(sql);
        for (int i = 0; i < tblCongThucThanhPhanTienIn.getRowCount(); i++) {
            int thanhPhan = Integer.parseInt(tblCongThucThanhPhanTienIn.getValueAt(i, 0).toString());
            double heSo = Double.parseDouble(tblCongThucThanhPhanTienIn.getValueAt(i, 1).toString());
            String tieuChi = tblCongThucThanhPhanTienIn.getValueAt(i, 2).toString();
            String sqltp = "INSERT INTO congthuc_thanhphan(ma_ct, heso, tieuchi, thanhphan) VALUES (" + congThucTienIn.getMaCongThuc() + "," + heSo + ",'" + tieuChi + "','" + thanhPhan + "')";
            ConnectionDB.ExcuteQueryUpdateDB(sqltp);
        }
    }

    public static void ThemCongThucTienTho(CongThuc congThucTienTho, JTable tblCongThucThanhPhanTienTho) {
        String sql = "insert into congthuc(ten_ct, loai, ma_mayin, ma_ct) VALUES ('" + congThucTienTho.getTenCongThuc() + "','" + congThucTienTho.getLoai() + "','" + congThucTienTho.getMaMayIn() + "','" + congThucTienTho.getMaCongThuc() + "')";
        ConnectionDB.ExcuteQueryUpdateDB(sql);
        for (int i = 0; i < tblCongThucThanhPhanTienTho.getRowCount(); i++) {
            int thanhPhan = Integer.parseInt(tblCongThucThanhPhanTienTho.getValueAt(i, 0).toString());
            double heSo = Double.parseDouble(tblCongThucThanhPhanTienTho.getValueAt(i, 1).toString());
            String tieuChi = tblCongThucThanhPhanTienTho.getValueAt(i, 2).toString();
            String sqltp = "INSERT INTO congthuc_thanhphan(ma_ct, heso, tieuchi, thanhphan) VALUES (" + congThucTienTho.getMaCongThuc() + "," + heSo + ",'" + tieuChi + "','" + thanhPhan + "')";
            ConnectionDB.ExcuteQueryUpdateDB(sqltp);
        }
    }

    public static int layMaCongThuc(String maMayIn, int suaTienIn) {
        try {
            ResultSet rsMayIn;
            rsMayIn = CauTruyVanLayMayInTheoMa(maMayIn);
            String SQL;
            if (rsMayIn.next()) {//Có mã máy in
                ResultSet rs;
                if (suaTienIn == 1) {
                    SQL = "select ma_ct from congthuc where ten_ct='Công thức tiền in' and ma_mayin='" + maMayIn + "'";
                    rs = ConnectionDB.ExcuteQueryGetTable(SQL);
                } else {
                    SQL = "select ma_ct from congthuc where ten_ct='Công thức tiền thợ' and ma_mayin='" + maMayIn + "'";
                    rs = ConnectionDB.ExcuteQueryGetTable(SQL);
                }
                if (rs.next()) {
                    return rs.getInt("ma_ct");
                }
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MayInDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static ResultSet layCongThuc(int maCongThuc) {
        String sql = "select * from congthuc_thanhphan where ma_ct = " + maCongThuc;
        return ConnectionDB.ExcuteQueryGetTable(sql);
    }

    public static ResultSet layCongThucIn(String maMayIn, boolean b) {
        String SQL;
        if (b) {
            SQL = "select * from congthuc where ten_ct='Công thức tiền in' and ma_mayin='" + maMayIn + "'";
            return ConnectionDB.ExcuteQueryGetTable(SQL);
        } else {
            SQL = "select * from congthuc where ten_ct='Công thức tiền thợ' and ma_mayin='" + maMayIn + "'";
            return ConnectionDB.ExcuteQueryGetTable(SQL);
        }
    }

    public static void XoaCongThucTienIn(String maMayIn) {
        int maCongThuc = MayInDAL.layMaCongThuc(maMayIn, 1);
        String SQL = "delete from congthuc_thanhphan where ma_ct=" + maCongThuc;
        ConnectionDB.ExcuteQueryUpdateDB(SQL);
        String SQLct = "delete from congthuc where ma_ct=" + maCongThuc;
        ConnectionDB.ExcuteQueryUpdateDB(SQLct);
        System.out.println(SQL + SQLct);
    }

    public static void XoaCongThucTienTho(String maMayIn) {
        int maCongThuc = MayInDAL.layMaCongThuc(maMayIn, 2);
        String SQL = "delete from congthuc_thanhphan where ma_ct=" + maCongThuc;
        ConnectionDB.ExcuteQueryUpdateDB(SQL);
        String SQLct = "delete from congthuc where ma_ct=" + maCongThuc;
        ConnectionDB.ExcuteQueryUpdateDB(SQLct);
        System.out.println(SQL + SQLct);
    }

    public static String layCongThucTienIn(Object selectedItem) {
        try {
            String sql = "select * from mayin where ten_mayin='" + selectedItem + "'";
            ResultSet rs = ConnectionDB.ExcuteQueryGetTable(sql);
            if (rs.next()) {
                return rs.getString("ct_tienin");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MayInDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String layCongThucTienTho(Object selectedItem) {
        try {
            String sql = "select * from mayin where ten_mayin='" + selectedItem + "'";
            ResultSet rs = ConnectionDB.ExcuteQueryGetTable(sql);
            if (rs.next()) {
                return rs.getString("ct_tientho");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MayInDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
