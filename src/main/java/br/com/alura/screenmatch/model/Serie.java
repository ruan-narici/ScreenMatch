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
}
