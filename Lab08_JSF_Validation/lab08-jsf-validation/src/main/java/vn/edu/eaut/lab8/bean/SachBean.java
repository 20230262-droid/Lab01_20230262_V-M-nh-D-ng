package vn.edu.eaut.lab8.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import vn.edu.eaut.lab8.model.Sach;
import vn.edu.eaut.lab8.repository.SachRepository;

import java.io.Serializable;
import java.util.List;

@Named("sachBean")
@SessionScoped
public class SachBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Sach item = new Sach();
    private final SachRepository repo = new SachRepository();

    public String save() {
        repo.add(item);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công", "Đã lưu sách"));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        item = new Sach();
        return "sach-form?faces-redirect=true";
    }

    public List<Sach> getDanhSach() { return repo.findAll(); }
    public Sach getItem() { return item; }
    public void setItem(Sach item) { this.item = item; }
}
