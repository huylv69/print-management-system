/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import DataTranferObject.DonHang;
import java.sql.ResultSet;

/**
 *
 * @author HuyLV
 */
public class DonHangDAL {

    public static ResultSet CauTruyVanLayTatCaDonHang() {
        ResultSet rs;
        String SQLSelect = "select * from don_hang";
        rs = ConnectionDB.ExcuteQueryGetTable(SQLSelect);
        return rs;
    }

    public static ResultSet CauTruyVanLayDonHangTheoMa(DonHang donHang) {
        ResultSet rs;
        String CauTruyVan = "select * from don_hang where sodon_kan='" + donHang.getSoDonKan() + "'";
        rs = ConnectionDB.ExcuteQueryGetTable(CauTruyVan);
        return rs;
    }

    public static void CauTruyVanThemDonHang(DonHang donHang) {
        String SQLThem = "insert into don_hang (sodon_kan,ngaydat,ma_kh) values('" + donHang.getSoDonKan() + "','" + donHang.getNgayDatHang() + "','" + donHang.getMaKhachHang() + "')";
        ConnectionDB.ExcuteQueryUpdateDB(SQLThem);
    }

    public static void CauTruyVanXoaDonHang(String soDonKan) {
        String sql = "delete from don_hang where sodon_kan = '" + soDonKan + "'";
        ConnectionDB.ExcuteQueryUpdateDB(sql);
    }

    public static void CauTruyVanSuaDonHang(DonHang donHang) {
        String SQLSua = "update don_hang set ngaydat='" + donHang.getNgayDatHang() + "',ma_kh='" + donHang.getMaKhachHang() + "' where sodon_kan='" + donHang.getSoDonKan() + "'";
        ConnectionDB.ExcuteQueryUpdateDB(SQLSua);
    }

    public static ResultSet CauTruyVanLaySoDonTheoMa(String maKH) {
        ResultSet rs;
        String CauTruyVan = "select * from don_hang where ma_kh='" + maKH + "'";
        rs = ConnectionDB.ExcuteQueryGetTable(CauTruyVan);
        return rs;
    }

    public static void CauTruyVanXoaDonHangTheoMa(int maKhachHang) {
        String sql = "delete from don_hang where ma_kh = '" + maKhachHang + "'";
        ConnectionDB.ExcuteQueryUpdateDB(sql);
    }

}
