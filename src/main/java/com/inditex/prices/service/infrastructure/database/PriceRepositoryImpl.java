package com.inditex.prices.service.infrastructure.database;

import com.inditex.prices.service.domain.database.PriceRepository;
import com.inditex.prices.service.domain.models.Price;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class PriceRepositoryImpl implements PriceRepository {

    @Override
    public Optional<Price> findPrices(Long productId, Long brandId, LocalDateTime dateTime) {
        // Dummy implementation
        return Optional.empty();
    }
}