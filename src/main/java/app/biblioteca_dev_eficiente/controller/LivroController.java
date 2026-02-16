package app.biblioteca_dev_eficiente.controller;

import app.biblioteca_dev_eficiente.request.CreateLivroRequest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.biblioteca_dev_eficiente.model.Autor;
import app.biblioteca_dev_eficiente.model.Categoria;
import app.biblioteca_dev_eficiente.model.Livro;
import app.biblioteca_dev_eficiente.repository.LivroRepository;
import app.biblioteca_dev_eficiente.response.LivroResponse;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;

@RestController
public class LivroController {

  private final EntityManager entityManager;
  private final LivroRepository livroRepository;

  public LivroController(EntityManager entityManager, LivroRepository livroRepository) {
    this.entityManager = entityManager;
    this.livroRepository = livroRepository;
  }

  @PostMapping("/livros")
  @ResponseStatus(HttpStatus.CREATED)
  @Transactional
  public LivroResponse create(@RequestBody @Valid CreateLivroRequest request) {
    if (livroRepository.existsByTitulo(request.titulo())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Título já cadastrado");
    }
    if (livroRepository.existsByIsbn(request.isbn())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ISBN já cadastrado");
    }

    Categoria categoria = entityManager.find(Categoria.class, request.categoriaId());
    if (categoria == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inexistente");
    }

    Autor autor = entityManager.find(Autor.class, request.autorId());
    if (autor == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Autor inexistente");
    }

    Livro livro =
            new Livro(
                    request.titulo(),
                    request.resumo(),
                    request.sumario(),
                    request.preco(),
                    request.numeroPaginas(),
                    request.isbn(),
                    request.publicadoEm(),
                    categoria,
                    autor);

    entityManager.persist(livro);
    entityManager.flush();

    return new LivroResponse(livro);
  }
}