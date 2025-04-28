package com.inditex.prices.service.infrastructure.mappers;

import com.inditex.prices.service.domain.models.Price;
import com.inditex.prices.service.infrastructure.entity.PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {
    Price toDomain(PriceEntity entity);
}