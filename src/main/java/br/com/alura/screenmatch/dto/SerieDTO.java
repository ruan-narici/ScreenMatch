package br.com.alura.screenmatch.dto;

import br.com.alura.screenmatch.model.Categoria;

public record SerieDTO(
        Long id,
        String titulo,
        Integer temporada,
        String ano,
        Double avaliacao,
        Categoria genero,
        String atores,
        String baner,
        String sinopse
) {
}
