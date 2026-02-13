package app.biblioteca_dev_eficiente.controller;

import java.net.URI;
import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import app.biblioteca_dev_eficiente.model.Categoria;
import app.biblioteca_dev_eficiente.repository.CategoriaRepository;
import app.biblioteca_dev_eficiente.request.CreateCategoriaRequest;
import app.biblioteca_dev_eficiente.response.CategoriaResponse;
import app.biblioteca_dev_eficiente.validation.UniqueCategoriaNomeValidator;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {

  private final CategoriaRepository categoriaRepository;
  private final UniqueCategoriaNomeValidator uniqueCategoriaNomeValidator;

  public CategoriasController(
      CategoriaRepository categoriaRepository,
      UniqueCategoriaNomeValidator uniqueCategoriaNomeValidator) {
    this.categoriaRepository = categoriaRepository;
    this.uniqueCategoriaNomeValidator = uniqueCategoriaNomeValidator;
  }

  @InitBinder
  void initBinder(WebDataBinder binder) {
    binder.addValidators(uniqueCategoriaNomeValidator);
  }

  @GetMapping
  public List<CategoriaResponse> index() {
    return categoriaRepository.findAll().stream().map(CategoriaResponse::new).toList();
  }

  @PostMapping
  @Transactional
  public ResponseEntity<CategoriaResponse> create(
      @RequestBody @Valid CreateCategoriaRequest request, UriComponentsBuilder uriBuilder) {

    Categoria categoria = request.toModel();
    categoriaRepository.save(categoria);

    URI location = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
    return ResponseEntity.created(location).body(new CategoriaResponse(categoria));
  }
}
