/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogicLayer;

import DataAccessLayer.LichSuDAL;
import PresentationLayer.frmQuanTriHeThong;
import static PresentationLayer.frmQuanTriHeThong.sizeTblLichSu;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HuyLV
 */
public class LichSuBLL {

    public static ResultSet layTatCaLichSu() {
        ResultSet rs;
        rs = LichSuDAL.layLichSu();
        return rs;
    }

    public static void DoDuLieuvaoJtaBle(ResultSet rsLichSu, JTable tblLichSu) {
        Object[] obj = new Object[]{"STT", "User", "Query", "Times", "Result"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(obj, 0);
        tblLichSu.setModel(defaultTableModel);
        frmQuanTriHeThong.setSizeJtable(tblLichSu, frmQuanTriHeThong.sizeTblLichSu);
        try {
            while (rsLichSu.next()) {
                Object[] item = new Object[5];
                item[0] = rsLichSu.getInt("STT");
                item[1] = rsLichSu.getString("users");
                item[2] = rsLichSu.getString("query");
                item[3] = rsLichSu.getTimestamp("times");
                if (rsLichSu.getBoolean("result")) {
                    item[4] = "Pass";
                } else {
                    item[4] = "Fail";
                }
                defaultTableModel.addRow(item);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

}
