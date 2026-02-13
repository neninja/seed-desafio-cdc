package app.biblioteca_dev_eficiente.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import app.biblioteca_dev_eficiente.repository.AutorRepository;
import app.biblioteca_dev_eficiente.request.CreateAutorRequest;

@Component
public class UniqueAutorEmailValidator implements Validator {

  private final AutorRepository autorRepository;

  public UniqueAutorEmailValidator(AutorRepository autorRepository) {
    this.autorRepository = autorRepository;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return CreateAutorRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (errors.hasErrors()) {
      return;
    }

    CreateAutorRequest request = (CreateAutorRequest) target;

    String email = request.email();
    if (email == null || email.isBlank()) {
      return; // @NotBlank cuida disso
    }

    if (autorRepository.existsByEmailIgnoreCase(email.trim())) {
      errors.rejectValue("email", "autor.email.duplicado");
    }
  }
}
