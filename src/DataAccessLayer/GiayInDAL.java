/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import DataTranferObject.GiayIn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HuyLV
 */
public class GiayInDAL {

    public static ResultSet CauTruyVanLayThongTinGiay() {
        ResultSet rs;
        String sql = "select * from giayin ";
        rs = ConnectionDB.ExcuteQueryGetTable(sql);
        return rs;
    }

    public static ResultSet CauTruyVanLayGiayTheoLoai(String loai) {
        ResultSet rs;
        String CauTruyVan = "select * from giayin where loai='" + loai + "'";
        rs = ConnectionDB.ExcuteQueryGetTable(CauTruyVan);
        return rs;
    }

    public static ResultSet CauTruyVanLayTatCaGiayIn(GiayIn giayIn) {
        ResultSet rs;
        String CauTruyVan = "select * from giayin where ma_giayin='" + giayIn.getMaGiay() + "'";
        rs = ConnectionDB.ExcuteQueryGetTable(CauTruyVan);
        return rs;
    }

    public static void CauTruyVanThemGiayIn(GiayIn giayIn) {
        String SQLThem = "insert into giayin (ma_giayin,kho,loai,gia) values('" + giayIn.getMaGiay() + "','" + giayIn.getKhoGiay() + "','" + giayIn.getLoaiGiay() + "','" + giayIn.getGiaGiay() + "')";
        ConnectionDB.ExcuteQueryUpdateDB(SQLThem);
    }

    public static void CauTruyVanSuaGiayIn(GiayIn giayIn) {
        String SQLSua = "update giayin set gia='" + giayIn.getGiaGiay() + "',kho='" + giayIn.getKhoGiay() + "',loai='" + giayIn.getLoaiGiay() + "' where ma_giayin='" + giayIn.getMaGiay() + "'";
        ConnectionDB.ExcuteQueryUpdateDB(SQLSua);
    }

    public static void CauTruyVanXoaGiayIn(int maGiayIn) {
        String sql = "delete from giayin where ma_giayin = '" + maGiayIn + "'";
        ConnectionDB.ExcuteQueryUpdateDB(sql);
    }

    public static int layGiaTienGiay(String loaiGiay, String khogiay) {
        try {
            String CauTruyVan = "select gia from giayin where loai='" + loaiGiay + "'and kho='"+khogiay+"'";
            ResultSet   rs = ConnectionDB.ExcuteQueryGetTable(CauTruyVan);
            if(rs.next()){
                return rs.getInt("gia");
            }  } catch (SQLException ex) {
            Logger.getLogger(GiayInDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
