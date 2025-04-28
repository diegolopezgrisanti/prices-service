package com.inditex.prices.service.infrastructure.entrypoint.rest;

import com.inditex.prices.service.domain.exceptions.InvalidDateFormatException;
import org.junit.jupiter.api.Test;
import org.openapitools.model.PriceResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PriceApiControllerIntegrationTest {

    @Autowired
    private PriceApiController priceApiController;

    @Test
    void shouldReturnPriceWhenValidInput() {
        // GIVEN
        Long productId = 35455L;
        Long brandId = 1L;
        String dateTime = "2020-06-14T10:00:00";

        PriceResponseDTO expectedResponse = new PriceResponseDTO();
        expectedResponse.setProductId(35455L);
        expectedResponse.setBrandId(1L);
        expectedResponse.setPriceList(1);
        expectedResponse.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0));
        expectedResponse.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        expectedResponse.setFinalPrice(35.50);
        expectedResponse.setCurrency("EUR");

        // WHEN
        ResponseEntity<PriceResponseDTO> responseEntity = priceApiController.getPrices(productId, brandId, dateTime);
        PriceResponseDTO actualResponse = responseEntity.getBody();

        // THEN
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actualResponse).isNotNull();

        assertThat(actualResponse)
                .usingRecursiveComparison()
                .ignoringFields("finalPrice")
                .isEqualTo(expectedResponse);
        assertThat(actualResponse.getFinalPrice().toString())
                .isEqualTo(expectedResponse.getFinalPrice().toString());
    }

    @Test
    void shouldReturnBadRequestWhenDateFormatIsInvalid() {
        // GIVEN
        long productId = 35455L;
        long brandId = 1L;
        String invalidDateTime = "invalid-date-format";

        // WHEN & THEN
        InvalidDateFormatException exception = assertThrows(
                InvalidDateFormatException.class,
                () -> priceApiController.getPrices(productId, brandId, invalidDateTime)
        );

        assertEquals("Invalid date format: " + invalidDateTime, exception.getMessage());
    }
}