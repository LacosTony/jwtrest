package be.technocite.jwtrest.repository.impl;

import be.technocite.jwtrest.model.Product;
import be.technocite.jwtrest.repository.ProductDAO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    private List<Product> products = new ArrayList<>();

    @Override
    public Product findById(String id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Product save(Product product) {
        if (findById(product.getId()) == null) {
            products.add(product);
        } else {
            products.remove(product);
            products.add(product);
        }
        return findById(product.getId());
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    @Override
    public boolean delete(Product product) {
        return products.remove(product);
    }

    @Override
    public void onPostConstruct() {
        products.add(new Product("id1", "banane", 10.5));
        products.add(new Product("id2", "barbie", 15));
    }
}
