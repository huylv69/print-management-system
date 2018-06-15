/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTranferObject;

import java.util.Date;

/**
 *
 * @author HuyLV
 */
public class LenhIn {
    private int soLenh;
    private String userName;
    private String makhachHang;
    private String tenKhachHang;
    private String maMatHang;
    private String soDonKan;
    private String soDonKhach;
    private Date ngayLap;
    private String loaiGiay;
    private String khoGiay;
    private int tongSoGiay;
    private String tinhTrang;
    private String luuYGiaCong;
    private String khuonIn;
    private String tenHangIn;
    private int tro;
    private int soBanPhoi;
    private int soLuongTPIn;
    private int buHaoBanIn;
    private int soLuongTPYC;
    private int buHaoGiaCong;
    private int soLuotIn;
    private String binhBan;
    private String luuYDacBiet;
    private String noiIn;
    private String tenGiaCongNgoai;
    private int tienCongNgoai;
    private int tienCongIn;
    private int tienCongThoIn;
    private int tienCongKan;
    private int tienPhim;
    private String xuatPhim;
    private int tienGiay;
    private int vanChuyen;
    private int chiPhiKhac;
    private String dienGiaiChiPhiKhac;
    private String khoXuatgiay;
    private Date ngayGiaoDuKien;
    private double laiThuan;
    private double tiLeLai;
    private double tongChiPhi;
    private int thangTinhLuongThoIn;
    private int donGia;

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public LenhIn(int soLenh, String userName, String makhachHang, String tenKhachHang, String maMatHang, String soDonKan, String soDonKhach, Date ngayLap, String loaiGiay, String khoGiay, int tongSoGiay, String tinhTrang, String luuYGiaCong, String khuonIn, String tenHangIn, int tro, int soBanPhoi, int soLuongTPIn, int buHaoBanIn, int soLuongTPYC, int buHaoGiaCong, int soLuotIn, String binhBan, String luuYDacBiet, String noiIn, String tenGiaCongNgoai, int tienCongNgoai, int tienCongIn, int tienCongThoIn, int tienCongKan, int tienPhim, String xuatPhim, int tienGiay, int vanChuyen, int chiPhiKhac, String dienGiaiChiPhiKhac, String khoXuatgiay, Date ngayGiaoDuKien, double laiThuan, double tiLeLai, double tongChiPhi, int thangTinhLuongThoIn,int donGia) {
        this.soLenh = soLenh;
        this.userName = userName;
        this.makhachHang = makhachHang;
        this.tenKhachHang = tenKhachHang;
        this.maMatHang = maMatHang;
        this.soDonKan = soDonKan;
        this.soDonKhach = soDonKhach;
        this.ngayLap = ngayLap;
        this.loaiGiay = loaiGiay;
        this.khoGiay = khoGiay;
        this.tongSoGiay = tongSoGiay;
        this.tinhTrang = tinhTrang;
        this.luuYGiaCong = luuYGiaCong;
        this.khuonIn = khuonIn;
        this.tenHangIn = tenHangIn;
        this.tro = tro;
        this.soBanPhoi = soBanPhoi;
        this.soLuongTPIn = soLuongTPIn;
        this.buHaoBanIn = buHaoBanIn;
        this.soLuongTPYC = soLuongTPYC;
        this.buHaoGiaCong = buHaoGiaCong;
        this.soLuotIn = soLuotIn;
        this.binhBan = binhBan;
        this.luuYDacBiet = luuYDacBiet;
        this.noiIn = noiIn;
        this.tenGiaCongNgoai = tenGiaCongNgoai;
        this.tienCongNgoai = tienCongNgoai;
        this.tienCongIn = tienCongIn;
        this.tienCongThoIn = tienCongThoIn;
        this.tienCongKan = tienCongKan;
        this.tienPhim = tienPhim;
        this.xuatPhim = xuatPhim;
        this.tienGiay = tienGiay;
        this.vanChuyen = vanChuyen;
        this.chiPhiKhac = chiPhiKhac;
        this.dienGiaiChiPhiKhac = dienGiaiChiPhiKhac;
        this.khoXuatgiay = khoXuatgiay;
        this.ngayGiaoDuKien = ngayGiaoDuKien;
        this.laiThuan = laiThuan;
        this.tiLeLai = tiLeLai;
        this.tongChiPhi = tongChiPhi;
        this.thangTinhLuongThoIn = thangTinhLuongThoIn;
        this.donGia=donGia;
    }

    public LenhIn() {
    }

    public int getSoLenh() {
        return soLenh;
    }

    public void setSoLenh(int soLenh) {
        this.soLenh = soLenh;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMakhachHang() {
        return makhachHang;
    }

    public void setMakhachHang(String makhachHang) {
        this.makhachHang = makhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getMaMatHang() {
        return maMatHang;
    }

    public void setMaMatHang(String maMatHang) {
        this.maMatHang = maMatHang;
    }

    public String getSoDonKan() {
        return soDonKan;
    }

    public void setSoDonKan(String soDonKan) {
        this.soDonKan = soDonKan;
    }

    public String getSoDonKhach() {
        return soDonKhach;
    }

    public void setSoDonKhach(String soDonKhach) {
        this.soDonKhach = soDonKhach;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getLoaiGiay() {
        return loaiGiay;
    }

    public void setLoaiGiay(String loaiGiay) {
        this.loaiGiay = loaiGiay;
    }

    public String getKhoGiay() {
        return khoGiay;
    }

    public void setKhoGiay(String khoGiay) {
        this.khoGiay = khoGiay;
    }

    public int getTongSoGiay() {
        return tongSoGiay;
    }

    public void setTongSoGiay(int tongSoGiay) {
        this.tongSoGiay = tongSoGiay;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getLuuYGiaCong() {
        return luuYGiaCong;
    }

    public void setLuuYGiaCong(String luuYGiaCong) {
        this.luuYGiaCong = luuYGiaCong;
    }

    public String getKhuonIn() {
        return khuonIn;
    }

    public void setKhuonIn(String khuonIn) {
        this.khuonIn = khuonIn;
    }

    public String getTenHangIn() {
        return tenHangIn;
    }

    public void setTenHangIn(String tenHangIn) {
        this.tenHangIn = tenHangIn;
    }

    public int getTro() {
        return tro;
    }

    public void setTro(int tro) {
        this.tro = tro;
    }

    public int getSoBanPhoi() {
        return soBanPhoi;
    }

    public void setSoBanPhoi(int soBanPhoi) {
        this.soBanPhoi = soBanPhoi;
    }

    public int getSoLuongTPIn() {
        return soLuongTPIn;
    }

    public void setSoLuongTPIn(int soLuongTPIn) {
        this.soLuongTPIn = soLuongTPIn;
    }

    public int getBuHaoBanIn() {
        return buHaoBanIn;
    }

    public void setBuHaoBanIn(int buHaoBanIn) {
        this.buHaoBanIn = buHaoBanIn;
    }

    public int getSoLuongTPYC() {
        return soLuongTPYC;
    }

    public void setSoLuongTPYC(int soLuongTPYC) {
        this.soLuongTPYC = soLuongTPYC;
    }

    public int getBuHaoGiaCong() {
        return buHaoGiaCong;
    }

    public void setBuHaoGiaCong(int buHaoGiaCong) {
        this.buHaoGiaCong = buHaoGiaCong;
    }

    public int getSoLuotIn() {
        return soLuotIn;
    }

    public void setSoLuotIn(int soLuotIn) {
        this.soLuotIn = soLuotIn;
    }

    public String getBinhBan() {
        return binhBan;
    }

    public void setBinhBan(String binhBan) {
        this.binhBan = binhBan;
    }

    public String getLuuYDacBiet() {
        return luuYDacBiet;
    }

    public void setLuuYDacBiet(String luuYDacBiet) {
        this.luuYDacBiet = luuYDacBiet;
    }

    public String getNoiIn() {
        return noiIn;
    }

    public void setNoiIn(String noiIn) {
        this.noiIn = noiIn;
    }

    public String getTenGiaCongNgoai() {
        return tenGiaCongNgoai;
    }

    public void setTenGiaCongNgoai(String tenGiaCongNgoai) {
        this.tenGiaCongNgoai = tenGiaCongNgoai;
    }

    public int getTienCongNgoai() {
        return tienCongNgoai;
    }

    public void setTienCongNgoai(int tienCongNgoai) {
        this.tienCongNgoai = tienCongNgoai;
    }

    public int getTienCongIn() {
        return tienCongIn;
    }

    public void setTienCongIn(int tienCongIn) {
        this.tienCongIn = tienCongIn;
    }

    public int getTienCongThoIn() {
        return tienCongThoIn;
    }

    public void setTienCongThoIn(int tienCongThoIn) {
        this.tienCongThoIn = tienCongThoIn;
    }

    public int getTienCongKan() {
        return tienCongKan;
    }

    public void setTienCongKan(int tienCongKan) {
        this.tienCongKan = tienCongKan;
    }

    public int getTienPhim() {
        return tienPhim;
    }

    public void setTienPhim(int tienPhim) {
        this.tienPhim = tienPhim;
    }

    public String getXuatPhim() {
        return xuatPhim;
    }

    public void setXuatPhim(String xuatPhim) {
        this.xuatPhim = xuatPhim;
    }

    public int getTienGiay() {
        return tienGiay;
    }

    public void setTienGiay(int tienGiay) {
        this.tienGiay = tienGiay;
    }

    public int getVanChuyen() {
        return vanChuyen;
    }

    public void setVanChuyen(int vanChuyen) {
        this.vanChuyen = vanChuyen;
    }

    public int getChiPhiKhac() {
        return chiPhiKhac;
    }

    public void setChiPhiKhac(int chiPhiKhac) {
        this.chiPhiKhac = chiPhiKhac;
    }

    public String getDienGiaiChiPhiKhac() {
        return dienGiaiChiPhiKhac;
    }

    public void setDienGiaiChiPhiKhac(String dienGiaiChiPhiKhac) {
        this.dienGiaiChiPhiKhac = dienGiaiChiPhiKhac;
    }

    public String getKhoXuatgiay() {
        return khoXuatgiay;
    }

    public void setKhoXuatgiay(String khoXuatgiay) {
        this.khoXuatgiay = khoXuatgiay;
    }

    public Date getNgayGiaoDuKien() {
        return ngayGiaoDuKien;
    }

    public void setNgayGiaoDuKien(Date ngayGiaoDuKien) {
        this.ngayGiaoDuKien = ngayGiaoDuKien;
    }

    public double getLaiThuan() {
        return laiThuan;
    }

    public void setLaiThuan(double laiThuan) {
        this.laiThuan = laiThuan;
    }

    public double getTiLeLai() {
        return tiLeLai;
    }

    public void setTiLeLai(double tiLeLai) {
        this.tiLeLai = tiLeLai;
    }

    public double getTongChiPhi() {
        return tongChiPhi;
    }

    public void setTongChiPhi(double tongChiPhi) {
        this.tongChiPhi = tongChiPhi;
    }

    public int getThangTinhLuongThoIn() {
        return thangTinhLuongThoIn;
    }

    public void setThangTinhLuongThoIn(int thangTinhLuongThoIn) {
        this.thangTinhLuongThoIn = thangTinhLuongThoIn;
    }
    
}
