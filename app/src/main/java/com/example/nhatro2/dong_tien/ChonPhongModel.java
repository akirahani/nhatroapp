package com.example.nhatro2.dong_tien;

import java.util.List;

public class ChonPhongModel {
    private String phongtrangthai;

    private int phithanhvien;
    private String thuthemthanhvien;
    private int	songuoio;
    private String danhsachthanhvien;
    private String tienthanhvienthang;

    private int phithietbi;
    private String tienthietbiphaithu;
    private String tenthietbisudung;
    private String landongthietbi;
    private String tienthietbithang;

    private	int tiencocthuthem;
    private	String tiencocthuthemformat;
    private	String	dacoc;
    private	String tencoc;

    private	int tienphongcanthu;
    private	String tennopphong;
    private	String phong;
    private	int dutienphong;
    private	String dutienphongformat;
    private	String lichsunopphong;

    private	String tenchuphong;
    private	String dienthoaichuphong;
    private	int idchuphong;
    private	int idphong;

    private	int sodiendau;
    private	int sodiencuoi;
    private	int sodiensudung;
    private	String tiendiensudung;
    private	String sodienphaithu;

    private	int	sonuocdau;
    private	int sonuoccuoi ;
    private	int sonuocsudung ;
    private	String tiennuocsudung;
    private	String sonuocphaithu;

    private	int tongthu;
    private	String tongthuformat;

    private	String uudai;
    private	int hopdong;

    public ChonPhongModel(String phongtrangthai, int phithanhvien, String thuthemthanhvien, int songuoio, String danhsachthanhvien, String tienthanhvienthang, int phithietbi, String tienthietbiphaithu, String tenthietbisudung, String landongthietbi, String tienthietbithang, int tiencocthuthem, String tiencocthuthemformat, String dacoc, String tencoc, int tienphongcanthu, String tennopphong, String phong, int dutienphong, String dutienphongformat, String lichsunopphong, String tenchuphong, String dienthoaichuphong, int idchuphong, int idphong, int sodiendau, int sodiencuoi, int sodiensudung, String tiendiensudung, String sodienphaithu, int sonuocdau, int sonuoccuoi, int sonuocsudung, String tiennuocsudung, String sonuocphaithu, int tongthu, String tongthuformat, String uudai, int hopdong) {
        this.phongtrangthai = phongtrangthai;
        this.phithanhvien = phithanhvien;
        this.thuthemthanhvien = thuthemthanhvien;
        this.songuoio = songuoio;
        this.danhsachthanhvien = danhsachthanhvien;
        this.tienthanhvienthang = tienthanhvienthang;
        this.phithietbi = phithietbi;
        this.tienthietbiphaithu = tienthietbiphaithu;
        this.tenthietbisudung = tenthietbisudung;
        this.landongthietbi = landongthietbi;
        this.tienthietbithang = tienthietbithang;
        this.tiencocthuthem = tiencocthuthem;
        this.tiencocthuthemformat = tiencocthuthemformat;
        this.dacoc = dacoc;
        this.tencoc = tencoc;
        this.tienphongcanthu = tienphongcanthu;
        this.tennopphong = tennopphong;
        this.phong = phong;
        this.dutienphong = dutienphong;
        this.dutienphongformat = dutienphongformat;
        this.lichsunopphong = lichsunopphong;
        this.tenchuphong = tenchuphong;
        this.dienthoaichuphong = dienthoaichuphong;
        this.idchuphong = idchuphong;
        this.idphong = idphong;
        this.sodiendau = sodiendau;
        this.sodiencuoi = sodiencuoi;
        this.sodiensudung = sodiensudung;
        this.tiendiensudung = tiendiensudung;
        this.sodienphaithu = sodienphaithu;
        this.sonuocdau = sonuocdau;
        this.sonuoccuoi = sonuoccuoi;
        this.sonuocsudung = sonuocsudung;
        this.tiennuocsudung = tiennuocsudung;
        this.sonuocphaithu = sonuocphaithu;
        this.tongthu = tongthu;
        this.tongthuformat = tongthuformat;
        this.uudai = uudai;
        this.hopdong = hopdong;
    }


    public String getPhongtrangthai() {
        return phongtrangthai;
    }

    public void setPhongtrangthai(String phongtrangthai) {
        this.phongtrangthai = phongtrangthai;
    }

    public int getPhithanhvien() {
        return phithanhvien;
    }

    public void setPhithanhvien(int phithanhvien) {
        this.phithanhvien = phithanhvien;
    }

    public String getThuthemthanhvien() {
        return thuthemthanhvien;
    }

    public void setThuthemthanhvien(String thuthemthanhvien) {
        this.thuthemthanhvien = thuthemthanhvien;
    }

    public int getSonguoio() {
        return songuoio;
    }

    public void setSonguoio(int songuoio) {
        this.songuoio = songuoio;
    }

    public String getDanhsachthanhvien() {
        return danhsachthanhvien;
    }

    public void setDanhsachthanhvien(String danhsachthanhvien) {
        this.danhsachthanhvien = danhsachthanhvien;
    }

    public String getTienthanhvienthang() {
        return tienthanhvienthang;
    }

    public void setTienthanhvienthang(String tienthanhvienthang) {
        this.tienthanhvienthang = tienthanhvienthang;
    }

    public int getPhithietbi() {
        return phithietbi;
    }

    public void setPhithietbi(int phithietbi) {
        this.phithietbi = phithietbi;
    }

    public String getTienthietbiphaithu() {
        return tienthietbiphaithu;
    }

    public void setTienthietbiphaithu(String tienthietbiphaithu) {
        this.tienthietbiphaithu = tienthietbiphaithu;
    }

    public String getTenthietbisudung() {
        return tenthietbisudung;
    }

    public void setTenthietbisudung(String tenthietbisudung) {
        this.tenthietbisudung = tenthietbisudung;
    }

    public String getLandongthietbi() {
        return landongthietbi;
    }

    public void setLandongthietbi(String landongthietbi) {
        this.landongthietbi = landongthietbi;
    }

    public String getTienthietbithang() {
        return tienthietbithang;
    }

    public void setTienthietbithang(String tienthietbithang) {
        this.tienthietbithang = tienthietbithang;
    }

    public int getTiencocthuthem() {
        return tiencocthuthem;
    }

    public void setTiencocthuthem(int tiencocthuthem) {
        this.tiencocthuthem = tiencocthuthem;
    }

    public String getTiencocthuthemformat() {
        return tiencocthuthemformat;
    }

    public void setTiencocthuthemformat(String tiencocthuthemformat) {
        this.tiencocthuthemformat = tiencocthuthemformat;
    }

    public String getDacoc() {
        return dacoc;
    }

    public void setDacoc(String dacoc) {
        this.dacoc = dacoc;
    }

    public String getTencoc() {
        return tencoc;
    }

    public void setTencoc(String tencoc) {
        this.tencoc = tencoc;
    }

    public int getTienphongcanthu() {
        return tienphongcanthu;
    }

    public void setTienphongcanthu(int tienphongcanthu) {
        this.tienphongcanthu = tienphongcanthu;
    }

    public String getTennopphong() {
        return tennopphong;
    }

    public void setTennopphong(String tennopphong) {
        this.tennopphong = tennopphong;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public int getDutienphong() {
        return dutienphong;
    }

    public void setDutienphong(int dutienphong) {
        this.dutienphong = dutienphong;
    }

    public String getDutienphongformat() {
        return dutienphongformat;
    }

    public void setDutienphongformat(String dutienphongformat) {
        this.dutienphongformat = dutienphongformat;
    }

    public String getLichsunopphong() {
        return lichsunopphong;
    }

    public void setLichsunopphong(String lichsunopphong) {
        this.lichsunopphong = lichsunopphong;
    }

    public String getTenchuphong() {
        return tenchuphong;
    }

    public void setTenchuphong(String tenchuphong) {
        this.tenchuphong = tenchuphong;
    }

    public String getDienthoaichuphong() {
        return dienthoaichuphong;
    }

    public void setDienthoaichuphong(String dienthoaichuphong) {
        this.dienthoaichuphong = dienthoaichuphong;
    }

    public int getIdchuphong() {
        return idchuphong;
    }

    public void setIdchuphong(int idchuphong) {
        this.idchuphong = idchuphong;
    }

    public int getIdphong() {
        return idphong;
    }

    public void setIdphong(int idphong) {
        this.idphong = idphong;
    }

    public int getSodiendau() {
        return sodiendau;
    }

    public void setSodiendau(int sodiendau) {
        this.sodiendau = sodiendau;
    }

    public int getSodiencuoi() {
        return sodiencuoi;
    }

    public void setSodiencuoi(int sodiencuoi) {
        this.sodiencuoi = sodiencuoi;
    }

    public int getSodiensudung() {
        return sodiensudung;
    }

    public void setSodiensudung(int sodiensudung) {
        this.sodiensudung = sodiensudung;
    }

    public String getTiendiensudung() {
        return tiendiensudung;
    }

    public void setTiendiensudung(String tiendiensudung) {
        this.tiendiensudung = tiendiensudung;
    }

    public String getSodienphaithu() {
        return sodienphaithu;
    }

    public void setSodienphaithu(String sodienphaithu) {
        this.sodienphaithu = sodienphaithu;
    }

    public int getSonuocdau() {
        return sonuocdau;
    }

    public void setSonuocdau(int sonuocdau) {
        this.sonuocdau = sonuocdau;
    }

    public int getSonuoccuoi() {
        return sonuoccuoi;
    }

    public void setSonuoccuoi(int sonuoccuoi) {
        this.sonuoccuoi = sonuoccuoi;
    }

    public int getSonuocsudung() {
        return sonuocsudung;
    }

    public void setSonuocsudung(int sonuocsudung) {
        this.sonuocsudung = sonuocsudung;
    }

    public String getTiennuocsudung() {
        return tiennuocsudung;
    }

    public void setTiennuocsudung(String tiennuocsudung) {
        this.tiennuocsudung = tiennuocsudung;
    }

    public String getSonuocphaithu() {
        return sonuocphaithu;
    }

    public void setSonuocphaithu(String sonuocphaithu) {
        this.sonuocphaithu = sonuocphaithu;
    }

    public int getTongthu() {
        return tongthu;
    }

    public void setTongthu(int tongthu) {
        this.tongthu = tongthu;
    }

    public String getTongthuformat() {
        return tongthuformat;
    }

    public void setTongthuformat(String tongthuformat) {
        this.tongthuformat = tongthuformat;
    }

    public String getUudai() {
        return uudai;
    }

    public void setUudai(String uudai) {
        this.uudai = uudai;
    }

    public int getHopdong() {
        return hopdong;
    }

    public void setHopdong(int hopdong) {
        this.hopdong = hopdong;
    }
}
