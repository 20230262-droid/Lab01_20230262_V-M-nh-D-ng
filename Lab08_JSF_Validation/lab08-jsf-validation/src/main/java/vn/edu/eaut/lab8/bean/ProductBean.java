package vn.edu.eaut.lab8.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import vn.edu.eaut.lab8.model.Product;
import vn.edu.eaut.lab8.repository.ProductRepository;

import java.io.Serializable;
import java.util.List;

@Named("productBean")
@SessionScoped
public class ProductBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Product item = new Product();
    private final ProductRepository repo = new ProductRepository();

    public String save() {
        repo.add(item);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công", "Đã lưu sản phẩm"));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        item = new Product();
        return "product-form?faces-redirect=true";
    }

    public List<Product> getDanhSach() { return repo.findAll(); }
    public Product getItem() { return item; }
    public void setItem(Product item) { this.item = item; }
}
