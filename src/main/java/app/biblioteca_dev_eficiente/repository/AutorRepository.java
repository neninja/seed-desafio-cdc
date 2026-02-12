package app.biblioteca_dev_eficiente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.biblioteca_dev_eficiente.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
  boolean existsByNome(String nome);

  boolean existsByEmailIgnoreCase(String email);
}
