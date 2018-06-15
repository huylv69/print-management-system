/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import java.sql.ResultSet;

/**
 *
 * @author HuyLV
 */
public class LichSuDAL {

    public static ResultSet layLichSu() {
        ResultSet rs ;
        String cauTruyVan = "select * from lichsu_user order by times desc";
         rs = ConnectionDB.ExcuteQueryGetTable(cauTruyVan);
         return rs;
    }
    
}
