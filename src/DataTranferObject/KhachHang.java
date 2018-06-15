/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTranferObject;

/**
 *
 * @author HuyLV
 */
public class KhachHang {
    private String tenKhachHang;
    private String diaChi;
    private int maKhachHang;
    private int soPO;

    public KhachHang(String tenKhachHang, String diaChi, int maKhachHang, int soPO) {
        this.tenKhachHang = tenKhachHang;
        this.diaChi = diaChi;
        this.maKhachHang = maKhachHang;
        this.soPO = soPO;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public int getSoPO() {
        return soPO;
    }

    public void setSoPO(int soPO) {
        this.soPO = soPO;
    }
 
}
