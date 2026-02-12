package app.biblioteca_dev_eficiente.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.springframework.util.Assert;

@Entity
@Table(
    name = "autores",
    uniqueConstraints = {@UniqueConstraint(name = "uk_autores_email", columnNames = "email")})
public class Autor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NotBlank
  private String nome;

  @NotBlank
  @Email
  @Column(nullable = false)
  private String email;

  @NotBlank
  @Size(max = 400)
  @Column(nullable = false, length = 400)
  private String descricao;

  @Column(nullable = false, updatable = false)
  private Instant registradoEm;

  /**
   * @deprecated construtor para uso do JPA
   */
  @Deprecated
  protected Autor() {}

  public Autor(
      @NotBlank String nome,
      @NotBlank @Email String email,
      @NotBlank @Size(max = 400) String descricao) {
    Assert.hasLength(nome, "Nome obrigatório");
    this.nome = nome;
    this.email = email;
    this.descricao = descricao;
  }

  @PrePersist
  private void prePersist() {
    if (this.registradoEm == null) {
      this.registradoEm = Instant.now();
    }
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getEmail() {
    return email;
  }

  public String getDescricao() {
    return descricao;
  }

  public Instant getRegistradoEm() {
    return registradoEm;
  }
}
