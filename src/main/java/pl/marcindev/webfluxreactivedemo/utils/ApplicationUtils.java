package pl.marcindev.webfluxreactivedemo.utils;

import org.springframework.beans.BeanUtils;
import pl.marcindev.webfluxreactivedemo.dto.ProductDto;
import pl.marcindev.webfluxreactivedemo.entity.ProductEntity;

public class ApplicationUtils {

    public static ProductDto entityToDto(ProductEntity productEntity) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(productEntity, productDto);
        return productDto;
    }

    public static ProductEntity dtoTOEntity(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productDto, productEntity);
        return productEntity;
    }
}
