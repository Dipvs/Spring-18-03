package br.uniesp.si.techback.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_filme")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, length = 50)
    private String genero;

    @Column(name = "ano_lancamento", nullable = false)
    private Integer anoLancamento;

    @Column(length = 500)
    private String sinopse;

    @Column(name = "data_cadastro", updatable = false)
    private LocalDate dataCadastro;

    // Construtor padrão exigido pelo JPA
    public Filme() {
    }

    // Construtor com campos obrigatórios
    public Filme(String titulo, String genero, Integer anoLancamento) {
        this.titulo = titulo;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
    }

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDate.now();
    }

    // Getters e Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título não pode ser vazio");
        }
        this.titulo = titulo;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        if (genero == null || genero.trim().isEmpty()) {
            throw new IllegalArgumentException("Gênero não pode ser vazio");
        }
        this.genero = genero;
    }

    public Integer getAnoLancamento() {
        return this.anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        if (anoLancamento == null || anoLancamento <= 0) {
            throw new IllegalArgumentException("Ano de lançamento inválido");
        }
        this.anoLancamento = anoLancamento;
    }

    public String getSinopse() {
        return this.sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public LocalDate getDataCadastro() {
        return this.dataCadastro;
    }

    protected void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    // equals e hashCode otimizados
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Filme)) return false;
        Filme filme = (Filme) o;
        return Objects.equals(getId(), filme.getId()) &&
                Objects.equals(getTitulo(), filme.getTitulo()) &&
                Objects.equals(getGenero(), filme.getGenero()) &&
                Objects.equals(getAnoLancamento(), filme.getAnoLancamento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitulo(), getGenero(), getAnoLancamento());
    }

    // toString melhorado
    @Override
    public String toString() {
        return "Filme [" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", anoLancamento=" + anoLancamento +
                (sinopse != null ? ", sinopse(length)=" + sinopse.length() : "") +
                ", dataCadastro=" + dataCadastro +
                ']';
    }

    // Método builder estático
    public static FilmeBuilder builder() {
        return new FilmeBuilder();
    }

    // Classe Builder interna
    public static final class FilmeBuilder {
        private String titulo;
        private String genero;
        private Integer anoLancamento;
        private String sinopse;

        private FilmeBuilder() {
        }

        public FilmeBuilder titulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public FilmeBuilder genero(String genero) {
            this.genero = genero;
            return this;
        }

        public FilmeBuilder anoLancamento(Integer anoLancamento) {
            this.anoLancamento = anoLancamento;
            return this;
        }

        public FilmeBuilder sinopse(String sinopse) {
            this.sinopse = sinopse;
            return this;
        }

        public Filme build() {
            Filme filme = new Filme(titulo, genero, anoLancamento);
            filme.setSinopse(sinopse);
            return filme;
        }
    }
}