package app.biblioteca_dev_eficiente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.biblioteca_dev_eficiente.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
  boolean existsByNomeIgnoreCase(String email);
}
