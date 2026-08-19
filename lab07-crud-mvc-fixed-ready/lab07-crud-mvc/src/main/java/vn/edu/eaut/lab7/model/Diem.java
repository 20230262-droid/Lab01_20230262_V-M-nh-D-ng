package vn.edu.eaut.lab7.model;

public class Diem {

    private int id;
    private String maSinhVien;
    private String hoTen;
    private double chuyenCan;
    private double giuaKy;
    private double cuoiKy;

    public Diem() {
    }

    public Diem(int id, String maSinhVien, String hoTen,
                double chuyenCan, double giuaKy,
                double cuoiKy) {

        this.id = id;
        this.maSinhVien = maSinhVien;
        this.hoTen = hoTen;
        this.chuyenCan = chuyenCan;
        this.giuaKy = giuaKy;
        this.cuoiKy = cuoiKy;
    }

    public Diem(String maSinhVien, String hoTen,
                double chuyenCan, double giuaKy,
                double cuoiKy) {

        this.maSinhVien = maSinhVien;
        this.hoTen = hoTen;
        this.chuyenCan = chuyenCan;
        this.giuaKy = giuaKy;
        this.cuoiKy = cuoiKy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public double getChuyenCan() {
        return chuyenCan;
    }

    public void setChuyenCan(double chuyenCan) {
        this.chuyenCan = chuyenCan;
    }

    public double getGiuaKy() {
        return giuaKy;
    }

    public void setGiuaKy(double giuaKy) {
        this.giuaKy = giuaKy;
    }

    public double getCuoiKy() {
        return cuoiKy;
    }

    public void setCuoiKy(double cuoiKy) {
        this.cuoiKy = cuoiKy;
    }

    public double getDiemTrungBinh() {
        return chuyenCan * 0.1
                + giuaKy * 0.3
                + cuoiKy * 0.6;
    }
}