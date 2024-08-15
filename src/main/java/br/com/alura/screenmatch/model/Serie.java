package br.com.alura.screenmatch.model;

import java.util.OptionalDouble;

public class Serie {


    private String titulo;
    private Integer temporada;
    private String ano;
    private Double avaliacao;
    private Categoria genero;
    private String atores;
    private String baner;
    private String sinopse;

    public Serie(DadosSerie dadosSerie) {
        this.titulo = dadosSerie.titulo();
        this.temporada = dadosSerie.temporadas();
        this.ano = dadosSerie.ano();
        this.avaliacao = OptionalDouble.of(Double.valueOf(dadosSerie.avaliacao())).orElse(0);
        this.genero = Categoria.fromString(dadosSerie.genero().split(",")[0]);
        this.atores = dadosSerie.atores();
        this.baner = dadosSerie.baner();
        this.sinopse = dadosSerie.sinopse();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public String getBaner() {
        return baner;
    }

    public void setBaner(String baner) {
        this.baner = baner;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    @Override
    public String toString() {
        return
            "genero=" + genero +
            ", titulo='" + titulo + '\'' +
            ", temporada=" + temporada +
            ", ano='" + ano + '\'' +
            ", avaliacao=" + avaliacao +
            ", atores='" + atores + '\'' +
            ", baner='" + baner + '\'' +
            ", sinopse='" + sinopse + '\'';
    }
}
