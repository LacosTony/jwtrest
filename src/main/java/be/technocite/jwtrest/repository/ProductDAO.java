package be.technocite.jwtrest.repository;

import be.technocite.jwtrest.model.Product;

import java.util.List;

public interface ProductDAO {

    Product findById(String id);

    Product save(Product product);

    List<Product> findAll();

    boolean delete(Product product);

    void onPostConstruct();
}
