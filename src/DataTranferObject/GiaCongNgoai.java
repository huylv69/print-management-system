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
public class GiaCongNgoai {
    private String tenGiaCongNgoai;
    private String diaChi;

    public GiaCongNgoai(String tenGiaCongNgoai, String diaChi) {
        this.tenGiaCongNgoai = tenGiaCongNgoai;
        this.diaChi = diaChi;
    }

    public String getTenGiaCongNgoai() {
        return tenGiaCongNgoai;
    }

    public void setTenGiaCongNgoai(String tenGiaCongNgoai) {
        this.tenGiaCongNgoai = tenGiaCongNgoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    
    
}
