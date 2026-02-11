package app.biblioteca_dev_eficiente.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import app.biblioteca_dev_eficiente.model.Autor;

public record CreateAutorRequestDto(
    @NotBlank String nome,
    @NotBlank @Email String email,
    @NotBlank @Size(max = 400) String descricao) {

  public Autor toModel() {
    return new Autor(nome, email, descricao);
  }
}
