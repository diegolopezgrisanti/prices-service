package com.inditex.prices.service.domain.usecases;

import com.inditex.prices.service.domain.models.Price;

import java.time.LocalDateTime;

public interface FindPricesUseCase {
    Price execute(Long productId, Long brandId, LocalDateTime dateTime);
}