package vn.edu.eaut.lab7.model;

public class LopHoc {

    private int id;
    private String maLop;
    private String tenLop;
    private String coVan;
    private int soLuongSinhVien;

    public LopHoc() {
    }

    public LopHoc(int id, String maLop, String tenLop,
                  String coVan, int soLuongSinhVien) {

        this.id = id;
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.coVan = coVan;
        this.soLuongSinhVien = soLuongSinhVien;
    }

    public LopHoc(String maLop, String tenLop,
                  String coVan, int soLuongSinhVien) {

        this.maLop = maLop;
        this.tenLop = tenLop;
        this.coVan = coVan;
        this.soLuongSinhVien = soLuongSinhVien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getCoVan() {
        return coVan;
    }

    public void setCoVan(String coVan) {
        this.coVan = coVan;
    }

    public int getSoLuongSinhVien() {
        return soLuongSinhVien;
    }

    public void setSoLuongSinhVien(int soLuongSinhVien) {
        this.soLuongSinhVien = soLuongSinhVien;
    }
}