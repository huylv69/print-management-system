/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTranferObject;


import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author HuyLV
 */
public class DonHang {
    private String soDonKan;
    private Date ngayDatHang;
    private String maKhachHang;

    public DonHang(String soDonKan, Date ngayDatHang, String maKhachHang) {
        this.soDonKan = soDonKan;
        this.ngayDatHang = ngayDatHang;
        this.maKhachHang = maKhachHang;
    }


    public String getSoDonKan() {
        return soDonKan;
    }

    public void setSoDonKan(String soDonKan) {
        this.soDonKan = soDonKan;
    }

    public Date getNgayDatHang() {
        return ngayDatHang;
    }

    public void setNgayDatHang(Date ngayDatHang) {
        this.ngayDatHang = ngayDatHang;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }
    
}
