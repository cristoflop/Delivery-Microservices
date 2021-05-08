package com.urjc.code.products.application;

import com.urjc.code.products.application.dto.NewProductDto;
import com.urjc.code.products.application.dto.ProductDto;
import com.urjc.code.products.application.dto.PurchaseDto;
import com.urjc.code.products.application.dto.UpdateProductDescriptionDto;
import com.urjc.code.products.exception.NotEnoughStockToPurchaseException;
import com.urjc.code.products.exception.ResourceAlreadyExistException;
import com.urjc.code.products.exception.ResourceNotFoundException;
import com.urjc.code.products.data.ProductRepository;
import com.urjc.code.products.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> findAll() {
        return this.productRepository.findAll()
                .stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }

    public ProductDto findById(Long id) {
        Product product = this.productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return this.mapToProductDto(product);
    }

    public List<ProductDto> findByName(String name) {
        return this.productRepository.findAllByNameContaining(name)
                .stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }

    public Long save(NewProductDto product) {
        if (this.productRepository.findByName(product.getName()).isPresent())
            throw new ResourceAlreadyExistException();
        return this.productRepository.save(this.mapToProduct(product)).getId();
    }

    public Long updateProductDescription(Long id, UpdateProductDescriptionDto productDescription) {
        Product product = this.productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        product.setDescription(productDescription.getDescription());
        return this.productRepository.save(product).getId();
    }

    public Long purchaseProduct(Long id, PurchaseDto purchase) {
        Product product = this.productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        if (product.getStock() - purchase.getQuantity() < 0)
            throw new NotEnoughStockToPurchaseException();
        product.setStock(product.getStock() - purchase.getQuantity());
        return this.productRepository.save(product).getId();
    }

    public void delete(Long id) {
        this.productRepository.deleteById(id);
    }

    private Product mapToProduct(NewProductDto product) {
        return new Product(
                product.getName(),
                product.getDescription(),
                product.getStock());
    }

    private ProductDto mapToProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getStock());
    }

}
