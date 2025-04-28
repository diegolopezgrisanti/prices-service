package com.inditex.prices.service.infrastructure.database;

import com.inditex.prices.service.domain.database.PriceRepository;
import com.inditex.prices.service.domain.models.Price;

import com.inditex.prices.service.infrastructure.entity.PriceEntity;
import com.inditex.prices.service.infrastructure.mappers.PriceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {

    private final JpaPriceRepository jpaPriceRepository;
    private final PriceMapper priceMapper;

    @Override
    public Optional<Price> findPrices(Long productId, Long brandId, LocalDateTime dateTime) {
        Optional<PriceEntity> entityOptional = jpaPriceRepository.findProductPrices(productId, brandId, dateTime);
        return entityOptional.map(priceMapper::toDomain);
    }
}