/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import DataTranferObject.LenhIn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HuyLV
 */
public class LenhInDAL {

    public static ResultSet CauTruyVanSoLenhTiepTheo() {
        ResultSet rs;
        String sql = "select nextval('lenhin_solenh_seq')";
        rs = ConnectionDB.ExcuteQueryGetTable(sql);
        return rs;
    }
    //select setval('"lichsu_user_STT_seq"',1)

    public static ResultSet CauTruyVanLaySoLenh(LenhIn lenhIn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void CauTruyVanThemLenhIn(LenhIn lenhIn) {
        String SQLThem = " INSERT INTO lenhin(solenh, user_name, ma_kh, ten_kh, ma_mh, sodonkan, sodonkhach, ngaylap, loaigiay, khogiay, tongsogiay, tinhtrang, luu_y_dacbiet, luu_y_giacong, khuon_in, tenhangin, tro, so_ban_phoi, sl_thanhpham_yc_in, sl_thanhpham_yc, buhao_banin, buhao_giacong, so_luot_in, binhban, noi_in, ten_gc_ngoai, tiencong_ngoai, tiencong_in, tiencong_thoin, tiencong_kan, tien_phim, xuat_phim, tien_giay, vanchuyen, chiphikhac, diengiai_chiphikhac, kho_xuatgiay, ngaygiaohang_dukien, laithuan, tong_chiphi, thang_tinhluong_thoin, tilelai,dongia) VALUES ('" + lenhIn.getSoLenh() + "','" + lenhIn.getUserName() + "','" + lenhIn.getMakhachHang() + "','" + lenhIn.getTenKhachHang() + "','" + lenhIn.getMaMatHang() + "','" + lenhIn.getSoDonKan() + "','" + lenhIn.getSoDonKhach() + "','" + lenhIn.getNgayLap() + "','" + lenhIn.getLoaiGiay() + "','" + lenhIn.getKhoGiay() + "','" + lenhIn.getTongSoGiay() + "','" + lenhIn.getTinhTrang() + "','" + lenhIn.getLuuYDacBiet() + "','" + lenhIn.getLuuYGiaCong() + "','" + lenhIn.getKhuonIn() + "','" + lenhIn.getTenHangIn() + "','" + lenhIn.getTro() + "','" + lenhIn.getSoBanPhoi() + "','" + lenhIn.getSoLuongTPIn() + "','" + lenhIn.getSoLuongTPYC() + "','" + lenhIn.getBuHaoBanIn() + "','" + lenhIn.getBuHaoGiaCong() + "','" + lenhIn.getSoLuotIn() + "','" + lenhIn.getBinhBan() + "','" + lenhIn.getNoiIn() + "','" + lenhIn.getTenGiaCongNgoai() + "','" + lenhIn.getTienCongNgoai() + "','" + lenhIn.getTienCongIn() + "','" + lenhIn.getTienCongThoIn() + "','" + lenhIn.getTienCongKan() + "','" + lenhIn.getTienPhim() + "','" + lenhIn.getXuatPhim() + "','" + lenhIn.getTienGiay() + "','" + lenhIn.getVanChuyen() + "','" + lenhIn.getChiPhiKhac() + "','" + lenhIn.getDienGiaiChiPhiKhac() + "','" + lenhIn.getKhoXuatgiay() + "','" + lenhIn.getNgayGiaoDuKien() + "'," + lenhIn.getLaiThuan() + "," + lenhIn.getTongChiPhi() + "," + lenhIn.getThangTinhLuongThoIn() + "," + lenhIn.getTiLeLai() + ","+lenhIn.getDonGia()+")";
        System.out.println(SQLThem);
        ConnectionDB.ExcuteQueryUpdateDB(SQLThem);

    }

    public static ResultSet cauTruyVanLayLenhIn() {
        ResultSet rs;
        String sql = "select * from lenhin";
        rs = ConnectionDB.ExcuteQueryGetTable(sql);
        return rs;
    }

    public static void themVaoBangTam(String soLuotIn, String soBanPhoi) {
        String SQLThem = " INSERT INTO tblTemp(sobanphoi,soluotin) VALUES (" + soBanPhoi + "," + soLuotIn + ")";
        ConnectionDB.ExcuteQueryUpdateDB(SQLThem);
    }

    public static int tinhTienMAX(String ct) {
        try {
            ResultSet rs;
            String sql = "select " + ct + "from tblTemp";
            rs = ConnectionDB.ExcuteQueryGetTable(sql);
            if (rs.next()) {
                return rs.getInt("greatest");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LenhInDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static void xoaBangTam() {
        String sql = " delete from tblTemp";
        ConnectionDB.ExcuteQueryUpdateDB(sql);
    }

    public static int tinhTienMIN(String ct) {
        try {
            ResultSet rs;
            String sql = "select " + ct + "from tblTemp";
            rs = ConnectionDB.ExcuteQueryGetTable(sql);
            if (rs.next()) {
                return rs.getInt("least");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LenhInDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
