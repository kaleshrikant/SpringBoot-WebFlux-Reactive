package com.kaleshrikant.service;

import com.kaleshrikant.dto.ProductDto;
import com.kaleshrikant.repository.ProductRepository;
import com.kaleshrikant.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public Flux <ProductDto> getProducts() {
		return repository
						.findAll ( )
						.map ( AppUtils :: entityToDto );
	}

	public Mono <ProductDto> getProduct(String productId) {
		return repository
						.findById (productId)
						.map (AppUtils :: entityToDto);
	}

	public Flux<ProductDto> getProductsInRange(double minPrice, double maxPrice) {
		return repository.findByPriceBetween(Range.closed(minPrice,maxPrice));
	}

	public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono) {
		return productDtoMono
					.map(AppUtils::dtoToEntity)
					.flatMap(repository::insert)
					.map(AppUtils::entityToDto);
	}

	public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String productId) {
		return repository
						.findById(productId)
						.flatMap(
								product -> productDtoMono
											.map(AppUtils::dtoToEntity)
											.doOnNext(e -> e.setId(productId))
											.flatMap(repository::save)
						)
						.map (AppUtils::entityToDto);
	}

	public Mono<Void> deleteProduct(String productId) {
		return repository.deleteById(productId);
	}

}
