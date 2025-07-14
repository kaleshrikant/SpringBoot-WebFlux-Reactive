package com.kaleshrikant.util;

import com.kaleshrikant.dto.ProductDto;
import com.kaleshrikant.entity.Product;
import org.springframework.beans.BeanUtils;

/*
	 Util class to convert entity to dto and vice versa
*/

public class AppUtils {

	public static ProductDto entityToDto(Product product){
		ProductDto productDto = new ProductDto ();
		BeanUtils.copyProperties (product, productDto);
		return productDto;
	}

	public static Product dtoToEntity(ProductDto productDto){
		Product product = new Product ();
		BeanUtils.copyProperties (productDto, product);
		return product;
	}
}
