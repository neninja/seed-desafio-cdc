package app.biblioteca_dev_eficiente.controller;

import java.net.URI;
import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import app.biblioteca_dev_eficiente.model.Autor;
import app.biblioteca_dev_eficiente.repository.AutorRepository;
import app.biblioteca_dev_eficiente.request.CreateAutorRequest;
import app.biblioteca_dev_eficiente.response.AutorResponse;
import app.biblioteca_dev_eficiente.validation.UniqueAutorEmailValidator;

@RestController
@RequestMapping("/autores")
public class AutoresController {

  private final AutorRepository autorRepository;
  private final UniqueAutorEmailValidator uniqueAutorEmailValidator;

  public AutoresController(
      AutorRepository autorRepository, UniqueAutorEmailValidator uniqueAutorEmailValidator) {
    this.autorRepository = autorRepository;
    this.uniqueAutorEmailValidator = uniqueAutorEmailValidator;
  }

  @InitBinder
  void initBinder(WebDataBinder binder) {
    binder.addValidators(uniqueAutorEmailValidator);
  }

  @GetMapping()
  public List<AutorResponse> index() {
    return autorRepository.findAll().stream().map(AutorResponse::new).toList();
  }

  @PostMapping
  @Transactional
  public ResponseEntity<AutorResponse> create(
      @RequestBody @Valid CreateAutorRequest request, UriComponentsBuilder uriBuilder) {

    Autor autor = request.toModel();
    autorRepository.save(autor);

    URI location = uriBuilder.path("/autores/{id}").buildAndExpand(autor.getId()).toUri();
    return ResponseEntity.created(location).body(new AutorResponse(autor));
  }
}
