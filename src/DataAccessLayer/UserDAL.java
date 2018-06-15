/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import DataTranferObject.User;
import PresentationLayer.QLIN;
import java.sql.ResultSet;

/**
 *
 * @author HuyLV
 */
public class UserDAL {

    //Lấy Thông Tin
    public static ResultSet CauTruyVanDangNhap(String userName, String userPass) {
        ResultSet rs;
        String Cautruyvan = "select * from users where user_name='" + userName + "' and user_pass='" + userPass + "'";
        rs = ConnectionDB.ExcuteQueryGetTable(Cautruyvan);
        return rs;
    }

    public static ResultSet CauTruyVanLayThongTinUser() {
        ResultSet rs;
        String SQlSelect = "select * from users ";
        rs = ConnectionDB.ExcuteQueryGetTable(SQlSelect);
        return rs;
    }

    public static ResultSet LayVaiTroNV(String userName) {
        ResultSet rs;
        String SQLSelect = "select * from vaitro_user where user_name= '" + userName + "'";
        rs = ConnectionDB.ExcuteQueryGetTable(SQLSelect);
        return rs;
    }

    //Thêm Sửa Xóa
    public static void CauTruyVanThemUser(User user) {
        String SQLThem = "insert into users (ten_user,user_name,user_pass) values('" + user.getTenUser() + "',N'" + user.getUserName() + "','" + user.getUserPass() + "')";
        ConnectionDB.ExcuteQueryUpdateDB(SQLThem);
        String SQLThemVaiTro = "insert into vaitro_user(user_name,ma_vaitro) values('" + user.getUserName() + "','" + user.getvaiTro() + "')";
        ConnectionDB.ExcuteQueryUpdateDB(SQLThemVaiTro);
        QLIN.ThongBao("Đã Thêm User. ", "Thông Báo", 1);

    }

    public static void CauTruyVanSuaUser(User user) {
        String SQLSua = "update users set ten_user='" + user.getTenUser() + "',user_pass='" + user.getUserPass() + "' where user_name='" + user.getUserName() + "'";
        ConnectionDB.ExcuteQueryUpdateDB(SQLSua);
        String SQLSuaVT = "update vaitro_user set ma_vaitro='" + user.getvaiTro() + "' where user_name='" + user.getUserName() + "'";
        ConnectionDB.ExcuteQueryUpdateDB(SQLSuaVT);
        QLIN.ThongBao("Đã Cập Nhật User. ", "Thông Báo", 1);
    }

    public static void CauTruyVanXoaUser(String userName) {
        String sql = "delete from users where user_name='" + userName + "'";
        ConnectionDB.ExcuteQueryUpdateDB(sql);
        String SQLxoaVT = "delete from vaitro_user where user_name='" + userName + "'";
        ConnectionDB.ExcuteQueryUpdateDB(SQLxoaVT);
        QLIN.ThongBao("Đã Xóa User. ", "Thông Báo", 1);
    }

    public static ResultSet CauTruyVanLayTatCaUserName(User user) {
        ResultSet rs;
        String CauTruyVan = "select * from users where user_name='" + user.getUserName() + "'";
        rs = ConnectionDB.ExcuteQueryGetTable(CauTruyVan);
        return rs;
    }

    public static ResultSet TimKiemTatCa(String TimKiem) {
        String CauTruyVan = " select * from users ";
        return ConnectionDB.ExcuteQueryGetTable(CauTruyVan);
    }

    public static ResultSet TimKiemTheoTen(String textSearch) {
        String CauTruyVan = "select * from users where ten_user='%" + textSearch + "%'";
        return ConnectionDB.ExcuteQueryGetTable(CauTruyVan);
    }

    public static ResultSet TimKiemTheoUser(String textSearch) {
        String CauTruyVan = "select * from users where user_name '%" + textSearch + "%'";
        return ConnectionDB.ExcuteQueryGetTable(CauTruyVan);
    }

    public static void thayDoiThongTin(String userName, String newPass, String hoTen) {
        String cauTruyVan = "update users set user_pass = '" + newPass + "' , ten_user = '" + hoTen + "' where user_name = '" + userName + "';";
        System.out.println(cauTruyVan);
        ConnectionDB.ExcuteQueryUpdateDB(cauTruyVan);
        QLIN.ThongBao("Đã cập nhật thông tin. ", "Thông Báo", 1);
    }
}
