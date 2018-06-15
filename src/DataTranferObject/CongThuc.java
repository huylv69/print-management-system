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
public class CongThuc implements Cloneable{
    private int maCongThuc;
    private String tenCongThuc;
    private String loai;
    private String maMayIn;

    public CongThuc() {
    }

    public CongThuc(int maCongThuc, String tenCongThuc, String loai, String maMayIn) {
        this.maCongThuc = maCongThuc;
        this.tenCongThuc = tenCongThuc;
        this.loai = loai;
        this.maMayIn = maMayIn;
    }

    @Override
    public CongThuc clone() throws CloneNotSupportedException {
        return (CongThuc) super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    
    public int getMaCongThuc() {
        return maCongThuc;
    }

    public void setMaCongThuc(int maCongThuc) {
        this.maCongThuc = maCongThuc;
    }

    public String getTenCongThuc() {
        return tenCongThuc;
    }

    public void setTenCongThuc(String tenCongThuc) {
        this.tenCongThuc = tenCongThuc;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getMaMayIn() {
        return maMayIn;
    }

    public void setMaMayIn(String maMayIn) {
        this.maMayIn = maMayIn;
    }

    @Override
    public String toString() {
        return "CongThuc{" + "maCongThuc=" + maCongThuc + ", tenCongThuc=" + tenCongThuc + ", loai=" + loai + ", maMayIn=" + maMayIn + '}';
    }

   
    
    
    
}
