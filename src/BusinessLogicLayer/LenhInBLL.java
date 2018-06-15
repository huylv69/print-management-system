/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogicLayer;

import DataAccessLayer.LenhInDAL;
import DataTranferObject.LenhIn;
import PresentationLayer.QLIN;
import java.sql.ResultSet;

/**
 *
 * @author HuyLV
 */
public class LenhInBLL {

    public static ResultSet LaySoLenhTiepTheo() {
        ResultSet rs;
        rs = LenhInDAL.CauTruyVanSoLenhTiepTheo();
        return rs;
    }

    public static void ThemLenhIn(LenhIn lenhIn) {
        if (kiemTra(lenhIn)) {
            LenhInDAL.CauTruyVanThemLenhIn(lenhIn);
        }
    }

    public static boolean kiemTra(LenhIn lenhIn) {
        if (lenhIn.getTenHangIn() == null) {
            QLIN.ThongBao("Chưa nhập tên Hàng In", "Xòe rồi", 2);
            return false;
        } else if (lenhIn.getMaMatHang() == null) {
            QLIN.ThongBao("Chưa nhập mã hàng", "Lỗi rồi", 2);
            return false;
        } else if (lenhIn.getKhoXuatgiay() == null) {
            QLIN.ThongBao("Chưa nhập Kho xuất Giấy", "Lỗi rồi", 2);
            return false;
        } else {
            return true;
        }
    }
}
