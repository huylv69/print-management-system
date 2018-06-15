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
public class MayIn {

    private String maMayIn; 
    private String tenMayIn;
    private String   ctTienIn;
    private String ctTienCongTho;

    public MayIn(String maMayIn, String tenMayIn, String ctTienIn, String ctTienCongTho) {
        this.maMayIn = maMayIn;
        this.tenMayIn = tenMayIn;
        this.ctTienIn = ctTienIn;
        this.ctTienCongTho = ctTienCongTho;
    }

    public String getMaMayIn() {
        return maMayIn;
    }

    public void setMaMayIn(String maMayIn) {
        this.maMayIn = maMayIn;
    }

    public String getTenMayIn() {
        return tenMayIn;
    }

    public void setTenMayIn(String tenMayIn) {
        this.tenMayIn = tenMayIn;
    }

    public String getCtTienIn() {
        return ctTienIn;
    }

    public void setCtTienIn(String ctTienIn) {
        this.ctTienIn = ctTienIn;
    }

    public String getCtTienCongTho() {
        return ctTienCongTho;
    }

    public void setCtTienCongTho(String ctTienCongTho) {
        this.ctTienCongTho = ctTienCongTho;
    }

}
