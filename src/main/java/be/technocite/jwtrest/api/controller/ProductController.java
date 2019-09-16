package be.technocite.jwtrest.api.controller;

import be.technocite.jwtrest.api.dto.CreateProductCommand;
import be.technocite.jwtrest.api.dto.ProductDTO;
import be.technocite.jwtrest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    /*
    "GET /api/products (findAll)"
    "POST /api/products (create)"
    "GET /api/products/{id} (get)"
    */

    @GetMapping("/api/products")
    public List<ProductDTO> findAll() {
        return productService.findAll();
    }

    @PostMapping("/api/products")
    public String create(@RequestBody CreateProductCommand command) {
        return productService.handleCreateCommand(command);
    }

    @GetMapping("/api/products/{id}")
    public ProductDTO findById(@PathVariable String id) {
        return productService.findById(id);
    }
}
