package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.*;
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

    public void exibeMenu() {

        Integer opcao = -1;

        while (opcao != 0) {
            System.out.println("""
                #############################
                1 - Buscar por série        #
                2 - Buscar episódios        # 
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
                    DadosSerie serie = buscarSerie();
                    buscarEpisodios(serie.titulo());
                    break;
                }
                case 0: {
                    System.out.println("# Sistema finalizado");
                    break;
                }
            }
        }
    }

    public DadosSerie buscarSerie() {
        System.out.println("Digite o nome da série: ");
        String nomeSerie = leitor.nextLine().replace(" ", "+");

        String json = consumoApi.obterDados(ENDERECO_URL + nomeSerie + CHAVE_API);
        DadosSerie serie = converteDados.obterDados(json, DadosSerie.class);
        System.out.println(serie);
        return serie;
    }

    public void buscarEpisodios(String tituloSerie) {
        String nomeSerie =  tituloSerie.replace(" ", "+");
        System.out.println("Digite o nome do episódio: ");
        String nomeEpisodio = leitor.nextLine().replace(" ", "+");;

        String json = consumoApi.obterDados(ENDERECO_URL + nomeSerie + CHAVE_API);
        DadosSerie serie = converteDados.obterDados(json, DadosSerie.class);
        List<DadosTemporada> dadosTemporadaList = new ArrayList<>();

        for (int i = 1; i <= serie.temporadas(); i++) {
            json = consumoApi.obterDados(ENDERECO_URL + nomeSerie + "&season=" + i + CHAVE_API);
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
}
