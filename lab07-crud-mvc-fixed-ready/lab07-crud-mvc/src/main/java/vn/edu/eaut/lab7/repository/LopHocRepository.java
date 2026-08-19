package vn.edu.eaut.lab7.repository;

import vn.edu.eaut.lab7.model.LopHoc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LopHocRepository {

    private final List<LopHoc> data = new ArrayList<>();

    private int autoId = 1;

    public LopHocRepository() {

        data.add(new LopHoc(
                autoId++,
                "LH001",
                "Công nghệ thông tin 1",
                "Nguyễn Văn A",
                40
        ));

        data.add(new LopHoc(
                autoId++,
                "LH002",
                "Công nghệ thông tin 2",
                "Trần Văn B",
                38
        ));

        data.add(new LopHoc(
                autoId++,
                "LH003",
                "Khoa học máy tính",
                "Lê Văn C",
                35
        ));
    }

    public List<LopHoc> findAll() {
        return new ArrayList<>(data);
    }

    public LopHoc findById(int id) {

        for (LopHoc lop : data) {
            if (lop.getId() == id) {
                return lop;
            }
        }

        return null;
    }

    public void add(LopHoc lop) {
        lop.setId(autoId++);
        data.add(lop);
    }

    public void update(LopHoc lop) {

        LopHoc old = findById(lop.getId());

        if (old != null) {
            old.setMaLop(lop.getMaLop());
            old.setTenLop(lop.getTenLop());
            old.setCoVan(lop.getCoVan());
            old.setSoLuongSinhVien(lop.getSoLuongSinhVien());
        }
    }

    public void delete(int id) {
        data.removeIf(lop -> lop.getId() == id);
    }

    public List<LopHoc> search(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            return findAll();
        }

        String key = keyword.trim().toLowerCase();

        return data.stream()
                .filter(lop ->
                        lop.getMaLop().toLowerCase().contains(key)
                                || lop.getTenLop().toLowerCase().contains(key)
                )
                .collect(Collectors.toList());
    }
}