package app.biblioteca_dev_eficiente.dto;

import jakarta.validation.constraints.NotBlank;

public record AutorRequestDto(
        @NotBlank String nome
) {}