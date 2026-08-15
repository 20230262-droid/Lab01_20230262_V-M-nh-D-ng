package vn.edu.eaut.lab5.model;

public class TaiKhoan {

    private int id;
    private String username;
    private String password;
    private String hoTen;
    private String vaiTro;
    private int trangThai;

    public TaiKhoan() {
    }

    public TaiKhoan(
            int id,
            String username,
            String password,
            String hoTen,
            String vaiTro,
            int trangThai
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.hoTen = hoTen;
        this.vaiTro = vaiTro;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return hoTen + " (" + vaiTro + ")";
    }
}