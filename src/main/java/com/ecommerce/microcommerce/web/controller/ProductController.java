package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.model.Product;
import com.ecommerce.microcommerce.repository.ProductRepository;
import com.ecommerce.microcommerce.web.exceptions.UnavailableProductException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@Api(description = "API for CRUD operations on products")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ApiOperation(value = "Retrieve the list of available products")
    public List<Product> productList() {
        return (List<Product>) productRepository.findAll();
    }

    @GetMapping(value = "/products/{id}")
    @ApiOperation(value = "Retrieves a product with its ID provided that it exists")
    public Optional<Product> getProduct(@PathVariable int id) throws UnavailableProductException {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new UnavailableProductException("The requested product is not found");
        }
        return product;
    }

    @PostMapping(value = "/products")
    @ApiOperation(value = "Method to add a product to the database")
    public ResponseEntity<Void> addProduct(@Valid @RequestBody Product product) {
        Product productAdded = productRepository.save(product);
        if (productAdded == null) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
