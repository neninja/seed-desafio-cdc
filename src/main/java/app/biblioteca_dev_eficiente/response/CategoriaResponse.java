package app.biblioteca_dev_eficiente.response;

import app.biblioteca_dev_eficiente.model.Categoria;

public record CategoriaResponse(Long id, String nome) {
  public CategoriaResponse(Categoria categoria) {
    this(categoria.getId(), categoria.getNome());
  }
}
