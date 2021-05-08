package com.urjc.code.products.controller;

import com.urjc.code.products.application.ProductService;
import com.urjc.code.products.application.dto.NewProductDto;
import com.urjc.code.products.application.dto.ProductDto;
import com.urjc.code.products.application.dto.PurchaseDto;
import com.urjc.code.products.application.dto.UpdateProductDescriptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> findAll(@RequestParam(value = "name", required = false) String name) {
        List<ProductDto> products;
        if (name != null) {
            products = this.productService.findByName(name);
        } else {
            products = this.productService.findAll();
        }
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PostMapping("/products")
    public ResponseEntity<Long> save(@RequestBody NewProductDto product) {
        Long id = this.productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> findOne(@PathVariable("id") Long id) {
        ProductDto product = this.productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Long> updateDescription(@PathVariable("id") Long id,
                                                  @RequestBody UpdateProductDescriptionDto productDescriptionDto) {
        Long updatedId = this.productService.updateProductDescription(id, productDescriptionDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedId);
    }

    @PostMapping("/products/{id}")
    public ResponseEntity<Long> purchase(@PathVariable("id") Long id,
                                         @RequestBody PurchaseDto purchaseDto) {
        Long updatedId = this.productService.purchaseProduct(id, purchaseDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedId);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
