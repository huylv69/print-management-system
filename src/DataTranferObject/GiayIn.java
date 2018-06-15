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
public class GiayIn {
    private String maGiay;
    private String khoGiay;
    private String loaiGiay;
    private int giaGiay=0;

    public GiayIn(String maGiay, String khoGiay, String loaiGiay, int giaGiay) {
        this.maGiay = maGiay;
        this.khoGiay = khoGiay;
        this.loaiGiay = loaiGiay;
        this.giaGiay = giaGiay;
    }

    public String getMaGiay() {
        return maGiay;
    }

    public void setMaGiay(String maGiay) {
        this.maGiay = maGiay;
    }

    public String getKhoGiay() {
        return khoGiay;
    }

    public void setKhoGiay(String khoGiay) {
        this.khoGiay = khoGiay;
    }

    public String getLoaiGiay() {
        return loaiGiay;
    }

    public void setLoaiGiay(String loaiGiay) {
        this.loaiGiay = loaiGiay;
    }

    public int getGiaGiay() {
        return giaGiay;
    }

    public void setGiaGiay(int giaGiay) {
        this.giaGiay = giaGiay;
    }
    
}
