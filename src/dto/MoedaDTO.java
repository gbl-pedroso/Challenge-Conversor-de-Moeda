package dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MoedaDTO (
        @JsonProperty ("base_code") String baseCode,
        @JsonProperty ("target_code") String targetCode,
        @JsonProperty ("conversion_result") Double conversionResult,
        LocalDateTime dataHora
) {}
