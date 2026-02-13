package app.biblioteca_dev_eficiente.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import org.springframework.util.Assert;

@Entity
@Table(
    name = "categorias",
    uniqueConstraints = {@UniqueConstraint(name = "uk_categorias_nome", columnNames = "nome")})
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NotBlank
  private String nome;

  /**
   * @deprecated construtor para uso do JPA
   */
  @Deprecated
  protected Categoria() {}

  public Categoria(@NotBlank String nome) {
    Assert.hasLength(nome, "Nome obrigatório");
    this.nome = nome;
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }
}
