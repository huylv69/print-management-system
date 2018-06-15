/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import PresentationLayer.frmDangNhap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HuyLV
 */
public class ConnectionDB {

    public static Connection con;

    public ConnectionDB() {
        getConnection();
    }

    /**
     *
     * @param Cautruyvan
     * @return
     */
    //Thực thi câu lệnh SELECT
    public static ResultSet ExcuteQueryGetTable(String Cautruyvan) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(Cautruyvan);
            return rs;
        } catch (SQLException ex) {
            System.out.println("Lỗi thực hiện câu truy vấn dữ liệu !");
        }
        return null;
    }

    //Thực thi INSERT, DELETE, UPDATE
    public static boolean ExcuteQueryUpdateDB(String cauTruyVanSQL) {
        Statement stmt;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(cauTruyVanSQL);
            PreparedStatement preparedStatement = con.prepareStatement("insert into lichsu_user(users,query,result) values('" + frmDangNhap.user.getUserName() + "',?," + true + ")");
            preparedStatement.setString(1, cauTruyVanSQL);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            try {
                PreparedStatement preparedStatement = con.prepareStatement("insert into lichsu_user(users,query,result) values('" + frmDangNhap.user.getUserName() + "',?," + false + ")");
                preparedStatement.setString(1, cauTruyVanSQL);
                preparedStatement.executeUpdate();
            } catch (SQLException ex1) {
                Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        }
    }

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/data_qlin",
                            "postgres", "vanhuy");
            if (con != null) {
                System.out.println("Kết nối CSDL SQL Server thành công!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
        }
        return con;
    }
}
