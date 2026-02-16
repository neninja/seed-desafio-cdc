package app.biblioteca_dev_eficiente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.biblioteca_dev_eficiente.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
  boolean existsByTitulo(String titulo);

  boolean existsByIsbn(String isbn);
}
