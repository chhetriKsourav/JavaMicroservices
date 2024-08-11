package com.demoproject.ProductService.service;

import com.demoproject.ProductService.entity.Product;
import com.demoproject.ProductService.exception.ProductServiceCustomException;
import com.demoproject.ProductService.model.ProductResponse;
import com.demoproject.ProductService.model.ProductRequest;
import com.demoproject.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.*;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding product");

        Product product = Product.builder()
                .productName(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);

        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Get the product using productid");

        Product product = productRepository.findById(productId)
                .orElseThrow(
                        ()->new ProductServiceCustomException("Product with given id is not present","PRODUCT NOT FOUND"));

        ProductResponse productReponse = new ProductResponse();
        copyProperties(product,productReponse);
        return productReponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce Quantity {} for id : {}", quantity,productId);

        Product product = productRepository.findById(productId).orElseThrow(()-> new ProductServiceCustomException(
                "Product with given id not found",
                "Product_not_found"
        ));

        if (product.getQuantity() < quantity){
            throw new ProductServiceCustomException("Product does not have sufficient quanity", "Insufficeint quantity");
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        log.info("Quantity updated");
    }
}
