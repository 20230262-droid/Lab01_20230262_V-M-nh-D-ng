package vn.edu.eaut.lab8.repository;

import vn.edu.eaut.lab8.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachRepository {
    private final List<Sach> data = new ArrayList<>(List.of(
            new Sach(1, "Lập trình Java", "Nguyễn Văn A", 2024),
            new Sach(2, "Jakarta Faces", "Trần Thị B", 2025)
    ));
    private int nextId = 3;

    public List<Sach> findAll() {
        return data;
    }

    public void add(Sach sach) {
        sach.setId(nextId++);
        data.add(sach);
    }
}
