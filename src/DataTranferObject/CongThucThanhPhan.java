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
public class CongThucThanhPhan {
    private String tieuChi;
    private double heSo;
    private int thanhPhan;
    private int maCongThuc;

    public CongThucThanhPhan() {
    }

    public CongThucThanhPhan(String tieuChi, double heSo, int thanhPhan, int maCongThuc) {
        this.tieuChi = tieuChi;
        this.heSo = heSo;
        this.thanhPhan = thanhPhan;
        this.maCongThuc = maCongThuc;
    }

    public String getTieuChi() {
        return tieuChi;
    }

    public void setTieuChi(String tieuChi) {
        this.tieuChi = tieuChi;
    }

    public double getHeSo() {
        return heSo;
    }

    public void setHeSo(double heSo) {
        this.heSo = heSo;
    }

    public int getThanhPhan() {
        return thanhPhan;
    }

    public void setThanhPhan(int thanhPhan) {
        this.thanhPhan = thanhPhan;
    }

    public int getMaCongThuc() {
        return maCongThuc;
    }

    public void setMaCongThuc(int maCongThuc) {
        this.maCongThuc = maCongThuc;
    }
    
    
}
