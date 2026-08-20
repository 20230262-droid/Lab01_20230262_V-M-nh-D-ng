package vn.edu.eaut.lab8.repository;

import vn.edu.eaut.lab8.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private final List<Product> data = new ArrayList<>(List.of(
            new Product(1, "Laptop", 25000000, 10),
            new Product(2, "Chuột không dây", 450000, 25)
    ));
    private int nextId = 3;

    public List<Product> findAll() {
        return data;
    }

    public void add(Product product) {
        product.setId(nextId++);
        data.add(product);
    }
}
