package dto;

public record MoedaDTO(String base_code, String target_code, Double conversion_result ) {

    public record MoedaDTOVazio(){}
}

