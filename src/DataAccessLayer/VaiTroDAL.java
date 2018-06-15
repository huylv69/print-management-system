/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import DataTranferObject.VaiTro;
import java.sql.ResultSet;

/**
 *
 * @author HuyLV
 */
public class VaiTroDAL {
     /*public static void CauTruyVanThemQuyen(VaiTro vt) {
        String SQLThem = "insert into vaitro(TenQuyen,MoTa) values(N'" + Q.getTenQuyen() + "',N'" + Q.getMoTa() + "')";
        ConnectionDB.ExcuteQueryUpdateDB(SQLThem);
    }

    public static void CauTruyVanSuaQuyen(VaiTro vt) {
        String SQlSua = "update Quyen set TenQuyen=N'" + Q.getTenQuyen() + "',MoTa=N'" + Q.getMoTa() + "' where MaQuyen=" + Q.getMaQuyen();
        ConnectionDB.ExcuteQueryUpdateDB(SQlSua);
    }

    public static void CauTruyVanXoaQuyen(String MaQuyen) {
        String SQLXoa = "delete Quyen where MaQuyen=" + MaQuyen;
        ConnectionDB.ExcuteQueryUpdateDB(SQLXoa);
    }
       */
    public static ResultSet CauTruyVanLayVaiTroNV() {
        ResultSet rs;
        String SQLSelect = "select * from vaitro_user where user_name= ";
        rs = ConnectionDB.ExcuteQueryGetTable(SQLSelect);
        return rs;
    }
    public static ResultSet laytenquyentheomaquyen(int MaQuyen){
        String cautruyvan="select * from Quyen where MaQuyen="+MaQuyen+"";
        ResultSet rs;
        rs= ConnectionDB.ExcuteQueryGetTable(cautruyvan);
        return rs;
    }
}
