package vn.edu.eaut.lab8.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import vn.edu.eaut.lab8.model.SinhVien;
import vn.edu.eaut.lab8.repository.SinhVienRepository;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named("sinhVienBean")
@SessionScoped
public class SinhVienBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private SinhVien sinhVien = new SinhVien();
    private String keyword = "";
    private final SinhVienRepository repo = new SinhVienRepository();

    public String save() {
        if (sinhVien.getId() == 0) {
            repo.add(sinhVien);
            addMessage("Thêm thành công", "Sinh viên đã được thêm");
        } else {
            repo.update(sinhVien);
            addMessage("Cập nhật thành công", "Sinh viên đã được cập nhật");
        }

        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        sinhVien = new SinhVien();
        return "sinhvien-list?faces-redirect=true";
    }

    public String edit(int id) {
        SinhVien found = repo.find(id);
        if (found == null) {
            addMessage("Không tìm thấy", "Sinh viên không tồn tại hoặc đã bị xóa");
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            return "sinhvien-list?faces-redirect=true";
        }
        sinhVien = found;
        return "sinhvien-form?faces-redirect=true";
    }

    public void delete(int id) {
        repo.delete(id);
        addMessage("Xóa thành công", "Đã xóa sinh viên");
    }

    public List<SinhVien> getDanhSach() {
        String k = keyword == null ? "" : keyword.toLowerCase().trim();
        return repo.findAll().stream()
                .filter(x -> k.isEmpty()
                        || x.getHoTen().toLowerCase().contains(k)
                        || x.getLop().toLowerCase().contains(k))
                .collect(Collectors.toList());
    }

    private void addMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
    }

    public SinhVien getSinhVien() { return sinhVien; }
    public void setSinhVien(SinhVien sinhVien) { this.sinhVien = sinhVien; }
    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }
}
