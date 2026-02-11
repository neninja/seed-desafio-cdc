package app.biblioteca_dev_eficiente.repository;

import app.biblioteca_dev_eficiente.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    boolean existsByNome(String nome);
}