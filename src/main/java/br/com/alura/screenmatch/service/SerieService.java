package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.dto.EpisodioDTO;
import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public List<SerieDTO> obterLancamentos() {
        return this.converteSerieDTO(serieRepository.lancamentosRecentes());
    }

    public SerieDTO obterPorID(Long id) {
        Optional<Serie> optionalSerie = serieRepository.findById(id);

        if (optionalSerie.isPresent()) {
            Serie s = optionalSerie.get();
            return new SerieDTO(s.getId(),
                    s.getTitulo(),
                    s.getTemporada(),
                    s.getAno(),
                    s.getAvaliacao(),
                    s.getGenero(),
                    s.getAtores(),
                    s.getBaner(),
                    s.getSinopse());
        }

        return null;
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

    public List<EpisodioDTO> obterTodasTemporadas(Long id) {
        Optional<Serie> optionalSerie =  serieRepository.findById(id);
        if (optionalSerie.isPresent()) {
            Serie serie = optionalSerie.get();
            return serie.getEpisodios().stream()
                    .map(e -> new EpisodioDTO(
                            e.getId(),
                            e.getNumeroTemporada(),
                            e.getTitulo()
                    ))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodioDTO> obterTemporadaPorNumero(Long id, Long numeroTemporada) {
        List<Episodio> episodioList = serieRepository.buscarTemporadaPorNumero(id, numeroTemporada);
        return episodioList.stream()
                .map(e -> new EpisodioDTO(e.getId(), e.getNumeroTemporada(), e.getTitulo()))
                .collect(Collectors.toList());
    }
}
