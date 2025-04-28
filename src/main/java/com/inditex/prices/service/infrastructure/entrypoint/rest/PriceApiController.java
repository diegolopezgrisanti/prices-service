package com.inditex.prices.service.infrastructure.entrypoint.rest;

import com.inditex.prices.service.domain.exceptions.InvalidDateFormatException;
import com.inditex.prices.service.domain.models.Price;
import com.inditex.prices.service.domain.usecases.FindPricesUseCase;
import com.inditex.prices.service.infrastructure.mappers.PriceMapper;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.PricesApi;
import org.openapitools.model.PriceResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@RestController
@RequiredArgsConstructor
public class PriceApiController implements PricesApi {

    private final FindPricesUseCase findPricesUseCase;
    private final PriceMapper priceMapper;

    @Override
    public ResponseEntity<PriceResponseDTO> getPrices(
            Long productId,
            Long brandId,
            String dateTime
    ) {
        LocalDateTime parsedDateTime = parseDateTime(dateTime);
        Price price = findPricesUseCase.execute(productId, brandId, parsedDateTime);

        return ResponseEntity.ok(priceMapper.toResponseDTO(price));
    }

    private LocalDateTime parseDateTime(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Invalid date format: " + dateTime);
        }
    }
}