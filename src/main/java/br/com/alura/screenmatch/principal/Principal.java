package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.*;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.ConsumoApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {


    private static final String ENDERECO_URL = "https://www.omdbapi.com/?t=";
    private static final String CHAVE_API = "&apikey=bb1eaabe";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();
    private Scanner leitor = new Scanner(System.in);
    private Serie serie;
    private SerieRepository serieRepository;

    public Principal(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public void exibeMenu() {

        Integer opcao = -1;

        while (opcao != 0) {
            System.out.println("""
                #############################
                1 - Buscar por série        #
                2 - Buscar episódios        #
                3 - Listar séries buscadas  # 
                                            #
                0 - Sair                    #
                #############################
                """);
            opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao) {
                case 1: {
                    buscarSerie();
                    break;
                }
                case 2: {
                    buscarEpisodios();
                    break;
                }
                case 3: {
                    listarSeriesBuscadas();
                    break;
                }
                case 0: {
                    System.out.println("# Sistema finalizado");
                    break;
                }
            }
        }
    }

    public Serie buscarSerie() {
        System.out.println("Digite o nome da série: ");
        String nomeSerie = leitor.nextLine().replace(" ", "+");

        String json = consumoApi.obterDados(ENDERECO_URL + nomeSerie + CHAVE_API);
        DadosSerie dadosSerie = converteDados.obterDados(json, DadosSerie.class);
        Serie serie = new Serie(dadosSerie);
        this.serie = serie;
        this.serieRepository.save(serie);
        System.out.println(serie);
        return serie;
    }

    public void buscarEpisodios() {
        String nomeSerie =  this.serie.getTitulo().replace(" ", "+");
        System.out.println("Digite o nome do episódio: ");
        String nomeEpisodio = leitor.nextLine().replace(" ", "+");;

        List<DadosTemporada> dadosTemporadaList = new ArrayList<>();

        for (int i = 1; i <= this.serie.getTemporada(); i++) {
            String json = consumoApi.obterDados(ENDERECO_URL + nomeSerie + "&season=" + i + CHAVE_API);
            DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
            dadosTemporadaList.add(dadosTemporada);
        }

        List<Episodio> episodioList = dadosTemporadaList.stream()
                .flatMap(t -> t.episodios().stream()
                        .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A")
                                && e.titulo().toLowerCase().contains(nomeEpisodio.toLowerCase()))
                        .map(e -> new Episodio(t.numero(), e)))
                .collect(Collectors.toList());

        episodioList.forEach(System.out::println);
    }

    public void listarSeriesBuscadas() {
        List<Serie> serieList = this.serieRepository.findAll();
        serieList.forEach(System.out::println);
    }
}
