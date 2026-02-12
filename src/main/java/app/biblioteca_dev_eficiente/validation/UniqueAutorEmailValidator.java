package app.biblioteca_dev_eficiente.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import app.biblioteca_dev_eficiente.dto.CreateAutorRequestDto;
import app.biblioteca_dev_eficiente.repository.AutorRepository;

@Component
public class UniqueAutorEmailValidator implements Validator {

  private final AutorRepository autorRepository;

  public UniqueAutorEmailValidator(AutorRepository autorRepository) {
    this.autorRepository = autorRepository;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return CreateAutorRequestDto.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (errors.hasErrors()) {
      return;
    }

    CreateAutorRequestDto request = (CreateAutorRequestDto) target;

    String email = request.email();
    if (email == null || email.isBlank()) {
      return; // @NotBlank cuida disso
    }

    if (autorRepository.existsByEmailIgnoreCase(email.trim())) {
      errors.rejectValue("email", "autor.email.duplicado");
    }
  }
}
