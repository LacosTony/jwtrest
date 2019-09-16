package be.technocite.jwtrest.service;

import be.technocite.jwtrest.api.dto.ProductDTO;
import be.technocite.jwtrest.model.Product;
import be.technocite.jwtrest.repository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductService implements Function<Product, ProductDTO> {

    @Autowired
    private ProductDAO productDAO;

    public List<ProductDTO> findAll() {
        return productDAO.findAll()
                .stream()
                .map(this)
                .collect(Collectors.toList());
    }

    public ProductDTO save(Product product) {
        return apply(productDAO.save(product));
    }

    public ProductDTO findById(String id) {
        return apply(productDAO.findById(id));
    }

    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }

}
