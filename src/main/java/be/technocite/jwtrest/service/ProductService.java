package be.technocite.jwtrest.service;

import be.technocite.jwtrest.api.dto.CreateProductCommand;
import be.technocite.jwtrest.api.dto.ProductDTO;
import be.technocite.jwtrest.model.Product;
import be.technocite.jwtrest.repository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Service
public class ProductService implements Function<Product, ProductDTO> {

    @Autowired
    private ProductDAO productDAO;

    public List<ProductDTO> findAll() {
        return productDAO.findAll()
                .stream()
                .map(this)
                .collect(toList());
    }

    public ProductDTO save(Product product) {
        return apply(productDAO.save(product));
    }

    public ProductDTO findById(String id) {
        Product product = productDAO.findById(id);
        if (product != null) {
            return apply(product);
        } else {
            throw new RuntimeException("product not found");
        }
        //return apply(productDAO.findById(id));
    }

    public String handleCreateCommand(CreateProductCommand command) {
        return save(new Product(
                UUID.randomUUID().toString(),
                command.getName(),
                command.getPrice()))
                .getId();
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
