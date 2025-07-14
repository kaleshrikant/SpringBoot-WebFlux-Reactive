package com.kaleshrikant.controller;

import com.kaleshrikant.dto.ProductDto;
import com.kaleshrikant.service.ProductService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;

	public ProductController ( ProductService productService ) {
		this.productService = productService;
	}

	@GetMapping
	public Flux<ProductDto> getProducts() {
		return productService.getProducts ();
	}

	@GetMapping("/{productId}")
	public Mono<ProductDto> getProduct(@PathVariable String productId) {
		return productService.getProduct (productId);
	}

	@GetMapping("/product-range")
	public Flux<ProductDto> getProductsInRange(@RequestParam("min") double lowerBound, @RequestParam("max") double upperBound) {
		return productService.getProductsInRange (lowerBound,upperBound);
	}

	@PostMapping
	public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDto) {
		return productService.saveProduct(productDto);
	}

	@PutMapping("/update/{productId}")
	public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDtoMono, @PathVariable String productId) {
		return productService.updateProduct(productDtoMono, productId);
	}

	@DeleteMapping("/delete/{productId}")
	public Mono<Void> deleteProduct(@PathVariable String productId) {
		return productService.deleteProduct(productId);
	}
}
