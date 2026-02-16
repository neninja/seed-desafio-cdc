package app.biblioteca_dev_eficiente.model;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.util.Assert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(
    name = "livros",
    uniqueConstraints = {
      @UniqueConstraint(name = "uk_livros_titulo", columnNames = "titulo"),
      @UniqueConstraint(name = "uk_livros_isbn", columnNames = "isbn")
    })
public class Livro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NotBlank
  private String titulo;

  @Column(nullable = false, length = 500)
  @NotBlank
  @Size(max = 500)
  private String resumo;

  @Lob
  @Column(nullable = true)
  private String sumario;

  @Column(nullable = false, precision = 19, scale = 2)
  @NotNull
  @DecimalMin(value = "20.00")
  private BigDecimal preco;

  @Column(nullable = false)
  @NotNull
  @Min(100)
  private Integer numeroPaginas;

  @Column(nullable = false)
  @NotBlank
  private String isbn;

  @Column(nullable = false)
  @NotNull
  @Future
  private Instant publicadoEm;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "categoria_id", nullable = false)
  @NotNull
  private Categoria categoria;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "autor_id", nullable = false)
  @NotNull
  private Autor autor;

  /**
   * @deprecated construtor para uso do JPA
   */
  @Deprecated
  protected Livro() {}

  public Livro(
      @NotBlank String titulo,
      @NotBlank @Size(max = 500) String resumo,
      String sumario,
      @NotNull @DecimalMin("20.00") BigDecimal preco,
      @NotNull @Min(100) Integer numeroPaginas,
      @NotBlank String isbn,
      @NotNull @Future Instant publicadoEm,
      @NotNull Categoria categoria,
      @NotNull Autor autor) {

    Assert.hasLength(titulo, "Título obrigatório");
    Assert.hasLength(resumo, "Resumo obrigatório");
    Assert.isTrue(resumo.length() <= 500, "Resumo deve ter no máximo 500 caracteres");
    Assert.notNull(preco, "Preço obrigatório");
    Assert.isTrue(preco.compareTo(new BigDecimal("20.00")) >= 0, "Preço mínimo é 20");
    Assert.notNull(numeroPaginas, "Número de páginas obrigatório");
    Assert.isTrue(numeroPaginas >= 100, "Número de páginas mínimo é 100");
    Assert.hasLength(isbn, "ISBN obrigatório");
    Assert.notNull(publicadoEm, "Data de publicação obrigatória");
    Assert.isTrue(publicadoEm.isAfter(Instant.now()), "Data de publicação deve ser no futuro");
    Assert.notNull(categoria, "Categoria obrigatória");
    Assert.notNull(autor, "Autor obrigatório");

    this.titulo = titulo;
    this.resumo = resumo;
    this.sumario = sumario;
    this.preco = preco;
    this.numeroPaginas = numeroPaginas;
    this.isbn = isbn;
    this.publicadoEm = publicadoEm;
    this.categoria = categoria;
    this.autor = autor;
  }

  public Long getId() {
    return id;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getResumo() {
    return resumo;
  }

  public String getSumario() {
    return sumario;
  }

  public BigDecimal getPreco() {
    return preco;
  }

  public Integer getNumeroPaginas() {
    return numeroPaginas;
  }

  public String getIsbn() {
    return isbn;
  }

  public Instant getPublicadoEm() {
    return publicadoEm;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public Autor getAutor() {
    return autor;
  }
}
