package vn.edu.eaut.lab5.bus;

import vn.edu.eaut.lab5.dal.AuthDAL;
import vn.edu.eaut.lab5.model.TaiKhoan;

public class AuthBUS {

    private final AuthDAL authDAL;

    public AuthBUS() {
        authDAL = new AuthDAL();
    }

    public TaiKhoan login(String username, String password) {

        if (username == null || username.trim().isEmpty()) {
            return null;
        }

        if (password == null || password.isEmpty()) {
            return null;
        }

        return authDAL.login(
                username.trim(),
                password
        );
    }
}