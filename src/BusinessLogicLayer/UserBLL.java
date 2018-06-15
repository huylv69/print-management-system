/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogicLayer;

import DataAccessLayer.UserDAL;
import DataTranferObject.User;
import PresentationLayer.QLIN;
import PresentationLayer.frmDangNhap;
import PresentationLayer.frmQuanTriHeThong;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HuyLV
 */
public class UserBLL {

    public static User KiemtraDangNhap(String userName, String userPass) {
        User user = new User("", "", "", "");
        ResultSet rs;
        rs = UserDAL.CauTruyVanDangNhap(userName, userPass);
        try {
            if (rs.next()) {
                ResultSet rstrue;
                rstrue = UserDAL.LayVaiTroNV(userName);
                if (rstrue.next()) {
                    frmDangNhap.vaiTro = rstrue.getInt("ma_vaitro");
                    user.setTenUser(rs.getString("ten_user"));
                    user.setUserName(userName);
                    user.setUserPass(userPass);
                    user.setvaiTro(rstrue.getString("ma_vaitro"));
                }
            } else {
                QLIN.ThongBao("Tên Đăng Nhập / Mật Khẩu Không Đúng !", "Xòe Mất Rồi", 0);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public static ResultSet LayTatCaUser() {
        ResultSet rs;
        rs = UserDAL.CauTruyVanLayThongTinUser();
        return rs;
    }

    //Thêm Sửa Xóa
    public static void ThemUser(User user) {
        boolean KQ = kiemTra(user);
        //Kiểm tra Username
        try {
            ResultSet rs = UserDAL.CauTruyVanLayTatCaUserName(user);
            if (rs.next()) {
                QLIN.ThongBao("User đã đăng ký", "Thông Báo", 2);
                return;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (KQ) {
            UserDAL.CauTruyVanThemUser(user);
        }
    }

    public static void SuaUser(User user) {
        boolean KQ = kiemTra(user);
        //Kiểm tra Username
        if (KQ) {
            try {
                ResultSet rs = UserDAL.CauTruyVanLayTatCaUserName(user);
                if (rs.next() == false) {
                    QLIN.ThongBao("Không tồn tại User", "Thông Báo", 2);
                    return;
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserBLL.class.getName()).log(Level.SEVERE, null, ex);
            }
            UserDAL.CauTruyVanSuaUser(user);
        }
    }

    public static void XoaUser(JTable tblUser) {
        int[] index = tblUser.getSelectedRows();
        if (index.length == 0) {
            QLIN.ThongBao("Hãy chọn một User để xóa!", "Xòe rồi  ", 2);
        } else {
            //tao bang hoi
            int choice = JOptionPane.showConfirmDialog(tblUser, "Bạn có thật sự muốn XÓA?");
            if (choice == JOptionPane.YES_OPTION) {
                for (int i = 0; i < index.length; i++) {
                    String userName = tblUser.getValueAt(index[i], 2).toString();
                    System.out.println(userName);
                    UserDAL.CauTruyVanXoaUser(userName);
                }
            }
        }
    }

    private static boolean kiemTra(User user) {
        boolean kq = true;
        if (user.getUserName().trim().equals("")) {
            QLIN.ThongBao("Chưa nhập Username", "Thông Báo", 2);
            return false;
        } else if (user.getUserPass().trim().equals("")) {
            QLIN.ThongBao("Chưa nhập Password", "Thông Báo ", 2);
            return false;
        } else if (user.getvaiTro().trim().equals("")) {
            QLIN.ThongBao("Chưa có vai trò ", "Thông Báo ", 2);
            return false;
        }
        return kq;
    }

    public static void DoDuLieuvaoJtaBle(ResultSet rs, JTable tblUser) {
        Object[] obj = new Object[]{"STT", "Tên User", "Tên Đăng Nhập", "Mật khẩu", "Vai Trò"};
        DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
        tblUser.setModel(tableModel);
        frmQuanTriHeThong.setSizeJtable(tblUser, frmQuanTriHeThong.sizeTblUser);
        try {
            while (rs.next()) {
                Object[] item = new Object[5];
                item[0] = tblUser.getRowCount() + 1;
                item[1] = rs.getString("ten_user");
                item[2] = rs.getString("user_name");
                item[3] = rs.getString("user_pass");
                ResultSet rstrue = UserDAL.LayVaiTroNV((String) item[2]);
                if (rstrue.next()) {
                    switch (rstrue.getString("ma_vaitro")) {
                        case "1": {
                            item[4] = "Quản Trị";
                            break;
                        }
                        case "2": {
                            item[4] = "Sản Xuất";
                            break;
                        }
                        case "3": {
                            item[4] = "Kế Hoạch";
                            break;
                        }
                        case "12": {
                            item[4] = "Quản Trị , Sản Xuất";
                            break;
                        }

                        case "23": {
                            item[4] = "Kế Hoạch , Sản Xuất";
                            break;
                        }
                        case "13": {
                            item[4] = "Quản Trị , Kế Hoạch";
                            break;
                        }
                        case "123": {
                            item[4] = "Quản Trị , Kế Hoạch ,Sản Xuất";
                            break;
                        }
                    }
                }
                tableModel.addRow(item);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());

        }

    }

    public static void TimKiemTatCa(String textSearch, JTable tblUser) {
        DefaultTableModel tbDefaultTableModel = (DefaultTableModel) tblUser.getModel();
        int n = tblUser.getRowCount();
        int m = tblUser.getColumnCount();
        for (int i = 0; i < n; i++) {
            boolean kiemtra = false;
            for (int j = 0; j < m; j++) {
                if (tblUser.getModel().getValueAt(i, j).toString().toLowerCase().contains(textSearch)) {
                    kiemtra = true;
                    break;
                } else {
                    kiemtra = false;
                }
            }
            if (!kiemtra) {
                tbDefaultTableModel.removeRow(i);
                i--;
                n--;
            }
        }
        tblUser.setModel(tbDefaultTableModel);
    }

    private static boolean kiemTraUser(String userName, String currentPass) {
        try {
            ResultSet rs = UserDAL.CauTruyVanDangNhap(userName, currentPass);
            return rs.next();
        } catch (SQLException ex) {
            QLIN.ThongBao("Mật Khẩu Không Đúng", "Thông Báo", 2);
            return false;
        }

    }

    public static void thayDoiThongTin(String userName, String currentPass, String newPass, String confirmPass, String hoTen) {
        if (kiemTraUser(userName, currentPass)) {
            if (newPass.equals(confirmPass)) {
                UserDAL.thayDoiThongTin(userName, newPass, hoTen);
            } else {
                QLIN.ThongBao("Mật Khẩu Mới Không Khớp ! ", "Thông Báo", 0);
            }
        } else {
            QLIN.ThongBao("Mật Khẩu Hiện Tại Không Đúng", "Thông Báo", 2);
        }
    }

    public static void timKiemUser(String textSearch, JTable tblUser) {
        System.out.println("Tìm User");
        DefaultTableModel tbDefaultTableModel = (DefaultTableModel) tblUser.getModel();
        int n = tblUser.getRowCount();
        int j = 2;
        for (int i = 0; i < n; i++) {
            boolean kiemtra ;
            System.out.println(tblUser.getModel().getValueAt(i, j).toString().toLowerCase());
            kiemtra = tblUser.getModel().getValueAt(i, j).toString().toLowerCase().contains(textSearch);
            System.out.println(kiemtra);
            if (!kiemtra) {
                tbDefaultTableModel.removeRow(i);
                n--;
                System.out.println(n + i);
            }
        }
        tblUser.setModel(tbDefaultTableModel);
    }

    public static void timKiemVaiTro(String textSearch, JTable tblUser) {
        DefaultTableModel tbDefaultTableModel = (DefaultTableModel) tblUser.getModel();
         System.out.println("Tìm Kiểu");
        int n = tblUser.getRowCount();
        int j = 4;
        for (int i = 0; i < n; i++) {
            boolean kiemtra;
            System.out.println(tblUser.getModel().getValueAt(i, j).toString().toLowerCase());
            kiemtra = tblUser.getModel().getValueAt(i, j).toString().toLowerCase().contains(textSearch);
            if (!kiemtra) {
                tbDefaultTableModel.removeRow(i);
                i--;
                n--;
            }
        }
        tblUser.setModel(tbDefaultTableModel);
    }

}
