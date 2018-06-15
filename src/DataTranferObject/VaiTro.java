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
public class VaiTro {
    private int maVaiTro;
    private String tenVaiTro;
    private String chucNang;

    public VaiTro(int maVaiTro, String tenVaiTro, String chucNang) {
        this.maVaiTro = maVaiTro;
        this.tenVaiTro = tenVaiTro;
        this.chucNang = chucNang;
    }

    public int getMaVaiTro() {
        return maVaiTro;
    }

    public void setMaVaiTro(int maVaiTro) {
        this.maVaiTro = maVaiTro;
    }

    public String getTenVaiTro() {
        return tenVaiTro;
    }

    public void setTenVaiTro(String tenVaiTro) {
        this.tenVaiTro = tenVaiTro;
    }

    public String getChucNang() {
        return chucNang;
    }

    public void setChucNang(String chucNang) {
        this.chucNang = chucNang;
    }
    
    
    
}
