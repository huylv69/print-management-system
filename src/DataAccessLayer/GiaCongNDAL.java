/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import DataTranferObject.GiaCongNgoai;
import java.sql.ResultSet;

/**
 *
 * @author HuyLV
 */
public class GiaCongNDAL {

    public static void CauTruyVanThemGiaCong(GiaCongNgoai giacong) {
        String SQLThem = "insert into giacong_ngoai (ten_gc_ngoai  ,diachi) values('" + giacong.getTenGiaCongNgoai() + "','" + giacong.getDiaChi() + "')";
        ConnectionDB.ExcuteQueryUpdateDB(SQLThem);
    }

    public static ResultSet CauTruyVanLayTatCaGiaCong() {
        ResultSet rs;
        String SQLSelect = "select * from giacong_ngoai";
        rs = ConnectionDB.ExcuteQueryGetTable(SQLSelect);
        return rs;
    }

    public static ResultSet CauTruyVanLayGiaCongTheoTen(GiaCongNgoai giacong) {
        ResultSet rs;
        String CauTruyVan = "select * from giacong_ngoai where ten_gc_ngoai='" + giacong.getTenGiaCongNgoai() + "'";
        rs = ConnectionDB.ExcuteQueryGetTable(CauTruyVan);
        return rs;
    }

    public static void CauTruyVanSuaGiaCongN(GiaCongNgoai giacong) {
        String SQLSua = "update giacong_ngoai set diachi='" + giacong.getDiaChi() + "' where ten_gc_ngoai='" + giacong.getTenGiaCongNgoai() + "'";
        ConnectionDB.ExcuteQueryUpdateDB(SQLSua);
    }

    public static boolean CauTruyVanXoaGiaCong(String tenGiaCong) {
        String sql = "delete from giacong_ngoai where ten_gc_ngoai='" + tenGiaCong + "'";
        return ConnectionDB.ExcuteQueryUpdateDB(sql);
    }

}
