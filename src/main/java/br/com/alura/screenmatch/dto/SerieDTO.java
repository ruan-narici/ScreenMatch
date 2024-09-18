package br.com.alura.screenmatch.dto;

import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Episodio;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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
