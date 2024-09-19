package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    SerieRepository serieRepository;

    public List<SerieDTO> obterTodasSeries() {
        return this.converteSerieDTO(serieRepository.findAll());
    }

    public List<SerieDTO> obterTop5Series() {
        return this.converteSerieDTO(serieRepository.findTop5ByOrderByAvaliacaoDesc());
    }

    private List<SerieDTO> converteSerieDTO(List<Serie> series) {
        return series.stream()
                .map(s -> new SerieDTO(
                        s.getId(),
                        s.getTitulo(),
                        s.getTemporada(),
                        s.getAno(),
                        s.getAvaliacao(),
                        s.getGenero(),
                        s.getAtores(),
                        s.getBaner(),
                        s.getSinopse()
                ))
                .collect(Collectors.toList());
    }
}
