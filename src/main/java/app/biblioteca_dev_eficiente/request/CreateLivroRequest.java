package app.biblioteca_dev_eficiente.request;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateLivroRequest(
    @NotBlank String titulo,
    @NotBlank @Size(max = 500) String resumo,
    String sumario,
    @NotNull @DecimalMin("20.00") BigDecimal preco,
    @NotNull @Min(100) Integer numeroPaginas,
    @NotBlank String isbn,
    @NotNull @Future Instant publicadoEm,
    @NotNull Long categoriaId,
    @NotNull Long autorId) {}
