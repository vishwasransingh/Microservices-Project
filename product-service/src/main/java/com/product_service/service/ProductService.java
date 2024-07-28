package com.product_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product_service.dto.ProductRequest;
import com.product_service.dto.ProductResponse;
import com.product_service.model.Product;
import com.product_service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	private final ProductRepository productRepository;
	
	public void createProduct(ProductRequest productRequest) {
		Product product = Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice())
				.build();
		productRepository.save(product);
		log.info("Product {} is saved", product.getId());
	}
	
	public List<ProductResponse> getAllProducts() {
		List<Product> products = productRepository.findAll();
		List<ProductResponse> productResponse = products.stream()
				.map(this :: mapToProductResponse)
				.toList();
		return productResponse;
	}

	private ProductResponse mapToProductResponse(Product product) {
		
		ProductResponse productResponse = ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
		
		return productResponse;
	}
	
}
