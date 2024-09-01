package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.*;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.ConsumoApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {


    private static final String ENDERECO_URL = "https://www.omdbapi.com/?t=";
    private static final String CHAVE_API = "&apikey=bb1eaabe";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();
    private Scanner leitor = new Scanner(System.in);
    private SerieRepository serieRepository;
    private List<Serie> serieList;
    private Optional<Serie> serieBusca;

    public Principal(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public void exibeMenu() {

        Integer opcao = -1;

        while (opcao != 0) {
            System.out.println("""
                ##########################################
                1 - Buscar por série                     #
                2 - Buscar episódios                     #
                3 - Listar séries buscadas               # 
                4 - Buscar série pelo titulo             # 
                5 - Top 5 séries                         #
                6 - Filtrar por temporada e avaliação    # 
                7 - Buscar episodio por trecho           # 
                8 - Top 5 episodios por série            # 
                                                         #
                0 - Sair                                 #
                ##########################################
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
                case 4: {
                    buscarSeriePorTitulo();
                    break;
                }
                case 5: {
                    listarTop5Series();
                    break;
                }
                case 6: {
                    filtrarPorTemporadaEAvaliacao();
                    break;
                }
                case 7: {
                    buscaEpisodioPorTrecho();
                    break;
                }
                case 8: {
                    listarTop5Episodios();
                    break;
                }
                case 0: {
                    System.out.println("# Sistema finalizado");
                    break;
                }
            }
        }
    }

    public void buscarSerie() {
        System.out.println("Digite o nome da série: ");
        String nomeSerie = leitor.nextLine().replace(" ", "+");

        String json = consumoApi.obterDados(ENDERECO_URL + nomeSerie + CHAVE_API);
        DadosSerie dadosSerie = converteDados.obterDados(json, DadosSerie.class);
        Serie serie = new Serie(dadosSerie);
        this.serieRepository.save(serie);
        System.out.println(serie);
    }

    public void buscarEpisodios() {
        listarSeriesBuscadas();
        System.out.println("Selecione a serie pelo nome: ");
        String nomeSerie = leitor.nextLine();

        Optional<Serie> serieBuscada =  this.serieList.stream()
                        .filter(s -> s.getTitulo().toLowerCase().contains(nomeSerie.toLowerCase()))
                                .findFirst();

        if (serieBuscada.isPresent()) {
            Serie serie = serieBuscada.get();
            List<DadosTemporada> dadosTemporadaList = new ArrayList<>();

            for (int i = 1; i < serie.getTemporada(); i++) {
                String json = consumoApi.obterDados(ENDERECO_URL + serie.getTitulo().replace(" ", "+") + "&season=" + i + CHAVE_API);
                DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
                dadosTemporadaList.add(dadosTemporada);
            }

            List<Episodio> episodioList = dadosTemporadaList.stream()
                    .flatMap(t -> t.episodios().stream()
                            .map(e -> new Episodio(t.numero(), e)))
                    .collect(Collectors.toList());

            episodioList.forEach(System.out::println);
            serie.setEpisodios(episodioList);
            this.serieRepository.save(serie);
        }
    }

    public void listarSeriesBuscadas() {
        this.serieList = this.serieRepository.findAll();
        serieList.forEach(System.out::println);
    }

    public void buscarSeriePorTitulo() {
        System.out.println("Digite o nome da série: ");
        String nomeDaSerie = leitor.nextLine();

        serieBusca = serieRepository.findByTituloContainingIgnoreCase(nomeDaSerie);

        if (serieBusca.isPresent()) {
            System.out.println("Dados da serie: " + serieBusca.get());
        } else {
            System.out.println("Nenhuma série encontrada!");
        }
    }

    public void listarTop5Series() {
        List<Serie> top5Series = serieRepository.findTop5ByOrderByAvaliacaoDesc();
        System.out.println("Top 5 séries: ");
        top5Series.forEach(s -> System.out.println(
                s.getTitulo() + " avaliacao:" + s.getAvaliacao()
        ));
    }

    public void filtrarPorTemporadaEAvaliacao() {
        System.out.println("Digite o numero da temporada: ");
        int numTemporada = leitor.nextInt();
        leitor.nextLine();
        System.out.println("Digite a avaliacao: ");
        int avaliacao = leitor.nextInt();
        leitor.nextLine();

        List<Serie> seriesFiltradas = serieRepository.seriesPorTemporadaEAvaliacao(numTemporada, avaliacao);
        seriesFiltradas.forEach(s -> System.out.println(
                s.getTitulo() + " avaliacao:" + s.getAvaliacao()
        ));
    }

    public void buscaEpisodioPorTrecho() {
        System.out.println("Digite o trecho do título: ");
        String trecho = leitor.nextLine();
        List<Episodio> episodioList = serieRepository.episodiosPorTrecho(trecho);

        episodioList.forEach(e -> System.out.printf(
                "Serie: %s, Temporada: %s, Episodio: %s\n",
                e.getSerie().getTitulo(), e.getNumeroTemporada(), e.getTitulo()
        ));
    }

    public void listarTop5Episodios() {
        buscarSeriePorTitulo();
        if (serieBusca.isPresent()) {
            List<Episodio> episodioList = serieRepository.buscarTop5Episodios(serieBusca.get());

            episodioList.forEach(e -> System.out.printf(
                    "Serie: %s, Temporada: %s, Episodio: %s, Avaliacao: %s\n",
                    e.getSerie().getTitulo(), e.getNumeroTemporada(), e.getTitulo(), e.getAvaliacao()
            ));
        }
    }
}
