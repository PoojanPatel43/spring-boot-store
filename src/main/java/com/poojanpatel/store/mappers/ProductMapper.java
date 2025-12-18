package com.poojanpatel.store.mappers;


import com.poojanpatel.store.dtos.ProductDto;
import com.poojanpatel.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "categoryId", source = "category.id")
    ProductDto toDto(Product product);

    Product toEntity(ProductDto request);

    @Mapping(target = "id", ignore = true)
    void updateEntity(ProductDto request, @MappingTarget Product entity);
}
