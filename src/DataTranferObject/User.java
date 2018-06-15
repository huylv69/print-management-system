/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTranferObject;

/**
 *
 * @author TAO-PC
 */
public class User {

    private String tenUser;
    private String userName;
    private String userPass;
    private String vaiTro;

    public User(String tenUser, String userName, String userPass, String vaiTro) {
        this.tenUser = tenUser;
        this.userName = userName;
        this.userPass = userPass;
        this.vaiTro = vaiTro;
    }

    public User() {
    }

    public String getTenUser() {
        return tenUser;
    }

    public void setTenUser(String tenUser) {
        this.tenUser = tenUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getvaiTro() {
        return vaiTro;
    }

    public void setvaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

}
