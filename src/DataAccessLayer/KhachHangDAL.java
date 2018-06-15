 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import DataAccessLayer.ConnectionDB;
import DataTranferObject.KhachHang;
import DataTranferObject.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HuyLV
 */
public class KhachHangDAL {

    public static ResultSet CauTruyVanLayKhachHangTheoMa(KhachHang khachHang) {
        ResultSet rs;
        String CauTruyVan = "select * from khach_hang where ma_kh='" + khachHang.getMaKhachHang() + "'";
        rs = ConnectionDB.ExcuteQueryGetTable(CauTruyVan);
        return rs;}
    
     public static ResultSet CauTruyVanLayKhachHangTheoMa(String maKhachHang) {
        ResultSet rs;
        String CauTruyVan = "select * from khach_hang where ma_kh='" + maKhachHang + "'";
        rs = ConnectionDB.ExcuteQueryGetTable(CauTruyVan);
        return rs;}

    public static void CauTruyVanThemKhachHang(KhachHang khachHang) {
     String SQLThem = "insert into khach_hang (ten_kh,soPO,diachi_kh,ma_kh) values('" + khachHang.getTenKhachHang()+ "','" + khachHang.getSoPO()+ "','" + khachHang.getDiaChi()+ "','"+khachHang.getMaKhachHang()+"')";
        ConnectionDB.ExcuteQueryUpdateDB(SQLThem);
}

    public static ResultSet CauTruyVanLayTatCaKhachHang() {
     ResultSet rs;
        String SQLSelect = "select * from khach_hang";
        rs = ConnectionDB.ExcuteQueryGetTable(SQLSelect);
        return rs;}

    public static void CauTruyVanSuaKhachHang(KhachHang khachhang) {
        String SQLSua = "update khach_hang set ten_kh='" + khachhang.getTenKhachHang()+ "',diachi_kh='" + khachhang.getDiaChi()+ "',soPO='"+khachhang.getSoPO()+"' where ma_kh='" + khachhang.getMaKhachHang()+ "'";
        ConnectionDB.ExcuteQueryUpdateDB(SQLSua);}

    public static void CauTruyVanXoaKhachHang(int maKhachHang) {
     String sql = "delete from khach_hang where ma_kh='"+maKhachHang+"'";
     ConnectionDB.ExcuteQueryUpdateDB(sql);
    }

   
}
