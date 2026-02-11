package app.biblioteca_dev_eficiente.controller;

import java.net.URI;
import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import app.biblioteca_dev_eficiente.dto.AutorResponseDto;
import app.biblioteca_dev_eficiente.dto.CreateAutorRequestDto;
import app.biblioteca_dev_eficiente.model.Autor;
import app.biblioteca_dev_eficiente.repository.AutorRepository;

@RestController
@RequestMapping("/autores")
public class AutorController {

  private final AutorRepository autorRepository;

  public AutorController(AutorRepository autorRepository) {
    this.autorRepository = autorRepository;
  }

  @GetMapping
  public List<AutorResponseDto> index() {
    return autorRepository.findAll().stream().map(AutorResponseDto::new).toList();
  }

  @PostMapping
  @Transactional
  public ResponseEntity<AutorResponseDto> create(
      @RequestBody @Valid CreateAutorRequestDto request, UriComponentsBuilder uriBuilder) {

    Autor autor = request.toModel();
    autorRepository.save(autor);

    URI location = uriBuilder.path("/autores/{id}").buildAndExpand(autor.getId()).toUri();
    return ResponseEntity.created(location).body(new AutorResponseDto(autor));
  }
}
