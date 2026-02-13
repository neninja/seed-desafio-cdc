package app.biblioteca_dev_eficiente.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import app.biblioteca_dev_eficiente.repository.CategoriaRepository;
import app.biblioteca_dev_eficiente.request.CreateCategoriaRequest;

@Component
public class UniqueCategoriaNomeValidator implements Validator {

  private final CategoriaRepository categoriaRepository;

  public UniqueCategoriaNomeValidator(CategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return CreateCategoriaRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (errors.hasErrors()) {
      return;
    }

    CreateCategoriaRequest request = (CreateCategoriaRequest) target;

    String nome = request.nome();
    if (nome == null || nome.isBlank()) {
      return; // @NotBlank cuida disso
    }

    if (categoriaRepository.existsByNomeIgnoreCase(nome.trim())) {
      errors.rejectValue("nome", "categoria.nome.duplicado");
    }
  }
}
